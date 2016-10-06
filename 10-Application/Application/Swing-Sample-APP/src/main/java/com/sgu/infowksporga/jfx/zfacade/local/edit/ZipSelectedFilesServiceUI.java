package com.sgu.infowksporga.jfx.zfacade.local.edit;

import java.awt.Container;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JProgressBar;
import javax.swing.ProgressMonitor;

import org.apache.commons.compress.archivers.ArchiveOutputStream;
import org.apache.commons.compress.archivers.ArchiveStreamFactory;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.gui.swing.util.UtilNotificationMsg;
import com.sgu.core.framework.util.Properties;
import com.sgu.core.framework.util.UtilIO;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.jfx.InfoWrkspOrgaFrame;
import com.sgu.infowksporga.jfx.component.StatusBar;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UserPreferencesConstant;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.zfacade.local.AbstractInfoWrkspOrgaServiceUI;

import lombok.Getter;
import lombok.Setter;
import net.infonode.docking.internalutil.InternalDockingUtil;

/**
 * Description : Zip Files on disk Facade class<br>
 */
@Service
@Getter
@Setter
public class ZipSelectedFilesServiceUI extends AbstractInfoWrkspOrgaServiceUI {

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(ZipSelectedFilesServiceUI.class);

  /**
   * Contain the path and the name of the zip file
   */
  private String saveZipPath;

  /**
   * Reference to the thread which create the zip to be able to stop it if necessary
   */
  private Thread zipThread;

  /**
   * Store the selected file to treat.
   */
  private List<String> selectedFiles;

  /**
   * keep Directory File Location
   */
  private boolean keepDirectoryFileLocation;

  /**
   * zip Exclude file list separated by ;
   */
  private String zipExcludeFile;

  /**
   * zip include file list separated by ;
   */
  private String zipIncludeFile;

  /**
   * Constructor<br>
   */
  public ZipSelectedFilesServiceUI() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  public boolean checkFieldsValidity(Container container, ProgressMonitor monitor) {

    final FileExplorerView currentView = (FileExplorerView) container;

    boolean isAllFieldsValid = true;

    final InfoWrkspOrgaFrame frame = GUISessionProxy.getInfoWrkspOrgaFrame();
    final StatusBar statusBar = frame.getStatusBar();
    final JProgressBar progressBar = statusBar.getProgressBar();

    final ArrayList<Object> views = new ArrayList<Object>(10);
    // If the current view is null search on all Workspace views
    if (currentView == null) {
      InternalDockingUtil.getViews(GUISessionProxy.getCurrentWorkspace().getRootWindow(), views);
    }
    else {
      views.add(currentView);
    }

    // Get list of selected files & Directories in Directory Desk views Tree
    selectedFiles = EditUtil.getSelectedFilesFromView(views);
    statusBar.getLblMessage().setText("zip.selected.files.message.load.files");

    final ArrayList<String> includeFiles = new ArrayList<String>();
    if (UtilString.isNotBlank(zipIncludeFile)) {
      final String[] result = zipIncludeFile.split(";");
      for (int i = 0; i < result.length; i++) {
        includeFiles.add(result[i]);
      }
    }

    final ArrayList<String> excludeFiles = new ArrayList<String>();
    if (UtilString.isNotBlank(zipExcludeFile)) {
      final String[] result = zipExcludeFile.split(";");
      for (int i = 0; i < result.length; i++) {
        excludeFiles.add(result[i]);
      }
    }

    selectedFiles = recursivelyFindAllFilesFromDirectories(selectedFiles, includeFiles, excludeFiles);

    // Their are no file selected by user
    if (selectedFiles.size() == 0) {
      Toolkit.getDefaultToolkit().beep();
      final String message = "<html><body>" + I18nHelperApp.getMessage("zip.selected.files.message.no.file.selected") + "</body></html>";
      UtilNotificationMsg.displayMessage(message);
      progressBar.setVisible(false);
      statusBar.getLblMessage().setText(message);

      isAllFieldsValid = false;
    }

    return isAllFieldsValid;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object execute(final Object... params) throws TechnicalException, BusinessException {

    // Update preferred zip destination path
    final Properties userLocalSettings = GUISession.getInstance().getUserLocalSettings();
    final String basePath = UtilIO.getBasePath(saveZipPath);
    userLocalSettings.put(UserPreferencesConstant.ZIP_EXPORT_DIRECTORY_SETTING, basePath);
    userLocalSettings.put(UserPreferencesConstant.ZIP_EXCLUDE_FILE, zipExcludeFile);
    userLocalSettings.put(UserPreferencesConstant.ZIP_INCLUDE_FILE, zipIncludeFile);
    UtilInfoWrkspOrga.updateUserSettingsOnDisk();

    final InfoWrkspOrgaFrame frame = GUISessionProxy.getInfoWrkspOrgaFrame();
    final StatusBar statusBar = frame.getStatusBar();
    final JProgressBar progressBar = statusBar.getProgressBar();
    progressBar.setVisible(true);

    progressBar.setValue(0);

    File zipFile = null;
    try {
      /* Create Output Stream that will have final zip files */
      zipFile = new File(saveZipPath);
      final OutputStream zipOutput = new FileOutputStream(zipFile);
      /* Create Archive Output Stream that attaches File Output Stream / and specifies type of compression */
      final ArchiveOutputStream logicalZip = new ArchiveStreamFactory().createArchiveOutputStream(ArchiveStreamFactory.ZIP, zipOutput);

      int count = 0;
      final int maxFiles = selectedFiles.size();

      for (final String file : selectedFiles) {
        if (zipThread == null || !zipThread.isInterrupted()) {
          statusBar.getLblMessage().setText(I18nHelperApp.getMessage("zip.selected.files.message.traitment.file", file));

          /* Create Archive entry - write header information */
          String archiveEntryName = FilenameUtils.getName(file);
          if (keepDirectoryFileLocation) {
            archiveEntryName = UtilString.replaceString(FilenameUtils.getPath(file), FilenameUtils.getPrefix(file), "") + "\\"
                               + FilenameUtils.getName(file);
          }

          final ZipArchiveEntry archiveEntry = new ZipArchiveEntry(archiveEntryName);
          logicalZip.putArchiveEntry(archiveEntry);
          /* Copy input file */
          IOUtils.copy(new FileInputStream(new File(file)), logicalZip);
          /* Close Archive entry, write trailer information */
          logicalZip.closeArchiveEntry();

          count++;
          // Update status progress bar
          progressBar.setValue(100 * count / maxFiles);
        }
        else {
          break;
        }
      }

      /* Finish addition of entries to the file */
      logicalZip.finish();
      /* Close output stream, our files are zipped */
      zipOutput.close();

      // Notify user Zip is finished
      if (!zipThread.isInterrupted()) {
        Toolkit.getDefaultToolkit().beep();
        final String message = "<html><body>" + I18nHelperApp.getMessage("zip.selected.files.message.finish", saveZipPath)
                               + "</body></html>";
        UtilNotificationMsg.displayMessage(message);
        progressBar.setVisible(false);
        statusBar.getLblMessage().setText(UtilString.replaceString(message, "<br/>", " :  "));
      }
      else {
        // Notify user Zip has been stopped
        statusBar.reinitProgressBar();
        progressBar.setVisible(false);
        UtilNotificationMsg.displayMessage("zip.selected.files.message.interrupted");
        statusBar.getLblMessage().setText("zip.selected.files.message.interrupted");
        if (zipFile != null && zipFile.exists()) {
          zipFile.delete();
        }
      }

    } catch (final Exception e) {
      statusBar.reinitProgressBar();
      statusBar.getLblMessage().setText("");
      progressBar.setVisible(false);
      if (zipFile != null && zipFile.exists()) {
        zipFile.delete();
      }
      throw new TechnicalException(e);
    }

    return null;

  }

  /**
   * Description : recursivelyFindAllFilesFromDirectories method <br>
   *
   * @param selectedFiles
   * @return
   */
  private List<String> recursivelyFindAllFilesFromDirectories(final List<String> selectedFiles, final ArrayList<String> includeFiles,
  final ArrayList<String> excludeFiles) {
    final List<String> fileLst = new ArrayList<String>(selectedFiles.size());

    for (final String path : selectedFiles) {
      addFileToList(fileLst, path, includeFiles, excludeFiles);
    }

    return fileLst;
  }

  /**
   * Description : addFileToList method <br>
   *
   * @param fileLst
   * @param path
   * @throws IOException
   */
  private void addFileToList(final List<String> fileLst, final String path, final ArrayList<String> includeFiles,
  final ArrayList<String> excludeFiles) {
    final File f = new File(path);
    if (!zipThread.isInterrupted()) {
      if (checkFileEligible(f, includeFiles, excludeFiles)) {
        if (f.isFile()) {
          fileLst.add(path);
        }

        else {

          final File[] children = f.listFiles();
          if (children != null) {
            for (final File child : children) {
              addFileToList(fileLst, child.getAbsolutePath(), includeFiles, excludeFiles);
            }
          }
        }
      }
    }
  }

  /**
   * Description : checkFileEligible method <br>
   *
   * @param file
   * @return
   */
  protected boolean checkFileEligible(final File file, final ArrayList<String> includeFiles, final ArrayList<String> excludeFiles) {

    for (final String exclusion : excludeFiles) {
      final String pattern = exclusion.replace(".", "\\.").replace("*", ".*");
      if (file.getName().matches(pattern)) {
        return false;
      }
    }

    for (final String inclusion : includeFiles) {
      final String pattern = inclusion.replace(".", "\\.").replace("*", ".*");
      if (file.getName().matches(pattern)) {
        return true;
      }
    }

    return true;
  }

}

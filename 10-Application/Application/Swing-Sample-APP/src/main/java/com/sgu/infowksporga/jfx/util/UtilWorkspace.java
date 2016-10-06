package com.sgu.infowksporga.jfx.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.dialog.GMessageBox;
import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;
import com.sgu.core.framework.gui.swing.docking.GDockingUtil;
import com.sgu.core.framework.gui.swing.tree.GTreeNode;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.serialization.xml.GXStream;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.tree.nodevo.WorkspaceNodeVo;
import com.sgu.infowksporga.jfx.workspace.AbstractWorkspace;
import com.sgu.infowksporga.jfx.zfacade.remote.workspace.SaveWorkspaceLayoutFacade;

import net.infonode.docking.DockingWindow;
import net.infonode.docking.RootWindow;
import net.infonode.docking.SplitWindow;
import net.infonode.docking.TabWindow;
import net.infonode.docking.View;
import net.infonode.tabbedpanel.TabAdapter;
import net.infonode.tabbedpanel.TabDragEvent;
import net.infonode.tabbedpanel.TabListener;
import net.infonode.tabbedpanel.TabStateChangedEvent;
import net.infonode.tabbedpanel.TabbedPanel;

/**
 * Description : WorkspaceUtil class<br>
 */
public final class UtilWorkspace {

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(UtilWorkspace.class);

  /**
   * The tab listener
   */
  private static TabListener tabListener = new TabAdapter() {
    @Override
    public void tabDropped(final TabDragEvent event) {
      UtilWorkspace.workspaceIsModified();
    }

    @Override
    public void tabSelected(final TabStateChangedEvent event) {
      if (event != null && event.getTab() != null) {
        LOGGER.debug("Selected Tab = {}", event.getTab().toString());
      }
    }
  };

  /**
   * Constructor<br>
   */
  private UtilWorkspace() {
  }

  /**
   * Description : checkFieldsValidity method <br>
   * 
   * @param workspaceDialog The dialog box with fields to check
   * @return true if all fields ok
   */
  /*
   * public static boolean checkFieldsValidity(final WorkspacePropertiesDlg workspaceDialog) {
   * final String workspaceCode = workspaceDialog.getTxtCode().getText();
   * final String projectCode = (String) workspaceDialog.getCbProjectCode().getSelectedItem();
   * final String workspaceVisibility = (String) workspaceDialog.getCbVisibility().getSelectedItem();
   * final String workspaceType = (String) workspaceDialog.getCbType().getSelectedItem();
   * UtilGUI.markAllFieldsAsValid(workspaceDialog.getContentPane());
   * boolean isAllFieldsValid = true;
   * if (UtilString.isBlank(workspaceCode)) {
   * workspaceDialog.getTxtCode().markInvalid(UtilGUI.getMandatoryMessageForField(workspaceDialog.getLblCode().getI18nKey()));
   * isAllFieldsValid = false;
   * }
   * // Verify workspace can be visible in tree view
   * if (isAllFieldsValid) {
   * String workspacePossibility = workspaceVisibility + "_" + workspaceType;
   * if (UtilString.isNotBlank(projectCode)) {
   * workspacePossibility += "_" + projectCode;
   * }
   * final List<String> workspaceCreationPossibilities = GUISessionProxy.getWorkspaceCreationPossibilities();
   * if (!workspaceCreationPossibilities.contains(workspacePossibility)) {
   * String possibilities = workspaceCreationPossibilities.toString();
   * possibilities = possibilities.replace("[", " - ");
   * possibilities = possibilities.replace("]", "");
   * possibilities = possibilities.replaceAll(", ", "<br/> - ");
   * final String error = I18nHelperApp.getMessage("workspace.properties.error.combination", possibilities);
   * UtilDlgMessage.error("dialog.error", error, "", 750, 300, false);
   * workspaceDialog.getTxtCode().markInvalid(error);
   * isAllFieldsValid = false;
   * }
   * }
   * return isAllFieldsValid;
   * }
   */

  /**
   * Description :getCurrentWorkspaceLayout method <br>
   * 
   * @return current workspace layout
   */
  public static byte[] getCurrentWorkspaceLayout() {
    // Transform Workspace Layout to byte array
    ByteArrayOutputStream bos;
    try {
      bos = new ByteArrayOutputStream();
      final GXStream xstream = new GXStream();
      final ObjectOutputStream out = xstream.createObjectOutputStream(bos);
      GUISessionProxy.getCurrentWorkspace().getRootWindow().write(out, true);
      out.close();
    } catch (final IOException e) {
      throw new TechnicalException(e);
    }

    final byte[] layout = bos.toByteArray();
    if (LOGGER.isDebugEnabled()) {
      final String xml = new String(layout);
      System.out.println(xml);
    }
    return layout;
  }

  /**
   * Description : Used this method to detect workspace change and ask user to save it
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "workspace.change.confirm.message",
                value = "Le workspace suivant <b>{0}</b> a changé.\\nVoulez vous le sauvegarder avant de poursuivre ? "), // Force /n
  })
  public static int saveWorkspaceIfChanged() {
    // Check if the previous workspace don't has to be saved before opening this new one
    if (Boolean.TRUE.equals(GUISessionProxy.isCurrentWorkspaceHasChanged())) {
      final AbstractWorkspace currentWorkspace = GUISessionProxy.getCurrentWorkspace();
      final int result = UtilDlgMessage.question(I18nHelperApp.getMessage("workspace.change.confirm.message",
                                                                          currentWorkspace.getWorkspaceDto().getWorkspace().getName()),
                                                 GMessageBox.YES | GMessageBox.NO | GMessageBox.CANCEL);

      if (result == GMessageBox.YES) {

        SaveWorkspaceLayoutFacade facade = (SaveWorkspaceLayoutFacade) SpringBeanHelper.getImplementationByInterface(SaveWorkspaceLayoutFacade.class);
        GUISession.getInstance().getServiceDelegate().execute(facade, null);
      }

      if (result == GMessageBox.CANCEL) {
        return result;
      }
    }

    return -1;
  }

  /**
   * Description : add New View To Workspace method <br>
   * As a split Window to facilitate the workspace integration
   * 
   * @param newView the view to add
   */
  public static void addNewViewToWorkspace(final View newView) {
    addNewViewToWorkspace(GUISessionProxy.getCurrentWorkspace(), newView, true);
  }

  /**
   * Adds the new view to workspace.
   *
   * @param workspace the workspace
   * @param newView the new view
   */
  public static void addNewViewToWorkspace(AbstractWorkspace workspace, final View newView, boolean horizontal) {
    final RootWindow rootWindow = workspace.getRootWindow();

    final DockingWindow w = rootWindow.getWindow();
    if (w != null) {
      final SplitWindow splitWindow = new SplitWindow(horizontal);
      splitWindow.setWindows(w, newView);
      rootWindow.setWindow(splitWindow);
    }
    else {
      rootWindow.setWindow(newView);
    }

    // Transfer focus to the new created view
    newView.restoreFocus();

  }

  /**
   * Description : getSelectedWorkspaceNodeVo method <br>
   * 
   * @return the WorkspaceNodeVo
   */
  public static WorkspaceNodeVo getSelectedWorkspaceNodeVo() {
    // Get the Selected project node parent
    final PerspectivePanel perspective = (PerspectivePanel) GUISessionProxy.getCurrentPerspective();
    final GTreeNode treeNode = (GTreeNode) perspective.getTree().getLastSelectedPathComponent();

    if (treeNode.getUserObject() instanceof WorkspaceNodeVo) {
      return (WorkspaceNodeVo) treeNode.getUserObject();
    }

    return null;
  }

  /**
   * Description : workspaceIsModified method <br>
   */
  public static void workspaceIsModified() {
    GUISessionProxy.setCurrentWorkspaceHasChanged(Boolean.TRUE);
    GUISessionProxy.getInfoWrkspOrgaFrame().getWorkspaceMapView().refreshWorkspaceMapHTML();
  }

  /**
   * Description : update Workspace Tabs Listener method <br>
   */
  public static void updateWorkspaceTabsListener() {
    // We modify the tab listener if drop modify the layout
    final RootWindow rootWindow = GUISessionProxy.getCurrentWorkspace().getRootWindow();
    final ArrayList<TabWindow> tabWindows = new ArrayList<TabWindow>();
    GDockingUtil.getTabWindowList(rootWindow, tabWindows);

    // First remove listener for all tabs
    for (final TabWindow tabWindow : tabWindows) {
      final TabbedPanel tp = GDockingUtil.getTabbedPanelFor(tabWindow);
      if (tabListener != null) {
        tp.removeTabListener(tabListener);
      }
    }

    // Secondly add listener for all tabs
    for (final TabWindow tabWindow : tabWindows) {
      final TabbedPanel tp = GDockingUtil.getTabbedPanelFor(tabWindow);
      tp.addTabListener(tabListener);
    }

  }

}

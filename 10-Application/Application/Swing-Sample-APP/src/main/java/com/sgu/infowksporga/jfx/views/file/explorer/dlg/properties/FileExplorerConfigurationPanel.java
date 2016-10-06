package com.sgu.infowksporga.jfx.views.file.explorer.dlg.properties;

import java.io.File;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.GLabelField;
import com.sgu.core.framework.gui.swing.MessageTypeEnum;
import com.sgu.core.framework.gui.swing.overlay.GDefaultOverlayable;
import com.sgu.core.framework.gui.swing.overlay.UtilOverlay;
import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractSpecificViewConfigurationPanel;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerViewIdentifier;
import com.sgu.infowksporga.jfx.views.file.explorer.dlg.properties.listener.LstnTxtFolderFocusListener;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : FileExplorerConfigurationPanel class<br>
 */
@Getter
@Setter
public class FileExplorerConfigurationPanel extends AbstractSpecificViewConfigurationPanel {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -8774555177914892315L;

  /**
   * Used to associate CMMI Process to the view
   */
  //protected CmmiProcessSelectionPanel cmmiProcessSelectionPanel;

  /**
   * Label View Folder
   */
  private GLabel lblFolder;

  /**
   * txt view target Folder
   */
  private GTextField txtFolder;

  /**
   * Constructor<br>
   */
  public FileExplorerConfigurationPanel(final FileExplorerViewPropertiesDlg dialog) {
    super(dialog);
  }

  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "file.explorer.view.properties.dlg.lbl.folder.text", value = "Folder"), // Force /n
  })
  public void createUI() {
    super.createUI();

    lblFolder = new GLabelField();
    lblFolder.setBundleConfiguration("file.explorer.view.properties.dlg.lbl.folder", I18nHelperApp.getI18nHelper());
    lblFolder.setMandatory(true);

    txtFolder = new GTextField();
    txtFolder.addFocusListener(new LstnTxtFolderFocusListener(this));

    final GDefaultOverlayable txtUrlOverlay = UtilOverlay.addOverlayErrorRenderer(txtFolder);
    txtFolder.setOverlay(txtUrlOverlay);
    txtFolder.setLabel(lblFolder);

    add(lblFolder);
    add(txtFolder, "width 500px");
  }

  /**
   * Validate url.
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "file.explorer.view.properties.dlg.folder.error.tooltip",
                value = "Attention ce répertoire n'est pas accessible pour le moment !"), // Force /n
                @I18nProperty(key = "file.explorer.view.properties.dlg.folder.ok.tooltip", value = "Ce dossier est bien accessible !"), // Force /n
  })
  public void validateFolder() {
    getTxtFolder().clearMessage();

    final String folder = getTxtFolder().getText();
    final File folderTester = new File(folder);

    if (folderTester != null && folderTester.isDirectory()) {
      getTxtFolder().showOverlayMessage("file.explorer.view.properties.dlg.folder.ok.tooltip", MessageTypeEnum.VALID);

    }
    else {
      getTxtFolder().showErrorMessage("file.explorer.view.properties.dlg.folder.error.tooltip");
    }
  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();

    final FileExplorerView viewUI = (FileExplorerView) getDialog().getView();
    final FileExplorerViewIdentifier viewIdentifier = viewUI.getViewIdentifier();

    // Get view Name
    String viewEntityName = viewIdentifier.getViewEntityName();
    getDialog().getPnlIdentity().getTxtViewTitle().setText(viewEntityName);

    // Get Specific view
    // For the moment we assume view are not base on a master
    View viewEntity = getWorkspacesViewsDto().getWorkspaceViewsByName().get(viewIdentifier.getViewEntityName());
    viewEntity.toString();

    final String folderStr = viewEntity.getAttributesAsMap().get(ViewAttribute.FILE_EXPLORER_PATH).getValue();
    getTxtFolder().setText(folderStr);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setDisplayMode(final int mode) {
    super.setDisplayMode(mode);

  }

}

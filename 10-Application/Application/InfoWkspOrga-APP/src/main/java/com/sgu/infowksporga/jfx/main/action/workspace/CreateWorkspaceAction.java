package com.sgu.infowksporga.jfx.main.action.workspace;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.jfx.action.AppBaseAction;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : SaveWorkspaceAction class<br>
 */
@Slf4j
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = CreateWorkspaceAction.ACTION_BUNDLE_KEY + I18NConstant.TEXT, value = "Nouveau Workspace"), // Force /n
              @I18nProperty(key = CreateWorkspaceAction.ACTION_BUNDLE_KEY + I18NConstant.TOOLTIP_TEXT,
              value = "<html>Création d'un nouveau workspace sous le workspace (arbre) selectionné</html>"), // Force /n 
              @I18nProperty(key = CreateWorkspaceAction.ACTION_BUNDLE_KEY + I18NConstant.ICON, value = "/icons/workspace-add.png"), // Force /n
              @I18nProperty(key = CreateWorkspaceAction.ACTION_BUNDLE_KEY + I18NConstant.MNEMONIC, value = "N"), // Force /n
              @I18nProperty(key = CreateWorkspaceAction.ACTION_BUNDLE_KEY + I18NConstant.SHORTCUT, value = "Control+N"), // Force /n
              @I18nProperty(key = CreateWorkspaceAction.ACTION_BUNDLE_KEY + I18NConstant.NAME, value = "ACTION_WORKSPACE_NEW"), // Force /n
})
public class CreateWorkspaceAction extends AppBaseAction<ActionEvent> {

  /** The Constant ACTION_PROP_BASE. */
  public static final String ACTION_BUNDLE_KEY = "application.action.workpace.create";

  /**
   * The Constructor.
   *
   * @param control The control
   * @param bundleConfigKey The bundle config key
   */
  public CreateWorkspaceAction(final Control control) {
    super(control, ACTION_BUNDLE_KEY, I18nHelperApp.getI18nHelper());
    // Add action to Application action manager
    GUISessionProxy.getApplication().getActionManager().addEntry(control, this);
  }

  /** {@inheritDoc} */
  @Override
  public void handle(final ActionEvent event) {
    final GDockPane dockPane = GUISessionProxy.getApplication().getApplicationScreen().getView().getDockPane();

    UtilGUIMessage.showNotYetImplementedDlg();

  }

}

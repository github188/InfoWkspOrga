package com.sgu.infowksporga.jfx.main.action.workspace;

import java.util.List;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.control.pane.dock.serialization.XDock;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.core.framework.util.UtilDockFX;
import com.sgu.core.framework.util.UtilXml;
import com.sgu.infowksporga.jfx.action.AppBaseAction;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : SaveWorkspaceAction class<br>
 */
@Slf4j
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = SaveWorkspaceAction.ACTION_BUNDLE_KEY + I18NConstant.TEXT, value = "Save Workspace"), // Force /n
              @I18nProperty(key = SaveWorkspaceAction.ACTION_BUNDLE_KEY + I18NConstant.TOOLTIP_TEXT,
              value = "<html>Enregistre le Workspace courant en base de données</html>"), // Force /n 
              @I18nProperty(key = SaveWorkspaceAction.ACTION_BUNDLE_KEY + I18NConstant.ICON, value = "/icons/workspace-save.png"), // Force /n
              @I18nProperty(key = SaveWorkspaceAction.ACTION_BUNDLE_KEY + I18NConstant.MNEMONIC, value = "S"), // Force /n
              @I18nProperty(key = SaveWorkspaceAction.ACTION_BUNDLE_KEY + I18NConstant.SHORTCUT, value = "Control+S"), // Force /n
              @I18nProperty(key = SaveWorkspaceAction.ACTION_BUNDLE_KEY + I18NConstant.NAME, value = "ACTION_WORKSPACE_SAVE"), // Force /n
})
public class SaveWorkspaceAction extends AppBaseAction<ActionEvent> {

  /** The Constant ACTION_PROP_BASE. */
  public static final String ACTION_BUNDLE_KEY = "application.action.workpace.save";

  /**
   * The Constructor.
   *
   * @param control The control
   * @param bundleConfigKey The bundle config key
   */
  public SaveWorkspaceAction(final Control control) {
    super(control, ACTION_BUNDLE_KEY, I18nHelperApp.getI18nHelper());
    // Add action to Application action manager
    GUISessionProxy.getCurrentApplication().getActionManager().addEntry(control, this);
  }

  /** {@inheritDoc} */
  @Override
  public void handle(final ActionEvent event) {
    final GDockPane dockPane = GUISessionProxy.getCurrentApplication().getApplicationScreen().getView().getDockPane();

    final List<Node> root = dockPane.getChildren();
    XDock xDock = new XDock();
    UtilDockFX.serializeDockFxStructureRecursively(root, xDock, 0);

    log.debug(UtilXml.jaxbObjectToXml(xDock, XDock.class));

  }

}

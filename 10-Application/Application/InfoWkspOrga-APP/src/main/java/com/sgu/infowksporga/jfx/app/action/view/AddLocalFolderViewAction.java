package com.sgu.infowksporga.jfx.app.action.view;

import org.dockfx.DockPos;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockNode;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.business.entity.enumeration.DockPosEnum;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilView;
import com.sgu.infowksporga.jfx.view.web.ui.WebViewModel;

import javafx.event.ActionEvent;
import javafx.scene.control.Control;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : AddLocalFolderViewAction class<br>
 */
@Slf4j
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = AddLocalFolderViewAction.ACTION_BUNDLE_KEY + I18NConstant.TEXT, value = "Folder"), // Force /n
              @I18nProperty(key = AddLocalFolderViewAction.ACTION_BUNDLE_KEY + I18NConstant.TOOLTIP_TEXT,
              value = "<html>Ajoute une vue de type : <b>Répertoire du poste de travail</b></html>"), // Force /n 
              @I18nProperty(key = AddLocalFolderViewAction.ACTION_BUNDLE_KEY + I18NConstant.ICON, value = "/icons/folder.png"), // Force /n
              @I18nProperty(key = AddLocalFolderViewAction.ACTION_BUNDLE_KEY + I18NConstant.MNEMONIC, value = "F"), // Force /n
              @I18nProperty(key = AddLocalFolderViewAction.ACTION_BUNDLE_KEY + I18NConstant.SHORTCUT, value = "Control+F"), // Force /n
              @I18nProperty(key = AddLocalFolderViewAction.ACTION_BUNDLE_KEY + I18NConstant.NAME, value = "ACTION_ADD_LOCAL_FOLDER_VIEW"), // Force /n
})
public class AddLocalFolderViewAction extends AAddViewAction {

  /** The Constant ACTION_PROP_BASE. */
  public static final String ACTION_BUNDLE_KEY = "application.action.add.local.folder";

  /**
   * The Constructor.
   *
   * @param control The control
   * @param bundleConfigKey The bundle config key
   */
  public AddLocalFolderViewAction(final Control control) {
    super(control, ACTION_BUNDLE_KEY, I18nHelperApp.getI18nHelper());
    // Add action to Application action manager
    GUISessionProxy.getApplication().getActionManager().addEntry(control, this);
  }

  /** {@inheritDoc} */
  @Override

  public void handle(final ActionEvent event) {

    // TODO show View Configuration instead direct creation
    final GDockNode dockNode = UtilView.buildDefaultViewLocalFolder();

    final DockPosEnum docPosEnum = ((WebViewModel) dockNode.getScreen().getModel()).getViewEntity().getDockPos();
    UtilView.addDockNodeView(dockNode, DockPos.valueOf(docPosEnum.getValue()), null);

  }

}

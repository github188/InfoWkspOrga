package com.sgu.infowksporga.jfx.menu.action.edit;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.rules.IsAtLeastViewFileSelectedRule;

/**
 * Description : Unselect Nodes For All Directory Desk View Action class<br>
 * Unselect all tree item from directory Desk view
 * 
 * @author SGU
 */
public class UnselectNodesForAllFileExplorerViewFilesAction extends AbstractInfoWrkspOrgaAction {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.edit.unselect.node.for.all.file.explorer.view.text", value = "Effacer sélection"), // Force /n
                @I18nProperty(key = "menu.edit.unselect.node.for.all.file.explorer.view.name",
                value = "UNSELECT_NODES_FOR_ALL_DIRECTORIES_DESK_VIEW"), // Force /n
                @I18nProperty(key = "menu.edit.unselect.node.for.all.file.explorer.view.tooltip",
                value = "Efface les sélections de fichiers/dosiers dans de toutes les vues (même non visibles)"), // Force /n
                @I18nProperty(key = "menu.edit.unselect.node.for.all.file.explorer.view.mnemonic", value = "f"), // Force /n
                @I18nProperty(key = "menu.edit.unselect.node.for.all.file.explorer.view.shortcut", value = "control E"), // Force /n
                @I18nProperty(key = "menu.edit.unselect.node.for.all.file.explorer.view.icon", value = "/icons/eraser.png"), // Force /n
  })
  public UnselectNodesForAllFileExplorerViewFilesAction() {
    super("menu.edit.unselect.node.for.all.file.explorer.view");
    setRule(new IsAtLeastViewFileSelectedRule(null));
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    //final UnselectAllNodesForAllFileViewFacade facade = SpringBeanHelper.getImplementationByInterface(UnselectAllNodesForAllFileViewFacade.class);
    //GUISession.getInstance().getServiceDelegate().execute(facade, null);
    UtilGUI.showNotYetImplementedDlg();
  }
}

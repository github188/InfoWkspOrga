package com.sgu.infowksporga.jfx.views.file.explorer.action.all;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.rules.IsAtLeastViewFileSelectedRule;
import com.sgu.infowksporga.jfx.zfacade.local.edit.UnselectAllNodesForAllFileViewServiceUI;

/**
 * Description : Unselect Nodes For All Directory Desk View Action class<br>
 * Unselect all tree item from directory Desk view
 *
 * @author SGU
 */
public class UnselectNodesForAllFileViewAction extends AbstractInfoWrkspOrgaAction {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "menu.edit.unselect.node.for.all.file.explorer.view.text",
                value = "Désélectionner tous les fichiers/dossiers"), // Force \n
                @I18nProperty(key = "menu.edit.unselect.node.for.all.file.explorer.view.tooltip",
                value = "Désélectionne tous les fichiers/dossiers sélectionnés dans les différentes vues"), // Force \n
                @I18nProperty(key = "menu.edit.unselect.node.for.all.file.explorer.view.mnemonic", value = "D"), // Force \n
                @I18nProperty(key = "menu.edit.unselect.node.for.all.file.explorer.view.icon", value = "/icons/eraser.png"), // Force \n
  })
  public UnselectNodesForAllFileViewAction() {
    super("menu.edit.unselect.node.for.all.file.explorer.view");
    setRule(new IsAtLeastViewFileSelectedRule(null));
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    final UnselectAllNodesForAllFileViewServiceUI serviceUI = SpringBeanHelper.getImplementationByInterface(UnselectAllNodesForAllFileViewServiceUI.class);
    serviceUI.execute(null);
  }
}

package com.sgu.infowksporga.jfx.menu.action.edit;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.rules.IsAtLeastViewFileSelectedRule;

/**
 * Description : CutFilesAction class<br>
 * Cut all workspace selected files from directory view to clipboard
 * 
 * @author SGU
 */
public class CutFileExplorerViewFilesToClipboardAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.edit.cut.file.explorer.view.files.text", value = "Couper"), // Force /n
                @I18nProperty(key = "menu.edit.cut.file.explorer.view.files.name", value = "CUT_SELECTED_DIRECTORIES_FILES"), // Force /n
                @I18nProperty(key = "menu.edit.cut.file.explorer.view.files.tooltip",
                value = "Coupe les fichiers sélectionnés de toutes les vues (même non visibles)"), // Force /n
                @I18nProperty(key = "menu.edit.cut.file.explorer.view.files.mnemonic", value = "C"), // Force /n
                @I18nProperty(key = "menu.edit.cut.file.explorer.view.files.shortcut", value = "control X"), // Force /n
                @I18nProperty(key = "menu.edit.cut.file.explorer.view.files.icon", value = "/icons/cut.png"), // Force /n
  })
  public CutFileExplorerViewFilesToClipboardAction() {
    super("menu.edit.cut.file.explorer.view.files");
    setRule(new IsAtLeastViewFileSelectedRule(null));
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    //final CopyCutFilesToClipboardFacade facade = SpringBeanHelper.getImplementationByInterface(CopyCutFilesToClipboardFacade.class);
    //facade.setCutOrCopy(CopyCutFilesToClipboardFacade.CUT);
    //GUISession.getInstance().getServiceDelegate().execute(facade, null);
    UtilGUI.showNotYetImplementedDlg();
  }
}

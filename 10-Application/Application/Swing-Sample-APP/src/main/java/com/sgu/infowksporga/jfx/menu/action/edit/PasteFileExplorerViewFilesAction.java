package com.sgu.infowksporga.jfx.menu.action.edit;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.rules.IsAtLeastViewFileSelectedRule;

/**
 * Description : PasteFilesAction class<br>
 * Paste all workspace selected files from directory view to clipboard
 * 
 * @author SGU
 */
public class PasteFileExplorerViewFilesAction extends AbstractInfoWrkspOrgaAction {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.edit.paste.file.explorer.view.files.text", value = "Coller"), // Force /n
                @I18nProperty(key = "menu.edit.paste.file.explorer.view.files.name", value = "PASTE_SELECTED_DIRECTORIES_FILES"), // Force /n
                @I18nProperty(key = "menu.edit.paste.file.explorer.view.files.tooltip",
                value = "Colle les fichiers/dossiers sélectionnés de toutes les vues (même non visibles) dans le dernier dossier selectionné"), // Force /n
                @I18nProperty(key = "menu.edit.paste.file.explorer.view.files.mnemonic", value = "o"), // Force /n
                @I18nProperty(key = "menu.edit.paste.file.explorer.view.files.shortcut", value = "control V"), // Force /n
                @I18nProperty(key = "menu.edit.paste.file.explorer.view.files.icon", value = "/icons/paste.png"), // Force /n
  })
  public PasteFileExplorerViewFilesAction() {
    super("menu.edit.paste.file.explorer.view.files");
    setRule(new IsAtLeastViewFileSelectedRule(null));
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    //final PasteFilesFromClipboardFacade facade = SpringBeanHelper.getImplementationByInterface(PasteFilesFromClipboardFacade.class);
    //GUISession.getInstance().getServiceDelegate().execute(facade);
    UtilGUI.showNotYetImplementedDlg();

  }
}

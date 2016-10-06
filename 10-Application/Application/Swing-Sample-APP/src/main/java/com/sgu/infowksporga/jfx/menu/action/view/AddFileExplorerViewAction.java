package com.sgu.infowksporga.jfx.menu.action.view;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;
import com.sgu.infowksporga.jfx.rules.workspace.IsWorkspaceSelectedRule;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilWorkspace;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerViewIdentifier;

/**
 * Description : AddFileExplorerViewAction class<br>
 *
 * @author SGU
 */
public class AddFileExplorerViewAction extends AbstractAddViewAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.workspace.views.file.explorer.text", value = "Explorateur de Fichier"), // Force /n
                @I18nProperty(key = "menu.workspace.views.file.explorer.name", value = "ADD_FILE_EXPLORER_VIEW"), // Force /n
                @I18nProperty(key = "menu.workspace.views.file.explorer.tooltip",
                value = "<html>Ajoute une vue de type : <b>Explorateur de fichier et dossier</b><br>(comme windows explorer).</html>"), // Force /n
                @I18nProperty(key = "menu.workspace.views.file.explorer.mnemonic", value = "E"), // Force /n
                @I18nProperty(key = "menu.workspace.views.file.explorer.shortcut", value = "control E"), // Force /n
                @I18nProperty(key = "menu.workspace.views.file.explorer.icon", value = "/icons/folder.png"), // Force /n
  })
  public AddFileExplorerViewAction() {
    super("menu.workspace.views.file.explorer");
    setRule(new IsWorkspaceSelectedRule());
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    View view = new View();
    view.setOwner(GUISessionProxy.getGuiSession().getCurrentUser());
    view.setCategory("Default");
    view.setPartage(PartageEnum.PUBLIC);
    view.setWorkspaceId(getWorkspaceDto().getWorkspace().getId());
    view.setJavaType(FileExplorerViewIdentifier.class.getName());
    view.setName("My Folder");

    ViewAttribute attribute = new ViewAttribute();
    attribute.setName(ViewAttribute.FILE_EXPLORER_PATH);
    attribute.setValue("G:\\sauvegarde\\Tools\\RegExpression");
    view.addAttribute(attribute);

    getWorkspaceDto().getWorkspace().addView(view);

    FileExplorerViewIdentifier viewIdentifier = new FileExplorerViewIdentifier(null, view.getName());
    final FileExplorerView newViewUI = new FileExplorerView(viewIdentifier);

    GUISessionProxy.setCurrentWorkspaceHasChanged(true);

    /*
     * final FileExplorerViewPropertiesDlg dlg = new FileExplorerViewPropertiesDlg(newView);
     * dlg.pack();
     * dlg.centerDialogVsScreen();
     * dlg.setVisible(true);
     * if (dlg.isCancelClicked()) {
     * return;
     * }
     */

    UtilWorkspace.addNewViewToWorkspace(newViewUI);

  }
}

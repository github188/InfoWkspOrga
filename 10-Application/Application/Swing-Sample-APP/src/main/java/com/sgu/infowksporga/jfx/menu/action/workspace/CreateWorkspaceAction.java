package com.sgu.infowksporga.jfx.menu.action.workspace;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.tree.nodevo.WorkspaceNodeVo;
import com.sgu.infowksporga.jfx.rules.workspace.IsWorkspaceSelectedRule;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.workspace.dlg.properties.WorkspaceDlg;

/**
 * Description : CreateWorkspaceAction class<br>
 * This action is used to create a new workspace
 * 
 * @author SGU
 */
public class CreateWorkspaceAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.workpace.new.text", value = "Nouveau"), // Force /n
                @I18nProperty(key = "menu.workpace.new.name", value = "WORKSPACE_NEW"), // Force /n
                @I18nProperty(key = "menu.workpace.new.tooltip",
                value = "<html>Création d'un nouveau workspace sous le workspace (arbre) selectionné</html>"), // Force /n
                @I18nProperty(key = "menu.workpace.new.mnemonic", value = "N"), // Force /n
                @I18nProperty(key = "menu.workpace.new.shortcut", value = "control N"), // Force /n
                @I18nProperty(key = "menu.workpace.new.icon", value = "/icons/workspace-add.png"), // Force /n
  })
  public CreateWorkspaceAction() {
    super("menu.workpace.new");
    setRule(new IsWorkspaceSelectedRule());
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {

    final PerspectivePanel perspective = (PerspectivePanel) GUISessionProxy.getCurrentPerspective();
    final WorkspaceNodeVo parentWorkspaceNodeVo = (WorkspaceNodeVo) perspective.getTree().getLastSelectedUserObject();
    final Workspace parentWorkspace = parentWorkspaceNodeVo.getWorkspace();

    Workspace newWorkspace = new Workspace();
    newWorkspace.setId("");
    newWorkspace.setOwner(GUISessionProxy.getGuiSession().getCurrentUser());
    newWorkspace.setParent(parentWorkspace);

    newWorkspace.setName("New-Name");
    newWorkspace.setDescription("La description de mon nouveau workspace");

    newWorkspace.setIcon("/icons/edit.png");
    newWorkspace.setColor("0, 0, 0");

    WorkspaceDlg dialog = new WorkspaceDlg(WorkspaceDlg.MODE_CREATE, newWorkspace);
    dialog.showDialog();

  }

}

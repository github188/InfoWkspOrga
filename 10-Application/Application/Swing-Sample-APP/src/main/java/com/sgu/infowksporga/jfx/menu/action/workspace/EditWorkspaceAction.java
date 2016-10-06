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
 * Description : EditWorkspaceAction class<br>
 * This action is used to update a workspace
 * 
 * @author SGU
 */
public class EditWorkspaceAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "menu.workpace.edit.text", value = "Modifier"), // Force /n
                @I18nProperty(key = "menu.workpace.edit.name", value = "WORKSPACE_UPDATE"), // Force /n
                @I18nProperty(key = "menu.workpace.edit.tooltip", value = "<html>Modification du workspace selectionné</html>"), // Force /n
                @I18nProperty(key = "menu.workpace.edit.mnemonic", value = "M"), // Force /n
                @I18nProperty(key = "menu.workpace.edit.shortcut", value = "control M"), // Force /n
                @I18nProperty(key = "menu.workpace.edit.icon", value = "/icons/workspace-edit.png"), // Force /n
  })
  public EditWorkspaceAction() {
    super("menu.workpace.edit");
    setRule(new IsWorkspaceSelectedRule());
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {

    final PerspectivePanel perspective = (PerspectivePanel) GUISessionProxy.getCurrentPerspective();
    final WorkspaceNodeVo selectedWorkspaceNodeVo = (WorkspaceNodeVo) perspective.getTree().getLastSelectedUserObject();
    final Workspace selectedWorkspace = selectedWorkspaceNodeVo.getWorkspace();

    WorkspaceDlg dialog = new WorkspaceDlg(WorkspaceDlg.MODE_UPDATE, selectedWorkspace);
    dialog.showDialog();

  }

}

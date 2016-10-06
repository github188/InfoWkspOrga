package com.sgu.infowksporga.jfx.perspective.tree.listener;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTree;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.zfacade.remote.workspace.ShowWorkspaceFacade;

/**
 * Description : Display Workspace related to tree selection<br>
 */
public class PerspectiveTreeSelectionListener implements TreeSelectionListener {
  /**
   * Keep reference to the tree
   */
  private final PerspectiveTree perspectiveTree;

  /**
   * Constructor<br>
   * 
   * @param perspectiveTree reference to the tree
   */
  public PerspectiveTreeSelectionListener(final PerspectiveTree perspectiveTree) {
    super();
    this.perspectiveTree = perspectiveTree;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void valueChanged(final TreeSelectionEvent evt) {
    if (evt.getNewLeadSelectionPath() != null) {
      //perspectiveTree.getSelectionModel().removeTreeSelectionListener(this);

      //perspectiveTree.setSelectionPath(evt.getOldLeadSelectionPath());
      //final int dialogResult = UtilWorkspace.saveWorkspaceIfChanged();
      // if (dialogResult != GMessageBox.CANCEL) {
      perspectiveTree.setSelectionPath(evt.getNewLeadSelectionPath());

      // Get the current Perspective selected node
      final ShowWorkspaceFacade facade = SpringBeanHelper.getImplementationByInterface(ShowWorkspaceFacade.class);
      GUISession.getInstance().getServiceDelegate().execute(facade, GUISessionProxy.getInfoWrkspOrgaFrame());
      //  }

      //perspectiveTree.getSelectionModel().addTreeSelectionListener(this);
    }

  }
}

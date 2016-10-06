package com.sgu.infowksporga.jfx.zfacade.remote.workspace;

import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;

import javax.swing.JComponent;
import javax.swing.ProgressMonitor;
import javax.swing.SwingUtilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.docking.GObjectInputStream;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.serialization.xml.GXStream;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.perspective.FindWorkspaceIn;
import com.sgu.infowksporga.business.pivot.perspective.FindWorkspaceOut;
import com.sgu.infowksporga.jfx.InfoWrkspOrgaFrame;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.tree.node.PerspectiveTreeNode;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.workspace.AbstractWorkspace;
import com.sgu.infowksporga.jfx.workspace.WorkspaceDefault;
import com.sgu.infowksporga.jfx.workspace.WorkspaceSerializer;
import com.sgu.infowksporga.jfx.zfacade.AbstractBusinessFacade;
import com.sgu.infowksporga.rest.RestServiceMapping;

/**
 * Description : ShowWorkspaceFacade class<br>
 */
@Service
public class ShowWorkspaceFacade extends AbstractBusinessFacade<FindWorkspaceOut, InfoWrkspOrgaFrame> {

  /**
   * The logger.
   */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(ShowWorkspaceFacade.class);

  private static final WorkspaceSerializer workspaceSerializer = new WorkspaceSerializer();

  /**
   * Constructor<br>
   */
  public ShowWorkspaceFacade() {
    super();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public JComponent getGlasspanesFor(final InfoWrkspOrgaFrame container) {
    return null;
  }

  @Override
  public void doBeforeServiceCall(final InfoWrkspOrgaFrame container, final ProgressMonitor monitor) {
    super.doBeforeServiceCall(container, monitor);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FindWorkspaceOut execute(final InfoWrkspOrgaFrame container) throws TechnicalException, BusinessException {
    FindWorkspaceOut out = null;

    final FindWorkspaceIn in = new FindWorkspaceIn();
    final PerspectivePanel pnlPerspective = (PerspectivePanel) container.getPnlPerspective();
    if (pnlPerspective.getTree().getSelectionCount() == 0) {
      return null;
    }

    final PerspectiveTreeNode nodeVo = (PerspectiveTreeNode) pnlPerspective.getTree().getSelectedNodes().get(0);

    final String workspaceId = nodeVo.getWorkspace().getId();
    in.setWorkspaceId(workspaceId);
    in.setUserLogin(GUISession.getInstance().getCurrentUser());

    // Call the service
    out = (FindWorkspaceOut) UtilInfoWrkspOrga.callRestBusinessService(RestServiceMapping.URL_SERVICE_FIND_WORKSPACE, in,
                                                                      FindWorkspaceOut.class);

    return out;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refreshScreen(final FindWorkspaceOut out, final InfoWrkspOrgaFrame container, final StringBuilder reportMessages,
  final ProgressMonitor monitor) {

    if (out == null) {
      final WorkspaceDefault defaultWorkspace = new WorkspaceDefault();
      container.showSelectedWorkspace(defaultWorkspace);
      return;
    }

    if (out.getWorkspaceViews() != null) {
      AbstractWorkspace workspaceUI = null;
      workspaceUI = buildWorkspaceUI(out);

      container.showSelectedWorkspace(workspaceUI);
      workspaceUI.defineListener();
      return;
    }

  }

  /**
   * Builds the workspace ui.
   *
   * @param out the out
   * @return the abstract workspace
   */
  private AbstractWorkspace buildWorkspaceUI(final FindWorkspaceOut out) {
    final Workspace workspace = out.getWorkspaceViews().getWorkspace();

    final WorkspaceDefault workspaceUI = new WorkspaceDefault(out.getWorkspaceViews());
    GUISessionProxy.getInfoWrkspOrgaFrame().setWrkspSelected(workspaceUI);

    // Load the layout from a byte array
    if (workspace != null && workspace.getLayout() != null && workspace.getLayout().length != 0) {

      try {

        ObjectInputStream in;

        try {

          final GXStream xstream = new GXStream();
          in = xstream.createObjectInputStream(new ByteArrayInputStream(workspace.getLayout()));
        } catch (final Exception e) {
          in = new GObjectInputStream(new ByteArrayInputStream(workspace.getLayout()));
        }

        // Use the Default Workspace to update it with loaded layout
        // First remove default view
        workspaceUI.getViewDefault().close();
        workspaceUI.getRootWindow().removeView(workspaceUI.getViewDefault());
        workspaceUI.getRootWindow().read(in, true);

        in.close();
      } catch (final Exception e) {
        SwingUtilities.invokeLater(new Runnable() {
          @Override
          public void run() {
            // Try to display workspace despite the Exception
            GUISessionProxy.getInfoWrkspOrgaFrame().showSelectedWorkspace(workspaceUI);
          }
        });

        throw new TechnicalException(e);
      }
    }

    return workspaceUI;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void finalizeService(final FindWorkspaceOut out, final InfoWrkspOrgaFrame container, final ProgressMonitor monitor) {

  }

}

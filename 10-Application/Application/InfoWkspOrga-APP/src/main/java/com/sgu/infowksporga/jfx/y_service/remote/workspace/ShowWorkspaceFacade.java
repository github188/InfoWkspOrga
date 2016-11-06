package com.sgu.infowksporga.jfx.y_service.remote.workspace;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.dockfx.DockPos;
import org.dockfx.pane.ContentSplitPane;
import org.dockfx.pane.ContentTabPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockNode;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewScreen;
import com.sgu.core.framework.gui.jfx.control.pane.dock.serialization.XDock;
import com.sgu.core.framework.gui.jfx.control.pane.dock.serialization.XSplitPane;
import com.sgu.core.framework.gui.jfx.service.AbstractBusinessFacade;
import com.sgu.core.framework.gui.jfx.util.GUISession;
import com.sgu.core.framework.gui.jfx.util.UtilApplication;
import com.sgu.core.framework.util.UtilCollection;
import com.sgu.core.framework.util.UtilString;
import com.sgu.core.framework.util.UtilXml;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.pivot.perspective.FindWorkspaceIn;
import com.sgu.infowksporga.business.pivot.perspective.FindWorkspaceOut;
import com.sgu.infowksporga.jfx.app.mvc.ApplicationScreen;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTreeItem;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilView;
import com.sgu.infowksporga.jfx.util.UtilWorkspace;
import com.sgu.infowksporga.jfx.view.ui.AAppViewFxml;
import com.sgu.infowksporga.jfx.view.ui.AAppViewModel;
import com.sgu.infowksporga.rest.RestServiceMapping;

import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : ShowWorkspaceFacade class<br>
 */
@Service
@Slf4j
public class ShowWorkspaceFacade extends AbstractBusinessFacade<FindWorkspaceOut, ApplicationScreen> {

  /**
   * The logger.
   */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(ShowWorkspaceFacade.class);

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
  public FindWorkspaceOut execute(final ApplicationScreen screen) throws TechnicalException, BusinessException {
    FindWorkspaceOut out = null;

    final FindWorkspaceIn in = new FindWorkspaceIn();
    final PerspectiveTreeItem treeItem = (PerspectiveTreeItem) screen.getPerspectiveScreen().getView().getTreeWorkspaces().getSelectionModel().getSelectedItem();
    if (treeItem == null) {
      return null;
    }

    final String workspaceId = treeItem.getWorkspace().getId();
    in.setWorkspaceId(workspaceId);
    in.setUserInfo(GUISession.getInstance().getCurrentUser());

    // Call the service
    out = (FindWorkspaceOut) UtilApplication.callRestBusinessService(RestServiceMapping.URL_SERVICE_FIND_WORKSPACE, in, FindWorkspaceOut.class);

    return out;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refreshScreen(final FindWorkspaceOut out, final ApplicationScreen appScreen, final StringBuilder reportMessages, final ProgressBar progressBar) {

    final List<GDockNode> dockNodes = new ArrayList<GDockNode>(5);

    try {
      if (UtilCollection.isEmpty(out.getWorkspace().getViews())) {
        UtilWorkspace.showDefaultDockPane();
        return;
      }

      UtilWorkspace.showEmptyDockPane();

      final List<View> views = out.getWorkspace().getViews();

      GDockNode lastAddedDockNode = null;
      for (final View view : views) {
        if (view.isNextSibling()) {
          lastAddedDockNode = null;
        }

        final AAppViewModel model = (AAppViewModel) Class.forName(view.getModelBean()).newInstance();
        model.setViewEntity(view);

        final ADockableViewScreen screen = (ADockableViewScreen) Class.forName(view.getScreenBean()).newInstance();
        screen.setModel(model);
        screen.initMVC();

        final GDockNode dockNode = new GDockNode(screen);
        lastAddedDockNode = UtilView.addDockNodeView(dockNode, DockPos.valueOf(view.getDockPos().getValue()), lastAddedDockNode);
        dockNodes.add(lastAddedDockNode);

        Platform.runLater(() -> {
          dockNode.getScreen().initMVC();
        });

      }

      // Update node preferred size for all views
      for (final GDockNode dockNode : dockNodes) {
        final ADockableViewScreen dockableScreen = dockNode.getScreen();
        final AAppViewFxml view = (AAppViewFxml) dockableScreen.view();
        dockableScreen.setDockNode(dockNode);

        final View viewEntity = ((AAppViewModel) view.model()).getViewEntity();

        dockNode.setPrefSize(viewEntity.getWidth(), viewEntity.getHeight());
        log.debug("{} ==> W:{} / H:{}", dockNode.getTitle(), viewEntity.getWidth(), viewEntity.getHeight());
      }

      // Retrieve each node size
      applyDividerPositionStoredInXml(out);

    } catch (final Exception e) {
      throw new TechnicalException(e);
    }

  }

  /**
   * Apply divider position stored in xml.
   *
   * @param out the out
   */
  private void applyDividerPositionStoredInXml(final FindWorkspaceOut out) {
    final List<ContentSplitPane> flatSplitPanes = new ArrayList<>(5);
    final List<XSplitPane> flatXSplitPanes = new ArrayList<>(5);

    final GDockPane dockPane = GUISessionProxy.getApplication().getApplicationScreen().getView().getDockPane();
    // convert String into InputStream
    final InputStream is = new ByteArrayInputStream(out.getWorkspace().getLayout());
    final XDock xDock = (XDock) UtilXml.jaxbXmlToObject(is, XDock.class);
    log.debug(new String(out.getWorkspace().getLayout()));

    retrieveFlatContentSplitPaneRecursively(flatSplitPanes, dockPane.getChildren());
    retrieveFlatXSplitPaneRecursively(flatXSplitPanes, xDock.getXSplitPane());

    for (int i = 0; i < flatSplitPanes.size(); i++) {
      final ContentSplitPane flatSplitPane = flatSplitPanes.get(i);
      String xDividersPos = flatXSplitPanes.get(i).getDividerPositions();
      xDividersPos = UtilString.replaceString(xDividersPos, "[", "");
      xDividersPos = UtilString.replaceString(xDividersPos, "]", "");

      if (UtilString.isNotBlank(xDividersPos.trim())) {
        double[] dividerPos = null;

        if (xDividersPos.contains(",")) {
          final String[] dividerStoredPos = xDividersPos.split(",");
          dividerPos = new double[dividerStoredPos.length];
          for (int j = 0; j < dividerPos.length; j++) {
            dividerPos[j] = Double.valueOf(dividerStoredPos[j]);
          }

        }
        else { // only one Divider
          dividerPos = new double[] { Double.valueOf(xDividersPos) };
        }

        log.debug("Divider Position ==> {}", Arrays.toString(dividerPos));
        flatSplitPane.setDividerPositions(dividerPos);

      }
    }
  }

  /**
   * Retrieve flat content split pane recursively.
   *
   * @param flatSplitPanes the flat split panes
   * @param children the children
   */
  private void retrieveFlatContentSplitPaneRecursively(final List<ContentSplitPane> flatSplitPanes, final List<Node> children) {

    for (final Node node : children) {
      if (node instanceof ContentSplitPane) {
        final ContentSplitPane contentSplitPane = (ContentSplitPane) node;
        log.debug("ContentSplitPane ==> {}", contentSplitPane);
        flatSplitPanes.add(contentSplitPane);
        retrieveFlatContentSplitPaneRecursively(flatSplitPanes, contentSplitPane.getChildrenList());
      }
      else if (node instanceof ContentTabPane) {
        final ContentTabPane contentTabPane = (ContentTabPane) node;
        retrieveFlatContentSplitPaneRecursively(flatSplitPanes, contentTabPane.getChildrenList());
      }
    }
  }

  /**
   * Retrieve flat x split pane recursively.
   *
   * @param flatXSplitPanes the flat x split panes
   * @param children the children
   */
  private void retrieveFlatXSplitPaneRecursively(final List<XSplitPane> flatXSplitPanes, final List<XSplitPane> children) {

    for (final XSplitPane xSplitPane : children) {
      flatXSplitPanes.add(xSplitPane);
      log.debug("ContentSplitPane ==> {}", xSplitPane);
      retrieveFlatXSplitPaneRecursively(flatXSplitPanes, xSplitPane.getXSplitPane());
    }
  }

}

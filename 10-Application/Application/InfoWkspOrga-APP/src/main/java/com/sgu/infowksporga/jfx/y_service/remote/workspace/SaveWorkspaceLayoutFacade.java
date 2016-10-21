package com.sgu.infowksporga.jfx.y_service.remote.workspace;

import java.util.ArrayList;
import java.util.List;

import org.dockfx.pane.ContentSplitPane;
import org.dockfx.pane.ContentTabPane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockNode;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.service.AbstractBusinessFacade;
import com.sgu.core.framework.gui.jfx.util.UtilApplication;
import com.sgu.core.framework.gui.jfx.util.UtilDockFX;
import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.infowksporga.business.dto.WorkspaceDto;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.entity.enumeration.DockPosEnum;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceIn;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceOut;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.main.mvc.ApplicationScreen;
import com.sgu.infowksporga.jfx.perspective.tree.vo.WorkspaceItemVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.view.ui.AApplicationViewModel;
import com.sgu.infowksporga.rest.RestServiceMapping;

import javafx.scene.Node;
import javafx.scene.control.ProgressBar;
import lombok.extern.slf4j.Slf4j;

/**
 * Description : saveWorkspaceFacade class<br>
 */
@Service
@Slf4j
public class SaveWorkspaceLayoutFacade extends AbstractBusinessFacade<SaveWorkspaceOut, ApplicationScreen> {

  /**
   * The logger.
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(SaveWorkspaceLayoutFacade.class);

  /**
   * Constructor<br>
   */
  public SaveWorkspaceLayoutFacade() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SaveWorkspaceOut execute(final ApplicationScreen screen) throws TechnicalException, BusinessException {
    // Get current workspace
    final WorkspaceItemVo workspaceItemVo = GUISessionProxy.getCurrentWorkspace();

    // -------- First serialize the workspace in XML
    final GDockPane dockPane = GUISessionProxy.getApplication().getApplicationScreen().getView().getDockPane();
    final String xmlDock = UtilDockFX.serializeDockFxStructureToXmlString(dockPane);
    log.debug(xmlDock);

    // -------- Secondly  rebuild Workspace informations

    // Workspace DTO to build for saving
    final WorkspaceDto workspaceDtoToSave = new WorkspaceDto();
    workspaceDtoToSave.setWorkspace(workspaceItemVo.getWorkspaceDto().getWorkspace());

    // Update workspace layout
    final Workspace workspace = workspaceDtoToSave.getWorkspace();
    workspace.setLayout(xmlDock.getBytes());
    workspace.setWidth(dockPane.getWidth());
    workspace.setHeight(dockPane.getHeight());

    // Build Views from layout
    workspace.setViews(getViewsFromDockPane(dockPane));

    // Prepare the pivot in to be persisted
    final SaveWorkspaceIn in = new SaveWorkspaceIn(workspaceDtoToSave);
    in.setUserInfo(GUISessionProxy.getGuiSession().getCurrentUser());

    // Call the service
    final SaveWorkspaceOut out = (SaveWorkspaceOut) UtilApplication.callRestBusinessService(RestServiceMapping.URL_SERVICE_SAVE_WORKSPACE, in, SaveWorkspaceOut.class);

    return out;
  }

  /**
   * Serialize dock fx structure.
   *
   * @param dockPane the dock pane
   * @return the x dock
   */
  public static List<View> getViewsFromDockPane(final GDockPane dockPane) {

    final List<Node> root = dockPane.getChildren();
    final List<View> views = new ArrayList<View>();
    final SaveWorkspaceLayoutFacade.ViewInfoWrapper wrapper = new SaveWorkspaceLayoutFacade.ViewInfoWrapper(0, false);
    getViewsFromDockPaneRecursively(root, dockPane, views, 0, wrapper);

    return views;
  }

  /**
   * Gets the views from dock pane recursively.
   *
   * @param children the children
   * @param parent the parent
   * @param views the views
   * @param level the level
   */
  protected static void getViewsFromDockPaneRecursively(final List<Node> children, final Node parent, final List<View> views, final int level,
  final SaveWorkspaceLayoutFacade.ViewInfoWrapper viewInfo) {

    int childIndex = 0;
    for (final Node childNode : children) {

      if (childNode instanceof ContentSplitPane) {
        final ContentSplitPane contentSplitPane = (ContentSplitPane) childNode;
        getViewsFromDockPaneRecursively(contentSplitPane.getChildrenList(), contentSplitPane, views, level + 1, viewInfo);
        viewInfo.isNextSibiling = true;
      }
      else if (childNode instanceof ContentTabPane) {
        final ContentTabPane contentTabPane = (ContentTabPane) childNode;
        getViewsFromDockPaneRecursively(contentTabPane.getChildrenList(), contentTabPane, views, level + 1, viewInfo);
      }
      else if (childNode instanceof GDockNode) {
        final GDockNode dockNode = (GDockNode) childNode;
        final AApplicationViewModel model = (AApplicationViewModel) dockNode.getModel();

        final View view = model.getEntityView();
        view.setOrder(viewInfo.viewOrder);
        view.setDockNodeBean(dockNode.getClass().getName());
        view.setModelBean(dockNode.getModel().getClass().getName());
        view.setWidth(dockNode.getWidth());
        view.setHeight(dockNode.getHeight());
        final String dockPos = UtilDockFX.getDockPosition(dockNode, parent, childIndex);
        view.setDockPos(DockPosEnum.valueOf(dockPos));
        viewInfo.viewOrder += 1;
        view.setNextSibling(viewInfo.isNextSibiling);
        viewInfo.isNextSibiling = false;

        //--------------------------------
        view.setOwner("Moi");

        views.add(view);

        childIndex++;
      }
      else {
        throw new TechnicalException();
      }
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "workspace.message.saving.completed.body", value = "La sauvegarde du workspace <b>{0}</b> est terminée."), // Force /n
                @I18nProperty(key = "workspace.message.saving.completed.header", value = "Statut du workspace :"), // Force /n
  })
  public void refreshScreen(final SaveWorkspaceOut out, final ApplicationScreen screen, final StringBuilder reportMessages, final ProgressBar progressBar) {
    if (out != null) {
      // Display message Workspace saved
      final String message = I18nHelperApp.getMessage("workspace.message.saving.completed.body", out.getWorkspaceDto().getWorkspace().getName());
      UtilGUIMessage.showInformationMessage("workspace.message.saving.completed.header", message);

      GUISessionProxy.setCurrentWorkspaceHasChanged(false);
    }
  }

  /**
   * Used to be able to get modified value other recursivity method call
   * and to manage sibiling change
   */
  private static class ViewInfoWrapper {
    public int viewOrder = 0;
    public boolean isNextSibiling = false;

    public ViewInfoWrapper(final int viewOrder, final boolean isNextSibiling) {
      super();
      this.viewOrder = viewOrder;
      this.isNextSibiling = isNextSibiling;
    }

  }

}

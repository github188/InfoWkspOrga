package com.sgu.infowksporga.business.service.rest.serialized;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.interfaces.GenerateInterface;
import com.sgu.apt.annotation.rest.Rest;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.dao.repository.PerspectiveWorkspacesRepository;
import com.sgu.infowksporga.business.dao.repository.ViewAttributeRepository;
import com.sgu.infowksporga.business.dao.repository.ViewRepository;
import com.sgu.infowksporga.business.dao.repository.WorkspaceRepository;
import com.sgu.infowksporga.business.service.rest.serialized.api.AbstractSerializedService;
import com.sgu.infowksporga.business.service.rest.serialized.api.ISaveWorkspaceService;
import com.sgu.infowksporga.business.dto.PerspectiveWorkspaceOrderDto;
import com.sgu.infowksporga.business.entity.PerspectiveWorkspaces;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceIn;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceOut;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class Save Workspace And Views Configuration.
 */
@Service
@Transactional
@Slf4j
public class SaveWorkspaceService extends AbstractSerializedService implements ISaveWorkspaceService {

  @Autowired
  private WorkspaceRepository repositoryWorkspace;

  @Autowired
  private PerspectiveWorkspacesRepository repositoryPerspectiveWorkspaces;

  @Autowired
  private ViewRepository repositoryView;

  @Autowired
  private ViewAttributeRepository repositoryViewAttribute;

  //---------------------------------------------------------------------------------------------------------------------------------------
  @GenerateInterface(baseSource = AnnotationConfig.INTERFACE_SERVICE_TARGET_SOURCE_FOLDER,
  interfacePackage = "com.sgu.infowksporga.business.service.rest.serialized.api", interfaceName = "ISaveWorkspaceService",
  pivotIn = "com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceIn",
  pivotOut = "com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceOut")

  @Rest(requestControllerUri = "/workspace", requestServiceUri = "/save", controllerPackage = "com.sgu.infowksporga.web.rest.controller",
  controllerName = "SaveWorkspaceController", method = "RequestMethod.POST",
  produces = "{ GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE }",
  controllerBaseSource = AnnotationConfig.REST_CONTROLLER_TARGET_SOURCE_FOLDER,
  restServiceMappingTargetClass = AnnotationConfig.REST_REQUEST_MAPPING_TARGET)

  //---------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public SaveWorkspaceOut executeService(final SaveWorkspaceIn in) {
    // Init du workspace de sortie
    final SaveWorkspaceOut out = new SaveWorkspaceOut(in.getWorkspaceDto());

    final Date saveDate = new Date(); // Définie au début du servive pour avoir le même horodatage pour tous les enreg
    /*---------------------------*/
    /* Workspace management */
    /*---------------------------*/
    Workspace workspace = in.getWorkspaceDto().getWorkspace();

    if (UtilString.isBlank(workspace.getId())) {
      workspace.setId(null);
      log.debug("Workspace Creation");
      //it's a workspace creation
      workspace.setCreationInfo(in.getUserLogin(), saveDate);
      workspace = repositoryWorkspace.save(workspace);

      PerspectiveWorkspaces pw = new PerspectiveWorkspaces();
      pw.setPerspectiveId(in.getPerspectiveWorkspaceOrderDto().getPerspectiveId());
      pw.setWorkspaceId(workspace.getId());
      int newWorkspaceOrder = in.getPerspectiveWorkspaceOrderDto().getNewWorkspaceIdOrder().get("");
      pw.setWorkspaceOrder(newWorkspaceOrder);
      pw.setCreationInfo(in.getUserLogin(), saveDate);
      repositoryPerspectiveWorkspaces.save(pw);

      // update workspaceId in NewWorkspaceIdOrder Map (used by reindex order)
      in.getPerspectiveWorkspaceOrderDto().getNewWorkspaceIdOrder().remove("");
      in.getPerspectiveWorkspaceOrderDto().getNewWorkspaceIdOrder().put(workspace.getId(), newWorkspaceOrder);
    }
    else {// it's an update
      log.debug("Workspace Update");
      workspace.setUpdateInfo(in.getUserLogin(), saveDate);
      repositoryWorkspace.save(workspace);

      //-------------------------------------------------------------------------------------------
      // Delete all views (with attributes) not in the workspace views list (them deleted by user)
      removeAllViewsNotReferencedByWorkspace(workspace);
      //--------------------------------------------------------------------------------------------      
    }

    /*---------------------------------------------------*/
    /* Re-Index Perspective Workspace order if necessary */
    /*---------------------------------------------------*/
    reIndexPerspectiveWorkspacesOrder(in, saveDate, workspace);

    /*---------------------------------------------*/
    /* Views management */
    /*---------------------------------------------*/
    manageViewsAndAttributes(in, saveDate, workspace);

    return out;
  }

  /**
   * Manage views and attributes.
   *
   * @param in the in
   * @param saveDate the save date
   * @param workspace the workspace
   */
  private void manageViewsAndAttributes(final SaveWorkspaceIn in, final Date saveDate, Workspace workspace) {
    List<View> views = workspace.getViews();
    for (View view : views) {

      if (view.getId() == null) {
        log.debug("View Creation");
        //it's a view creation
        view.setWorkspaceId(workspace.getId());
        view.setCreationInfo(in.getUserLogin(), saveDate);
        view = repositoryView.save(view);
      }
      else {// it's an update
        log.debug("View Update");
        view.setUpdateInfo(in.getUserLogin(), saveDate);
        repositoryView.save(view);
      }

      /*---------------------------------------------*/
      /* View ATTRIBUTES management */
      /*---------------------------------------------*/
      Set<ViewAttribute> viewAttributes = view.getAttributes();
      for (ViewAttribute viewAttribute : viewAttributes) {
        if (viewAttribute.getId() == null) {
          log.debug("viewAttribute Creation");
          //it's a view attribute creation
          viewAttribute.setViewId(view.getId());
          viewAttribute.setCreationInfo(in.getUserLogin(), saveDate);
          viewAttribute = repositoryViewAttribute.save(viewAttribute);
        }
        else {// it's an update
          log.debug("View Update");
          viewAttribute.setUpdateInfo(in.getUserLogin(), saveDate);
          repositoryViewAttribute.save(viewAttribute);
        }
      }

    }
  }

  /**
   * Re index perspective workspaces order.
   *
   * @param in the in
   * @param saveDate the save date
   * @param workspace the workspace
   */
  private void reIndexPerspectiveWorkspacesOrder(final SaveWorkspaceIn in, final Date saveDate, Workspace workspace) {
    if (in.getPerspectiveWorkspaceOrderDto() != null) {
      Map<String, PerspectiveWorkspaces> currentPerspectiveWorkspacesLst = findAllCurrentPerspectiveWorkspacesLink(in);

      PerspectiveWorkspaceOrderDto orderDto = in.getPerspectiveWorkspaceOrderDto();
      Set<String> keys = orderDto.getNewWorkspaceIdOrder().keySet();

      for (String workspaceId : keys) {
        Integer oldOrder = orderDto.getOldWorkspaceIdOrder().get(workspaceId);
        Integer newOrder = orderDto.getNewWorkspaceIdOrder().get(workspaceId);
        log.debug("odlOrder'{}' --> newOrder '{}'", oldOrder, newOrder);

        if (newOrder != oldOrder) {
          PerspectiveWorkspaces pw = currentPerspectiveWorkspacesLst.get(workspaceId);
          if (pw != null) {
            pw.setUpdateInfo(in.getUserLogin(), saveDate);
          }
          else {
            pw = new PerspectiveWorkspaces();
            pw.setPerspectiveId(in.getPerspectiveWorkspaceOrderDto().getPerspectiveId());
            pw.setWorkspaceId(workspace.getId());
            pw.setCreationInfo(in.getUserLogin(), saveDate);
          }

          pw.setWorkspaceOrder(newOrder);
          repositoryPerspectiveWorkspaces.save(pw);
        }
      }
    }
  }

  /**
   * Find all current perspective workspaces link.
   *
   * @param in the in
   * @return the map< string, perspective workspaces>
   */
  private Map<String, PerspectiveWorkspaces> findAllCurrentPerspectiveWorkspacesLink(final SaveWorkspaceIn in) {
    Map<String, PerspectiveWorkspaces> currentPerspectiveWorkspacesLst = new HashMap<String, PerspectiveWorkspaces>(10);

    List<PerspectiveWorkspaces> pwLst = repositoryPerspectiveWorkspaces.findAllBy("perspectiveId",
                                                                                  in.getPerspectiveWorkspaceOrderDto().getPerspectiveId());
    for (PerspectiveWorkspaces perspectiveWorkspaces : pwLst) {
      currentPerspectiveWorkspacesLst.put(perspectiveWorkspaces.getWorkspaceId(), perspectiveWorkspaces);
    }

    return currentPerspectiveWorkspacesLst;
  }

  /**
   * Removes the all views not referenced by workspace.
   *
   * @param workspace the workspace
   */
  private void removeAllViewsNotReferencedByWorkspace(Workspace workspace) {
    List<Integer> viewsIdToKeep = new ArrayList<Integer>();

    for (View view : workspace.getViews()) {
      if (view.getId() != null) {
        viewsIdToKeep.add(view.getId());
      }
    }

    List<Integer> viewsIdToDelete = null;
    if (viewsIdToKeep.size() > 0) {
      viewsIdToDelete = repositoryView.findViewsNotInGivenListForWorkspace(viewsIdToKeep, workspace.getId());
    }
    else { // On peut supprimer toutes les vues
      viewsIdToDelete = repositoryView.findWorkspaceViews(workspace.getId());
    }

    if (viewsIdToDelete != null && viewsIdToDelete.size() > 0) {
      // First remove all attributes for all views to evict reference problem
      repositoryViewAttribute.removeViewsAttributes(viewsIdToDelete);
      // Now remove all not needed Views 
      repositoryView.removeViews(viewsIdToDelete);
    }
  }

}

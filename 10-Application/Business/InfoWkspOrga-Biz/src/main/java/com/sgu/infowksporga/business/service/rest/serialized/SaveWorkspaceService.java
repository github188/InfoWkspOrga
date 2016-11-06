package com.sgu.infowksporga.business.service.rest.serialized;

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
import com.sgu.infowksporga.business.dao.api.IWorkspaceDao;
import com.sgu.infowksporga.business.dao.repository.PerspectiveWorkspacesRepository;
import com.sgu.infowksporga.business.dao.repository.WorkspaceRepository;
import com.sgu.infowksporga.business.entity.PerspectiveWorkspaces;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceIn;
import com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceOut;
import com.sgu.infowksporga.business.service.rest.serialized.api.AbstractSerializedService;
import com.sgu.infowksporga.business.service.rest.serialized.api.ISaveWorkspaceService;

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
  private IWorkspaceDao workspaceDao;

  @Autowired
  private PerspectiveWorkspacesRepository repositoryPerspectiveWorkspaces;

  //---------------------------------------------------------------------------------------------------------------------------------------
  @GenerateInterface(baseSource = AnnotationConfig.INTERFACE_SERVICE_TARGET_SOURCE_FOLDER, interfacePackage = "com.sgu.infowksporga.business.service.rest.serialized.api",
  interfaceName = "ISaveWorkspaceService", pivotIn = "com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceIn",
  pivotOut = "com.sgu.infowksporga.business.pivot.workspace.SaveWorkspaceOut")

  @Rest(requestControllerUri = "/workspace", requestServiceUri = "/save", controllerPackage = "com.sgu.infowksporga.web.rest.controller",
  controllerName = "SaveWorkspaceController", method = "RequestMethod.POST",
  produces = "{ GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE }",
  controllerBaseSource = AnnotationConfig.REST_CONTROLLER_TARGET_SOURCE_FOLDER, restServiceMappingTargetClass = AnnotationConfig.REST_REQUEST_MAPPING_TARGET)

  //---------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public SaveWorkspaceOut executeService(final SaveWorkspaceIn in) {
    // Init du workspace de sortie
    final SaveWorkspaceOut out = new SaveWorkspaceOut(in.getWorkspace());

    /*---------------------------*/
    /* Workspace management */
    /*---------------------------*/
    Workspace workspace = in.getWorkspace();

    if (Workspace.I_AM_A_NEW_WORKSPACE.equals(workspace.getId())) {
      workspace.setId(null);
      log.debug("Workspace Creation");
      //it's a workspace creation
      workspace.setCreationInfo(in.getUserLogin(), in.getTreatmentDate());
      workspace.setWidth(-1.0);  // Because they are not null in db, but updated at the same time than the Layout
      workspace.setHeight(-1.0); // Because they are null in db, but updated at the same time than the Layout
      workspace = repositoryWorkspace.save(workspace);

      final PerspectiveWorkspaces pw = new PerspectiveWorkspaces();
      pw.setPerspectiveId(in.getPerspectiveId());
      pw.setWorkspaceId(workspace.getId());
      final int newWorkspaceOrder = in.getNewWorkspacesOrder().get(Workspace.I_AM_A_NEW_WORKSPACE);
      pw.setWorkspaceOrder(newWorkspaceOrder);
      pw.setCreationInfo(in.getUserLogin(), in.getTreatmentDate());
      repositoryPerspectiveWorkspaces.save(pw);

      // update workspaceId in NewWorkspaceIdOrder Map (used by reindex order)
      in.getNewWorkspacesOrder().remove(Workspace.I_AM_A_NEW_WORKSPACE);
      in.getNewWorkspacesOrder().put(workspace.getId(), newWorkspaceOrder);
    }
    else {// it's an update
      log.debug("Workspace Update");
      workspaceDao.updateWorkspaceProperties(workspace, in.getUserLogin(), in.getTreatmentDate());
      workspace.setUpdateInfo(in.getUserLogin(), in.getTreatmentDate());
    }

    /*---------------------------------------------------*/
    /* Re-Index Perspective Workspace order if necessary */
    /*---------------------------------------------------*/
    reIndexPerspectiveWorkspacesOrder(in, in.getTreatmentDate(), workspace);

    return out;
  }

  /**
   * Re index perspective workspaces order.
   *
   * @param in the in
   * @param saveDate the save date
   * @param workspace the workspace
   */
  private void reIndexPerspectiveWorkspacesOrder(final SaveWorkspaceIn in, final Date saveDate, final Workspace workspace) {
    final Map<String, PerspectiveWorkspaces> currentOrders = findAllCurrentPerspectiveWorkspacesLink(in);
    final Set<String> keys = in.getNewWorkspacesOrder().keySet();

    for (final String workspaceId : keys) {
      PerspectiveWorkspaces pw = currentOrders.get(workspaceId);

      final Integer newOrder = in.getNewWorkspacesOrder().get(workspaceId);
      final Integer databaseOrder = pw == null ? null : pw.getWorkspaceOrder();
      log.debug("currentOrder'{}' --> newOrder '{}'", databaseOrder, newOrder);

      if (newOrder.equals(databaseOrder) == false) {

        if (databaseOrder == null) { // it's a new workspace to add
          pw.setUpdateInfo(in.getUserLogin(), saveDate);
          pw = new PerspectiveWorkspaces();
          pw.setPerspectiveId(in.getPerspectiveId());
          pw.setWorkspaceId(workspaceId);
          pw.setWorkspaceOrder(newOrder);
          pw.setCreationInfo(in.getUserLogin(), saveDate);
        }
        else {
          pw.setWorkspaceOrder(newOrder);
          pw.setUpdateInfo(in.getUserLogin(), saveDate);
        }

        repositoryPerspectiveWorkspaces.save(pw);
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
    final Map<String, PerspectiveWorkspaces> currentPerspectiveWorkspacesLst = new HashMap<String, PerspectiveWorkspaces>(10);

    final List<PerspectiveWorkspaces> pwLst = repositoryPerspectiveWorkspaces.findAllBy("perspectiveId", in.getPerspectiveId());
    for (final PerspectiveWorkspaces perspectiveWorkspaces : pwLst) {
      currentPerspectiveWorkspacesLst.put(perspectiveWorkspaces.getWorkspaceId(), perspectiveWorkspaces);
    }

    return currentPerspectiveWorkspacesLst;
  }

}

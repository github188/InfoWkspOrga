package com.sgu.infowksporga.business.service.rest.serialized;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.interfaces.GenerateInterface;
import com.sgu.apt.annotation.rest.Rest;
import com.sgu.infowksporga.business.dao.repository.PerspectiveWorkspacesRepository;
import com.sgu.infowksporga.business.entity.PerspectiveWorkspaces;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureIn;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureOut;
import com.sgu.infowksporga.business.service.rest.serialized.api.AbstractSerializedService;
import com.sgu.infowksporga.business.service.rest.serialized.api.IFindPerspectiveStructureService;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class FindPerspectiveWorkspacesService.
 * On combo Perspective Selection return the associated workspaces properties tree graph (without both layout and views)
 */
@Service
@Transactional
@Slf4j
public class FindPerspectiveStructureService extends AbstractSerializedService implements IFindPerspectiveStructureService {

  /** The repository perspective workspaces. */
  @Autowired
  private PerspectiveWorkspacesRepository repositoryPerspectiveWorkspaces;

  //---------------------------------------------------------------------------------------------------------------------------------------
  @GenerateInterface(baseSource = AnnotationConfig.INTERFACE_SERVICE_TARGET_SOURCE_FOLDER, interfacePackage = "com.sgu.infowksporga.business.service.rest.serialized.api",
  interfaceName = "IFindPerspectiveStructureService", pivotIn = "com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureIn",
  pivotOut = "com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureOut")

  @Rest(requestControllerUri = "/perspective", requestServiceUri = "/find/structure", controllerPackage = "com.sgu.infowksporga.web.rest.controller",
  controllerName = "FindPerspectiveStructureController", method = "RequestMethod.POST",
  produces = "{ GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE }",
  controllerBaseSource = AnnotationConfig.REST_CONTROLLER_TARGET_SOURCE_FOLDER, restServiceMappingTargetClass = AnnotationConfig.REST_REQUEST_MAPPING_TARGET)

  //---------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public FindPerspectiveStructureOut executeService(final FindPerspectiveStructureIn in) {
    final FindPerspectiveStructureOut out = new FindPerspectiveStructureOut();

    // Find all workspaces from Database with hierarchical imbrication (Parent -> Children)
    // Flat list
    final List<PerspectiveWorkspaces> selectResultAsObject = repositoryPerspectiveWorkspaces.findAllChildrenWorkspaces(in.getPerspective().getId());
    final List<Workspace> finalResult = out.getWorkspaces();
    final Map<String, Integer> currentWorkspaceIdOrder = out.getCurrentWorkspaceIdOrder();

    // Reorganize Select Result
    for (final Object object : selectResultAsObject) {
      final Object[] perspectiveWorkspaces = (Object[]) object;
      final PerspectiveWorkspaces perspectiveWorkspace = (PerspectiveWorkspaces) perspectiveWorkspaces[0];
      final Workspace workspace = (Workspace) perspectiveWorkspaces[1];
      currentWorkspaceIdOrder.put(workspace.getId(), perspectiveWorkspace.getWorkspaceOrder());

      // remove proxies for serialization
      finalResult.add(workspace.cloneWithoutProxy());
    }

    out.setWorkspaces(finalResult);
    return out;
  }

}
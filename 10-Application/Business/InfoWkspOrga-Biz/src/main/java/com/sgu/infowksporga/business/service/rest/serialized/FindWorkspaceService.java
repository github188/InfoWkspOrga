package com.sgu.infowksporga.business.service.rest.serialized;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.interfaces.GenerateInterface;
import com.sgu.apt.annotation.rest.Rest;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.infowksporga.business.dao.api.IWorkspaceDao;
import com.sgu.infowksporga.business.service.rest.serialized.api.AbstractSerializedService;
import com.sgu.infowksporga.business.service.rest.serialized.api.IFindWorkspaceService;
import com.sgu.infowksporga.business.dto.WorkspaceDto;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.mapper.EntityWorkspaceCloner;
import com.sgu.infowksporga.business.pivot.perspective.FindWorkspaceIn;
import com.sgu.infowksporga.business.pivot.perspective.FindWorkspaceOut;

import lombok.extern.slf4j.Slf4j;

/**
 * The Class Load Workspace And Views Configuration.
 */
@Service
@Transactional
@Slf4j
public class FindWorkspaceService extends AbstractSerializedService implements IFindWorkspaceService {

  @Autowired
  private IWorkspaceDao dao;

  //---------------------------------------------------------------------------------------------------------------------------------------
  @GenerateInterface(baseSource = AnnotationConfig.INTERFACE_SERVICE_TARGET_SOURCE_FOLDER,
  interfacePackage = "com.sgu.infowksporga.business.service.rest.serialized.api", interfaceName = "IFindWorkspaceService",
  pivotIn = "com.sgu.infowksporga.business.pivot.perspective.FindWorkspaceIn",
  pivotOut = "com.sgu.infowksporga.business.pivot.perspective.FindWorkspaceOut")

  @Rest(requestControllerUri = "/workspace", requestServiceUri = "/find/views",
  controllerPackage = "com.sgu.infowksporga.web.rest.controller", controllerName = "FindWorkspaceController", method = "RequestMethod.POST",
  produces = "{ GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE }",
  controllerBaseSource = AnnotationConfig.REST_CONTROLLER_TARGET_SOURCE_FOLDER,
  restServiceMappingTargetClass = AnnotationConfig.REST_REQUEST_MAPPING_TARGET)

  //---------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public FindWorkspaceOut executeService(final FindWorkspaceIn in) {
    final FindWorkspaceOut out = new FindWorkspaceOut();

    Workspace workspace = dao.findWorkspaceWithViewsAndAttr(in.getWorkspaceId());

    if (workspace == null) {
      out.setWorkspaceViews(null);
      return out;
    }

    // ---------------------------------------------------------
    // If this workspace is based on a master got it !!!
    // ---------------------------------------------------------
    Workspace workspaceMaster = null;
    if (workspace.getMaster() != null) {
      workspaceMaster = dao.findWorkspaceWithViewsAndAttr(workspace.getMaster().getId());
      if (workspaceMaster == null) {
        throw new TechnicalException("Workspace id '" + workspace.getId() + "' has a master not found with id '"
                                     + workspace.getMaster().getId() + "'");
      }
    }

    // Build return result without proxies
    final WorkspaceDto workspaceDto = new WorkspaceDto();
    workspaceDto.setWorkspace(EntityWorkspaceCloner.instance.cloneWithoutProxy(workspace));
    if (workspaceMaster != null) {
      workspaceDto.setWorkspaceMaster(EntityWorkspaceCloner.instance.cloneWithoutProxy(workspaceMaster));
    }
    out.setWorkspaceViews(workspaceDto);

    return out;
  }

}

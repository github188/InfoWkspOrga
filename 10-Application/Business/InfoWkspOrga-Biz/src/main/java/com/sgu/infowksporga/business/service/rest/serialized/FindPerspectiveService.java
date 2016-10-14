package com.sgu.infowksporga.business.service.rest.serialized;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.interfaces.GenerateInterface;
import com.sgu.apt.annotation.rest.Rest;
import com.sgu.core.framework.pivot.AbstractOut.ReturnCode;
import com.sgu.core.framework.pivot.Message;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.dao.repository.PerspectiveRepository;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveIn;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveOut;
import com.sgu.infowksporga.business.service.rest.serialized.api.AbstractSerializedService;
import com.sgu.infowksporga.business.service.rest.serialized.api.IFindPerspectiveService;

/**
 * The Class FindPerspectiveService.
 */
@Service
@Transactional
public class FindPerspectiveService extends AbstractSerializedService implements IFindPerspectiveService {

  /**
   * DAO
   */
  @Autowired
  private PerspectiveRepository repository;

  //---------------------------------------------------------------------------------------------------------------------------------------
  @GenerateInterface(baseSource = AnnotationConfig.INTERFACE_SERVICE_TARGET_SOURCE_FOLDER, interfacePackage = "com.sgu.infowksporga.business.service.rest.serialized.api",
  interfaceName = "IFindPerspectiveService", pivotIn = "com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveIn",
  pivotOut = "com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveOut")

  @Rest(requestControllerUri = "/perspective", requestServiceUri = "/find", controllerPackage = "com.sgu.infowksporga.web.rest.controller",
  controllerName = "FindPerspectiveController", method = "RequestMethod.POST",
  produces = "{ GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE }",
  controllerBaseSource = AnnotationConfig.REST_CONTROLLER_TARGET_SOURCE_FOLDER, restServiceMappingTargetClass = AnnotationConfig.REST_REQUEST_MAPPING_TARGET)

  //---------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public FindPerspectiveOut executeService(final FindPerspectiveIn in) {
    final FindPerspectiveOut out = new FindPerspectiveOut();

    if (in.isAll()) {
      final List<Perspective> all = repository.findAll();
      out.setPerspectiveLst(all);
    }
    else if (UtilString.isNoneBlank(in.getName())) {
      final Perspective perspective = repository.findByName(in.getName());
      out.addPerspective(perspective);
    }
    else {
      out.setReturnCode(ReturnCode.BUSINESS_ERROR);
      out.addError(new Message("Pivot In is not correctly filled"));
    }

    return out;
  }

}

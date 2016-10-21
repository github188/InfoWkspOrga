package com.sgu.infowksporga.business.service.rest.serialized.orchestration;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.interfaces.GenerateInterface;
import com.sgu.apt.annotation.rest.Rest;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.business.pivot.orchestration.FindDataToInitApplicationIn;
import com.sgu.infowksporga.business.pivot.orchestration.FindDataToInitApplicationOut;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveOut;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureOut;
import com.sgu.infowksporga.business.service.rest.serialized.FindPerspectiveService;
import com.sgu.infowksporga.business.service.rest.serialized.FindPerspectiveStructureService;
import com.sgu.infowksporga.business.service.rest.serialized.api.AbstractSerializedService;
import com.sgu.infowksporga.business.service.rest.serialized.api.orchestration.IFindDataToInitApplicationOService;

@Service
@Transactional
public class FindDataToInitApplicationOService extends AbstractSerializedService implements IFindDataToInitApplicationOService {

  /**
   * Service to get first information to populate ComboBox perspectives
   */
  @Autowired
  private FindPerspectiveService findPerspectiveService;

  /**
   * Service to get prefered/last user selected perspective
   */
  @Autowired
  private FindPerspectiveStructureService findPerspectiveStructureService;

  //---------------------------------------------------------------------------------------------------------------------------------------
  @Override
  @GenerateInterface(baseSource = AnnotationConfig.INTERFACE_SERVICE_TARGET_SOURCE_FOLDER,
  interfacePackage = "com.sgu.infowksporga.business.service.rest.serialized.api.orchestration", interfaceName = "IFindDataToInitApplicationOService",
  pivotIn = "com.sgu.infowksporga.business.pivot.orchestration.FindDataToInitApplicationIn",
  pivotOut = "com.sgu.infowksporga.business.pivot.orchestration.FindDataToInitApplicationOut")

  @Rest(requestControllerUri = "/application", requestServiceUri = "/initialization", controllerPackage = "com.sgu.infowksporga.web.rest.controller",
  controllerName = "FindDataToInitApplicationController", method = "RequestMethod.POST", interfaceServiceName = "orchestration.IFindDataToInitApplicationOService",
  produces = "{ GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE }",
  controllerBaseSource = AnnotationConfig.REST_CONTROLLER_TARGET_SOURCE_FOLDER, restServiceMappingTargetClass = AnnotationConfig.REST_REQUEST_MAPPING_TARGET)

  //---------------------------------------------------------------------------------------------------------------------------------------

  public FindDataToInitApplicationOut executeService(final FindDataToInitApplicationIn in) {
    final FindDataToInitApplicationOut out = new FindDataToInitApplicationOut();

    // First find all perspectives
    final FindPerspectiveOut findPerspectiveOut = findPerspectiveService.executeService(in.getFindPerspectiveIn());

    if (findPerspectiveOut.getPerspectiveLst() != null && findPerspectiveOut.getPerspectiveLst().size() == 1) {
      in.setUserPreferedPerspective(findPerspectiveOut.getPerspectiveLst().get(0).getId());
    }

    if (in.getUserPreferedPerspective() != null && in.getFindPerspectiveStructureIn().getPerspective() == null) {
      // Find the perspective structure if prefered perspective is selected or if their is only one perspective for user
      final Perspective perspective = new Perspective();
      perspective.setId(in.getUserPreferedPerspective());
      in.getFindPerspectiveStructureIn().setPerspective(perspective);
    }

    final FindPerspectiveStructureOut findPerspectiveStructureOut = findPerspectiveStructureService.executeService(in.getFindPerspectiveStructureIn());

    // Find all perspective to add in combo box
    out.setFindPerspectiveOut(findPerspectiveOut);
    out.setFindPerspectiveStructureOut(findPerspectiveStructureOut);

    return out;
  }

}

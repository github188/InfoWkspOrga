package com.sgu.infowksporga.business.service.rest.serialized;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.interfaces.GenerateInterface;
import com.sgu.apt.annotation.rest.Rest;
import com.sgu.core.framework.pivot.AbstractOut.ReturnCode;
import com.sgu.infowksporga.business.dao.repository.PreferenceRepository;
import com.sgu.infowksporga.business.service.rest.serialized.api.AbstractSerializedService;
import com.sgu.infowksporga.business.service.rest.serialized.api.IFindPreferencesService;
import com.sgu.core.framework.pivot.Message;
import com.sgu.infowksporga.business.entity.Preference;
import com.sgu.infowksporga.business.pivot.preference.FindPreferencesIn;
import com.sgu.infowksporga.business.pivot.preference.FindPreferencesOut;

/**
 * The Class FindPreferencesService.
 */
@Service
@Transactional
public class FindPreferencesService extends AbstractSerializedService implements IFindPreferencesService {

  /**
   * DAO to test
   */
  @Autowired
  private PreferenceRepository repository;

  //---------------------------------------------------------------------------------------------------------------------------------------
  @GenerateInterface(baseSource = AnnotationConfig.INTERFACE_SERVICE_TARGET_SOURCE_FOLDER,
  interfacePackage = "com.sgu.infowksporga.business.service.rest.serialized.api", interfaceName = "IFindPreferencesService",
  pivotIn = "com.sgu.infowksporga.business.pivot.preference.FindPreferencesIn",
  pivotOut = "com.sgu.infowksporga.business.pivot.preference.FindPreferencesOut")

  @Rest(requestControllerUri = "/preferences", requestServiceUri = "/find", controllerPackage = "com.sgu.infowksporga.web.rest.controller",
  controllerName = "FindPreferencesController", method = "RequestMethod.POST",
  produces = "{ GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE }",
  controllerBaseSource = AnnotationConfig.REST_CONTROLLER_TARGET_SOURCE_FOLDER,
  restServiceMappingTargetClass = AnnotationConfig.REST_REQUEST_MAPPING_TARGET)

  //---------------------------------------------------------------------------------------------------------------------------------------
  @Override
  public FindPreferencesOut executeService(final FindPreferencesIn in) {
    final FindPreferencesOut out = new FindPreferencesOut();

    if (in.isAll()) {
      final List<Preference> all = repository.findAll();
      out.setPreferenceLst(all);
    }
    else {
      out.setReturnCode(ReturnCode.BUSINESS_ERROR);
      out.addError(new Message("Pivot In is not correctly filled"));
    }

    return out;
  }

}

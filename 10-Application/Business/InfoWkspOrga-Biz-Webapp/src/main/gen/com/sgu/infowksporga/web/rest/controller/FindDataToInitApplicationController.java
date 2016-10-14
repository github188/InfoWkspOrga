package com.sgu.infowksporga.web.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sgu.core.framework.web.rest.controller.AbstractRestController;
import com.sgu.core.framework.web.rest.http.GMediaType;
import com.sgu.infowksporga.business.service.rest.serialized.orchestration.api.IFindDataToInitApplicationOService;
import com.sgu.infowksporga.rest.RestServiceMapping;

/**
 * Description : FindDataToInitApplicationController class<br>.
 */
@RestController
@RequestMapping(value = RestServiceMapping.APPLICATION_CONTROLLER_URI)
public class FindDataToInitApplicationController extends AbstractRestController {

  /** The Class Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(FindDataToInitApplicationController.class);

  /** The service. */
  @Autowired
  private IFindDataToInitApplicationOService service;

  /**
   * Constructor<br>.
   */
  public FindDataToInitApplicationController() {
    super();
  }

  @RequestMapping(value = RestServiceMapping.INITIALIZATION_SERVICE_URI, method = RequestMethod.POST,
  produces = { GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE })
  @ResponseBody
  public com.sgu.infowksporga.business.pivot.orchestration.FindDataToInitApplicationOut
  findDataToInitApplication(@RequestBody(required = true) final com.sgu.infowksporga.business.pivot.orchestration.FindDataToInitApplicationIn pivotIn) {
    final com.sgu.infowksporga.business.pivot.orchestration.FindDataToInitApplicationOut result = service.executeService(pivotIn);
    return result;
  }

}

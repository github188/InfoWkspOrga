package com.sgu.infowksporga.web.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sgu.core.framework.web.rest.controller.AbstractRestController;
import com.sgu.infowksporga.business.service.rest.serialized.api.IFindPreferencesService;
import com.sgu.infowksporga.rest.RestServiceMapping;
import com.sgu.core.framework.web.rest.http.GMediaType;

/**
 * Description : FindPreferencesController class<br>.
 */
@RestController
@RequestMapping(value = RestServiceMapping.PREFERENCES_CONTROLLER_URI)
public class FindPreferencesController extends AbstractRestController {

  /** The Class Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(FindPreferencesController.class);

  /** The service. */
  @Autowired
  private IFindPreferencesService service;

  /**
   * Constructor<br>.
   */
  public FindPreferencesController() {
    super();
  }

  @RequestMapping(value = RestServiceMapping.FIND_SERVICE_URI, method = RequestMethod.POST,
  produces = { GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE })
  @ResponseBody
  public com.sgu.infowksporga.business.pivot.preference.FindPreferencesOut  findPreferences (@RequestBody(required = true) final com.sgu.infowksporga.business.pivot.preference.FindPreferencesIn pivotIn) {
    final com.sgu.infowksporga.business.pivot.preference.FindPreferencesOut  result = service.executeService(pivotIn);
    return result;
  }

}

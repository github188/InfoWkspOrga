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
import com.sgu.infowksporga.business.service.rest.serialized.api.IFindPerspectiveStructureService;
import com.sgu.infowksporga.rest.RestServiceMapping;
import com.sgu.core.framework.web.rest.http.GMediaType;

/**
 * Description : FindPerspectiveStructureController class<br>.
 */
@RestController
@RequestMapping(value = RestServiceMapping.PERSPECTIVE_CONTROLLER_URI)
public class FindPerspectiveStructureController extends AbstractRestController {

  /** The Class Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(FindPerspectiveStructureController.class);

  /** The service. */
  @Autowired
  private IFindPerspectiveStructureService service;

  /**
   * Constructor<br>.
   */
  public FindPerspectiveStructureController() {
    super();
  }

  @RequestMapping(value = RestServiceMapping.FIND_STRUCTURE_SERVICE_URI, method = RequestMethod.POST,
  produces = { GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE })
  @ResponseBody
  public com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureOut  findPerspectiveStructure (@RequestBody(required = true) final com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureIn pivotIn) {
    final com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureOut  result = service.executeService(pivotIn);
    return result;
  }

}

package com.sgu.infowksporga.web.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sgu.core.framework.web.rest.controller.AbstractRestController;

/**
 * Description : TestRestAccessController class<br>.
 */
@RestController
@RequestMapping(value = "/test")
public class TestRestAccessController extends AbstractRestController {

  /** The Class Logger. */
  private static final Logger LOGGER = LoggerFactory.getLogger(TestRestAccessController.class);

  /**
   * Constructor<br>.
   */
  public TestRestAccessController() {
    super();
  }

  @RequestMapping(value = "/access", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public String testRestAccess() {
    return "Hello World !!";
  }

}

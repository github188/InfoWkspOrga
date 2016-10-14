package com.sgu.infowksporga.rest;

import com.sgu.core.framework.util.Properties;
import com.sgu.core.framework.util.UtilString;

/**
 * Description : RestServiceMapping class<br>
 * Store all service mapping constant used by at webapp controllers
 */
public class RestServiceMapping {

  private static final Properties properties;

  /** The base uri. */
  public static String BASE_BUSINESS_SERVICE_URI;

  static {
    properties = new Properties("/spring/server-localization.properties");
    BASE_BUSINESS_SERVICE_URI = properties.getProperty("biz.url.rest.service");
    final String[] result = UtilString.getListOfStringBetween(BASE_BUSINESS_SERVICE_URI, "{", "}");
    BASE_BUSINESS_SERVICE_URI = UtilString.replace(BASE_BUSINESS_SERVICE_URI, result[0], properties.getProperty(result[0]));
    BASE_BUSINESS_SERVICE_URI = UtilString.replace(BASE_BUSINESS_SERVICE_URI, result[1], properties.getProperty(result[1]));
    BASE_BUSINESS_SERVICE_URI = UtilString.replace(BASE_BUSINESS_SERVICE_URI, "${", "");
    BASE_BUSINESS_SERVICE_URI = UtilString.replace(BASE_BUSINESS_SERVICE_URI, "}", "");
  }


  /*---------------------------------------------------------------------------------------------------------------------------------------
   * Controller and Services URI List
   *---------------------------------------------------------------------------------------------------------------------------------------
   */
  public static final String PERSPECTIVE_CONTROLLER_URI = "/perspective";
  public static final String FIND_SERVICE_URI = "/find";

  public static final String FIND_STRUCTURE_SERVICE_URI = "/find/structure";

  public static final String APPLICATION_CONTROLLER_URI = "/application";
  public static final String INITIALIZATION_SERVICE_URI = "/initialization";

  public static final String PREFERENCES_CONTROLLER_URI = "/preferences";
  public static final String PREFERENCE_CONTROLLER_URI = "/preference";
  public static final String LOAD_XML_STRUCTURE_SERVICE_URI = "/load/xml/structure";

  public static final String WORKSPACE_CONTROLLER_URI = "/workspace";
  public static final String SAVE_SERVICE_URI = "/save";

  public static final String AUTHENTIFICATION_CONTROLLER_URI = "/authentification";
  public static final String USER_SERVICE_URI = "/user";

  public static final String FIND_VIEWS_SERVICE_URI = "/find/views";

    
  
  /*---------------------------------------------------------------------------------------------------------------------------------------
   * COMPLETE SERVICES URI
   *---------------------------------------------------------------------------------------------------------------------------------------
   */
  public static final String URL_SERVICE_FIND_PERSPECTIVE = BASE_BUSINESS_SERVICE_URI + PERSPECTIVE_CONTROLLER_URI + FIND_SERVICE_URI;
  public static final String URL_SERVICE_FIND_PERSPECTIVE_STRUCTURE = BASE_BUSINESS_SERVICE_URI + PERSPECTIVE_CONTROLLER_URI + FIND_STRUCTURE_SERVICE_URI;
  public static final String URL_SERVICE_FIND_DATA_TO_INIT_APPLICATION = BASE_BUSINESS_SERVICE_URI + APPLICATION_CONTROLLER_URI + INITIALIZATION_SERVICE_URI;
  public static final String URL_SERVICE_FIND_PREFERENCES = BASE_BUSINESS_SERVICE_URI + PREFERENCES_CONTROLLER_URI + FIND_SERVICE_URI;
  public static final String URL_SERVICE_LOAD_PREFERENCES_STRUCTURE = BASE_BUSINESS_SERVICE_URI + PREFERENCE_CONTROLLER_URI + LOAD_XML_STRUCTURE_SERVICE_URI;
  public static final String URL_SERVICE_SAVE_WORKSPACE = BASE_BUSINESS_SERVICE_URI + WORKSPACE_CONTROLLER_URI + SAVE_SERVICE_URI;
  public static final String URL_SERVICE_AUTHENTICATE_USER = BASE_BUSINESS_SERVICE_URI + AUTHENTIFICATION_CONTROLLER_URI + USER_SERVICE_URI;
  public static final String URL_SERVICE_FIND_WORKSPACE = BASE_BUSINESS_SERVICE_URI + WORKSPACE_CONTROLLER_URI + FIND_VIEWS_SERVICE_URI;
  public static final String URL_SERVICE_LOAD_PERSPECTIVES_STRUCTURE = BASE_BUSINESS_SERVICE_URI + PERSPECTIVE_CONTROLLER_URI + LOAD_XML_STRUCTURE_SERVICE_URI;
  

  /**
   * Constructor<br>
   */
  private RestServiceMapping() {
  }

}

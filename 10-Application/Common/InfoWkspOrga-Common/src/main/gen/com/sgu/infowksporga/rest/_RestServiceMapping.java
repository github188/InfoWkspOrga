package com.sgu.infowksporga.rest;

import com.sgu.apt.annotation.AnnotationConfig;

/**
 * Description : RestServiceMapping class<br>
 * Store all service mapping constant used by at webapp controllers
 */
public class _RestServiceMapping {

  /** The base uri. */
  public static final String BASE_BUSINESS_SERVICE_URI = AnnotationConfig.REST_BASE_BUSINESS_SERVICE_URI;

  /*---------------------------------------------------------------------------------------------------------------------------------------
   * Controller and Services URI List
   *---------------------------------------------------------------------------------------------------------------------------------------
   */
  public static final String AUTHENTIFICATION_CONTROLLER_URI = "/authentification";
  public static final String AUTHENTICATE_USER_SERVICE_URI = "/authenticate/user";

  public static final String INFRASTRUCTURE_CHECK_CONTROLLER_URI = "/infrastructure/check";
  public static final String DATABASE_AVAILABLE_SERVICE_URI = "/database/available";

  public static final String PLANNING_CONTROLLER_URI = "/planning";
  public static final String RESOLVE_SERVICE_URI = "/resolve";

  public static final String ANALYZE_CONTROLLER_URI = "/analyze";
  public static final String PROPERTIES_RULES_SERVICE_URI = "/properties/rules";

  public static final String WORKSPACES_CONTROLLER_URI = "/workspaces";
  public static final String LOAD_TREE_MODEL_SERVICE_URI = "/load/tree/model";

  /*---------------------------------------------------------------------------------------------------------------------------------------
   * COMPLETE SERVICES URI
   *---------------------------------------------------------------------------------------------------------------------------------------
   */
  public static final String URL_SERVICE_AUTHENTICATE_USER = BASE_BUSINESS_SERVICE_URI + AUTHENTIFICATION_CONTROLLER_URI
                                                             + AUTHENTICATE_USER_SERVICE_URI;
  public static final String URL_SERVICE_CHECK_IF_DATABASE_IS_AVAILABLE = BASE_BUSINESS_SERVICE_URI + INFRASTRUCTURE_CHECK_CONTROLLER_URI
                                                                          + DATABASE_AVAILABLE_SERVICE_URI;
  public static final String URL_SERVICE_PLANNING_RESOLVER = BASE_BUSINESS_SERVICE_URI + PLANNING_CONTROLLER_URI + RESOLVE_SERVICE_URI;
  public static final String URL_SERVICE_ANALYZE_PROPERTIES_RULES = BASE_BUSINESS_SERVICE_URI + ANALYZE_CONTROLLER_URI
                                                                    + PROPERTIES_RULES_SERVICE_URI;
  public static final String URL_SERVICE_LOAD_WORKSPACES_TREE_MODEL = BASE_BUSINESS_SERVICE_URI + WORKSPACES_CONTROLLER_URI
                                                                      + LOAD_TREE_MODEL_SERVICE_URI;

  /**
   * Constructor<br>
   */
  private _RestServiceMapping() {
  }

}

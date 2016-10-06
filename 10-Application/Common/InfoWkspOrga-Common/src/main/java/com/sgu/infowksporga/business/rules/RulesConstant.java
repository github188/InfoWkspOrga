package com.sgu.infowksporga.business.rules;

import com.sgu.core.framework.drools.rules.validation.properties.RulesPropertiesConstant;

/**
 * Description : RulesAuthentificationConstant class<br>
 */
public final class RulesConstant extends RulesPropertiesConstant {

  /**
   * The constant for AUTHENTIFICATION
   */
  public static final String KSESSION_AUTHENTIFICATION = "ksession-authentification";
  public static final String PROCESS_CHECK_AUTHENTIFICATION = "process-check-authentification";

  /**
   * The constant for PROJECT_CONFIGURATION
   */
  public static final String KSESSION_PROJECT_CONFIGURATION = "ksession-project-configuration";
  public static final String PROCESS_CHECK_PROJECT_CONFIGURATION = "process-check-project-configuration";

  /**
   * The constant for PROJECT
   */
  public static final String KSESSION_PROJECT = "ksession-project";
  public static final String PROCESS_CHECK_PROJECT = "process-check-project";

  /**
   * Constructor<br>
   */
  private RulesConstant() {
    // Utility class
  }
}

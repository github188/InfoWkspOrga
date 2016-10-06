package com.sgu.infowksporga.business.rules;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgu.infowksporga.business.rules.authentification.AuthentificationBusinessRulesTest;

/**
 * Description : AllTests class<br>
 */
@RunWith(Suite.class)
@SuiteClasses({ AuthentificationBusinessRulesTest.class, })
public class AllTests {
  /**
   * The logger
   */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(AllTests.class);
}

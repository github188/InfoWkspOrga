package com.sgu.infowksporga.business.rules.authentification;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sgu.core.framework.drools.rules.validation.RuleMessages;
import com.sgu.core.framework.drools.test.AbstractBusinessRulesTest;
import com.sgu.core.framework.drools.util.UtilDrools;
import com.sgu.core.framework.pivot.FieldNameFieldLabelAssociation;
import com.sgu.infowksporga.business.entity.User;
import com.sgu.infowksporga.business.pivot.authentication.AuthenticateUserIn;
import com.sgu.infowksporga.business.rules.RulesConstant;

/**
 * Description : AuthentificationBusinessRulesTest class<br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-test.xml")
public class AuthentificationBusinessRulesTest extends AbstractBusinessRulesTest {
  /**
   * The logger
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthentificationBusinessRulesTest.class);

  /**
   * field Name Field Label to display error message
   */
  private FieldNameFieldLabelAssociation fieldNameFieldLabel;

  /**
   * Ref to the initialised session
   */
  private KieSession kSession;

  /**
   * Description : initializeTests method <br>
   */
  @Before
  public void initializeTests() {
    fieldNameFieldLabel = new FieldNameFieldLabelAssociation();
    fieldNameFieldLabel.put("txtLogin", "login");
    fieldNameFieldLabel.put("txtPassword", "password");
    fieldNameFieldLabel.put("cbLanguage", "language");

    final KieServices ks = KieServices.Factory.get();
    final KieContainer kContainer = ks.getKieClasspathContainer();
    kSession = kContainer.newKieSession("ksession-authentification");
  }

  /**
   * Description : testAuthentificationFieldMandatory1 method <br>
   */
  @Test
  public void testAuthentificationFieldMandatory1() {
    LOGGER.debug("start testAuthentificationFieldMandatory1");
    final String login = null;
    final String password = null;
    final String language = null;

    final AuthenticateUserIn pivotIn = new AuthenticateUserIn(login, password, language);
    pivotIn.setFieldNameFieldLabel(fieldNameFieldLabel);

    final RuleMessages messages = new RuleMessages();

    executeTest(pivotIn, messages, new User());

    Assert.assertTrue(messages.getErrors().size() == 3);

  }

  /**
   * Description : executeTest method <br>
   *
   * @param pivotIn pivot In
   * @param messages messages object
   */
  private void executeTest(final AuthenticateUserIn pivotIn, final RuleMessages messages) {
    executeTest(pivotIn, messages, null);
  }

  /**
   * Description : executeTest method <br>
   *
   * @param pivotIn pivot In
   * @param messages messages object
   * @param user user object
   */
  private void executeTest(final AuthenticateUserIn pivotIn, final RuleMessages messages, final User user) {

    if (user != null) {
      UtilDrools.executeDroolsProcess(RulesConstant.KSESSION_AUTHENTIFICATION, RulesConstant.PROCESS_CHECK_AUTHENTIFICATION, messages,
                                      pivotIn, user);
    }
    else {
      UtilDrools.executeDroolsProcess(RulesConstant.KSESSION_AUTHENTIFICATION, RulesConstant.PROCESS_CHECK_AUTHENTIFICATION, messages,
                                      pivotIn);
    }

    UtilDrools.logAllMessages(messages);

    if (messages.hasError()) {
      LOGGER.error("Rules not compliant");
    }
  }

  /**
   * Description : testAuthentificationFieldMandatory2 method <br>
   */
  @Test
  public void testAuthentificationFieldMandatory2() {
    LOGGER.debug("start testAuthentificationFieldMandatory2");
    final String login = "sguisse";
    final String password = null;
    final String language = null;

    final AuthenticateUserIn pivotIn = new AuthenticateUserIn(login, password, language);
    pivotIn.setFieldNameFieldLabel(fieldNameFieldLabel);

    final RuleMessages messages = new RuleMessages();
    final User user = new User();
    user.setLogin("KO");
    user.setPassword("KO");

    executeTest(pivotIn, messages, user);

    Assert.assertTrue(messages.getErrors().size() == 2);

  }

  /**
   * Description : testAuthentificationFieldMandatory3 method <br>
   */
  @Test
  public void testAuthentificationFieldMandatory3() {
    LOGGER.debug("start testAuthentificationFieldMandatory3");
    final String login = "sguisse";
    final String password = "MyPassword";
    final String language = null;

    final AuthenticateUserIn pivotIn = new AuthenticateUserIn(login, password, language);
    pivotIn.setFieldNameFieldLabel(fieldNameFieldLabel);

    final RuleMessages messages = new RuleMessages();
    final User user = new User();
    user.setLogin("KO");
    user.setPassword("KO");

    executeTest(pivotIn, messages, user);

    Assert.assertTrue(messages.getErrors().size() == 1);

  }

  /**
   * Description : testAuthentificationFieldMandatory3 method <br>
   */
  @Test
  public void testAuthentificationUserLoginKo() {
    LOGGER.debug("start testAuthentificationFieldMandatory3");
    final String login = "sguisse";
    final String password = "MyPassword";
    final String language = "FR";

    final AuthenticateUserIn pivotIn = new AuthenticateUserIn(login, password, language);
    pivotIn.setFieldNameFieldLabel(fieldNameFieldLabel);

    final RuleMessages messages = new RuleMessages();
    final User user = new User();
    user.setLogin("KO");
    user.setPassword("KO");

    executeTest(pivotIn, messages, user);
    Assert.assertTrue(messages.getErrors().size() == 1);

  }

  /**
   * Description : testAuthentificationUserPasswordKo method <br>
   */
  @Test
  public void testAuthentificationUserPasswordKo() {
    LOGGER.debug("start testAuthentificationFieldMandatory3");
    final String login = "sguisse";
    final String password = "MyPassword";
    final String language = "FR";

    final AuthenticateUserIn pivotIn = new AuthenticateUserIn(login, password, language);
    pivotIn.setFieldNameFieldLabel(fieldNameFieldLabel);

    final RuleMessages messages = new RuleMessages();
    final User user = new User();
    user.setLogin("sguisse");
    user.setPassword("KO");

    executeTest(pivotIn, messages, user);

    Assert.assertTrue(messages.getErrors().size() == 1);

  }

  /**
   * Description : testAuthentificationUserOk method <br>
   */
  @Test
  public void testAuthentificationUserOk() {
    LOGGER.debug("start testAuthentificationFieldMandatory3");
    final String login = "sguisse";
    final String password = "MyPassword";
    final String language = "FR";

    final AuthenticateUserIn pivotIn = new AuthenticateUserIn(login, password, language);
    pivotIn.setFieldNameFieldLabel(fieldNameFieldLabel);

    final RuleMessages messages = new RuleMessages();
    final User user = new User();
    user.setLogin("sguisse");
    user.setPassword("MyPassword");

    executeTest(pivotIn, messages, user);

    Assert.assertTrue(messages.getErrors().size() == 0);

  }

}

package com.sgu.infowksporga.business.rules.authentification.xml;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sgu.core.framework.drools.rules.validation.RuleMessages;
import com.sgu.core.framework.drools.test.AbstractBusinessRulesTest;
import com.sgu.core.framework.drools.util.UtilDrools;
import com.sgu.core.framework.pivot.FieldNameFieldLabelAssociation;
import com.sgu.infowksporga.business.pivot.authentication.AuthenticateUserIn;

/**
 * Description : PropertiesFieldTest class<br>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/spring-test.xml")
public class XmlAuthentificationBusinessRulesTest extends AbstractBusinessRulesTest {
  /**
   * field Name Field Label to display error message
   */
  private FieldNameFieldLabelAssociation fieldNameFieldLabel;

  /**
   * Description : initializeTests method <br>
   */
  @Before
  public void initializeTests() {
    fieldNameFieldLabel = new FieldNameFieldLabelAssociation();
    fieldNameFieldLabel.put("txtLogin", "userLogin");
    fieldNameFieldLabel.put("txtPassword", "password");
    fieldNameFieldLabel.put("cbLanguage", "language");
  }

  /**
   * The logger
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(XmlAuthentificationBusinessRulesTest.class);

  /**
   * Description : testPropertiesFieldStringSpecial method <br>
   *
   * @throws JAXBException
   */
  @Test
  public void testPropertiesFieldXML() throws JAXBException {
    LOGGER.debug("start testPropertiesFieldXML");

    final String xmlRulesLocation = "com/sgu/projectmanager/business/rules/drools-validation.xml";

    final String login = null;
    final String password = "rer";
    final String language = null;

    final AuthenticateUserIn pivotIn = new AuthenticateUserIn(login, password, language);
    pivotIn.setFieldNameFieldLabel(fieldNameFieldLabel);

    // Create the object to be filled with engine rules result
    final RuleMessages messages = new RuleMessages();

    /* Execute rule engine */
    executeTest(xmlRulesLocation, pivotIn, messages);

    // Control test execution
    Assert.assertTrue(messages.getErrors().size() == 2);
  }

  /**
   * Execute test.
   *
   * @param xmlRulesLocation the xml rules location
   * @param beanToTest the bean to test
   * @param messages the messages
   */
  private void executeTest(final String xmlRulesLocation, final Object beanToTest, final RuleMessages messages) {

    UtilDrools.executeDroolsOnBeanProperties(xmlRulesLocation, beanToTest, messages);

    UtilDrools.logAllMessages(messages);

    if (messages.hasError()) {
      LOGGER.error("Rules not compliant");
    }

  }
}

package com.sgu.infowksporga.business.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sgu.core.framework.dao.jpa.test.AbstractJpaTest;
import com.sgu.infowksporga.business.entity.User;

/**
 * Populate the data base with some dummy data.
 */
@org.junit.FixMethodOrder(MethodSorters.JVM)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/META-INF/spring/application-context.xml", })
@Rollback(false)
@Transactional
public class Populate extends AbstractJpaTest {

  static {
    System.setProperty("app-env", "");
  }

  @Test
  public void populate() throws IOException {
    // populateSystem();
    // populateDemo();
    populateInfoWkspOrgaUser();
  }

  /**
   * Persist system data from CSV file
   */
  /*
   * private void populateSystem() throws IOException {
   * csvForJpa.reset("csv/test/system", SystemUser.class, SystemRole.class, SystemAuthorization.class,
   * SystemRoleAssignment.class);
   * }
   */

  /**
   * Persist demo data from CSV file
   */
  /*
   * private void populateDemo() throws IOException {
   * csvForJpa.reset("csv/test/demo", Browser.class, Flag.class, Note.class, Wine.class);
   * }
   */

  /**
   * Persist info wrksp orga User data from CSV file
   */
  private void populateInfoWkspOrgaUser() throws IOException {
    csvForJpa.reset("csv/test", new Class[] { User.class }, StandardCharsets.UTF_8.name());
  }

}

package com.sgu.infowksporga.business.dao;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sgu.core.framework.dao.jpa.test.AbstractJpaTest;
import com.sgu.infowksporga.business.dao.api.IWorkspaceDao;
import com.sgu.infowksporga.business.entity.Workspace;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:/META-INF/spring/application-context.xml")
@Rollback
@Transactional
public class WorkspaceRepositoryTest extends AbstractJpaTest {

  /**
   * DAO to test
   */
  @Autowired
  private IWorkspaceDao dao;

  /*
   * @Before
   * public void prepareData() throws IOException {
   * for (final Flag flag : csvForJpa.toJpa(Flag.class, "csv/test/demo/flag.csv", true)) {
   * em.persist(flag);
   * }
   * em.flush();
   * em.clear();
   * }
   */

  @Test
  public void testFindAllWithUsage() {

    Workspace entity = dao.findWorkspaceWithViewsAndAttr("sguisse");
    entity.getViews().get(0).getAttributes();

    Assert.assertNotNull(entity);
  }

}

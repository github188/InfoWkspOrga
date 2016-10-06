package com.sgu.infowksporga.spring.tool;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sgu.core.framework.spring.service.remote.IGetRemoteSpringObjectService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring-test.xml")
public class SpringRemoteObjectFinderServiceTest {

  @Autowired
  private IGetRemoteSpringObjectService remoteService;

  @Test
  public void getRemoteDefinedApplicationName() {
    System.out.println("----------------------------------------------------------------------------------------------------------");
    System.out.println("Remote Sercive call result : '" + remoteService.getRemoteSpringObject("Application.Name") + "  "
                       + remoteService.getRemoteSpringObject("Application.Version") + "'");
    System.out.println("----------------------------------------------------------------------------------------------------------");
  }
}
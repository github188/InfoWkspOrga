package com.sgu.infowksporga.jfx.rest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sgu.infowksporga.business.pivot.authentication.AuthenticateUserIn;
import com.sgu.infowksporga.business.pivot.authentication.AuthenticateUserOut;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.rest.RestServiceMapping;

@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("/spring-test.xml")
public class CallRestWebServiceTest {

  @Test
  public void testAuthentificationService() {
    AuthenticateUserIn pivotIn = new AuthenticateUserIn();
    pivotIn.setUserLogin("sguisse");
    pivotIn.setPassword("sguisse");

    // Call the service
    final AuthenticateUserOut pivotOut = (AuthenticateUserOut) UtilInfoWrkspOrga.callRestBusinessService(RestServiceMapping.URL_SERVICE_AUTHENTICATE_USER,
                                                                                                        pivotIn, AuthenticateUserOut.class);

    Assert.assertNotNull(pivotOut);
  }
}

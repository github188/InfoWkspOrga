import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sgu.infowksporga.business.pivot.authentication.AuthenticateUserIn;
import com.sgu.infowksporga.business.service.rest.serialized.api.IAuthenticateUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class ServiceTest {

  @Autowired
  private IAuthenticateUserService service;

  @Test
  public void testDateService() {
    System.out.println(service.executeService(new AuthenticateUserIn("userId", "password", "fr")));
  }
}
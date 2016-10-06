package com.sgu.infowksporga.business.service.rest.serialized;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.interfaces.GenerateInterface;
import com.sgu.apt.annotation.rest.Rest;
import com.sgu.core.framework.drools.rules.validation.RuleMessages;
import com.sgu.core.framework.drools.util.UtilDrools;
import com.sgu.core.framework.pivot.AbstractOut.ReturnCode;
import com.sgu.infowksporga.business.dao.repository.UserRepository;
import com.sgu.infowksporga.business.service.rest.serialized.api.AbstractSerializedService;
import com.sgu.infowksporga.business.service.rest.serialized.api.IAuthenticateUserService;
import com.sgu.infowksporga.business.entity.User;
import com.sgu.infowksporga.business.pivot.authentication.AuthenticateUserIn;
import com.sgu.infowksporga.business.pivot.authentication.AuthenticateUserOut;
import com.sgu.infowksporga.business.rules.RulesConstant;

/**
 * The Class AuthentificationService.
 */
@Service
@Transactional
public class AuthenticateUserService extends AbstractSerializedService implements IAuthenticateUserService {

  /**
   * DAO to test
   */
  @Autowired
  private UserRepository repository;

  //---------------------------------------------------------------------------------------------------------------------------------------
  @GenerateInterface(baseSource = AnnotationConfig.INTERFACE_SERVICE_TARGET_SOURCE_FOLDER,
  interfacePackage = "com.sgu.infowksporga.business.service.rest.serialized.api", interfaceName = "IAuthenticateUserService",
  pivotIn = "com.sgu.infowksporga.business.pivot.authentication.AuthenticateUserIn",
  pivotOut = "com.sgu.infowksporga.business.pivot.authentication.AuthenticateUserOut")

  @Rest(requestControllerUri = "/authentification", requestServiceUri = "/user",
  controllerPackage = "com.sgu.infowksporga.web.rest.controller", controllerName = "AuthenticateUserController",
  method = "RequestMethod.POST",
  produces = "{ GMediaType.APPLICATION_JSON_VALUE, GMediaType.APPLICATION_JAVA_SERIALIZED_OBJECT_VALUE, GMediaType.APPLICATION_XML_VALUE }",
  controllerBaseSource = AnnotationConfig.REST_CONTROLLER_TARGET_SOURCE_FOLDER,
  restServiceMappingTargetClass = AnnotationConfig.REST_REQUEST_MAPPING_TARGET)

  //---------------------------------------------------------------------------------------------------------------------------------------

  public AuthenticateUserOut executeService(AuthenticateUserIn in) {
    AuthenticateUserOut out = new AuthenticateUserOut();

    User user = repository.findBy("login", in.getUserLogin());
    if (user == null) {
      user = new User();
      user.setLogin("");// to prevent nullpointer in drools
      user.setPassword("");// to prevent nullpointer in drools
    }

    //callDroolsEngineToValidateUser(in, out, user);

    return out;
  }

  /**
   * Description : runDroolsEngine method <br>
   *
   * @param authenticateIn
   * @param out
   * @param user
   */
  private void callDroolsEngineToValidateUser(final AuthenticateUserIn authenticateIn, final AuthenticateUserOut out, final User user) {
    final RuleMessages messages = new RuleMessages();
    UtilDrools.executeDroolsProcess(RulesConstant.KSESSION_AUTHENTIFICATION, RulesConstant.PROCESS_CHECK_AUTHENTIFICATION, messages,
                                    authenticateIn, user);

    if (messages.hasError()) {
      out.setErrors(messages.getErrors());
      out.setReturnCode(ReturnCode.BUSINESS_ERROR);
    }
  }

}

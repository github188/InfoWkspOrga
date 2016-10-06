package com.sgu.infowksporga.jfx.zfacade.remote.authorization;

import javax.swing.ProgressMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.crypto.UtilCrypto;
import com.sgu.core.framework.drools.rules.validation.RuleMessages;
import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.pivot.AbstractOut.ReturnCode;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.core.framework.util.Properties;
import com.sgu.infowksporga.business.pivot.authentication.AuthenticateUserIn;
import com.sgu.infowksporga.business.pivot.authentication.AuthenticateUserOut;
import com.sgu.infowksporga.jfx.connexion.ConnexionDlg;
import com.sgu.infowksporga.jfx.connexion.cbbox.ComboBoxLanguageVo;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UserPreferencesConstant;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.zfacade.AbstractBusinessFacade;
import com.sgu.infowksporga.jfx.zfacade.remote._init.LoadPerspectivesStructureFacade;
import com.sgu.infowksporga.rest.RestServiceMapping;

/**
 * Description : AuthenticateFacade class<br>
 */
@Service
public class AuthenticateFacade extends AbstractBusinessFacade<AuthenticateUserOut, ConnexionDlg> {

  /**
   * The logger.
   */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticateFacade.class);

  /**
   * First build in checkFieldsValidity to be used in execute if no error found
   */
  private AuthenticateUserIn pivotIn;

  /**
   * Constructor<br>
   */
  public AuthenticateFacade() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AuthenticateUserOut checkFieldsValidity(final ConnexionDlg container, final ProgressMonitor monitor) {
    UtilGUI.markAllFieldsAsValid(container.getPnlConnexion());

    final String login = container.getTxtLogin().getText();
    final String password = container.getTxtPassword().getValue();
    final String language = ((ComboBoxLanguageVo) container.getCbLanguage().getSelectedItem()).getLabel();

    pivotIn = new AuthenticateUserIn(login, password, language);
    pivotIn.setFieldNameFieldLabel(container.getFieldNameFieldLabel());
    final RuleMessages messages = new RuleMessages();

    /*
     * UtilDrools.executeDroolsProcess(RulesConstant.KSESSION_AUTHENTIFICATION, RulesConstant.PROCESS_CHECK_AUTHENTIFICATION, messages,
     * pivotIn);
     */
    // Transform Drools message to pivot type to be analyzed by the Fwk
    final AuthenticateUserOut checkResult = new AuthenticateUserOut();
    if (messages.hasInformation()) {
      checkResult.setReturnCode(ReturnCode.BUSINESS_INFORMATION);
    }

    if (messages.hasWarning()) {
      checkResult.setReturnCode(ReturnCode.BUSINESS_WARNING);
    }

    if (messages.hasError()) {
      checkResult.setReturnCode(ReturnCode.BUSINESS_ERROR);
    }

    checkResult.addErrors(messages.getErrors());
    checkResult.addWarnings(messages.getWarnings());
    checkResult.addInformations(messages.getInformations());

    return checkResult;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public AuthenticateUserOut execute(final ConnexionDlg connexionDlg) throws TechnicalException, BusinessException {

    // Call the service
    final AuthenticateUserOut pivotOut = (AuthenticateUserOut) UtilInfoWrkspOrga.callRestBusinessService(RestServiceMapping.URL_SERVICE_AUTHENTICATE_USER,
                                                                                                        pivotIn, AuthenticateUserOut.class);

    return pivotOut;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refreshScreen(final AuthenticateUserOut output, final ConnexionDlg container, final StringBuilder reportMessages,
  final ProgressMonitor monitor) {

    if (reportMessages != null) {
      UtilDlgMessage.error(reportMessages.toString(), "");
    }
    else if (output != null) {
      // User is correctly identified
      updateUserDiskSettings(container);
      container.dispose();
      // Store the current user login in session
      GUISessionProxy.getGuiSession().setCurrentUser(container.getTxtLogin().getText());

      // Pour la creation de la perspective de l'utilisateur si elle n'existe pas déjà
      final LoadPerspectivesStructureFacade facade = SpringBeanHelper.getImplementationByInterface(LoadPerspectivesStructureFacade.class);
      GUISession.getInstance().getServiceDelegate().execute(facade, null);
    }
  }

  /**
   * Description : updateUserDiskSettings in memory and on disk <br>
   *
   * @param connexionDlg reference
   */
  private void updateUserDiskSettings(final ConnexionDlg connexionDlg) {

    final ComboBoxLanguageVo selectedLanguage = (ComboBoxLanguageVo) connexionDlg.getCbLanguage().getModel().getSelectedItem();
    // Update selected locale in memory
    I18nHelperApp.getI18nHelper().setLocale(selectedLanguage.getLocale());

    // Update preferred locale and user login in local user settings (on disk)
    final Properties userLocalSettings = GUISession.getInstance().getUserLocalSettings();
    userLocalSettings.put(UserPreferencesConstant.USER_LANGUAGE_SETTING, selectedLanguage.getLocale().getLanguage());
    userLocalSettings.put(UserPreferencesConstant.USER_LOGIN_SETTING, connexionDlg.getTxtLogin().getText());
    userLocalSettings.put(UserPreferencesConstant.USER_PASSWORD_SETTING,
                          UtilCrypto.cryptString(connexionDlg.getTxtPassword().getValue(), UserPreferencesConstant.USER_PASSWORD_SETTING));
    UtilInfoWrkspOrga.updateUserSettingsOnDisk();
  }

}

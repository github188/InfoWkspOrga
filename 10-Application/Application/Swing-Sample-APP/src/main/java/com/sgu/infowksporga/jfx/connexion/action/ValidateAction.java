package com.sgu.infowksporga.jfx.connexion.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.connexion.ConnexionDlg;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.zfacade.remote.authorization.AuthenticateFacade;

/**
 * Description : ValidateAction class<br>
 *
 * @author SGU
 */
public class ValidateAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 2568554388620155754L;

  /**
   * Ref to the dialog
   */
  private final ConnexionDlg connexionDlg;

  /**
   * Constructor<br>
   *
   * @param connexionDlg Ref to the dialog
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "connexion.dlg.action.validate.text", value = "Connection"), // Force /n
                @I18nProperty(key = "connexion.dlg.action.validate.icon", value = "/icons/lock.png"), // Force /n
  })
  public ValidateAction(final ConnexionDlg connexionDlg) {
    super("connexion.dlg.action.validate");
    this.connexionDlg = connexionDlg;
  }

  @Override
  public void actionPerformed(final ActionEvent e) {

    final AuthenticateFacade facade = SpringBeanHelper.getImplementationByInterface(AuthenticateFacade.class);
    GUISession.getInstance().getServiceDelegate().execute(facade, connexionDlg);

  }

}
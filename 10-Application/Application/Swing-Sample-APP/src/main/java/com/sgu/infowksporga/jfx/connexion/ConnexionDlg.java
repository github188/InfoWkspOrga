package com.sgu.infowksporga.jfx.connexion;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.crypto.UtilCrypto;
import com.sgu.core.framework.gui.swing.GButton;
import com.sgu.core.framework.gui.swing.GComboBox;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.GLabelField;
import com.sgu.core.framework.gui.swing.dialog.GDialog;
import com.sgu.core.framework.gui.swing.overlay.GDefaultOverlayable;
import com.sgu.core.framework.gui.swing.overlay.UtilOverlay;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.textfield.GPasswordField;
import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.pivot.FieldNameFieldLabelAssociation;
import com.sgu.core.framework.util.Properties;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.jfx.connexion.action.CancelAction;
import com.sgu.infowksporga.jfx.connexion.action.ValidateAction;
import com.sgu.infowksporga.jfx.connexion.cbbox.ComboBoxLanguageRenderer;
import com.sgu.infowksporga.jfx.connexion.cbbox.ComboBoxLanguageVo;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.UserPreferencesConstant;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;

import net.miginfocom.swing.MigLayout;

/**
 * Description : ConnexionDlg class<br>
 */
public class ConnexionDlg extends GDialog implements ComponentListener {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -8198672854160646831L;

  /**
   * Ref to properties panel
   */
  private GPanel pnlConnexion;

  /**
   * Ref to the label login
   */
  private GLabel lblLogin;

  /**
   * txt Login
   */
  private GTextField txtLogin;

  /**
   * Ref to the label password
   */
  private GLabel lblPassword;

  /**
   * txt password
   */
  private GPasswordField txtPassword;

  /**
   * Ref to the label language
   */
  private GLabel lblLanguage;

  /**
   * cb language
   */
  private GComboBox cbLanguage;

  /**
   * Ref to the cancel action
   */
  private CancelAction cancelAction;

  /**
   * Ref to validate button
   */
  private GButton btValidate;

  /**
   * Store the field name of textField, combo, checkbox, ... To manage business error
   */
  private FieldNameFieldLabelAssociation fieldNameFieldLabel;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "connexion.dlg.title", value = "InfoWrkspOrga Authentification"), // Force /n

  })
  public ConnexionDlg() {
    super("connexion.dlg.title", true);
    buildUI();
  }

  /** {@inheritDoc} */
  @Override
  public void initUI() {
    super.initUI();
    UtilInfoWrkspOrga.setInfoWrkspOrgaAppIcon(this);
    setResizable(false);
    fieldNameFieldLabel = new FieldNameFieldLabelAssociation(3);
  }

  /** {@inheritDoc} */
  @Override
  public void createListeners() {
    super.createListeners();
    defineCloseAction();
    addComponentListener(this);
  }

  /**
   * @see #fieldNameFieldLabel
   * @return the fieldNameFieldLabel : See field description
   */
  public FieldNameFieldLabelAssociation getFieldNameFieldLabel() {
    return fieldNameFieldLabel;
  }

  /**
   * Description : defineCloseAction method <br>
   */
  private void defineCloseAction() {
    setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {

      /** {@inheritDoc} */
      @Override
      public void windowClosing(final WindowEvent e) {
        // Simulate a press on exit menu
        cancelAction.actionPerformed(new ActionEvent(new GLabel(), 0, ""));
      }
    });
  }

  /**
   * Gets the pnl main mig layout data.
   *
   * @return the pnl main mig layout data
   */
  protected MigLayout getPnlMainMigLayoutData() {
    return new MigLayout("insets 0 0 0 0", "[grow,fill]");
  }

  /**
   * Description : buildDialog method <br>
   */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "connexion.dlg.fieldset.title", value = "Veuillez vous authentifier"), // Force /n

                @I18nProperty(key = "connexion.dlg.login.text", value = "login"), // Force /n
                @I18nProperty(key = "connexion.dlg.login.mandatory", value = "true"), // Force /n

                @I18nProperty(key = "connexion.dlg.password.text", value = "mot de passe"),// Force /n
                @I18nProperty(key = "connexion.dlg.password.mandatory", value = "true"), // Force /n

                @I18nProperty(key = "connexion.dlg.language.text", value = "langue"), // Force /n
                @I18nProperty(key = "connexion.dlg.language.mandatory", value = "true"), // Force /n

  })
  public void createUI() {
    final ValidateAction validateAction = new ValidateAction(this);
    cancelAction = new CancelAction(this);

    // Build properties PAnel
    pnlConnexion = new GPanel(new MigLayout("insets 2 5 2 2, fill", "[right][grow]", ""));
    pnlConnexion.setBorder(BorderFactory.createTitledBorder(I18nHelperApp.getMessage("connexion.dlg.fieldset.title")));

    lblLogin = new GLabelField();
    lblLogin.setBundleConfiguration("connexion.dlg.login", I18nHelperApp.getI18nHelper());
    txtLogin = new GTextField();
    txtLogin.setName("txtLogin");
    txtLogin.addActionListener(validateAction); // To allow validation on enter key press
    final GDefaultOverlayable txtLoginOverlay = UtilOverlay.addOverlayErrorRenderer(txtLogin);
    txtLogin.setOverlay(txtLoginOverlay);
    txtLogin.setLabel(lblLogin);

    lblPassword = new GLabelField();
    lblPassword.setBundleConfiguration("connexion.dlg.password", I18nHelperApp.getI18nHelper());
    txtPassword = new GPasswordField(15);
    txtPassword.setName("txtPassword");
    txtPassword.addActionListener(validateAction); // To allow validation on enter key press
    final GDefaultOverlayable txtPasswordOverlay = UtilOverlay.addOverlayErrorRenderer(txtPassword);
    txtPassword.setOverlay(txtPasswordOverlay);
    txtPassword.setLabel(lblPassword);

    lblLanguage = new GLabelField();
    lblLanguage.setBundleConfiguration("connexion.dlg.language", I18nHelperApp.getI18nHelper());
    cbLanguage = new GComboBox();
    cbLanguage.setName("cbLanguage");
    cbLanguage.setRenderer(new ComboBoxLanguageRenderer(cbLanguage));
    final GDefaultOverlayable cbLanguageOverlay = UtilOverlay.addOverlayErrorRenderer(cbLanguage);
    cbLanguage.setOverlay(cbLanguageOverlay);
    cbLanguage.setLabel(lblLanguage);

    /*
     * Store label field associated to component
     */
    fieldNameFieldLabel.put(txtLogin.getName(), lblLogin.getBundleConfigKey());
    fieldNameFieldLabel.put(txtPassword.getName(), lblPassword.getBundleConfigKey());
    fieldNameFieldLabel.put(cbLanguage.getName(), lblLanguage.getBundleConfigKey());

    pnlConnexion.add(lblLogin);
    pnlConnexion.add(txtLoginOverlay, "growx, wrap");
    pnlConnexion.add(lblPassword);
    pnlConnexion.add(txtPasswordOverlay, "growx, wrap");
    pnlConnexion.add(lblLanguage);
    pnlConnexion.add(cbLanguageOverlay, "growx, wrap");

    pnlMain.add(pnlConnexion, "wrap");

    // Build the button panel
    final GPanel pnlButton = buildButtonPanel();
    pnlButton.setLayout(new MigLayout("insets 5 0 5 0, fill", "[right][left]"));
    btValidate = validateAction.createButton(true, true);
    final GButton btCancel = cancelAction.createButton(true);

    pnlButton.add(btValidate, "width 110px");
    pnlButton.add(btCancel, "width 110px");

    pnlMain.add(pnlButton, "growx");

  }

  /**
   * Description :buildImagePanel method <br>
   *
   * @return the image panel
   */
  private Component buildImagePanel() {
    final GPanel imagePanel = new GPanel(new BorderLayout());

    return imagePanel;
  }

  /**
   * Description : define Field Values method <br>
   */
  @Override
  public void fillUI() {
    super.fillUI();

    final Properties userLocalSettings = GUISession.getInstance().getUserLocalSettings();
    final String loginSettings = userLocalSettings.getProperty(UserPreferencesConstant.USER_LOGIN_SETTING);

    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        if (UtilString.isNotBlank(loginSettings)) {
          txtLogin.setText(loginSettings);
          UtilGUI.requestFocus(txtPassword);
        }

        final String passwordSettings = userLocalSettings.getProperty(UserPreferencesConstant.USER_PASSWORD_SETTING);
        if (UtilString.isNotBlank(passwordSettings)) {
          txtPassword.setText(UtilCrypto.decryptString(passwordSettings, UserPreferencesConstant.USER_PASSWORD_SETTING));
          UtilGUI.requestFocus(btValidate);
        }

        cbLanguage.setModel(createComboBoxLanguageModel());
      }
    });

  }

  /**
   * Description : createComboBoxLanguageModel method <br>
   *
   * @return the model to associate to the language combobox model
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "language.french", value = "Français"), // Force /n
                @I18nProperty(key = "language.french.icon", value = "/icons/flag/flag-fr.png"), // Force /n

                @I18nProperty(key = "language.english", value = "English"),// Force /n
                @I18nProperty(key = "language.english.icon", value = "/icons/flag/flag-uk.png"), // Force /n

                @I18nProperty(key = "language.italia", value = "Italiano"), // Force /n
                @I18nProperty(key = "language.italia.icon", value = "/icons/flag/flag-it.png"), // Force /n

                @I18nProperty(key = "language.germany", value = "Deutch"),// Force /n
                @I18nProperty(key = "language.germany.icon", value = "/icons/flag/flag-germany.png"), // Force /n
  })
  private ComboBoxModel createComboBoxLanguageModel() {
    // Build the list of accessible language
    final List<ComboBoxLanguageVo> languages = new ArrayList<ComboBoxLanguageVo>(5);
    languages.add(new ComboBoxLanguageVo(" ", null, null));
    languages.add(new ComboBoxLanguageVo("language.french", "/icons/flag/flag-fr.png", Locale.FRENCH));
    languages.add(new ComboBoxLanguageVo("language.english", "/icons/flag/flag-uk.png", Locale.ENGLISH));
    languages.add(new ComboBoxLanguageVo("language.italia", "/icons/flag/flag-it.png", Locale.ITALY));
    languages.add(new ComboBoxLanguageVo("language.germany", "/icons/flag/flag-germany.png", Locale.GERMANY));

    final DefaultComboBoxModel model = new DefaultComboBoxModel(languages.toArray());

    // Select the preferred language define in user settings
    final String preferredLanguage = GUISession.getInstance().getUserLocalSettings()
                                               .getProperty(UserPreferencesConstant.USER_LANGUAGE_SETTING);
    for (final ComboBoxLanguageVo languageVo : languages) {
      if (languageVo.getLocale() != null && languageVo.getLocale().getLanguage().equals(preferredLanguage)) {
        model.setSelectedItem(languageVo);
        break;
      }
    }

    return model;
  }

  /**
   * @see #pnlConnexion
   * @return the connexionPanel : See field description
   */
  public GPanel getPnlConnexion() {
    return pnlConnexion;
  }

  /**
   * @see #txtLogin
   * @return the txtLogin : See field description
   */
  public GTextField getTxtLogin() {
    return txtLogin;
  }

  /**
   * @see #lblLogin
   * @return the lblLogin : See field description
   */
  public GLabel getLblLogin() {
    return lblLogin;
  }

  /**
   * @see #lblPassword
   * @return the lblPassword : See field description
   */
  public GLabel getLblPassword() {
    return lblPassword;
  }

  /**
   * @see #txtPassword
   * @return the txtPassword : See field description
   */
  public GPasswordField getTxtPassword() {
    return txtPassword;
  }

  /**
   * @see #lblLanguage
   * @return the lblLanguage : See field description
   */
  public GLabel getLblLanguage() {
    return lblLanguage;
  }

  /**
   * @see #cbLanguage
   * @return the cbLanguage : See field description
   */
  public GComboBox getCbLanguage() {
    return cbLanguage;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void componentResized(final ComponentEvent e) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void componentMoved(final ComponentEvent e) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void componentShown(final ComponentEvent e) {
    // Resize combo box language ti the size of TextField
    cbLanguage.setPreferredSize(new Dimension(txtLogin.getPreferredSize().width, cbLanguage.getPreferredSize().height));
    this.validate();
    this.repaint();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void componentHidden(final ComponentEvent e) {
  }

}

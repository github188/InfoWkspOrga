package com.sgu.infowksporga.jfx.view.adlg.config.mvc;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.screen.AGView;
import com.sgu.core.framework.gui.jfx.util.UtilControl;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.jfx.component.HorodatePanelFxml;
import com.sgu.infowksporga.jfx.component.IdentityCardPanelFxml;
import com.sgu.infowksporga.jfx.component.StylePanelFxml;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.view.adlg.config.mvc.panel.AConfigurationPanelFxml;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class AViewDlgViewFxml.
 */
@Slf4j
@Getter
@Setter
public abstract class AViewDlgViewFxml<M extends AViewDlgModel, C extends AViewDlgController> extends AGView<AViewDlgModel, AViewDlgController> implements Initializable {

  /** The dlg pnl worksapce. */
  @FXML
  private DialogPane dlgPnlworkspace;

  /** The pnl identity card. */
  @FXML
  private IdentityCardPanelFxml pnlIdentityCardController;

  /** The pnl style. */
  @FXML
  private StylePanelFxml pnlStyleController;

  /** The pnl horodate. */
  @FXML
  private HorodatePanelFxml pnlHorodateController;

  /*------------------------------------------------------*/
  // ==> Controls not defined in FXML file
  /*------------------------------------------------------*/
  private ButtonType validateButtonType;

  private ButtonType cancelButtonType;

  /** The Constant dialogBaseTitle. */
  private static final String dialogBaseTitle = I18nHelperApp.getMessage(AViewDlgScreen.VIEW_DLG_TITLE_KEY);

  /** The Constant VALIDATE_ACTION_BUNDLE_KEY. */
  private static final String VALIDATE_ACTION_BUNDLE_KEY = "dialog.view.action.validate";

  /** The Constant CANCEL_ACTION_BUNDLE_KEY. */
  private static final String CANCEL_ACTION_BUNDLE_KEY = "dialog.view.action.cancel";

  /**
   * The Constructor.
   */
  public AViewDlgViewFxml() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createUI() {
    super.createUI();

    defineButtonBar();

  }

  /**
   * Define button bar.
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = VALIDATE_ACTION_BUNDLE_KEY + I18NConstant.TEXT, value = "Validate"), // Force /n
                @I18nProperty(key = VALIDATE_ACTION_BUNDLE_KEY + I18NConstant.TOOLTIP_TEXT, value = "Valide l'action de l'utilisateur en mémoire."), // Force /n
                @I18nProperty(key = VALIDATE_ACTION_BUNDLE_KEY + I18NConstant.ICON, value = "/icons/save_memory.png"), // Force /n
                @I18nProperty(key = VALIDATE_ACTION_BUNDLE_KEY + I18NConstant.NAME, value = "ACTION_VIEW_PROPERTIES_VALIDATE"), // Force /n
                //-----------------------------------
                @I18nProperty(key = CANCEL_ACTION_BUNDLE_KEY + I18NConstant.TEXT, value = "Cancel"), // Force /n
                @I18nProperty(key = CANCEL_ACTION_BUNDLE_KEY + I18NConstant.TOOLTIP_TEXT, value = "Annule les modifications apportées aux propriétés de la vue."), // Force /n
                @I18nProperty(key = CANCEL_ACTION_BUNDLE_KEY + I18NConstant.NAME, value = "ACTION_VIEW_PROPERTIES_CANCEL"), })
  private void defineButtonBar() {
    // Set the button types.
    validateButtonType = new ButtonType("Validate", ButtonData.OK_DONE);
    cancelButtonType = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
    dlgPnlworkspace.getButtonTypes().addAll(validateButtonType, cancelButtonType);

    UtilControl.applyBundleConfigToButton(VALIDATE_ACTION_BUNDLE_KEY, getButtonValidate());
    UtilControl.applyBundleConfigToButton(CANCEL_ACTION_BUNDLE_KEY, getButtonCancel());
  }

  /**
   * Gets the button validate.
   *
   * @return the button validate
   */
  public Button getButtonValidate() {
    return (Button) dlgPnlworkspace.lookupButton(validateButtonType);
  }

  /**
   * Gets the button cancel.
   *
   * @return the button cancel
   */
  public Button getButtonCancel() {
    return (Button) dlgPnlworkspace.lookupButton(cancelButtonType);
  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();

  }

  /** {@inheritDoc} */
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    super.initialize(location, resources);

  }

  /**
   * Apply display mode consult.
   */
  @Override
  public void applyDisplayModeConsult() {
    getButtonValidate().setVisible(false);
    UtilControl.applyBundleConfigToButton(I18NConstant.ACTION_CLOSE, getButtonCancel());
    applyDisplayModeToDialogTitle(I18NConstant.MODE_DISPLAY_CONSULTATION);

    pnlIdentityCardController.applyDisplayModeConsult();
    getPnlConfigurationController().applyDisplayModeConsult();
    pnlHorodateController.applyDisplayModeConsult();
    pnlStyleController.applyDisplayModeConsult();

    Platform.runLater(() -> {
      // build Model
      //model().mapworkspaceToModel(GUISessionProxy.getCurrentworkspace().getworkspace());
    });

  }

  /**
   * Apply display mode delete.
   */
  @Override
  public void applyDisplayModeDelete() {
    UtilControl.applyBundleConfigToButton(I18NConstant.ACTION_DELETION, getButtonValidate());
    applyDisplayModeToDialogTitle(I18NConstant.MODE_DISPLAY_DELETION);

    pnlIdentityCardController.applyDisplayModeDelete();
    getPnlConfigurationController().applyDisplayModeDelete();
    pnlHorodateController.applyDisplayModeDelete();
    pnlStyleController.applyDisplayModeDelete();

    Platform.runLater(() -> {
      // build Model
      //model().mapworkspaceToModel(GUISessionProxy.getCurrentworkspace().getworkspace());
    });
  }

  /**
   * Apply display mode update.
   */
  @Override
  public void applyDisplayModeUpdate() {
    UtilControl.applyBundleConfigToButton(I18NConstant.ACTION_UPDATE, getButtonValidate());
    applyDisplayModeToDialogTitle(I18NConstant.MODE_DISPLAY_UPDATE);

    pnlIdentityCardController.applyDisplayModeUpdate();
    getPnlConfigurationController().applyDisplayModeUpdate();
    pnlHorodateController.applyDisplayModeUpdate();
    pnlStyleController.applyDisplayModeUpdate();

    Platform.runLater(() -> {
      // build Model
      // model().mapViewToModel(GUISessionProxy.getCurrentWorkspace().getworkspace());
    });

  }

  /**
   * Apply display mode copy.
   */
  @Override
  public void applyDisplayModeCopy() {
    UtilControl.applyBundleConfigToButton(I18NConstant.ACTION_DUPLICATE, getButtonValidate());
    applyDisplayModeToDialogTitle(I18NConstant.MODE_DISPLAY_DUPLICATE);

    pnlIdentityCardController.applyDisplayModeCopy();
    getPnlConfigurationController().applyDisplayModeCopy();
    pnlHorodateController.applyDisplayModeCopy();
    pnlStyleController.applyDisplayModeCopy();

    Platform.runLater(() -> {
      // build Model
      //model().mapworkspaceToModel(GUISessionProxy.getCurrentworkspace().getworkspace());
    });
  }

  /**
   * Apply display mode create.
   */
  @Override
  public void applyDisplayModeCreate() {
    UtilControl.applyBundleConfigToButton(I18NConstant.ACTION_CREATION, getButtonValidate());
    applyDisplayModeToDialogTitle(I18NConstant.MODE_DISPLAY_CREATION);

    pnlIdentityCardController.applyDisplayModeCreate();
    getPnlConfigurationController().applyDisplayModeCreate();
    pnlHorodateController.applyDisplayModeCreate();
    pnlStyleController.applyDisplayModeCreate();
  }

  /**
   * Apply display mode to dialog title.
   *
   * @param i18nModeDisplay the i18n mode display
   */
  public void applyDisplayModeToDialogTitle(final String i18nModeDisplay) {
    final String dialogModeDisplay = I18nHelperApp.getMessage(i18nModeDisplay);
    screen().getDialog().setTitle(dialogBaseTitle + dialogModeDisplay);
  }

  /**
   * @return the pnlConfigurationController
   */
  public abstract AConfigurationPanelFxml getPnlConfigurationController(); // {
  //return pnlConfigurationController;
  //}

  /**
   * @param pnlConfigurationController the pnlConfigurationController to set
   */
  public abstract void setPnlConfigurationController(final AConfigurationPanelFxml pnlConfigurationController); // {
  //this.pnlConfigurationController = pnlConfigurationController;
  //}

}

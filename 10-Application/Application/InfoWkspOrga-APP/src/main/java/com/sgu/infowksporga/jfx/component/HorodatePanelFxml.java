package com.sgu.infowksporga.jfx.component;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.pane.GGridPane;
import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.core.framework.gui.jfx.screen.AGScreen;
import com.sgu.core.framework.gui.jfx.screen.AGView;
import com.sgu.core.framework.gui.jfx.util.UtilControl;
import com.sgu.core.framework.i18n.util.I18NConstant;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class HorodatePanelFxml.
 *
 * @see {ViewComponentAttributesGenerator}
 */
@Slf4j
@Setter
@Getter
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // Force /n
              @I18nProperty(key = HorodatePanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.TEXT, value = "Horodatage"), // Force /n
              @I18nProperty(key = HorodatePanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.STYLE_CSS, value = "-fx-text-fill: #11468E"), // Force /n
              @I18nProperty(key = HorodatePanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.ICON, value = "/icons/clock.png"), // Force /n
              //----------
              @I18nProperty(key = HorodatePanelFxml.PROPERTIES_PREFIX + "createdDate" + I18NConstant.TEXT, value = "Créé le"), // Force /n
              @I18nProperty(key = HorodatePanelFxml.PROPERTIES_PREFIX + "createdDate" + I18NConstant.TOOLTIP_TEXT, value = "Date et heure de création\""), // Force /n
              //----------
              @I18nProperty(key = HorodatePanelFxml.PROPERTIES_PREFIX + "lastModifiedDate" + I18NConstant.TEXT, value = "Modifié le"), // Force /n
              @I18nProperty(key = HorodatePanelFxml.PROPERTIES_PREFIX + "lastModifiedDate" + I18NConstant.TOOLTIP_TEXT, value = "Date et heure de derniére modification"), // Force /n
              //----------
              @I18nProperty(key = HorodatePanelFxml.PROPERTIES_PREFIX + "createdBy" + I18NConstant.TEXT, value = "par"), // Force /n
              @I18nProperty(key = HorodatePanelFxml.PROPERTIES_PREFIX + "createdBy" + I18NConstant.TOOLTIP_TEXT, value = "Personne qui a fait la création"), // Force /n
              //----------
              @I18nProperty(key = HorodatePanelFxml.PROPERTIES_PREFIX + "lastModifiedBy" + I18NConstant.TEXT, value = "par"), // Force /n
              @I18nProperty(key = HorodatePanelFxml.PROPERTIES_PREFIX + "lastModifiedBy" + I18NConstant.TOOLTIP_TEXT,
              value = "Dernière personne à avoir fait une mofication"), // Force /n
})
public class HorodatePanelFxml extends AGView<AGScreen, AGModel, AGController> implements Initializable {

  /** The Constant PROPERTIES_PREFIX. */
  public static final String PROPERTIES_PREFIX = "panel.horodate.";

  //---------------------------------
  //Horodate Panel components
  //---------------------------------

  @FXML
  private TitledPane ttlPnlHistory;

  @FXML
  private AnchorPane achPnlHistory;

  @FXML
  private GGridPane grdPnlHistory;

  @FXML
  private Label lblCreatedDate;

  @FXML
  private Label lblLastModifiedDate;

  @FXML
  private Label lblCreatedDateValue;

  @FXML
  private Label lblCreatedBy;

  @FXML
  private Label lblLastModifiedBy;

  @FXML
  private Label lblLastModifiedDateValue;

  @FXML
  private Label lblCreatedByValue;

  @FXML
  private Label lblLastModifiedByValue;

  /*------------------------------------------------------*/
  // ==> Controls not defined in FXML file
  /*------------------------------------------------------*/

  /**
   * The Constructor.
   */
  public HorodatePanelFxml() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createUI() {
    super.createUI();

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

    UtilControl.applyBundleConfigToLabeledControl(PROPERTIES_PREFIX + "title", ttlPnlHistory);

    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "createdBy", lblCreatedBy);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "createdDate", lblCreatedDate);

    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "lastModifiedBy", lblLastModifiedBy);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "lastModifiedDate", lblLastModifiedDate);

  }

}

package com.sgu.infowksporga.jfx.view.adlg.config.mvc.panel;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.GLabel;
import com.sgu.core.framework.gui.jfx.control.GTextField;
import com.sgu.core.framework.gui.jfx.control.pane.GGridPane;
import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.core.framework.gui.jfx.screen.AGView;
import com.sgu.core.framework.gui.jfx.util.UtilControl;
import com.sgu.core.framework.i18n.util.I18NConstant;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class AConfigurationPanelFxml.
 */
@Slf4j
@Setter
@Getter
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = {// Force /n
              @I18nProperty(key = AConfigurationPanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.TEXT, value = "Configuration"), // Force /n
              @I18nProperty(key = AConfigurationPanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.STYLE_CSS, value = "-fx-text-fill: #11468E"), // Force /n
              @I18nProperty(key = AConfigurationPanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.ICON, value = "/icons/view/configuration.gif"), // Force /n
              //----------
              //----------
              @I18nProperty(key = AConfigurationPanelFxml.PROPERTIES_PREFIX + "category" + I18NConstant.TEXT, value = "Category :"), // Force /n
              @I18nProperty(key = AConfigurationPanelFxml.PROPERTIES_PREFIX + "category" + I18NConstant.TOOLTIP_TEXT, value = "???"), // Force /n
              //----------
              @I18nProperty(key = AConfigurationPanelFxml.PROPERTIES_PREFIX + "tags" + I18NConstant.TEXT, value = "Etiquettes :"), // Force /n
              @I18nProperty(key = AConfigurationPanelFxml.PROPERTIES_PREFIX + "tags" + I18NConstant.TOOLTIP_TEXT,
              value = "Permet de définir des signets pemettant de faciliter le regroupement d'informations lors des recherches "), // Force /n
              //----------
              @I18nProperty(key = AConfigurationPanelFxml.PROPERTIES_PREFIX + "cmmi.practices" + I18NConstant.TEXT, value = "Cmmi Practices :"), // Force /n
              @I18nProperty(key = AConfigurationPanelFxml.PROPERTIES_PREFIX + "cmmi.practices" + I18NConstant.ICON, value = "/icons/cmmi.png"), // Force /n
              @I18nProperty(key = AConfigurationPanelFxml.PROPERTIES_PREFIX + "cmmi.practices" + I18NConstant.TOOLTIP_TEXT,
              value = "Permet de définir les pratiques CMMI couvertes var cette vue"), // Force /n
})
public class AConfigurationPanelFxml extends AGView<AGModel, AGController> implements Initializable {

  /** The Constant AConfigurationPanelFxml.PROPERTIES_PREFIX. */
  public static final String PROPERTIES_PREFIX = "dialog.view.panel.configuration.";

  //---------------------------------
  //Configuration Panel components
  //---------------------------------

  @FXML
  private TitledPane ttlPnlViewConfig;

  @FXML
  private AnchorPane ancPnlViewConfig;

  @FXML
  private GGridPane grdPnlViewConfig;

  @FXML
  private GLabel lblCmmiPractices;

  @FXML
  private GLabel lblCategory;

  @FXML
  private GTextField txtCategory;

  @FXML
  private GLabel lblTags;

  @FXML
  private GTextField txtTags;

  @FXML
  private GTextField txtCmmiPractices;

  /*------------------------------------------------------*/
  // ==> Controls not defined in FXML file
  /*------------------------------------------------------*/

  /**
   * The Constructor.
   */
  public AConfigurationPanelFxml() {
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

    UtilControl.applyBundleConfigToLabeledControl(PROPERTIES_PREFIX + "title", ttlPnlViewConfig);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "category", lblCategory);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "tags", lblTags);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "cmmi.practices", lblCmmiPractices);

  }

}

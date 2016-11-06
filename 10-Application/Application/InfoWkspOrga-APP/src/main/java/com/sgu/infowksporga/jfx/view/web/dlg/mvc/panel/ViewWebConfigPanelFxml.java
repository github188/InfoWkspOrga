package com.sgu.infowksporga.jfx.view.web.dlg.mvc.panel;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.GLabel;
import com.sgu.core.framework.gui.jfx.control.GTextField;
import com.sgu.core.framework.gui.jfx.control.pane.GGridPane;
import com.sgu.core.framework.gui.jfx.util.UtilControl;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.jfx.view.adlg.config.mvc.panel.AConfigurationPanelFxml;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class ViewWebConfigPanelFxml.
 *
 * @see {ViewComponentAttributesGenerator}
 */
@Slf4j
@Setter
@Getter
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = {// Force /n
              @I18nProperty(key = ViewWebConfigPanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.TEXT, value = "Configuration"), // Force /n
              @I18nProperty(key = ViewWebConfigPanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.STYLE_CSS, value = "-fx-text-fill: #11468E"), // Force /n
              @I18nProperty(key = ViewWebConfigPanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.ICON, value = "/icons/configuration.gif"), // Force /n
              //----------
              @I18nProperty(key = ViewWebConfigPanelFxml.PROPERTIES_PREFIX + "url" + I18NConstant.TEXT, value = "URL :"), // Force /n
              @I18nProperty(key = ViewWebConfigPanelFxml.PROPERTIES_PREFIX + "url" + I18NConstant.TOOLTIP_TEXT, value = "URL à afficher dans la vue"), // Force /n
//----------
})
public class ViewWebConfigPanelFxml extends AConfigurationPanelFxml implements Initializable {

  /** The Constant ViewWebConfigPanelFxml.PROPERTIES_PREFIX. */
  public static final String PROPERTIES_PREFIX = "dialog.view.web.panel.configuration.";

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
  private GLabel lblUrl;

  @FXML
  private GTextField txtUrl;

  /*------------------------------------------------------*/
  // ==> Controls not defined in FXML file
  /*------------------------------------------------------*/

  /**
   * The Constructor.
   */
  public ViewWebConfigPanelFxml() {
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
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "url", lblUrl);

  }

  /** {@inheritDoc} */
  @Override
  public void applyDisplayModeCreate() {
    super.applyDisplayModeCreate();

  }

  /** {@inheritDoc} */
  @Override
  public void applyDisplayModeUpdate() {
    super.applyDisplayModeUpdate();

    // Get the position of the workspace in his parent

  }

}

package com.sgu.infowksporga.jfx.component;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.GCheckBox;
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
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class StylePanelFxml.
 * 
 * @see {ViewComponentAttributesGenerator}
 */
@Slf4j
@Setter
@Getter
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "textColor" + I18NConstant.TEXT, value = "Couleur du text :"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "textColor" + I18NConstant.TOOLTIP_TEXT, value = "Couleur du text"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "icon" + I18NConstant.TEXT, value = "Icône :"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "icon" + I18NConstant.TOOLTIP_TEXT, value = "Icône ajouté à côté du libellé"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "iconRenderer" + I18NConstant.TEXT, value = ""), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "iconRenderer" + I18NConstant.TOOLTIP_TEXT, value = "Affiche l'icône si celui-ci est retrouvé"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "textStyle" + I18NConstant.TEXT, value = "Style :"), // Force /n
              @I18nProperty(key = StylePanelFxml.PROPERTIES_PREFIX + "textStyle" + I18NConstant.TOOLTIP_TEXT, value = "Liste des styles à appliquer au libellé"), // Force /n
})
public class StylePanelFxml extends AGView<AGModel, AGController> implements Initializable {

  /** The Constant PROPERTIES_PREFIX. */
  public static final String PROPERTIES_PREFIX = "idendity.card.panel.";

  //---------------------------------
  //Style  Panel components 
  //---------------------------------

  @FXML
  private TitledPane ttlPnlStyle;

  @FXML
  private AnchorPane ancPnlStyle;

  @FXML
  private GGridPane grdPnlStyle;

  @FXML
  private GCheckBox chkBold;

  @FXML
  private GCheckBox chkItalic;

  @FXML
  private GCheckBox chkStrike;

  @FXML
  private GCheckBox chkUnderline;

  @FXML
  private GLabel lblTextColor;

  @FXML
  private GTextField txtStyleRenderer;

  @FXML
  private GLabel lblIcon;

  @FXML
  private GTextField txtIcon;

  @FXML
  private GLabel lblIconRenderer;

  @FXML
  private GLabel lblTextStyle;

  @FXML
  private ColorPicker cpkTextColor;

  /*------------------------------------------------------*/
  // ==> Controls not defined in FXML file
  /*------------------------------------------------------*/

  /**
   * The Constructor.
   */
  public StylePanelFxml() {
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

    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "textColor", lblTextColor);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "icon", lblIcon);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "iconRenderer", lblIconRenderer);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "textStyle", lblTextStyle);

  }

}

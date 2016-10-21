package com.sgu.infowksporga.jfx.component;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.GComboBox;
import com.sgu.core.framework.gui.jfx.control.GLabel;
import com.sgu.core.framework.gui.jfx.control.GTextField;
import com.sgu.core.framework.gui.jfx.control.pane.GGridPane;
import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.core.framework.gui.jfx.screen.AGView;
import com.sgu.core.framework.gui.jfx.util.UtilControl;
import com.sgu.core.framework.i18n.util.I18NConstant;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.HTMLEditor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class IdentityCardPanelFxml.
 * 
 * @see {ViewComponentAttributesGenerator}
 */
@Slf4j
@Setter
@Getter

@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "id" + I18NConstant.TEXT, value = "Id :"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "id" + I18NConstant.TOOLTIP_TEXT, value = "The database unique identifier"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "name" + I18NConstant.TEXT, value = "Name :"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "name" + I18NConstant.TOOLTIP_TEXT, value = "The name user give to this element"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "owner" + I18NConstant.TEXT, value = "Owner :"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "owner" + I18NConstant.TOOLTIP_TEXT, value = "The owner of this element"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "description" + I18NConstant.TEXT, value = "Description :"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "description" + I18NConstant.TOOLTIP_TEXT, value = "Describe the utility of this element"), // Force /n
})
public class IdentityCardPanelFxml extends AGView<AGModel, AGController> implements Initializable {

  /** The Constant PROPERTIES_PREFIX. */
  public static final String PROPERTIES_PREFIX = "idendity.card.panel.";

  //---------------------------------
  //Identity Card Panel components 
  //---------------------------------

  @FXML
  private TitledPane ttlPnlIdentityCard;

  @FXML
  private AnchorPane ancPnlIdentityCard;

  @FXML
  private GGridPane grdPnlIdentityCard;

  @FXML
  private GLabel lblId;

  @FXML
  private GLabel lblName;

  @FXML
  private GTextField txtName;

  @FXML
  private GLabel lblOwner;

  @FXML
  private GLabel lblDescription;

  @FXML
  private GLabel lblIdValue;

  @FXML
  private GComboBox cbbOwner;

  @FXML
  private HTMLEditor htmlEdtDescription;

  /*------------------------------------------------------*/
  // ==> Controls not defined in FXML file
  /*------------------------------------------------------*/

  /**
   * The Constructor.
   */
  public IdentityCardPanelFxml() {
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

    Platform.runLater(() -> {
      UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "id", lblId);
      UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "name", lblName);
      UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "owner", lblOwner);
      UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "description", lblDescription);
    });

  }

}

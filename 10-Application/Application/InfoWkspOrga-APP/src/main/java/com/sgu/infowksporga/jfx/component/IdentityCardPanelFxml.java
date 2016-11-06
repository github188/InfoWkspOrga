package com.sgu.infowksporga.jfx.component;

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
import com.sgu.core.framework.gui.jfx.screen.AGScreen;
import com.sgu.core.framework.gui.jfx.screen.AGView;
import com.sgu.core.framework.gui.jfx.util.UtilControl;
import com.sgu.core.framework.i18n.util.I18NConstant;

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
properties = { // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.TEXT, value = "Carte d'identification"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.STYLE_CSS, value = "-fx-text-fill: #11468E"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.ICON, value = "/icons/object-card.png"), // Force /n
              //----------
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "id" + I18NConstant.TEXT, value = "Id :"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "id" + I18NConstant.TOOLTIP_TEXT, value = "The database unique identifier"), // Force /n
              //----------
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "name" + I18NConstant.TEXT, value = "Name :"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "name" + I18NConstant.TOOLTIP_TEXT, value = "The name user give to this element"), // Force /n

              //----------
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "description" + I18NConstant.TEXT, value = "Description :"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "description" + I18NConstant.TOOLTIP_TEXT, value = "Describe the utility of this element"), // Force /n
              //----------
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "category" + I18NConstant.TEXT, value = "Category :"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "category" + I18NConstant.TOOLTIP_TEXT, value = "???"), // Force /n
              //----------
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "tags" + I18NConstant.TEXT, value = "Etiquettes :"), // Force /n
              @I18nProperty(key = IdentityCardPanelFxml.PROPERTIES_PREFIX + "tags" + I18NConstant.TOOLTIP_TEXT,
              value = "Permet de définir des signets pemettant de faciliter le regroupement d'informations lors des recherches "), // Force /n
})
public class IdentityCardPanelFxml extends AGView<AGScreen, AGModel, AGController> implements Initializable {

  /** The Constant PROPERTIES_PREFIX. */
  public static final String PROPERTIES_PREFIX = "panel.idendity.card.";

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
  private GLabel lblDescription;

  @FXML
  private GLabel lblIdValue;

  @FXML
  private GLabel lblCategory;

  @FXML
  private GTextField txtCategory;

  @FXML
  private GLabel lblTags;

  @FXML
  private GTextField txtTags;

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

    UtilControl.applyBundleConfigToLabeledControl(PROPERTIES_PREFIX + "title", ttlPnlIdentityCard);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "id", lblId);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "name", lblName);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "description", lblDescription);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "category", lblCategory);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "tags", lblTags);

  }

}

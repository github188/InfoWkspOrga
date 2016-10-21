package com.sgu.infowksporga.jfx.workspace.dlg.mvc.panel;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.GCheckBox;
import com.sgu.core.framework.gui.jfx.control.GComboBox;
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
 * The Class ConfigurationPanelFxml.
 * 
 * @see {ViewComponentAttributesGenerator}
 */
@Slf4j
@Setter
@Getter
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "baseFolder" + I18NConstant.TEXT, value = "Base des dossiers :"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "baseFolder" + I18NConstant.TOOLTIP_TEXT, value = "Utilisées par les vues de type Dossier"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "category" + I18NConstant.TEXT, value = "Category :"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "category" + I18NConstant.TOOLTIP_TEXT, value = "???"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "tags" + I18NConstant.TEXT, value = "Etiquettes :"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "tags" + I18NConstant.TOOLTIP_TEXT,
              value = "Permet de définir des signets pemettant de faciliter le regroupement d'informations lors des recherches "), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "cbbPartage" + I18NConstant.TEXT, value = "Partage :"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "cbbPartage" + I18NConstant.TOOLTIP_TEXT,
              value = "Permet de déterminer la visibilité du Workspace"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "customer" + I18NConstant.TEXT, value = "Client :"), // Force /n
              @I18nProperty(key = ConfigurationPanelFxml.PROPERTIES_PREFIX + "customer" + I18NConstant.TOOLTIP_TEXT,
              value = "Le client pour qui ont réalise ce Workpsace (Gestion de la facturation, ...)"), // Force /n
})
public class ConfigurationPanelFxml extends AGView<AGModel, AGController> implements Initializable {

  /** The Constant ConfigurationPanelFxml.PROPERTIES_PREFIX. */
  public static final String PROPERTIES_PREFIX = "workspace.dialog.configuration.panel.";

  //---------------------------------
  //Configuration Panel components 
  //---------------------------------

  @FXML
  private TitledPane ttlPnlWorkspaceConfig;

  @FXML
  private AnchorPane ancPnlWorkspaceConfig;

  @FXML
  private GGridPane grdPnlWorkspaceConfig;

  @FXML
  private GCheckBox chkEnable;

  @FXML
  private GCheckBox chkChildrenAllowed;

  @FXML
  private GLabel lblBaseFolder;

  @FXML
  private GTextField txtBaseFolder;

  @FXML
  private GLabel lblCategory;

  @FXML
  private GTextField txtCategory;

  @FXML
  private GLabel lblTags;

  @FXML
  private GTextField txtTags;

  @FXML
  private GLabel lblCbbPartage;

  @FXML
  private GComboBox cbbPartage;

  @FXML
  private GLabel lblCustomer;

  @FXML
  private GTextField txtCustomer;

  /*------------------------------------------------------*/
  // ==> Controls not defined in FXML file
  /*------------------------------------------------------*/

  /**
   * The Constructor.
   */
  public ConfigurationPanelFxml() {
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

    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "baseFolder", lblBaseFolder);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "category", lblCategory);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "tags", lblTags);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "cbbPartage", lblCbbPartage);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "customer", lblCustomer);

  }

}

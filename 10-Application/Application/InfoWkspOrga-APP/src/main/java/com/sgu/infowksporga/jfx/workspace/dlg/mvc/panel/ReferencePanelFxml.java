package com.sgu.infowksporga.jfx.workspace.dlg.mvc.panel;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.GComboBox;
import com.sgu.core.framework.gui.jfx.control.GLabel;
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
 * The Class ReferencePanelFxml.
 * 
 * @see {ViewComponentAttributesGenerator}
 */
@Slf4j
@Setter
@Getter
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "parent" + I18NConstant.TEXT, value = "Parent :"), // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "parent" + I18NConstant.TOOLTIP_TEXT,
              value = "Permet de définir le workspace parent (utile pour la structuration des workspaces)"), // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "master" + I18NConstant.TEXT, value = "Master :"), // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "master" + I18NConstant.TOOLTIP_TEXT,
              value = "Permet de définir le workspace Maître. En général le Layout et les vues sont communes (Utile pour avoir des vues identiques entre projets: Revues CP, DP, RQO,...)"), // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "type" + I18NConstant.TEXT, value = "Type :"), // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "type" + I18NConstant.TOOLTIP_TEXT, value = "Type de Workspace"), // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "project" + I18NConstant.TEXT, value = "Projet :"), // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "project" + I18NConstant.TOOLTIP_TEXT, value = "Projet lié à ce Workspace"), // Force /n
})
public class ReferencePanelFxml extends AGView<AGModel, AGController> implements Initializable {

  /** The Constant PROPERTIES_PREFIX. */
  public static final String PROPERTIES_PREFIX = "workspace.dialog.reference.panel.";

  //---------------------------------
  //Reference Panel components 
  //---------------------------------

  @FXML
  private TitledPane ttlPnlReferences;

  @FXML
  private AnchorPane achPnlReferences;

  @FXML
  private GGridPane grdPnlReferences;

  @FXML
  private GLabel lblParent;

  @FXML
  private GLabel lblMaster;

  @FXML
  private GLabel lblType;

  @FXML
  private GComboBox cbbMaster;

  @FXML
  private GLabel lblProject;

  @FXML
  private GComboBox cbbParent;

  @FXML
  private GComboBox cbbType;

  @FXML
  private GComboBox cbbProject;

  /*------------------------------------------------------*/
  // ==> Controls not defined in FXML file
  /*------------------------------------------------------*/

  /**
   * The Constructor.
   */
  public ReferencePanelFxml() {
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

    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "parent", lblParent);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "master", lblMaster);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "type", lblType);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "project", lblProject);

  }

}

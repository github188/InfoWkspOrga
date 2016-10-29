package com.sgu.infowksporga.jfx.workspace.dlg.mvc.panel;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.GComboBox;
import com.sgu.core.framework.gui.jfx.control.GLabel;
import com.sgu.core.framework.gui.jfx.control.GTextField;
import com.sgu.core.framework.gui.jfx.control.list.AbstractItemVo;
import com.sgu.core.framework.gui.jfx.control.list.DefaultListCellFactory;
import com.sgu.core.framework.gui.jfx.control.pane.GGridPane;
import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.core.framework.gui.jfx.screen.AGView;
import com.sgu.core.framework.gui.jfx.util.UtilControl;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTreeItem;
import com.sgu.infowksporga.jfx.project.CbbProjectItemVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.workspace.dlg.mvc.panel.cbb.CbbWorkspaceItemVo;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TitledPane;
import javafx.scene.control.TreeItem;
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
properties = { // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.TEXT, value = "Références"), // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.STYLE_CSS, value = "-fx-text-fill: #11468E"), // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "title" + I18NConstant.ICON, value = "/icons/link.gif"), // Force /n
              //----------
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "parent" + I18NConstant.TEXT, value = "Parent :"), // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "parent" + I18NConstant.TOOLTIP_TEXT,
              value = "Permet de définir le workspace parent (utile pour la structuration des workspaces)"), // Force /n
              //----------
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "master" + I18NConstant.TEXT, value = "Master :"), // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "master" + I18NConstant.TOOLTIP_TEXT,
              value = "Permet de définir le workspace Maître. En général le Layout et les vues sont communes (Utile pour avoir des vues identiques entre projets: Revues CP, DP, RQO,...)"), // Force /n
              //----------
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "customer" + I18NConstant.TEXT, value = "Client :"), // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "customer" + I18NConstant.TOOLTIP_TEXT,
              value = "Le client pour qui ont réalise ce Workpsace (Gestion de la facturation, ...)"), // Force /n
              //----------
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "project" + I18NConstant.TEXT, value = "Projet :"), // Force /n
              @I18nProperty(key = ReferencePanelFxml.PROPERTIES_PREFIX + "project" + I18NConstant.TOOLTIP_TEXT, value = "Projet lié à ce Workspace"), // Force /n

})
public class ReferencePanelFxml extends AGView<AGModel, AGController> implements Initializable {

  /** The Constant PROPERTIES_PREFIX. */
  public static final String PROPERTIES_PREFIX = "dialog.workspace.panel.reference.";

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
  private GComboBox<CbbWorkspaceItemVo> cbbMaster;

  @FXML
  private GLabel lblProject;

  @FXML
  private GComboBox<CbbWorkspaceItemVo> cbbParent;

  @FXML
  private GComboBox<CbbProjectItemVo> cbbProject;

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
  public ReferencePanelFxml() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createUI() {
    super.createUI();

    cbbMaster.setCellFactory(c -> new DefaultListCellFactory<CbbWorkspaceItemVo>());
    // The button cell is used to render what is shown in the ComboBox 'button' area.
    cbbMaster.setButtonCell(new DefaultListCellFactory<CbbWorkspaceItemVo>());

    cbbParent.setCellFactory(c -> new DefaultListCellFactory<CbbWorkspaceItemVo>());
    // The button cell is used to render what is shown in the ComboBox 'button' area.
    cbbParent.setButtonCell(new DefaultListCellFactory<CbbWorkspaceItemVo>());

  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();

    // Fill the combo box with ---->ALL<---- Workspaces contained in perspective tree
    cbbParent.getItems().clear();
    final PerspectiveTreeItem root = (PerspectiveTreeItem) GUISessionProxy.getPerspectiveScreen().getView().getTreeWorkspaces().getRoot();
    cbbParent.getItems().add(new CbbWorkspaceItemVo(null)); // null correspond to the parent of the root node ;-)
    fillCbbParentWithAllTreeItemWorkspaces(cbbParent, root);

    Platform.runLater(() -> {
      cbbParent.getSelectionModel().select(0);
    });
  }

  /**
   * Fill cbb parent with all tree item workspaces.
   *
   * @param cbb the cbb
   * @param parent the parent
   */
  private void fillCbbParentWithAllTreeItemWorkspaces(final GComboBox<CbbWorkspaceItemVo> cbb, final PerspectiveTreeItem parent) {
    cbb.getItems().add(new CbbWorkspaceItemVo(parent.getWorkspace()));

    final List<TreeItem<AbstractItemVo>> children = parent.getChildren();
    for (final TreeItem<AbstractItemVo> child : children) {
      final PerspectiveTreeItem pti = (PerspectiveTreeItem) child;
      fillCbbParentWithAllTreeItemWorkspaces(cbb, pti);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void initialize(final URL location, final ResourceBundle resources) {
    super.initialize(location, resources);

    UtilControl.applyBundleConfigToLabeledControl(PROPERTIES_PREFIX + "title", ttlPnlReferences);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "parent", lblParent);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "master", lblMaster);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "project", lblProject);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "customer", lblCustomer);

  }

  /** {@inheritDoc} */
  @Override
  public void applyDisplayModeCreate() {
    super.applyDisplayModeCreate();

    // In creation all current node could be is parent
    applyCommonInitialization();
  }

  /** {@inheritDoc} */
  @Override
  public void applyDisplayModeUpdate() {
    super.applyDisplayModeUpdate();

    applyCommonInitialization();

    // In update mode remove himself because it couldn't be is parent
    final PerspectiveTreeItem selected = (PerspectiveTreeItem) GUISessionProxy.getPerspectiveScreen().getView().getTreeWorkspaces().getSelectionModel().getSelectedItem();
    cbbParent.getItems().clear();
    cbbParent.getItems().add(new CbbWorkspaceItemVo(selected.getWorkspace()));
  }

  /**
   * Apply common initialization.
   */
  private void applyCommonInitialization() {

    final PerspectiveTreeItem root = (PerspectiveTreeItem) GUISessionProxy.getPerspectiveScreen().getView().getTreeWorkspaces().getRoot();
    cbbParent.getItems().clear();
    cbbParent.getItems().add(new CbbWorkspaceItemVo(null));

    final Workspace workspace = GUISessionProxy.getCurrentWorkspace().getWorkspace();
    // The parent root node can't me moved by update, it must be = null
    if (root.getWorkspace().getId().equals(workspace.getId())) {
      cbbParent.getItems().clear();
      cbbParent.getItems().add(new CbbWorkspaceItemVo(null));
    }
    else {
      // Remove the current workspace because it can't be parent of himself
      cbbParent.getItems().remove(new CbbWorkspaceItemVo(workspace));
    }
  }
}

package com.sgu.infowksporga.jfx.perspective.mvc;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.GButton;
import com.sgu.core.framework.gui.jfx.control.GCheckBox;
import com.sgu.core.framework.gui.jfx.control.GComboBox;
import com.sgu.core.framework.gui.jfx.control.GLabel;
import com.sgu.core.framework.gui.jfx.control.list.DefaultListCellFactory;
import com.sgu.core.framework.gui.jfx.control.list.DefaultTreeCellFactory;
import com.sgu.core.framework.gui.jfx.control.pane.GGridPane;
import com.sgu.core.framework.gui.jfx.control.tree.GTreeView;
import com.sgu.core.framework.gui.jfx.screen.AGView;
import com.sgu.core.framework.gui.jfx.util.UtilControl;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.infowksporga.jfx.perspective.cbb.CbbPerspectiveItemVo;
import com.sgu.infowksporga.jfx.perspective.tree.vo.WorkspaceItemVo;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class PerspectiveViewFxml.
 */
@Slf4j
@Setter
@Getter

@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = {// Force /n
              @I18nProperty(key = PerspectivePanelViewFxml.PROPERTIES_PREFIX + "leaf" + I18NConstant.ICON, value = "/icons/leaf.gif"), // Force /n
              @I18nProperty(key = PerspectivePanelViewFxml.PROPERTIES_PREFIX + "leaf" + I18NConstant.TOOLTIP_TEXT,
              value = "si coché, on filtre uniquement sur les fichiers, si non coché on filtre sur les dossiers et fichiers"), // Force /n
              //----------
              @I18nProperty(key = PerspectivePanelViewFxml.PROPERTIES_PREFIX + "chk.filter" + I18NConstant.TOOLTIP_TEXT,
              value = "si coché, on filtre uniquement sur les fichiers, si non coché on filtre sur les dossiers et fichiers"), // Force /n
})
public class PerspectivePanelViewFxml extends AGView<PerspectivePanelScreen, PerspectivePanelModel, PerspectivePanelController> implements Initializable {

  /** The cb show hide item notifications. */
  public static final String CB_SHOW_HIDE_ITEM_NOTIFICATIONS = "Notifications";

  /** The cb show hide item menu. */
  public static final String CB_SHOW_HIDE_ITEM_MENU = "Menu";

  /** The Constant ViewWebConfigPanelFxml.PROPERTIES_PREFIX. */
  public static final String PROPERTIES_PREFIX = "perspective.panel.";

  /** The brd pnl perspective. */
  @FXML
  private GGridPane grdPnlPerspective;

  /** The lbl filter. */
  @FXML
  private GLabel lblFilter;

  /** The lbl leaf. */
  @FXML
  private GLabel lblLeaf;

  /** The cbb filter on workspaces. */
  @FXML
  private GComboBox cbbFilterOnWorkspaces;

  /** The btn collapse workspace tree. */
  @FXML
  private GButton btnCollapseWorkspaceTree;

  /** The btn expand workspace tree. */
  @FXML
  private GButton btnExpandWorkspaceTree;

  /** The tree workspaces. */
  @FXML
  private GTreeView treeWorkspaces;

  /** The lbl select. */
  @FXML
  private GLabel lblSelect;

  /** The cbb perspective. */
  @FXML
  private GComboBox cbbPerspective;

  /** The btn refresh perspective. */
  @FXML
  private GButton btnRefreshPerspective;

  /** The btn clear workspace filter. */
  @FXML
  private GButton btnClearWorkspaceFilter;

  /** The chkb workspace filter only on leaf. */
  @FXML
  private GCheckBox chkbWorkspaceFilterOnlyOnLeaf;

  @FXML
  private Button btnSaveWorkspace;

  @FXML
  private Button btnCreateWorkspace;

  @FXML
  private Button btnEditWorkspace;

  /*------------------------------------------------------*/
  // ==> Controls not defined in FXML file
  /*------------------------------------------------------*/

  /**
   * The Constructor.
   */
  public PerspectivePanelViewFxml() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createUI() {
    super.createUI();

    treeWorkspaces.setCellFactory(p -> new DefaultTreeCellFactory<WorkspaceItemVo>());

    cbbPerspective.setCellFactory(c -> new DefaultListCellFactory<CbbPerspectiveItemVo>());
    // The button cell is used to render what is shown in the ComboBox 'button' area.
    cbbPerspective.setButtonCell(new DefaultListCellFactory<CbbPerspectiveItemVo>());

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

    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "leaf", lblLeaf);
    UtilControl.applyBundleConfigToLabel(PROPERTIES_PREFIX + "chk.filter", chkbWorkspaceFilterOnlyOnLeaf);

  }

}

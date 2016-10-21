package com.sgu.infowksporga.jfx.perspective.mvc;

import java.net.URL;
import java.util.ResourceBundle;

import com.sgu.core.framework.gui.jfx.control.GButton;
import com.sgu.core.framework.gui.jfx.control.GCheckBox;
import com.sgu.core.framework.gui.jfx.control.GComboBox;
import com.sgu.core.framework.gui.jfx.control.GLabel;
import com.sgu.core.framework.gui.jfx.control.pane.GGridPane;
import com.sgu.core.framework.gui.jfx.control.tree.GTreeView;
import com.sgu.core.framework.gui.jfx.screen.AGView;
import com.sgu.infowksporga.jfx.perspective.tree.TreeCellFactory;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class PerspectiveViewFxml.
 */
@Slf4j
@Setter
@Getter
public class PerspectivePanelViewFxml extends AGView<PerspectivePanelModel, PerspectivePanelController> implements Initializable {

  /** The cb show hide item notifications. */
  public static final String CB_SHOW_HIDE_ITEM_NOTIFICATIONS = "Notifications";

  /** The cb show hide item menu. */
  public static final String CB_SHOW_HIDE_ITEM_MENU = "Menu";

  /** The brd pnl perspective. */
  @FXML
  private GGridPane grdPnlPerspective;

  /** The lbl filter. */
  @FXML
  private GLabel lblFilter;

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

    treeWorkspaces.setCellFactory(p -> new TreeCellFactory());

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
  }

}

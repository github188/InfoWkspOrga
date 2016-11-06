package com.sgu.infowksporga.jfx.perspective.tree.vo;

import com.google.common.base.Objects;
import com.sgu.core.framework.gui.jfx.control.list.AbstractItemVo;
import com.sgu.core.framework.gui.jfx.util.UtilControl;
import com.sgu.core.framework.gui.jfx.util.UtilGUI;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.perspective.mvc.PerspectivePanelScreen;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilView;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class WorkspaceItemVo.
 */
@Getter
@Setter
public class WorkspaceItemVo extends AbstractItemVo {

  /** The workspace. */
  private Workspace workspace;

  /**
   * The Constructor.
   *
   * @param treeNodeIdentifier the tree node identifier
   * @param workspace the workspace
   */
  public WorkspaceItemVo(final Workspace workspace) {
    this.workspace = workspace;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return getText();
  }

  /** {@inheritDoc} */
  @Override
  public String getText() {

    return I18nHelperApp.getMessage(workspace.getName());
  }

  /** {@inheritDoc} */
  @Override
  public Tooltip getTooltip() {
    Tooltip tooltip = null;
    String text = null;

    final PerspectivePanelScreen perspectiveScreen = GUISessionProxy.getApplication().getApplicationScreen().getPerspectiveScreen();
    final String cbbFilterItem = (String) perspectiveScreen.view().getCbbFilterOnWorkspaces().getSelectionModel().getSelectedItem();

    if (UtilString.isNotBlank(cbbFilterItem)) {
      text = I18nHelperApp.getMessage("perspective.filter.apply.tooltip");
    }
    else {
      final String description = getItemDescription();
      if (UtilString.isNotBlank(description)) {
        text = description;
      }
    }

    if (UtilString.isNotBlank(cbbFilterItem)) {
      tooltip = new Tooltip();
      if (text.contains("<html>")) {
        UtilControl.applyHtmlTextToTooltip(tooltip, text);
      }
      else {
        tooltip.setText(text);
      }
    }

    return tooltip;
  }

  /** {@inheritDoc} */
  @Override
  public ImageView getIcon() {
    final PerspectivePanelScreen perspectiveScreen = GUISessionProxy.getApplication().getApplicationScreen().getPerspectiveScreen();
    final String cbbFilterItem = (String) perspectiveScreen.view().getCbbFilterOnWorkspaces().getSelectionModel().getSelectedItem();

    if (UtilString.isNotBlank(cbbFilterItem)) {
      return UtilGUI.getImageView("/icons/filter.png");
    }
    /*
     * else if (treeItem.getChildCount() == 0) {
     * return UtilGUI.getImageIconFromClasspath("/icons/error-loading.png");
     * }
     */

    if (workspace.getIcon() != null) {
      return UtilGUI.getImageView(workspace.getIcon());
    }

    return null;
  }

  /**
   * Gets the node description.
   *
   * @param label the label
   * @param item the user object
   * @return the node description
   */
  private String getItemDescription() {

    if (UtilString.isNotBlank(workspace.getDescription())) {
      final String description = workspace.getDescription().replace("${User}", GUISessionProxy.getGuiSession().getCurrentUser().getLogin());
      return I18nHelperApp.getMessage(description);
    }

    return I18nHelperApp.getNullMessage(workspace.getName() + I18NConstant.TOOLTIP_TEXT);
  }

  /**
   * Gets the title.
   *
   * @param workspace the workspace
   * @return the title
   */
  @Override
  public String getStyle() {
    return UtilView.buildStyle(workspace);
  }

  /** {@inheritDoc} */
  @Override
  public Object getEncapsultatedObject() {
    return workspace;
  }

  @Override
  public boolean equals(final Object object) {
    if (object instanceof WorkspaceItemVo) {
      final WorkspaceItemVo that = (WorkspaceItemVo) object;
      return Objects.equal(this.getWorkspace().getId(), that.getWorkspace().getId());
    }
    return false;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), workspace);
  }

}

package com.sgu.infowksporga.jfx.workspace.dlg.mvc.panel.cbb;

import com.sgu.core.framework.gui.jfx.control.list.AbstractItemVo;
import com.sgu.core.framework.gui.jfx.util.UtilGUI;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class CbbOwnerItem.
 * Used to list all available Users
 */
@Getter
@Setter
@Slf4j
public class CbbWorkspaceItemVo extends AbstractItemVo {

  /** The workspace. */
  private Workspace workspace;

  /**
   * The Constructor.
   *
   * @param treeNodeIdentifier the tree node identifier
   * @param workspace the workspace
   */
  public CbbWorkspaceItemVo(final Workspace workspace) {
    this.workspace = workspace;
  }

  /** {@inheritDoc} */
  @Override
  public String getText() {
    if (workspace != null) {
      return I18nHelperApp.getMessage(workspace.getName());
    }

    return "";
  }

  /** {@inheritDoc} */
  @Override
  public Tooltip getTooltip() {
    if (workspace != null && workspace.getDescription() != null) {
      return new Tooltip(I18nHelperApp.getMessage(workspace.getDescription()));
    }
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public ImageView getIcon() {
    if (workspace != null && workspace.getIcon() != null) {
      return new ImageView(UtilGUI.getImage(workspace.getIcon()));
    }

    return null;
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
  public Object getEncapsultatedObject() {
    return workspace;
  }

  /** {@inheritDoc} */
  @Override
  public String getNodeIdentifier() {
    if (workspace != null) {  // If wksp is null check if it's not a Root node Parent 
      return workspace.getId();
    }
    return "";
  }

}

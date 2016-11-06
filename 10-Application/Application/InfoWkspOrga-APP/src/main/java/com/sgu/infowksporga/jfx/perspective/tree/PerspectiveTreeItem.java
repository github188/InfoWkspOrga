package com.sgu.infowksporga.jfx.perspective.tree;

import org.springframework.data.domain.Persistable;

import com.google.common.base.Objects;
import com.sgu.core.framework.gui.jfx.control.list.AbstractItemVo;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.jfx.perspective.tree.vo.WorkspaceItemVo;

import javafx.scene.control.TreeItem;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class PerspectiveTreeItem.
 */
@Getter
@Setter
public class PerspectiveTreeItem extends TreeItem<AbstractItemVo> implements Comparable<PerspectiveTreeItem> {

  /**
   * The Constructor.
   */
  public PerspectiveTreeItem() {

  }

  /**
   * The Constructor.
   *
   * @param treeNodeIdentifier the tree node identifier
   * @param userObject the user object
   */
  public PerspectiveTreeItem(final Workspace workspace) {
    super(null);
    setWorkspace(new WorkspaceItemVo(workspace));
  }

  /**
   * {@inheritDoc}
   */
  protected Object buildNewItem() {
    return new PerspectiveTreeItem();
  }

  /**
   * Sets the workspace.
   *
   * @param workspace the workspace
   */
  public void setWorkspace(final Workspace workspace) {
    if (getWorkspaceItemVo() == null) {
      setValue(new WorkspaceItemVo(new Workspace()));
    }
    getWorkspaceItemVo().setWorkspace(workspace);

  }

  /**
   * Gets the workspace.
   *
   * @return the workspace
   */
  public Workspace getWorkspace() {
    return getWorkspaceItemVo().getWorkspace();
  }

  /**
   * Gets the workspace.
   *
   * @return the workspace
   */
  public WorkspaceItemVo getWorkspaceItemVo() {
    return (WorkspaceItemVo) getValue();
  }

  /**
   * Sets the workspace.
   *
   * @param workspace the workspace
   */
  public void setWorkspace(final WorkspaceItemVo workspaceItemVo) {
    setValue(workspaceItemVo);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int compareTo(final PerspectiveTreeItem o) {
    if (this.equals(o)) {
      return 0;
    }

    if (o != null) {
      final Persistable userObject = (Persistable) this.getValue();
      final Persistable oUserObject = (Persistable) o.getValue();

      final int result = ((PerspectiveTreeItem) userObject.getId()).compareTo((PerspectiveTreeItem) oUserObject.getId());
      return result;

    }

    return -1;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode());
  }

  @Override
  public boolean equals(final Object object) {
    if (object instanceof PerspectiveTreeItem) {
      final PerspectiveTreeItem that = (PerspectiveTreeItem) object;
      return Objects.equal(this.getWorkspace(), that.getWorkspace());
    }
    return false;
  }

}

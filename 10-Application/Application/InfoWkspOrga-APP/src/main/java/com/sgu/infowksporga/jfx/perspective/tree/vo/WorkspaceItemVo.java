package com.sgu.infowksporga.jfx.perspective.tree.vo;

import com.sgu.infowksporga.business.dto.WorkspaceDto;

import lombok.Getter;

/**
 * The Class WorkspaceItemVo.
 */
@Getter
public class WorkspaceItemVo extends AbstractItemVo {

  /** The workspace. */
  private WorkspaceDto workspaceDto;

  /**
   * The Constructor.
   *
   * @param treeNodeIdentifier the tree node identifier
   * @param workspace the workspace
   */
  public WorkspaceItemVo(final long treeNodeIdentifier, final WorkspaceDto workspaceDto) {
    this.treeNodeIdentifier = treeNodeIdentifier;
    this.workspaceDto = workspaceDto;
  }

  /** {@inheritDoc} */
  @Override
  public String getName() {
    return workspaceDto.getWorkspace().getName();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return getName();
  }

}

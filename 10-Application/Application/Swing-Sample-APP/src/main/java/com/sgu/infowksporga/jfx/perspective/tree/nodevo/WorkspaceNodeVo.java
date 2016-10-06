package com.sgu.infowksporga.jfx.perspective.tree.nodevo;

import com.sgu.infowksporga.business.entity.Workspace;

/**
 * The Class WorkspaceNodeVo.
 */
public class WorkspaceNodeVo extends AbstractNodeVo {

  /** The workspace. */
  private Workspace workspace;

  /**
   * The Constructor.
   *
   * @param treeNodeIdentifier the tree node identifier
   * @param workspace the workspace
   */
  public WorkspaceNodeVo(final long treeNodeIdentifier, final Workspace workspace) {
    this.treeNodeIdentifier = treeNodeIdentifier;
    this.workspace = workspace;
  }

  public String getName() {
    return workspace.getName();
  }

  public Workspace getWorkspace() {
    return workspace;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return workspace.getName();
  }

}

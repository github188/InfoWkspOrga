package com.sgu.infowksporga.jfx.perspective.tree.nodevo;

/**
 * Description : Perspective Cmmi Default Node Vo class<br>
 */
public class StringNodeVo extends AbstractNodeVo {

  /** The node name. */
  private String name;

  /**
   * The Constructor.
   *
   * @param treeNodeIdentifier the tree node identifier
   * @param workspace the workspace
   */
  public StringNodeVo(final long treeNodeIdentifier, final String name) {
    this.treeNodeIdentifier = treeNodeIdentifier;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return getName();
  }

}

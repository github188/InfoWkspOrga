package com.sgu.infowksporga.jfx.perspective.tree.vo;

/**
 * Description : Perspective Cmmi Default Node Vo class<br>
 */
public class StringItemVo extends AbstractItemVo {

  /** The node name. */
  private String name;

  /**
   * The Constructor.
   *
   * @param treeNodeIdentifier the tree node identifier
   * @param workspace the workspace
   */
  public StringItemVo(final long treeNodeIdentifier, final String name) {
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

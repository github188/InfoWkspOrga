package com.sgu.infowksporga.jfx.perspective.tree.vo;

import com.sgu.core.framework.util.Util;

/**
 * Description : Perspective Cmmi Default Node Vo class<br>
 */
public abstract class AbstractItemVo {

  /**
   * Define the node identifier
   * it is used to identify same node name in tree (used in equals methods of tree user object Vo)
   * In perspective tree the identifier correspond to the attached project
   */
  protected long treeNodeIdentifier;

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = (int) (prime * result + getTreeNodeIdentifier());
    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals(final Object obj) {
    if (obj != null) {
      final AbstractItemVo castedObj = (AbstractItemVo) obj;

      final boolean result = Util.isEqual(this.getTreeNodeIdentifier(), castedObj.getTreeNodeIdentifier());
      if (!result) {
        return false;
      }

      return result;

    }
    return false;
  }

  /**
   * Gets the name.
   *
   * @return the name
   */
  public abstract String getName();

  /**
   * @see #treeNodeIdentifier
   * @return the identifier : See field description
   */
  public long getTreeNodeIdentifier() {
    return treeNodeIdentifier;
  }

  /**
   * @see #treeNodeIdentifier
   * @param identifier : See field description
   */
  public void setTreeNodeIdentifier(final long identifier) {
    this.treeNodeIdentifier = identifier;
  }

}

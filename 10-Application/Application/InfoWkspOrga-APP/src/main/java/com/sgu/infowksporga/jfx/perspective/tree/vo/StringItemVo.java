package com.sgu.infowksporga.jfx.perspective.tree.vo;

import com.sgu.core.framework.gui.jfx.control.list.AbstractItemVo;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;

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
  public StringItemVo(final String name) {
    this.name = name;
  }

  @Override
  public String getText() {
    return name;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return getText();
  }

  @Override
  public Tooltip getTooltip() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ImageView getIcon() {
    // TODO Auto-generated method stub
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public Object getEncapsultatedObject() {
    return name;
  }

  /** {@inheritDoc} */
  @Override
  public String getNodeIdentifier() {
    return name;
  }

}

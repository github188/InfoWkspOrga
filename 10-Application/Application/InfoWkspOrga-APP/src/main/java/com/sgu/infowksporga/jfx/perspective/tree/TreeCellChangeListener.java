package com.sgu.infowksporga.jfx.perspective.tree;

import com.sgu.infowksporga.jfx.perspective.tree.vo.AbstractItemVo;
import com.sgu.infowksporga.jfx.perspective.tree.vo.WorkspaceItemVo;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TreeItem;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class TreeCellChangeListener.
 */
@Slf4j
public class TreeCellChangeListener implements ChangeListener<TreeItem<AbstractItemVo>> {

  /**
   * The Constructor.
   */
  public TreeCellChangeListener() {
  }

  /** {@inheritDoc} */
  @Override
  public void changed(final ObservableValue<? extends TreeItem<AbstractItemVo>> observable, final TreeItem<AbstractItemVo> oldValue,
  final TreeItem<AbstractItemVo> newValue) {

    final WorkspaceItemVo selectedItem = (WorkspaceItemVo) newValue.getValue();
    log.debug(selectedItem.toString());
  }

}

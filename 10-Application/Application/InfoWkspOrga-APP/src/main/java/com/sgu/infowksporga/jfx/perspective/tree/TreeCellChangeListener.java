package com.sgu.infowksporga.jfx.perspective.tree;

import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.main.mvc.ApplicationScreen;
import com.sgu.infowksporga.jfx.perspective.tree.vo.AbstractItemVo;
import com.sgu.infowksporga.jfx.perspective.tree.vo.WorkspaceItemVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.y_service.remote.workspace.ShowWorkspaceFacade;

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

    final ShowWorkspaceFacade facade = SpringBeanHelper.getImplementationByInterface(ShowWorkspaceFacade.class);
    final ApplicationScreen applicationScreen = GUISessionProxy.getApplication().getApplicationScreen();
    GUISessionProxy.getGuiSession().getServiceDelegate().execute(facade, applicationScreen);
  }

}

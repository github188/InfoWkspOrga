package com.sgu.infowksporga.jfx.main.ui;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.infowksporga.jfx.main.action.ExitAction;
import com.sgu.infowksporga.jfx.main.action.PerspectiveShowHideAction;
import com.sgu.infowksporga.jfx.main.action.view.AddWebViewAction;
import com.sgu.infowksporga.jfx.main.action.workspace.SaveWorkspaceAction;

import javafx.collections.ListChangeListener;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The class <strong>ApplicationController</strong>.
 */

@Slf4j
@Setter
@Getter
public final class ApplicationController extends AGController<ApplicationModel, ApplicationViewFxml> {

  /**
   * The Constructor.
   */
  public ApplicationController() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createListeners() {
    super.createListeners();
    initShowHideMenuAndNotificationsListener();

    //---------------------------------------------------
    // initialize Actions
    // set empty String for toolbar buttons
    //---------------------------------------------------
    new ExitAction(view().getBtnExit());
    view().getBtnExit().setText("");

    new PerspectiveShowHideAction(view().getTgBtnPerspectiveVisible());
    view().getTgBtnPerspectiveVisible().setText("");

    /* Workspace Views addon */
    new AddWebViewAction(view().getBtnAddWebView());
    view().getBtnAddWebView().setText("");

    new SaveWorkspaceAction(view().getBtnSaveWorkspace());
    view().getBtnSaveWorkspace().setText("");

  }

  /**
   * Inits the show hide menu and notifications listener.
   */
  public void initShowHideMenuAndNotificationsListener() {
    // Listen to the relevant events (e.g. when the selected indices or selected items change).
    view().getCbShowHide().getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
      @Override
      public void onChanged(final ListChangeListener.Change<? extends String> change) {
        while (change.next()) {
          // check if it's a new checked item
          final List<? extends String> addedSubList = change.getAddedSubList();
          if (CollectionUtils.isNotEmpty(addedSubList)) {
            final String item = addedSubList.get(0);

            switch (item) {
              case ApplicationViewFxml.CB_SHOW_HIDE_ITEM_NOTIFICATIONS:
                view().getSpnApplication().getItems().add(0, view().getTtlPnlNotifications());
                view().getSpnApplication().setDividerPosition(0, 0.15);
                break;

              case ApplicationViewFxml.CB_SHOW_HIDE_ITEM_MENU:
                view().getVboxTop().getChildren().add(0, view().getPnlMenuBar());
                break;

              default:
                throw new IllegalArgumentException("Invalid Item: " + item);
            }
          }

          // check if it's a new checked item
          final List<? extends String> removedSubList = change.getRemoved();
          if (CollectionUtils.isNotEmpty(removedSubList)) {
            final String item = removedSubList.get(0);
            switch (item) {
              case ApplicationViewFxml.CB_SHOW_HIDE_ITEM_NOTIFICATIONS:
                view().getSpnApplication().getItems().remove(view().getTtlPnlNotifications());
                break;

              case ApplicationViewFxml.CB_SHOW_HIDE_ITEM_MENU:
                view().getVboxTop().getChildren().remove(view().getPnlMenuBar());
                break;

              default:
                throw new IllegalArgumentException("Invalid Item: " + item);
            }
          }
        }
      }
    });
  }

  /**
   * Creates the new workspace.
   */
  public void createNewEmptyWorkspace() {
    final GDockPane dockPane = new GDockPane();
    view().setDockPane(dockPane);

    view().getPnlWorkspace().setCenter(dockPane);
    view().getPnlWorkspace().getStyleClass().add("dock-node-border");

  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    super.bindComponentsWithPojo();
  }

}

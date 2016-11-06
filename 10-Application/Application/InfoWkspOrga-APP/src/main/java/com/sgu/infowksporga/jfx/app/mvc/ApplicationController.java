package com.sgu.infowksporga.jfx.app.mvc;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.dockfx.DockEvent;

import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.infowksporga.jfx.app.action.ExitAction;
import com.sgu.infowksporga.jfx.app.action.PerspectiveShowHideAction;
import com.sgu.infowksporga.jfx.app.action.view.AddWebViewAction;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.event.EventHandler;
import javafx.stage.Stage;
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
    initApplicationMaximizeListener();
    initShowHideMenuAndNotificationsListener();
    initShowNotificationPanelListener();

    //---------------------------------------------------
    // initialize Actions
    // set empty String for toolbar buttons
    //---------------------------------------------------
    new ExitAction(view().getBtnExit());
    view().getBtnExit().setText("");

    new PerspectiveShowHideAction(view().getTgBtnPerspectiveVisible());
    view().getTgBtnPerspectiveVisible().setText("");

    //------------------------------------------
    /* Workspace Views addon */
    //------------------------------------------
    new AddWebViewAction(view().getBtnAddWebView());
    view().getBtnAddWebView().setText("");

    //-------------------------------------------
    //* To update view selection in session
    view().getDockPane().addEventFilter(DockEvent.ANY, new EventHandler<DockEvent>() {
      @Override
      public void handle(DockEvent event) {
        if (event.getEventType() == DockEvent.DOCK_ENTER) {
          UtilGUIMessage.showInformationMessage(event.toString(), event.getTarget().toString());
        }
      }
    });

  }

  /**
   * Inits the show notification panel listener.
   */
  private void initShowNotificationPanelListener() {
    final Stage stage = GUISessionProxy.getApplication().getStage();

    GUISessionProxy.getApplication().getApplicationScreen().view().getTtlPnlNotifications().expandedProperty().addListener(new ChangeListener<Boolean>() {

      @Override
      public void changed(final ObservableValue<? extends Boolean> observable, final Boolean oldValue, final Boolean newValue) {
        if (newValue == true) {
          Platform.runLater(() -> {
            final double stageHeight = stage.getHeight();
            final double notificationHeightForce = GUISessionProxy.getApplication().getApplicationScreen().view().getSpnApplication().getPrefHeight();
            log.debug("notificationHeightForce = {}", notificationHeightForce);
            final double newSplitPaneNotifPos = notificationHeightForce / stageHeight;
            GUISessionProxy.getApplication().getApplicationScreen().view().getSpnApplication().setDividerPositions(newSplitPaneNotifPos);
            log.debug("newSplitPaneNotifPos = {}", newSplitPaneNotifPos);

          });
        }
      }
    });

  }

  /**
   * Inits the application maximize listener.
   */
  private void initApplicationMaximizeListener() {
    final Stage stage = GUISessionProxy.getApplication().getStage();

    stage.maximizedProperty().addListener(new ChangeListener<Boolean>() {

      @Override
      public void changed(final ObservableValue<? extends Boolean> observable, final Boolean oldValue, final Boolean newValue) {
        if (newValue == true) {
          Platform.runLater(() -> {
            final double stageWidth = stage.getWidth();
            final double wkspWidthForce = GUISessionProxy.getApplication().getApplicationScreen().view().getSpnWorkspace().getPrefWidth();
            log.debug("wkspWidthForce = {}", wkspWidthForce);
            final double newSplitPaneWkspPos = wkspWidthForce / stageWidth;
            GUISessionProxy.getApplication().getApplicationScreen().view().getSpnWorkspace().setDividerPositions(newSplitPaneWkspPos);
            log.debug("newSplitPaneWkspPos = {}", newSplitPaneWkspPos);

            final double stageHeight = stage.getHeight();
            final double notificationHeightForce = GUISessionProxy.getApplication().getApplicationScreen().view().getSpnApplication().getPrefHeight();
            log.debug("notificationHeightForce = {}", notificationHeightForce);
            final double newSplitPaneNotifPos = notificationHeightForce / stageHeight;
            GUISessionProxy.getApplication().getApplicationScreen().view().getSpnApplication().setDividerPositions(newSplitPaneNotifPos);
            log.debug("newSplitPaneNotifPos = {}", newSplitPaneNotifPos);
          });
        }
      }
    });
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
   * Display new empty workspace.
   */
  public void displayNewEmptyWorkspace() {
    GDockPane dockPane = view().getDockPane();
    if (view().getDockPane() != null) {
      dockPane = null;
    }

    dockPane = new GDockPane();

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

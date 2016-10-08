package com.sgu.infowksporga.jfx.main.ui;

import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.dockfx.DockPos;
import org.dockfx.demo.DockFX;

import com.sgu.core.framework.gui.jfx.control.GDockNode;
import com.sgu.core.framework.gui.jfx.control.GDockPane;
import com.sgu.core.framework.gui.jfx.control.GDockTabPane;
import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.infowksporga.jfx.main.Application;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import javafx.collections.ListChangeListener;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

  /**
   * Process exit application.
   */
  public void processExitApplication() {
    final Application application = (Application) GUISessionProxy.getGuiSession().getApplication();
    application.actionExitApplication();
  }

  /** {@inheritDoc} */
  @Override
  public void createListeners() {
    super.createListeners();

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
  public void createNewWorkspace() {
    final GDockPane dockPane = new GDockPane();

    // load an image to caption the dock nodes
    final Image dockImage = new Image(DockFX.class.getResource("docknode.png").toExternalForm());

    final GDockTabPane tabs_1 = new GDockTabPane();
    final GDockNode tabsDock_1 = new GDockNode(tabs_1, "Tabs Dock 01", new ImageView(dockImage));
    tabsDock_1.setPrefSize(300, 100);
    tabsDock_1.dock(dockPane, DockPos.CENTER);

    final GDockTabPane tabs_2 = new GDockTabPane();
    final GDockNode tabsDock_2 = new GDockNode(tabs_2, "Tabs Dock 02", new ImageView(dockImage));
    tabsDock_2.setPrefSize(300, 100);
    tabsDock_2.dock(dockPane, DockPos.RIGHT);

    final GDockTabPane tabs_3 = new GDockTabPane();
    final GDockNode tabsDock_3 = new GDockNode(tabs_3, "Tabs Dock 03", new ImageView(dockImage));
    tabsDock_3.setPrefSize(300, 100);
    tabsDock_3.dock(dockPane, DockPos.TOP);

    final GDockTabPane tabs_4 = new GDockTabPane();
    final GDockNode tabsDock_4 = new GDockNode(tabs_4, "Tabs Dock 04", new ImageView(dockImage));
    tabsDock_4.setPrefSize(300, 100);
    tabsDock_4.dock(dockPane, DockPos.BOTTOM);

    view().getPnlWorkspace().setCenter(dockPane);
    view().getPnlWorkspace().getStyleClass().add("dock-node-border");

  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    super.bindComponentsWithPojo();
  }

}

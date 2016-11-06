package com.sgu.infowksporga.jfx.view.ui;

import org.dockfx.DockTitleBar;

import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewController;
import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewScreen;
import com.sgu.core.framework.gui.jfx.util.UtilStyle;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.jfx.util.UtilView;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The class <strong>AAppViewController</strong>.
 */

@Slf4j
@Setter
@Getter
public abstract class AAppViewController<M extends AAppViewModel, V extends AAppViewFxml> extends ADockableViewController<M, V> {

  /**
   * The Constructor.
   */
  public AAppViewController() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public void createListeners() {
    super.createListeners();
  }

  /**
   * Apply model on view.
   */
  @Override
  public void bindComponentsWithPojo() {
    final ADockableViewScreen screen = (ADockableViewScreen) view().screen();

    if (screen.getDockNode() != null) {
      final DockTitleBar dockTitleBar = screen.getDockNode().getDockTitleBar();
      final Label dockTitleBarLabel = dockTitleBar.getLabel();

      dockTitleBarLabel.textProperty().unbind();
      dockTitleBarLabel.graphicProperty().unbind();

      dockTitleBarLabel.setText(model().getTitleTranslated());
      dockTitleBarLabel.setGraphic(model().getIcon());

      if (UtilString.isNotBlank(model().getViewEntity().getDescription())) {
        dockTitleBarLabel.setTooltip(new Tooltip(model().getViewEntity().getDescription()));
      }
      else {
        dockTitleBarLabel.setTooltip(null);
      }
      dockTitleBarLabel.setTextFill(UtilStyle.convertStringColorToFxColor(model().getViewEntity().getColor()));

      dockTitleBarLabel.setStyle(UtilView.buildStyle(model().getViewEntity()));

      final Background background = new Background(new BackgroundFill(UtilStyle.convertStringColorToFxColor(model().getViewEntity().getBgColor()), null, null));
      dockTitleBar.setBackground(background);
    }

  }

}

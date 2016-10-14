package com.sgu.infowksporga.jfx.perspective.cb;

import com.google.common.base.Objects;
import com.sgu.core.framework.gui.jfx.control.GLabel;
import com.sgu.core.framework.gui.jfx.util.UtilGUI;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.perspective.UtilPerspective;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class PerspectiveComboVo.
 * Used to list all available perspectives
 */
@Getter
@Setter
@Slf4j
public class CbbPerspectiveItem extends GLabel {

  /** The perspective. */
  private Perspective perspective;

  /**
   * The Constructor.
   *
   * @param perspective the perspective
   */
  public CbbPerspectiveItem(final Perspective perspective) {
    super();
    this.perspective = perspective;
    initializeItem();
  }

  private void initializeItem() {

    this.setTooltip(null);

    this.setText(I18nHelperApp.getMessage(perspective.getName()));
    if (perspective.getIcon() == null) {
      this.setGraphic(new ImageView(UtilPerspective.DEFAULT_PERSPECTIVE_ICON));
    }
    else {
      this.setGraphic(new ImageView(UtilGUI.getImage(perspective.getIcon())));
    }

    if (perspective.getDescription() != null) {
      this.setTooltip(new Tooltip(I18nHelperApp.getMessage(perspective.getDescription())));
    }

  }

  @Override
  public int hashCode() {
    return Objects.hashCode(perspective);
  }

  @Override
  public boolean equals(final Object object) {
    if (object instanceof CbbPerspectiveItem) {
      final CbbPerspectiveItem that = (CbbPerspectiveItem) object;
      return Objects.equal(this.perspective, that.perspective);
    }
    return false;
  }

}

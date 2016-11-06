package com.sgu.infowksporga.jfx.perspective.cbb;

import java.util.Map;

import com.sgu.core.framework.gui.jfx.control.list.AbstractItemVo;
import com.sgu.core.framework.gui.jfx.util.UtilGUI;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.UtilPerspective;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class CbbPerspectiveItemVo.
 * Used to list all available perspectives
 */
@Getter
@Setter
@Slf4j
public class CbbPerspectiveItemVo extends AbstractItemVo {

  /** The perspective. */
  private Perspective perspective;

  /** The current workspaces id order. */
  private Map<String, Integer> currentWorkspaceIdOrder;

  /**
   * The Constructor.
   *
   * @param perspective the perspective
   */
  public CbbPerspectiveItemVo(final Perspective perspective) {
    super();
    this.perspective = perspective;
  }

  /** {@inheritDoc} */
  @Override
  public String getText() {
    return I18nHelperApp.getMessage(perspective.getName());
  }

  /** {@inheritDoc} */
  @Override
  public Tooltip getTooltip() {
    if (perspective.getDescription() != null) {
      return new Tooltip(I18nHelperApp.getMessage(perspective.getDescription()));
    }
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public ImageView getIcon() {
    if (perspective.getIcon() != null) {
      return new ImageView(UtilGUI.getImage(perspective.getIcon()));
    }

    return new ImageView(UtilPerspective.DEFAULT_PERSPECTIVE_ICON);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return getText();
  }

  /** {@inheritDoc} */
  @Override
  public Object getEncapsultatedObject() {
    return perspective;
  }

  /** {@inheritDoc} */
  @Override
  public String getNodeIdentifier() {
    return "" + perspective.getId();
  }

}

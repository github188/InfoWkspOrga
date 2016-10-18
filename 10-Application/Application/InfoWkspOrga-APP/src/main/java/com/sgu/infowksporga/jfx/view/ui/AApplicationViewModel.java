package com.sgu.infowksporga.jfx.view.ui;

import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewController;
import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewFxml;
import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewModel;
import com.sgu.core.framework.gui.jfx.i18n.I18nHelperJavaFX;
import com.sgu.core.framework.gui.jfx.util.UtilGUI;
import com.sgu.infowksporga.business.entity.View;

import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class AApplicationViewModel.
 *
 * @param <V> the value type
 * @param <C> the generic type
 */
@Getter
@Setter
public class AApplicationViewModel<V extends ADockableViewFxml, C extends ADockableViewController> extends ADockableViewModel<V, C> {

  /** The view. */
  protected View entityView;

  /**
   * The Constructor.
   */
  public AApplicationViewModel() {
    super();
  }

  /**
   * The Constructor.
   *
   * @param view the view
   */
  public AApplicationViewModel(final View entityView) {
    super();
    this.entityView = entityView;
  }

  /**
   * Gets the title translated.
   *
   * @return the title translated
   */
  @Override
  public String getTitleTranslated() {
    return I18nHelperJavaFX.getMessage(entityView.getName());
  }

  /**
   * Gets the title translated.
   *
   * @return the title translated
   */
  @Override
  public ImageView getIcon() {
    final String icon = I18nHelperJavaFX.getMessage(entityView.getIcon());
    return UtilGUI.getImageView(icon);
  }

}

package com.sgu.infowksporga.jfx.view.ui;

import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewModel;
import com.sgu.core.framework.gui.jfx.i18n.I18nHelperJavaFX;
import com.sgu.core.framework.gui.jfx.util.UtilGUI;
import com.sgu.infowksporga.business.entity.View;

import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class AAppViewModel.
 *
 * @param <V> the value type
 * @param <C> the generic type
 */
@Getter
@Setter
public abstract class AAppViewModel<V extends AAppViewFxml, C extends AAppViewController> extends ADockableViewModel<V, C> {

  /** The view. */
  protected View viewEntity;

  /**
   * The Constructor.
   */
  public AAppViewModel() {
    super();
  }

  /**
   * The Constructor.
   *
   * @param view the view
   */
  public AAppViewModel(final View viewEntity) {
    super();
    this.viewEntity = viewEntity;
  }

  /**
   * Gets the title translated.
   *
   * @return the title translated
   */
  @Override
  public String getTitleTranslated() {
    return I18nHelperJavaFX.getMessage(viewEntity.getName());
  }

  /**
   * Gets the title translated.
   *
   * @return the title translated
   */
  @Override
  public ImageView getIcon() {
    final String icon = I18nHelperJavaFX.getMessage(viewEntity.getIcon());
    return UtilGUI.getImageView(icon);
  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();

  }

}

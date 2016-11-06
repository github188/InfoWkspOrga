package com.sgu.infowksporga.jfx.view.web.ui;

import com.sgu.core.framework.gui.jfx.control.pane.dock.serialization.XDockView;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.UtilView;
import com.sgu.infowksporga.jfx.view.ui.AAppViewModel;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class WebViewModel.
 */
@Getter
@Setter
public class WebViewModel extends AAppViewModel<WebViewFxml, WebViewController> {

  /**
   * The Constructor.
   */
  public WebViewModel() {
    super();
  }

  /**
   * The Constructor.
   *
   * @param viewEntity the view
   */
  public WebViewModel(final View viewEntity) {
    super(viewEntity);
  }

  /** {@inheritDoc} */
  @Override
  public XDockView getXDockView() {
    return UtilView.convertViewEntityToXDockView(viewEntity);
  }

  /**
   * Gets the url.
   *
   * @return the url
   */
  public String getUrl() {
    final ViewAttribute attribute = viewEntity.getAttributesAsMap().get(ViewAttribute.WEB_VIEW_URL);
    return I18nHelperApp.getMessage(attribute.getValue());
  }

  /**
   * Fill ui.
   */
  @Override
  public void fillUI() {

  }

}

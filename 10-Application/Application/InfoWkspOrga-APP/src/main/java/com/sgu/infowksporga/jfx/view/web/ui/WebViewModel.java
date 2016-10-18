package com.sgu.infowksporga.jfx.view.web.ui;

import com.sgu.core.framework.gui.jfx.control.pane.dock.serialization.XDockView;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.UtilView;
import com.sgu.infowksporga.jfx.view.ui.AApplicationViewModel;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class WebViewModel.
 */
@Getter
@Setter
public class WebViewModel extends AApplicationViewModel<WebViewFxml, WebViewController> {

  /** The Constant WEB_VIEW_URL. Parameter */
  public static final String WEB_VIEW_URL = "WEB_VIEW_URL";

  /**
   * The Constructor.
   */
  public WebViewModel() {
    super();
  }

  /**
   * The Constructor.
   *
   * @param view the view
   */
  public WebViewModel(final View view) {
    super(view);
  }

  /** {@inheritDoc} */
  @Override
  public XDockView getXDockView() {
    return UtilView.convertViewEntityToXDockView(entityView);
  }

  /**
   * Gets the url.
   *
   * @return the url
   */
  public String getUrl() {
    return I18nHelperApp.getMessage(entityView.getAttributesAsMap().get(WEB_VIEW_URL).getValue());
  }

  /**
   * Fill ui.
   */
  @Override
  public void fillUI() {
    view().getWebEngine().load(getUrl());
  }

}

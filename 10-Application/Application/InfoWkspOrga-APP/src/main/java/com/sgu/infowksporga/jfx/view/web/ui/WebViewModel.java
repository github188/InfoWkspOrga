package com.sgu.infowksporga.jfx.view.web.ui;

import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewModel;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class WebViewModel.
 */
@Getter
@Setter
public class WebViewModel extends ADockableViewModel<WebViewFxml, WebViewController> {

  /** The Constant WEB_VIEW_URL. Parameter */
  public static final String WEB_VIEW_URL = "WEB_VIEW_URL";

  /**
   * The Constructor.
   */
  public WebViewModel() {
    super();
  }

  /**
   * Gets the url.
   *
   * @return the url
   */
  public String getUrl() {
    return I18nHelperApp.getMessage(getParameters().get(WEB_VIEW_URL));
  }

  /**
   * Fill ui.
   */
  @Override
  public void fillUI() {
    view().getWebEngine().load(getUrl());
  }

}

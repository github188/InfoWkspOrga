package com.sgu.infowksporga.jfx.views.web.dlg.properties;

import com.sgu.infowksporga.jfx.views.AbstractView;
import com.sgu.infowksporga.jfx.views.common.action.AbstractShowViewPropertiesDlgAction;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractViewPropertiesDlg;
import com.sgu.infowksporga.jfx.views.web.WebView;

/**
 * Description : Display Web Content View Properties Dlg Action class<br>
 * 
 * @author SGU
 */
public class ShowWebViewPropertiesDlgAction extends AbstractShowViewPropertiesDlgAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -6124596911162025134L;

  /**
   * Constructor<br>
   */
  public ShowWebViewPropertiesDlgAction(final AbstractView view) {
    super(view);
  }

  /** {@inheritDoc} */
  protected AbstractViewPropertiesDlg builViewPropertiesDlg(AbstractView view) {
    return new WebViewPropertiesDlg((WebView) view);
  }
}
package com.sgu.infowksporga.jfx.views.web.dlg.properties;

import com.sgu.infowksporga.jfx.views.common.dialog.AbstractSpecificViewConfigurationPanel;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractViewPropertiesDlg;
import com.sgu.infowksporga.jfx.views.common.dialog.action.AbstractViewCancelAction;
import com.sgu.infowksporga.jfx.views.common.dialog.action.AbstractViewValidateAction;
import com.sgu.infowksporga.jfx.views.web.WebView;
import com.sgu.infowksporga.jfx.views.web.dlg.properties.action.CancelAction;
import com.sgu.infowksporga.jfx.views.web.dlg.properties.action.ValidateAction;

/**
 * Description : WebViewPropertiesDlg class<br>
 */
public class WebViewPropertiesDlg extends AbstractViewPropertiesDlg {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -8198672854160646831L;

  /**
   * Constructor<br>
   * 
   * @param htmlView Reference to the view to manage
   */
  public WebViewPropertiesDlg(final WebView webView) {
    super(webView);
  }

  @Override
  protected AbstractViewValidateAction buildValidateAction() {
    return new ValidateAction(this);
  }

  @Override
  protected AbstractViewCancelAction buildCancelAction() {
    return new CancelAction(this);
  }

  @Override
  protected AbstractSpecificViewConfigurationPanel buildSpecificConfigurationPanel() {
    return new WebViewConfigurationPanel(this);
  }

}

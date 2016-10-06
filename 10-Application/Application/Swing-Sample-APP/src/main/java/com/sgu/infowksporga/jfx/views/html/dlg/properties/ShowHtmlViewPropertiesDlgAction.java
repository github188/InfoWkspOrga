package com.sgu.infowksporga.jfx.views.html.dlg.properties;

import com.sgu.infowksporga.jfx.views.AbstractView;
import com.sgu.infowksporga.jfx.views.common.action.AbstractShowViewPropertiesDlgAction;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractViewPropertiesDlg;
import com.sgu.infowksporga.jfx.views.html.HtmlView;

/**
 * Description : Display HTML Content View Properties Dlg Action class<br>
 *
 * @author SGU
 */
public class ShowHtmlViewPropertiesDlgAction extends AbstractShowViewPropertiesDlgAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -6124596911162025134L;

  /**
   * Constructor<br>
   */
  public ShowHtmlViewPropertiesDlgAction(final HtmlView htmlView) {
    super(htmlView);
  }

  /** {@inheritDoc} */
  @Override
  protected AbstractViewPropertiesDlg builViewPropertiesDlg(final AbstractView view) {
    return new HtmlViewPropertiesDlg((HtmlView) view);
  }
}
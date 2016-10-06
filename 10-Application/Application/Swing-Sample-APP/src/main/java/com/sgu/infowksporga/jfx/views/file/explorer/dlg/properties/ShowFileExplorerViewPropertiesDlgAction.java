package com.sgu.infowksporga.jfx.views.file.explorer.dlg.properties;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.views.AbstractView;
import com.sgu.infowksporga.jfx.views.common.action.AbstractShowViewPropertiesDlgAction;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractViewPropertiesDlg;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;

/**
 * The Class ShowFileExplorerViewPropertiesDlgAction.
 */
public class ShowFileExplorerViewPropertiesDlgAction extends AbstractShowViewPropertiesDlgAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -6124596911162025134L;

  /**
   * Constructor<br>
   */
  public ShowFileExplorerViewPropertiesDlgAction(final FileExplorerView fileExplorerView) {
    super(fileExplorerView);
  }

  @Override
  protected AbstractViewPropertiesDlg builViewPropertiesDlg(AbstractView view) {
    return new FileExplorerViewPropertiesDlg((FileExplorerView) view);
  }
}
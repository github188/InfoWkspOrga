package com.sgu.infowksporga.jfx.views.file.explorer.dlg.properties;

import java.awt.BorderLayout;

import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.panel.GSplitPane;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractSpecificViewConfigurationPanel;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractViewPropertiesDlg;
import com.sgu.infowksporga.jfx.views.common.dialog.action.AbstractViewCancelAction;
import com.sgu.infowksporga.jfx.views.common.dialog.action.AbstractViewValidateAction;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.dlg.properties.action.CancelAction;
import com.sgu.infowksporga.jfx.views.file.explorer.dlg.properties.action.ValidateAction;

import net.miginfocom.swing.MigLayout;

/**
 * Description : FileExplorerViewPropertiesDlg class<br>
 */
public class FileExplorerViewPropertiesDlg extends AbstractViewPropertiesDlg {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -8198672854160646831L;

  /**
   * ref
   */
  private GSplitPane splitPaneConfiguration;

  /**
   * To detect if it's cancel or validate clicked
   */
  private boolean cancelClicked = false;

  /**
   * Constructor<br>
   *
   * @param fileExplorerView Reference to the view to manage
   */
  public FileExplorerViewPropertiesDlg(final FileExplorerView fileExplorerView) {
    super(fileExplorerView);
  }

  /**
   * Description : buildDialog method <br>
   */
  protected void buildDialog() {

    final GPanel dialogPanel = new GPanel(new MigLayout("", "[grow,fill]"));

    add(dialogPanel, BorderLayout.CENTER);

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
    return new FileExplorerConfigurationPanel(this);
  }

}

package com.sgu.infowksporga.jfx.views.web.dlg.properties.action;

import java.awt.event.ActionEvent;

import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;
import com.sgu.infowksporga.business.dto.WorkspacesViewsDto;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractViewPropertiesDlg;
import com.sgu.infowksporga.jfx.views.common.dialog.action.AbstractViewValidateAction;
import com.sgu.infowksporga.jfx.views.web.WebView;
import com.sgu.infowksporga.jfx.views.web.WebViewIdentifier;
import com.sgu.infowksporga.jfx.views.web.dlg.properties.WebViewConfigurationPanel;

/**
 * Description : ValidateAction class<br>
 *
 * @author SGU
 */
public class ValidateAction extends AbstractViewValidateAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 2568554388620155754L;

  /**
   * Constructor<br>
   */
  public ValidateAction(final AbstractViewPropertiesDlg dialog) {
    super(dialog);
  }

  /**
   * Description : validateFields method <br>
   *
   * @return true if error is detected false otherwise
   */
  @Override
  protected boolean validateFields() {
    final boolean hasError = super.validateFields();

    // check URL

    return hasError;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent e) {

    final boolean hasError = validateFields();

    if (hasError) {
      UtilDlgMessage.error("error.fields", "");
      return;
    }

    final WebView viewUI = (WebView) getDialog().getView();
    final WebViewIdentifier viewIdentifier = (WebViewIdentifier) viewUI.getViewIdentifier();
    final WebViewConfigurationPanel pnlConfig = (WebViewConfigurationPanel) getDialog().getPnlSpecificConfig();

    // ---------------------------------
    // Get the "old" values from entity
    // ---------------------------------    
    WorkspacesViewsDto workspacesViewsDto = getWorkspacesViewsDto();

    // For the moment we assume view are not base on a master
    View viewEntity = workspacesViewsDto.getWorkspaceViewsByName().get(viewIdentifier.getViewEntityName());

    final String oldViewName = viewEntity.getName();
    final String oldDescription = viewEntity.getDescription();
    final String oldViewIcon = viewEntity.getIcon();
    final String oldCategory = viewEntity.getCategory();
    final String oldCmmiPractices = viewEntity.getCmmiPractices();
    final PartageEnum oldPartage = viewEntity.getPartage();
    final String oldTags = viewEntity.getTags();

    final String oldWebUrl = viewEntity.getAttributesAsMap().get(ViewAttribute.WEB_URL).getValue();

    // ---------------------------------
    // Get the "new" values
    // ---------------------------------
    final String newViewName = getDialog().getPnlIdentity().getTxtViewTitle().getText();
    final String newWebUrl = pnlConfig.getTxtUrl().getText();

    // ---------------------------------
    // Application des modifications
    // ---------------------------------
    viewIdentifier.setViewEntityName(newViewName);

    viewEntity.setName(newViewName);
    viewEntity.getAttributesAsMap().get(ViewAttribute.WEB_URL).setValue(newWebUrl);

    viewUI.applyViewConfiguration();

    GUISessionProxy.setCurrentWorkspaceHasChanged(true);
    GUISessionProxy.getInfoWrkspOrgaFrame().getWorkspaceMapView().refreshWorkspaceMapHTML();

    getDialog().dispose();

  }

}
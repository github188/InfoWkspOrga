package com.sgu.infowksporga.jfx.views.html.dlg.properties.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;
import com.sgu.infowksporga.business.dto.WorkspacesViewsDto;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractViewPropertiesDlg;
import com.sgu.infowksporga.jfx.views.common.dialog.action.AbstractViewValidateAction;
import com.sgu.infowksporga.jfx.views.html.HtmlView;
import com.sgu.infowksporga.jfx.views.html.HtmlViewIdentifier;
import com.sgu.infowksporga.jfx.views.html.dlg.properties.HtmlViewConfigurationPanel;

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

    final HtmlView viewUI = (HtmlView) getDialog().getView();
    final HtmlViewIdentifier viewIdentifier = (HtmlViewIdentifier) viewUI.getViewIdentifier();
    final HtmlViewConfigurationPanel pnlConfig = (HtmlViewConfigurationPanel) getDialog().getPnlSpecificConfig();

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

    //final String oldHTML = viewEntity.getAttributesAsMap().get(ViewAttribute.HTML_TEXT).getValue();
    final String oldZoom = viewEntity.getAttributesAsMap().get(ViewAttribute.HTML_ZOOM).getValue();

    // ---------------------------------
    // Get the "new" values
    // ---------------------------------
    final String newViewName = getDialog().getPnlIdentity().getTxtViewTitle().getText();
    final String newZoom = pnlConfig.getTxtZoom().getText();

    // ---------------------------------
    // Application des modifications
    // ---------------------------------
    viewIdentifier.setViewEntityName(newViewName);

    viewEntity.setName(newViewName);
    viewEntity.getAttributesAsMap().get(ViewAttribute.HTML_ZOOM).setValue(newZoom);

    viewUI.applyViewConfiguration();

    GUISessionProxy.setCurrentWorkspaceHasChanged(true);
    GUISessionProxy.getInfoWrkspOrgaFrame().getWorkspaceMapView().refreshWorkspaceMapHTML();

    getDialog().dispose();

  }

  /**
   * Validate zoom.
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "html.view.properties.dlg.zoom.error.tooltip",
                value = "Le zoom doit être compris entre 0 <= et <= 400"), // Force /n
  })
  public void validateZoomValue() {
    //getTxtZoom().clearMessage();

    //final String zoom = getTxtZoom().getText();
    /*
     * if (UtilString.isNotBlank(zoom)) {
     * // Check ZOOM
     * final boolean isZoomExists = UtilNumber.;
     * getTxtZoom().showErrorMessage("web.view.properties.dlg.zoom.error.tooltip");
     * }
     * } else {
     * }
     */

  }

}
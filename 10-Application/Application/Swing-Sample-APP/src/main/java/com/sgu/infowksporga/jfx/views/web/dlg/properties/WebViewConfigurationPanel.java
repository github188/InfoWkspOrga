/*
 *
 */
package com.sgu.infowksporga.jfx.views.web.dlg.properties;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.GLabelField;
import com.sgu.core.framework.gui.swing.MessageTypeEnum;
import com.sgu.core.framework.gui.swing.overlay.GDefaultOverlayable;
import com.sgu.core.framework.gui.swing.overlay.UtilOverlay;
import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.core.framework.util.UtilIO;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractSpecificViewConfigurationPanel;
import com.sgu.infowksporga.jfx.views.web.WebView;
import com.sgu.infowksporga.jfx.views.web.WebViewIdentifier;
import com.sgu.infowksporga.jfx.views.web.dlg.properties.listener.LstnTxtUrlFocusListener;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : WebViewConfigurationPanel class<br>
 */
@Getter
@Setter
public class WebViewConfigurationPanel extends AbstractSpecificViewConfigurationPanel {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -8774555177914892315L;

  /**
   * Label View URL
   */
  private GLabel lblUrl;

  /**
   * txt view target URL
   */
  private GTextField txtUrl;

  /**
   * Constructor<br>
   */
  public WebViewConfigurationPanel(final WebViewPropertiesDlg dialog) {
    super(dialog);
  }

  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "web.view.properties.dlg.lbl.url.text", value = "URL"), // Force /n
  })
  public void createUI() {
    super.createUI();

    lblUrl = new GLabelField();
    lblUrl.setBundleConfiguration("web.view.properties.dlg.lbl.url", I18nHelperApp.getI18nHelper());
    lblUrl.setMandatory(true);

    txtUrl = new GTextField();
    txtUrl.addFocusListener(new LstnTxtUrlFocusListener(this));

    final GDefaultOverlayable txtUrlOverlay = UtilOverlay.addOverlayErrorRenderer(txtUrl);
    txtUrl.setOverlay(txtUrlOverlay);
    txtUrl.setLabel(lblUrl);

    add(lblUrl);
    add(txtUrlOverlay, "growx");
  }

  /**
   * Validate url.
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "web.view.properties.dlg.url.warning.tooltip",
                value = "Attention cette URL n'est pas accessible pour le moment !"), // Force /n
                @I18nProperty(key = "web.view.properties.dlg.url.ok.tooltip", value = "Cette URL est accessible !"), // Force /n
  })
  public void validateURL() {
    getTxtUrl().clearMessage();

    final String url = getTxtUrl().getText();

    if (UtilString.isNotBlank(url)) {
      // Check URL
      final boolean isUrlExists = UtilIO.isUrlExists(url);
      if (isUrlExists == true) {
        getTxtUrl().showOverlayMessage("web.view.properties.dlg.url.ok.tooltip", MessageTypeEnum.VALID);
      }
      else {
        getTxtUrl().showOverlayMessage("web.view.properties.dlg.url.warning.tooltip", MessageTypeEnum.WARNING);
      }
    }
  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();

    final WebView viewUI = (WebView) getDialog().getView();
    final WebViewIdentifier viewIdentifier = (WebViewIdentifier) viewUI.getViewIdentifier();

    // Get view Name
    String viewEntityName = viewIdentifier.getViewEntityName();
    getDialog().getPnlIdentity().getTxtViewTitle().setText(viewEntityName);

    // Get Specific view
    // For the moment we assume view are not base on a master
    View viewEntity = getWorkspacesViewsDto().getWorkspaceViewsByName().get(viewIdentifier.getViewEntityName());

    final String webUrlStr = viewEntity.getAttributesAsMap().get(ViewAttribute.WEB_URL).getValue();
    getTxtUrl().setText(webUrlStr);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setDisplayMode(final int mode) {
    super.setDisplayMode(mode);

  }

}

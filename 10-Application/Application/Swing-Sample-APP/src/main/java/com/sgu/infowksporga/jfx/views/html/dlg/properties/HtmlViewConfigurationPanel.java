/*
 *
 */
package com.sgu.infowksporga.jfx.views.html.dlg.properties;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.GLabelField;
import com.sgu.core.framework.gui.swing.overlay.GDefaultOverlayable;
import com.sgu.core.framework.gui.swing.overlay.UtilOverlay;
import com.sgu.core.framework.gui.swing.textfield.GNumericField;
import com.sgu.core.framework.util.Range;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractSpecificViewConfigurationPanel;
import com.sgu.infowksporga.jfx.views.html.HtmlView;
import com.sgu.infowksporga.jfx.views.html.HtmlViewIdentifier;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : HtmlViewConfigurationPanel class<br>
 */
@Getter
@Setter
public class HtmlViewConfigurationPanel extends AbstractSpecificViewConfigurationPanel {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -8774555177914892315L;

  /** The lbl html value. */
  private GLabel lblHtml;

  /** The lbl html value. */
  private GLabel lblHtmlValue;

  /**
   * Label View ZOOM
   */
  private GLabel lblZoom;

  /**
   * txt view target ZOOM
   */
  private GNumericField txtZoom;

  /**
   * Constructor<br>
   */
  public HtmlViewConfigurationPanel(final HtmlViewPropertiesDlg dialog) {
    super(dialog);
  }

  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "html.view.properties.dlg.lbl.zoom.text", value = "ZOOM"), // Force /n
                @I18nProperty(key = "html.view.properties.dlg.lbl.content.text", value = "Contenu HTML"), // Force /n
                @I18nProperty(key = "html.view.properties.dlg.lbl.content.tooltip",
                value = "Le contenu de cette vue se modifie via l'icône edit de la vue"),
                @I18nProperty(key = "html.view.properties.dlg.lbl.content.access.text",
                value = "Le contenu de cette vue se modifie via l'icône edit de la vue"), })
  public void createUI() {
    super.createUI();

    lblHtml = new GLabelField();
    lblHtml.setBundleConfiguration("html.view.properties.dlg.lbl.content", I18nHelperApp.getI18nHelper());
    lblHtml.setMandatory(true);

    lblHtmlValue = new GLabel();
    lblHtmlValue.setBundleConfiguration("html.view.properties.dlg.lbl.content.access", I18nHelperApp.getI18nHelper());

    lblZoom = new GLabelField();
    lblZoom.setBundleConfiguration("html.view.properties.dlg.lbl.zoom", I18nHelperApp.getI18nHelper());
    lblZoom.setMandatory(true);

    txtZoom = new GNumericField();
    txtZoom.setLabel(lblZoom);
    txtZoom.setNumericFieldType(GNumericField.INTEGER_FIELD);
    txtZoom.getNumericDocument().setRange(new Range(0, 400));
    txtZoom.getNumericDocument().setTestOnValidCharsActive(true);
    txtZoom.getNumericDocument().setValidCharacters(new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' });

    final GDefaultOverlayable txtZoomOverlay = UtilOverlay.addOverlayErrorRenderer(txtZoom);
    txtZoom.setOverlay(txtZoomOverlay);
    txtZoom.setLabel(lblZoom);

    add(lblHtml);
    add(lblHtmlValue, "wrap");
    add(lblZoom);
    add(txtZoomOverlay, "width 50px");
  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();

    final HtmlView viewUI = (HtmlView) getDialog().getView();
    final HtmlViewIdentifier viewIdentifier = (HtmlViewIdentifier) viewUI.getViewIdentifier();

    // Get view Name
    String viewEntityName = viewIdentifier.getViewEntityName();
    getDialog().getPnlIdentity().getTxtViewTitle().setText(viewEntityName);

    // Get Specific view
    // For the moment we assume view are not base on a master
    View viewEntity = getWorkspacesViewsDto().getWorkspaceViewsByName().get(viewIdentifier.getViewEntityName());

    final String webZoomStr = viewEntity.getAttributesAsMap().get(ViewAttribute.HTML_ZOOM).getValue();
    getTxtZoom().setText(webZoomStr);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setDisplayMode(final int mode) {
    super.setDisplayMode(mode);

  }

}

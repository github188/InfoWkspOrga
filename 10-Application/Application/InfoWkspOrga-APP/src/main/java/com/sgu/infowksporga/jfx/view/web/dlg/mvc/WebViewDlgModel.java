package com.sgu.infowksporga.jfx.view.web.dlg.mvc;

import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.view.adlg.config.mvc.AViewDlgModel;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class WebViewModel.
 */
@Getter
@Setter
public class WebViewDlgModel extends AViewDlgModel<WebViewDlgViewFxml, WebViewDlgController> {

  /** The url property. */
  private StringProperty urlProperty = new SimpleStringProperty();

  /**
   * The Constructor.
   */
  public WebViewDlgModel() {
    super();
  }

  /**
   * Map configuration view to model.
   *
   * @param view the view
   */
  @Override
  public void mapConfigurationViewToModel(final View viewEntity) {
    final ViewAttribute attr = viewEntity.getAttributesAsMap().get(ViewAttribute.WEB_VIEW_URL);
    urlProperty.setValue(I18nHelperApp.getMessage(attr.getValue()));
  }

  @Override
  public void mapConfigurationModelToView(final View viewEntity) {
    ViewAttribute urlAttribute = viewEntity.getAttributesAsMap().get(ViewAttribute.WEB_VIEW_URL);
    if (urlAttribute == null) {
      urlAttribute = new ViewAttribute(viewEntity.getId(), ViewAttribute.WEB_VIEW_URL, urlProperty.getValue());
      viewEntity.getAttributes().add(urlAttribute);
    }
    else {
      urlAttribute.setValue(urlProperty.getValue());
    }
  }

}

package com.sgu.infowksporga.jfx.util;

import java.util.Set;

import org.dockfx.DockPos;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockNode;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.control.pane.dock.serialization.XAttribute;
import com.sgu.core.framework.gui.jfx.control.pane.dock.serialization.XDockView;
import com.sgu.core.framework.gui.jfx.util.UtilDockFX;
import com.sgu.core.framework.util.UtilDate;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.jfx.view.ui.AApplicationViewModel;
import com.sgu.infowksporga.jfx.view.web.ui.WebViewModel;
import com.sgu.infowksporga.jfx.view.web.ui.WebViewScreen;

/**
 * The Class UtilView.
 */
public class UtilView {

  /**
   * The Constructor.
   */
  private UtilView() {
  }

  /**
   * Convert view entity to x dock view.
   *
   * @param view the view
   * @return the x dock view
   */
  public final static XDockView convertViewEntityToXDockView(final View view) {
    final XDockView xDocView = new XDockView();
    xDocView.setCategory(view.getCategory());
    xDocView.setCmmiPractices(view.getCmmiPractices());
    xDocView.setCreatedBy(view.getCreatedBy());
    xDocView.setCreatedDate(UtilDate.formatDate(view.getCreatedDate(), "dd/MM/yyyy HH:mm:ss"));
    xDocView.setDescription(view.getDescription());
    xDocView.setDockPos(view.getDockPos().toString());
    xDocView.setOrder(view.getOrder());
    xDocView.setNextSibling(view.isNextSibling());
    xDocView.setHeight(view.getHeight());
    xDocView.setIcon(view.getIcon());
    xDocView.setId(view.getId());
    xDocView.setLastModifiedBy(view.getLastModifiedBy());
    xDocView.setLastModifiedDate(UtilDate.formatDate(view.getLastModifiedDate(), "dd/MM/yyyy HH:mm:ss"));
    xDocView.setModelBean(view.getModelBean());
    xDocView.setName(view.getName());
    xDocView.setNameBgColor(view.getNameBgColor());
    xDocView.setNameColor(view.getNameColor());
    xDocView.setOwner(view.getOwner());

    if (view.getPartage() != null) {
      xDocView.setPartage(view.getPartage().getValue());
    }

    xDocView.setScreenBean(view.getScreenBean());
    xDocView.setTags(view.getTags());
    xDocView.setDockNodeBean(view.getDockNodeBean());
    xDocView.setWidth(view.getWidth());
    xDocView.setWorkspaceId(view.getWorkspaceId());

    final Set<ViewAttribute> viewAttributes = view.getAttributes();
    for (final ViewAttribute viewAttribute : viewAttributes) {
      final XAttribute xAttribute = new XAttribute();
      xAttribute.setCreatedBy(viewAttribute.getCreatedBy());
      xAttribute.setCreatedDate(UtilDate.formatDate(viewAttribute.getCreatedDate(), "dd/MM/yyyy HH:mm:ss"));
      xAttribute.setDescription(viewAttribute.getDescription());
      xAttribute.setId(viewAttribute.getId());
      xAttribute.setKey(viewAttribute.getName());
      xAttribute.setLastModifiedBy(viewAttribute.getLastModifiedBy());
      xAttribute.setLastModifiedDate(UtilDate.formatDate(viewAttribute.getLastModifiedDate(), "dd/MM/yyyy HH:mm:ss"));
      xAttribute.setValue(viewAttribute.getValue());
      xAttribute.setViewId(viewAttribute.getViewId());

      xDocView.getXAttribute().add(xAttribute);
    }

    return xDocView;
  }

  /**
   * Builds the default view model.
   *
   * @return the a application view model
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "application.web.view.default.text", value = "Vue par défaut (Quand le workspace sélectionné n'est encore définit)"), // Force /n
                @I18nProperty(key = "application.web.view.default.url", value = "https://www.google.fr"), // Force /n
                @I18nProperty(key = "application.web.view.default.icon", value = "/icons/web.png"), // Force /n
  })
  public final static AApplicationViewModel buildDefaultViewModel() {
    final WebViewModel model = new WebViewModel();
    final View view = new View();
    view.setName("application.web.view.default.text");
    view.setIcon("application.web.view.default.icon");
    view.setScreenBean(WebViewScreen.class.getName());
    view.setModelBean(WebViewModel.class.getName());
    view.setDockNodeBean(GDockNode.class.getName());
    view.addAttribute(null, null, WebViewModel.WEB_VIEW_URL, "application.web.view.default.url");

    model.setEntityView(view);
    return model;
  }

  /**
   * Adds the view.
   *
   * @param model the model
   */
  public final static GDockNode addDockPaneView(final AApplicationViewModel model) {
    return addDockPaneView(model, DockPos.RIGHT, null);
  }

  /**
   * Adds the dock pane view.
   *
   * @param model the model
   * @param dockPos the dock pos
   */
  public final static GDockNode addDockPaneView(final AApplicationViewModel model, final DockPos dockPos, final GDockNode sibling) {
    try {
      final GDockNode dockNode = UtilDockFX.buildDockableViewNodeFromModel(model);
      final GDockPane dockPane = GUISessionProxy.getApplication().getApplicationScreen().getView().getDockPane();
      if (sibling != null) {
        dockNode.dock(dockPane, dockPos, sibling);
      }
      else {
        dockNode.dock(dockPane, dockPos);
      }

      return dockNode;
    } catch (final Exception e) {
      throw new TechnicalException(e);
    }
  }

}

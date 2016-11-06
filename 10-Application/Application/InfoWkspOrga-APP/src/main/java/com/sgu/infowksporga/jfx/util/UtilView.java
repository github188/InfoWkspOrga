package com.sgu.infowksporga.jfx.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.dockfx.DockPos;
import org.dockfx.pane.ContentSplitPane;
import org.dockfx.pane.ContentTabPane;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockNode;
import com.sgu.core.framework.gui.jfx.control.pane.dock.GDockPane;
import com.sgu.core.framework.gui.jfx.control.pane.dock.serialization.XAttribute;
import com.sgu.core.framework.gui.jfx.control.pane.dock.serialization.XDockView;
import com.sgu.core.framework.gui.jfx.util.UtilDockFX;
import com.sgu.core.framework.gui.jfx.util.UtilStyle;
import com.sgu.core.framework.util.UtilDate;
import com.sgu.infowksporga.business.entity.IStylable;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.business.entity.enumeration.DockPosEnum;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.view.ui.AAppViewModel;
import com.sgu.infowksporga.jfx.view.web.ui.WebViewModel;
import com.sgu.infowksporga.jfx.view.web.ui.WebViewScreen;

import javafx.scene.Node;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class UtilView.
 */
@Slf4j
public class UtilView {

  /**
   * The Constructor.
   */
  private UtilView() {
  }

  /**
   * Convert view entity to x dock view.
   *
   * @param viewEntity the view
   * @return the x dock view
   */
  public final static XDockView convertViewEntityToXDockView(final View viewEntity) {
    final XDockView xDocView = new XDockView();
    xDocView.setCategory(viewEntity.getCategory());
    xDocView.setCmmiPractices(viewEntity.getCmmiPractices());
    xDocView.setCreatedBy(viewEntity.getCreatedBy());
    xDocView.setCreatedDate(UtilDate.formatDate(viewEntity.getCreatedDate(), "dd/MM/yyyy HH:mm:ss"));
    xDocView.setDescription(viewEntity.getDescription());
    xDocView.setDockPos(viewEntity.getDockPos().toString());
    xDocView.setOrder(viewEntity.getOrder());
    xDocView.setNextSibling(viewEntity.isNextSibling());
    xDocView.setHeight(viewEntity.getHeight());
    xDocView.setIcon(viewEntity.getIcon());
    xDocView.setId(viewEntity.getId());
    xDocView.setLastModifiedBy(viewEntity.getLastModifiedBy());
    xDocView.setLastModifiedDate(UtilDate.formatDate(viewEntity.getLastModifiedDate(), "dd/MM/yyyy HH:mm:ss"));
    xDocView.setModelBean(viewEntity.getModelBean());
    xDocView.setName(viewEntity.getName());
    xDocView.setBgColor(viewEntity.getBgColor());
    xDocView.setColor(viewEntity.getColor());
    xDocView.setScreenBean(viewEntity.getScreenBean());
    xDocView.setTags(viewEntity.getTags());
    xDocView.setDockNodeBean(viewEntity.getDockNodeBean());
    xDocView.setWidth(viewEntity.getWidth());
    xDocView.setWorkspaceId(viewEntity.getWorkspaceId());
    xDocView.setBold(viewEntity.isBold());
    xDocView.setItalic(viewEntity.isItalic());
    xDocView.setStrike(viewEntity.isStrike());
    xDocView.setUnderline(viewEntity.isUnderline());

    final Set<ViewAttribute> viewAttributes = viewEntity.getAttributes();
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
  public final static GDockNode buildDefaultViewDockNode() {
    final WebViewModel model = new WebViewModel();
    final View view = new View();
    view.setName(I18nHelperApp.getMessage("application.web.view.default.text"));
    view.setIcon(I18nHelperApp.getMessage("application.web.view.default.icon"));
    view.setScreenBean(WebViewScreen.class.getName());
    view.setModelBean(WebViewModel.class.getName());
    view.setDockNodeBean(GDockNode.class.getName());
    view.setDockPos(DockPosEnum.getEnumForValue(DockPos.RIGHT.toString()));
    view.addAttribute(null, null, ViewAttribute.WEB_VIEW_URL, I18nHelperApp.getMessage("application.web.view.default.url"));

    model.setViewEntity(view);

    final WebViewScreen screen = new WebViewScreen();
    screen.setModel(model);
    screen.initMVC();

    final GDockNode dockNode = new GDockNode(screen);

    return dockNode;
  }

  /**
   * Adds the view.
   *
   * @param model the model
   */
  public final static GDockNode addDockNodeView(final GDockNode dockNode) {
    return addDockNodeView(dockNode, DockPos.RIGHT, null);
  }

  /**
   * Adds the dock pane view.
   *
   * @param model the model
   * @param dockPos the dock pos
   */
  public final static GDockNode addDockNodeView(final GDockNode dockNode, final DockPos dockPos, final GDockNode sibling) {
    try {
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

  /**
   * Serialize dock fx structure.
   *
   * @param dockPane the dock pane
   * @return the x dock
   */
  public final static List<View> getViewsFromDockPane(final GDockPane dockPane) {

    final List<Node> root = dockPane.getChildren();
    final List<View> views = new ArrayList<View>();
    final ViewInfoWrapper wrapper = new ViewInfoWrapper(0, false);
    getViewsFromDockPaneRecursively(root, dockPane, views, 0, wrapper);

    return views;
  }

  /**
   * Gets the views from dock pane recursively.
   *
   * @param children the children
   * @param parent the parent
   * @param views the views
   * @param level the level
   */
  public final static void getViewsFromDockPaneRecursively(final List<Node> children, final Node parent, final List<View> views, final int level,
  final ViewInfoWrapper viewInfo) {

    int childIndex = 0;
    for (final Node childNode : children) {

      if (childNode instanceof ContentSplitPane) {
        final ContentSplitPane contentSplitPane = (ContentSplitPane) childNode;
        getViewsFromDockPaneRecursively(contentSplitPane.getChildrenList(), contentSplitPane, views, level + 1, viewInfo);
        viewInfo.isNextSibiling = true;
      }
      else if (childNode instanceof ContentTabPane) {
        final ContentTabPane contentTabPane = (ContentTabPane) childNode;
        getViewsFromDockPaneRecursively(contentTabPane.getChildrenList(), contentTabPane, views, level + 1, viewInfo);
      }
      else if (childNode instanceof GDockNode) {
        final GDockNode dockNode = (GDockNode) childNode;
        final AAppViewModel model = (AAppViewModel) dockNode.getScreen().getModel();

        final View view = model.getViewEntity();
        view.setOrder(viewInfo.viewOrder);
        view.setDockNodeBean(dockNode.getClass().getName());
        view.setScreenBean(dockNode.getScreen().getClass().getName());
        view.setModelBean(model.getClass().getName());
        view.setWidth(dockNode.getWidth());
        view.setHeight(dockNode.getHeight());
        final String dockPos = UtilDockFX.getDockPosition(dockNode, parent, childIndex);
        view.setDockPos(DockPosEnum.valueOf(dockPos));
        viewInfo.viewOrder += 1;
        view.setNextSibling(viewInfo.isNextSibiling);
        viewInfo.isNextSibiling = false;

        //--------------------------------

        views.add(view);

        childIndex++;
      }
      else {
        throw new TechnicalException();
      }
    }
  }

  /**
   * Builds the style.
   *
   * @param stylable the stylable
   * @return the string
   */
  public final static String buildStyle(final IStylable stylable) {
    String style = "";

    if (stylable.getColor() != null) {
      style += UtilStyle.getStyleForColor(stylable.getColor());
    }
    if (stylable.isBold() == true) {
      style += UtilStyle.BOLD_FX_CSS;
    }
    if (stylable.isItalic() == true) {
      style += UtilStyle.ITALIC_FX_CSS;
    }
    if (stylable.isUnderline() == true) {
      style += UtilStyle.UNDERLINE_FX_CSS;
    }
    if (stylable.isStrike() == true) {
      style += UtilStyle.STRIKE_FX_CSS;
    }

    return style;
  }

}

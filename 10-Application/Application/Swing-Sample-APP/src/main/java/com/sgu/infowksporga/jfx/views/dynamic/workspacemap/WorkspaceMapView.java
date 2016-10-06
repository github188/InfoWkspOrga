package com.sgu.infowksporga.jfx.views.dynamic.workspacemap;

import java.awt.Desktop;
import java.util.ArrayList;

import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import org.apache.commons.lang3.StringEscapeUtils;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.docking.GDockingUtil;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.util.UtilHttp;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.html.HtmlView;
import com.sgu.infowksporga.jfx.views.html.UtilZoom;
import com.sgu.infowksporga.jfx.workspace.AbstractWorkspace;

import lombok.extern.slf4j.Slf4j;
import net.infonode.docking.DockingWindow;
import net.infonode.docking.RootWindow;
import net.infonode.docking.SplitWindow;
import net.infonode.docking.TabWindow;
import net.infonode.docking.WindowBar;

/**
 * Description : WorkspaceMapView class<br>
 * We use an extension of HTML View to display site MAP, HTML is not editable the site map is builded automatically
 */
@Slf4j
public class WorkspaceMapView extends HtmlView {

  /**
   * String indentation
   */
  private static final String INDENT_STRING = "&nbsp;&nbsp;&nbsp;&nbsp;";

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 6674606955595787973L;

  /** The view title param for URL link to view. */
  private static String VIEW_TITLE_PARAM = "viewTitle";

  /** The view type param for URL link to view. */
  private static String VIEW_TYPE_PARAM = "viewType";

  /**
   * Constructor<br>
   *
   * @param configuration it's the view configuration. In this view not used, just here to respect the pattern
   */
  public WorkspaceMapView() {
    super(null);

  }

  /**
   * Description : setConfiguration method <br>
   *
   * @param workspaceDto The links configuration
   */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "workspace.map.view.text", value = "Plan du workspace"), // Force \n
                @I18nProperty(key = "workspace.map.view.icon", value = "/icons/map.png"), // Force \n
  })
  public void applyViewConfiguration() {
    getViewProperties().setTitle(I18nHelperApp.getMessage("workspace.map.view.text"));
    getViewProperties().setIcon(UtilGUI.getImageIcon(I18nHelperApp.getMessage("workspace.map.view.icon")));

    // Manage HTML Content to display
    final String htmlContentStr = buildWorkspaceMapHTML();
    htmlContent.setText(htmlContentStr);

    // Manage Html Zoom
    final String htmlZoomStr = txtZoom.getText();
    if (UtilString.isNotBlank(htmlZoomStr)) {
      UtilZoom.zoomTo(this, Integer.valueOf(htmlZoomStr));
    }
    else {
      UtilZoom.zoomTo(this, 0);
    }

    htmlContent.setCaretPosition(0);
  }

  /**
   * Refresh workspace map html.
   */
  public void refreshWorkspaceMapHTML() {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {
        applyViewConfiguration();
      }
    });

  }

  /** {@inheritDoc} */
  @Override
  protected void addToolbarEditorButton(final GPanel toolbar) {
    // In this view don't add modification posibilities, just read
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void addHyperLinkListener() {
    // To enable click when user select an HTML link and to redirect to view
    htmlContent.addHyperlinkListener(new HyperlinkListener() {
      @Override
      public void hyperlinkUpdate(final HyperlinkEvent evt) {
        if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
          if (Desktop.isDesktopSupported()) {
            if (evt.getURL() != null) {
              final AbstractWorkspace workspace = GUISessionProxy.getCurrentWorkspace();
              final RootWindow root = workspace.getRootWindow();

              final DockingWindow window = GDockingUtil.getViewByTitle(root,
                                                                       UtilHttp.getQueryMap(evt.getURL().getQuery()).get(VIEW_TITLE_PARAM));

              buildWorkspaceMapHTML(true); // used to minimize an eventually maximized windows; to evict conflict

              SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                  window.maximize();
                  window.restoreFocus();
                }
              });

            }
          }
        }
      }
    });
  }

  /**
   * Builds the workspace map html.
   *
   * @return the string
   */
  public String buildWorkspaceMapHTML() {
    return buildWorkspaceMapHTML(false);
  }

  /**
   * Builds the workspace map html.
   *
   * @param restoreAllWindows the restore all windows
   * @return the string
   */
  protected String buildWorkspaceMapHTML(boolean restoreAllWindows) {
    final StringBuilder builder = new StringBuilder("");
    builder.append("<div>");

    final AbstractWorkspace workspace = GUISessionProxy.getCurrentWorkspace();
    if (workspace != null) {

      final RootWindow root = workspace.getRootWindow();

      // retrieve all tab window
      final ArrayList tabWindows = new ArrayList();
      GDockingUtil.getTabWindowList(root, tabWindows);

      final int rootChildCount = root.getChildWindowCount();
      for (int i = 0; i < rootChildCount; i++) {
        final DockingWindow window = root.getChildWindow(i);

        if (window.isMaximized() && restoreAllWindows) {
          window.restoreFocus();
          window.restore();
          if (log.isDebugEnabled()) {
            log.debug("buildWorkspaceMapHTML(boolean) - Restore 01"); //$NON-NLS-1$
          }
        }

        if (!(window instanceof WindowBar)) {
          parseWorkspaceRecursively(builder, window, tabWindows, 0, restoreAllWindows);
        }

      }
    }

    builder.append("</div>");

    return builder.toString();
  }

  /**
   * Description : parse Workspace Recursively method <br>
   *
   * @param builder
   * @param window
   */
  private static void parseWorkspaceRecursively(final StringBuilder builder, final DockingWindow window, final ArrayList tabWindows,
  final int depth, boolean restoreAllWindows) {

    if (window instanceof TabWindow && tabWindows.contains(window)) {
      final TabWindow tabWindow = (TabWindow) window;

      if (tabWindow.isMaximized() && restoreAllWindows) {
        tabWindow.restoreFocus();
        tabWindow.restore();
        if (log.isDebugEnabled()) {
          log.debug("parseWorkspaceRecursively(StringBuilder, DockingWindow, ArrayList, int, boolean) - Restore 02"); //$NON-NLS-1$
        }
      }

      final int tabChildCount = tabWindow.getChildWindowCount();
      for (int i = 0; i < tabChildCount; i++) {
        for (int j = 0; j < depth; j++) {
          builder.append(INDENT_STRING);
        }

        DockingWindow childWindow = tabWindow.getChildWindow(i);
        if (window.isMaximized() && restoreAllWindows) {
          window.restoreFocus();
          window.restore();
          if (log.isDebugEnabled()) {
            log.debug("parseWorkspaceRecursively(StringBuilder, DockingWindow, ArrayList, int, boolean) - Restore 03"); //$NON-NLS-1$
          }
        }

        addWindowUrl(builder, childWindow.toString(), "Tab", depth);
        if (childWindow.getChildWindowCount() > 0) {
          parseWorkspaceRecursively(builder, childWindow, tabWindows, depth + 1, restoreAllWindows);
        }
      }
    }

    else if (window instanceof SplitWindow) {
      final SplitWindow splitWindow = (SplitWindow) window;

      if (splitWindow.isMaximized() && restoreAllWindows) {
        window.restoreFocus();
        splitWindow.restore();
        if (log.isDebugEnabled()) {
          log.debug("parseWorkspaceRecursively(StringBuilder, DockingWindow, ArrayList, int, boolean) - Restore 04"); //$NON-NLS-1$
        }
      }
      if (splitWindow.getLeftWindow().isMaximized() && restoreAllWindows) {
        window.restoreFocus();
        splitWindow.getLeftWindow().restore();
        if (log.isDebugEnabled()) {
          log.debug("parseWorkspaceRecursively(StringBuilder, DockingWindow, ArrayList, int, boolean) - Restore 05"); //$NON-NLS-1$
        }
      }
      if (splitWindow.getRightWindow().isMaximized() && restoreAllWindows) {
        window.restoreFocus();
        splitWindow.getRightWindow().restore();
        if (log.isDebugEnabled()) {
          log.debug("parseWorkspaceRecursively(StringBuilder, DockingWindow, ArrayList, int, boolean) - Restore 06"); //$NON-NLS-1$
        }
      }

      parseWorkspaceRecursively(builder, splitWindow.getLeftWindow(), tabWindows, depth, restoreAllWindows);

      parseWorkspaceRecursively(builder, splitWindow.getRightWindow(), tabWindows, depth, restoreAllWindows);
    }

    else {
      for (int j = 0; j < depth; j++) {
        builder.append(INDENT_STRING);
      }

      if (window != null) {
        if (window.isMaximized() && restoreAllWindows) {
          window.restoreFocus();
          window.restore();
          if (log.isDebugEnabled()) {
            log.debug("parseWorkspaceRecursively(StringBuilder, DockingWindow, ArrayList, int, boolean) - Restore 07"); //$NON-NLS-1$
          }
        }
        addWindowUrl(builder, window.toString(), "Simple_View", depth);
      }
    }

  }

  /**
   * Description : addWindowUrl method <br>
   *
   * @param builder
   * @param viewTitle
   * @param viewType
   */
  private static void addWindowUrl(final StringBuilder builder, final String viewTitle, final String viewType, final int depth) {
    final String font = "Tahoma";
    String color = "green";
    final int size = 3;

    if (depth == 0) {
      color = "blue";
    }
    if (depth == 1) {
      color = "#ff8000"; // Orange
    }
    if (depth > 1) {
      color = "707070"; // Gris clair
    }

    builder.append("<font face=\"").append(font).append("\" size=\"").append(size).append("\" color=\"").append(color)
           .append("\"><a href=\"http://www.infowrksporga.fr?").append(VIEW_TITLE_PARAM).append("=").append(viewTitle).append("&")
           .append(VIEW_TYPE_PARAM).append("=").append(viewType).append("\">").append(StringEscapeUtils.escapeHtml4(viewTitle))
           .append("</a>").append("<br/>\n");

    if (depth == 0) {
      builder.append("</font>");
    }

  }

  /** {@inheritDoc} */
  @Override
  protected void buildTitleBarIcon() {
    // This view is Not customizable because it is dynamic
  }

}

package com.sgu.infowksporga.jfx.views.web;

import java.awt.Component;

import javax.swing.SwingUtilities;

import com.sgu.core.framework.gui.swing.GButton;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.panel.GWebPanel;
import com.sgu.core.framework.gui.swing.separator.GSeparator;
import com.sgu.core.framework.gui.swing.util.GlobalGUI;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.AbstractView;
import com.sgu.infowksporga.jfx.views.common.action.AbstractShowViewPropertiesDlgAction;
import com.sgu.infowksporga.jfx.views.web.action.BackwardAction;
import com.sgu.infowksporga.jfx.views.web.action.ForwardAction;
import com.sgu.infowksporga.jfx.views.web.action.HomeAction;
import com.sgu.infowksporga.jfx.views.web.action.OpenInSystemBrowserAction;
import com.sgu.infowksporga.jfx.views.web.action.RefreshAction;
import com.sgu.infowksporga.jfx.views.web.action.StopAction;
import com.sgu.infowksporga.jfx.views.web.action.ZoomInAction;
import com.sgu.infowksporga.jfx.views.web.action.ZoomOutAction;
import com.sgu.infowksporga.jfx.views.web.action.ZoomResetAction;
import com.sgu.infowksporga.jfx.views.web.dlg.properties.ShowWebViewPropertiesDlgAction;

import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;

/**
 * Description : WebView class<br>.
 */
@Getter
@Setter
public class WebView extends AbstractView {

  /** The attribute serialVersionUID. */
  private static final long serialVersionUID = 6674606955595787973L;

  /** Store the reference to the webPanel. */
  private GWebPanel webPanel;

  /** The btn backward. */
  private GButton btnBackward;

  /** The btn home. */
  private GButton btnHome;

  /** The btn forward. */
  private GButton btnForward;

  /** The btn stop. */
  private GButton btnStop;

  /** The btn refresh. */
  private GButton btnRefresh;

  /**
   * Constructor<br>.
   *
   * @param viewIdentifier it's the view Identifier to rebuild view
   */
  public WebView(final WebViewIdentifier viewIdentifier) {
    super("", UtilGUI.getImageIconFromClasspath("/icons/web.png"), viewIdentifier);
  }

  /**
   * Builds the tool bar.
   */
  protected GPanel buildToolBar() {
    final GPanel toolbar = new GPanel(new MigLayout("insets 1 1 2 1"));
    toolbar.setBackground(GlobalGUI.GREY_BACKGROUND_COLOR);

    btnBackward = UtilGUI.createToolbarButtonFomAction(new BackwardAction(this));
    btnHome = UtilGUI.createToolbarButtonFomAction(new HomeAction(this));
    btnForward = UtilGUI.createToolbarButtonFomAction(new ForwardAction(this));
    btnStop = UtilGUI.createToolbarButtonFomAction(new StopAction(this));
    btnRefresh = UtilGUI.createToolbarButtonFomAction(new RefreshAction(this));

    toolbar.add(btnBackward);
    toolbar.add(btnHome);
    toolbar.add(btnForward);

    toolbar.add(new GSeparator(GSeparator.VERTICAL), "growy");

    toolbar.add(btnStop);
    toolbar.add(btnRefresh);

    toolbar.add(new GSeparator(GSeparator.VERTICAL), "growy");

    toolbar.add(UtilGUI.createToolbarButtonFomAction(new ZoomOutAction(this)));
    toolbar.add(UtilGUI.createToolbarButtonFomAction(new ZoomResetAction(this)));
    toolbar.add(UtilGUI.createToolbarButtonFomAction(new ZoomInAction(this)));

    toolbar.add(new GSeparator(GSeparator.VERTICAL), "growy");

    toolbar.add(UtilGUI.createToolbarButtonFomAction(new OpenInSystemBrowserAction(this)));

    return toolbar;

  }

  @Override
  protected AbstractShowViewPropertiesDlgAction buildShowViewPropertiesDlgAction() {
    return new ShowWebViewPropertiesDlgAction(this);
  }

  /**
   * Description : buildViewComponent method <br>.
   *
   * @return the view component
   */
  @Override
  protected Component buildViewComponent() {
    final WebView _this = this;
    webPanel = new GWebPanel() {

      /** {@inheritDoc} */
      @Override
      protected void buildToolbar() {
        final GPanel toolbar = _this.buildToolBar();
        this.add(toolbar, "growx, gap 0");
      }
    };

    return webPanel;
  }

  /**
   * Apply view configuration.
   */
  @Override
  public void applyViewConfiguration() {
    if (viewIdentifier != null) {

      // For the moment we assume view are not base on a master
      final View viewEntity = getWorkspacesViewsDto().getWorkspaceViewsByName().get(viewIdentifier.getViewEntityName());

      //--------------------------------------
      applyCommonViewConfiguration(viewEntity);

      // Manage URL to display
      final String webUrlStr = viewEntity.getAttributesAsMap().get(ViewAttribute.WEB_URL).getValue();
      if (UtilString.isNotBlank(webUrlStr)) {
        webPanel.setContentURL(webUrlStr);
      }
    }
  }

  /**
   * Gets the browser view.
   *
   * @return the browser view
   */
  public BrowserView getBrowserView() {
    return webPanel.getBrowserView();
  }

  /** {@inheritDoc} */
  @Override
  public void createListeners() {
    super.createListeners();

    webPanel.getBrowserView().getBrowser().addLoadListener(new LoadAdapter() {
      @Override
      public void onStartLoadingFrame(final StartLoadingEvent event) {
        if (event.isMainFrame()) {
          SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              btnRefresh.setEnabled(false);
              btnStop.setEnabled(true);
            }
          });
        }
      }

      @Override
      public void onProvisionalLoadingFrame(final ProvisionalLoadingEvent event) {
        if (event.isMainFrame()) {
          SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              GUISessionProxy.getInfoWrkspOrgaFrame().getStatusBar().getLblMessage().setText(event.getURL());
            }
          });
        }
      }

      @Override
      public void onFinishLoadingFrame(final FinishLoadingEvent event) {
        if (event.isMainFrame()) {
          SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
              btnRefresh.setEnabled(true);
              btnStop.setEnabled(false);

              final Browser browser = event.getBrowser();
              final boolean canGoForward = browser.canGoForward();
              final boolean canGoBack = browser.canGoBack();
              SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                  btnForward.setEnabled(canGoForward);
                  btnBackward.setEnabled(canGoBack);
                }
              });
            }
          });
        }
      }
    });

  }

}

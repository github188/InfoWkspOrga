package com.sgu.infowksporga.jfx.views.html;

import java.awt.Component;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.net.URISyntaxException;

import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import com.jidesoft.swing.Searchable;
import com.jidesoft.swing.SearchableBar;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.GButton;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.GToggleButton;
import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;
import com.sgu.core.framework.gui.swing.editor.html.GHTMLEditorPane;
import com.sgu.core.framework.gui.swing.editor.scaled.GEditorPaneZoom;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.scrollpane.GScrollPane;
import com.sgu.core.framework.gui.swing.search.GSearchableBar;
import com.sgu.core.framework.gui.swing.search.GTextComponentSearchable;
import com.sgu.core.framework.gui.swing.separator.GSeparator;
import com.sgu.core.framework.gui.swing.textfield.GNumericField;
import com.sgu.core.framework.gui.swing.util.GlobalGUI;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.views.AbstractView;
import com.sgu.infowksporga.jfx.views.common.action.AbstractShowViewPropertiesDlgAction;
import com.sgu.infowksporga.jfx.views.html.action.EditHtmlAction;
import com.sgu.infowksporga.jfx.views.html.action.FindAction;
import com.sgu.infowksporga.jfx.views.html.action.ZoomInAction;
import com.sgu.infowksporga.jfx.views.html.action.ZoomOutAction;
import com.sgu.infowksporga.jfx.views.html.dlg.properties.ShowHtmlViewPropertiesDlgAction;

import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;

/**
 * Description : HtmlView class<br>.
 */
@Getter
@Setter
public class HtmlView extends AbstractView {

  /** The attribute serialVersionUID. */
  private static final long serialVersionUID = 6674606955595787973L;

  /** Store the reference to the htmlPanel. */
  private GPanel htmlPanel;

  /** Store the html content to display and to zoom on it. */
  protected GEditorPaneZoom htmlContent;

  /** The html editor pane. */
  protected GHTMLEditorPane htmlEditorPane;

  /** keep ref. */
  private GScrollPane htmlContentScrollPane;

  /** The html editor pane scroll pane. */
  private GScrollPane htmlEditorPaneScrollPane;

  /** menu bar to edit HTML. */
  private JMenuBar menuBar;

  /** Contient le ratio actuel d'affichage (zoom). */
  protected GNumericField txtZoom;

  /** ref. */
  private GButton btnOpenViewProperties;

  /** The btn edit html. */
  private GToggleButton btnEditHTML;

  /** The searchable bar. */
  private GSearchableBar searchableBar;

  /**
   * Constructor<br>.
   *
   * @param configuration it's the view configuration
   */
  public HtmlView(final HtmlViewIdentifier configuration) {
    super("", UtilGUI.getImageIconFromClasspath("/icons/html.png"), configuration);
  }

  /** {@inheritDoc} */
  @Override
  protected AbstractShowViewPropertiesDlgAction buildShowViewPropertiesDlgAction() {
    return new ShowHtmlViewPropertiesDlgAction(this);
  }

  /**
   * Description : buildViewComponent method <br>.
   *
   * @return the Directory view component
   */
  @Override
  protected Component buildViewComponent() {
    htmlPanel = new GPanel();
    final MigLayout layout = new MigLayout("fill, insets 0, flowy, gap 0, hidemode 1");
    htmlPanel.setLayout(layout);

    buildToolBar();
    buildContentPane();
    buildSearchableBar();

    return htmlPanel;
  }

  /**
   * Builds the searchable bar.
   */
  protected void buildSearchableBar() {
    // Attached the searchable bar
    final Searchable searchable = new GTextComponentSearchable(htmlContent);
    searchable.setRepeats(true);
    searchableBar = (GSearchableBar) GSearchableBar.install(searchable, KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK),
                                                            new SearchableBar.Installer() {
                                                              @Override
                                                              public void openSearchBar(final SearchableBar searchableBar) {
                                                                final String selectedText = htmlContent.getSelectedText();
                                                                if (selectedText != null && selectedText.length() > 0) {
                                                                  searchableBar.setSearchingText(selectedText);
                                                                }
                                                                searchableBar.setVisible(true);
                                                                invalidate();
                                                                revalidate();
                                                              }

                                                              @Override
                                                              public void closeSearchBar(final SearchableBar searchableBar) {
                                                                searchableBar.setVisible(false);
                                                                invalidate();
                                                                revalidate();
                                                              }
                                                            });
    searchableBar.setShowMatchCount(true);
    searchableBar.setMaxHistoryLength(10);

    htmlPanel.add(searchableBar, "growx");
    searchableBar.setVisible(false);
  }

  /**
   * Builds the content pane.
   */
  protected void buildContentPane() {
    // Add the htmlContent to panel
    htmlContent = new GEditorPaneZoom();
    htmlContent.setEditable(false);

    htmlContentScrollPane = new GScrollPane(htmlContent);
    htmlPanel.add(htmlContentScrollPane, "grow, height 100%");

    /* to edit HTML */
    htmlEditorPane = new GHTMLEditorPane();
    htmlEditorPaneScrollPane = new GScrollPane(htmlEditorPane);

    final FocusAdapter editorFocus = new FocusAdapter() {
      @Override
      public void focusGained(final FocusEvent e) {
        // build Menu
        menuBar = new JMenuBar();
        menuBar.add(htmlEditorPane.getEditMenu());
        menuBar.add(htmlEditorPane.getFormatMenu());
        menuBar.add(htmlEditorPane.getInsertMenu());
      }

    };
    htmlEditorPane.getWysEditor().addFocusListener(editorFocus);
    htmlEditorPane.getSrcEditor().addFocusListener(editorFocus);

    htmlPanel.add(htmlEditorPaneScrollPane, "grow, height 100%");
    htmlEditorPaneScrollPane.setVisible(false);
  }

  /**
   * Builds the tool bar.
   */
  protected void buildToolBar() {
    final GPanel toolbar = new GPanel(new MigLayout("insets 1 1 2 1"));
    toolbar.setBackground(GlobalGUI.GREY_BACKGROUND_COLOR);

    final GButton btnZoomIn = UtilGUI.createToolbarButtonFomAction(new ZoomInAction(this));
    final GButton btnZoomOut = UtilGUI.createToolbarButtonFomAction(new ZoomOutAction(this));
    final GButton btnFind = UtilGUI.createToolbarButtonFomAction(new FindAction(this));
    btnEditHTML = new EditHtmlAction(this).createToggleButton(false, true);
    btnEditHTML.setPreferredSize(new Dimension(22, 24));

    txtZoom = UtilZoom.getZoomTextField();

    toolbar.add(btnFind, "gapleft 3, gaptop 2");
    toolbar.add(new GSeparator(GSeparator.VERTICAL), "growy");

    toolbar.add(btnZoomOut);
    toolbar.add(txtZoom);
    toolbar.add(new GLabel("%"));
    toolbar.add(btnZoomIn);

    addToolbarEditorButton(toolbar);

    htmlPanel.add(toolbar, "growx, gap 0");

  }

  /**
   * Adds the toolbar editor button.
   *
   * @param toolbar the toolbar
   */
  @SuppressWarnings("unchecked")
  protected void addToolbarEditorButton(final GPanel toolbar) {
    toolbar.add(UtilGUI.createToolbarGroupSeparatorLabel());
    toolbar.add(btnEditHTML);
  }

  /**
   * Description : getDisplayViewPropertiesAction method <br>.
   *
   * @return the display view properties action
   */
  protected ShowHtmlViewPropertiesDlgAction getDisplayViewPropertiesAction() {
    return new ShowHtmlViewPropertiesDlgAction(this);
  }

  /**
   * Gets the view identifier.
   *
   * @return the configuration : See field description
   * @see #viewIdentifier
   */
  @Override
  public HtmlViewIdentifier getViewIdentifier() {
    return (HtmlViewIdentifier) viewIdentifier;
  }

  /**
   * Description : setConfiguration method <br>.
   */

  /**
   * Apply view configuration.
   */
  @Override
  public void applyViewConfiguration() {
    // For the moment we assume view are not base on a master
    final View viewEntity = getViewEntity();

    //--------------------------------------
    applyCommonViewConfiguration(viewEntity);

    // Manage HTML Content to display
    final String htmlContentStr = viewEntity.getAttributesAsMap().get(ViewAttribute.HTML_TEXT).getValue();
    if (UtilString.isNotBlank(htmlContentStr)) {
      htmlContent.setText(htmlContentStr);
    }
    else {
      htmlContent.setText("");
    }

    // Manage Html Zoom
    final String htmlZoomStr = viewEntity.getAttributesAsMap().get(ViewAttribute.HTML_ZOOM).getValue();
    if (UtilString.isNotBlank(htmlZoomStr)) {
      UtilZoom.zoomTo(this, Integer.valueOf(htmlZoomStr));
    }
    else {
      UtilZoom.zoomTo(this, 0);
    }

    htmlContent.setCaretPosition(0);
  }

  /**
   * Gets the view entity.
   *
   * @return the view entity
   */
  public View getViewEntity() {
    return getWorkspacesViewsDto().getWorkspaceViewsByName().get(viewIdentifier.getViewEntityName());
  }

  /** {@inheritDoc} */
  @Override
  public void createListeners() {
    super.createListeners();

    addHyperLinkListener();

    // Zoom with mouse
    final HtmlView htmlView = this;
    htmlContentScrollPane.addMouseWheelListener(new MouseWheelListener() {
      @Override
      public void mouseWheelMoved(final MouseWheelEvent e) {
        if (e.isControlDown()) {
          // If wheel rotation value is a negative it means rotate up, while
          // positive value means rotate down

          if (e.getWheelRotation() < 0) {
            UtilZoom.zoomIn(htmlView, e.getScrollAmount() / 100.0);
          }
          else {
            UtilZoom.zoomOut(htmlView, e.getScrollAmount() / 100.0);
          }
        }
      }
    });

    txtZoom.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(final ActionEvent e) {
        UtilZoom.zoomTo(htmlView, (Integer) txtZoom.getValue());
      }
    });
  }

  /**
   * Description : addHyperLinkListener method <br>.
   */
  protected void addHyperLinkListener() {
    // To enable click when user select an HTML link
    htmlContent.addHyperlinkListener(new HyperlinkListener() {
      @Override
      public void hyperlinkUpdate(final HyperlinkEvent evt) {
        if (evt.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
          if (Desktop.isDesktopSupported()) {
            if (evt.getURL() != null) {
              try {
                UtilInfoWrkspOrga.displayWithDesktopTool(evt.getURL().toURI().toString());
              } catch (final URISyntaxException e) {
                throw new TechnicalException(e);
              }
            }
            else {
              UtilDlgMessage.error(I18nHelperApp.getMessage("error.desktop.open.document", ""), "");
            }

          }
        }
      }
    });
  }

}

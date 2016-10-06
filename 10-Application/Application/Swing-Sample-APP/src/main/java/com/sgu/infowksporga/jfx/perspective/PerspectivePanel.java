package com.sgu.infowksporga.jfx.perspective;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;

import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GButton;
import com.sgu.core.framework.gui.swing.GCheckbox;
import com.sgu.core.framework.gui.swing.GComboBox;
import com.sgu.core.framework.gui.swing.GLabelField;
import com.sgu.core.framework.gui.swing.panel.GGradientPanel;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.scrollpane.GScrollPane;
import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.gui.swing.util.GlobalGUI;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.perspective.action.ClearFilterAction;
import com.sgu.infowksporga.jfx.perspective.action.CollapseAllPerspectiveAction;
import com.sgu.infowksporga.jfx.perspective.action.ExpandAllPerspectiveAction;
import com.sgu.infowksporga.jfx.perspective.action.RefreshPerspectiveAction;
import com.sgu.infowksporga.jfx.perspective.cb.ComboBoxPerspectiveListCellRenderer;
import com.sgu.infowksporga.jfx.perspective.listener.ComboBoxPerspectiveItemListener;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTree;
import com.sgu.infowksporga.jfx.perspective.tree.listener.PerspectiveTreeFilterListener;
import com.sgu.infowksporga.jfx.zfacade.remote.perspective.FindPerspectiveFacade;

import net.miginfocom.swing.MigLayout;

/**
 * Description : Perspective Panel class<br>
 */
public class PerspectivePanel extends AbstractPerspectivePanel {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -6678068285873684387L;

  /**
   * Constructor<br>
   */
  public PerspectivePanel() {
    super();
    buildUI();
  }

  @Override
  public void initUI() {
    super.initUI();

    final MigLayout layout = new MigLayout("fill, insets 2, flowy, gap 0");
    setLayout(layout);
    setBorder(new MatteBorder(0, 1, 1, 1, new Color(182, 188, 204)));
    setPreferredSize(new Dimension(240, 2000));
  }

  @Override
  public void createUI() {
    super.createUI();
    buildPerspective();
  }

  @Override
  public void createListeners() {
    // TODO Auto-generated method stub
    super.createListeners();
  }

  @Override
  public void fillUI() {
    // TODO Auto-generated method stub
    super.fillUI();

    // Fill the Perspective Tree. By default load the perspective define in global configuration,
    // So set null in given parameter
    fillComboBoxPerspective();
  }

  /**
   * Description : buildPerspectiveProject method <br>
   */
  protected void buildPerspective() {

    /* Build the perspective tool bar */
    buildTitleBar();
    buildToolBar();

    buildTree();

  }

  /**
   * Description : fillComboBoxPerspective method <br>
   */
  private void fillComboBoxPerspective() {

    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        final FindPerspectiveFacade facade = SpringBeanHelper.getImplementationByInterface(FindPerspectiveFacade.class);
        GUISession.getInstance().getServiceDelegate().execute(facade, PerspectivePanel.this);
      }
    });

  }

  /**
   * Description : buildTitleBar method <br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "perspective.lbl.title.text", value = "Perspectives"), // Force \n
  })
  private void buildTitleBar() {
    final GGradientPanel titlePanel = new GGradientPanel(new MigLayout("insets 1 1 2 1, fill", "[][grow][]", "[]"));
    titlePanel.setForeground(new Color(170, 205, 105));// Vert clair
    titlePanel.setBackground(new Color(133, 173, 58)); // Vert Foncé

    final GLabelField title = new GLabelField();
    title.setBundleConfiguration("perspective.lbl.title", I18nHelperApp.getI18nHelper());
    title.setFont(UtilGUI.LABEL_BOLD_FONT);

    cbPerspective = new GComboBox();
    cbPerspective.setPreferredSize(new Dimension(90, cbPerspective.getPreferredSize().height));
    cbPerspective.setRenderer(new ComboBoxPerspectiveListCellRenderer());

    cbPerspectiveItemListener = new ComboBoxPerspectiveItemListener();
    cbPerspective.addItemListener(cbPerspectiveItemListener);

    titlePanel.add(title, "gapleft 3");
    titlePanel.add(cbPerspective, "growx");

    // Refresh Action
    RefreshPerspectiveAction refreshAction = new RefreshPerspectiveAction(this);
    titlePanel.add(refreshAction.createToolBarButton(), "gaptop 4, gapbottom 4");

    titlePanel.setSize(new Dimension(200, 50));
    add(titlePanel, "growx, gap 0");

  }

  /**
   * Description : buildToolBar method <br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "perspective.lbl.filter.text", value = "Filtre"), // Force \n
                @I18nProperty(key = "perspective.lbl.filter.tooltip",
                value = "<HTML><BODY>Utilisez '*' pour effectuer une recherche en case insensitive "
                        + "(ex : *rech* => Contient reCh)<br><font color=\"#ff9900\"><b>/!\\\\</b></font> "
                        + "Certains libellés utilisent du HTML, il faut donc utiliser *...* </BODY></HTML>"), // Force \n
                @I18nProperty(key = "perspective.chk.filter.text", value = "Sur les feuilles"), // Force \n
                @I18nProperty(key = "perspective.chk.filter.tooltip",
                value = "Si coché recherche sur les feuilles de l'arbre si non, recherche sur les \"Répertoires\""), // Force \n
  })
  private void buildToolBar() {
    final GPanel toolbar = new GPanel(new MigLayout("insets 1 1 2 1"));

    final CollapseAllPerspectiveAction collapseAction = new CollapseAllPerspectiveAction(this);
    final ExpandAllPerspectiveAction expandAction = new ExpandAllPerspectiveAction(this);
    final GButton collapseButton = collapseAction.createToolBarButton();
    final GButton expandButton = expandAction.createToolBarButton();

    toolbar.add(collapseButton, "gapleft 3");
    toolbar.add(expandButton);

    lblFilter = new GLabelField();
    lblFilter.setBundleConfiguration("perspective.lbl.filter", I18nHelperApp.getI18nHelper());
    lblFilter.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
    lblFilter.setOpaque(false);
    txtFilter = new GTextField(15);

    final ClearFilterAction clearFilterAction = new ClearFilterAction(this);
    final GButton clearFilterButton = clearFilterAction.createToolBarButton();

    chkFilter = new GCheckbox();
    chkFilter.setBundleConfiguration("perspective.chk.filter", I18nHelperApp.getI18nHelper());
    chkFilter.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
    chkFilter.setOpaque(false);
    chkFilter.setSelected(true);

    toolbar.add(lblFilter, "gapleft 2, gaptop 4");
    toolbar.add(txtFilter);
    toolbar.add(clearFilterButton, "");
    toolbar.add(chkFilter);

    final PerspectiveTreeFilterListener perspectiveTreeFilterListener = new PerspectiveTreeFilterListener(this);
    txtFilter.addActionListener(perspectiveTreeFilterListener);
    chkFilter.addActionListener(perspectiveTreeFilterListener);

    toolbar.setBackground(GlobalGUI.GREY_BACKGROUND_COLOR);

    add(toolbar, "growx, gap 0");

  }

  /**
   * Description : buildTree method <br>
   */
  private void buildTree() {
    tree = new PerspectiveTree(null);

    // Lastly, put the Tree into a ScrollPane.
    final GScrollPane scrollpaneTree = new GScrollPane();
    scrollpaneTree.getViewport().add(tree);
    // Add tree to the panel
    add(scrollpaneTree, "grow, height 100%");

  }

}

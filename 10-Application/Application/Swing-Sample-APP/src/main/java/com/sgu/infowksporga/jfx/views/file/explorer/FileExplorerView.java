package com.sgu.infowksporga.jfx.views.file.explorer;

import java.awt.Component;
import java.awt.Insets;
import java.io.File;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;
import javax.swing.border.EmptyBorder;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GButton;
import com.sgu.core.framework.gui.swing.GCheckbox;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.GLabelField;
import com.sgu.core.framework.gui.swing.dnd.ghost.GhostComponentAdapter;
import com.sgu.core.framework.gui.swing.dnd.ghost.GhostMotionAdapter;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.scrollpane.GScrollPane;
import com.sgu.core.framework.gui.swing.separator.GSeparator;
import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.core.framework.gui.swing.util.GlobalGUI;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.business.entity.ViewAttribute;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.AbstractView;
import com.sgu.infowksporga.jfx.views.common.action.AbstractShowViewPropertiesDlgAction;
import com.sgu.infowksporga.jfx.views.file.explorer.action.AddNewDirectoryAction;
import com.sgu.infowksporga.jfx.views.file.explorer.action.ClearFilterAction;
import com.sgu.infowksporga.jfx.views.file.explorer.action.CollapseAllDirectoryAction;
import com.sgu.infowksporga.jfx.views.file.explorer.action.CopyFilesToClipboardAction;
import com.sgu.infowksporga.jfx.views.file.explorer.action.CutFilesToClipboardAction;
import com.sgu.infowksporga.jfx.views.file.explorer.action.ExpandAllDirectoryAction;
import com.sgu.infowksporga.jfx.views.file.explorer.action.OpenFileAction;
import com.sgu.infowksporga.jfx.views.file.explorer.action.PasteFilesAction;
import com.sgu.infowksporga.jfx.views.file.explorer.action.RefreshDirectoryAction;
import com.sgu.infowksporga.jfx.views.file.explorer.action.RemoveFilesToTrashAction;
import com.sgu.infowksporga.jfx.views.file.explorer.action.UnselectNodesAction;
import com.sgu.infowksporga.jfx.views.file.explorer.action.ZipSelectedFilesAction;
import com.sgu.infowksporga.jfx.views.file.explorer.dlg.properties.ShowFileExplorerViewPropertiesDlgAction;
import com.sgu.infowksporga.jfx.views.file.explorer.listener.FileTreeFilterListener;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTree;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTreeModel;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileTreeNode;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.FileVoBuilder;

import net.miginfocom.swing.MigLayout;

/**
 * Description : Directory Content View class<br>
 */
public class FileExplorerView extends AbstractView {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 8462459081322361297L;

  /**
   * collapse action Files
   */
  private CollapseAllDirectoryAction collapseAction;
  /**
   * expand action Files
   */
  private ExpandAllDirectoryAction expandAction;

  /**
   * unselect action Files
   */
  private UnselectNodesAction unselectNodesAction;
  /**
   * Cut action Files
   */
  private CutFilesToClipboardAction cutFilesToClipboardAction;
  /**
   * Copy action files
   */
  private CopyFilesToClipboardAction copyFilesToClipboardAction;
  /**
   * Paste action files
   */
  private PasteFilesAction pasteFilesAction;

  /**
   * Remove files to trash action files
   */
  private RemoveFilesToTrashAction removeFilesToTrashAction;

  /**
   * Zip selected files to disk action files
   */
  private ZipSelectedFilesAction zipSelectedFilesAction;

  /** The clear filter action. */
  private ClearFilterAction clearFilterAction;

  /**
   * The panel contain Components
   */
  private GPanel directoryViewPanel;

  /**
   * Reference to the scrollpane tree
   */
  private GScrollPane scrollpaneTree;

  /**
   * Reference to the file tree
   */
  private FileTree fileTree;

  /**
   * Ref to label filter
   */
  private GLabel lblFilter;

  /**
   * Ref to filter text
   */
  private GTextField txtFilter;

  /**
   * Ref to filter text on leaf or directory
   */
  protected GCheckbox chkFilter;

  /**
   * ref
   */
  protected GButton btnOpenViewProperties;

  /**
   * The Constructor.
   *
   * @param viewIdentifier the view identifier
   */
  public FileExplorerView(final FileExplorerViewIdentifier viewIdentifier) {
    super("", UtilGUI.getImageIconFromClasspath("/icons/folder.png"), viewIdentifier);

  }

  /**
   * Description : buildViewComponent method <br>
   *
   * @return the Directory view component
   */
  @Override
  protected Component buildViewComponent() {
    directoryViewPanel = new GPanel();
    final MigLayout layout = new MigLayout("fill, insets 0, flowy, gap 0");
    directoryViewPanel.setLayout(layout);

    buildToolBar(directoryViewPanel);
    buildFileList(directoryViewPanel);
    initializeTreeActions();

    return directoryViewPanel;
  }

  /**
   * Description : initialize Tree CUT / COPY / PASTE Actions method <br>
   */
  private void initializeTreeActions() {
    final ActionMap map = fileTree.getActionMap();
    final InputMap imap = fileTree.getInputMap();

    map.put(TransferHandler.getCutAction().getValue(Action.NAME), cutFilesToClipboardAction);
    imap.put(KeyStroke.getKeyStroke("control X"), TransferHandler.getCutAction().getValue(Action.NAME));

    map.put(TransferHandler.getCopyAction().getValue(Action.NAME), copyFilesToClipboardAction);
    imap.put(KeyStroke.getKeyStroke("control C"), TransferHandler.getCopyAction().getValue(Action.NAME));

    map.put(TransferHandler.getPasteAction().getValue(Action.NAME), pasteFilesAction);
    imap.put(KeyStroke.getKeyStroke("control V"), TransferHandler.getPasteAction().getValue(Action.NAME));

    map.put("delete", removeFilesToTrashAction);
    imap.put(KeyStroke.getKeyStroke("DELETE"), "delete");

  }

  /**
   * Description : buildToolBar method <br>
   *
   * @param directoryViewPanel the directory View panel
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "file.explorer.view.lbl.filter.text", value = "Filtre"), // Force \n
                @I18nProperty(key = "file.explorer.view.lbl.filter.tooltip",
                value = "Utilisez '*' pour effectuer une recherche en case insensitive (ex : *rech* => Contient reCh)"), // Force \n
                @I18nProperty(key = "file.explorer.view.chk.filter.text", value = "Sur les feuilles"), // Force \n
                @I18nProperty(key = "file.explorer.view.chk.filter.tooltip",
                value = "Si coché recherche sur les feuilles de l'arbre sinon, recherche sur les \"Répertoires\""), // Force \n
  })
  private void buildToolBar(final GPanel directoryViewPanel) {
    final GPanel toolbar = new GPanel(new MigLayout("insets 1 1 2 1"));
    collapseAction = new CollapseAllDirectoryAction(this);
    expandAction = new ExpandAllDirectoryAction(this);
    final OpenFileAction openDirectoryAction = new OpenFileAction(this);
    final RefreshDirectoryAction refreshDirectoryAction = new RefreshDirectoryAction(this);
    unselectNodesAction = new UnselectNodesAction(this);
    cutFilesToClipboardAction = new CutFilesToClipboardAction(this);
    copyFilesToClipboardAction = new CopyFilesToClipboardAction(this);
    pasteFilesAction = new PasteFilesAction(this);

    clearFilterAction = new ClearFilterAction(this);

    removeFilesToTrashAction = new RemoveFilesToTrashAction(this);
    zipSelectedFilesAction = new ZipSelectedFilesAction(this);

    final AddNewDirectoryAction addNewFolderAction = new AddNewDirectoryAction(this);

    final GButton unselectNodesButton = unselectNodesAction.createToolBarButton();
    final GButton cutFilesToClipboardButton = cutFilesToClipboardAction.createToolBarButton();
    final GButton copyFilesToClipboardButton = copyFilesToClipboardAction.createToolBarButton();
    final GButton pasteFilesButton = pasteFilesAction.createToolBarButton();
    final GButton removeFilesToTrashButton = removeFilesToTrashAction.createToolBarButton();
    final GButton zipSelectedFilesButton = zipSelectedFilesAction.createToolBarButton();
    final GButton addNewFolderButton = addNewFolderAction.createToolBarButton();
    final GButton refreshDirectoryButton = refreshDirectoryAction.createToolBarButton();
    final GButton openDirectoryButton = openDirectoryAction.createToolBarButton();
    final GButton collapseButton = collapseAction.createToolBarButton();
    final GButton expandButton = expandAction.createToolBarButton();
    final GButton clearFilterButton = clearFilterAction.createToolBarButton();

    lblFilter = new GLabelField();
    lblFilter.setBundleConfiguration("file.explorer.view.lbl.filter", I18nHelperApp.getI18nHelper());
    lblFilter.addMouseListener(new GhostComponentAdapter(GUISessionProxy.getInfoWrkspOrgaFrame().getGhostGlassPane(), "labelViewTitle"));
    lblFilter.addMouseMotionListener(new GhostMotionAdapter(GUISessionProxy.getInfoWrkspOrgaFrame().getGhostGlassPane()));
    lblFilter.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
    lblFilter.setOpaque(false);

    txtFilter = new GTextField(20);

    chkFilter = new GCheckbox();
    chkFilter.setBundleConfiguration("file.explorer.view.chk.filter", I18nHelperApp.getI18nHelper());
    chkFilter.setBorder(new EmptyBorder(new Insets(0, 0, 0, 0)));
    chkFilter.setOpaque(false);
    chkFilter.setSelected(true);

    final FileTreeFilterListener fileTreeFilterListener = new FileTreeFilterListener(this);
    txtFilter.addActionListener(fileTreeFilterListener);
    chkFilter.addActionListener(fileTreeFilterListener);

    toolbar.add(refreshDirectoryButton, "gapleft 3, gaptop 2");
    toolbar.add(openDirectoryButton, "gapleft 3, gaptop 2");
    toolbar.add(addNewFolderButton, "gaptop 3");
    toolbar.add(collapseButton);
    toolbar.add(expandButton);
    toolbar.add(new GSeparator(GSeparator.VERTICAL), "growy");
    toolbar.add(unselectNodesButton);
    toolbar.add(cutFilesToClipboardButton);
    toolbar.add(copyFilesToClipboardButton);
    toolbar.add(pasteFilesButton);
    toolbar.add(removeFilesToTrashButton);
    toolbar.add(new GSeparator(GSeparator.VERTICAL), "growy");
    toolbar.add(zipSelectedFilesButton);
    toolbar.add(new GSeparator(GSeparator.VERTICAL), "growy");
    toolbar.add(lblFilter, "gapleft 7, gaptop 4");
    toolbar.add(txtFilter);
    toolbar.add(clearFilterButton);
    toolbar.add(chkFilter);

    toolbar.setBackground(GlobalGUI.GREY_BACKGROUND_COLOR);

    directoryViewPanel.add(toolbar, "growx, gap 0");

  }

  /**
   * Description : buildFileList method <br>
   *
   * @param directoryViewPanel the directory View panel
   */
  private void buildFileList(final GPanel directoryViewPanel) {
    fileTree = new FileTree(null);
    fileTree.setEditable(true);

    // Put the Tree into a ScrollPane.
    scrollpaneTree = new GScrollPane();
    scrollpaneTree.getViewport().add(fileTree);

    // Add tree to the panel
    directoryViewPanel.add(scrollpaneTree, "grow, height 100%");

    setConfiguration((FileExplorerViewIdentifier) viewIdentifier);

  }

  /**
   * @see #scrollpaneTree
   * @return the scrollpaneTree : See field description
   */
  public GScrollPane getScrollpaneTree() {
    return scrollpaneTree;
  }

  /**
   * @see #directoryViewPanel
   * @return the directoryViewPanel : See field description
   */
  public GPanel getDirectoryViewPanel() {
    return directoryViewPanel;
  }

  /**
   * Description : set Directory Configuration method <br>
   *
   * @param configuration the configuration to apply to the view
   */
  public void setConfiguration(final FileExplorerViewIdentifier configuration) {
    this.viewIdentifier = configuration;
  }

  /**
   * @see #fileTree
   * @return the fileTree : See field description
   */
  public FileTree getFileTree() {
    return fileTree;
  }

  /**
   * @see #viewIdentifier
   * @return the configuration : See field description
   */
  @Override
  public FileExplorerViewIdentifier getViewIdentifier() {
    return (FileExplorerViewIdentifier) viewIdentifier;
  }

  /**
   * @see #cutFilesToClipboardAction
   * @return the cutFilesToClipboardAction : See field description
   */
  public CutFilesToClipboardAction getCutFilesToClipboardAction() {
    return cutFilesToClipboardAction;
  }

  /**
   * @see #copyFilesToClipboardAction
   * @return the copyFilesToClipboardAction : See field description
   */
  public CopyFilesToClipboardAction getCopyFilesToClipboardAction() {
    return copyFilesToClipboardAction;
  }

  /**
   * @see #pasteFilesAction
   * @return the pasteFilesAction : See field description
   */
  public PasteFilesAction getPasteFilesAction() {
    return pasteFilesAction;
  }

  /**
   * @see #removeFilesToTrashAction
   * @return the removeFilesToTrashAction : See field description
   */
  public RemoveFilesToTrashAction getRemoveFilesToTrashAction() {
    return removeFilesToTrashAction;
  }

  /**
   * @see #collapseAction
   * @return the collapseAction : See field description
   */
  public CollapseAllDirectoryAction getCollapseAction() {
    return collapseAction;
  }

  /**
   * @see #expandAction
   * @return the expandAction : See field description
   */
  public ExpandAllDirectoryAction getExpandAction() {
    return expandAction;
  }

  /**
   * @see #unselectNodesAction
   * @return the unselectNodesAction : See field description
   */
  public UnselectNodesAction getUnselectNodesAction() {
    return unselectNodesAction;
  }

  /**
   * @see #txtFilter
   * @return the txtFilter : See field description
   */
  public GTextField getTxtFilter() {
    return txtFilter;
  }

  /**
   * @see #chkFilter
   * @return the chkFilter : See field description
   */
  public GCheckbox getChkFilter() {
    return chkFilter;
  }

  /**
   * @see #btnOpenViewProperties
   * @return the openDirectoryPropertiesButton : See field description
   */
  public GButton getBtnOpenViewProperties() {
    return btnOpenViewProperties;
  }

  /** {@inheritDoc} */
  @Override
  protected AbstractShowViewPropertiesDlgAction buildShowViewPropertiesDlgAction() {
    return new ShowFileExplorerViewPropertiesDlgAction(this);
  }

  /**
   * Apply view configuration.
   */
  @Override
  public void applyViewConfiguration() {
    if (viewIdentifier != null) {

      // For the moment we assume view are not base on a master
      View viewEntity = getWorkspacesViewsDto().getWorkspaceViewsByName().get(viewIdentifier.getViewEntityName());

      //----------------------------------------
      applyCommonViewConfiguration(viewEntity);
      //----------------------------------------

      // Manage Folder content to display
      final String directoryTarget = viewEntity.getAttributesAsMap().get(ViewAttribute.FILE_EXPLORER_PATH).getValue();

      FileTreeNode root = null;
      if (UtilString.isNotBlank(directoryTarget)) {
        root = new FileTreeNode(FileVoBuilder.buildFileVo(new File(directoryTarget)));
        fileTree.setBackground(GlobalGUI.defaultColor);
      }
      else {
        fileTree.setBackground(GlobalGUI.errorColor);
      }

      final FileTreeModel treeModel = new FileTreeModel(root);
      fileTree.setModel(treeModel);

      // initialize node content
      if (root != null) {
        fileTree.loadAllFileRecursively(root, false);
      }
      treeModel.reload();

    }
  }

}

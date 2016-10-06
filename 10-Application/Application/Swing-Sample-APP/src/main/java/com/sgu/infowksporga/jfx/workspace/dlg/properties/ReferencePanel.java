package com.sgu.infowksporga.jfx.workspace.dlg.properties;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GComboBox;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.GLabelField;
import com.sgu.core.framework.gui.swing.IBuilderUI;
import com.sgu.core.framework.gui.swing.IDisplayMode;
import com.sgu.core.framework.gui.swing.border.GTitledBorder;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.tree.GTreeNode;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.Project;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.entity.enumeration.WorkspaceTypeEnum;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.tree.node.PerspectiveTreeNode;
import com.sgu.infowksporga.jfx.perspective.tree.nodevo.WorkspaceNodeVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.workspace.dlg.properties.cbbox.ComboBoxWorkspaceRenderer;

import lombok.Getter;
import net.miginfocom.swing.MigLayout;

@Getter
public class ReferencePanel extends GPanel implements IBuilderUI, IDisplayMode {

  /** The workspace data to display and to bind with components . */
  private Workspace workspaceIn;

  private GComboBox cbBoxParentWorkspace;
  private GComboBox cbBoxMasterWorkspace;
  private GComboBox cbBoxProject;
  private GComboBox cbBoxType;

  /**
   * Store the display mode
   */
  private int displayMode;

  /**
   * Create the panel.
   */
  public ReferencePanel(Workspace workspaceIn) {
    this.workspaceIn = workspaceIn;
    buildUI();
  }

  @Override
  public void buildUI() {
    initUI();
    createUI();
    fillUI();
    bindComponentsWithPojo();
    createListeners();
  }

  /** {@inheritDoc} */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "workspace.properties.dlg.reference.fieldset.text",
                value = "<html><b>R\\u00E9f\\u00E9rence</b></html>"), // Force /n 
  })
  public void initUI() {
    setBorder(new GTitledBorder(UIManager.getBorder("TitledBorder.border"), "workspace.properties.dlg.reference.fieldset.text",
                                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    setLayout(new MigLayout("insets 0 0 0 0", "[46px][grow][][grow]", "[20px][grow]"));
  }

  /** {@inheritDoc} */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "workspace.properties.dlg.reference.workspace.parent.text", value = "Workspace parent"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.reference.workspace.parent.mandatory", value = "true"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.reference.workspace.master.text", value = "Workspace hérité"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.reference.project.text", value = "Projet"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.reference.type.text", value = "Type"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.reference.type.mandatory", value = "true"), // Force /n 
  })
  public void createUI() {
    GLabel lblParentWorkspace = new GLabelField();
    lblParentWorkspace.setBundleConfiguration("workspace.properties.dlg.reference.workspace.parent", I18nHelperApp.getI18nHelper());
    add(lblParentWorkspace, "cell 0 0,alignx left,aligny center");

    cbBoxParentWorkspace = new GComboBox();
    ComboBoxWorkspaceRenderer cbBoxParentWorkspaceRenderer = new ComboBoxWorkspaceRenderer(cbBoxParentWorkspace);
    cbBoxParentWorkspace.setRenderer(cbBoxParentWorkspaceRenderer);
    add(cbBoxParentWorkspace, "cell 1 0,alignx left,aligny top,grow");

    GLabel lblMasterWorkspace = new GLabelField();
    lblMasterWorkspace.setBundleConfiguration("workspace.properties.dlg.reference.workspace.master", I18nHelperApp.getI18nHelper());
    add(lblMasterWorkspace, "cell 2 0");

    cbBoxMasterWorkspace = new GComboBox();
    ComboBoxWorkspaceRenderer cbBoxMasterWorkspaceRenderer = new ComboBoxWorkspaceRenderer(cbBoxMasterWorkspace);
    cbBoxMasterWorkspace.setRenderer(cbBoxMasterWorkspaceRenderer);
    add(cbBoxMasterWorkspace, "cell 3 0,grow");

    GLabel lblProject = new GLabelField();
    lblProject.setBundleConfiguration("workspace.properties.dlg.reference.project", I18nHelperApp.getI18nHelper());
    add(lblProject, "cell 0 1");

    cbBoxProject = new GComboBox();
    add(cbBoxProject, "cell 1 1,growx");

    GLabel lblType = new GLabelField();
    lblType.setBundleConfiguration("workspace.properties.dlg.reference.type", I18nHelperApp.getI18nHelper());
    add(lblType, "cell 2 1");

    cbBoxType = new GComboBox();
    add(cbBoxType, "cell 3 1,growx");
  }

  @Override
  public void fillUI() {
    // Initialize combo box values
    cbBoxType.addItem(" ");
    List<String> wrkspTypeLst = WorkspaceTypeEnum.getValuesAsList();
    for (String wrkspType : wrkspTypeLst) {
      cbBoxType.addItem(wrkspType);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    if (workspaceIn.getType() != null) {
      cbBoxType.setSelectedItem(workspaceIn.getType().getValue());
    }
    else {
      cbBoxType.setSelectedItem(0); // " "
    }

    if (workspaceIn.getParent() != null) {
      cbBoxParentWorkspace.setSelectedItem(workspaceIn.getParent());
    }
  }

  /** {@inheritDoc} */
  @Override
  public void bindPojoWithComponents(Object out) {
    Workspace workspaceOut = (Workspace) out;

    String selectedType = (String) cbBoxType.getSelectedItem();
    if (UtilString.isNotBlank(selectedType)) {
      workspaceOut.setType(WorkspaceTypeEnum.getEnumForValue(selectedType));
    }
    else {
      workspaceOut.setType(null);
    }

    Object selectedParent = cbBoxParentWorkspace.getSelectedItem();
    if (selectedParent != null && selectedParent instanceof Workspace) {
      workspaceOut.setParent((Workspace) selectedParent);
    }
    else {
      workspaceOut.setParent(null);
    }

    Object selectedMaster = cbBoxMasterWorkspace.getSelectedItem();
    if (selectedMaster != null && selectedMaster instanceof Workspace) {
      workspaceOut.setMaster((Workspace) selectedMaster);
    }
    else {
      workspaceOut.setMaster(null);
    }

    Object selectedProject = cbBoxProject.getSelectedItem();
    if (selectedProject != null && selectedProject instanceof Project) {
      workspaceOut.setProject((Project) selectedProject);
    }
    else {
      workspaceOut.setProject(null);
    }

  }

  @Override
  public void createListeners() {
    // TODO Auto-generated method stub

  }

  /** {@inheritDoc} */
  @Override
  public void setDisplayMode(int mode) {
    displayMode = mode;
    final PerspectivePanel perspective = (PerspectivePanel) GUISessionProxy.getCurrentPerspective();
    final WorkspaceNodeVo currentWorkspaceNodeVo = (WorkspaceNodeVo) perspective.getTree().getLastSelectedUserObject();
    final Workspace currentWorkspace = currentWorkspaceNodeVo.getWorkspace();

    switch (mode) {
      case MODE_CREATE:
      case MODE_COPY:
        cbBoxParentWorkspace.removeAllItems();
        cbBoxParentWorkspace.addItem(currentWorkspace);
        cbBoxParentWorkspace.setSelectedItem(currentWorkspace); // by default select the tree selected item
        break;

      case MODE_UPDATE:
        List<GTreeNode> flatTreeNodeList = new ArrayList<GTreeNode>();
        perspective.getTree().getFlatTreeNodeRecursively((PerspectiveTreeNode) perspective.getTree().getModel().getRoot(),
                                                         flatTreeNodeList);
        for (GTreeNode gTreeNode : flatTreeNodeList) {
          PerspectiveTreeNode pTreeNode = (PerspectiveTreeNode) gTreeNode;
          // In update mode we can't select the selected node as parent
          if (pTreeNode.getWorkspace().getId().equals(currentWorkspace.getId()) == false) {
            cbBoxParentWorkspace.addItem(pTreeNode.getWorkspace());
          }
        }

        cbBoxParentWorkspace.setSelectedItem(currentWorkspace.getParent());

        break;

      default:
        break;
    }
  }

}

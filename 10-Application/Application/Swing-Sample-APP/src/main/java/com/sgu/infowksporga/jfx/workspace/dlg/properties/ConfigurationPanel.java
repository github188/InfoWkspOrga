package com.sgu.infowksporga.jfx.workspace.dlg.properties;

import java.awt.Color;
import java.util.List;

import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GCheckbox;
import com.sgu.core.framework.gui.swing.GComboBox;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.GLabelField;
import com.sgu.core.framework.gui.swing.IBuilderUI;
import com.sgu.core.framework.gui.swing.IDisplayMode;
import com.sgu.core.framework.gui.swing.border.GTitledBorder;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import lombok.Getter;
import net.miginfocom.swing.MigLayout;

@Getter
public class ConfigurationPanel extends GPanel implements IBuilderUI, IDisplayMode {

  /** The workspace data to display and to bind with components . */
  private Workspace workspaceIn;

  private GCheckbox chkbxEnabled;
  private GCheckbox chkbxChildrenWorkspaceEnabled;
  private GComboBox cbBoxPartage;
  private GTextField txtBaseFolder;
  private GTextField txtCategory;
  private GTextField txtTags;
  private GTextField txtCustomer;

  /**
   * Create the panel.
   */
  public ConfigurationPanel(Workspace workspaceIn) {
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

  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "workspace.properties.dlg.configuration.fieldset.text", value = "<html><b>Configuration</b></html>"), // Force /n 
  })
  public void initUI() {
    setBorder(new GTitledBorder(UIManager.getBorder("TitledBorder.border"), "workspace.properties.dlg.configuration.fieldset.text",
                                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    setLayout(new MigLayout("insets 0 0 0 0", "[46px][grow][][grow][grow]", "[][20px][][]"));

  }

  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "workspace.properties.dlg.configuration.enabled.text", value = "Enabled"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.configuration.children.workspace.enabled.text",
                value = "Children workspace enabled"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.configuration.folder.root.text", value = "Répertoire root"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.configuration.category.text", value = "Category"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.configuration.tags.text", value = "Etiquettes"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.configuration.customer.text", value = "Client"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.configuration.partage.text", value = "Partage"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.configuration.partage.mandatory", value = "true"), // Force /n 
  })
  public void createUI() {
    chkbxEnabled = new GCheckbox();
    chkbxEnabled.setBundleConfiguration("workspace.properties.dlg.configuration.enabled", I18nHelperApp.getI18nHelper());
    add(chkbxEnabled, "cell 0 0");

    chkbxChildrenWorkspaceEnabled = new GCheckbox();
    chkbxChildrenWorkspaceEnabled.setBundleConfiguration("workspace.properties.dlg.configuration.children.workspace.enabled",
                                                         I18nHelperApp.getI18nHelper());
    add(chkbxChildrenWorkspaceEnabled, "flowx,cell 1 0 2 1");

    cbBoxPartage = new GComboBox();
    add(cbBoxPartage, "cell 3 0 2 1,growx");

    GLabel lblBaseFolder = new GLabelField();
    lblBaseFolder.setBundleConfiguration("workspace.properties.dlg.configuration.folder.root", I18nHelperApp.getI18nHelper());
    add(lblBaseFolder, "cell 0 1,alignx left,aligny center");

    txtBaseFolder = new GTextField();
    txtBaseFolder.setColumns(10);
    add(txtBaseFolder, "cell 1 1 4 1,growx");

    GLabel lblCategory = new GLabelField();
    lblCategory.setBundleConfiguration("workspace.properties.dlg.configuration.category", I18nHelperApp.getI18nHelper());
    add(lblCategory, "cell 0 2,alignx trailing,aligny center");

    txtCategory = new GTextField();
    txtCategory.setColumns(10);
    add(txtCategory, "cell 1 2,growx");

    GLabel lblTags = new GLabelField("Etiquettes");
    lblTags.setBundleConfiguration("workspace.properties.dlg.configuration.tags", I18nHelperApp.getI18nHelper());
    add(lblTags, "cell 2 2,alignx trailing");

    txtTags = new GTextField();
    add(txtTags, "cell 3 2 2 1,growx");
    txtTags.setColumns(10);

    GLabel lblCustomer = new GLabelField("Customer");
    lblCustomer.setBundleConfiguration("workspace.properties.dlg.configuration.customer", I18nHelperApp.getI18nHelper());
    add(lblCustomer, "cell 0 3,alignx trailing");

    txtCustomer = new GTextField();
    add(txtCustomer, "cell 1 3 2 1,growx");
    txtCustomer.setColumns(10);

    GLabel lblPartage = new GLabelField();
    lblPartage.setBundleConfiguration("workspace.properties.dlg.configuration.partage", I18nHelperApp.getI18nHelper());
    add(lblPartage, "cell 2 0,alignx trailing");
  }

  @Override
  public void fillUI() {

    // Initialize combo box values
    cbBoxPartage.addItem(" ");
    List<String> partageLst = PartageEnum.getValuesAsList();
    for (String partage : partageLst) {
      cbBoxPartage.addItem(partage);
    }

  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    chkbxEnabled.setSelected(workspaceIn.isEnabled());
    chkbxChildrenWorkspaceEnabled.setSelected(workspaceIn.isChildrenWrkspCreationEnabled());

    txtBaseFolder.setText(workspaceIn.getBaseFolder());
    txtCategory.setText(workspaceIn.getCategory());
    txtCustomer.setText(workspaceIn.getCustomer());
    txtTags.setText(workspaceIn.getTags());

    cbBoxPartage.setSelectedItem(workspaceIn.getPartage().getValue());
  }

  /** {@inheritDoc} */
  @Override
  public void bindPojoWithComponents(Object out) {
    Workspace workspaceOut = (Workspace) out;

    workspaceOut.setBaseFolder(txtBaseFolder.getText());
    workspaceOut.setCategory(txtCategory.getText());
    workspaceOut.setCustomer(txtCustomer.getText());
    workspaceOut.setTags(txtTags.getText());

    workspaceOut.setEnabled(chkbxEnabled.isSelected());
    workspaceOut.setChildrenWrkspCreationEnabled(chkbxChildrenWorkspaceEnabled.isSelected());

    String selectedPartage = (String) cbBoxPartage.getSelectedItem();
    if (UtilString.isNoneBlank(selectedPartage)) {
      workspaceOut.setPartage(PartageEnum.getEnumForValue(selectedPartage));
    }
    else {
      workspaceOut.setPartage(null);
    }
  }

  @Override
  public void createListeners() {
    // TODO Auto-generated method stub

  }

  /** {@inheritDoc} */
  @Override
  public void setDisplayMode(int mode) {
    // TODO Auto-generated method stub

  }

}

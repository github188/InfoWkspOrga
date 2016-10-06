package com.sgu.infowksporga.jfx.workspace.dlg.properties;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GComboBox;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.GLabelField;
import com.sgu.core.framework.gui.swing.GTextArea;
import com.sgu.core.framework.gui.swing.IBuilderUI;
import com.sgu.core.framework.gui.swing.IDisplayMode;
import com.sgu.core.framework.gui.swing.border.GTitledBorder;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.scrollpane.GScrollPane;
import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import lombok.Getter;
import net.miginfocom.swing.MigLayout;

@Getter
public class IdentityCardPanel extends GPanel implements IBuilderUI, IDisplayMode {

  /** The workspace data to display and to bind with components . */
  private Workspace workspaceIn;

  private JTextArea txtAreaDescription;

  private JTextField txtName;

  private GLabel lblIdValue;
  private JComboBox cbBoxOwner;

  /**
   * Create the panel.
   */
  public IdentityCardPanel(Workspace workspaceIn) {
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
                @I18nProperty(key = "workspace.properties.dlg.identidy.card.fieldset.text",
                value = "<html><b>Carte d'identit\u00E9</b></html>"), // Force /n 
  })
  public void initUI() {
    setBorder(new GTitledBorder(UIManager.getBorder("TitledBorder.border"), "workspace.properties.dlg.identidy.card.fieldset.text",
                                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    setLayout(new MigLayout("insets 0 0 0 0", "[46px][grow][][grow]", "[20px][][50px][]"));
  }

  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "workspace.properties.dlg.identity.card.id.text", value = "Id"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.identity.card.name.text", value = "Nom"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.identity.card.name.mandatory", value = "true"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.identity.card.owner.text", value = "Propriétaire"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.identity.card.owner.mandatory", value = "true"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.identity.card.description.text", value = "Description"), // Force /n 
  })
  public void createUI() {
    GLabel lblId = new GLabelField();
    lblId.setBundleConfiguration("workspace.properties.dlg.identity.card.id", I18nHelperApp.getI18nHelper());
    add(lblId, "cell 0 0,alignx right,aligny center");

    lblIdValue = new GLabel();
    lblIdValue.setFont(new Font("Tahoma", Font.BOLD, 11));
    add(lblIdValue, "cell 1 0");

    GLabel lblName = new GLabelField();
    lblName.setBundleConfiguration("workspace.properties.dlg.identity.card.name", I18nHelperApp.getI18nHelper());
    add(lblName, "cell 0 1,alignx right");

    txtName = new GTextField();
    add(txtName, "cell 1 1,growx,alignx left,aligny top");
    txtName.setColumns(10);

    GLabel lblOwner = new GLabelField("Owner");
    lblOwner.setBundleConfiguration("workspace.properties.dlg.identity.card.owner", I18nHelperApp.getI18nHelper());
    add(lblOwner, "flowx,cell 2 1,alignx trailing");

    cbBoxOwner = new GComboBox();
    add(cbBoxOwner, "cell 3 1,growx");

    GLabel lblDescription = new GLabelField("Description");
    lblDescription.setBundleConfiguration("workspace.properties.dlg.identity.card.description", I18nHelperApp.getI18nHelper());
    add(lblDescription, "cell 0 2 1 2,alignx right,aligny top");

    GScrollPane scrlpDescription = new GScrollPane();
    add(scrlpDescription, "cell 1 2 3 2,width 510px, grow");

    txtAreaDescription = new GTextArea();
    txtAreaDescription.setWrapStyleWord(true);
    scrlpDescription.setViewportView(txtAreaDescription);

  }

  @Override
  public void fillUI() {

    // Add all User to owner combobox
    // TODO search for all user add completion to combobox
    cbBoxOwner.addItem(" ");
    cbBoxOwner.addItem("sguisse");
    cbBoxOwner.addItem("cdelrue");
    cbBoxOwner.addItem("ango-xuan-coi");
    cbBoxOwner.addItem("hsouala");
    cbBoxOwner.addItem("pcieslar");
    cbBoxOwner.addItem("mnezarri");
    cbBoxOwner.addItem("mmoiroux");
    cbBoxOwner.addItem("jcatrix");

  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    lblIdValue.setText(workspaceIn.getId());
    txtName.setText(workspaceIn.getName());
    txtAreaDescription.setText(workspaceIn.getDescription());

    if (workspaceIn.getOwner() != null) {
      cbBoxOwner.getModel().setSelectedItem(workspaceIn.getOwner());
    }
    else {
      cbBoxOwner.getModel().setSelectedItem(0);
    }

  }

  /** {@inheritDoc} */
  @Override
  public void bindPojoWithComponents(Object out) {
    Workspace workspaceOut = (Workspace) out;

    workspaceOut.setId(lblIdValue.getText());
    workspaceOut.setName(txtName.getText());
    workspaceOut.setDescription(txtAreaDescription.getText());
    workspaceOut.setOwner((String) cbBoxOwner.getModel().getSelectedItem());
  }

  @Override
  public void createListeners() {
    // TODO Auto-generated method stub

  }

  /** {@inheritDoc} */
  @Override
  public void setDisplayMode(int mode) {
    switch (mode) {
      case MODE_CREATE:
      case MODE_COPY:
      case MODE_CONSULT:
      case MODE_UPDATE:
      case MODE_DELETE:
        break;

      default:
        break;

    }
  }

}

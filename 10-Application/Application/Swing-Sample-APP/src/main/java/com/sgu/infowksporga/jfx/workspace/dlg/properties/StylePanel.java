package com.sgu.infowksporga.jfx.workspace.dlg.properties;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.swing.GCheckbox;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.GLabelField;
import com.sgu.core.framework.gui.swing.IBuilderUI;
import com.sgu.core.framework.gui.swing.IDisplayMode;
import com.sgu.core.framework.gui.swing.border.GTitledBorder;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import lombok.Getter;
import net.miginfocom.swing.MigLayout;

@Getter
public class StylePanel extends GPanel implements IBuilderUI, IDisplayMode {

  /** The workspace data to display and to bind with components . */
  private Workspace workspaceIn;

  private GTextField txtColor;
  private GTextField txtIcon;
  private GLabel lblIconTest;

  private GCheckbox chkbxBold;
  private GCheckbox chkbxItalic;
  private GCheckbox chkbxStrike;
  private GCheckbox chkbxUnderline;

  /**
   * Create the panel.
   */
  public StylePanel(Workspace workspaceIn) {
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
                @I18nProperty(key = "workspace.properties.dlg.style.fieldset.text", value = "<html><b>Style</b></html>"), // Force /n 
  })
  public void initUI() {
    setBorder(new GTitledBorder(UIManager.getBorder("TitledBorder.border"), "workspace.properties.dlg.style.fieldset.text",
                                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
    setLayout(new MigLayout("insets 0 0 0 0", "[][grow]", "[grow][grow][]"));
  }

  /** {@inheritDoc} */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "workspace.properties.dlg.style.bold.text", value = "Gras"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.style.italic.text", value = "Italique"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.style.strike.text", value = "Barré"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.style.underline.text", value = "Souligné"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.style.color.text", value = "Couleur"), // Force /n 
                @I18nProperty(key = "workspace.properties.dlg.style.icon.text", value = "Icône"), // Force /n 
  })
  public void createUI() {

    GPanel pnlStyle = new GPanel();
    FlowLayout pnlStyleLayout = (FlowLayout) pnlStyle.getLayout();
    pnlStyleLayout.setHgap(10);
    pnlStyleLayout.setVgap(0);
    add(pnlStyle, "flowx,cell 1 0,alignx center");

    chkbxBold = new GCheckbox();
    chkbxBold.setBundleConfiguration("workspace.properties.dlg.style.bold", I18nHelperApp.getI18nHelper());
    pnlStyle.add(chkbxBold);
    chkbxBold.setHorizontalAlignment(SwingConstants.CENTER);

    chkbxItalic = new GCheckbox();
    chkbxItalic.setBundleConfiguration("workspace.properties.dlg.style.italic", I18nHelperApp.getI18nHelper());
    pnlStyle.add(chkbxItalic);
    chkbxItalic.setHorizontalAlignment(SwingConstants.CENTER);

    chkbxStrike = new GCheckbox();
    chkbxStrike.setBundleConfiguration("workspace.properties.dlg.style.strike", I18nHelperApp.getI18nHelper());
    pnlStyle.add(chkbxStrike);

    chkbxUnderline = new GCheckbox();
    chkbxUnderline.setBundleConfiguration("workspace.properties.dlg.style.underline", I18nHelperApp.getI18nHelper());
    pnlStyle.add(chkbxUnderline);
    chkbxUnderline.setHorizontalAlignment(SwingConstants.CENTER);

    GLabel lblColor = new GLabelField();
    lblColor.setBundleConfiguration("workspace.properties.dlg.style.color", I18nHelperApp.getI18nHelper());
    add(lblColor, "cell 0 1,alignx right");

    txtColor = new GTextField();
    txtColor.setText("Color");
    add(txtColor, "cell 1 1,growx");
    txtColor.setColumns(10);

    GLabel lblIcon = new GLabelField();
    lblIcon.setBundleConfiguration("workspace.properties.dlg.style.icon", I18nHelperApp.getI18nHelper());
    add(lblIcon, "cell 0 2,alignx trailing");

    txtIcon = new GTextField();
    txtIcon.setText("Icon");
    add(txtIcon, "flowx,cell 1 2,growx");
    txtIcon.setColumns(10);

    lblIconTest = new GLabel();
    add(lblIconTest, "cell 1 2");

  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {

  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    chkbxStrike.setSelected(workspaceIn.isStrike());
    chkbxItalic.setSelected(workspaceIn.isItalic());
    chkbxBold.setSelected(workspaceIn.isBold());
    chkbxUnderline.setSelected(workspaceIn.isUnderline());

    txtColor.setText(workspaceIn.getColor());
    if (workspaceIn.getColor() != null) {
      defineTxtColorBackground(workspaceIn.getColor());
    }

    txtIcon.setText(workspaceIn.getIcon());
    if (workspaceIn.getIcon() != null) {
      lblIconTest.setIcon(UtilGUI.getImageIcon(workspaceIn.getIcon()));
    }
  }

  /**
   * Define txt color background.
   */
  private void defineTxtColorBackground(String color) {
    try {
      if (color.startsWith("rgb")) {
        String rgbColor = UtilString.replaceString(color, "rgb(", "");
        rgbColor = UtilString.replaceString(rgbColor, ")", "");
        String[] rgbSplit = rgbColor.split(",");
        txtColor.setBackground(new Color(Integer.valueOf(rgbSplit[0].trim()), Integer.valueOf(rgbSplit[1].trim()),
                                         Integer.valueOf(rgbSplit[2].trim())));
      }
      else if (color.startsWith("#")) {
        txtColor.setBackground(Color.decode(color));
      }
      else {
        throw new TechnicalException("Color format unknown !!");
      }
    } catch (Exception e) {
      txtColor.setBackground(Color.white);
    }
  }

  /** {@inheritDoc} */
  @Override
  public void bindPojoWithComponents(Object out) {
    Workspace workspaceOut = (Workspace) out;

    workspaceOut.setStrike(chkbxStrike.isSelected());
    workspaceOut.setBold(chkbxBold.isSelected());
    workspaceOut.setItalic(chkbxItalic.isSelected());
    workspaceOut.setUnderline(chkbxUnderline.isSelected());

    workspaceOut.setColor(txtColor.getText());
    workspaceOut.setIcon(txtIcon.getText());

  }

  @Override
  public void createListeners() {

    txtIcon.addFocusListener(new FocusAdapter() {

      /** {@inheritDoc} */
      @Override
      public void focusLost(FocusEvent e) {
        lblIconTest.setIcon(UtilGUI.getImageIcon(txtIcon.getText()));
      }

    });

    txtColor.addFocusListener(new FocusAdapter() {

      /** {@inheritDoc} */
      @Override
      public void focusLost(FocusEvent evt) {
        try {
          SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
              defineTxtColorBackground(txtColor.getText());
            }
          });
        } catch (Exception e) {
          txtColor.setBackground(Color.white);
        }
      }

    });

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

package com.sgu.infowksporga.jfx.dialog.about;

import java.awt.Dimension;

import javax.swing.SwingUtilities;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GButton;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.dialog.GDialog;
import com.sgu.core.framework.gui.swing.editor.GEditorPane;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.scrollpane.GScrollPane;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.dialog.about.action.OkAction;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import net.miginfocom.swing.MigLayout;

/**
 * Description : AboutDlg class.<br>
 */
public class AboutDlg extends GDialog {

  /** The attribute serialVersionUID. */
  private static final long serialVersionUID = -8198672854160646831L;

  /** Ref to properties panel. */
  private GPanel pnlProperties;

  /** Ref to the label file. */
  private GLabel lblAboutIcon;

  /** Reference to the editor. */
  private GEditorPane editorInfo;

  /**
   * Constructor.<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "about.dlg.title", value = "A propos de info wrksp orga"), // Force /n
  })
  public AboutDlg() {
    super("about.dlg.title", true);
    buildUI();
  }

  /** {@inheritDoc} */
  @Override
  public void initUI() {
    super.initUI();
  }

  /** {@inheritDoc} */
  protected MigLayout getPnlMainMigLayoutData() {
    return new MigLayout("insets 0 0 0 0", "[grow,fill]");
  }

  /**
   * Description : buildDialog method. <br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "about.dlg.message.icon", value = "/icons/phare.png"), // Force /n
  })
  /** {@inheritDoc} */
  @Override
  public void createUI() {
    // Build properties Panel
    pnlProperties = new GPanel(new MigLayout("insets 0 0 0 0, fill", "[][grow]"));

    lblAboutIcon = new GLabel(UtilGUI.getImageIconFromClasspath(I18nHelperApp.getMessage("about.dlg.message.icon")));

    editorInfo = new GEditorPane();
    editorInfo.setEditable(false);
    editorInfo.setContentType("text/html");

    // Assemble properties panel
    pnlProperties.add(lblAboutIcon, "top");
    pnlProperties.add(new GScrollPane(editorInfo), "wrap, grow, height 100%");
    pnlMain.add(pnlProperties, "wrap");

    // Build the button panel
    final GPanel pnlButton = buildButtonPanel();
    final OkAction okAction = new OkAction(this);
    final GButton validateBt = okAction.createButton(true);
    validateBt.setPreferredSize(new Dimension(100, validateBt.getPreferredSize().height));
    pnlButton.add(validateBt);

    pnlMain.add(pnlButton, "growx");

    setPreferredSize(new Dimension(670, 417));

    // By default select Ok Button
    SwingUtilities.invokeLater(new Runnable() {

      @Override
      public void run() {
        UtilGUI.smartRequestFocus(validateBt);
      }
    });

  }

  /**
   * Description : fillData method. <br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "about.dlg.message",
                value = "<html><body><div><font face=\"Tahoma\" size=\"3\">Project Manager to facilitate the CMMI for development Version <b>1.3</b> "
                        + "implementation and Appraisal.<br/><br/>Version: 1.0.0<br/>Build id: 2013<br/><br/>(c) "
                        + "Copyright GUISSE S&#233;bastien.  All rights reserved.</font></div></body></html>"), // Force /n
  })
  /** {@inheritDoc} */
  @Override
  public void fillUI() {

    editorInfo.setText(I18nHelperApp.getMessage("about.dlg.message"));

  }

}

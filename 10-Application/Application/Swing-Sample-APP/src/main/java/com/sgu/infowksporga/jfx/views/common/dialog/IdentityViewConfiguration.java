package com.sgu.infowksporga.jfx.views.common.dialog;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.BorderFactory;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.GLabelField;
import com.sgu.core.framework.gui.swing.overlay.GDefaultOverlayable;
import com.sgu.core.framework.gui.swing.overlay.UtilOverlay;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.UtilView;
import com.sgu.infowksporga.jfx.views.common.dialog.action.AbstractViewValidateAction;

import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;

/**
 * The Class IdentityViewConfiguration.
 */
@Getter
@Setter
public class IdentityViewConfiguration extends GPanel {

  /**
   * Label View Title (= name/Code)
   */
  private GLabel lblViewTitle;

  /**
   * txt view title
   */
  private GTextField txtViewTitle;

  /**
   * Ref to the label view (= name/Code) contains the traduction of the content field
   */
  private GLabel lblViewTitleTranslated;

  /** The validate action. */
  private AbstractViewValidateAction validateAction;

  /**
   * The Constructor.
   */
  public IdentityViewConfiguration(final AbstractViewValidateAction validateAction) {
    super();
    this.validateAction = validateAction;
    buildUI();
  }

  /** {@inheritDoc} */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "common.view.properties.dlg.fieldset.identity", value = "Identification de la vue"), // Force /n
  })
  public void initUI() {
    super.initUI();
    setLayout(new MigLayout("insets 2 0 2 0"));
    setBorder(BorderFactory.createTitledBorder(I18nHelperApp.getMessage("common.view.properties.dlg.fieldset.identity")));
  }

  /** {@inheritDoc} */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "common.view.properties.dlg.lbl.title.text", value = "Titre"), // Force /n
                @I18nProperty(key = "common.view.properties.dlg.lbl.title.tooltip", value = "Définit le titre de la vue"), // Force /n
                @I18nProperty(key = "common.view.properties.dlg.lbl.traduction.text", value = ""), // Force /n
  })
  public void createUI() {
    super.createUI();

    lblViewTitle = new GLabelField();
    lblViewTitle.setBundleConfiguration("common.view.properties.dlg.lbl.title", I18nHelperApp.getI18nHelper());
    lblViewTitle.setMandatory(true);

    txtViewTitle = new GTextField();
    txtViewTitle.addActionListener(validateAction); // To allow validation on enter key press
    final GDefaultOverlayable txtViewTitleOverlay = UtilOverlay.addOverlayErrorRenderer(txtViewTitle);
    txtViewTitle.setOverlay(txtViewTitleOverlay);
    txtViewTitle.setLabel(lblViewTitle);

    lblViewTitleTranslated = new GLabel();
    lblViewTitleTranslated.setBundleConfiguration("common.view.properties.dlg.lbl.traduction", I18nHelperApp.getI18nHelper());
    lblViewTitleTranslated.setFont(UtilGUI.LABEL_BOLD_FONT);
    lblViewTitleTranslated.setHorizontalAlignment(GLabel.LEFT);

    add(lblViewTitle);
    add(txtViewTitle, "width 250px");
    add(lblViewTitleTranslated);

  }

  /** {@inheritDoc} */
  @Override
  public void createListeners() {
    super.createListeners();

    txtViewTitle.addFocusListener(new FocusAdapter() {
      /**
       * {@inheritDoc}
       */
      @Override
      public void focusLost(final FocusEvent e) {
        lblViewTitleTranslated.setText(UtilView.translateViewTitle(txtViewTitle.getText()));
      }
    });
  }

}

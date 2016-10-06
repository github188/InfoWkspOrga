package com.sgu.infowksporga.jfx.component;

import java.time.LocalDateTime;

import javax.swing.BorderFactory;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nDescription;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.dao.jpa.entity.AbstractAuditedEntity;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.IDisplayMode;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.i18n.FwkCoreMessages;
import com.sgu.core.framework.i18n.I18nHelperFwk;
import com.sgu.core.framework.util.UtilDate;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import net.miginfocom.swing.MigLayout;

/**
 * Description : HorodatagePanel class<br>
 */
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
descriptions = { // Class comment to include in properties file
                @I18nDescription(
                line = "#------------------------------------------------------------------------------------------------------------------------------------------"),
                @I18nDescription(line = "# Horodatage Panel  "), // Title
                @I18nDescription(
                line = "#------------------------------------------------------------------------------------------------------------------------------------------"), })

public class HorodatagePanel extends GPanel implements IDisplayMode {
  /**
   * The Class Logger
   */
  private static final Logger LOGGER = LoggerFactory.getLogger(HorodatagePanel.class);

  /**
   * Ref to the workspace UserCre
   */
  private GLabel lblUserCre;

  /**
   * Ref to the workspace UserMaj
   */
  private GLabel lblUserUpd;

  /**
   * Store the display mode
   */
  private int displayMode = IDisplayMode.MODE_CONSULT;

  /** The audited info. */
  private AbstractAuditedEntity<?> auditedInfo;

  /**
   * The Constructor.
   *
   * @param viewComposite the viewComposite
   * @param displayMode the display mode
   */
  public HorodatagePanel(AbstractAuditedEntity<?> auditedInfo) {
    super();
    this.auditedInfo = auditedInfo;
    buildUI();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "horodatage.panel.text", value = "Historique"), // Force /n 
  })
  public void initUI() {
    setLayout(new MigLayout("insets 0 0 0 0", "[][grow,fill]"));
    // Create Horodatage Fieldset
    setBorder(BorderFactory.createTitledBorder(I18nHelperApp.getMessage("horodatage.panel.text")));
  }

  /**
   * {@inheritDoc}
   */

  public void createUI() {
    createUIHorodatageCreation();
    createUIHorodatageUpdate();
  }

  /**
   * Description : createUIHorodatageCreation method <br>
   */

  private void createUIHorodatageCreation() {
    // Create the label
    lblUserCre = new GLabel();
    //lblUserCre.setReadOnly();

    add(lblUserCre, "wrap");
  }

  /**
   * Sets the creation info.
   *
   * @param createThe the create the (mandatory not null)
   * @param by the by (mandatory not null)
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label create
                @I18nProperty(key = "horodatage.panel.label.create.the.text", value = "Créé le&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"), // Force /n
                @I18nProperty(key = "horodatage.panel.label.create.by.text", value = "&nbsp;&nbsp;par&nbsp;&nbsp;"),

  })

  public void setCreationInfo(final LocalDateTime createThe, final String by) {

    String createTheStr = "-";
    String byValue = "-";

    if (createThe != null) {
      final String dateFormat = I18nHelperFwk.getMessage(FwkCoreMessages.DATE_HOUR_MINUTE_SECOND_FORMAT);
      createTheStr = UtilDate.formatDateTime(createThe, dateFormat);
    }

    if (StringUtils.isNotBlank(by)) {
      byValue = by;
    }

    final String htmlInfo = "<HTML>" + I18nHelperApp.getMessage("horodatage.panel.label.create.the.text") + "<b> " + createTheStr + " </b>"
                            + I18nHelperApp.getMessage("horodatage.panel.label.create.by.text") + " <b> " + byValue + " </b></HTML>";

    HorodatagePanel.LOGGER.debug(" htmlInfo - create = " + htmlInfo);

    lblUserCre.setText(htmlInfo);
  }

  /**
   * Description : createUIHorodatageUpdate method <br>
   */

  private void createUIHorodatageUpdate() {
    // Create the label
    lblUserUpd = new GLabel();
    //lblUserUpd.setReadOnly();

    add(lblUserUpd);

  }

  /**
   * Sets the update info.
   *
   * @param modifiedThe the modified the
   * @param by the by
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // label update
                @I18nProperty(key = "horodatage.panel.label.update.the.text", value = "Modifié le&nbsp;"), // Force /n
                @I18nProperty(key = "horodatage.panel.label.update.by.text", value = "&nbsp;&nbsp;par&nbsp;&nbsp;"), })
  public void setUpdateInfo(final LocalDateTime updateThe, final String by) {
    String updateTheStr = "-";
    String byValue = "-";

    if (updateThe != null) {
      final String dateFormat = I18nHelperFwk.getMessage(FwkCoreMessages.DATE_HOUR_MINUTE_SECOND_FORMAT);
      updateTheStr = UtilDate.formatDateTime(updateThe, dateFormat);
    }

    if (StringUtils.isNotBlank(by)) {
      byValue = by;
    }

    final String htmlInfo = "<HTML>" + I18nHelperApp.getMessage("horodatage.panel.label.update.the.text") + "<b> " + updateTheStr + " </b>"
                            + I18nHelperApp.getMessage("horodatage.panel.label.update.by.text") + " <b> " + byValue + " </b></HTML>";

    HorodatagePanel.LOGGER.debug(" htmlInfo - update = " + htmlInfo);
    lblUserUpd.setText(htmlInfo);

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void fillUI() {
    if (auditedInfo != null) {

      if (auditedInfo.getCreatedDate() != null) {
        setCreationInfo(UtilDate.convertUtilDateAsLocalDateTime(auditedInfo.getCreatedDate()), auditedInfo.getCreatedBy());
      }

      if (auditedInfo.getLastModifiedDate() != null) {
        setUpdateInfo(UtilDate.convertUtilDateAsLocalDateTime(auditedInfo.getLastModifiedDate()), auditedInfo.getLastModifiedBy());
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void createListeners() {
    super.createListeners();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setDisplayMode(final int mode) {
    displayMode = mode;

    switch (displayMode) {
      case MODE_CREATE:
      case MODE_COPY:
        setVisible(false);
        break;

      default:
        setVisible(true);
        break;
    }

  }

}

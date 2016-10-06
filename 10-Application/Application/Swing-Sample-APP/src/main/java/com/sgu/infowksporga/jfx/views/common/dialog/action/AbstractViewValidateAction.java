package com.sgu.infowksporga.jfx.views.common.dialog.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.dialog.UtilDlgMessage;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.dto.WorkspacesViewsDto;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractViewPropertiesDlg;

import lombok.Getter;

/**
 * Description : AbstractViewValidateAction class<br>
 *
 * @author SGU
 */
@Getter
public class AbstractViewValidateAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 2568554388620155754L;

  /** The dialog. */
  protected AbstractViewPropertiesDlg dialog;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "common.view.properties.dlg.action.validate.text", value = "Valider"), // Force /n
                @I18nProperty(key = "common.view.properties.dlg.action.validate.tooltip",
                value = "<HTML> Enregistre les informations en mémoire.<br/>\\\r\n"
                        + "                                                          Il faut sauvegarder le workspace complet <b>(Ctrl+S)</b> pour enregistrer les informations en base de données"), // Force /n
                @I18nProperty(key = "common.view.properties.dlg.action.validate.icon", value = "/icons/save_memory.png"), // Force /n
  })
  public AbstractViewValidateAction(final AbstractViewPropertiesDlg dialog) {
    super("common.view.properties.dlg.action.validate");
    this.dialog = dialog;
  }

  /**
   * Description : validateFields method <br>
   *
   * @return true if error is detected false otherwise
   */
  protected boolean validateFields() {
    UtilGUI.markAllFieldsAsValid(dialog);
    boolean hasError = false;

    final String viewTitle = dialog.getPnlIdentity().getTxtViewTitle().getText();

    // Check all field are filled
    if (UtilString.isBlank(viewTitle)) {
      dialog.getPnlIdentity().getTxtViewTitle()
            .showErrorMessage(UtilGUI.getMandatoryMessageForField(dialog.getPnlIdentity().getLblViewTitle().getBundleConfigKey()));
      hasError = true;
    }

    return hasError;
  }

  /**
   * Gets the workspaces views dto.
   *
   * @return the workspaces views dto
   */
  public WorkspacesViewsDto getWorkspacesViewsDto() {
    return GUISessionProxy.getCurrentWorkspace().getWorkspaceDto().buildWorkspacesViewsDtoExtract();
  }

  @Override
  public void actionPerformed(final ActionEvent e) {

    final boolean hasError = validateFields();

    if (hasError) {
      UtilDlgMessage.error("error.fields", "");
      return;
    }

  }

}
package com.sgu.infowksporga.jfx.workspace.dlg.mvc;

import java.util.Optional;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.screen.AGScreen;
import com.sgu.core.framework.gui.jfx.screen.IDisplayMode;
import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.infowksporga.jfx.y_service.ServiceFacadeFactory;

import javafx.scene.control.Dialog;
import lombok.Getter;

/**
 * The Class WorkspaceDlgScreen.
 */
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = WorkspaceDlgScreen.WORKSPACE_DLG_TITLE_KEY, value = "Gestion du Workspace - Mode "), // Force /n

})
@Getter
public class WorkspaceDlgScreen extends AGScreen<WorkspaceDlgModel, WorkspaceDlgViewFxml, WorkspaceDlgController> {

  /** The Constant WORKSPACE_DLG_TITLE_KEY. */
  public static final String WORKSPACE_DLG_TITLE_KEY = "workspace.dialog.title";

  /**
   * The Constructor.
   */
  public WorkspaceDlgScreen() {
    super();
  }

  /**
   * Inits the mvc.
   * Initialized all components by default
   */
  @Override
  public void initMVC() {
    super.initMVC();
  }

  /** {@inheritDoc} */
  @Override
  public void finalizeMvcBuildOfComponent() {
    super.finalizeMvcBuildOfComponent();

    // Affect the controller to the included views
    view().getPnlIdentityCardController().setController(controller());
    view().getPnlConfigurationController().setController(controller());
    view().getPnlReferenceController().setController(controller());
    view().getPnlStyleController().setController(controller());
    view().getPnlHorodateController().setController(controller());

    // Affect the model to the included views
    view().getPnlIdentityCardController().setModel(model());
    view().getPnlConfigurationController().setModel(model());
    view().getPnlReferenceController().setModel(model());
    view().getPnlStyleController().setModel(model());
    view().getPnlHorodateController().setModel(model());

    // Build the UI of the included views
    view().getPnlIdentityCardController().buildUI();
    view().getPnlConfigurationController().buildUI();
    view().getPnlReferenceController().buildUI();
    view().getPnlStyleController().buildUI();
    view().getPnlHorodateController().buildUI();

  }

  /**
   * Show dialog.
   *
   * @param displayMode the display mode
   */
  public void showDialog(final int displayMode) {
    final Dialog<Integer> dialog = buildDialogBox(displayMode);
    dialog.setResizable(true);

    // Convert the result to the display mode when the validation (create, update, delete, copy) button is clicked.
    // If Cancel is selected return null
    dialog.setResultConverter(dialogButton -> {
      if (dialogButton == view().getValidateButtonType()) {
        return displayMode;
      }
      return -1; // Cancel mode 
    });

    // Show the dialog and wait for the result
    final Optional<Integer> result = dialog.showAndWait();

    // Check button clicked by the user and call the service associated with the display mode
    final Integer validationRequested = result.get();
    switch (validationRequested) {
      case IDisplayMode.MODE_CREATE:
        // call service
        ServiceFacadeFactory.callWorkspaceCreateService(this);
        break;

      case IDisplayMode.MODE_UPDATE:
        // call service
        ServiceFacadeFactory.callWorkspaceUpdateService(this);
        break;

      case IDisplayMode.MODE_DELETE:
        // call service
        UtilGUIMessage.showNotYetImplementedDlg();
        break;

      default: // value = -1  --> Cancel mode
        break;
    }

    // Destroy de la boite de dialog
    dialog.close();
  }

}

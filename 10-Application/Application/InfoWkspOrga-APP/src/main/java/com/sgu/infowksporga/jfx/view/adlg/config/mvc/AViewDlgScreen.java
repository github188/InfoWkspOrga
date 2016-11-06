package com.sgu.infowksporga.jfx.view.adlg.config.mvc;

import java.util.Optional;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.control.pane.dock.mvc.ADockableViewFxml;
import com.sgu.core.framework.gui.jfx.screen.AGController;
import com.sgu.core.framework.gui.jfx.screen.AGModel;
import com.sgu.core.framework.gui.jfx.screen.AGScreen;
import com.sgu.core.framework.gui.jfx.screen.AGView;
import com.sgu.core.framework.gui.jfx.screen.IDisplayMode;
import com.sgu.core.framework.gui.jfx.util.UtilGUIMessage;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.jfx.view.ui.AAppViewModel;

import javafx.scene.control.Dialog;
import lombok.Getter;

/**
 * The Class AViewDlgScreen.
 */
@I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
properties = { // label create
              @I18nProperty(key = AViewDlgScreen.VIEW_DLG_TITLE_KEY, value = "Gestion de la vue - Mode "), // Force /n

})
@Getter
public abstract class AViewDlgScreen<M extends AGModel, V extends AGView, C extends AGController> extends AGScreen<M, V, C> {

  /** The Constant VIEW_DLG_TITLE_KEY. */
  public static final String VIEW_DLG_TITLE_KEY = "view.dialog.title";

  /**
   * We store the parent view to be allow to update it
   */
  private ADockableViewFxml viewFxmlParent;

  /**
   * The Constructor.
   */
  public AViewDlgScreen() {
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

    final AViewDlgViewFxml view = (AViewDlgViewFxml) view();

    // Affect the controller to the included views
    view.getPnlIdentityCardController().setController(controller());
    view.getPnlConfigurationController().setController(controller());
    view.getPnlStyleController().setController(controller());
    view.getPnlHorodateController().setController(controller());

    // Affect the model to the included views
    view.getPnlIdentityCardController().setModel(model());
    view.getPnlConfigurationController().setModel(model());
    view.getPnlStyleController().setModel(model());
    view.getPnlHorodateController().setModel(model());

    // Build the UI of the included views
    view.getPnlIdentityCardController().buildUI();
    view.getPnlConfigurationController().buildUI();
    view.getPnlStyleController().buildUI();
    view.getPnlHorodateController().buildUI();

  }

  /**
   * Show dialog.
   *
   * @param displayMode the display mode
   */
  public void showDialog(final int displayMode, final AAppViewModel selectedViewModel) {
    final Dialog<Integer> dialog = buildDialogBox(displayMode);
    dialog.setResizable(true);

    // Save parent view this dialog box
    viewFxmlParent = (ADockableViewFxml) selectedViewModel.view();

    // build Model
    final View viewEntity = selectedViewModel.getViewEntity();

    // ---------------------------------------------------------
    // Initialize all config Dialog fields with view entity info
    // ---------------------------------------------------------
    final AViewDlgModel dlgModel = (AViewDlgModel) model();
    dlgModel.mapViewEntityToModel(viewEntity);

    // Convert the result to the display mode when the validation (create, update, delete, copy) button is clicked.
    // If Cancel is selected return null
    dialog.setResultConverter(dialogButton -> {
      final AViewDlgViewFxml view = (AViewDlgViewFxml) view();
      if (dialogButton == view.getValidateButtonType()) {
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
        UtilGUIMessage.showNotYetImplementedDlg();
        break;

      case IDisplayMode.MODE_UPDATE:
        // call service
        final View newViewData = dlgModel.mapModelToViewEntity();
        selectedViewModel.setViewEntity(newViewData);
        viewFxmlParent.controller().bindComponentsWithPojo();
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

package com.sgu.infowksporga.jfx.workspace.dlg.mvc;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.jfx.screen.AGScreen;

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
  public void finalizeMvcBuildOfComponent() {
    super.finalizeMvcBuildOfComponent();

    view().getPnlIdentityCardController().setController(controller());
    view().getPnlConfigurationController().setController(controller());
    view().getPnlReferenceController().setController(controller());
    view().getPnlStyleController().setController(controller());
    view().getPnlHorodateController().setController(controller());

    view().getPnlIdentityCardController().setModel(model());
    view().getPnlConfigurationController().setModel(model());
    view().getPnlReferenceController().setModel(model());
    view().getPnlStyleController().setModel(model());
    view().getPnlHorodateController().setModel(model());

    view().getPnlIdentityCardController().buildUI();
    view().getPnlConfigurationController().buildUI();
    view().getPnlReferenceController().buildUI();
    view().getPnlStyleController().buildUI();
    view().getPnlHorodateController().buildUI();

  }

}

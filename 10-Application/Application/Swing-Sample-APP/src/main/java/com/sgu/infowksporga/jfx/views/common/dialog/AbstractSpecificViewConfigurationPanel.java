package com.sgu.infowksporga.jfx.views.common.dialog;

import javax.swing.BorderFactory;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.IDisplayMode;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.infowksporga.business.dto.WorkspacesViewsDto;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import lombok.Getter;
import net.miginfocom.swing.MigLayout;

/**
 * Description : AbstractSpecificViewConfigurationPanel class<br>
 */
@Getter
public class AbstractSpecificViewConfigurationPanel extends GPanel implements IDisplayMode {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -8774555177914892315L;

  /** The dialog. */
  private AbstractViewPropertiesDlg dialog;

  /**
   * Constructor<br>
   */
  public AbstractSpecificViewConfigurationPanel(final AbstractViewPropertiesDlg dialog) {
    super();
    this.dialog = dialog;
    buildUI();
  }

  @Override
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "common.view.properties.dlg.fieldset.configuration", value = "Configuration"), // Force /n
  })
  public void initUI() {
    super.initUI();

    setLayout(new MigLayout("insets 0", "[][fill, grow]"));
    setBorder(BorderFactory.createTitledBorder(I18nHelperApp.getMessage("common.view.properties.dlg.fieldset.configuration")));
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
  public void createUI() {
    super.createUI();
  }

  @Override
  public void createListeners() {
    super.createListeners();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setDisplayMode(final int mode) {

  }

}

package com.sgu.infowksporga.jfx.views.common.dialog;

import java.awt.BorderLayout;

import javax.swing.UIManager;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GButton;
import com.sgu.core.framework.gui.swing.IDisplayMode;
import com.sgu.core.framework.gui.swing.dialog.GDialog;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.infowksporga.business.dto.WorkspacesViewsDto;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.AbstractView;
import com.sgu.infowksporga.jfx.views.common.dialog.action.AbstractViewCancelAction;
import com.sgu.infowksporga.jfx.views.common.dialog.action.AbstractViewValidateAction;

import lombok.Getter;
import lombok.Setter;
import net.miginfocom.swing.MigLayout;

/**
 * Description : AbstractViewPropertiesDlg class<br>.
 */
@Getter
@Setter
public abstract class AbstractViewPropertiesDlg extends GDialog implements IDisplayMode {

  /** The attribute serialVersionUID. */
  private static final long serialVersionUID = -8198672854160646831L;

  /** To detect if it's cancel or validate clicked. */
  protected boolean cancelClicked = false;

  /** The view information used to fill dlg components. */
  protected AbstractView view;

  /** The dialog main panel. */
  protected GPanel pnlDialogMain;

  /** The pnl identity view. */
  protected IdentityViewConfiguration pnlIdentity;

  /** The pnl specific config. */
  protected AbstractSpecificViewConfigurationPanel pnlSpecificConfig;

  /** The pnl button. */
  protected GPanel pnlButton;

  /** The cancel action. */
  protected AbstractViewCancelAction cancelAction;

  /** The vadidate action. */
  private AbstractViewValidateAction validateAction;

  /**
   * Constructor<br>.
   *
   * @param view the view
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "common.view.properties.dlg.title", value = "Propriétés de la vue"), // Force /n
  })
  public AbstractViewPropertiesDlg(final AbstractView view) {
    super("common.view.properties.dlg.title", true);
    this.view = view;
    buildUI();
  }

  /** {@inheritDoc} */
  @Override
  public void initUI() {
    super.initUI();
    setLayout(new BorderLayout(0, 0));
    pnlDialogMain = new GPanel(new MigLayout("insets 2 0 0 0", "[grow,fill]", "[][grow,fill][]"));

    add(pnlDialogMain, BorderLayout.CENTER);

  }

  /**
   * Builds the validate action.
   *
   * @return the validate action
   */
  protected abstract AbstractViewValidateAction buildValidateAction();

  /**
   * Builds the cancel action.
   *
   * @return the abstract view cancel action
   */
  protected abstract AbstractViewCancelAction buildCancelAction();

  /** {@inheritDoc} */
  @Override
  public void buildUI() {
    super.buildUI();

    // Build Button Panel
    buildButtonPanel();

    pnlIdentity = new IdentityViewConfiguration(validateAction);

    pnlSpecificConfig = buildSpecificConfigurationPanel();

    pnlDialogMain.add(pnlIdentity, "wrap");
    pnlDialogMain.add(pnlSpecificConfig, "wrap, growy");
    pnlDialogMain.add(pnlButton, "");

  }

  /**
   * Builds the button panel.
   *
   * @return the g panel
   */
  protected GPanel buildButtonPanel() {
    validateAction = buildValidateAction();
    cancelAction = buildCancelAction();

    // Build the button panel
    pnlButton = new GPanel(new MigLayout("insets 5 0 5 0, fill", "[right][left]"));
    pnlButton.setBackground(UIManager.getColor("Button.background"));

    final GButton validateBt = validateAction.createButton(true, true);
    final GButton cancelBt = cancelAction.createButton(true);

    pnlButton.add(validateBt);
    pnlButton.add(cancelBt);
    return pnlButton;
  }

  /**
   * Builds the specific configuration panel.
   *
   * @return the abstract specific view configuration panel
   */
  protected abstract AbstractSpecificViewConfigurationPanel buildSpecificConfigurationPanel();

  /** {@inheritDoc} */
  @Override
  public void setDisplayMode(final int mode) {

  }

  /**
   * Gets the workspaces views dto.
   *
   * @return the workspaces views dto
   */
  public WorkspacesViewsDto getWorkspacesViewsDto() {
    return GUISessionProxy.getCurrentWorkspace().getWorkspaceDto().buildWorkspacesViewsDtoExtract();
  }

  /** {@inheritDoc} */
  @Override
  public void createListeners() {
    super.createListeners();
  }

}

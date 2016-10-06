package com.sgu.infowksporga.jfx.workspace.dlg.properties;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.GButton;
import com.sgu.core.framework.gui.swing.IDisplayMode;
import com.sgu.core.framework.gui.swing.dialog.GDialog;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.jfx.component.HorodatagePanel;
import com.sgu.infowksporga.jfx.workspace.dlg.properties.action.CancelAction;
import com.sgu.infowksporga.jfx.workspace.dlg.properties.action.SaveWorkspacePropertiesAction;

import lombok.Getter;
import net.miginfocom.swing.MigLayout;

/**
 * Description : WorkspaceDlg class<br>
 */
@Getter
public class WorkspaceDlg extends GDialog implements IDisplayMode {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -8198672854160646831L;

  /**
   * Store the display mode
   */
  private int displayMode;

  /** The pnl identity card. */
  private IdentityCardPanel pnlIdentityCard;

  /** The pnl reference. */
  private ReferencePanel pnlReference;

  /** The pnl configuration. */
  private ConfigurationPanel pnlConfiguration;

  /** The pnl style. */
  private StylePanel pnlStyle;

  /**
   * Store the reference to the historic panel
   */
  private HorodatagePanel pnlHorodatage;

  /** The pnl button. */
  private GPanel pnlButton;

  /** The workspace data. */
  private Workspace workspaceIn;

  /**
   * Constructor<br>
   * 
   * @param displayMode the display mode to use (creation, update, consult, ...)
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Action buttons
                @I18nProperty(key = "workspace.properties.dlg.title", value = "Workspace"), // Force /n
                @I18nProperty(key = "workspace.properties.dlg.title.create", value = "Workspace - Création"), // Force /n
                @I18nProperty(key = "workspace.properties.dlg.title.duplicate", value = "Workspace - Duplication"), // Force /n
                @I18nProperty(key = "workspace.properties.dlg.title.update", value = "Workspace- Modification"), // Force /n
                @I18nProperty(key = "workspace.properties.dlg.title.consult", value = "Workspace - Visualisation"), // Force /n
  })
  public WorkspaceDlg(final int displayMode, Workspace workspaceIn) {
    super("workspace.properties.dlg.title", true);
    this.displayMode = displayMode;
    this.workspaceIn = workspaceIn;
    buildUI();
  }

  /**
   * Inits the ui.
   */
  @Override
  public void initUI() {
    super.initUI();
  }

  /**
   * Creates the ui.
   */
  @Override
  public void createUI() {
    super.createUI();
    buildDialog();
    setDisplayMode(displayMode);
  }

  /** {@inheritDoc} */
  @Override
  public void fillUI() {
    super.fillUI();
  }

  /**
   * Gets the pnl main mig layout data.
   *
   * @return the pnl main mig layout data
   */
  protected MigLayout getPnlMainMigLayoutData() {
    return new MigLayout("insets 5 0 0 0, hidemode 1", "[grow,fill]", "[][][][][][]");
  }

  /**
   * Description : buildDialog method <br>
   */
  protected void buildDialog() {

    pnlIdentityCard = new IdentityCardPanel(workspaceIn);
    pnlMain.add(pnlIdentityCard, "cell 0 0");

    pnlReference = new ReferencePanel(workspaceIn);
    pnlMain.add(pnlReference, "cell 0 1,grow");

    pnlConfiguration = new ConfigurationPanel(workspaceIn);
    pnlMain.add(pnlConfiguration, "cell 0 2,grow");

    pnlStyle = new StylePanel(workspaceIn);
    pnlMain.add(pnlStyle, "cell 0 3,grow");

    pnlHorodatage = new HorodatagePanel(workspaceIn);
    pnlMain.add(pnlHorodatage, "cell 0 4,grow");

    // Build the button panel
    pnlButton = buildButtonPanel();
    pnlButton.setLayout(new MigLayout("insets 5 5 5 5, fill", "[right][left]"));
    SaveWorkspacePropertiesAction saveAction = new SaveWorkspacePropertiesAction(this);
    final GButton btnSaveWorkspaceProperties = saveAction.createButton(true, true);
    CancelAction cancelAction = new CancelAction(this);
    final GButton btnCancel = cancelAction.createButton(true);
    pnlButton.add(btnSaveWorkspaceProperties);
    pnlButton.add(btnCancel);

    // Assemble properties panel
    pnlMain.add(pnlButton, "cell 0 5");

  }

  @Override
  public void setDisplayMode(int mode) {
    displayMode = mode;

    pnlIdentityCard.setDisplayMode(mode);
    pnlReference.setDisplayMode(mode);
    pnlConfiguration.setDisplayMode(mode);
    pnlStyle.setDisplayMode(mode);
    pnlHorodatage.setDisplayMode(mode);

  }

  /**
   * Bind ui component to entity.
   *
   * @return the workspace
   */
  public Workspace bindUiComponentToEntity() {
    Workspace workspace = new Workspace();
    bindPojoWithComponents(workspace);

    return workspace;

  }

  /** {@inheritDoc} */
  @Override
  public void bindPojoWithComponents(Object pojo) {

    Workspace workspace = (Workspace) pojo;

    pnlIdentityCard.bindPojoWithComponents(workspace);
    pnlReference.bindPojoWithComponents(workspace);
    pnlConfiguration.bindPojoWithComponents(workspace);
    pnlStyle.bindPojoWithComponents(workspace);

    workspace.setCreationInfo(workspaceIn.getCreatedBy(), workspaceIn.getCreatedDate());
    workspace.setUpdateInfo(workspaceIn.getLastModifiedBy(), workspaceIn.getLastModifiedDate());

  }

}

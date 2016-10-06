package com.sgu.infowksporga.jfx.views;

import java.awt.Component;

import javax.swing.Icon;

import com.sgu.core.framework.gui.swing.GButton;
import com.sgu.core.framework.gui.swing.IBuilderUI;
import com.sgu.core.framework.gui.swing.docking.GView;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.dto.WorkspacesViewsDto;
import com.sgu.infowksporga.business.entity.View;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.views.common.action.AbstractShowViewPropertiesDlgAction;

import net.infonode.docking.RootWindow;
import net.infonode.docking.drag.DockingWindowDragger;

/**
 * Description : AbstractView class<br>
 */
public abstract class AbstractView extends GView implements IBuilderUI {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 3479383157231027675L;

  /**
   * Store the view configuration with LAYOUT info
   */
  protected AbstractViewIdentifier viewIdentifier;

  /**
   * Constructor<br>
   *
   * @param title the title of the view
   * @param icon the icon for the view
   * @param component the component to place inside the view
   */
  public AbstractView(final String title, final Icon icon, final AbstractViewIdentifier viewIdentifier) {
    super(title, icon, null);
    this.viewIdentifier = viewIdentifier;
    buildUI();

  }

  /**
   * Gets the workspaces views dto.
   *
   * @return the workspaces views dto
   */
  public WorkspacesViewsDto getWorkspacesViewsDto() {
    return GUISessionProxy.getCurrentWorkspace().getWorkspaceDto().buildWorkspacesViewsDtoExtract();
  }

  /**
   * Gets the view identifier.
   *
   * @return the view identifier
   */
  public AbstractViewIdentifier getViewIdentifier() {
    return viewIdentifier;
  }

  /**
   * Sets the view identifier.
   *
   * @param viewIdentifier the view identifier
   */
  public void setViewIdentifier(final AbstractViewIdentifier viewIdentifier) {
    this.viewIdentifier = viewIdentifier;
  }

  /**
   * Starts a drag and drop operation for this window.
   *
   * @param dropTarget the {@link RootWindow} in which the window can be dropped
   * @return an {@link DockingWindowDragger} object which controls the drag and drop operation
   * @since IDW 1.3.0
   */
  @Override
  public DockingWindowDragger startDrag(final RootWindow dropTarget) {
    return new WindowDragger(this, dropTarget);
  }

  @Override
  public void buildUI() {
    initUI();
    createUI();
    createListeners();
    fillUI();
    bindComponentsWithPojo();
  }

  @Override
  public void initUI() {

  }

  @Override
  public void createUI() {
    buildTitleBarIcon();
    setComponent(buildViewComponent());

    applyViewConfiguration();

  }

  /** {@inheritDoc} */
  @Override
  public void bindComponentsWithPojo() {
    // TODO Auto-generated method stub

  }

  @Override
  public void bindPojoWithComponents(Object pojo) {
    // TODO Auto-generated method stub

  }

  /**
   * Apply common view configuration.
   *
   * @param viewEntity the view entity
   */
  protected void applyCommonViewConfiguration(final View viewEntity) {
    if (viewIdentifier != null) {
      getViewProperties().setTitle(viewEntity.getName());

      if (UtilString.isNotBlank(viewEntity.getIcon())) {
        getViewProperties().setIcon(UtilGUI.getImageIcon(viewEntity.getIcon()));
      }

    }
  }

  /**
   * Builds the title bar icon.
   */
  protected void buildTitleBarIcon() {
    final AbstractShowViewPropertiesDlgAction showPropertiesDlgAction = buildShowViewPropertiesDlgAction();

    final GButton viewPropertiesButton = UtilGUI.createToolbarButtonFomAction(showPropertiesDlgAction);

    getCustomTitleBarComponents().add(viewPropertiesButton);
    getCustomTitleBarComponents().add(UtilGUI.createToolbarGroupSeparatorLabel());
  }

  /**
   * Builds the show view properties dlg action.
   *
   * @return the abstract show view properties dlg action
   */
  protected abstract AbstractShowViewPropertiesDlgAction buildShowViewPropertiesDlgAction();

  /**
   * Builds the view component.
   *
   * @return the component
   */
  protected abstract Component buildViewComponent();

  /**
   * Apply configuration.
   */
  protected abstract void applyViewConfiguration();

  @Override
  public void fillUI() {

  }

  @Override
  public void createListeners() {
  }
}

package com.sgu.infowksporga.jfx.perspective.tree;

import com.sgu.core.framework.gui.jfx.util.UtilGUI;
import com.sgu.core.framework.i18n.util.I18NConstant;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.business.entity.enumeration.WorkspaceTypeEnum;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.perspective.PerspectiveScreen;
import com.sgu.infowksporga.jfx.perspective.cb.CbbPerspectiveItem;
import com.sgu.infowksporga.jfx.perspective.tree.vo.AbstractItemVo;
import com.sgu.infowksporga.jfx.perspective.tree.vo.WorkspaceItemVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import javafx.scene.control.Tooltip;
import javafx.scene.control.TreeCell;

/**
 * The Class TreeCellFactory.
 */
public class TreeCellFactory extends TreeCell<AbstractItemVo> {

  /**
   * The Constructor.
   */
  public TreeCellFactory() {
  }

  /** {@inheritDoc} */
  @Override
  public void updateItem(final AbstractItemVo item, final boolean empty) {
    super.updateItem(item, empty);

    final PerspectiveScreen perspectiveScreen = GUISessionProxy.getApplication().getApplicationScreen().getPerspectiveScreen();
    final CbbPerspectiveItem cbbPerspectiveItem = (CbbPerspectiveItem) perspectiveScreen.view().getCbbPerspective().getSelectionModel().getSelectedItem();

    //Re-init values
    setTooltip(null);
    setGraphic(null);
    setText("");
    setStyle("");

    if (item != null && cbbPerspectiveItem != null) {

      final WorkspaceItemVo userVoObject = (WorkspaceItemVo) item;
      final Workspace workspace = userVoObject.getWorkspaceDto().getWorkspace();

      setText(I18nHelperApp.getMessage(workspace.getName()));
      setStyle(getTitleStyle(workspace));

      if (workspace.getType() == WorkspaceTypeEnum.ROOT) {
        setText("Perspective - " + I18nHelperApp.getMessage(cbbPerspectiveItem.getPerspective().getName()));

        if (UtilString.isNotBlank((String) perspectiveScreen.view().getCbbFilterOnWorkspaces().getSelectionModel().getSelectedItem())) {
          setGraphic(UtilGUI.getImageView("/icons/filter.png"));
          setTooltip(new Tooltip(I18nHelperApp.getMessage("perspective.filter.apply.tooltip")));
        }
        /*
         * else if (treeItem.getChildCount() == 0) {
         * label.setIcon(UtilGUI.getImageIconFromClasspath("/icons/error-loading.png"));
         * label.setToolTipText(I18nHelperApp.getMessage("perspective.error.loading.tooltip"));
         * }
         */

      }

      final String icon = workspace.getIcon();
      setGraphic(UtilGUI.getImageView(icon));

      final String description = getItemDescription(userVoObject);
      if (UtilString.isNotBlank(description)) {
        setTooltip(new Tooltip(description));
      }

    }
  }

  /**
   * Gets the node description.
   *
   * @param label the label
   * @param userItem the user object
   * @return the node description
   */
  private String getItemDescription(final WorkspaceItemVo userItem) {

    final Workspace workspace = userItem.getWorkspaceDto().getWorkspace();

    if (UtilString.isNotBlank(workspace.getDescription())) {
      final String description = workspace.getDescription().replace("${User}", GUISessionProxy.getGuiSession().getCurrentUser().getLogin());
      return I18nHelperApp.getMessage(description);
    }

    return I18nHelperApp.getNullMessage(workspace.getName() + I18NConstant.TOOLTIP_TEXT);
  }

  /**
   * Gets the title.
   *
   * @param workspace the workspace
   * @return the title
   */
  public String getTitleStyle(final Workspace workspace) {
    String style = "";

    if (workspace.getColor() != null) {
      style += "-fx-text-fill: " + workspace.getColor() + ";";
    }
    if (workspace.isBold() == true) {
      style += "-fx-font-weight: bold;";
    }
    if (workspace.isItalic() == true) {
      style += "-fx-font-style: italic;";
    }
    if (workspace.isUnderline() == true) {
      style += "-fx-underline: true;";
    }
    if (workspace.isStrike() == true) {
      style += "-fx-strikethrough: true;";
    }

    return style;
  }

}

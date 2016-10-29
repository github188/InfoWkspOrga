package com.sgu.infowksporga.jfx.project;

import com.sgu.core.framework.gui.jfx.control.list.AbstractItemVo;
import com.sgu.infowksporga.business.entity.Project;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class CbbProjectItemVo.
 */
@Getter
@Setter
@Slf4j
public class CbbProjectItemVo extends AbstractItemVo {

  /** The partage. */
  private Project project;

  /**
   * The Constructor.
   *
   * @param project the project
   */
  public CbbProjectItemVo(final Project project) {
    super();
    this.project = project;
  }

  /** {@inheritDoc} */
  @Override
  public String getText() {
    return I18nHelperApp.getMessage(project.getName());
  }

  /** {@inheritDoc} */
  @Override
  public Tooltip getTooltip() {
    if (project.getDescription() != null) {
      return new Tooltip(I18nHelperApp.getMessage(project.getDescription()));
    }
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public ImageView getIcon() {
    /*
     * if (project.getI18nIcon() != null) {
     * return new ImageView(UtilGUI.getImage(project.getI18nIcon()));
     * }
     */

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return getText();
  }

  /** {@inheritDoc} */
  @Override
  public Object getEncapsultatedObject() {
    return project;
  }

  /** {@inheritDoc} */
  @Override
  public String getNodeIdentifier() {
    return "" + project.getId();
  }

}

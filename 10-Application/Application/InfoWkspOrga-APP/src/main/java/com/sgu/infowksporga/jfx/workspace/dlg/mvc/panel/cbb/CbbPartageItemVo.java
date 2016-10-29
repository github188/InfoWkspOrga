package com.sgu.infowksporga.jfx.workspace.dlg.mvc.panel.cbb;

import com.sgu.core.framework.gui.jfx.control.list.AbstractItemVo;
import com.sgu.core.framework.gui.jfx.util.UtilGUI;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class CbbPartageItem.
 * Used to list all available Workspace Type
 */
@Getter
@Setter
@Slf4j
public class CbbPartageItemVo extends AbstractItemVo {

  /** The partage. */
  private PartageEnum partage;

  /**
   * The Constructor.
   *
   * @param partage the partage
   */
  public CbbPartageItemVo(final PartageEnum partage) {
    super();
    this.partage = partage;
  }

  /** {@inheritDoc} */
  @Override
  public String getText() {
    return I18nHelperApp.getMessage(partage.getI18nText());
  }

  /** {@inheritDoc} */
  @Override
  public Tooltip getTooltip() {
    if (partage.getI18nDescription() != null) {
      return new Tooltip(I18nHelperApp.getMessage(partage.getI18nDescription()));
    }
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public ImageView getIcon() {
    if (partage.getI18nIcon() != null) {
      return new ImageView(UtilGUI.getImage(partage.getI18nIcon()));
    }

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
    return partage;
  }

  /** {@inheritDoc} */
  @Override
  public String getNodeIdentifier() {
    return partage.getValue();
  }

}

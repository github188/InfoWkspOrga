package com.sgu.infowksporga.jfx.perspective.cb;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;

import com.sgu.core.framework.gui.swing.GDefaultListCellRenderer;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.UtilPerspective;

/**
 * Description : ComboBoxPerspectiveListCellRenderer class<br>
 * is used to display perspective in combo box
 */
public class ComboBoxPerspectiveListCellRenderer extends GDefaultListCellRenderer {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -5524245295926272318L;

  /**
   * Constructor<br>
   *
   * @param comboBox reference
   */
  public ComboBoxPerspectiveListCellRenderer() {
    super();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected,
                                                final boolean cellHasFocus) {
    final JLabel component = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

    component.setToolTipText(null);

    if (value != null && value instanceof ComboBoxPerspectiveVo) {
      final ComboBoxPerspectiveVo vo = (ComboBoxPerspectiveVo) value;
      component.setText(I18nHelperApp.getMessage(vo.getPerspective().getName()));
      if (vo.getPerspective().getIcon() == null) {
        component.setIcon(UtilPerspective.DEFAULT_PERSPECTIVE_ICON);
      }
      else {
        component.setIcon(UtilGUI.getImageIconFromClasspath(vo.getPerspective().getIcon()));
      }

      if (vo.getPerspective().getDescription() != null) {
        component.setToolTipText(I18nHelperApp.getMessage(vo.getPerspective().getDescription()));
      }

    }
    else if (value != null) {
      component.setText("<html>&nbsp; " + value.toString() + "</HTML>");
    }
    else {
      component.setText("Not valid Object !!");
    }

    return component;
  }

}

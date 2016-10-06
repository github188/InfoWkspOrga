package com.sgu.infowksporga.jfx.workspace.dlg.properties.cbbox;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;

import com.sgu.core.framework.gui.swing.GComboBox;
import com.sgu.core.framework.gui.swing.GDefaultListCellRenderer;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.business.entity.Workspace;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

/**
 * The Class ComboBoxWorkspaceRenderer.
 */
public class ComboBoxWorkspaceRenderer extends GDefaultListCellRenderer {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -5524245295926272318L;

  /**
   * reference
   */
  private final GComboBox comboBox;

  /**
   * Constructor<br>
   * 
   * @param comboBox reference
   */
  public ComboBoxWorkspaceRenderer(final GComboBox comboBox) {
    super();
    this.comboBox = comboBox;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Component getListCellRendererComponent(final JList list, final Object value, final int index, final boolean isSelected,
  final boolean cellHasFocus) {
    final JLabel component = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

    if (value != null) {
      final Workspace workspace = (Workspace) value;
      component.setText(I18nHelperApp.getMessage(workspace.getName()));
      if (workspace.getIcon() != null) {
        component.setIcon(UtilGUI.getImageIcon(workspace.getIcon()));
      }
    }
    else {
      component.setText("");
    }

    return component;
  }

}

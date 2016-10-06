package com.sgu.infowksporga.jfx.connexion.cbbox;

import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JList;

import com.sgu.core.framework.gui.swing.GComboBox;
import com.sgu.core.framework.gui.swing.GDefaultListCellRenderer;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

/**
 * Description : ComboBoxLanguageListCellRenderer class<br>
 * is used to display country flag in combo box
 */
public class ComboBoxLanguageRenderer extends GDefaultListCellRenderer {
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
  public ComboBoxLanguageRenderer(final GComboBox comboBox) {
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
      final ComboBoxLanguageVo language = (ComboBoxLanguageVo) value;
      component.setText(I18nHelperApp.getMessage(language.getLabel()));
      if (language.getFlag() != null) {
        component.setIcon(UtilGUI.getImageIconFromClasspath(I18nHelperApp.getMessage(language.getFlag())));
      }
    }

    if (comboBox.hasError() && !isSelected) {
      component.setBackground(comboBox.computeBackgroundColor());
    }

    return component;
  }

}

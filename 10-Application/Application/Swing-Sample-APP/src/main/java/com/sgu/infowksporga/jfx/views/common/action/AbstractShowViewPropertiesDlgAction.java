package com.sgu.infowksporga.jfx.views.common.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.AbstractView;
import com.sgu.infowksporga.jfx.views.common.dialog.AbstractViewPropertiesDlg;

/**
 * Description : Display View Properties Dlg Action class<br>
 * 
 * @author SGU
 */
public abstract class AbstractShowViewPropertiesDlgAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -6124596911162025134L;

  /**
   * The reference to get the view
   */
  protected final AbstractView view;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "common.view.action.properties.text", value = "Propriétés de la vue"), // Force \n
                @I18nProperty(key = "common.view.action.properties.tooltip", value = "Configure la vue sélectionnée"), // Force \n
                @I18nProperty(key = "common.view.action.properties.icon", value = "/icons/configuration.gif"), // Force \n
  })
  public AbstractShowViewPropertiesDlgAction(final AbstractView view) {
    super("common.view.action.properties");
    this.view = view;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    final AbstractViewPropertiesDlg dlg = builViewPropertiesDlg(view);
    dlg.pack();
    dlg.centerDialogVsScreen();
    dlg.setVisible(true);
  }

  /**
   * Buil view properties dlg.
   *
   * @param view the view
   * @return the abstract view properties dlg
   */
  protected abstract AbstractViewPropertiesDlg builViewPropertiesDlg(AbstractView view);
}
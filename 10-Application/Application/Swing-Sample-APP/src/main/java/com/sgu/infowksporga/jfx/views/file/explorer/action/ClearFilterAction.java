package com.sgu.infowksporga.jfx.views.file.explorer.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.tree.UtilFileTreeFilter;

/**
 * The Class ClearFilterAction.
 */
public class ClearFilterAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The reference to get the directory tree
   */
  private final FileExplorerView fileExplorerView;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "file.explorer.view.action.clear.filter.text", value = "Efface le filtre"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.clear.filter.tooltip",
                value = "Efface le filtre et ré-affiche l'ensemble du contenu du répertoire sélectionné "), // Force \n
                @I18nProperty(key = "file.explorer.view.action.clear.filter.icon", value = "/icons/table/clear-filter.png"), // Force \n
  })
  public ClearFilterAction(final FileExplorerView fileExplorerView) {
    super("file.explorer.view.action.clear.filter");
    this.fileExplorerView = fileExplorerView;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evts) {
    fileExplorerView.getTxtFilter().setText("");
    UtilFileTreeFilter.applyFilter(fileExplorerView);
  }
}
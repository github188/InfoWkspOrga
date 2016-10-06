package com.sgu.infowksporga.jfx.views.file.explorer.action;

import java.awt.event.ActionEvent;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.views.file.explorer.FileExplorerView;
import com.sgu.infowksporga.jfx.views.file.explorer.rules.IsAtLeastViewFileSelectedRule;
import com.sgu.infowksporga.jfx.zfacade.local.edit.PasteFilesFromClipboardServiceUI;

/**
 * Description : Paste Files Action class<br>
 *
 * @author SGU
 */
public class PasteFilesAction extends AbstractInfoWrkspOrgaAction {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * The reference to get the directory tree
   */
  private final FileExplorerView fileExplorerView;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "file.explorer.view.action.paste.files.text", value = "Coller les fichiers"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.paste.files.tooltip",
                value = "Coller les fichiers/dossiers de la vue en cours dans le répertoire selectionné"), // Force \n
                @I18nProperty(key = "file.explorer.view.action.paste.files.icon", value = "/icons/paste.png"), // Force \n
  })
  public PasteFilesAction(final FileExplorerView fileExplorerView) {
    super("file.explorer.view.action.paste.files");
    this.fileExplorerView = fileExplorerView;

    setRule(new IsAtLeastViewFileSelectedRule(fileExplorerView));
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    final PasteFilesFromClipboardServiceUI facade = SpringBeanHelper.getImplementationByInterface(PasteFilesFromClipboardServiceUI.class);
    facade.execute();
  }
}

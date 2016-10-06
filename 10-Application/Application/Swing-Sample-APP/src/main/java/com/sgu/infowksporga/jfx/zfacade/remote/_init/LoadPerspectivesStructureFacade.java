package com.sgu.infowksporga.jfx.zfacade.remote._init;

import javax.swing.ProgressMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.infowksporga.business.pivot.perspective.LoadPerspectivesStructureIn;
import com.sgu.infowksporga.business.pivot.perspective.LoadPerspectivesStructureOut;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.zfacade.AbstractBusinessFacade;
import com.sgu.infowksporga.rest.RestServiceMapping;

/**
 * Description : LoadPerspectiveWorkspaceFacade class<br>
 * This service is called 2 times :
 * - The first one to initialized common perspective before user connexion
 * - The second one to initialize user specific perspective after user connexion
 */
@Service
public class LoadPerspectivesStructureFacade extends AbstractBusinessFacade<LoadPerspectivesStructureOut, PerspectivePanel> {

  /**
   * The logger.
   */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(LoadPerspectivesStructureFacade.class);

  /**
   * Constructor<br>
   */
  public LoadPerspectivesStructureFacade() {
    super();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LoadPerspectivesStructureOut execute(final PerspectivePanel container) throws TechnicalException, BusinessException {
    LoadPerspectivesStructureOut out = null;

    final LoadPerspectivesStructureIn in = new LoadPerspectivesStructureIn();
    in.getPerspectivesConfig().put("prez.host.port", I18nHelperApp.getMessage("prez.host.port"));
    in.getPerspectivesConfig().put("biz.host.port", I18nHelperApp.getMessage("biz.host.port"));
    in.getPerspectivesConfig().put("prez.app.name", I18nHelperApp.getMessage("prez.app.name"));
    in.getPerspectivesConfig().put("biz.app.name", I18nHelperApp.getMessage("biz.app.name"));

    in.getPerspectivesConfig().put("perspectives.init.xml.ur.base", I18nHelperApp.getMessage("perspectives.init.xml.ur.base"));

    String currentUser = GUISessionProxy.getGuiSession().getCurrentUser();
    if (currentUser == null) {
      throw new TechnicalException("User must be connected !!");
    }
    else {
      in.getPerspectivesConfig().put("perspectives.init.xml.files", I18nHelperApp.getMessage("perspectives.init.xml.files"));
      // the user is logged now we use the user perspective file to initialize his own space
      // "perspective.USER.xml.file"
      //in.getPerspectivesConfig().put("perspectives.init.xml.files", I18nHelperApp.getMessage("perspective.user.xml.file"));
    }

    in.setFilesToLoadKey("perspectives.init.xml.files");
    in.setUserLogin(currentUser);

    // Call the service
    out = (LoadPerspectivesStructureOut) UtilInfoWrkspOrga.callRestBusinessService(RestServiceMapping.URL_SERVICE_LOAD_PERSPECTIVES_STRUCTURE,
                                                                                  in, LoadPerspectivesStructureOut.class);

    return out;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refreshScreen(final LoadPerspectivesStructureOut out, final PerspectivePanel container, final StringBuilder reportMessages,
  final ProgressMonitor monitor) {
    // Do nothing

  }

}

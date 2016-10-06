package com.sgu.infowksporga.jfx.zfacade.remote._init;

import javax.swing.ProgressMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.web.rest.http.GMediaType;
import com.sgu.infowksporga.business.pivot.preference.LoadPreferencesStructureIn;
import com.sgu.infowksporga.business.pivot.preference.LoadPreferencesStructureOut;
import com.sgu.infowksporga.jfx.InfoWrkspOrgaFrame;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.zfacade.AbstractBusinessFacade;
import com.sgu.infowksporga.rest.RestServiceMapping;

/**
 * Description : LoadPreferencesStructureFacade class<br>
 */
@Service
public class LoadPreferencesStructureFacade extends AbstractBusinessFacade<LoadPreferencesStructureOut, InfoWrkspOrgaFrame> {

  /**
   * The logger.
   */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(LoadPreferencesStructureFacade.class);

  /**
   * Constructor<br>
   */
  public LoadPreferencesStructureFacade() {
    super();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public LoadPreferencesStructureOut execute(final InfoWrkspOrgaFrame container) throws TechnicalException, BusinessException {
    LoadPreferencesStructureOut out = null;

    final LoadPreferencesStructureIn in = new LoadPreferencesStructureIn();
    in.getPreferencesConfig().put("prez.host.port", I18nHelperApp.getMessage("prez.host.port"));
    in.getPreferencesConfig().put("biz.host.port", I18nHelperApp.getMessage("biz.host.port"));
    in.getPreferencesConfig().put("prez.app.name", I18nHelperApp.getMessage("prez.app.name"));
    in.getPreferencesConfig().put("biz.app.name", I18nHelperApp.getMessage("biz.app.name"));

    in.getPreferencesConfig().put("preferences.init.xml.ur.base", I18nHelperApp.getMessage("preferences.init.xml.ur.base"));
    in.getPreferencesConfig().put("preferences.init.xml.file", I18nHelperApp.getMessage("preferences.init.xml.file"));

    String currentUser = GUISessionProxy.getGuiSession().getCurrentUser();
    if (currentUser == null) {
      currentUser = "admin"; // Because we are not logged at this time
    }

    in.setFileToLoadKey("preferences.init.xml.file");
    in.setUserLogin(currentUser);

    // Call the service
    out = (LoadPreferencesStructureOut) UtilInfoWrkspOrga.callRestBusinessService(RestServiceMapping.URL_SERVICE_LOAD_PREFERENCES_STRUCTURE,
                                                                                 in, LoadPreferencesStructureOut.class);

    return out;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refreshScreen(final LoadPreferencesStructureOut out, final InfoWrkspOrgaFrame container, final StringBuilder reportMessages,
  final ProgressMonitor monitor) {
    // Do nothing

  }

}

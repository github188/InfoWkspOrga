package com.sgu.infowksporga.jfx.zfacade.remote.preferences;

import javax.swing.JComponent;
import javax.swing.ProgressMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.infowksporga.business.pivot.preference.FindPreferencesIn;
import com.sgu.infowksporga.business.pivot.preference.FindPreferencesOut;
import com.sgu.infowksporga.jfx.InfoWrkspOrgaFrame;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.zfacade.AbstractBusinessFacade;
import com.sgu.infowksporga.rest.RestServiceMapping;

/**
 * Description : FindPreferencesFacade class<br>
 */
@Service
public class FindPreferencesFacade extends AbstractBusinessFacade<FindPreferencesOut, InfoWrkspOrgaFrame> {

  /**
   * The logger.
   */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(FindPreferencesFacade.class);

  /**
   * Constructor<br>
   */
  public FindPreferencesFacade() {
    super();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void doBeforeServiceCall(final InfoWrkspOrgaFrame container, final ProgressMonitor monitor) {

    // TODO get current selected Preferences
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public JComponent getGlasspanesFor(final InfoWrkspOrgaFrame container) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FindPreferencesOut execute(final InfoWrkspOrgaFrame container) throws TechnicalException, BusinessException {
    FindPreferencesOut out = null;

    final FindPreferencesIn in = new FindPreferencesIn();
    //in.setAll(true);
    // Call the service
    out = (FindPreferencesOut) UtilInfoWrkspOrga.callRestBusinessService(RestServiceMapping.URL_SERVICE_FIND_PERSPECTIVE, in,
                                                                        FindPreferencesOut.class);

    return out;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refreshScreen(final FindPreferencesOut out, final InfoWrkspOrgaFrame container, final StringBuilder reportMessages,
  final ProgressMonitor monitor) {

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void finalizeService(final FindPreferencesOut out, final InfoWrkspOrgaFrame container, final ProgressMonitor monitor) {

  }

}

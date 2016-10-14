package com.sgu.infowksporga.jfx.y_service.remote;

import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.core.framework.gui.jfx.service.AbstractBusinessFacade;
import com.sgu.core.framework.gui.jfx.util.GUISession;
import com.sgu.core.framework.gui.jfx.util.UtilApplication;
import com.sgu.infowksporga.business.pivot.orchestration.FindDataToInitApplicationIn;
import com.sgu.infowksporga.business.pivot.orchestration.FindDataToInitApplicationOut;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveIn;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureIn;
import com.sgu.infowksporga.jfx.perspective.PerspectiveScreen;
import com.sgu.infowksporga.rest.RestServiceMapping;

import javafx.scene.control.ProgressBar;

/**
 * The Class FindDataToInitApplicationFacade.
 */
@Service
public class FindDataToInitApplicationFacade extends AbstractBusinessFacade<FindDataToInitApplicationOut, PerspectiveScreen> {

  /**
   * The Constructor.
   */
  public FindDataToInitApplicationFacade() {
    super();
  }

  /** {@inheritDoc} */
  @Override
  public FindDataToInitApplicationOut execute(final PerspectiveScreen screen) throws TechnicalException, BusinessException {

    FindDataToInitApplicationOut out = null;

    final FindPerspectiveIn in_1 = new FindPerspectiveIn();
    in_1.setAll(true);

    final FindPerspectiveStructureIn in_2 = new FindPerspectiveStructureIn();
    in_2.setPerspective(null);

    final FindDataToInitApplicationIn in_3 = new FindDataToInitApplicationIn();
    in_3.setUserInfo(GUISession.getInstance().getCurrentUser());
    in_3.setUserPreferedPerspective(null);
    in_3.setFindPerspectiveIn(in_1);
    in_3.setFindPerspectiveStructureIn(in_2);

    // Call the service
    out = (FindDataToInitApplicationOut) UtilApplication.callRestBusinessService(RestServiceMapping.URL_SERVICE_FIND_DATA_TO_INIT_APPLICATION, in_3,
                                                                                 FindDataToInitApplicationOut.class);

    return out;

  }

  /** {@inheritDoc} */
  @Override
  public void refreshScreen(final FindDataToInitApplicationOut output, final PerspectiveScreen screen, final StringBuilder reportMessages, final ProgressBar monitor) {
    screen.model().fillUI(output.getFindPerspectiveOut());
    screen.model().fillUI(output.getFindPerspectiveStructureOut());
  }

}

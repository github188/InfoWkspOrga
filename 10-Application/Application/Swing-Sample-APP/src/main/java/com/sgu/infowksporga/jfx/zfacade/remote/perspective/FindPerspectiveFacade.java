package com.sgu.infowksporga.jfx.zfacade.remote.perspective;

import java.util.List;

import javax.swing.JComponent;
import javax.swing.ProgressMonitor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.sgu.core.framework.exception.BusinessException;
import com.sgu.core.framework.exception.TechnicalException;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveIn;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveOut;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.cb.ComboBoxPerspectiveVo;
import com.sgu.infowksporga.jfx.util.UtilInfoWrkspOrga;
import com.sgu.infowksporga.jfx.zfacade.AbstractBusinessFacade;
import com.sgu.infowksporga.rest.RestServiceMapping;

/**
 * Description : FindPerspectiveFacade class<br>
 * Used to Fill Perspective combobox
 */
@Service
public class FindPerspectiveFacade extends AbstractBusinessFacade<FindPerspectiveOut, PerspectivePanel> {

  /**
   * The logger.
   */
  @SuppressWarnings("unused")
  private static final Logger LOGGER = LoggerFactory.getLogger(FindPerspectiveFacade.class);

  /**
   * Constructor<br>
   */
  public FindPerspectiveFacade() {
    super();

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void doBeforeServiceCall(final PerspectivePanel container, final ProgressMonitor monitor) {

    // TODO get current selected Perspective
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public JComponent getGlasspanesFor(final PerspectivePanel container) {
    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public FindPerspectiveOut execute(final PerspectivePanel container) throws TechnicalException, BusinessException {
    FindPerspectiveOut out = null;

    final FindPerspectiveIn in = new FindPerspectiveIn();
    in.setAll(true);
    // Call the service
    out = (FindPerspectiveOut) UtilInfoWrkspOrga.callRestBusinessService(RestServiceMapping.URL_SERVICE_FIND_PERSPECTIVE, in,
                                                                        FindPerspectiveOut.class);

    return out;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void refreshScreen(final FindPerspectiveOut out, final PerspectivePanel container, final StringBuilder reportMessages,
  final ProgressMonitor monitor) {

    container.getCbPerspective().removeAllItems();
    container.getCbPerspective().addItem("");

    final List<Perspective> perspectives = out.getPerspectiveLst();
    for (final Perspective perspective : perspectives) {
      final ComboBoxPerspectiveVo vo = new ComboBoxPerspectiveVo(perspective);
      container.getCbPerspective().addItem(vo);
    }

  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void finalizeService(final FindPerspectiveOut out, final PerspectivePanel container, final ProgressMonitor monitor) {

  }

}

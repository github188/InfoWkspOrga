package com.sgu.infowksporga.jfx.perspective.listener;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.cb.ComboBoxPerspectiveVo;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;
import com.sgu.infowksporga.jfx.workspace.WorkspaceDefault;
import com.sgu.infowksporga.jfx.zfacade.remote.perspective.FindPerspectiveStructureFacade;

/**
 * The Class ComboBoxPerspectiveItemListener.
 */
public class ComboBoxPerspectiveItemListener implements ItemListener {

  /**
   * The Constructor.
   */
  public ComboBoxPerspectiveItemListener() {
  }

  /** {@inheritDoc} */
  @Override
  public void itemStateChanged(final ItemEvent evt) {

    if (ItemEvent.SELECTED == evt.getStateChange()) {
      final PerspectivePanel perspectivePanel = (PerspectivePanel) UtilGUI.getParentComponent((Component) evt.getSource(),
                                                                                              PerspectivePanel.class);

      if (evt.getItem() instanceof ComboBoxPerspectiveVo) {

        final FindPerspectiveStructureFacade facade = SpringBeanHelper.getImplementationByInterface(FindPerspectiveStructureFacade.class);
        GUISession.getInstance().getServiceDelegate().execute(facade, perspectivePanel);
      }
      else {
        perspectivePanel.getTree().setModel(null);
        GUISessionProxy.getInfoWrkspOrgaFrame().showSelectedWorkspace(new WorkspaceDefault());
      }
    }

  }
}

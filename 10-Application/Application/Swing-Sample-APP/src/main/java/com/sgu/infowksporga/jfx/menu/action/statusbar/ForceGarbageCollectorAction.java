package com.sgu.infowksporga.jfx.menu.action.statusbar;

import java.awt.event.ActionEvent;

import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;

/**
 * Description : ForceGarbageCollectorAction class<br>
 * 
 * @author SGU
 */
public class ForceGarbageCollectorAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -3651435084049489336L;

  /**
   * Constructor<br>
   */
  public ForceGarbageCollectorAction() {
    super("status.bar.force.garbage.collector");
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {
    System.gc();
  }
}

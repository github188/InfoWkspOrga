package com.sgu.infowksporga.jfx.component;

import com.jidesoft.swing.JideSplitPaneDivider;
import com.sgu.core.framework.gui.swing.panel.GSplitPane;

/**
 * The Class MeSplitpane.
 */
public class MeSplitpane extends GSplitPane {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = -1955818267745726451L;

  /**
   * Store the splitpane location at initialization time and after user dragging
   */
  private int prefereredDividerlocation;

  /**
   * Constructor<br>
   */
  public MeSplitpane() {
  }

  /**
   * {@inheritDoc}
   */
  @Override
  protected void finishDraggingTo(final JideSplitPaneDivider divider, final int location) {
    super.finishDraggingTo(divider, location);
    prefereredDividerlocation = location;
  }

  /**
   * @see #prefereredDividerlocation
   * @return the prefereredDividerlocation : See field description
   */
  public int getPrefereredDividerlocation() {
    return prefereredDividerlocation;
  }

  /**
   * @see #prefereredDividerlocation
   * @param prefereredDividerlocation : See field description
   */
  public void setPrefereredDividerlocation(final int prefereredDividerlocation) {
    this.prefereredDividerlocation = prefereredDividerlocation;
  }

}

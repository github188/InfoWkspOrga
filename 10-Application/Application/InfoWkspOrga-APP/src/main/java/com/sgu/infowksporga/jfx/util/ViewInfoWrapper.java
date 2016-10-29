package com.sgu.infowksporga.jfx.util;

/**
 * Used to be able to get modified value other recursivity method call
 * and to manage sibiling change
 */
class ViewInfoWrapper {
  public int viewOrder = 0;
  public boolean isNextSibiling = false;

  /**
   * The Constructor.
   *
   * @param viewOrder the view order
   * @param isNextSibiling the is next sibiling
   */
  public ViewInfoWrapper(final int viewOrder, final boolean isNextSibiling) {
    super();
    this.viewOrder = viewOrder;
    this.isNextSibiling = isNextSibiling;
  }

}
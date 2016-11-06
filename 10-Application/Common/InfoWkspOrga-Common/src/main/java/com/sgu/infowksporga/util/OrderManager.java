package com.sgu.infowksporga.util;

/**
 * The Class OrderManager.
 */
public class OrderManager {

  /** The next order. */
  private int nextOrder = 0;

  /**
   * The Constructor.
   */
  public OrderManager(final int initOrder) {
    nextOrder = initOrder;
  }

  /**
   * @return the nextOrder
   */
  public final int getNextOrder() {
    this.nextOrder = this.nextOrder + 1;
    return this.nextOrder;
  }

}

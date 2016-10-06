package com.sgu.infowksporga.business;

import java.io.Serializable;

/**
 * Description : this class define informations to obtain the context used by business service.<br>
 *
 * @author SGU
 */
public class BusinessSessionContext implements Serializable {

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 2320274307924690505L;
  /**
   * The ticket providing during the login
   */
  private String ticket;

  /**
   * @see #ticket
   * @return the jeton : See field description
   */
  public String getTicket() {
    return ticket;
  }

  /**
   * @see #ticket
   * @param ticket : See field description
   */
  public void setTicket(final String ticket) {
    this.ticket = ticket;
  }
}

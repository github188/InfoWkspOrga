package com.sgu.infowksporga.business.pivot.preference;

import com.sgu.core.framework.pivot.AbstractIn;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : Find Preferences In class<br>
 */
@Getter
@Setter
public class FindPreferencesIn extends AbstractIn {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1471654661319L;

  /**
   * Retrieve all Preferences
   */
  private boolean all;

  /**
   * Constructor<br>
   */
  public FindPreferencesIn() {
    super();
  }

}

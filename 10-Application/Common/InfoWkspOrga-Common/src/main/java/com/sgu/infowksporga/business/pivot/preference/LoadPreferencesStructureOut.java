package com.sgu.infowksporga.business.pivot.preference;

import com.sgu.core.framework.pivot.AbstractOut;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : LoadPreferencesStructureOut class<br>
 */
@Getter
@Setter
public class LoadPreferencesStructureOut extends AbstractOut {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1471643187815L;

  /**
   * Attribute
   */
 private String attribute;

  /**
   * Constructor<br>
   */
  public LoadPreferencesStructureOut() {
    super();
  }

}

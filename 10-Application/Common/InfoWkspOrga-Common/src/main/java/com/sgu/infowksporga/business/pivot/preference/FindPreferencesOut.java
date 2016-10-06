package com.sgu.infowksporga.business.pivot.preference;

import java.util.List;

import com.sgu.core.framework.pivot.AbstractOut;
import com.sgu.infowksporga.business.entity.Preference;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : FindPreferencesOut class<br>
 */
@Getter
@Setter
public class FindPreferencesOut extends AbstractOut {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1471654661334L;

  /**
   * Attribute
   */
  private List<Preference> preferenceLst;

  /**
   * Constructor<br>
   */
  public FindPreferencesOut() {
    super();
  }

}

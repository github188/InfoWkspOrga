package com.sgu.infowksporga.business.pivot.perspective;

import java.util.Map;

import com.sgu.core.framework.pivot.AbstractIn;
import com.sgu.infowksporga.business.entity.Perspective;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : FindPerspectiveStructureIn class<br>
 */
@Getter
@Setter
public class FindPerspectiveStructureIn extends AbstractIn {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1471221529003L;

  /**
   * Attribute
   */
  private Perspective perspective;

  /**
   * Use to decode user perspectives URL localization
   * cf file /InfoWkspOrga-Common/src/main/resources/spring/server-localization.properties
   */
  private Map<String, String> perspectivesConfig;

  /**
   * Constructor<br>
   */
  public FindPerspectiveStructureIn() {
    super();
  }

}

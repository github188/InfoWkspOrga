package com.sgu.infowksporga.business.pivot.perspective;

import java.util.HashMap;
import java.util.Map;

import com.sgu.core.framework.pivot.AbstractIn;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : LoadPerspectivesStructureIn class<br>
 */
@Getter
@Setter
public class LoadPerspectivesStructureIn extends AbstractIn {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1470510856561L;

  /**
   * Use to decode perspectives URL localization
   * cf file /InfoWkspOrga-Common/src/main/resources/spring/server-localization.properties
   */
  private Map<String, String> perspectivesConfig;

  /**
   * The files to load keys.
   * separated by ";"
   */
  private String filesToLoadKey;

  /**
   * Constructor<br>
   */
  public LoadPerspectivesStructureIn() {
    super();
    perspectivesConfig = new HashMap<String, String>(4);
  }
}
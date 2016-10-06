package com.sgu.infowksporga.business.pivot.preference;

import java.util.HashMap;
import java.util.Map;

import com.sgu.core.framework.pivot.AbstractIn;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : LoadPreferencesStructureIn class<br>
 */
@Getter
@Setter
public class LoadPreferencesStructureIn extends AbstractIn {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1471643187805L;

  /**
   * Use to decode preferences URL localization
   * cf file /InfoWkspOrga-Common/src/main/resources/spring/server-localization.properties
   */
  private Map<String, String> preferencesConfig;

  /**
   * The preferences unique file to load key.
   */
  private String fileToLoadKey;

  /**
   * Constructor<br>
   */
  public LoadPreferencesStructureIn() {
    super();
    preferencesConfig = new HashMap<String, String>(4);
  }

}

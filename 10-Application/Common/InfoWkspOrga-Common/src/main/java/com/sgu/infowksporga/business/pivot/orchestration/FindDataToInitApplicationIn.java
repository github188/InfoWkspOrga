package com.sgu.infowksporga.business.pivot.orchestration;

import com.sgu.core.framework.pivot.AbstractIn;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveIn;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureIn;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : FindDataToInitApplicationIn class<br>
 */
@Getter
@Setter
public class FindDataToInitApplicationIn extends AbstractIn {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1476255074664L;

  /** The user preferred perspective. */
  private Integer userPreferedPerspective;

  /** The find perspective in. */
  private FindPerspectiveIn findPerspectiveIn;

  /** The find perspective structure in. */
  private FindPerspectiveStructureIn findPerspectiveStructureIn;

  /**
   * Constructor<br>
   */
  public FindDataToInitApplicationIn() {
    super();
  }

}

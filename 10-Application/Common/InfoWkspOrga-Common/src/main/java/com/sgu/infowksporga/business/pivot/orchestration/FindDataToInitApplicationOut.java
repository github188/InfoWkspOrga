package com.sgu.infowksporga.business.pivot.orchestration;

import com.sgu.core.framework.pivot.AbstractOut;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveOut;
import com.sgu.infowksporga.business.pivot.perspective.FindPerspectiveStructureOut;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : FindDataToInitApplicationOut class<br>
 */
@Getter
@Setter
public class FindDataToInitApplicationOut extends AbstractOut {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1476255074679L;

  /** The find perspective in. */
  private FindPerspectiveOut findPerspectiveOut;

  /** The find perspective structure in. */
  private FindPerspectiveStructureOut findPerspectiveStructureOut;

  /**
   * Constructor<br>
   */
  public FindDataToInitApplicationOut() {
    super();
  }

}

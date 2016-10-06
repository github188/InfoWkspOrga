package com.sgu.infowksporga.business.pivot.perspective;

import java.util.ArrayList;
import java.util.List;

import com.sgu.core.framework.pivot.AbstractOut;
import com.sgu.infowksporga.business.entity.Perspective;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : FindPerspectiveOut class<br>
 */
@Getter
@Setter
public class FindPerspectiveOut extends AbstractOut {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1470118877871L;

  /**
   * Attribute
   */
  private List<Perspective> perspectiveLst;

  /**
   * Constructor<br>
   */
  public FindPerspectiveOut() {
    super();
  }

  /**
   * Adds the perspective.
   *
   * @param perspective the perspective
   */
  public void addPerspective(Perspective perspective) {
    if (perspectiveLst == null) {
      perspectiveLst = new ArrayList<Perspective>(2);
    }
    perspectiveLst.add(perspective);
  }

}

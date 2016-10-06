package com.sgu.infowksporga.business.pivot.perspective;

import com.sgu.core.framework.pivot.AbstractIn;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : FindPerspectiveIn class<br>
 */
@Getter
@Setter
public class FindPerspectiveIn extends AbstractIn {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1470118877864L;

  /**
   * search all perspective
   */
  private boolean all;

  /**
   * search perspective by name
   */
  private String name;

  /**
   * Constructor<br>
   */
  public FindPerspectiveIn() {
    super();
  }

}

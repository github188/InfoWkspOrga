package com.sgu.infowksporga.business.pivot.workspace;

import java.util.List;

import com.sgu.core.framework.pivot.AbstractOut;
import com.sgu.infowksporga.business.entity.View;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : SaveWorkspaceLayoutOut class<br>
 */
@Getter
@Setter
public class SaveWorkspaceLayoutOut extends AbstractOut {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1477888750510L;

  /**
   * Constructor<br>
   */
  public SaveWorkspaceLayoutOut() {
    super();
  }

  /** The views with new Id. */
  private List<View> views;

}

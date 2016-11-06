package com.sgu.infowksporga.business.pivot.workspace;

import java.util.List;

import com.sgu.core.framework.pivot.AbstractIn;
import com.sgu.infowksporga.business.entity.View;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : SaveWorkspaceLayoutIn class<br>
 */
@Getter
@Setter
public class SaveWorkspaceLayoutIn extends AbstractIn {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1477888750499L;

  /** The workspace id. */
  private String workspaceId;
  /**
   * Attribute
   */
  private String layout;

  /** The screen height. */
  private Double height;

  /** The screen width. */
  private Double width;

  /** The views. */
  private List<View> views;

  /**
   * Constructor<br>
   */
  public SaveWorkspaceLayoutIn() {
    super();
  }

}

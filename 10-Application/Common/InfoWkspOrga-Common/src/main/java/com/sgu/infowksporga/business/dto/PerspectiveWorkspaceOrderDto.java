package com.sgu.infowksporga.business.dto;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class PerspectiveWorkspaceOrder.
 * It is used to manage workspace order in a perspective
 */
@Getter
@Setter
public class PerspectiveWorkspaceOrderDto extends AbstractDto implements Serializable {
  /** serialVersionUID. */
  private static final long serialVersionUID = 3833132488644338922L;

  /** The perspective id. */
  private Integer perspectiveId;

  /** The old workspace id order. */
  Map<String, Integer> oldWorkspaceIdOrder = new LinkedHashMap<>();

  /** The new workspace id order. */
  Map<String, Integer> newWorkspaceIdOrder = new LinkedHashMap<>();

  /**
   * The Constructor.
   */
  public PerspectiveWorkspaceOrderDto() {
  }

}

package com.sgu.infowksporga.jfx.views;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : AbstractViewConfig class<br>.
 */
@Getter
@Setter
public abstract class AbstractViewIdentifier implements Serializable {

  /** The attribute serialVersionUID. */
  private static final long serialVersionUID = 3212553999299475228L;

  /**
   * The view Id .
   * If view entity depends on a master, viewEntityId will be = NULL
   */
  private Integer viewEntityId;

  /** The view code . */
  private String viewEntityName;

  /**
   * The Constructor.
   */
  public AbstractViewIdentifier() {
    super();
  }

  /**
   * Constructor<br>.
   */
  public AbstractViewIdentifier(Integer viewEntityId, String viewEntityName) {
    super();
    this.viewEntityId = viewEntityId;
    this.viewEntityName = viewEntityName;
  }

}

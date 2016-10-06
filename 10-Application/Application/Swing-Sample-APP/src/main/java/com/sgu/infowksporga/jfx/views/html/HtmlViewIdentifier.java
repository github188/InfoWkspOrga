package com.sgu.infowksporga.jfx.views.html;

import java.io.Serializable;

import com.sgu.infowksporga.jfx.views.AbstractViewIdentifier;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : HtmlViewConfig class<br>
 */
@Getter
@Setter
public class HtmlViewIdentifier extends AbstractViewIdentifier implements Serializable {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 3212553999299475228L;

  /**
   * The Constructor.
   */
  public HtmlViewIdentifier() {
    super();
  }

  /**
   * The Constructor.
   *
   * @param viewEntityId the view entity id
   * @param viewEntityName the view entity name
   */
  public HtmlViewIdentifier(Integer viewEntityId, String viewEntityName) {
    super(viewEntityId, viewEntityName);
  }

}

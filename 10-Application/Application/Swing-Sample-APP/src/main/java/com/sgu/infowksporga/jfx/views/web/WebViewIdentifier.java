package com.sgu.infowksporga.jfx.views.web;

import java.io.Serializable;

import com.sgu.infowksporga.jfx.views.AbstractViewIdentifier;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : AbstractViewIdentifier class<br>
 * Stored by serialization of layout to retrieve view informations stored in database
 */
@Getter
@Setter
public class WebViewIdentifier extends AbstractViewIdentifier implements Serializable {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 3212553999299475228L;

  /**
   * The Constructor.
   */
  public WebViewIdentifier() {
    super();
  }

  /**
   * The Constructor.
   *
   * @param viewEntityId the view entity id
   * @param viewEntityName the view entity name
   */
  public WebViewIdentifier(Integer viewEntityId, String viewEntityName) {
    super(viewEntityId, viewEntityName);
  }

}

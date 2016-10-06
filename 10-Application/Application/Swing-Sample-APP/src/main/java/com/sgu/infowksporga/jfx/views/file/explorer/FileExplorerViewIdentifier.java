package com.sgu.infowksporga.jfx.views.file.explorer;

import java.io.Serializable;

import com.sgu.infowksporga.jfx.views.AbstractViewIdentifier;

import lombok.Getter;
import lombok.Setter;

/**
 * Description : FileExplorerViewConfig class<br>
 */
@Getter
@Setter
public class FileExplorerViewIdentifier extends AbstractViewIdentifier implements Serializable {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 3212553999299475228L;

  /**
   * The Constructor.
   */
  public FileExplorerViewIdentifier() {
    super();
  }

  /**
   * The Constructor.
   *
   * @param viewEntityId the view entity id
   * @param viewEntityName the view entity name
   */
  public FileExplorerViewIdentifier(Integer viewEntityId, String viewEntityName) {
    super(viewEntityId, viewEntityName);
  }

}

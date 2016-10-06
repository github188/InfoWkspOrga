package com.sgu.infowksporga.jfx.perspective;

import com.sgu.core.framework.gui.swing.docking.GView;
import com.sgu.core.framework.gui.swing.util.UtilGUI;

import lombok.Getter;
import lombok.Setter;
import net.infonode.docking.properties.ViewTitleBarProperties;

/**
 * Description : PerspectiveView class<br>.
 */
@Getter
@Setter
public class PerspectiveView extends GView {

  /** The attribute serialVersionUID. */
  private static final long serialVersionUID = 6674606955595787973L;

  /** Store the reference to the webPanel. */
  private static PerspectivePanel pnlPerpective;

  /**
   * Constructor<br>.
   *
   * @param viewIdentifier it's the view Identifier to rebuild view
   */
  public PerspectiveView(PerspectivePanel pnlPerpective) {
    super("Perspectives", UtilGUI.getImageIconFromClasspath("/icons/perspective.png"), pnlPerpective);
    this.pnlPerpective = pnlPerpective;

    final ViewTitleBarProperties titleBarProperties = getViewProperties().getViewTitleBarProperties();
    titleBarProperties.toString();
  }

}

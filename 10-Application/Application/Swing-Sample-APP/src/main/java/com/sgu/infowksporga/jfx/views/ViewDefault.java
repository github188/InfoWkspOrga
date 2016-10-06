package com.sgu.infowksporga.jfx.views;

import java.util.List;

import javax.swing.ImageIcon;

import com.sgu.core.framework.gui.swing.panel.GBackgroundPanel;
import com.sgu.core.framework.gui.swing.scrollpane.GScrollPane;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

import net.infonode.docking.View;

/**
 * Description : ViewDefault class<br>
 */
public class ViewDefault extends View {
  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 1349737558027163419L;
  /**
   * Store the last position of displayed images
   */
  public static int imagesIndex = 0;

  /**
   * Constructor<br>
   */
  public ViewDefault() {
    super("default", null, null);

    final List<ImageIcon> images = GUISessionProxy.getCarrouselImages();

    final ImageIcon image = images.get(imagesIndex);
    imagesIndex++;
    if (imagesIndex == images.size()) {
      imagesIndex = 0; // reset position of list
    }

    final GBackgroundPanel panel = new GBackgroundPanel(image.getImage());

    if (image.getIconWidth() > 800 || image.getIconHeight() > 700) {
      this.setComponent(new GScrollPane(panel));
    }
    else {
      this.setComponent(panel);
    }

    this.getViewProperties().setAlwaysShowTitle(false);
    this.getViewProperties().getViewTitleBarProperties().setVisible(false);
  }

}

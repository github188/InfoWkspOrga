package com.sgu.infowksporga.jfx.component.cbb;

import java.io.InputStream;

import com.sgu.core.framework.gui.jfx.control.list.AbstractItemVo;
import com.sgu.core.framework.gui.jfx.util.UtilGUI;
import com.sgu.core.framework.pivot.UserInfo;
import com.sgu.core.framework.util.UtilIO;
import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class CbbOwnerItem.
 * Used to list all available Users
 */
@Getter
@Setter
@Slf4j
public class CbbOwnerItemVo extends AbstractItemVo {

  /** The user info. */
  private UserInfo userInfo;

  /**
   * The Constructor.
   *
   * @param treeNodeIdentifier the tree node identifier
   * @param workspace the workspace
   */
  public CbbOwnerItemVo(final UserInfo userInfo) {
    this.userInfo = userInfo;
  }

  /** {@inheritDoc} */
  @Override
  public String getText() {
    return userInfo.getName() + " " + userInfo.getSurname();
  }

  /** {@inheritDoc} */
  @Override
  public ImageView getIcon() {
    String flagFile = "";
    if (userInfo.getNationality() != null) {
      // First search for language and country
      final String language = userInfo.getNationality().split("_")[0];
      final String country = userInfo.getNationality().split("_")[1];

      final String locale = language + "_" + country;
      flagFile = I18nHelperApp.getMessage("icons.flag.path.template", locale);
      final InputStream is = UtilIO.getClasspathFileInputStream(flagFile);

      if (is != null) {
        return new ImageView(UtilGUI.getImage(flagFile));
      }
    }
    return null;
  }

  /** {@inheritDoc} */
  @Override
  public Tooltip getTooltip() {
    if (userInfo.getEmail_1() != null) {
      return new Tooltip(I18nHelperApp.getMessage(userInfo.getEmail_1()));
    }

    return null;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public String toString() {
    return getText();
  }

  /** {@inheritDoc} */
  @Override
  public Object getEncapsultatedObject() {
    return userInfo;
  }

  /** {@inheritDoc} */
  @Override
  public String getNodeIdentifier() {
    return userInfo.getLogin();
  }

}

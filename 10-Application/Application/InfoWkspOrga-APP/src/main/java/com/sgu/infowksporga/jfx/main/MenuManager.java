package com.sgu.infowksporga.jfx.main;

import com.sgu.infowksporga.business.xml.jaxb.menu.MenuApplication;
import com.sgu.infowksporga.business.xml.jaxb.toolbar.ToolbarApplication;

import lombok.extern.slf4j.Slf4j;

/**
 * Description : MenuManager class . Update the menu and toolbar
 *
 * @author SGU
 */
@Slf4j
public class MenuManager {

  /**
   * XML Structure of the application menu
   */
  public static MenuApplication menuApplication;

  /**
   * XML Structure of the application toolbar
   */
  public static ToolbarApplication toolbarApplication;

}

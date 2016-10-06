package com.sgu.infowksporga.jfx;

import java.io.Serializable;
import java.util.List;

import javax.swing.SwingUtilities;

import com.sgu.core.framework.gui.swing.GButton;
import com.sgu.core.framework.gui.swing.action.AbstractGAction;
import com.sgu.core.framework.gui.swing.action.ActionManager;
import com.sgu.core.framework.gui.swing.i18n.I18nHelperSwing;
import com.sgu.core.framework.gui.swing.menu.GMenu;
import com.sgu.core.framework.gui.swing.menu.GMenuBar;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.separator.GSeparator;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.gui.swing.util.UtilGUI;
import com.sgu.infowksporga.business.xml.jaxb.menu.Menu;
import com.sgu.infowksporga.business.xml.jaxb.menu.MenuApplication;
import com.sgu.infowksporga.business.xml.jaxb.menu.MenuItem;
import com.sgu.infowksporga.business.xml.jaxb.toolbar.ToolbarApplication;
import com.sgu.infowksporga.business.xml.jaxb.toolbar.ToolbarItem;
import com.sgu.infowksporga.jfx.util.GUISessionProxy;

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

  /**
   * Description : refreshToolbar method . Refresh the toolbar with actions
   *
   * @param actionManager manager with all actions of toolbar
   */
  public static void refreshApplicationToolbar(final ActionManager actionManager) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {

        final InfoWrkspOrgaFrame frame = (InfoWrkspOrgaFrame) GUISession.getInstance().getApplicationFrame();
        final GPanel toolBar = frame.getToolBar();
        toolBar.removeAll();

        final List<Object> toolbarElements = toolbarApplication.getSeparatorOrToolbarItem();

        for (final Object toolbarElement : toolbarElements) {
          if (toolbarElement instanceof com.sgu.infowksporga.business.xml.jaxb.toolbar.Separator) {
            toolBar.add(new GSeparator(GSeparator.VERTICAL), "growy");
          }
          else {
            final ToolbarItem tbi = (ToolbarItem) toolbarElement;
            final AbstractGAction action = actionManager.getGAction(tbi.getName());
            final GButton button = UtilGUI.createToolbarButtonFomAction(action);
            button.setBorderPainted(false);
            toolBar.add(button);
          }
        }

        frame.getToolBar().validate();
        frame.getToolBar().repaint();
      }
    });
  }

  /**
   * Description : refreshMenu method .
   *
   * @param actionManager list of actions
   */
  public static void refreshApplicationMenu(final ActionManager actionManager) {
    SwingUtilities.invokeLater(new Runnable() {
      @Override
      public void run() {

        // Clean menu bar
        final InfoWrkspOrgaFrame frame = GUISessionProxy.getInfoWrkspOrgaFrame();
        final GMenuBar menuBar = (GMenuBar) frame.getJMenuBar();
        menuBar.removeAll();
        menuBar.validate();
        menuBar.repaint();

        // Build the menu bar
        final List<Menu> menus = menuApplication.getMenu();

        for (final Menu menu : menus) {
          // create a menu with key
          final GMenu gMenu = new GMenu();
          gMenu.setBundleConfiguration(menu.getName(), I18nHelperSwing.getI18nHelper());
          buildMenu(actionManager, gMenu, menu.getMenuOrMenuItemOrSeparator());
          menuBar.add(gMenu);
        }

        menuBar.validate();
        menuBar.repaint();
      }
    });
  }

  /**
   * Description : buildMenu recursively method <br>
   *
   * @param actionManager ist of actions
   * @param menuParent The parent menu
   * @param menuElements The items to add
   */
  private static void buildMenu(final ActionManager actionManager, final GMenu menuParent, final List<Serializable> menuElements) {

    for (final Serializable menuElement : menuElements) {

      if (menuElement instanceof MenuItem) {

        final MenuItem mi = (MenuItem) menuElement;
        final AbstractGAction action = actionManager.getGAction(mi.getName());
        log.debug("Build Menu --> Ajout de l'action name : '{}'", mi.getName());
        menuParent.add(action.createMenuItem(true, true));
      }
      else if (menuElement instanceof com.sgu.infowksporga.business.xml.jaxb.menu.Separator) {
        menuParent.add(new GSeparator());
      }
      else if (menuElement instanceof Menu) {

        final GMenu subMenu = new GMenu();
        subMenu.setBundleConfiguration(((Menu) menuElement).getName(), I18nHelperSwing.getI18nHelper());
        menuParent.add(subMenu);
        buildMenu(actionManager, subMenu, ((Menu) menuElement).getMenuOrMenuItemOrSeparator());
      }
    }
  }
}

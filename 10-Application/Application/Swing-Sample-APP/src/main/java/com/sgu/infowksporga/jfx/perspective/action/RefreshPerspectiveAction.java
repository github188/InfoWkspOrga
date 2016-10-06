package com.sgu.infowksporga.jfx.perspective.action;

import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import com.sgu.apt.annotation.AnnotationConfig;
import com.sgu.apt.annotation.i18n.I18n;
import com.sgu.apt.annotation.i18n.I18nProperty;
import com.sgu.core.framework.gui.swing.dialog.GMessageBox;
import com.sgu.core.framework.gui.swing.util.GUISession;
import com.sgu.core.framework.spring.loader.SpringBeanHelper;
import com.sgu.core.framework.util.UtilString;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.jfx.menu.action.AbstractInfoWrkspOrgaAction;
import com.sgu.infowksporga.jfx.perspective.PerspectivePanel;
import com.sgu.infowksporga.jfx.perspective.cb.ComboBoxPerspectiveVo;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTree;
import com.sgu.infowksporga.jfx.util.UtilWorkspace;
import com.sgu.infowksporga.jfx.zfacade.remote.perspective.FindPerspectiveFacade;

/**
 * Description : RefreshPerspectiveAction class<br>
 * 
 * @author SGU
 */
public class RefreshPerspectiveAction extends AbstractInfoWrkspOrgaAction {

  /**
   * The attribute serialVersionUID.
   */
  private static final long serialVersionUID = 1L;

  /**
   * The reference to get the tree
   */
  private final PerspectivePanel perspectivePanel;

  /**
   * Constructor<br>
   */
  @I18n(baseProject = AnnotationConfig.I18N_TARGET_APPLICATION_PROPERTIES_FOLDER, filePackage = "i18n", fileName = "application-prez",
  properties = { // Force \n
                @I18nProperty(key = "perspective.action.refresh.text", value = "Rafraîchir"), // Force \n
                @I18nProperty(key = "perspective.action.refresh.tooltip",
                value = "Rafraîchit la liste des perspectives \\n & l'arbre de la perspective éventuellement sélectionnée"), // Force \n
                @I18nProperty(key = "perspective.action.refresh.icon", value = "/icons/refresh-yellow.png"), // Force \n
  })
  public RefreshPerspectiveAction(final PerspectivePanel perspectivePanel) {
    super("perspective.action.refresh");
    this.perspectivePanel = perspectivePanel;
  }

  /** {@inheritDoc} */
  @Override
  public void actionPerformed(final ActionEvent evt) {

    // Verify no need to save the current workspace
    final int dialogResult = UtilWorkspace.saveWorkspaceIfChanged();

    if (dialogResult != GMessageBox.CANCEL) {

      SwingUtilities.invokeLater(new Runnable() {

        @Override
        public void run() {
          Perspective perspective = perspectivePanel.getCbSelectedPerspective();

          PerspectiveTree perspectiveTree = perspectivePanel.getTree();
          // Save expansion state
          String expansionState = perspectiveTree.getExpansionStateAsRowList(0);
          // Store the selected node
          TreePath selectedPath = perspectiveTree.getSelectionPath();

          // Get selected perspective
          final FindPerspectiveFacade facade = SpringBeanHelper.getImplementationByInterface(FindPerspectiveFacade.class);
          GUISession.getInstance().getServiceDelegate().execute(facade, perspectivePanel);

          SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
              if (perspective != null) {
                perspectivePanel.getCbPerspective().setSelectedItem(new ComboBoxPerspectiveVo(perspective));
              }

              SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                  // Re-apply filter if exist
                  if (!UtilString.isBlank(perspectiveTree.getFilteredText())) {
                    perspectiveTree.filterTree(perspectiveTree.getFilteredText(), perspectiveTree.isOnLeaf());
                  }

                  perspectiveTree.loadExpansionStateAsRowList(0, expansionState);

                  if (selectedPath != null) {
                    perspectiveTree.setSelectionPath(selectedPath);
                  }
                }
              });

            }
          });

        }
      });

    }

  }
}
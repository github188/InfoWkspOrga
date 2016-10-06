package com.sgu.infowksporga.jfx.perspective;

import java.awt.LayoutManager;
import java.awt.event.ItemListener;

import com.sgu.core.framework.gui.swing.GCheckbox;
import com.sgu.core.framework.gui.swing.GComboBox;
import com.sgu.core.framework.gui.swing.GLabel;
import com.sgu.core.framework.gui.swing.action.ActionManager;
import com.sgu.core.framework.gui.swing.panel.GPanel;
import com.sgu.core.framework.gui.swing.textfield.GTextField;
import com.sgu.infowksporga.business.entity.Perspective;
import com.sgu.infowksporga.jfx.perspective.cb.ComboBoxPerspectiveVo;
import com.sgu.infowksporga.jfx.perspective.tree.PerspectiveTree;

/**
 * Description : AbstractPerspectivePanel class<br>
 */
public abstract class AbstractPerspectivePanel extends GPanel {

  /**
   * The action manager
   */
  protected ActionManager actionManager;

  /**
   * Ref to label filter
   */
  protected GLabel lblFilter;

  /**
   * Ref to filter text
   */
  protected GTextField txtFilter;

  /**
   * Ref to filter text on leaf or directory
   */
  protected GCheckbox chkFilter;

  /**
   * Store the perspective tree
   */
  protected PerspectiveTree tree;

  /**
   * Contain all available perspective
   */
  protected GComboBox cbPerspective;
  /**
   * keep it to facilitate add or remove of the listener (like in perspective loading facade)
   */
  protected ItemListener cbPerspectiveItemListener;

  /**
   * The attribute serialVersionUID
   */
  private static final long serialVersionUID = 2504302392933848845L;

  /**
   * Constructor<br>
   */
  public AbstractPerspectivePanel() {
    super();
  }

  /**
   * Constructor<br>
   *
   * @param layout
   */
  public AbstractPerspectivePanel(final LayoutManager layout) {
    super(layout);
  }

  /**
   * Gets the selected cb perspective.
   *
   * @return the selected cb perspective
   */
  public Perspective getCbSelectedPerspective() {
    final Object cbSelectedPerspective = getCbPerspective().getSelectedItem();

    if (cbSelectedPerspective instanceof ComboBoxPerspectiveVo) {
      final ComboBoxPerspectiveVo selectedcbItem = (ComboBoxPerspectiveVo) cbSelectedPerspective;
      return selectedcbItem.getPerspective();
    }

    return null;
  }

  /**
   * @see #tree
   * @return the tree : See field description
   */

  public PerspectiveTree getTree() {
    return tree;
  }

  /**
   * @see #tree
   * @param tree : See field description
   */

  public void setTree(final PerspectiveTree tree) {
    this.tree = tree;
  }

  /**
   * @see #lblFilter
   * @return the lblFilter : See field description
   */
  public GLabel getLblFilter() {
    return lblFilter;
  }

  /**
   * @see #lblFilter
   * @param lblFilter : See field description
   */
  public void setLblFilter(final GLabel lblFilter) {
    this.lblFilter = lblFilter;
  }

  /**
   * @see #txtFilter
   * @return the txtFilter : See field description
   */
  public GTextField getTxtFilter() {
    return txtFilter;
  }

  /**
   * @see #txtFilter
   * @param txtFilter : See field description
   */
  public void setTxtFilter(final GTextField txtFilter) {
    this.txtFilter = txtFilter;
  }

  /**
   * @see #chkFilter
   * @return the chkFilter : See field description
   */
  public GCheckbox getChkFilter() {
    return chkFilter;
  }

  /**
   * @see #chkFilter
   * @param chkFilter : See field description
   */
  public void setChkFilter(final GCheckbox chkFilter) {
    this.chkFilter = chkFilter;
  }

  /**
   * @see #actionManager
   * @return the actionManager : See field description
   */
  public ActionManager getActionManager() {
    return actionManager;
  }

  /**
   * @see #cbPerspective
   * @return the cbPerspectiveCode : See field description
   */
  public GComboBox getCbPerspective() {
    return cbPerspective;
  }

}

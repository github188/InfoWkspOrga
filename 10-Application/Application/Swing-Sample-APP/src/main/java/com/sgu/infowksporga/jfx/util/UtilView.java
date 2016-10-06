package com.sgu.infowksporga.jfx.util;

import com.sgu.infowksporga.jfx.i18n.I18nHelperApp;

/**
 * Description : ViewUtil class<br>
 */
public final class UtilView {

  /**
   * Constructor<br>
   */
  private UtilView() {
    super();
  }

  /**
   * Description : translateViewTitle method <br>
   *
   * @param text
   */
  public static String translateViewTitle(final String text) {
    return I18nHelperApp.getGivenDefaultMessageIfNull(text, "N/A");
  }

  /**
   * Description : getSelectedPracticeLst from CMMI tree method <br>
   *
   * @param dialog
   * @return
   */
  /*
   * public static List<String> getSelectedCmmiPracticeLst(final CmmiProcessSelectionPanel cmmiProcessSelectionPanel) {
   * final List<String> selectedPracticeLst = new ArrayList<String>(2);
   * final GTree selectedPracticeTree = cmmiProcessSelectionPanel.getCmmiSelectedPracticeTree();
   * final List<Object> practices = selectedPracticeTree.findUserObjectByUserObjectType((GTreeNode) selectedPracticeTree
   * .getModel().getRoot(), CmmiPracticeNodeVo.class);
   * for (final Object object : practices) {
   * final CmmiPracticeNodeVo practiceNodeVo = (CmmiPracticeNodeVo) object;
   * selectedPracticeLst.add(practiceNodeVo.getPractice().getId());
   * }
   * return selectedPracticeLst;
   * }
   */

}

package com.sgu.infowksporga.jfx.perspective.cb;

import com.google.common.base.Objects;
import com.sgu.infowksporga.business.entity.Perspective;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class PerspectiveComboVo.
 * Used to list all available perspectives
 */
@Getter
@Setter
@Slf4j
public class ComboBoxPerspectiveVo {

  /** The perspective. */
  private Perspective perspective;

  /**
   * The Constructor.
   *
   * @param perspective the perspective
   */
  public ComboBoxPerspectiveVo(final Perspective perspective) {
    this.perspective = perspective;

  }

  @Override
  public int hashCode() {
    return Objects.hashCode(perspective);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof ComboBoxPerspectiveVo) {
      ComboBoxPerspectiveVo that = (ComboBoxPerspectiveVo) object;
      return Objects.equal(this.perspective, that.perspective);
    }
    return false;
  }

}
/*
 * protected void buildPerspectiveTreeNodes() {
 * // First get the XML content of the perspective
 * try {
 * if (perspectiveTreeNodes == null) {
 * String xmlConfig = perspective.getConfiguration();
 * if (PerspectiveConfigTypeEnum.URL.equals(PerspectiveConfigTypeEnum.valueOf(perspective.getConfigurationType()))) {
 * // Read File with the given URL
 * xmlConfig = UtilIO.readFile(new URL(xmlConfig));
 * }
 * // Now start to build tree recursively
 * }
 * } catch (MalformedURLException e) {
 * log.error(e.getMessage(), e);
 * throw new TechnicalException(e);
 * }
 * }
 * public static String replaceByUserCode(final String str) {
 * if (str != null) {
 * String userLogin = GUISessionProxy.getGuiSession().getCurrentUser();
 * return UtilString.replaceString(str, "${User}", userLogin);
 * }
 * return null;
 * }
 */
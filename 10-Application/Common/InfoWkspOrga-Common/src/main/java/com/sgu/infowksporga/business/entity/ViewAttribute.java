package com.sgu.infowksporga.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.sgu.core.framework.dao.jpa.entity.AbstractDescribedAuditedEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the view database table.
 */
@Entity
@Table(name = "view_attribute")
@Getter
@Setter
public class ViewAttribute extends AbstractDescribedAuditedEntity<Integer> {
  private static final long serialVersionUID = 1L;

  // ---------------------------------------------
  // ATTRIBUTE NAME Only for View Directory
  //----------------------------------------------

  /**
   * Identifies the Custom path filled by a user - View Directory Desk
   */
  public static final String FILE_EXPLORER_PATH = "FILE_EXPLORER_PATH";

  //---------------------------------------------
  // ATTRIBUTE NAME Only for View HTML
  //---------------------------------------------
  /**
   * Identifies the html content - View HTML
   */
  public static final String HTML_TEXT = "HTML_TEXT";

  /**
   * Identifies the html zoom to use at view creation - View HTML
   */
  public static final String HTML_ZOOM = "HTML_ZOOM";

  //-------------------------------------------------------
  // ATTRIBUTE NAME Only for View WEB
  //-------------------------------------------------------
  /**
   * The Constant WEB_URL.
   */
  public static final String WEB_URL = "WEB_URL";

  //---------------------------------------------------
  // ATTRIBUTE NAME Only for View WORKSAPCE_MAP
  //---------------------------------------------------
  /**
   * Identifies the workspace map zoom to use at view creation - View WORKSPACE_MAP
   */
  public static final String WORKSPACE_MAP_HTML = "WORKSPACE_MAP_HTML";

  /**
   * Identifies the workspace map zoom to use at view creation - View WORKSPACE_MAP
   */
  public static final String WORKSPACE_MAP_ZOOM = "WORKSPACE_MAP_ZOOM";

  @Id
  @GenericGenerator(name = "MySqlIdentifierGenerator", strategy = "com.sgu.core.framework.dao.mysql.MySqlIdentifierGenerator",
  parameters = { @Parameter(name = "tableName", value = "view_attribute") })
  @GeneratedValue(generator = "MySqlIdentifierGenerator")
  private Integer id;

  @Column(name = "view_id", nullable = false, length = 45)
  private Integer viewId;

  /**
   * Identifies the View Type (Package with .class name)',
   */
  @Column(nullable = true)
  private String value;

  /**
   * The Constructor.
   */
  public ViewAttribute() {
  }

  /** {@inheritDoc} */
  @Override
  public Integer getId() {
    return id;
  }

  /** {@inheritDoc} */
  @Override
  public void setId(final Integer id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("super", super.toString()).add("id", id).add("viewId", viewId).add("value", value)
                      .toString();
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), id);
  }

  @Override
  public boolean equals(Object object) {
    if (object instanceof ViewAttribute) {
      if (!super.equals(object))
        return false;
      ViewAttribute that = (ViewAttribute) object;
      return Objects.equal(this.id, that.id);
    }
    return false;
  }

}
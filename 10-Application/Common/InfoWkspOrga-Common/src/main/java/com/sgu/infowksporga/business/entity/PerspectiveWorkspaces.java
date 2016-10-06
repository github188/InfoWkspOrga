package com.sgu.infowksporga.business.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.MoreObjects;
import com.sgu.core.framework.dao.jpa.entity.AbstractAuditedEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for display the workspaces organization database table.
 */
@Entity
@Table(name = "perspective_workspaces")

/**
 * Gets the workspace parent id.
 *
 * @return the workspace parent id
 */
@Getter

/**
 * Sets the workspace parent id.
 *
 * @param workspaceParentId the workspace parent id
 */
@Setter
public class PerspectiveWorkspaces extends AbstractAuditedEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /** The id. */
  @Id
  @GenericGenerator(name = "MySqlIdentifierGenerator", strategy = "com.sgu.core.framework.dao.mysql.MySqlIdentifierGenerator",
  parameters = { @Parameter(name = "tableName", value = "perspective_workspaces") })
  @GeneratedValue(generator = "MySqlIdentifierGenerator")
  private Integer id;

  /** The perspective id. */
  @Column(name = "perspective_id", nullable = false)
  private Integer perspectiveId;

  /** The workspace id. */
  @Column(name = "workspace_id")
  private String workspaceId;

  /**
   * Index d'affichage du workspace
   */
  @Column(nullable = false)
  private Integer workspaceOrder = 0;

  /**
   * The Constructor.
   */
  public PerspectiveWorkspaces() {
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
    return MoreObjects.toStringHelper(this).add("super", super.toString()).add("id", id).add("perspectiveId", perspectiveId)
                      .add("workspaceId", workspaceId).toString();
  }

}
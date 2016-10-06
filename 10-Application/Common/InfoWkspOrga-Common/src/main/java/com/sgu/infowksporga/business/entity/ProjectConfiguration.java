package com.sgu.infowksporga.business.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.MoreObjects;
import com.sgu.core.framework.bean.PropertyTypeEnum;
import com.sgu.core.framework.dao.jpa.entity.PropertyEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the project_configuration database table.
 */
@Entity
@Table(name = "project_configuration")
@Setter
@Getter
public class ProjectConfiguration extends PropertyEntity<Integer> implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(name = "MySqlIdentifierGenerator", strategy = "com.sgu.core.framework.dao.mysql.MySqlIdentifierGenerator",
  parameters = { @Parameter(name = "tableName", value = "project_configuration") })
  @GeneratedValue(generator = "MySqlIdentifierGenerator")
  private Integer id;

  /** bi-directional many-to-one association to Project. */
  @ManyToOne
  @JoinColumn(name = "project_id", nullable = false)
  private Project project;

  /**
   * The Constructor.
   */
  public ProjectConfiguration() {
    super();
  }

  /**
   * The Constructor.
   *
   * @param toClone the object to clone
   */
  public ProjectConfiguration(final ProjectConfiguration toClone) {
    super(toClone);
  }

  /**
   * The Constructor.
   *
   * @param group the group
   * @param category the category
   * @param name the name
   * @param type the type
   * @param value the value
   * @param constraints the constraints
   * @param mandatory the mandatory
   * @param order the order
   */
  public ProjectConfiguration(final String group, final String category, final String name, final PropertyTypeEnum type, final String value,
                              final String constraints, final Boolean mandatory, final int order) {
    super(group, category, name, type, value, constraints, mandatory, order);
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

  /** {@inheritDoc} */
  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("super", super.toString()).add("project", project != null ? project.getName() : null)
                      .toString();
  }

  /**
   * Clone.
   *
   * @return Object
   * @autogenerated by CodeHaggis (http://sourceforge.net/projects/haggis) clone
   */
  @Override
  public ProjectConfiguration clone() {
    final ProjectConfiguration cloned = new ProjectConfiguration(this);

    final Project projectCloned = null; // project.;
    cloned.setProject(projectCloned);

    return cloned;
  }

}
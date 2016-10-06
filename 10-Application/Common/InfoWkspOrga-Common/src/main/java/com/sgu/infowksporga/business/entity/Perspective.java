package com.sgu.infowksporga.business.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.google.common.base.MoreObjects;
import com.sgu.core.framework.dao.jpa.converter.BooleanByteConverter;
import com.sgu.core.framework.dao.jpa.entity.AbstractDescribedAuditedEntity;
import com.sgu.infowksporga.business.entity.converter.PartageEnumConverter;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for display the workspaces organization database table.
 */
@Entity
@Table(name = "perspective")
@Getter
@Setter
public class Perspective extends AbstractDescribedAuditedEntity<Integer> {

  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(name = "MySqlIdentifierGenerator", strategy = "com.sgu.core.framework.dao.mysql.MySqlIdentifierGenerator",
  parameters = { @Parameter(name = "tableName", value = "perspective") })
  @GeneratedValue(generator = "MySqlIdentifierGenerator")
  private Integer id;

  @Column(length = 255)
  private String icon;

  @Column(nullable = false)
  @Convert(converter = BooleanByteConverter.class)
  private boolean enabled;

  @Column(length = 45, nullable = false)
  private String owner;

  /**
   * Index d'affichage de la préférence
   */
  @Column(nullable = false)
  private Integer order;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @Convert(converter = PartageEnumConverter.class)
  private PartageEnum partage;

  public Perspective() {
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
    return MoreObjects.toStringHelper(this).add("super", super.toString()).add("id", id).add("icon", icon).add("enabled", enabled)
                      .add("owner", owner).add("order", order).add("partage", partage).toString();
  }

}
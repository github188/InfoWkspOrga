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
 * The persistent class for the i18n database table.
 */
@Entity
@Table(name = "i18n")
@Getter
@Setter
public class I18n extends AbstractAuditedEntity<Integer> {
  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(name = "MySqlIdentifierGenerator", strategy = "com.sgu.core.framework.dao.mysql.MySqlIdentifierGenerator",
  parameters = { @Parameter(name = "tableName", value = "i18n") })
  @GeneratedValue(generator = "MySqlIdentifierGenerator")
  private Integer id;

  @Column(length = 2)
  private String language;

  @Column(length = 255)
  private String value;

  public I18n() {
    language = "fr";
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
    return MoreObjects.toStringHelper(this).add("super", super.toString()).add("id", id).add("language", language).add("value", value)
                      .toString();
  }

}
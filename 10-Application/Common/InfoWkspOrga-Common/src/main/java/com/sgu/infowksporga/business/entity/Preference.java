package com.sgu.infowksporga.business.entity;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.MoreObjects;
import com.sgu.core.framework.bean.PropertyTypeEnum;
import com.sgu.core.framework.dao.jpa.converter.BooleanByteConverter;
import com.sgu.core.framework.dao.jpa.entity.AbstractDescribedAuditedEntity;
import com.sgu.infowksporga.business.entity.converter.AttributeValueLocalizationEnumConverter;
import com.sgu.infowksporga.business.entity.converter.PartageEnumConverter;
import com.sgu.infowksporga.business.entity.converter.PropertyTypeEnumConverter;
import com.sgu.infowksporga.business.entity.enumeration.AttributeValueLocalizationEnum;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the preference database table.
 */
@Entity
@Table(name = "preference")
@Getter
@Setter
public class Preference extends AbstractDescribedAuditedEntity<Integer> {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(name = "MySqlIdentifierGenerator", strategy = "com.sgu.core.framework.dao.mysql.MySqlIdentifierGenerator",
  parameters = { @Parameter(name = "tableName", value = "preference") })
  @GeneratedValue(generator = "MySqlIdentifierGenerator")
  private Integer id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "parent_id")
  private Preference parent;

  @Length(max = 100)
  @NotBlank
  @NotNull
  private String category;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @Convert(converter = PropertyTypeEnumConverter.class)
  private PropertyTypeEnum type = PropertyTypeEnum.STRING;

  private String value;

  @Length(max = 500)
  private String constraints;

  @Column(nullable = false)
  @Convert(converter = BooleanByteConverter.class)
  private boolean updatable = true;

  @Column(nullable = false)
  @Convert(converter = BooleanByteConverter.class)
  private boolean enabled = true;

  /**
   * Index d'affichage de la préférence
   */
  @Column(nullable = false)
  private Integer order = 0;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @Convert(converter = AttributeValueLocalizationEnumConverter.class)
  private AttributeValueLocalizationEnum localization = AttributeValueLocalizationEnum.DATABASE;

  @Length(max = 45)
  @Column(nullable = false)
  private String owner;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @Convert(converter = PartageEnumConverter.class)
  private PartageEnum partage = PartageEnum.PUBLIC;

  /**
   * The Constructor.
   */
  public Preference() {
    super();
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
    return MoreObjects.toStringHelper(this).add("super", super.toString()).add("id", id).add("parent", parent).add("category", category)
                      .add("type", type).add("value", value).add("constraints", constraints).add("updatable", updatable)
                      .add("enabled", enabled).add("order", order).add("localization", localization).add("owner", owner)
                      .add("partage", partage).toString();
  }
}
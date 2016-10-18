package com.sgu.infowksporga.business.entity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.sgu.core.framework.dao.jpa.converter.BooleanByteConverter;
import com.sgu.core.framework.dao.jpa.entity.AbstractDescribedAuditedEntity;
import com.sgu.infowksporga.business.entity.converter.DockPosEnumConverter;
import com.sgu.infowksporga.business.entity.converter.PartageEnumConverter;
import com.sgu.infowksporga.business.entity.enumeration.DockPosEnum;
import com.sgu.infowksporga.business.entity.enumeration.PartageEnum;

import lombok.Getter;
import lombok.Setter;

/**
 * The persistent class for the view database table.
 */
@Entity
@Table(name = "view")
@Getter
@Setter
public class View extends AbstractDescribedAuditedEntity<Integer> {
  private static final long serialVersionUID = 1L;

  @Id
  @GenericGenerator(name = "MySqlIdentifierGenerator", strategy = "com.sgu.core.framework.dao.mysql.MySqlIdentifierGenerator",
  parameters = { @Parameter(name = "tableName", value = "view") })
  @GeneratedValue(generator = "MySqlIdentifierGenerator")
  private Integer id;

  @Column(name = "workspace_id", nullable = false, length = 45)
  private String workspaceId;

  /**
   * Identifies the Model Type (Package with .class name)',
   */
  @Column(name = "model_bean", nullable = false, length = 300)
  private String modelBean;

  /**
   * Identifies the Screen Type (Package with .class name)',
   */
  @Column(name = "screen_bean", nullable = false, length = 300)
  private String screenBean;

  /**
   * Identifies the Dock View Type (Package with .class name)',
   */
  @Column(name = "dock_node_bean", nullable = false, length = 300)
  private String dockNodeBean;

  @Column(name = "dock_pos", nullable = false, length = 300)
  @Enumerated(EnumType.STRING)
  @Convert(converter = DockPosEnumConverter.class)
  private DockPosEnum dockPos = DockPosEnum.RIGHT;

  @Column(nullable = false)
  @Convert(converter = BooleanByteConverter.class)
  private boolean nextSibling;

  @Column(name = "order", nullable = false)
  private Integer order;

  @NotNull
  private Double height;

  @NotNull
  private Double width;

  @Column(name = "name_color", nullable = true, length = 20)
  private String nameColor;

  @Column(name = "name_bg_color", nullable = true, length = 20)
  private String nameBgColor;

  /**
   * Identifies the View Type (Package with .class name)',
   */
  @Column(length = 300)
  private String icon;

  /**
   * code de regroupement d''une même famille de views
   */
  @Length(max = 50)
  @NotBlank
  @NotNull
  private String category = "Default";

  @Column(nullable = true, length = 500)
  private String tags;

  /**
   * CMMI relative practices separated by ";"
   */
  @Column(name = "cmmi_practices", nullable = true, length = 500)
  private String cmmiPractices;

  @Column(nullable = false, length = 45)
  private String owner;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  @Convert(converter = PartageEnumConverter.class)
  private PartageEnum partage = PartageEnum.PUBLIC;

  @OneToMany(mappedBy = "viewId", fetch = FetchType.EAGER)
  private Set<ViewAttribute> attributes;

  /**
   * The Constructor.
   */
  public View() {
    attributes = new HashSet<ViewAttribute>();
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

  /**
   * Gets the attributes as map.
   *
   * @return the attributes as map
   */
  public Map<String, ViewAttribute> getAttributesAsMap() {
    final Map<String, ViewAttribute> mapAttributes = new HashMap<String, ViewAttribute>(2);

    for (final ViewAttribute viewAttribute : attributes) {
      mapAttributes.put(viewAttribute.getName(), viewAttribute);
    }

    return mapAttributes;
  }

  /**
   * Adds the attribute.
   *
   * @param attribute the attribute
   */
  public void addAttribute(final ViewAttribute attribute) {
    attributes.add(attribute);
  }

  /**
   * Adds the attribute.
   *
   * @param id the id
   * @param viewId the view id
   * @param value the value
   */
  public void addAttribute(final Integer id, final Integer viewId, final String name, final String value) {
    final ViewAttribute attribute = new ViewAttribute(id, viewId, value);
    attribute.setName(name);
    addAttribute(attribute);
  }

  /**
   * Sets the attributes.
   *
   * @param mapAttributes the attributes
   */
  public void setAttributes(final Map<String, ViewAttribute> mapAttributes) {
    attributes = new HashSet<ViewAttribute>(mapAttributes.values());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(super.hashCode(), id);
  }

  @Override
  public boolean equals(final Object object) {
    if (object instanceof View) {
      if (!super.equals(object)) {
        return false;
      }
      final View that = (View) object;
      return Objects.equal(this.id, that.id);
    }
    return false;
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("super", super.toString()).add("id", id).add("workspaceId", workspaceId).add("modelBean", modelBean)
                      .add("screenBean", screenBean).add("dockNodeBean", dockNodeBean).add("dockPos", dockPos).add("nextSibling", nextSibling).add("order", order)
                      .add("height", height).add("width", width).add("nameColor", nameColor).add("nameBgColor", nameBgColor).add("icon", icon).add("category", category)
                      .add("tags", tags).add("cmmiPractices", cmmiPractices).add("owner", owner).add("partage", partage).add("attributes", attributes).toString();
  }

}
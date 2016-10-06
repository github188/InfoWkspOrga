//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source.
// G�n�r� le : 2016.08.19 � 09:10:57 PM CEST
//

package com.sgu.infowksporga.business.xml.jaxb.preference;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>Classe Java pour anonymous complex type.
 * <p>Le fragment de sch�ma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}xmlPreference" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="category" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="constraints" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="enabled" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="localization" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="order" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="owner" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="partage" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="updatable" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="value" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "xmlPreference" })
@XmlRootElement(name = "xmlPreference")
public class XmlPreference implements Serializable {

  private final static long serialVersionUID = 12349L;
  protected List<XmlPreference> xmlPreference;
  @XmlAttribute(name = "category")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String category;
  @XmlAttribute(name = "constraints")
  @XmlSchemaType(name = "anySimpleType")
  protected String constraints;
  @XmlAttribute(name = "description")
  @XmlSchemaType(name = "anySimpleType")
  protected String description;
  @XmlAttribute(name = "enabled")
  protected boolean enabled;
  @XmlAttribute(name = "id", required = true)
  protected Integer id;
  @XmlAttribute(name = "localization")
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String localization;
  @XmlAttribute(name = "name", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String name;
  @XmlAttribute(name = "order")
  protected Integer order;
  @XmlAttribute(name = "owner", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String owner;
  @XmlAttribute(name = "partage", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String partage;
  @XmlAttribute(name = "type", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String type;
  @XmlAttribute(name = "updatable")
  protected boolean updatable;
  @XmlAttribute(name = "value")
  @XmlSchemaType(name = "anySimpleType")
  protected String value;

  /**
   * Gets the value of the xmlPreference property.
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the xmlPreference property.
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getXmlPreference().add(newItem);
   * </pre>
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link XmlPreference }
   */
  public List<XmlPreference> getXmlPreference() {
    if (xmlPreference == null) {
      xmlPreference = new ArrayList<XmlPreference>();
    }
    return this.xmlPreference;
  }

  /**
   * Obtient la valeur de la propri�t� category.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getCategory() {
    return category;
  }

  /**
   * D�finit la valeur de la propri�t� category.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setCategory(String value) {
    this.category = value;
  }

  /**
   * Obtient la valeur de la propri�t� constraints.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getConstraints() {
    return constraints;
  }

  /**
   * D�finit la valeur de la propri�t� constraints.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setConstraints(String value) {
    this.constraints = value;
  }

  /**
   * Obtient la valeur de la propri�t� description.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getDescription() {
    return description;
  }

  /**
   * D�finit la valeur de la propri�t� description.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setDescription(String value) {
    this.description = value;
  }

  /**
   * Obtient la valeur de la propri�t� enabled.
   * 
   * @return
   *         possible object is
   *         {@link boolean }
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * D�finit la valeur de la propri�t� enabled.
   * 
   * @param value
   *          allowed object is
   *          {@link boolean }
   */
  public void setEnabled(boolean value) {
    this.enabled = value;
  }

  /**
   * Obtient la valeur de la propri�t� id.
   * 
   * @return
   *         possible object is
   *         {@link Integer }
   */
  public Integer getId() {
    return id;
  }

  /**
   * D�finit la valeur de la propri�t� id.
   * 
   * @param value
   *          allowed object is
   *          {@link Integer }
   */
  public void setId(Integer value) {
    this.id = value;
  }

  /**
   * Obtient la valeur de la propri�t� localization.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getLocalization() {
    return localization;
  }

  /**
   * D�finit la valeur de la propri�t� localization.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setLocalization(String value) {
    this.localization = value;
  }

  /**
   * Obtient la valeur de la propri�t� name.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getName() {
    return name;
  }

  /**
   * D�finit la valeur de la propri�t� name.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setName(String value) {
    this.name = value;
  }

  /**
   * Obtient la valeur de la propri�t� order.
   * 
   * @return
   *         possible object is
   *         {@link Integer }
   */
  public Integer getOrder() {
    return order;
  }

  /**
   * D�finit la valeur de la propri�t� order.
   * 
   * @param value
   *          allowed object is
   *          {@link Integer }
   */
  public void setOrder(Integer value) {
    this.order = value;
  }

  /**
   * Obtient la valeur de la propri�t� owner.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getOwner() {
    return owner;
  }

  /**
   * D�finit la valeur de la propri�t� owner.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setOwner(String value) {
    this.owner = value;
  }

  /**
   * Obtient la valeur de la propri�t� partage.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getPartage() {
    return partage;
  }

  /**
   * D�finit la valeur de la propri�t� partage.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setPartage(String value) {
    this.partage = value;
  }

  /**
   * Obtient la valeur de la propri�t� type.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getType() {
    return type;
  }

  /**
   * D�finit la valeur de la propri�t� type.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setType(String value) {
    this.type = value;
  }

  /**
   * Obtient la valeur de la propri�t� updatable.
   * 
   * @return
   *         possible object is
   *         {@link boolean }
   */
  public boolean isUpdatable() {
    return updatable;
  }

  /**
   * D�finit la valeur de la propri�t� updatable.
   * 
   * @param value
   *          allowed object is
   *          {@link boolean }
   */
  public void setUpdatable(boolean value) {
    this.updatable = value;
  }

  /**
   * Obtient la valeur de la propri�t� value.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getValue() {
    return value;
  }

  /**
   * D�finit la valeur de la propri�t� value.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setValue(String value) {
    this.value = value;
  }

}

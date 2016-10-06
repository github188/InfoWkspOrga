//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source.
// G�n�r� le : 2016.08.19 � 10:52:17 PM CEST
//

package com.sgu.infowksporga.business.xml.jaxb.perspective;

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
 *         &lt;element ref="{}xmlWorkspace" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="baseFolder" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="bold" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="category" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="childrenWrkspCreationEnabled" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="color" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="created_by" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="customer" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="description" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="enabled" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="icon" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}NMTOKEN" />
 *       &lt;attribute name="italic" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="layout" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="masterId" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="order" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="owner" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="parentId" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="partage" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="projectId" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="strike" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="tags" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="type" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="underline" use="required" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "xmlWorkspace" })
@XmlRootElement(name = "xmlWorkspace")
public class XmlWorkspace extends AbstractXmlPerspectiveTemplateTag implements Serializable {

  private final static long serialVersionUID = 12343L;
  protected List<XmlWorkspace> xmlWorkspace;
  @XmlAttribute(name = "baseFolder")
  @XmlSchemaType(name = "anySimpleType")
  protected String baseFolder;
  @XmlAttribute(name = "bold", required = true)
  protected boolean bold;
  @XmlAttribute(name = "category")
  @XmlSchemaType(name = "anySimpleType")
  protected String category;
  @XmlAttribute(name = "childrenWrkspCreationEnabled", required = true)
  protected boolean childrenWrkspCreationEnabled;
  @XmlAttribute(name = "color")
  @XmlSchemaType(name = "anySimpleType")
  protected String color;
  @XmlAttribute(name = "created_by", required = true)
  @XmlSchemaType(name = "anySimpleType")
  protected String createdBy;
  @XmlAttribute(name = "customer")
  @XmlSchemaType(name = "anySimpleType")
  protected String customer;
  @XmlAttribute(name = "description")
  @XmlSchemaType(name = "anySimpleType")
  protected String description;
  @XmlAttribute(name = "enabled", required = true)
  protected boolean enabled;
  @XmlAttribute(name = "icon")
  @XmlSchemaType(name = "anySimpleType")
  protected String icon;
  @XmlAttribute(name = "id", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NMTOKEN")
  protected String id;
  @XmlAttribute(name = "italic", required = true)
  protected boolean italic;
  @XmlAttribute(name = "layout")
  @XmlSchemaType(name = "anySimpleType")
  protected String layout;
  @XmlAttribute(name = "masterId")
  @XmlSchemaType(name = "anySimpleType")
  protected String masterId;
  @XmlAttribute(name = "name", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String name;
  @XmlAttribute(name = "order", required = true)
  protected Integer order;
  @XmlAttribute(name = "owner", required = true)
  @XmlSchemaType(name = "anySimpleType")
  protected String owner;
  @XmlAttribute(name = "parentId")
  @XmlSchemaType(name = "anySimpleType")
  protected String parentId;
  @XmlAttribute(name = "partage", required = true)
  @XmlSchemaType(name = "anySimpleType")
  protected String partage;
  @XmlAttribute(name = "projectId")
  protected Integer projectId;
  @XmlAttribute(name = "strike", required = true)
  protected boolean strike;
  @XmlAttribute(name = "tags")
  @XmlSchemaType(name = "anySimpleType")
  protected String tags;
  @XmlAttribute(name = "type", required = true)
  @XmlSchemaType(name = "anySimpleType")
  protected String type;
  @XmlAttribute(name = "underline", required = true)
  protected boolean underline;

  /**
   * Gets the value of the xmlWorkspace property.
   * <p>
   * This accessor method returns a reference to the live list,
   * not a snapshot. Therefore any modification you make to the
   * returned list will be present inside the JAXB object.
   * This is why there is not a <CODE>set</CODE> method for the xmlWorkspace property.
   * <p>
   * For example, to add a new item, do as follows:
   * 
   * <pre>
   * getXmlWorkspace().add(newItem);
   * </pre>
   * 
   * <p>
   * Objects of the following type(s) are allowed in the list
   * {@link XmlWorkspace }
   */
  public List<XmlWorkspace> getXmlWorkspace() {
    if (xmlWorkspace == null) {
      xmlWorkspace = new ArrayList<XmlWorkspace>();
    }
    return this.xmlWorkspace;
  }

  /**
   * Obtient la valeur de la propri�t� baseFolder.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getBaseFolder() {
    return baseFolder;
  }

  /**
   * D�finit la valeur de la propri�t� baseFolder.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setBaseFolder(String value) {
    this.baseFolder = value;
  }

  /**
   * Obtient la valeur de la propri�t� bold.
   */
  public boolean isBold() {
    return bold;
  }

  /**
   * D�finit la valeur de la propri�t� bold.
   */
  public void setBold(boolean value) {
    this.bold = value;
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
   * Obtient la valeur de la propri�t� childrenWrkspCreationEnabled.
   */
  public boolean isChildrenWrkspCreationEnabled() {
    return childrenWrkspCreationEnabled;
  }

  /**
   * D�finit la valeur de la propri�t� childrenWrkspCreationEnabled.
   */
  public void setChildrenWrkspCreationEnabled(boolean value) {
    this.childrenWrkspCreationEnabled = value;
  }

  /**
   * Obtient la valeur de la propri�t� color.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getColor() {
    return color;
  }

  /**
   * D�finit la valeur de la propri�t� color.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setColor(String value) {
    this.color = value;
  }

  /**
   * Obtient la valeur de la propri�t� createdBy.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * D�finit la valeur de la propri�t� createdBy.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setCreatedBy(String value) {
    this.createdBy = value;
  }

  /**
   * Obtient la valeur de la propri�t� customer.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getCustomer() {
    return customer;
  }

  /**
   * D�finit la valeur de la propri�t� customer.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setCustomer(String value) {
    this.customer = value;
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
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * D�finit la valeur de la propri�t� enabled.
   */
  public void setEnabled(boolean value) {
    this.enabled = value;
  }

  /**
   * Obtient la valeur de la propri�t� icon.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getIcon() {
    return icon;
  }

  /**
   * D�finit la valeur de la propri�t� icon.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setIcon(String value) {
    this.icon = value;
  }

  /**
   * Obtient la valeur de la propri�t� id.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getId() {
    return id;
  }

  /**
   * D�finit la valeur de la propri�t� id.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setId(String value) {
    this.id = value;
  }

  /**
   * Obtient la valeur de la propri�t� italic.
   */
  public boolean isItalic() {
    return italic;
  }

  /**
   * D�finit la valeur de la propri�t� italic.
   */
  public void setItalic(boolean value) {
    this.italic = value;
  }

  /**
   * Obtient la valeur de la propri�t� layout.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getLayout() {
    return layout;
  }

  /**
   * D�finit la valeur de la propri�t� layout.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setLayout(String value) {
    this.layout = value;
  }

  /**
   * Obtient la valeur de la propri�t� masterId.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getMasterId() {
    return masterId;
  }

  /**
   * D�finit la valeur de la propri�t� masterId.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setMasterId(String value) {
    this.masterId = value;
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
   * Obtient la valeur de la propri�t� parentId.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getParentId() {
    return parentId;
  }

  /**
   * D�finit la valeur de la propri�t� parentId.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setParentId(String value) {
    this.parentId = value;
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
   * Obtient la valeur de la propri�t� projectId.
   * 
   * @return
   *         possible object is
   *         {@link Integer }
   */
  public Integer getProjectId() {
    return projectId;
  }

  /**
   * D�finit la valeur de la propri�t� projectId.
   * 
   * @param value
   *          allowed object is
   *          {@link Integer }
   */
  public void setProjectId(Integer value) {
    this.projectId = value;
  }

  /**
   * Obtient la valeur de la propri�t� strike.
   */
  public boolean isStrike() {
    return strike;
  }

  /**
   * D�finit la valeur de la propri�t� strike.
   */
  public void setStrike(boolean value) {
    this.strike = value;
  }

  /**
   * Obtient la valeur de la propri�t� tags.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getTags() {
    return tags;
  }

  /**
   * D�finit la valeur de la propri�t� tags.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setTags(String value) {
    this.tags = value;
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
   * Obtient la valeur de la propri�t� underline.
   */
  public boolean isUnderline() {
    return underline;
  }

  /**
   * D�finit la valeur de la propri�t� underline.
   */
  public void setUnderline(boolean value) {
    this.underline = value;
  }

}

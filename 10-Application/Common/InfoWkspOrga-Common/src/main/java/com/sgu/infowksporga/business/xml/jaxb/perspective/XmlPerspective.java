//
// Ce fichier a �t� g�n�r� par l'impl�mentation de r�f�rence JavaTM Architecture for XML Binding (JAXB), v2.2.8-b130911.1802
// Voir <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Toute modification apport�e � ce fichier sera perdue lors de la recompilation du sch�ma source.
// G�n�r� le : 2016.08.19 � 10:52:17 PM CEST
//

package com.sgu.infowksporga.business.xml.jaxb.perspective;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element ref="{}xmlWorkspace"/>
 *       &lt;/sequence>
 *       &lt;attribute name="description" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="enabled" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="icon" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="order" use="required" type="{http://www.w3.org/2001/XMLSchema}integer" />
 *       &lt;attribute name="owner" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *       &lt;attribute name="partage" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "xmlWorkspace" })
@XmlRootElement(name = "xmlPerspective")
public class XmlPerspective extends AbstractXmlPerspectiveTemplateTag implements Serializable {

  private final static long serialVersionUID = 12343L;
  @XmlElement(required = true)
  protected XmlWorkspace xmlWorkspace;
  @XmlAttribute(name = "description", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String description;
  @XmlAttribute(name = "enabled", required = true)
  protected boolean enabled;
  @XmlAttribute(name = "icon", required = true)
  @XmlSchemaType(name = "anySimpleType")
  protected String icon;
  @XmlAttribute(name = "id", required = true)
  protected Integer id;
  @XmlAttribute(name = "name", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String name;
  @XmlAttribute(name = "order", required = true)
  protected Integer order;
  @XmlAttribute(name = "owner", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String owner;
  @XmlAttribute(name = "partage", required = true)
  @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
  @XmlSchemaType(name = "NCName")
  protected String partage;

  /**
   * Obtient la valeur de la propri�t� xmlWorkspace.
   * 
   * @return
   *         possible object is
   *         {@link XmlWorkspace }
   */
  public XmlWorkspace getXmlWorkspace() {
    return xmlWorkspace;
  }

  /**
   * D�finit la valeur de la propri�t� xmlWorkspace.
   * 
   * @param value
   *          allowed object is
   *          {@link XmlWorkspace }
   */
  public void setXmlWorkspace(XmlWorkspace value) {
    this.xmlWorkspace = value;
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
   *         {@link Integer }
   */
  public boolean isEnabled() {
    return enabled;
  }

  /**
   * D�finit la valeur de la propri�t� enabled.
   * 
   * @param value
   *          allowed object is
   *          {@link Integer }
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

}

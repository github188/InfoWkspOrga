//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a>
// Any modifications to this file will be lost upon recompilation of the source schema.
// Generated on: 2014.01.06 at 02:27:18 PM CET
//

package com.sgu.infowksporga.business.xml.jaxb.toolbar;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>Java class for anonymous complex type.
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;attribute name="tag-name" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "toolbarItem")
public class ToolbarItem implements Serializable {

  private final static long serialVersionUID = 12376L;
  @XmlAttribute(name = "name", required = true)
  @XmlSchemaType(name = "anySimpleType")
  protected String name;

  /**
   * Gets the value of the tagName property.
   * 
   * @return
   *         possible object is
   *         {@link String }
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the value of the tagName property.
   * 
   * @param value
   *          allowed object is
   *          {@link String }
   */
  public void setName(String value) {
    this.name = value;
  }

}

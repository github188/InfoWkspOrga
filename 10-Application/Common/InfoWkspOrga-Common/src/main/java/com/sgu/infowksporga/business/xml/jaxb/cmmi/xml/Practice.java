//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.11 at 03:43:00 PM CET 
//


package com.sgu.infowksporga.business.xml.jaxb.cmmi.xml;

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
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}label"/>
 *         &lt;element ref="{}description"/>
 *         &lt;element ref="{}indirectQuestion"/>
 *         &lt;element ref="{}directQuestion"/>
 *       &lt;/sequence>
 *       &lt;attribute name="code" use="required" type="{http://www.w3.org/2001/XMLSchema}NCName" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "label",
    "description",
    "indirectQuestion",
    "directQuestion"
})
@XmlRootElement(name = "practice")
public class Practice
    implements Serializable
{

    private final static long serialVersionUID = 12376L;
    @XmlElement(required = true)
    protected Label label;
    @XmlElement(required = true)
    protected Description description;
    @XmlElement(required = true)
    protected IndirectQuestion indirectQuestion;
    @XmlElement(required = true)
    protected DirectQuestion directQuestion;
    @XmlAttribute(name = "code", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    @XmlSchemaType(name = "NCName")
    protected String code;

    /**
     * Gets the value of the label property.
     * 
     * @return
     *     possible object is
     *     {@link Label }
     *     
     */
    public Label getLabel() {
        return label;
    }

    /**
     * Sets the value of the label property.
     * 
     * @param value
     *     allowed object is
     *     {@link Label }
     *     
     */
    public void setLabel(Label value) {
        this.label = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Gets the value of the indirectQuestion property.
     * 
     * @return
     *     possible object is
     *     {@link IndirectQuestion }
     *     
     */
    public IndirectQuestion getIndirectQuestion() {
        return indirectQuestion;
    }

    /**
     * Sets the value of the indirectQuestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link IndirectQuestion }
     *     
     */
    public void setIndirectQuestion(IndirectQuestion value) {
        this.indirectQuestion = value;
    }

    /**
     * Gets the value of the directQuestion property.
     * 
     * @return
     *     possible object is
     *     {@link DirectQuestion }
     *     
     */
    public DirectQuestion getDirectQuestion() {
        return directQuestion;
    }

    /**
     * Sets the value of the directQuestion property.
     * 
     * @param value
     *     allowed object is
     *     {@link DirectQuestion }
     *     
     */
    public void setDirectQuestion(DirectQuestion value) {
        this.directQuestion = value;
    }

    /**
     * Gets the value of the code property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets the value of the code property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCode(String value) {
        this.code = value;
    }

}

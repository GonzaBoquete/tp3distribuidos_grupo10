//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.28 a las 06:52:59 PM ART 
//


package com.stockearte.tp3_grupo10.soap.interfaces;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ordenDeCompra" type="{http://www.stockearte.com/tp3_grupo10/soap/interfaces}ordenDeCompraInfo"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "ordenDeCompra"
})
@XmlRootElement(name = "addOrdenDeCompraRequest")
public class AddOrdenDeCompraRequest {

    @XmlElement(required = true)
    protected OrdenDeCompraInfo ordenDeCompra;

    /**
     * Obtiene el valor de la propiedad ordenDeCompra.
     * 
     * @return
     *     possible object is
     *     {@link OrdenDeCompraInfo }
     *     
     */
    public OrdenDeCompraInfo getOrdenDeCompra() {
        return ordenDeCompra;
    }

    /**
     * Define el valor de la propiedad ordenDeCompra.
     * 
     * @param value
     *     allowed object is
     *     {@link OrdenDeCompraInfo }
     *     
     */
    public void setOrdenDeCompra(OrdenDeCompraInfo value) {
        this.ordenDeCompra = value;
    }

}

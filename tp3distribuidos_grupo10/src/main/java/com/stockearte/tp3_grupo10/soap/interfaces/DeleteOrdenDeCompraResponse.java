//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.11.09 a las 09:22:49 AM ART 
//


package com.stockearte.tp3_grupo10.soap.interfaces;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="ordenDeCompraServiceStatus" type="{http://www.stockearte.com/tp3_grupo10/soap/interfaces}ordenDeCompraServiceStatus" minOccurs="0"/&gt;
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
    "ordenDeCompraServiceStatus"
})
@XmlRootElement(name = "deleteOrdenDeCompraResponse")
public class DeleteOrdenDeCompraResponse {

    protected OrdenDeCompraServiceStatus ordenDeCompraServiceStatus;

    /**
     * Obtiene el valor de la propiedad ordenDeCompraServiceStatus.
     * 
     * @return
     *     possible object is
     *     {@link OrdenDeCompraServiceStatus }
     *     
     */
    public OrdenDeCompraServiceStatus getOrdenDeCompraServiceStatus() {
        return ordenDeCompraServiceStatus;
    }

    /**
     * Define el valor de la propiedad ordenDeCompraServiceStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link OrdenDeCompraServiceStatus }
     *     
     */
    public void setOrdenDeCompraServiceStatus(OrdenDeCompraServiceStatus value) {
        this.ordenDeCompraServiceStatus = value;
    }

}

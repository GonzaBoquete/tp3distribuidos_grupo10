//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.11.08 a las 11:35:30 PM ART 
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
 *         &lt;element name="tiendaServiceStatus" type="{http://www.stockearte.com/tp3_grupo10/soap/interfaces}tiendaServiceStatus" minOccurs="0"/&gt;
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
    "tiendaServiceStatus"
})
@XmlRootElement(name = "addTiendaResponse")
public class AddTiendaResponse {

    protected TiendaServiceStatus tiendaServiceStatus;

    /**
     * Obtiene el valor de la propiedad tiendaServiceStatus.
     * 
     * @return
     *     possible object is
     *     {@link TiendaServiceStatus }
     *     
     */
    public TiendaServiceStatus getTiendaServiceStatus() {
        return tiendaServiceStatus;
    }

    /**
     * Define el valor de la propiedad tiendaServiceStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link TiendaServiceStatus }
     *     
     */
    public void setTiendaServiceStatus(TiendaServiceStatus value) {
        this.tiendaServiceStatus = value;
    }

}

//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.27 a las 03:46:18 PM ART 
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
 *         &lt;element name="tiendaServiceStatus" type="{http://www.stockearte.com/tp3_grupo10/soap/interfaces}tiendaServiceStatus"/&gt;
 *         &lt;element name="tienda" type="{http://www.stockearte.com/tp3_grupo10/soap/interfaces}tiendaInfo"/&gt;
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
    "tiendaServiceStatus",
    "tienda"
})
@XmlRootElement(name = "addTiendaResponse")
public class AddTiendaResponse {

    @XmlElement(required = true)
    protected TiendaServiceStatus tiendaServiceStatus;
    @XmlElement(required = true)
    protected TiendaInfo tienda;

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

    /**
     * Obtiene el valor de la propiedad tienda.
     * 
     * @return
     *     possible object is
     *     {@link TiendaInfo }
     *     
     */
    public TiendaInfo getTienda() {
        return tienda;
    }

    /**
     * Define el valor de la propiedad tienda.
     * 
     * @param value
     *     allowed object is
     *     {@link TiendaInfo }
     *     
     */
    public void setTienda(TiendaInfo value) {
        this.tienda = value;
    }

}
//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.28 a las 01:07:45 AM ART 
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
 *         &lt;element name="codigoFiltro" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="codigoTienda" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
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
    "codigoFiltro",
    "codigoTienda"
})
@XmlRootElement(name = "UpdateTiendaDeFiltroRequest")
public class UpdateTiendaDeFiltroRequest {

    @XmlElement(required = true)
    protected String codigoFiltro;
    protected long codigoTienda;

    /**
     * Obtiene el valor de la propiedad codigoFiltro.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoFiltro() {
        return codigoFiltro;
    }

    /**
     * Define el valor de la propiedad codigoFiltro.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoFiltro(String value) {
        this.codigoFiltro = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoTienda.
     * 
     */
    public long getCodigoTienda() {
        return codigoTienda;
    }

    /**
     * Define el valor de la propiedad codigoTienda.
     * 
     */
    public void setCodigoTienda(long value) {
        this.codigoTienda = value;
    }

}
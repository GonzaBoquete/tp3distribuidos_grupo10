//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.11.06 a las 11:09:13 PM ART 
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
 *         &lt;element name="catalogoServiceStatus" type="{http://www.stockearte.com/tp3_grupo10/soap/interfaces}catalogoServiceStatus" minOccurs="0"/&gt;
 *         &lt;element name="catalogo" type="{http://www.stockearte.com/tp3_grupo10/soap/interfaces}catalogoInfo" minOccurs="0"/&gt;
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
    "catalogoServiceStatus",
    "catalogo"
})
@XmlRootElement(name = "addCatalogoResponse")
public class AddCatalogoResponse {

    protected CatalogoServiceStatus catalogoServiceStatus;
    protected CatalogoInfo catalogo;

    /**
     * Obtiene el valor de la propiedad catalogoServiceStatus.
     * 
     * @return
     *     possible object is
     *     {@link CatalogoServiceStatus }
     *     
     */
    public CatalogoServiceStatus getCatalogoServiceStatus() {
        return catalogoServiceStatus;
    }

    /**
     * Define el valor de la propiedad catalogoServiceStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogoServiceStatus }
     *     
     */
    public void setCatalogoServiceStatus(CatalogoServiceStatus value) {
        this.catalogoServiceStatus = value;
    }

    /**
     * Obtiene el valor de la propiedad catalogo.
     * 
     * @return
     *     possible object is
     *     {@link CatalogoInfo }
     *     
     */
    public CatalogoInfo getCatalogo() {
        return catalogo;
    }

    /**
     * Define el valor de la propiedad catalogo.
     * 
     * @param value
     *     allowed object is
     *     {@link CatalogoInfo }
     *     
     */
    public void setCatalogo(CatalogoInfo value) {
        this.catalogo = value;
    }

}

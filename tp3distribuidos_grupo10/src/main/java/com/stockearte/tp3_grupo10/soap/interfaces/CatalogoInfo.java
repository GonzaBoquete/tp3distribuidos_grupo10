//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.27 a las 03:46:18 PM ART 
//


package com.stockearte.tp3_grupo10.soap.interfaces;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para catalogoInfo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="catalogoInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="idCatalogo" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="idTienda" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "catalogoInfo", propOrder = {
    "idCatalogo",
    "idTienda"
})
public class CatalogoInfo {

    protected long idCatalogo;
    protected long idTienda;

    /**
     * Obtiene el valor de la propiedad idCatalogo.
     * 
     */
    public long getIdCatalogo() {
        return idCatalogo;
    }

    /**
     * Define el valor de la propiedad idCatalogo.
     * 
     */
    public void setIdCatalogo(long value) {
        this.idCatalogo = value;
    }

    /**
     * Obtiene el valor de la propiedad idTienda.
     * 
     */
    public long getIdTienda() {
        return idTienda;
    }

    /**
     * Define el valor de la propiedad idTienda.
     * 
     */
    public void setIdTienda(long value) {
        this.idTienda = value;
    }

}
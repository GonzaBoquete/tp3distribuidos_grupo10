//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.11.08 a las 11:22:26 PM ART 
//


package com.stockearte.tp3_grupo10.soap.interfaces;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="catalogos" type="{http://www.stockearte.com/tp3_grupo10/soap/interfaces}catalogoInfo" maxOccurs="unbounded" minOccurs="0"/&gt;
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
    "catalogos"
})
@XmlRootElement(name = "getAllCatalogosResponse")
public class GetAllCatalogosResponse {

    protected List<CatalogoInfo> catalogos;

    /**
     * Gets the value of the catalogos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the catalogos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCatalogos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CatalogoInfo }
     * 
     * 
     */
    public List<CatalogoInfo> getCatalogos() {
        if (catalogos == null) {
            catalogos = new ArrayList<CatalogoInfo>();
        }
        return this.catalogos;
    }

}

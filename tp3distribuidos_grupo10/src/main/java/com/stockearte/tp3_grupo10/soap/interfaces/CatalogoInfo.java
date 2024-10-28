//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.28 a las 06:19:31 PM ART 
//


package com.stockearte.tp3_grupo10.soap.interfaces;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="productos" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="productoCodigo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
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
    "idTienda",
    "productos"
})
public class CatalogoInfo {

    protected long idCatalogo;
    protected long idTienda;
    protected List<CatalogoInfo.Productos> productos;

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

    /**
     * Gets the value of the productos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the productos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProductos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CatalogoInfo.Productos }
     * 
     * 
     */
    public List<CatalogoInfo.Productos> getProductos() {
        if (productos == null) {
            productos = new ArrayList<CatalogoInfo.Productos>();
        }
        return this.productos;
    }


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
     *         &lt;element name="productoCodigo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
        "productoCodigo"
    })
    public static class Productos {

        @XmlElement(required = true)
        protected String productoCodigo;

        /**
         * Obtiene el valor de la propiedad productoCodigo.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getProductoCodigo() {
            return productoCodigo;
        }

        /**
         * Define el valor de la propiedad productoCodigo.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setProductoCodigo(String value) {
            this.productoCodigo = value;
        }

    }

}

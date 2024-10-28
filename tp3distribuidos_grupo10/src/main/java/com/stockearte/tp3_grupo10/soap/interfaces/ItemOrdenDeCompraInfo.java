//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.28 a las 01:07:45 AM ART 
//


package com.stockearte.tp3_grupo10.soap.interfaces;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para itemOrdenDeCompraInfo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="itemOrdenDeCompraInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="productoCodigo" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="ordenDeCompraId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "itemOrdenDeCompraInfo", propOrder = {
    "id",
    "cantidad",
    "productoCodigo",
    "ordenDeCompraId"
})
public class ItemOrdenDeCompraInfo {

    protected long id;
    protected int cantidad;
    protected long productoCodigo;
    protected long ordenDeCompraId;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     */
    public void setId(long value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidad.
     * 
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Define el valor de la propiedad cantidad.
     * 
     */
    public void setCantidad(int value) {
        this.cantidad = value;
    }

    /**
     * Obtiene el valor de la propiedad productoCodigo.
     * 
     */
    public long getProductoCodigo() {
        return productoCodigo;
    }

    /**
     * Define el valor de la propiedad productoCodigo.
     * 
     */
    public void setProductoCodigo(long value) {
        this.productoCodigo = value;
    }

    /**
     * Obtiene el valor de la propiedad ordenDeCompraId.
     * 
     */
    public long getOrdenDeCompraId() {
        return ordenDeCompraId;
    }

    /**
     * Define el valor de la propiedad ordenDeCompraId.
     * 
     */
    public void setOrdenDeCompraId(long value) {
        this.ordenDeCompraId = value;
    }

}

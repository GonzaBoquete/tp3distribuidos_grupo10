//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.11.09 a las 09:22:49 AM ART 
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
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/&gt;
 *         &lt;element name="productoCodigo" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="ordenDeCompraId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
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

    protected Long id;
    protected Integer cantidad;
    protected Long productoCodigo;
    protected Long ordenDeCompraId;

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidad.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Define el valor de la propiedad cantidad.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidad(Integer value) {
        this.cantidad = value;
    }

    /**
     * Obtiene el valor de la propiedad productoCodigo.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getProductoCodigo() {
        return productoCodigo;
    }

    /**
     * Define el valor de la propiedad productoCodigo.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setProductoCodigo(Long value) {
        this.productoCodigo = value;
    }

    /**
     * Obtiene el valor de la propiedad ordenDeCompraId.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrdenDeCompraId() {
        return ordenDeCompraId;
    }

    /**
     * Define el valor de la propiedad ordenDeCompraId.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrdenDeCompraId(Long value) {
        this.ordenDeCompraId = value;
    }

}

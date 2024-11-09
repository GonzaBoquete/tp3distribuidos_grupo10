//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.11.09 a las 09:22:49 AM ART 
//


package com.stockearte.tp3_grupo10.soap.interfaces;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para ordenDeCompraInfo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="ordenDeCompraInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="fecha" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/&gt;
 *         &lt;element name="estado" type="{http://www.stockearte.com/tp3_grupo10/soap/interfaces}EstadoOrdenInfo" minOccurs="0"/&gt;
 *         &lt;element name="idTienda" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="itemsOrdenCompra" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="itemOrdenDeCompraId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
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
@XmlType(name = "ordenDeCompraInfo", propOrder = {
    "id",
    "fecha",
    "estado",
    "idTienda",
    "itemsOrdenCompra"
})
public class OrdenDeCompraInfo {

    protected Long id;
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar fecha;
    @XmlSchemaType(name = "string")
    protected EstadoOrdenInfo estado;
    protected Long idTienda;
    protected List<OrdenDeCompraInfo.ItemsOrdenCompra> itemsOrdenCompra;

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
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecha(XMLGregorianCalendar value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link EstadoOrdenInfo }
     *     
     */
    public EstadoOrdenInfo getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link EstadoOrdenInfo }
     *     
     */
    public void setEstado(EstadoOrdenInfo value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad idTienda.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getIdTienda() {
        return idTienda;
    }

    /**
     * Define el valor de la propiedad idTienda.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setIdTienda(Long value) {
        this.idTienda = value;
    }

    /**
     * Gets the value of the itemsOrdenCompra property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a <CODE>set</CODE> method for the itemsOrdenCompra property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getItemsOrdenCompra().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link OrdenDeCompraInfo.ItemsOrdenCompra }
     * 
     * 
     */
    public List<OrdenDeCompraInfo.ItemsOrdenCompra> getItemsOrdenCompra() {
        if (itemsOrdenCompra == null) {
            itemsOrdenCompra = new ArrayList<OrdenDeCompraInfo.ItemsOrdenCompra>();
        }
        return this.itemsOrdenCompra;
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
     *         &lt;element name="itemOrdenDeCompraId" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
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
        "itemOrdenDeCompraId"
    })
    public static class ItemsOrdenCompra {

        protected Long itemOrdenDeCompraId;

        /**
         * Obtiene el valor de la propiedad itemOrdenDeCompraId.
         * 
         * @return
         *     possible object is
         *     {@link Long }
         *     
         */
        public Long getItemOrdenDeCompraId() {
            return itemOrdenDeCompraId;
        }

        /**
         * Define el valor de la propiedad itemOrdenDeCompraId.
         * 
         * @param value
         *     allowed object is
         *     {@link Long }
         *     
         */
        public void setItemOrdenDeCompraId(Long value) {
            this.itemOrdenDeCompraId = value;
        }

    }

}

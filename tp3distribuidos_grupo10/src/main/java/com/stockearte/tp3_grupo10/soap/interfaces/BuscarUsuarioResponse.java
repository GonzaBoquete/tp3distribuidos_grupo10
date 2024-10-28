//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.10.27 a las 11:37:03 PM ART 
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
 *         &lt;element name="usuarioServiceStatus" type="{http://www.stockearte.com/tp3_grupo10/soap/interfaces}usuarioServiceStatus"/&gt;
 *         &lt;element name="usuario" type="{http://www.stockearte.com/tp3_grupo10/soap/interfaces}usuarioInfo" minOccurs="0"/&gt;
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
    "usuarioServiceStatus",
    "usuario"
})
@XmlRootElement(name = "buscarUsuarioResponse")
public class BuscarUsuarioResponse {

    @XmlElement(required = true)
    protected UsuarioServiceStatus usuarioServiceStatus;
    protected UsuarioInfo usuario;

    /**
     * Obtiene el valor de la propiedad usuarioServiceStatus.
     * 
     * @return
     *     possible object is
     *     {@link UsuarioServiceStatus }
     *     
     */
    public UsuarioServiceStatus getUsuarioServiceStatus() {
        return usuarioServiceStatus;
    }

    /**
     * Define el valor de la propiedad usuarioServiceStatus.
     * 
     * @param value
     *     allowed object is
     *     {@link UsuarioServiceStatus }
     *     
     */
    public void setUsuarioServiceStatus(UsuarioServiceStatus value) {
        this.usuarioServiceStatus = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link UsuarioInfo }
     *     
     */
    public UsuarioInfo getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link UsuarioInfo }
     *     
     */
    public void setUsuario(UsuarioInfo value) {
        this.usuario = value;
    }

}

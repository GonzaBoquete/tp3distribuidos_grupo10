//
// Este archivo ha sido generado por Eclipse Implementation of JAXB v3.0.0 
// Visite https://eclipse-ee4j.github.io/jaxb-ri 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.11.09 a las 09:22:49 AM ART 
//


package com.stockearte.tp3_grupo10.soap.interfaces;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para EstadoOrdenInfo.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>
 * &lt;simpleType name="EstadoOrdenInfo"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="SOLICITADA"/&gt;
 *     &lt;enumeration value="RECHAZADA"/&gt;
 *     &lt;enumeration value="ACEPTADA"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "EstadoOrdenInfo")
@XmlEnum
public enum EstadoOrdenInfo {

    SOLICITADA,
    RECHAZADA,
    ACEPTADA;

    public String value() {
        return name();
    }

    public static EstadoOrdenInfo fromValue(String v) {
        return valueOf(v);
    }

}

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.05.19 at 03:36:18 PM CEST 
//


package es.caib.signatura.api;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TipoSello.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="TipoSello">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="FIRMANTE"/>
 *     &lt;enumeration value="CUSTODIA"/>
 *     &lt;enumeration value="ARCHIVADO"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "TipoSello", namespace = "http://www.caib.es.signatura.custodia")
@XmlEnum
public enum TipoSello {

    FIRMANTE,
    CUSTODIA,
    ARCHIVADO;

    public String value() {
        return name();
    }

    public static TipoSello fromValue(String v) {
        return valueOf(v);
    }

}

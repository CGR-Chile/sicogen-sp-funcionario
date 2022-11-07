//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci�n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder�n si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.10.15 a las 07:39:10 PM GMT-04:00 
//


package cl.contraloria.sicogen.webservices.validacion.carga.tdrmp;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para validacionRegla complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="validacionRegla"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="seccionArchivo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="estadoCarga" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *         &lt;element name="idRegla" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *         &lt;element name="mensajeError" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "validacionRegla", propOrder = {
    "seccionArchivo",
    "estadoCarga",
    "idRegla",
    "mensajeError"
})
public class ValidacionRegla {

    @XmlElement(required = true)
    protected String seccionArchivo;
    protected short estadoCarga;
    protected short idRegla;
    @XmlElement(required = true)
    protected String mensajeError;

    /**
     * Obtiene el valor de la propiedad seccionArchivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSeccionArchivo() {
        return seccionArchivo;
    }

    /**
     * Define el valor de la propiedad seccionArchivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSeccionArchivo(String value) {
        this.seccionArchivo = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoCarga.
     * 
     */
    public short getEstadoCarga() {
        return estadoCarga;
    }

    /**
     * Define el valor de la propiedad estadoCarga.
     * 
     */
    public void setEstadoCarga(short value) {
        this.estadoCarga = value;
    }

    /**
     * Obtiene el valor de la propiedad idRegla.
     * 
     */
    public short getIdRegla() {
        return idRegla;
    }

    /**
     * Define el valor de la propiedad idRegla.
     * 
     */
    public void setIdRegla(short value) {
        this.idRegla = value;
    }

    /**
     * Obtiene el valor de la propiedad mensajeError.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeError() {
        return mensajeError;
    }

    /**
     * Define el valor de la propiedad mensajeError.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeError(String value) {
        this.mensajeError = value;
    }

}

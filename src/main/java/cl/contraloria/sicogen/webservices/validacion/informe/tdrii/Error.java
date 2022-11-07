//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci—n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder‡n si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.11.26 a las 09:17:37 AM GMT-04:00 
//


package cl.contraloria.sicogen.webservices.validacion.informe.tdrii;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para error complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="error"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="codigoCuenta" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="estadoRegla" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *         &lt;element name="idRegla" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *         &lt;element name="tipoError" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *         &lt;element name="mensajeError" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="identificaSalida" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *         &lt;element name="seccion" type="{http://www.w3.org/2001/XMLSchema}short"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "error", propOrder = {
    "codigoCuenta",
    "estadoRegla",
    "idRegla",
    "tipoError",
    "mensajeError",
    "identificaSalida",
    "seccion"
})
public class Error {

    @XmlElement(required = true)
    protected String codigoCuenta;
    protected short estadoRegla;
    protected short idRegla;
    protected short tipoError;
    @XmlElement(required = true)
    protected String mensajeError;
    protected short identificaSalida;
    protected short seccion;

    /**
     * Obtiene el valor de la propiedad codigoCuenta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    /**
     * Define el valor de la propiedad codigoCuenta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCodigoCuenta(String value) {
        this.codigoCuenta = value;
    }

    /**
     * Obtiene el valor de la propiedad estadoRegla.
     * 
     */
    public short getEstadoRegla() {
        return estadoRegla;
    }

    /**
     * Define el valor de la propiedad estadoRegla.
     * 
     */
    public void setEstadoRegla(short value) {
        this.estadoRegla = value;
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
     * Obtiene el valor de la propiedad tipoError.
     * 
     */
    public short getTipoError() {
        return tipoError;
    }

    /**
     * Define el valor de la propiedad tipoError.
     * 
     */
    public void setTipoError(short value) {
        this.tipoError = value;
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

    /**
     * Obtiene el valor de la propiedad identificaSalida.
     * 
     */
    public short getIdentificaSalida() {
        return identificaSalida;
    }

    /**
     * Define el valor de la propiedad identificaSalida.
     * 
     */
    public void setIdentificaSalida(short value) {
        this.identificaSalida = value;
    }

    /**
     * Obtiene el valor de la propiedad seccion.
     * 
     */
    public short getSeccion() {
        return seccion;
    }

    /**
     * Define el valor de la propiedad seccion.
     * 
     */
    public void setSeccion(short value) {
        this.seccion = value;
    }

}

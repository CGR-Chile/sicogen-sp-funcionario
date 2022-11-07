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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="mensaje" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="listaErrores" type="{http://www.cgr.cl/OSB/SectorPublico/TDRII/V2/EXP/ValidacionInforme}listaErrores"/&gt;
 *         &lt;element name="excepcion" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fechaInicio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fechaFin" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "estado",
    "mensaje",
    "listaErrores",
    "excepcion",
    "fechaInicio",
    "fechaFin"
})
@XmlRootElement(name = "validarInformeResponse")
public class ValidarInformeResponse {

    @XmlElement(required = true)
    protected String estado;
    @XmlElement(required = true)
    protected String mensaje;
    @XmlElement(required = true)
    protected ListaErrores listaErrores;
    @XmlElement(required = true)
    protected String excepcion;
    @XmlElement(required = true)
    protected String fechaInicio;
    @XmlElement(required = true)
    protected String fechaFin;

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstado(String value) {
        this.estado = value;
    }

    /**
     * Obtiene el valor de la propiedad mensaje.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensaje() {
        return mensaje;
    }

    /**
     * Define el valor de la propiedad mensaje.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensaje(String value) {
        this.mensaje = value;
    }

    /**
     * Obtiene el valor de la propiedad listaErrores.
     * 
     * @return
     *     possible object is
     *     {@link ListaErrores }
     *     
     */
    public ListaErrores getListaErrores() {
        return listaErrores;
    }

    /**
     * Define el valor de la propiedad listaErrores.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaErrores }
     *     
     */
    public void setListaErrores(ListaErrores value) {
        this.listaErrores = value;
    }

    /**
     * Obtiene el valor de la propiedad excepcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExcepcion() {
        return excepcion;
    }

    /**
     * Define el valor de la propiedad excepcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExcepcion(String value) {
        this.excepcion = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaInicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaInicio() {
        return fechaInicio;
    }

    /**
     * Define el valor de la propiedad fechaInicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaInicio(String value) {
        this.fechaInicio = value;
    }

    /**
     * Obtiene el valor de la propiedad fechaFin.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFechaFin() {
        return fechaFin;
    }

    /**
     * Define el valor de la propiedad fechaFin.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFechaFin(String value) {
        this.fechaFin = value;
    }

}

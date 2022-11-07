//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci—n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder‡n si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.11.18 a las 05:09:58 PM GMT-04:00 
//


package cl.contraloria.sicogen.webservices.validacion.carga.pi;

import java.math.BigDecimal;
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
 *         &lt;element name="nombreArchivo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="varInforme" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="varPeriodo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="varEjercicio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="periodo" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="idEntidad" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="idInforme" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="usuario" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="tipoArchivo" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="documento" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
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
    "nombreArchivo",
    "varInforme",
    "varPeriodo",
    "varEjercicio",
    "periodo",
    "idEntidad",
    "idInforme",
    "usuario",
    "tipoArchivo",
    "documento"
})
@XmlRootElement(name = "ValidarCarga")
public class ValidarCarga {

    @XmlElement(required = true)
    protected String nombreArchivo;
    @XmlElement(required = true)
    protected String varInforme;
    @XmlElement(required = true)
    protected String varPeriodo;
    @XmlElement(required = true)
    protected String varEjercicio;
    @XmlElement(required = true)
    protected BigDecimal periodo;
    @XmlElement(required = true)
    protected BigDecimal idEntidad;
    @XmlElement(required = true)
    protected BigDecimal idInforme;
    @XmlElement(required = true)
    protected String usuario;
    @XmlElement(required = true)
    protected BigDecimal tipoArchivo;
    @XmlElement(required = true)
    protected String documento;

    /**
     * Obtiene el valor de la propiedad nombreArchivo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * Define el valor de la propiedad nombreArchivo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreArchivo(String value) {
        this.nombreArchivo = value;
    }

    /**
     * Obtiene el valor de la propiedad varInforme.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVarInforme() {
        return varInforme;
    }

    /**
     * Define el valor de la propiedad varInforme.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVarInforme(String value) {
        this.varInforme = value;
    }

    /**
     * Obtiene el valor de la propiedad varPeriodo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVarPeriodo() {
        return varPeriodo;
    }

    /**
     * Define el valor de la propiedad varPeriodo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVarPeriodo(String value) {
        this.varPeriodo = value;
    }

    /**
     * Obtiene el valor de la propiedad varEjercicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVarEjercicio() {
        return varEjercicio;
    }

    /**
     * Define el valor de la propiedad varEjercicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVarEjercicio(String value) {
        this.varEjercicio = value;
    }

    /**
     * Obtiene el valor de la propiedad periodo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getPeriodo() {
        return periodo;
    }

    /**
     * Define el valor de la propiedad periodo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setPeriodo(BigDecimal value) {
        this.periodo = value;
    }

    /**
     * Obtiene el valor de la propiedad idEntidad.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdEntidad() {
        return idEntidad;
    }

    /**
     * Define el valor de la propiedad idEntidad.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdEntidad(BigDecimal value) {
        this.idEntidad = value;
    }

    /**
     * Obtiene el valor de la propiedad idInforme.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdInforme() {
        return idInforme;
    }

    /**
     * Define el valor de la propiedad idInforme.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdInforme(BigDecimal value) {
        this.idInforme = value;
    }

    /**
     * Obtiene el valor de la propiedad usuario.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsuario() {
        return usuario;
    }

    /**
     * Define el valor de la propiedad usuario.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsuario(String value) {
        this.usuario = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoArchivo.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTipoArchivo() {
        return tipoArchivo;
    }

    /**
     * Define el valor de la propiedad tipoArchivo.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTipoArchivo(BigDecimal value) {
        this.tipoArchivo = value;
    }

    /**
     * Obtiene el valor de la propiedad documento.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDocumento() {
        return documento;
    }

    /**
     * Define el valor de la propiedad documento.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDocumento(String value) {
        this.documento = value;
    }

}

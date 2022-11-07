//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci—n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder‡n si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.10.28 a las 06:06:51 PM GMT-04:00 
//


package cl.contraloria.sicogen.webservices.reproceso.ic;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para informeType complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="informeType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="uuid" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="periodo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="capitulo" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="partida" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ejercicio" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="filuId" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "informeType", propOrder = {
    "uuid",
    "periodo",
    "capitulo",
    "partida",
    "ejercicio",
    "filuId"
})
public class InformeType {

    @XmlElement(required = true)
    protected String uuid;
    @XmlElement(required = true)
    protected String periodo;
    @XmlElement(required = true)
    protected String capitulo;
    @XmlElement(required = true)
    protected String partida;
    @XmlElement(required = true)
    protected String ejercicio;
    @XmlElement(required = true)
    protected String filuId;

    /**
     * Obtiene el valor de la propiedad uuid.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * Define el valor de la propiedad uuid.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUuid(String value) {
        this.uuid = value;
    }

    /**
     * Obtiene el valor de la propiedad periodo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPeriodo() {
        return periodo;
    }

    /**
     * Define el valor de la propiedad periodo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPeriodo(String value) {
        this.periodo = value;
    }

    /**
     * Obtiene el valor de la propiedad capitulo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCapitulo() {
        return capitulo;
    }

    /**
     * Define el valor de la propiedad capitulo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCapitulo(String value) {
        this.capitulo = value;
    }

    /**
     * Obtiene el valor de la propiedad partida.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartida() {
        return partida;
    }

    /**
     * Define el valor de la propiedad partida.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartida(String value) {
        this.partida = value;
    }

    /**
     * Obtiene el valor de la propiedad ejercicio.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEjercicio() {
        return ejercicio;
    }

    /**
     * Define el valor de la propiedad ejercicio.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEjercicio(String value) {
        this.ejercicio = value;
    }

    /**
     * Obtiene el valor de la propiedad filuId.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFiluId() {
        return filuId;
    }

    /**
     * Define el valor de la propiedad filuId.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFiluId(String value) {
        this.filuId = value;
    }

}

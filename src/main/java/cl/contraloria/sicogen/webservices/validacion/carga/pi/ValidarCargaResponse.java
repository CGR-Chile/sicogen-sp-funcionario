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
 *         &lt;element name="idFileUpload" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="estado" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="mensajeCarga" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="listaValidaciones" type="{http://www.cgr.cl/OSB/CargaArchivo/V2/DOM/ValidacionCarga}listaValidaciones" minOccurs="0"/&gt;
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
    "idFileUpload",
    "estado",
    "mensajeCarga",
    "listaValidaciones"
})
@XmlRootElement(name = "ValidarCargaResponse")
public class ValidarCargaResponse {

    @XmlElement(required = true)
    protected BigDecimal idFileUpload;
    @XmlElement(required = true)
    protected String estado;
    @XmlElement(required = true)
    protected String mensajeCarga;
    protected ListaValidaciones listaValidaciones;

    /**
     * Obtiene el valor de la propiedad idFileUpload.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIdFileUpload() {
        return idFileUpload;
    }

    /**
     * Define el valor de la propiedad idFileUpload.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIdFileUpload(BigDecimal value) {
        this.idFileUpload = value;
    }

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
     * Obtiene el valor de la propiedad mensajeCarga.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMensajeCarga() {
        return mensajeCarga;
    }

    /**
     * Define el valor de la propiedad mensajeCarga.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMensajeCarga(String value) {
        this.mensajeCarga = value;
    }

    /**
     * Obtiene el valor de la propiedad listaValidaciones.
     * 
     * @return
     *     possible object is
     *     {@link ListaValidaciones }
     *     
     */
    public ListaValidaciones getListaValidaciones() {
        return listaValidaciones;
    }

    /**
     * Define el valor de la propiedad listaValidaciones.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaValidaciones }
     *     
     */
    public void setListaValidaciones(ListaValidaciones value) {
        this.listaValidaciones = value;
    }

}

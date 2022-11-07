//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci—n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder‡n si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.10.21 a las 02:20:36 PM GMT-04:00 
//


package cl.contraloria.sicogen.webservices.validacion.informe.tdrmp;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para listaErrores complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="listaErrores"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="erroresDetalle" type="{http://www.cgr.cl/OSB/SectorPublico/TDRMP/V2/EXP/ValidacionInforme}erroresDetalle" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="erroresGeneral" type="{http://www.cgr.cl/OSB/SectorPublico/TDRMP/V2/EXP/ValidacionInforme}erroresGeneral" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaErrores", propOrder = {
    "erroresDetalle",
    "erroresGeneral"
})
public class ListaErrores {

    protected List<ErroresDetalle> erroresDetalle;
    protected List<ErroresGeneral> erroresGeneral;

    /**
     * Gets the value of the erroresDetalle property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the erroresDetalle property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErroresDetalle().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErroresDetalle }
     * 
     * 
     */
    public List<ErroresDetalle> getErroresDetalle() {
        if (erroresDetalle == null) {
            erroresDetalle = new ArrayList<ErroresDetalle>();
        }
        return this.erroresDetalle;
    }

    /**
     * Gets the value of the erroresGeneral property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the erroresGeneral property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getErroresGeneral().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ErroresGeneral }
     * 
     * 
     */
    public List<ErroresGeneral> getErroresGeneral() {
        if (erroresGeneral == null) {
            erroresGeneral = new ArrayList<ErroresGeneral>();
        }
        return this.erroresGeneral;
    }

}

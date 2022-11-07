//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci—n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder‡n si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.11.18 a las 05:09:58 PM GMT-04:00 
//


package cl.contraloria.sicogen.webservices.validacion.carga.pi;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para listaValidaciones complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="listaValidaciones"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="validacionRegla" type="{http://www.cgr.cl/OSB/CargaArchivo/V2/DOM/ValidacionCarga}validacionRegla" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaValidaciones", propOrder = {
    "validacionRegla"
})
public class ListaValidaciones {

    @XmlElement(required = true)
    protected List<ValidacionRegla> validacionRegla;

    /**
     * Gets the value of the validacionRegla property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the validacionRegla property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getValidacionRegla().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ValidacionRegla }
     * 
     * 
     */
    public List<ValidacionRegla> getValidacionRegla() {
        if (validacionRegla == null) {
            validacionRegla = new ArrayList<ValidacionRegla>();
        }
        return this.validacionRegla;
    }

}

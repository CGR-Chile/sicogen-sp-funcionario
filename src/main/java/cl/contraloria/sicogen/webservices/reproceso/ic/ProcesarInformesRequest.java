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
 *         &lt;element name="listaInformes" type="{http://www.cgr.cl/osb/informes/ic/v2/sigfe/reproceso}listaInformesType"/&gt;
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
    "listaInformes"
})
@XmlRootElement(name = "procesarInformesRequest")
public class ProcesarInformesRequest {

    @XmlElement(required = true)
    protected ListaInformesType listaInformes;

    /**
     * Obtiene el valor de la propiedad listaInformes.
     * 
     * @return
     *     possible object is
     *     {@link ListaInformesType }
     *     
     */
    public ListaInformesType getListaInformes() {
        return listaInformes;
    }

    /**
     * Define el valor de la propiedad listaInformes.
     * 
     * @param value
     *     allowed object is
     *     {@link ListaInformesType }
     *     
     */
    public void setListaInformes(ListaInformesType value) {
        this.listaInformes = value;
    }

}

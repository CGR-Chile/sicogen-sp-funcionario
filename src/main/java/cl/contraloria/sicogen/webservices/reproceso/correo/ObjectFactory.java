//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci—n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder‡n si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.11.17 a las 09:44:20 AM GMT-04:00 
//


package cl.contraloria.sicogen.webservices.reproceso.correo;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.contraloria.sicogen.webservices.reproceso.correo package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EnviarCorreoReprocesoRequest_QNAME = new QName("http://www.contraloria.cl/xml/esquemas", "enviarCorreoReprocesoRequest");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.contraloria.sicogen.webservices.reproceso.correo
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnviarCorreoReprocesoRequestType }
     * 
     */
    public EnviarCorreoReprocesoRequestType createEnviarCorreoReprocesoRequestType() {
        return new EnviarCorreoReprocesoRequestType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnviarCorreoReprocesoRequestType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.contraloria.cl/xml/esquemas", name = "enviarCorreoReprocesoRequest")
    public JAXBElement<EnviarCorreoReprocesoRequestType> createEnviarCorreoReprocesoRequest(EnviarCorreoReprocesoRequestType value) {
        return new JAXBElement<EnviarCorreoReprocesoRequestType>(_EnviarCorreoReprocesoRequest_QNAME, EnviarCorreoReprocesoRequestType.class, null, value);
    }

}

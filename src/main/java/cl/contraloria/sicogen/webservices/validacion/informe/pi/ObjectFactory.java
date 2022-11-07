//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantaci—n de la referencia de enlace (JAXB) XML v2.2.11 
// Visite <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Todas las modificaciones realizadas en este archivo se perder‡n si se vuelve a compilar el esquema de origen. 
// Generado el: 2020.11.18 a las 11:44:47 PM GMT-04:00 
//


package cl.contraloria.sicogen.webservices.validacion.informe.pi;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the cl.contraloria.sicogen.webservices.validacion.informe.pi package. 
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


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: cl.contraloria.sicogen.webservices.validacion.informe.pi
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ValidarInforme }
     * 
     */
    public ValidarInforme createValidarInforme() {
        return new ValidarInforme();
    }

    /**
     * Create an instance of {@link ValidarInformeResponse }
     * 
     */
    public ValidarInformeResponse createValidarInformeResponse() {
        return new ValidarInformeResponse();
    }

    /**
     * Create an instance of {@link ListaErrores }
     * 
     */
    public ListaErrores createListaErrores() {
        return new ListaErrores();
    }

    /**
     * Create an instance of {@link Error }
     * 
     */
    public Error createError() {
        return new Error();
    }

}

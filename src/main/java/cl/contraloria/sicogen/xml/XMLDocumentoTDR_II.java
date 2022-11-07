package cl.contraloria.sicogen.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TDRidentificacionIniciativasInversion", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLDocumentoTDR_II {

    @XmlElement(name="cabecera", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    CabeceraTDR_II cabecera;


    @XmlElement(name="Decreto", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    List<Decreto_II> Decreto_II;

    public XMLDocumentoTDR_II() {
        super();
    }


    public CabeceraTDR_II getCabecera() {
        return cabecera;
    }

    public void setCabecera(CabeceraTDR_II cabecera) {
        this.cabecera = cabecera;
    }


    public List<Decreto_II> getDecreto_II() {
        return Decreto_II;
    }


    public void setDecreto_II(List<Decreto_II> decreto_II) {
        Decreto_II = decreto_II;
    }

}

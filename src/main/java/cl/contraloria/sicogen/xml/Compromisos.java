package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Compromisos {

    @XmlElement(name="limiteMaximoCompromisos", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    private List<LimiteMaximoCompromiso> limiteMaximoCompromisos;

    public List<LimiteMaximoCompromiso> getLimiteMaximoCompromisos() {
        return limiteMaximoCompromisos;
    }

    public void setLimiteMaximoCompromisos(List<LimiteMaximoCompromiso> limiteMaximoCompromisos) {
        this.limiteMaximoCompromisos = limiteMaximoCompromisos;
    }
}

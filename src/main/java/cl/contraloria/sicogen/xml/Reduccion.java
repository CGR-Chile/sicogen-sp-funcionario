package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Reduccion {
    @XmlElement(name="linea", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    private List<Long> linea;

    public List<Long> getLinea() {
        return linea;
    }

    public void setLinea(List<Long> linea) {
        this.linea = linea;
    }
}

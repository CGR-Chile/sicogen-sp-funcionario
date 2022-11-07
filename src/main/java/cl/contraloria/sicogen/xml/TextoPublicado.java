package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class TextoPublicado {
    @XmlElement(name="linea", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    private List<String> linea;

    public List<String> getLinea() {
        return linea;
    }

    public void setLinea(List<String> linea) {
        this.linea = linea;
    }
}

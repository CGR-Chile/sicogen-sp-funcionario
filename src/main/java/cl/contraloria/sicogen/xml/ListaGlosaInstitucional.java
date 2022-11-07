package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListaGlosaInstitucional {

    @XmlElement(name="Glosa", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    List<Glosas> glosas;

    public List<Glosas> getGlosas() {
        return glosas;
    }

    public void setGlosas(List<Glosas> glosas) {
        this.glosas = glosas;
    }
}

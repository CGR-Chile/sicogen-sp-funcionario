package cl.contraloria.sicogen.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ListaGlosaPresupuestaria {


    @XmlElement(name="GlosaPresupuestaria", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    List<GlosaPresupuestaria> GlosaPresupuestaria;

    public List<GlosaPresupuestaria> getGlosaPresupuestaria() {
        return GlosaPresupuestaria;
    }

    public void setGlosaPresupuestaria(List<GlosaPresupuestaria> glosaPresupuestaria) {
        GlosaPresupuestaria = glosaPresupuestaria;
    }

    public ListaGlosaPresupuestaria() {
        super();
    }

}

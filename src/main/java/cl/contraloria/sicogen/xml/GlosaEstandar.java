package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GlosaEstandar {

    @XmlElement(name="descripcionConceptoFisico", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String descripcionConceptoFisico;
    @XmlElement(name="codigoGlosaEstandar", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoGlosaEstandar;
    @XmlElement(name="tipoConceptoFisico", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String tipoConceptoFisico;
    @XmlElement(name="monedaMonto", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String monedaMonto;
    @XmlElement(name="valorConceptoFisico", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String valorConceptoFisico;

    public GlosaEstandar() {
        super();

    }

    public String getDescripcionConceptoFisico() {
        return descripcionConceptoFisico;
    }

    public void setDescripcionConceptoFisico(String descripcionConceptoFisico) {
        this.descripcionConceptoFisico = descripcionConceptoFisico;
    }

    public String getCodigoGlosaEstandar() {
        return codigoGlosaEstandar;
    }

    public void setCodigoGlosaEstandar(String codigoGlosaEstandar) {
        this.codigoGlosaEstandar = codigoGlosaEstandar;
    }

    public String getTipoConceptoFisico() {
        return tipoConceptoFisico;
    }

    public void setTipoConceptoFisico(String tipoConceptoFisico) {
        this.tipoConceptoFisico = tipoConceptoFisico;
    }

    public String getMonedaMonto() {
        return monedaMonto;
    }

    public void setMonedaMonto(String monedaMonto) {
        this.monedaMonto = monedaMonto;
    }

    public String getValorConceptoFisico() {
        return valorConceptoFisico;
    }

    public void setValorConceptoFisico(String valorConceptoFisico) {
        this.valorConceptoFisico = valorConceptoFisico;
    }
}

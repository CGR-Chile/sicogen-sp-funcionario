package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class GlosaPresupuestaria {
    @XmlElement(name="codigoPartida", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoPartida;
    @XmlElement(name="nombrePartida", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String nombrePartida;
    @XmlElement(name="codigoCapitulo", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoCapitulo;
    @XmlElement(name="codigoPrograma", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoPrograma;
    @XmlElement(name="codigoSubtitulo", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoSubtitulo;
    @XmlElement(name="codigoItem", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoItem;
    @XmlElement(name="codigoAsignacion", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoAsignacion;
    @XmlElement(name="indicadorPresupuestoGlosa", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String indicadorPresupuestoGlosa;
    @XmlElement(name="tipoGlosa", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String tipoGlosa;
    @XmlElement(name="numeroGlosa", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String numeroGlosa;
    @XmlElement(name="textoPublicado", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    TextoPublicado textoPublicado;
    @XmlElement(name="incremento", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    Incremento incremento;
    @XmlElement(name="reduccion", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    Reduccion reduccion;

    public GlosaPresupuestaria() {
        super();
    }
    public String getCodigoPartida() {
        return codigoPartida;
    }
    public void setCodigoPartida(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }
    public String getNombrePartida() {
        return nombrePartida;
    }
    public void setNombrePartida(String nombrePartida) {
        this.nombrePartida = nombrePartida;
    }
    public String getCodigoCapitulo() {
        return codigoCapitulo;
    }
    public void setCodigoCapitulo(String codigoCapitulo) {
        this.codigoCapitulo = codigoCapitulo;
    }
    public String getCodigoPrograma() {
        return codigoPrograma;
    }
    public void setCodigoPrograma(String codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }
    public String getCodigoSubtitulo() {
        return codigoSubtitulo;
    }
    public void setCodigoSubtitulo(String codigoSubtitulo) {
        this.codigoSubtitulo = codigoSubtitulo;
    }
    public String getCodigoItem() {
        return codigoItem;
    }
    public void setCodigoItem(String codigoItem) {
        this.codigoItem = codigoItem;
    }
    public String getCodigoAsignacion() {
        return codigoAsignacion;
    }
    public void setCodigoAsignacion(String codigoAsignacion) {
        this.codigoAsignacion = codigoAsignacion;
    }
    public String getTipoGlosa() {
        return tipoGlosa;
    }
    public void setTipoGlosa(String tipoGlosa) {
        this.tipoGlosa = tipoGlosa;
    }
    public String getIndicadorPresupuestoGlosa() {
        return indicadorPresupuestoGlosa;
    }
    public void setIndicadorPresupuestoGlosa(String indicadorPresupuestoGlosa) {
        this.indicadorPresupuestoGlosa = indicadorPresupuestoGlosa;
    }
    public String getNumeroGlosa() {
        return numeroGlosa;
    }
    public void setNumeroGlosa(String numeroGlosa) {
        this.numeroGlosa = numeroGlosa;
    }

    public TextoPublicado getTextoPublicado() {
        return textoPublicado;
    }

    public void setTextoPublicado(TextoPublicado textoPublicado) {
        this.textoPublicado = textoPublicado;
    }

    public Incremento getIncremento() {
        return incremento;
    }

    public void setIncremento(Incremento incremento) {
        this.incremento = incremento;
    }

    public Reduccion getReduccion() {
        return reduccion;
    }

    public void setReduccion(Reduccion reduccion) {
        this.reduccion = reduccion;
    }
}

package cl.contraloria.sicogen.xml;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Decreto_II {


    @XmlElement(name="codigoPartida", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoPartida;
    @XmlElement(name="nombrePartida", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String nombrePartida;
    @XmlElement(name="codigoCapitulo", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoCapitulo;
    @XmlElement(name="nombreCapitulo", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String nombreCapitulo;
    @XmlElement(name="codigoPrograma", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoPrograma;
    @XmlElement(name="nombrePrograma", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String nombrePrograma;
    @XmlElement(name="identificacionProyectos", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    List<IdentificacionProyectos> identificacionProyectos = new ArrayList<IdentificacionProyectos>();
    @XmlElement(name="modificacionProyectos", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    List<ModificacionProyectos> modificacionProyectos = new ArrayList<ModificacionProyectos>();
    @XmlElement(name="limiteMaximoCompromisos", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    List<LimiteMaximoCompromiso> limiteMaximoCompromisos = new ArrayList<LimiteMaximoCompromiso>();

    @XmlElement(name="_IdDecreto_II", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String _IdDecreto_II;

    public Decreto_II() {
        super();
    }

    public Decreto_II(String codigoPartida, String nombrePartida,
                      String codigoCapitulo, String nombreCapitulo,
                      String codigoPrograma, String nombrePrograma) {
        super();
        this.codigoPartida = codigoPartida;
        this.nombrePartida = nombrePartida;
        this.codigoCapitulo = codigoCapitulo;
        this.nombreCapitulo = nombreCapitulo;
        this.codigoPrograma = codigoPrograma;
        this.nombrePrograma = nombrePrograma;
        this._IdDecreto_II = String.valueOf(UUID.randomUUID()).substring(0,7);
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
    public String getNombreCapitulo() {
        return nombreCapitulo;
    }
    public void setNombreCapitulo(String nombreCapitulo) {
        this.nombreCapitulo = nombreCapitulo;
    }
    public String getCodigoPrograma() {
        return codigoPrograma;
    }
    public void setCodigoPrograma(String codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }
    public String getNombrePrograma() {
        return nombrePrograma;
    }
    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }
    public List<IdentificacionProyectos> getIdentificacionProyectos() {
        return identificacionProyectos;
    }
    public void setIdentificacionProyectos(
            List<IdentificacionProyectos> identificacionProyectos) {
        this.identificacionProyectos = identificacionProyectos;
    }
    public List<ModificacionProyectos> getModificacionProyectos() {
        return modificacionProyectos;
    }
    public void setModificacionProyectos(
            List<ModificacionProyectos> modificacionProyectos) {
        this.modificacionProyectos = modificacionProyectos;
    }
    public List<LimiteMaximoCompromiso> getLimiteMaximoCompromisos() {
        return limiteMaximoCompromisos;
    }
    public void setLimiteMaximoCompromisos(
            List<LimiteMaximoCompromiso> limiteMaximoCompromisos) {
        this.limiteMaximoCompromisos = limiteMaximoCompromisos;
    }

    public String get_IdDecreto_II() {
        return _IdDecreto_II;
    }

    public void set_IdDecreto_II(String _IdDecreto_II) {
        this._IdDecreto_II = _IdDecreto_II;
    }

}

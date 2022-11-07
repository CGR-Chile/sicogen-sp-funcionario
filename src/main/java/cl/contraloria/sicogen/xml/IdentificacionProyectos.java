package cl.contraloria.sicogen.xml;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class IdentificacionProyectos {


    @XmlElement(name="codigoSubtitulo", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoSubtitulo;
    @XmlElement(name="codigoItem", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoItem;
    @XmlElement(name="moneda", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String moneda;
    @XmlElement(name="detalleIdentificacionProyectos", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    List<DetalleIdentificacionProyectos> detalleIdentificacionProyectos;
    @XmlElement(name="compromisosIdentificaciones", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    Compromisos compromisosIdentificaciones;

    @XmlElement(name="_idIdentificacionProyectos", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String _idIdentificacionProyectos;

    public IdentificacionProyectos() {
        super();
    }

    public IdentificacionProyectos(String codigoSubtitulo, String codigoItem,
                                   String moneda) {
        super();
        this.codigoSubtitulo = codigoSubtitulo;
        this.codigoItem = codigoItem;
        this.moneda = moneda;
        this._idIdentificacionProyectos = String.valueOf(UUID.randomUUID()).substring(0,7);
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

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public List<DetalleIdentificacionProyectos> getDetalleIdentificacionProyectos() {
        return detalleIdentificacionProyectos;
    }

    public void setDetalleIdentificacionProyectos(
            List<DetalleIdentificacionProyectos> detalleIdentificacionProyectos) {
        this.detalleIdentificacionProyectos = detalleIdentificacionProyectos;
    }

    public String get_idIdentificacionProyectos() {
        return _idIdentificacionProyectos;
    }

    public void set_idIdentificacionProyectos(String _idIdentificacionProyectos) {
        this._idIdentificacionProyectos = _idIdentificacionProyectos;
    }

    public Compromisos getCompromisosIdentificaciones() {
        return compromisosIdentificaciones;
    }

    public void setCompromisosIdentificaciones(Compromisos compromisosIdentificaciones) {
        this.compromisosIdentificaciones = compromisosIdentificaciones;
    }
}

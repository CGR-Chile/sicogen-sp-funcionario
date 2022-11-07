package cl.contraloria.sicogen.xml;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ModificacionProyectos {


    @XmlElement(name="codigoSubtitulo", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoSubtitulo;
    @XmlElement(name="codigoItem", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoItem;
    @XmlElement(name="moneda", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String moneda;
    @XmlElement(name="detalleModificacionProyectos", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    List<DetalleModificacionProyectos> detalleModificacionProyectos;
    @XmlElement(name="compromisosModificaciones", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    Compromisos compromisosModificaciones;

    @XmlElement(name="_IdModificacionProyectos", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String _IdModificacionProyectos;

    public ModificacionProyectos() {
        super();
    }

    public ModificacionProyectos(String codigoSubtitulo, String codigoItem,
                                 String moneda) {
        super();
        this.codigoSubtitulo = codigoSubtitulo;
        this.codigoItem = codigoItem;
        this.moneda = moneda;
        this._IdModificacionProyectos = String.valueOf(UUID.randomUUID()).substring(0,7);
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

    public List<DetalleModificacionProyectos> getDetalleModificacionProyectos() {
        return detalleModificacionProyectos;
    }

    public void setDetalleModificacionProyectos(
            List<DetalleModificacionProyectos> detalleModificacionProyectos) {
        this.detalleModificacionProyectos = detalleModificacionProyectos;
    }

    public String get_IdModificacionProyectos() {
        return _IdModificacionProyectos;
    }

    public void set_IdModificacionProyectos(String _IdModificacionProyectos) {
        this._IdModificacionProyectos = _IdModificacionProyectos;
    }

    public Compromisos getCompromisosModificaciones() {
        return compromisosModificaciones;
    }

    public void setCompromisosModificaciones(Compromisos compromisosModificaciones) {
        this.compromisosModificaciones = compromisosModificaciones;
    }
}

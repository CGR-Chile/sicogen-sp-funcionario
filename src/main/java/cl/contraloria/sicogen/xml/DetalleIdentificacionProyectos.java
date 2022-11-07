package cl.contraloria.sicogen.xml;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DetalleIdentificacionProyectos {

    @XmlElement(name="codigoBIP", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoBIP;
    @XmlElement(name="codigoInternoDecretoSIAP", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoInternoDecretoSIAP;
    @XmlElement(name="denominacion", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String denominacion;
    @XmlElement(name="montosAsignacion", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    List<MontosAsignacion> montosAsignacion;

    @XmlElement(name="_idDetalleIdentificacionProyectos", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String _idDetalleIdentificacionProyectos;

    public DetalleIdentificacionProyectos() {
        super();
    }

    public DetalleIdentificacionProyectos(String codigoBIP,
                                          String codigoInternoDecretoSIAP, String denominacion) {
        super();
        this.codigoBIP = codigoBIP;
        this.codigoInternoDecretoSIAP = codigoInternoDecretoSIAP;
        this.denominacion = denominacion;
        this._idDetalleIdentificacionProyectos = String.valueOf(UUID.randomUUID()).substring(0,7);
    }

    public String get_idDetalleIdentificacionProyectos() {
        return _idDetalleIdentificacionProyectos;
    }

    public void set_idDetalleIdentificacionProyectos(
            String _idDetalleIdentificacionProyectos) {
        this._idDetalleIdentificacionProyectos = _idDetalleIdentificacionProyectos;
    }

    public String getCodigoBIP() {
        return codigoBIP;
    }

    public void setCodigoBIP(String codigoBIP) {
        this.codigoBIP = codigoBIP;
    }

    public String getCodigoInternoDecretoSIAP() {
        return codigoInternoDecretoSIAP;
    }

    public void setCodigoInternoDecretoSIAP(String codigoInternoDecretoSIAP) {
        this.codigoInternoDecretoSIAP = codigoInternoDecretoSIAP;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public List<MontosAsignacion> getMontosAsignacion() {
        return montosAsignacion;
    }

    public void setMontosAsignacion(List<MontosAsignacion> montosAsignacion) {
        this.montosAsignacion = montosAsignacion;
    }


}

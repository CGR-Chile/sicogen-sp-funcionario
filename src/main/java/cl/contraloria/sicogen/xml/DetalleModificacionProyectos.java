package cl.contraloria.sicogen.xml;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DetalleModificacionProyectos {


    @XmlElement(name="codigoBIP", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoBIP;
    @XmlElement(name="codigoInternoDecretoSIAP", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoInternoDecretoSIAP;
    @XmlElement(name="denominacion", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String denominacion;

    @XmlElement(name="montosAsignacion", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    List<MontosAsignacion> montosAsignacion;

    @XmlElement(name="_idDetalleModificacionProyectos", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String _idDetalleModificacionProyectos;

    public DetalleModificacionProyectos() {
        super();
    }

    public String getCodigoBIP() {
        return codigoBIP;
    }

    public DetalleModificacionProyectos(String codigoBIP,
                                        String codigoInternoDecretoSIAP, String denominacion) {
        super();
        this.codigoBIP = codigoBIP;
        this.codigoInternoDecretoSIAP = codigoInternoDecretoSIAP;
        this.denominacion = denominacion;
        this._idDetalleModificacionProyectos = String.valueOf(UUID.randomUUID()).substring(0,7);
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

    public String get_idDetalleModificacionProyectos() {
        return _idDetalleModificacionProyectos;
    }

    public void set_idDetalleModificacionProyectos(
            String _idDetalleModificacionProyectos) {
        this._idDetalleModificacionProyectos = _idDetalleModificacionProyectos;
    }
}

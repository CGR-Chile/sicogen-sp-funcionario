package cl.contraloria.sicogen.xml;

import java.util.List;
import java.util.UUID;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class LimiteMaximoCompromiso {

    @XmlElement(name="codigoBIP", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoBIP;
    @XmlElement(name="codigoInternoDecretoSIAP", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoInternoDecretoSIAP;

    @XmlElement(name="denominacion", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String denominacion;
    @XmlElement(name="moneda", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String moneda;

    @XmlElement(name="montosAnio", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    List<CompromisoFuturo> montosAnio;

    @XmlElement(name="_idLimiteMaximoCompromiso", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String _idLimiteMaximoCompromiso;

    public LimiteMaximoCompromiso() {
        super();
    }

    public LimiteMaximoCompromiso(String codigoBIP,
                                  String codigoInternoDecretoSIAP, String denominacion, String moneda) {
        super();
        this.codigoBIP = codigoBIP;
        this.codigoInternoDecretoSIAP = codigoInternoDecretoSIAP;
        this.denominacion = denominacion;
        this.moneda = moneda;
        this._idLimiteMaximoCompromiso = String.valueOf(UUID.randomUUID()).substring(0,7);
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

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public List<CompromisoFuturo> getMontosAnio() {
        return montosAnio;
    }

    public void setMontosAnio(List<CompromisoFuturo> montosAnio) {
        this.montosAnio = montosAnio;
    }

    public String get_idLimiteMaximoCompromiso() {
        return _idLimiteMaximoCompromiso;
    }

    public void set_idLimiteMaximoCompromiso(String _idLimiteMaximoCompromiso) {
        this._idLimiteMaximoCompromiso = _idLimiteMaximoCompromiso;
    }

}

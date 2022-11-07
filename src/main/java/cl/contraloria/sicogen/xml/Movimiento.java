package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Movimiento {

    @XmlAttribute(name="codigoCuenta")
    String codigoCuenta;

    @XmlAttribute(name="tipoCuenta")
    String tipoCuenta;
    @XmlAttribute(name="tipoMoneda")
    String tipoMoneda;
    @XmlAttribute(name="tipoModificacionCuenta")
    String tipoModificacionCuenta;

    @XmlAttribute(name="nivel")
    String nivel;

    @XmlElement(name="subtitulo", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String subtitulo;
    @XmlElement(name="item", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String item;
    @XmlElement(name="asignacion", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String asignacion;
    @XmlElement(name="subAsignacion", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String subAsignacion;
    @XmlElement(name="denominacion", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String denominacion;
    @XmlElement(name="montoIncremento", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    int montoIncremento;
    @XmlElement(name="montoReduccion", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    int montoReduccion;


    public Movimiento() {
        super();
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    //	public String getTipoModificacionCuenta() {
//		return tipoModificacionCuenta;
//	}
//	public void setTipoModificacionCuenta(String tipoModificacionCuenta) {
//		this.tipoModificacionCuenta = tipoModificacionCuenta;
//	}
    public int getMontoIncremento() {
        return montoIncremento;
    }
    public void setMontoIncremento(int montoIncremento) {
        this.montoIncremento = montoIncremento;
    }
    public int getMontoReduccion() {
        return montoReduccion;
    }
    public void setMontoReduccion(int montoReduccion) {
        this.montoReduccion = montoReduccion;
    }
    public String getSubtitulo() {
        return subtitulo;
    }
    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }
    public String getItem() {
        return item;
    }
    public void setItem(String item) {
        this.item = item;
    }
    public String getAsignacion() {
        return asignacion;
    }
    public void setAsignacion(String asignacion) {
        this.asignacion = asignacion;
    }
    public String getSubAsignacion() {
        return subAsignacion;
    }
    public void setSubAsignacion(String subAsignacion) {
        this.subAsignacion = subAsignacion;
    }
    public String getDenominacion() {
        return denominacion;
    }
    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getCodigoCuenta() {
        return codigoCuenta;
    }
    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }
    public String getTipoMoneda() {
        return tipoMoneda;
    }
    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public void setTipoModificacionCuenta(String tipoModificacionCuenta) {
        this.tipoModificacionCuenta = tipoModificacionCuenta;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }
}

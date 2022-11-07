package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class MontosAsignacion {

    @XmlElement(name="codigoAsignacion", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String codigoAsignacion;
    @XmlElement(name="nombreAsignacion", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String nombreAsignacion;
    @XmlElement(name="monto", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String monto;

    public MontosAsignacion() {
        super();
    }

    public String getCodigoAsignacion() {
        return codigoAsignacion;
    }

    public void setCodigoAsignacion(String codigoAsignacion) {
        this.codigoAsignacion = codigoAsignacion;
    }

    public String getNombreAsignacion() {
        return nombreAsignacion;
    }

    public void setNombreAsignacion(String nombreAsignacion) {
        this.nombreAsignacion = nombreAsignacion;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    @Override
    public String toString() {
        return "Update MontosAsignacion [codigoAsignacion=" + codigoAsignacion
                + ", nombreAsignacion=" + nombreAsignacion + ", monto=" + monto
                + "]";
    }


}

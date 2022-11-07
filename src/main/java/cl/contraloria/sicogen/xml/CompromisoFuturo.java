package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CompromisoFuturo {

    @XmlElement(name="anio", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String anio;
    @XmlElement(name="monto", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String monto;

    public String getAnio() {
        return anio;
    }
    public void setAnio(String anio) {
        this.anio = anio;
    }
    public String getMonto() {
        return monto;
    }
    public void setMonto(String monto) {
        this.monto = monto;
    }
//	public CompromisoFuturo(String anio, String monto) {
//		super();
//		this.anio = anio;
//		this.monto = monto;
//	}

}

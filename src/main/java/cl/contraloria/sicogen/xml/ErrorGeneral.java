package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorGeneral {

    @XmlElement(name="estadoRegla")
    private String estadoRegla;
    @XmlElement(name="idRegla")
    private String idRegla;
    @XmlElement(name="mensajeError")
    private String mensajeError;
    @XmlElement(name="tipoError")
    private String tipoError;
    @XmlElement(name="identificaSalida")
    private String identificaSalida;

    public String getEstadoRegla() {
        return estadoRegla;
    }

    public void setEstadoRegla(String estadoRegla) {
        this.estadoRegla = estadoRegla;
    }

    public String getIdRegla() {
        return idRegla;
    }

    public void setIdRegla(String idRegla) {
        this.idRegla = idRegla;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    public String getTipoError() {
        return tipoError;
    }

    public void setTipoError(String tipoError) {
        this.tipoError = tipoError;
    }

    public String getIdentificaSalida() {
        return identificaSalida;
    }

    public void setIdentificaSalida(String identificaSalida) {
        this.identificaSalida = identificaSalida;
    }
}

package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ErrorDetalle {

    @XmlElement(name="codigoPartida")
    private String codigoPartida;
    @XmlElement(name="codigoCapitulo")
    private String codigoCapitulo;
    @XmlElement(name="codigoPrograma")
    private String codigoPrograma;
    @XmlElement(name="cuenta")
    private String cuenta;
    @XmlElement(name="mensaje")
    private String mensaje;
    @XmlElement(name="tipoError")
    private String tipoError;

    public String getCodigoPartida() {
        return codigoPartida;
    }

    public void setCodigoPartida(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }

    public String getCodigoCapitulo() {
        return codigoCapitulo;
    }

    public void setCodigoCapitulo(String codigoCapitulo) {
        this.codigoCapitulo = codigoCapitulo;
    }

    public String getCodigoPrograma() {
        return codigoPrograma;
    }

    public void setCodigoPrograma(String codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipoError() {
        return tipoError;
    }

    public void setTipoError(String tipoError) {
        this.tipoError = tipoError;
    }
}

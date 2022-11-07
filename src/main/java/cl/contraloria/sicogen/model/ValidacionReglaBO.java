package cl.contraloria.sicogen.model;

public class ValidacionReglaBO {
    private String seccionArchivo;
    private short estadoCarga;
    private short idRegla;
    private String mensajeError;

    public String getSeccionArchivo() {
        return seccionArchivo;
    }

    public void setSeccionArchivo(String seccionArchivo) {
        this.seccionArchivo = seccionArchivo;
    }

    public short getEstadoCarga() {
        return estadoCarga;
    }

    public void setEstadoCarga(short estadoCarga) {
        this.estadoCarga = estadoCarga;
    }

    public short getIdRegla() {
        return idRegla;
    }

    public void setIdRegla(short idRegla) {
        this.idRegla = idRegla;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
}

package cl.contraloria.sicogen.model;

import java.util.List;

public class CuentaTDRMP {
    private String subtitulo;
    private String item;
    private String asignacion;
    private String subasignacion;
    private String denominacion;
    private String incremento;
    private String reduccion;
    private String mensajesErrores;
    private Boolean error;
    private List<ErrorDetalle> erroresDetalle;

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

    public String getSubasignacion() {
        return subasignacion;
    }

    public void setSubasignacion(String subasignacion) {
        this.subasignacion = subasignacion;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getIncremento() {
        return incremento;
    }

    public void setIncremento(String incremento) {
        this.incremento = incremento;
    }

    public String getReduccion() {
        return reduccion;
    }

    public void setReduccion(String reduccion) {
        this.reduccion = reduccion;
    }

    public String getMensajesErrores() {
        return mensajesErrores;
    }

    public void setMensajesErrores(String mensajesErrores) {
        this.mensajesErrores = mensajesErrores;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<ErrorDetalle> getErroresDetalle() {
        return erroresDetalle;
    }

    public void setErroresDetalle(List<ErrorDetalle> erroresDetalle) {
        this.erroresDetalle = erroresDetalle;
    }
}

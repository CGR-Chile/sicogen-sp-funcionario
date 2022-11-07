package cl.contraloria.sicogen.model;

import java.util.List;

public class InformeTDRRV {
    private String ejercicio;
    private String mes;
    private String dia;
    private String sectorResponsable;
    private String montoTotal;
    private String idDecreto;
    private String tipoRegistro;
    private String numeroRegistro;
    private String fechaRegistro;
    private List<ErrorGenerico> erroresGenericos;
    private List<DetalleInformeTDRMP> detalle;

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getSectorResponsable() {
        return sectorResponsable;
    }

    public void setSectorResponsable(String sectorResponsable) {
        this.sectorResponsable = sectorResponsable;
    }

    public String getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(String montoTotal) {
        this.montoTotal = montoTotal;
    }

    public String getIdDecreto() {
        return idDecreto;
    }

    public void setIdDecreto(String idDecreto) {
        this.idDecreto = idDecreto;
    }

    public String getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(String tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }

    public String getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public List<DetalleInformeTDRMP> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<DetalleInformeTDRMP> detalle) {
        this.detalle = detalle;
    }

    public List<ErrorGenerico> getErroresGenericos() {
        return erroresGenericos;
    }

    public void setErroresGenericos(List<ErrorGenerico> erroresGenericos) {
        this.erroresGenericos = erroresGenericos;
    }
}

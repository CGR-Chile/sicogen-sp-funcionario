package cl.contraloria.sicogen.model;

import java.util.List;

public class DatosInformePIDTO {

    private String estadoValidacion;
    private String tipoInforme;
    private String nombreInforme;
    private String periodo;
    private String ejercicio;
    private String usuario;
    private String entdad;
    private Integer idFileUp;
    private List<ErrorGenerico> erroresGenericos;
    private List<DatosEntidadInformePIDTO> listaEntidades;

    public String getEstadoValidacion() {
        return estadoValidacion;
    }

    public void setEstadoValidacion(String estadoValidacion) {
        this.estadoValidacion = estadoValidacion;
    }

    public String getTipoInforme() {
        return tipoInforme;
    }

    public void setTipoInforme(String tipoInforme) {
        this.tipoInforme = tipoInforme;
    }

    public String getNombreInforme() {
        return nombreInforme;
    }

    public void setNombreInforme(String nombreInforme) {
        this.nombreInforme = nombreInforme;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getEntdad() {
        return entdad;
    }

    public void setEntdad(String entdad) {
        this.entdad = entdad;
    }

    public List<ErrorGenerico> getErroresGenericos() {
        return erroresGenericos;
    }

    public void setErroresGenericos(List<ErrorGenerico> erroresGenericos) {
        this.erroresGenericos = erroresGenericos;
    }

    public Integer getIdFileUp() {
        return idFileUp;
    }

    public void setIdFileUp(Integer idFileUp) {
        this.idFileUp = idFileUp;
    }

    public List<DatosEntidadInformePIDTO> getListaEntidades() {
        return listaEntidades;
    }

    public void setListaEntidades(List<DatosEntidadInformePIDTO> listaEntidades) {
        this.listaEntidades = listaEntidades;
    }
}

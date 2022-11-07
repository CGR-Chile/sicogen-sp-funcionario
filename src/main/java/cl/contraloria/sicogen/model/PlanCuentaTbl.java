package cl.contraloria.sicogen.model;

public class PlanCuentaTbl {

    private String titulo;
    private String grupo;
    private String subGrupo;
    private String codCta;
    private String idEjercicio;
    private String agrDesc;

    private String userCreator;
    private String usoAuxiliar;
    private String imputacionPresup;
    private String clasificacion;
    private String cartera;
    private String tipoSaldo;

    /*Aun no estan siendo utilizados*/
    private String asociacionGasto;
    private String asociacionIngreso;
    private String periodidosHabilitados;

    public String getIdEjercicio() {
        return idEjercicio;
    }
    public void setIdEjercicio(String idEjercicio) {
        this.idEjercicio = idEjercicio;
    }
    public String getAgrDesc() {
        return agrDesc;
    }
    public void setAgrDesc(String agrDesc) {
        this.agrDesc = agrDesc;
    }
    public String getUserCreator() {
        return userCreator;
    }
    public void setUserCreator(String userCreator) {
        this.userCreator = userCreator;
    }
    public String getUsoAuxiliar() {
        return usoAuxiliar;
    }
    public void setUsoAuxiliar(String usoAuxiliar) {
        this.usoAuxiliar = usoAuxiliar;
    }
    public String getImputacionPresup() {
        return imputacionPresup;
    }
    public void setImputacionPresup(String imputacionPresup) {
        this.imputacionPresup = imputacionPresup;
    }
    public String getClasificacion() {
        return clasificacion;
    }
    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }
    public String getCartera() {
        return cartera;
    }
    public void setCartera(String cartera) {
        this.cartera = cartera;
    }
    public String getTipoSaldo() {
        return tipoSaldo;
    }
    public void setTipoSaldo(String tipoSaldo) {
        this.tipoSaldo = tipoSaldo;
    }
    public String getAsociacionGasto() {
        return asociacionGasto;
    }
    public void setAsociacionGasto(String asociacionGasto) {
        this.asociacionGasto = asociacionGasto;
    }
    public String getAsociacionIngreso() {
        return asociacionIngreso;
    }
    public void setAsociacionIngreso(String asociacionIngreso) {
        this.asociacionIngreso = asociacionIngreso;
    }
    public String getPeriodidosHabilitados() {
        return periodidosHabilitados;
    }
    public void setPeriodidosHabilitados(String periodidosHabilitados) {
        this.periodidosHabilitados = periodidosHabilitados;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getGrupo() {
        return grupo;
    }
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    public String getSubGrupo() {
        return subGrupo;
    }
    public void setSubGrupo(String subGrupo) {
        this.subGrupo = subGrupo;
    }
    public String getCodCta() {
        return codCta;
    }
    public void setCodCta(String codCta) {
        this.codCta = codCta;
    }
    @Override
    public String toString() {
        return "PlanCuentaTblBO [titulo=" + titulo + ", grupo=" + grupo
                + ", subGrupo=" + subGrupo + ", idEjercicio=" + idEjercicio
                + ", agrDesc=" + agrDesc + ", userCreator=" + userCreator
                + ", usoAuxiliar=" + usoAuxiliar + ", imputacionPresup="
                + imputacionPresup + ", clasificacion=" + clasificacion
                + ", cartera=" + cartera + ", tipoSaldo=" + tipoSaldo + "]";
    }
}

package cl.contraloria.sicogen.model;

public class PlanCuentasContables {

    private String idTabla1;
    private String idTabla2;
    private String agregacion;
    private String codPrimerNivel;
    private String codSegundoNivel;
    private String codTercerNivel;
    private String descripcion;
    private String cierre;
    private String asociacionGasto;
    private String asociacionIngreso;
    private String tipoSaldo;
    private String usoAuxiliar;
    private String imputacionPresup;
    private String cartera;
    private String clasificacion;
    private String periodidosHabilitados;
    private String fechaDesactivacion;
    private String idFather;

    public String getAgregacion() {
        return agregacion;
    }

    public void setAgregacion(String agregacion) {
        this.agregacion = agregacion;
    }

    public String getCodPrimerNivel() {
        return codPrimerNivel;
    }

    public void setCodPrimerNivel(String codPrimerNivel) {
        this.codPrimerNivel = codPrimerNivel;
    }

    public String getCodSegundoNivel() {
        return codSegundoNivel;
    }

    public void setCodSegundoNivel(String codSegundoNivel) {
        this.codSegundoNivel = codSegundoNivel;
    }

    public String getCodTercerNivel() {
        return codTercerNivel;
    }

    public void setCodTercerNivel(String codTercerNivel) {
        this.codTercerNivel = codTercerNivel;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCierre() {
        return cierre;
    }

    public void setCierre(String cierre) {
        this.cierre = cierre;
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

    public String getTipoSaldo() {
        return tipoSaldo;
    }

    public void setTipoSaldo(String tipoSaldo) {
        this.tipoSaldo = tipoSaldo;
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

    public String getCartera() {
        return cartera;
    }

    public void setCartera(String cartera) {
        this.cartera = cartera;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getPeriodidosHabilitados() {
        return periodidosHabilitados;
    }

    public void setPeriodidosHabilitados(String periodidosHabilitados) {
        this.periodidosHabilitados = periodidosHabilitados;
    }

    public String getIdTabla1() {
        return idTabla1;
    }

    public void setIdTabla1(String idTabla1) {
        this.idTabla1 = idTabla1;
    }

    public String getIdTabla2() {
        return idTabla2;
    }

    public void setIdTabla2(String idTabla2) {
        this.idTabla2 = idTabla2;
    }

    @Override
    public String toString() {
        return "PlanCuentasContableBO [idTabla1=" + idTabla1 + ", idTabla2="
                + idTabla2 + ", agregacion=" + agregacion + ", codPrimerNivel="
                + codPrimerNivel + ", codSegundoNivel=" + codSegundoNivel
                + ", codTercerNivel=" + codTercerNivel + ", descripcion="
                + descripcion + ", cierre=" + cierre + ", asociacionGasto="
                + asociacionGasto + ", asociacionIngreso=" + asociacionIngreso
                + ", tipoSaldo=" + tipoSaldo + ", usoAuxiliar=" + usoAuxiliar
                + ", imputacionPresup=" + imputacionPresup + ", cartera="
                + cartera + ", clasificacion=" + clasificacion
                + ", periodidosHabilitados=" + periodidosHabilitados + "]";
    }

    public String getFechaDesactivacion() {
        return fechaDesactivacion;
    }

    public void setFechaDesactivacion(String fechaDesactivacion) {
        this.fechaDesactivacion = fechaDesactivacion;
    }

    public String getIdFather() {
        return idFather;
    }

    public void setIdFather(String idFather) {
        this.idFather = idFather;
    }
}

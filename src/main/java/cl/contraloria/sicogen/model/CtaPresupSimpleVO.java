package cl.contraloria.sicogen.model;

public class CtaPresupSimpleVO {
    private String pkCuenta;
    private String codSubTitulo;
    private String codItem;
    private String codAsignacion;
    private String codSubAsignaciones;
    private String nomCuenta;

    public String getPkCuenta() {
        return pkCuenta;
    }

    public void setPkCuenta(String pkCuenta) {
        this.pkCuenta = pkCuenta;
    }

    public String getCodSubTitulo() {
        return codSubTitulo;
    }

    public void setCodSubTitulo(String codSubTitulo) {
        this.codSubTitulo = codSubTitulo;
    }

    public String getCodItem() {
        return codItem;
    }

    public void setCodItem(String codItem) {
        this.codItem = codItem;
    }

    public String getCodAsignacion() {
        return codAsignacion;
    }

    public void setCodAsignacion(String codAsignacion) {
        this.codAsignacion = codAsignacion;
    }

    public String getCodSubAsignaciones() {
        return codSubAsignaciones;
    }

    public void setCodSubAsignaciones(String codSubAsignaciones) {
        this.codSubAsignaciones = codSubAsignaciones;
    }

    public String getNomCuenta() {
        return nomCuenta;
    }

    public void setNomCuenta(String nomCuenta) {
        this.nomCuenta = nomCuenta;
    }
}

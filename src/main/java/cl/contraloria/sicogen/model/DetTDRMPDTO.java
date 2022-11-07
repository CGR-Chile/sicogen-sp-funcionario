package cl.contraloria.sicogen.model;

public class DetTDRMPDTO {
    private Integer TDRMPId;
    private Integer detTDRMPId;
    private String tipoModCuenta;
    private String codCuenta;
    private String nomCuenta;
    private String desMoneda;
    private Integer montoIncremento;
    private Integer montoDisminucion;

    public Integer getTDRMPId() {
        return TDRMPId;
    }

    public void setTDRMPId(Integer TDRMPId) {
        this.TDRMPId = TDRMPId;
    }

    public Integer getDetTDRMPId() {
        return detTDRMPId;
    }

    public void setDetTDRMPId(Integer detTDRMPId) {
        this.detTDRMPId = detTDRMPId;
    }

    public String getTipoModCuenta() {
        return tipoModCuenta;
    }

    public void setTipoModCuenta(String tipoModCuenta) {
        this.tipoModCuenta = tipoModCuenta;
    }

    public String getCodCuenta() {
        return codCuenta;
    }

    public void setCodCuenta(String codCuenta) {
        this.codCuenta = codCuenta;
    }

    public String getNomCuenta() {
        return nomCuenta;
    }

    public void setNomCuenta(String nomCuenta) {
        this.nomCuenta = nomCuenta;
    }

    public String getDesMoneda() {
        return desMoneda;
    }

    public void setDesMoneda(String desMoneda) {
        this.desMoneda = desMoneda;
    }

    public Integer getMontoIncremento() {
        return montoIncremento;
    }

    public void setMontoIncremento(Integer montoIncremento) {
        this.montoIncremento = montoIncremento;
    }

    public Integer getMontoDisminucion() {
        return montoDisminucion;
    }

    public void setMontoDisminucion(Integer montoDisminucion) {
        this.montoDisminucion = montoDisminucion;
    }
}

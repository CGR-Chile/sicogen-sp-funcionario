package cl.contraloria.sicogen.model;

public class CuentaTDRMPDTO {
    private Long idDetTDRMP;
    private Long idTDRMP;
    private String codCuenta;
    private String tipoCuenta;
    private String subtitulo;
    private String item;
    private String asignacion;
    private String subAsignacion;
    private String denominacion;
    private String tipoMoneda;
    private Integer mtoIncremento;
    private Integer mtoDisminucion;
    private Integer isDecreto;

    public Long getIdDetTDRMP() {
        return idDetTDRMP;
    }

    public void setIdDetTDRMP(Long idDetTDRMP) {
        this.idDetTDRMP = idDetTDRMP;
    }

    public String getCodCuenta() {
        return codCuenta;
    }

    public void setCodCuenta(String codCuenta) {
        this.codCuenta = codCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

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

    public String getSubAsignacion() {
        return subAsignacion;
    }

    public void setSubAsignacion(String subAsignacion) {
        this.subAsignacion = subAsignacion;
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public Long getIdTDRMP() {
        return idTDRMP;
    }

    public void setIdTDRMP(Long idTDRMP) {
        this.idTDRMP = idTDRMP;
    }

    public Integer getMtoIncremento() {
        return mtoIncremento;
    }

    public void setMtoIncremento(Integer mtoIncremento) {
        this.mtoIncremento = mtoIncremento;
    }

    public Integer getMtoDisminucion() {
        return mtoDisminucion;
    }

    public void setMtoDisminucion(Integer mtoDisminucion) {
        this.mtoDisminucion = mtoDisminucion;
    }

    public Integer getIsDecreto() {
        return isDecreto;
    }

    public void setIsDecreto(Integer isDecreto) {
        this.isDecreto = isDecreto;
    }
}

package cl.contraloria.sicogen.model;

public class DetalleInformePIDTO {

    private String codPartida;
    private String codCapitulo;
    private String codPrograma;
    private String cuenta;
    private String tipoCuenta;
    private String denominacionCuenta;
    private String glosas;
    private String montoCLP;
    private String montoUSD;
    private String estadoRegla1;
    private String mensajeRegla1;
    private String tipoErrorRegla;

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getDenominacionCuenta() {
        return denominacionCuenta;
    }

    public void setDenominacionCuenta(String denominacionCuenta) {
        this.denominacionCuenta = denominacionCuenta;
    }

    public String getGlosas() {
        return glosas;
    }

    public void setGlosas(String glosas) {
        this.glosas = glosas;
    }

    public String getMontoCLP() {
        return montoCLP;
    }

    public void setMontoCLP(String montoCLP) {
        this.montoCLP = montoCLP;
    }

    public String getMontoUSD() {
        return montoUSD;
    }

    public void setMontoUSD(String montoUSD) {
        this.montoUSD = montoUSD;
    }

    public String getEstadoRegla1() {
        return estadoRegla1;
    }

    public void setEstadoRegla1(String estadoRegla1) {
        this.estadoRegla1 = estadoRegla1;
    }

    public String getMensajeRegla1() {
        return mensajeRegla1;
    }

    public void setMensajeRegla1(String mensajeRegla1) {
        this.mensajeRegla1 = mensajeRegla1;
    }

    public String getCodPartida() {
        return codPartida;
    }

    public void setCodPartida(String codPartida) {
        this.codPartida = codPartida;
    }

    public String getCodCapitulo() {
        return codCapitulo;
    }

    public void setCodCapitulo(String codCapitulo) {
        this.codCapitulo = codCapitulo;
    }

    public String getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(String codPrograma) {
        this.codPrograma = codPrograma;
    }

    public String getTipoErrorRegla() {
        return tipoErrorRegla;
    }

    public void setTipoErrorRegla(String tipoErrorRegla) {
        this.tipoErrorRegla = tipoErrorRegla;
    }
}

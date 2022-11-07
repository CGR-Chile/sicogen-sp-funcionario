package cl.contraloria.sicogen.model;

public class ErroresSaldoInicial {
    private String ejercicio;
    private String moneda;
    private String partida;
    private String capitulo;
    private String programa;
    private String cuenta;
    private String saldoDeudor;
    private String saldoAcreedor;
    private String descripcionError;


    public ErroresSaldoInicial() {
        // TODO Auto-generated constructor stub
    }
    public ErroresSaldoInicial(String ejercicio, String moneda, String partida,
                               String capitulo, String programa, String cuenta,
                               String saldoDeudor, String saldoAcreedor, String descripcionError) {
        super();
        this.ejercicio = ejercicio;
        this.moneda = moneda;
        this.partida = partida;
        this.capitulo = capitulo;
        this.programa = programa;
        this.cuenta = cuenta;
        this.saldoDeudor = saldoDeudor;
        this.saldoAcreedor = saldoAcreedor;
        this.descripcionError = descripcionError;
    }

    public String getEjercicio() {
        return ejercicio;
    }
    public void setEjercicio(String ejercicio) {
        this.ejercicio = ejercicio;
    }
    public String getMoneda() {
        return moneda;
    }
    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }
    public String getPartida() {
        return partida;
    }
    public void setPartida(String partida) {
        this.partida = partida;
    }
    public String getCapitulo() {
        return capitulo;
    }
    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }
    public String getPrograma() {
        return programa;
    }
    public void setPrograma(String programa) {
        this.programa = programa;
    }
    public String getCuenta() {
        return cuenta;
    }
    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }
    public String getSaldoDeudor() {
        return saldoDeudor;
    }
    public void setSaldoDeudor(String saldoDeudor) {
        this.saldoDeudor = saldoDeudor;
    }
    public String getSaldoAcreedor() {
        return saldoAcreedor;
    }
    public void setSaldoAcreedor(String saldoAcreedor) {
        this.saldoAcreedor = saldoAcreedor;
    }
    public String getDescripcionError() {
        return descripcionError;
    }
    public void setDescripcionError(String descripcionError) {
        this.descripcionError = descripcionError;
    }
}

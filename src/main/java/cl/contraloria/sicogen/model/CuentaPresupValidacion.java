package cl.contraloria.sicogen.model;

public class CuentaPresupValidacion {
    private Integer codEjec;
    private String msgEjec;
    private Integer idCuenta;
    private String nombreCuenta;
    private Integer flagHabilitado;
    private Integer idCuenEnt;

    public Integer getCodEjec() {
        return codEjec;
    }

    public void setCodEjec(Integer codEjec) {
        this.codEjec = codEjec;
    }

    public String getMsgEjec() {
        return msgEjec;
    }

    public void setMsgEjec(String msgEjec) {
        this.msgEjec = msgEjec;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public Integer getFlagHabilitado() {
        return flagHabilitado;
    }

    public void setFlagHabilitado(Integer flagHabilitado) {
        this.flagHabilitado = flagHabilitado;
    }

    public Integer getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Integer idCuenta) {
        this.idCuenta = idCuenta;
    }

    public Integer getIdCuenEnt() {
        return idCuenEnt;
    }

    public void setIdCuenEnt(Integer idCuenEnt) {
        this.idCuenEnt = idCuenEnt;
    }
}

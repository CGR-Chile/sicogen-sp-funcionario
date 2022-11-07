package cl.contraloria.sicogen.model;

public class ResultadoEjecucion {
    private String codEjec;
    private String msgEjec;
    private Integer newId;

    public ResultadoEjecucion(String codEjec, String msgEjec) {
        this.codEjec = codEjec;
        this.msgEjec = msgEjec;
    }

    public ResultadoEjecucion() {
    }

    public String getCodEjec() {
        return codEjec;
    }

    public void setCodEjec(String codEjec) {
        this.codEjec = codEjec;
    }

    public String getMsgEjec() {
        return msgEjec;
    }

    public void setMsgEjec(String msgEjec) {
        this.msgEjec = msgEjec;
    }

    public Integer getNewId() {
        return newId;
    }

    public void setNewId(Integer newId) {
        this.newId = newId;
    }
}

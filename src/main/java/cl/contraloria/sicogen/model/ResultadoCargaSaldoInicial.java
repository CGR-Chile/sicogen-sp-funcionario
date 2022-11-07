package cl.contraloria.sicogen.model;

import java.util.ArrayList;
import java.util.List;

public class ResultadoCargaSaldoInicial {

    private List<LogCarga> resultadoLogCarga;
    private List<String> errorCargaSaldos = new ArrayList<String>();

    public ResultadoCargaSaldoInicial(List<LogCarga> resultadoLogCarga, List<String> errorCargaSaldos) {
        //super();
        this.resultadoLogCarga = resultadoLogCarga;
        this.errorCargaSaldos = errorCargaSaldos;

    }

    public List<LogCarga> getResultadoLogCarga() {
        return resultadoLogCarga;
    }
    public void setResultadoLogCarga(List<LogCarga> resultadoLogCarga) {
        this.resultadoLogCarga = resultadoLogCarga;
    }
    public List<String> getErrorCargaSaldos() {
        return errorCargaSaldos;
    }
    public void setErrorCargaSaldos(List<String> errorCargaSaldos) {
        this.errorCargaSaldos = errorCargaSaldos;
    }
}

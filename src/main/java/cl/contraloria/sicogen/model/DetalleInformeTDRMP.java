package cl.contraloria.sicogen.model;

import java.util.List;

public class DetalleInformeTDRMP {
    private String partida;
    private String capitulo;
    private String programa;
    private List<CuentaTDRMP> cuentasTDRMP;

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

    public List<CuentaTDRMP> getCuentasTDRMP() {
        return cuentasTDRMP;
    }

    public void setCuentasTDRMP(List<CuentaTDRMP> cuentasTDRMP) {
        this.cuentasTDRMP = cuentasTDRMP;
    }
}

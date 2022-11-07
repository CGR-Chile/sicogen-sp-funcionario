package cl.contraloria.sicogen.model;

import java.util.List;

public class DatosEntidadInformePIDTO {

    private String codPartida;
    private String codCapitulo;
    private String codPrograma;
    private String partida;
    private String capitulo;
    private String programa;
    private String totalIngresosCLP;
    private String totalGastosCLP;
    private List<DetalleInformePIDTO> listaDetalleCuentas;

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

    public String getTotalIngresosCLP() {
        return totalIngresosCLP;
    }

    public void setTotalIngresosCLP(String totalIngresosCLP) {
        this.totalIngresosCLP = totalIngresosCLP;
    }

    public String getTotalGastosCLP() {
        return totalGastosCLP;
    }

    public void setTotalGastosCLP(String totalGastosCLP) {
        this.totalGastosCLP = totalGastosCLP;
    }

    public List<DetalleInformePIDTO> getListaDetalleCuentas() {
        return listaDetalleCuentas;
    }

    public void setListaDetalleCuentas(List<DetalleInformePIDTO> listaDetalleCuentas) {
        this.listaDetalleCuentas = listaDetalleCuentas;
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
}

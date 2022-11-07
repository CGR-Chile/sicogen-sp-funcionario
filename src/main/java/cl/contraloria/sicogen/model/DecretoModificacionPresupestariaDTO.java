package cl.contraloria.sicogen.model;

import java.util.List;

public class DecretoModificacionPresupestariaDTO {
    private Long idTDRMP;
    private String codPartida;
    private String nomPartida;
    private String codCapitulo;
    private String nomCapitulo;
    private String codPrograma;
    private String nomPrograma;
    private List<CuentaTDRMPDTO> cuentas;

    public Long getIdTDRMP() {
        return idTDRMP;
    }

    public void setIdTDRMP(Long idTDRMP) {
        this.idTDRMP = idTDRMP;
    }

    public String getCodPartida() {
        return codPartida;
    }

    public void setCodPartida(String codPartida) {
        this.codPartida = codPartida;
    }

    public String getNomPartida() {
        return nomPartida;
    }

    public void setNomPartida(String nomPartida) {
        this.nomPartida = nomPartida;
    }

    public String getCodCapitulo() {
        return codCapitulo;
    }

    public void setCodCapitulo(String codCapitulo) {
        this.codCapitulo = codCapitulo;
    }

    public String getNomCapitulo() {
        return nomCapitulo;
    }

    public void setNomCapitulo(String nomCapitulo) {
        this.nomCapitulo = nomCapitulo;
    }

    public String getCodPrograma() {
        return codPrograma;
    }

    public void setCodPrograma(String codPrograma) {
        this.codPrograma = codPrograma;
    }

    public String getNomPrograma() {
        return nomPrograma;
    }

    public void setNomPrograma(String nomPrograma) {
        this.nomPrograma = nomPrograma;
    }

    public List<CuentaTDRMPDTO> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<CuentaTDRMPDTO> cuentas) {
        this.cuentas = cuentas;
    }
}

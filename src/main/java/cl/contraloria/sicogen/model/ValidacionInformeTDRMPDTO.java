package cl.contraloria.sicogen.model;

public class ValidacionInformeTDRMPDTO {
    private Integer idPeriodo;
    private Integer numDoc;
    private String dia;
    private String mes;
    private String tipoDoc;
    private Integer idInforme;
    private String idSistradoc;
    private Integer ejercicio;

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public Integer getNumDoc() {
        return numDoc;
    }

    public String getDia() {
        return dia;
    }

    public String getMes() {
        return mes;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public Integer getIdInforme() {
        return idInforme;
    }

    public String getIdSistradoc() {
        return idSistradoc;
    }

    public Integer getEjercicio() {
        return ejercicio;
    }
}

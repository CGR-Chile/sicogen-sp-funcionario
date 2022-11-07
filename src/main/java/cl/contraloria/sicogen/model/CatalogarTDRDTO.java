package cl.contraloria.sicogen.model;

public class CatalogarTDRDTO {
    private Integer idEjercicio;
    private Integer idInforme;
    private Integer idPeriodo;
    private String idDocs;
    private Integer idUser;

    public Integer getIdEjercicio() {
        return idEjercicio;
    }

    public Integer getIdInforme() {
        return idInforme;
    }

    public Integer getIdPeriodo() {
        return idPeriodo;
    }

    public String getIdDocs() {
        return idDocs;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdEjercicio(Integer idEjercicio) {
        this.idEjercicio = idEjercicio;
    }

    public void setIdInforme(Integer idInforme) {
        this.idInforme = idInforme;
    }

    public void setIdPeriodo(Integer idPeriodo) {
        this.idPeriodo = idPeriodo;
    }

    public void setIdDocs(String idDocs) {
        this.idDocs = idDocs;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }
}

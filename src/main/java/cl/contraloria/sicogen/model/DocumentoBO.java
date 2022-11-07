package cl.contraloria.sicogen.model;

public class DocumentoBO {
    private long id;
    private long estadoSicogen;
    private long estadoSitradoc;
    private String entidadEmisora;
    private String tipoDoc;
    private long numeroDoc;
    private long fileUId;

    public long getEstadoSicogen() {
        return estadoSicogen;
    }

    public void setEstadoSicogen(long estadoSicogen) {
        this.estadoSicogen = estadoSicogen;
    }

    public long getEstadoSitradoc() {
        return estadoSitradoc;
    }

    public void setEstadoSitradoc(long estadoSitradoc) {
        this.estadoSitradoc = estadoSitradoc;
    }

    public String getEntidadEmisora() {
        return entidadEmisora;
    }

    public void setEntidadEmisora(String entidadEmisora) {
        this.entidadEmisora = entidadEmisora;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public long getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(long numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public long getFileUId() {
        return fileUId;
    }

    public void setFileUId(long fileUId) {
        this.fileUId = fileUId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

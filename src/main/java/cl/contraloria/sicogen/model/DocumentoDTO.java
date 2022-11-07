package cl.contraloria.sicogen.model;

public class DocumentoDTO {

    public String entidad;
    public String numero;
    public String tipo;
    public String reingreso;
    public int idFileUpload;
    public int anno;
    private String rowClass;



    public String getEntidad() {
        return entidad;
    }
    public void setEntidad(String entidad) {
        this.entidad = entidad;
    }
    public String getNumero() {
        return numero;
    }
    public void setNumero(String numero) {
        this.numero = numero;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public String getReingreso() {
        return reingreso;
    }
    public void setReingreso(String reingreso) {
        this.reingreso = reingreso;
    }
    public int getIdFileUpload() {
        return idFileUpload;
    }
    public void setIdFileUpload(int idFileUpload) {
        this.idFileUpload = idFileUpload;
    }
    public int getAnno() {
        return anno;
    }
    public void setAnno(int anno) {
        this.anno = anno;
    }

    public String getRowClass() {
        return rowClass;
    }

    public void setRowClass(String rowClass) {
        this.rowClass = rowClass;
    }
}

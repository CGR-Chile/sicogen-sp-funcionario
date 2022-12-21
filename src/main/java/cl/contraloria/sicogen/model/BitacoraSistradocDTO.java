package cl.contraloria.sicogen.model;

public class BitacoraSistradocDTO {

    private int idBitacora;
    private int idDocumento;
    private String entidadEmisora;
    private String tipoDocumento;
    private int nroDocumento;
    private String fechaDocumento;
    private String fechaRecepcionCGR;
    private String analista;
    private String materiaGeneral;
    private String materiaEspecifica;
    private int ejercicio;
    private int anno;
    private String idTblSistradoc;
    private int idUsuario;
    private String nombreUsuario;
    private int secuenciaReingreso;
    private String fechaCreacion;
    private String operacion;

    public int getIdBitacora() {
        return idBitacora;
    }
    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }
    public int getIdDocumento() {
        return idDocumento;
    }
    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }
    public String getEntidadEmisora() {
        return entidadEmisora;
    }
    public void setEntidadEmisora(String entidadEmisora) {
        this.entidadEmisora = entidadEmisora;
    }
    public String getTipoDocumento() {
        return tipoDocumento;
    }
    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
    public int getNroDocumento() {
        return nroDocumento;
    }
    public void setNroDocumento(int nroDocumento) {
        this.nroDocumento = nroDocumento;
    }
    public int getAnno() {
        return anno;
    }
    public void setAnno(int anno) {
        this.anno = anno;
    }
    public String getFechaDocumento() {
        return fechaDocumento;
    }
    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }
    public String getFechaRecepcionCGR() {
        return fechaRecepcionCGR;
    }
    public void setFechaRecepcionCGR(String fechaRecepcionCGR) {
        this.fechaRecepcionCGR = fechaRecepcionCGR;
    }
    public String getAnalista() {
        return analista;
    }
    public void setAnalista(String analista) {
        this.analista = analista;
    }
    public String getMateriaGeneral() {
        return materiaGeneral;
    }
    public void setMateriaGeneral(String materiaGeneral) {
        this.materiaGeneral = materiaGeneral;
    }
    public String getMateriaEspecifica() {
        return materiaEspecifica;
    }
    public void setMateriaEspecifica(String materiaEspecifica) {
        this.materiaEspecifica = materiaEspecifica;
    }
    public int getEjercicio() {
        return ejercicio;
    }
    public void setEjercicio(int ejercicio) {
        this.ejercicio = ejercicio;
    }

    public String getIdTblSistradoc() {
        return idTblSistradoc;
    }
    public void setIdTblSistradoc(String idTblSistradoc) {
        this.idTblSistradoc = idTblSistradoc;
    }

    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }
    public int getSecuenciaReingreso() {
        return secuenciaReingreso;
    }
    public void setSecuenciaReingreso(int secuenciaReingreso) {
        this.secuenciaReingreso = secuenciaReingreso;
    }
    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public String getFechaCreacion() {
        return fechaCreacion;
    }
    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    public String getOperacion() {
        return operacion;
    }
}
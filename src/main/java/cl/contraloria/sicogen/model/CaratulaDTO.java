package cl.contraloria.sicogen.model;

public class CaratulaDTO {

    private int idDocumento;
    private String estadoSicogen;
    private String estadoSistradoc;
    private String entidadEmisora;
    private String tipoDocumento;
    private int nroDocumento;
    private String fechaDocumento;
    private String fechaRecepcionCGR;
    private String analista;
    private String materiaGeneral;
    private String materiaEspecifica;
    private int pEjercicio;
    private EjerciciosDTO ejercicio;
    private int anno;
    private int secuenciaReingreso;
    private String idTblSistradoc;

    private int idUsuario;
    private String nombreUsuario;
    private String rowClass;

    public int getIdDocumento() {
        return idDocumento;
    }
    public void setIdDocumento(int idDocumento) {
        this.idDocumento = idDocumento;
    }
    public String getEstadoSicogen() {
        return estadoSicogen;
    }
    public void setEstadoSicogen(String estadoSicogen) {
        this.estadoSicogen = estadoSicogen;
    }
    public String getEstadoSistradoc() {
        return estadoSistradoc;
    }
    public void setEstadoSistradoc(String estadoSistradoc) {
        this.estadoSistradoc = estadoSistradoc;
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
    public int getSecuenciaReingreso() {
        return secuenciaReingreso;
    }
    public void setSecuenciaReingreso(int secuenciaReingreso) {
        this.secuenciaReingreso = secuenciaReingreso;
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
    public EjerciciosDTO getEjercicio() {
        return ejercicio;
    }
    public void setEjercicio(EjerciciosDTO ejercicio) {
        this.ejercicio = ejercicio;
    }
    public int getpEjercicio() {
        return pEjercicio;
    }
    public void setpEjercicio(int pEjercicio) {
        this.pEjercicio = pEjercicio;
    }
//    public String getTipoInforme() {
//        return tipoInforme;
//    }
//    public void setTipoInforme(String tipoInforme) {
//        this.tipoInforme = tipoInforme;
//    }
//    public String getInforme() {
//        return informe;
//    }
//    public void setInforme(String informe) {
//        this.informe = informe;
//    }
//    public String getIdFileUpload() {
//        return idFileUpload;
//    }
//    public void setIdFileUpload(String idFileUpload) {
//        this.idFileUpload = idFileUpload;
//    }
//    public String getNombre() {
//        return nombre;
//    }
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//    public String getNombreArchivo() {
//        return nombreArchivo;
//    }
//    public void setNombreArchivo(String nombreArchivo) {
//        this.nombreArchivo = nombreArchivo;
//    }
//    public String getIdEstadoSicogen() {
//        return idEstadoSicogen;
//    }
//    public void setIdEstadoSicogen(String idEstadoSicogen) {
//        this.idEstadoSicogen = idEstadoSicogen;
//    }
    public String getIdTblSistradoc() {
        return idTblSistradoc;
    }
    public void setIdTblSistradoc(String idTblSistradoc) {
        this.idTblSistradoc = idTblSistradoc;
    }
//    @Override
//    public String toString() {
//        return "InformeSistradocBO [idDocumento=" + idDocumento
//                + ", tipoDocumento=" + tipoDocumento + ", nroDocumento="
//                + nroDocumento + ", ejercicio=" + ejercicio + ", tipoInforme="
//                + tipoInforme + ", informe=" + informe + ", idTblSistradoc="
//                + idTblSistradoc + ", analista=" + analista +"]";
//    }
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


    public String getRowClass() {
        return rowClass;
    }

    public void setRowClass(String rowClass) {
        this.rowClass = rowClass;
    }
}
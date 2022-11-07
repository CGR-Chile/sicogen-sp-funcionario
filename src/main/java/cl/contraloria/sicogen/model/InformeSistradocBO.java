package cl.contraloria.sicogen.model;

public class InformeSistradocBO {
	
	private int idDocumento;
	private String estadoSicogen;
	private String estadoSistradoc;
	private String entidadEmisora;
	private String tipoDocumento;
	private int nroDocumento;
	private String fechaDocumento;
	private String fechaRecepcionCGR;
	private String analista;
	private String materia;
	
	private String ejercicio;
	private String tipoInforme;
	private String informe;
	
	private String idFileUpload;
	private String nombre;
	private String nombreArchivo;
	
	private String idEstadoSicogen;
	private String idTblSistradoc;	
	
	private String idUsuario;
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
	public String getMateria() {
		return materia;
	}
	public void setMateria(String materia) {
		this.materia = materia;
	}
	public String getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}
	public String getTipoInforme() {
		return tipoInforme;
	}
	public void setTipoInforme(String tipoInforme) {
		this.tipoInforme = tipoInforme;
	}
	public String getInforme() {
		return informe;
	}
	public void setInforme(String informe) {
		this.informe = informe;
	}
	public String getIdFileUpload() {
		return idFileUpload;
	}
	public void setIdFileUpload(String idFileUpload) {
		this.idFileUpload = idFileUpload;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getIdEstadoSicogen() {
		return idEstadoSicogen;
	}
	public void setIdEstadoSicogen(String idEstadoSicogen) {
		this.idEstadoSicogen = idEstadoSicogen;
	}
	public String getIdTblSistradoc() {
		return idTblSistradoc;
	}
	public void setIdTblSistradoc(String idTblSistradoc) {
		this.idTblSistradoc = idTblSistradoc;
	}
	@Override
	public String toString() {
		return "InformeSistradocBO [idDocumento=" + idDocumento
				+ ", tipoDocumento=" + tipoDocumento + ", nroDocumento="
				+ nroDocumento + ", ejercicio=" + ejercicio + ", tipoInforme="
				+ tipoInforme + ", informe=" + informe + ", idTblSistradoc="
				+ idTblSistradoc + ", analista=" + analista +"]";
	}
	public String getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(String idUsuario) {
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

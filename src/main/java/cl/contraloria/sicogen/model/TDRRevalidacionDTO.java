package cl.contraloria.sicogen.model;

public class TDRRevalidacionDTO {
	
	private int id;
	private int idEstadoSicogen;
	private String estadoSicogen;
	private String estadoSistradoc;
	private String entidadEmisora;
	private String tipoDoc;
	private int numeroDoc;
	private String fechaDoc;
	private String fechaRecepcionCGR;
	private int idFileUpload;
	private int ejercicioID;
	private int tipoInforme;
	private String fechaValidacion;
	private String rowClass;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public int getNumeroDoc() {
		return numeroDoc;
	}
	public void setNumeroDoc(int numeroDoc) {
		this.numeroDoc = numeroDoc;
	}
	public String getFechaDoc() {
		return fechaDoc;
	}
	public void setFechaDoc(String fechaDoc) {
		this.fechaDoc = fechaDoc;
	}
	public String getFechaRecepcionCGR() {
		return fechaRecepcionCGR;
	}
	public void setFechaRecepcionCGR(String fechaRecepcionCGR) {
		this.fechaRecepcionCGR = fechaRecepcionCGR;
	}
	public int getIdFileUpload() {
		return idFileUpload;
	}
	public void setIdFileUpload(int idFileUpload) {
		this.idFileUpload = idFileUpload;
	}
	public int getEjercicioID() {
		return ejercicioID;
	}
	public void setEjercicioID(int ejercicioID) {
		this.ejercicioID = ejercicioID;
	}
	public int getTipoInforme() {
		return tipoInforme;
	}
	public void setTipoInforme(int tipoInforme) {
		this.tipoInforme = tipoInforme;
	}
	public int getIdEstadoSicogen() {
		return idEstadoSicogen;
	}
	public void setIdEstadoSicogen(int idEstadoSicogen) {
		this.idEstadoSicogen = idEstadoSicogen;
	}
	public String getFechaValidacion() {
		return fechaValidacion;
	}
	public void setFechaValidacion(String fechaValidacion) {
		this.fechaValidacion = fechaValidacion;
	}

	public String getRowClass() {
		return rowClass;
	}

	public void setRowClass(String rowClass) {
		this.rowClass = rowClass;
	}
}

package cl.contraloria.sicogen.model;

public class DatosEmitidosDTO {
	
	private String entidadEmisora;
	private String tipoDoc;
	private String numeroDoc;
	private String fechaDoc;
	private String fechaRecep;
	private String estadoSistradoc;	
	private String estadoSicogen;
	private String fechaTramitacion;
	private String horaTramitacion;
	private String fileID;
	private String documentoID;
	
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
	public String getNumeroDoc() {
		return numeroDoc;
	}
	public void setNumeroDoc(String numeroDoc) {
		this.numeroDoc = numeroDoc;
	}
	public String getFechaDoc() {
		return fechaDoc;
	}
	public void setFechaDoc(String fechaDoc) {
		this.fechaDoc = fechaDoc;
	}
	public String getFechaRecep() {
		return fechaRecep;
	}
	public void setFechaRecep(String fechaRecep) {
		this.fechaRecep = fechaRecep;
	}
	public String getEstadoSistradoc() {
		return estadoSistradoc;
	}
	public void setEstadoSistradoc(String estadoSistradoc) {
		this.estadoSistradoc = estadoSistradoc;
	}
	public String getEstadoSicogen() {
		return estadoSicogen;
	}
	public void setEstadoSicogen(String estadoSicogen) {
		this.estadoSicogen = estadoSicogen;
	}
	public String getFechaTramitacion() {
		return fechaTramitacion;
	}
	public void setFechaTramitacion(String fechaTramitacion) {
		this.fechaTramitacion = fechaTramitacion;
	}
	public String getFileID() {
		return fileID;
	}
	public void setFileID(String fileID) {
		this.fileID = fileID;
	}
	public String getHoraTramitacion() {
		return horaTramitacion;
	}
	public void setHoraTramitacion(String horaTramitacion) {
		this.horaTramitacion = horaTramitacion;
	}
	public String getDocumentoID() {
		return documentoID;
	}
	public void setDocumentoID(String documentoID) {
		this.documentoID = documentoID;
	}
	@Override
	public String toString() {
		return "DatosEmitidosDTO [entidadEmisora=" + entidadEmisora + ", tipoDoc="
				+ tipoDoc + ", numeroDoc=" + numeroDoc + ", estadoSistradoc="
				+ estadoSistradoc + ", estadoSicogen=" + estadoSicogen
				+ ", fileID=" + fileID + ", documentoID=" + documentoID + "]";
	}
	
}

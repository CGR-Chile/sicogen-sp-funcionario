package cl.contraloria.sicogen.model;

public class InformeTDR extends Informe{
	
	private String nombreArchivo;
	private String estadoSicogen;
	private String idFileUpload;
	
	private String informe;
	private String tipoInforme;
	private String analista;
	private String ejercicio;
	private String entidadEmisora;
	private String tipoDocumento;
	private String nroDocumento;
	private String fechaDocumento;
	private String fechaRecepcionCGR;
	
	private String idInforme;
	
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getEstadoSicogen() {
		return estadoSicogen;
	}
	public void setEstadoSicogen(String estadoSicogen) {
		this.estadoSicogen = estadoSicogen;
	}
	public String getIdFileUpload() {
		return idFileUpload;
	}
	public void setIdFileUpload(String idFileUpload) {
		this.idFileUpload = idFileUpload;
	}
	public String getInforme() {
		return informe;
	}
	public void setInforme(String informe) {
		this.informe = informe;
	}
	public String getTipoInforme() {
		return tipoInforme;
	}
	public void setTipoInforme(String tipoInforme) {
		this.tipoInforme = tipoInforme;
	}
	public String getAnalista() {
		return analista;
	}
	public void setAnalista(String analista) {
		this.analista = analista;
	}
	public String getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
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
	public String getNroDocumento() {
		return nroDocumento;
	}
	public void setNroDocumento(String nroDocumento) {
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
	
	@Override
	public String toString() {
		return "InformeTDR [idFileUpload="+idFileUpload+", nombreArchivo=" + 
				nombreArchivo + ", estadoSicogen=" + estadoSicogen + "]";
	}
	public String getIdInforme() {
		return idInforme;
	}
	public void setIdInforme(String idInforme) {
		this.idInforme = idInforme;
	}

}

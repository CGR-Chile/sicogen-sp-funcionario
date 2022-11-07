package cl.contraloria.sicogen.model;

public class ComboDTO {
	private String ejercicio;
	private String codPartida;
	private String codCapitulo;
	private String codPrograma;
	private String nombreCombo;
	private String fileId;
	
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}
	public String getCodPartida() {
		return codPartida;
	}
	public void setCodPartida(String codPartida) {
		this.codPartida = codPartida;
	}
	public String getCodCapitulo() {
		return codCapitulo;
	}
	public void setCodCapitulo(String codCapitulo) {
		this.codCapitulo = codCapitulo;
	}
	public String getCodPrograma() {
		return codPrograma;
	}
	public void setCodPrograma(String codPrograma) {
		this.codPrograma = codPrograma;
	}
	public String getNombreCombo() {
		return nombreCombo;
	}
	public void setNombreCombo(String nombreCombo) {
		this.nombreCombo = nombreCombo;
	}	
	

}

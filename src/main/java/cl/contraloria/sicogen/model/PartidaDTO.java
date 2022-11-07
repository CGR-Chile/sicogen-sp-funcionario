package cl.contraloria.sicogen.model;

public class PartidaDTO {
	private String partId;
	private String idPartida;
	private String codPartida;
	private String nombre;
	
	public String getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(String idPartida) {
		this.idPartida = idPartida;
	}
	public String getCodPartida() {
		return codPartida;
	}
	public void setCodPartida(String codPartida) {
		this.codPartida = codPartida;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPartId() {
		return partId;
	}
	public void setPartId(String partId) {
		this.partId = partId;
	}
}

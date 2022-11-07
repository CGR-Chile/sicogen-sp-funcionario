package cl.contraloria.sicogen.model;

public class Correccion {
	
	private int correcionId;
	private int correcionInfId;
	private int periodoInf;
	private int informeId;
	private String correccionNombre;
	private String mensaje;
	
	public int getCorrecionInfId() {
		return correcionInfId;
	}
	public void setCorrecionInfId(int correcionInfId) {
		this.correcionInfId = correcionInfId;
	}
	public int getCorrecionId() {
		return correcionId;
	}
	public void setCorrecionId(int correcionId) {
		this.correcionId = correcionId;
	}
	public String getCorreccionNombre() {
		return correccionNombre;
	}	
	public void setCorreccionNombre(String correccionNombre) {
		this.correccionNombre = correccionNombre;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getPeriodoInf() {
		return periodoInf;
	}
	public void setPeriodoInf(int periodoInf) {
		this.periodoInf = periodoInf;
	}
	public int getInformeId() {
		return informeId;
	}
	public void setInformeId(int informeId) {
		this.informeId = informeId;
	}
}

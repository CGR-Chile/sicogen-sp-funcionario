package cl.contraloria.sicogen.model;

public class ReportesEnvioDTO {
	
	public String nTransaccion;
	public String perContable;
	public String fechEnvio;
	public String Enviado;	
	public String certEnvio;
	public String entidad;
	private String rowClass;
	
	public String getEntidad() {
		return entidad;
	}
	public void setEntidad(String entidad) {
		this.entidad = entidad;
	}
	public String getnTransaccion() {
		return nTransaccion;
	}
	public void setnTransaccion(String nTransaccion) {
		this.nTransaccion = nTransaccion;
	}
	public String getPerContable() {
		return perContable;
	}
	public void setPerContable(String perContable) {
		this.perContable = perContable;
	}
	public String getFechEnvio() {
		return fechEnvio;
	}
	public void setFechEnvio(String fechEnvio) {
		this.fechEnvio = fechEnvio;
	}
	public String getEnviado() {
		return Enviado;
	}
	public void setEnviado(String enviado) {
		Enviado = enviado;
	}
	public String getCertEnvio() {
		return certEnvio;
	}
	public void setCertEnvio(String certEnvio) {
		this.certEnvio = certEnvio;
	}


	public String getRowClass() {
		return rowClass;
	}

	public void setRowClass(String rowClass) {
		this.rowClass = rowClass;
	}
}

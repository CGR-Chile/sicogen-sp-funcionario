package cl.contraloria.sicogen.model;

public class ReportesCumplimientoDTO {

	public String informe;
	public String perContable;
	public String Partida;
	public String Capitulo;
	public String Enviado;
	public String fechEnvio;
	private String rowClass;


	//	public ReportesCumplimiento(String informe, String perContable,
//			String partida, String capitulo, String enviado, String fechEnvio) {
//		super();
//		this.informe = informe;
//		this.perContable = perContable;
//		Partida = partida;
//		Capitulo = capitulo;
//		Enviado = enviado;
//		this.fechEnvio = fechEnvio;
//	}
	public String getInforme() {
		return informe;
	}
	public void setInforme(String informe) {
		this.informe = informe;
	}
	public String getPerContable() {
		return perContable;
	}
	public void setPerContable(String perContable) {
		this.perContable = perContable;
	}
	public String getPartida() {
		return Partida;
	}
	public void setPartida(String partida) {
		Partida = partida;
	}
	public String getCapitulo() {
		return Capitulo;
	}
	public void setCapitulo(String capitulo) {
		Capitulo = capitulo;
	}
	public String getEnviado() {
		return Enviado;
	}
	public void setEnviado(String enviado) {
		Enviado = enviado;
	}
	public String getFechEnvio() {
		return fechEnvio;
	}
	public void setFechEnvio(String fechEnvio) {
		this.fechEnvio = fechEnvio;
	}

	public String getRowClass() {
		return rowClass;
	}

	public void setRowClass(String rowClass) {
		this.rowClass = rowClass;
	}
}

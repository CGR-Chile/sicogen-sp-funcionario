package cl.contraloria.sicogen.model;

public class ResumenDetalle {
	
	private int linea;
	private String cuenta;
	private String denominacion;
	private Long debitos;
	private Long creditos;
	
	public ResumenDetalle() {
		// TODO Auto-generated constructor stub
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getDenominacion() {
		return denominacion;
	}

	public void setDenominacion(String denominacion) {
		this.denominacion = denominacion;
	}

	public Long getDebitos() {
		return debitos;
	}

	public void setDebitos(Long debitos) {
		this.debitos = debitos;
	}

	public Long getCreditos() {
		return creditos;
	}

	public void setCreditos(Long creditos) {
		this.creditos = creditos;
	}

}

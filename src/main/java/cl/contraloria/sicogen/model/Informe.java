package cl.contraloria.sicogen.model;

import java.io.Serializable;

public class Informe implements Serializable {

	private static final long serialVersionUID = 1L;
	private String id;
	private String codigo;
	private String nombre;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}	
}

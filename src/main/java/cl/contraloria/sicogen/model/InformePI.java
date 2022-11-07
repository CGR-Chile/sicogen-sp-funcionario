package cl.contraloria.sicogen.model;

import java.io.Serializable;

public class InformePI extends Informe implements Serializable {

	private static final long serialVersionUID = 1L;
	private String nombreArchivo;
	private String estadoSicogen;
	private String idFileUpload;
	private String rowClass;
	
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
	@Override
	public String toString() {
		return "InformePI [idFileUpload="+idFileUpload+", nombreArchivo=" + nombreArchivo + ", estadoSicogen="
				+ estadoSicogen + "]";
	}


	public String getRowClass() {
		return rowClass;
	}

	public void setRowClass(String rowClass) {
		this.rowClass = rowClass;
	}
}

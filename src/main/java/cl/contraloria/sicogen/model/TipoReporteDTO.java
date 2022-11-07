package cl.contraloria.sicogen.model;

public class TipoReporteDTO {
	private int id;
	private int codigo;
	private String nombre;
	
	private int TipoInforme;
	private String reporte;
	
	public int getTipoInforme() {
		return TipoInforme;
	}
	public void setTipoInforme(int tipoInforme) {
		TipoInforme = tipoInforme;
	}
	public String getReporte() {
		return reporte;
	}
	public void setReporte(String reporte) {
		this.reporte = reporte;
	}
	public TipoReporteDTO(){
		
	}
	public TipoReporteDTO(int id, int codigo, String nombre) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
 
}
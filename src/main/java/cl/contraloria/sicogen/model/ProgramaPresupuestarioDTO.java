package cl.contraloria.sicogen.model;

public class ProgramaPresupuestarioDTO {

	
	//partida
	private int idPartida;
	private String nombrePartida;
	private int idEjercicioPP;
	
	//Capitulo
	private int idCapitulo;
	private String nombreCapitulo;
	
	//ProgramaPresupuestario
	private int idPP;
	private String partida;
	private String capitulo;
	private String programa;
	private String nombre;
	private String estado;
	private String codPartida;
	private String codCapitulo;
	
	public String getCodPartida() {
		return codPartida;
	}
	public void setCodPartida(String codPartida) {
		this.codPartida = codPartida;
	}
	public int getIdPP() {
		return idPP;
	}
	public void setIdPP(int idPP) {
		this.idPP = idPP;
	}
	public String getPartida() {
		return partida;
	}
	public void setPartida(String partida) {
		this.partida = partida;
	}
	public String getCapitulo() {
		return capitulo;
	}
	public void setCapitulo(String capitulo) {
		this.capitulo = capitulo;
	}
	public String getPrograma() {
		return programa;
	}
	public void setPrograma(String programa) {
		this.programa = programa;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getIdCapitulo() {
		return idCapitulo;
	}
	public void setIdCapitulo(int idCapitulo) {
		this.idCapitulo = idCapitulo;
	}
	public String getNombreCapitulo() {
		return nombreCapitulo;
	}
	public void setNombreCapitulo(String nombreCapitulo) {
		this.nombreCapitulo = nombreCapitulo;
	}
	public int getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}
	public String getNombrePartida() {
		return nombrePartida;
	}
	public void setNombrePartida(String nombrePartida) {
		this.nombrePartida = nombrePartida;
	}
	public int getIdEjercicioPP() {
		return idEjercicioPP;
	}
	public void setIdEjercicioPP(int idEjercicioPP) {
		this.idEjercicioPP = idEjercicioPP;
	}
	public String getCodCapitulo() {
		return codCapitulo;
	}
	public void setCodCapitulo(String codCapitulo) {
		this.codCapitulo = codCapitulo;
	}
}

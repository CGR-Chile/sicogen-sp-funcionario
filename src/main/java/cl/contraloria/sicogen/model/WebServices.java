package cl.contraloria.sicogen.model;

public class WebServices {
	
	private int id;
	private int tipo;
	private int timeout;
	private String url;
	private String nombre;
	private String anulacion;
	private String usuario;
	private String password;
	private String itemTypeEntity;
	private String fecha;
	private int respuesta;
	private String mensaje;
	
	
	public int getRespuesta() {
		return respuesta;
	}
	public void setRespuesta(int respuesta) {
		this.respuesta = respuesta;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getTimeout() {
		return timeout;
	}
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getAnulacion() {
		return anulacion;
	}
	public void setAnulacion(String anulacion) {
		this.anulacion = anulacion;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getItemTypeEntity() {
		return itemTypeEntity;
	}
	public void setItemTypeEntity(String itemTypeEntity) {
		this.itemTypeEntity = itemTypeEntity;
	}
}

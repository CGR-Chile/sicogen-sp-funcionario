package cl.contraloria.sicogen.model;

public class PeriodoInforme {

    private Integer id;
    private Integer informeId;
    private String nombrePadre;
    private Integer codigo;
    private Integer ejercicioId;
    private Integer perinfSecuencia;
    private String anulacion;
    private String usuario;
    private String fecha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getInformeId() {
        return informeId;
    }

    public void setInformeId(Integer informeId) {
        this.informeId = informeId;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public Integer getEjercicioId() {
        return ejercicioId;
    }

    public void setEjercicioId(Integer ejercicioId) {
        this.ejercicioId = ejercicioId;
    }

    public Integer getPerinfSecuencia() {
        return perinfSecuencia;
    }

    public void setPerinfSecuencia(Integer perinfSecuencia) {
        this.perinfSecuencia = perinfSecuencia;
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
}

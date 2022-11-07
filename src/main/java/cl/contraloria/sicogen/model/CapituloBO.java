package cl.contraloria.sicogen.model;

public class CapituloBO {
    private String idCapitulo;
    private String codCapitulo;
    private String nombre;
    private boolean usaDolares;

    public String getIdCapitulo() {
        return idCapitulo;
    }

    public void setIdCapitulo(String idCapitulo) {
        this.idCapitulo = idCapitulo;
    }

    public String getCodCapitulo() {
        return codCapitulo;
    }

    public void setCodCapitulo(String codCapitulo) {
        this.codCapitulo = codCapitulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isUsaDolares() {
        return usaDolares;
    }

    public void setUsaDolares(boolean usaDolares) {
        this.usaDolares = usaDolares;
    }
}

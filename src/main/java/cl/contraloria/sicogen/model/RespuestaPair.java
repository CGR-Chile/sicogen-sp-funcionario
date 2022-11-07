package cl.contraloria.sicogen.model;

public class RespuestaPair {
    private int codido;
    private String mensaje;
    private String mensajeAux;
    private int valAux;

    public RespuestaPair(int codido, String mensaje, String mensajeAux) {
        super();
        this.codido = codido;
        this.mensaje = mensaje;
        this.mensajeAux = mensajeAux;
    }

    public int getCodido() {
        return codido;
    }

    public void setCodido(int codido) {
        this.codido = codido;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensajeAux() {
        return mensajeAux;
    }

    public void setMensajeAux(String mensajeAux) {
        this.mensajeAux = mensajeAux;
    }

    @Override
    public String toString() {
        return "RespuestaPair [codido=" + codido + ", mensaje=" + mensaje
                + ", mensajeAux=" + mensajeAux + "]";
    }

    public int getValAux() {
        return valAux;
    }

    public void setValAux(int valAux) {
        this.valAux = valAux;
    }
}

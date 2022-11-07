package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="listaErrores")
@XmlAccessorType(XmlAccessType.FIELD)
public class ListaErrores {

    @XmlElement(name="erroresGeneral")
    private ErroresGeneral erroresGeneral;
    @XmlElement(name="erroresDetalle")
    private ErroresDetalle erroresDetalle;

    public ErroresGeneral getErroresGeneral() {
        return erroresGeneral;
    }

    public void setErroresGeneral(ErroresGeneral erroresGeneral) {
        this.erroresGeneral = erroresGeneral;
    }

    public ErroresDetalle getErroresDetalle() {
        return erroresDetalle;
    }

    public void setErroresDetalle(ErroresDetalle erroresDetalle) {
        this.erroresDetalle = erroresDetalle;
    }
}

package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ErroresDetalle {

    @XmlElement(name="error")
    private List<ErrorDetalle> errores;

    public List<ErrorDetalle> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorDetalle> errores) {
        this.errores = errores;
    }
}

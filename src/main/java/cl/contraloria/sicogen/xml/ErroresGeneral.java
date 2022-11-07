package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class ErroresGeneral {

    @XmlElement(name="error")
    private List<ErrorGeneral> errores;

    public List<ErrorGeneral> getErrores() {
        return errores;
    }

    public void setErrores(List<ErrorGeneral> errores) {
        this.errores = errores;
    }
}

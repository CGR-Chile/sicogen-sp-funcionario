package cl.contraloria.sicogen.model;

import java.util.List;

public class CargaInformePIDTO {

    private InformePI informe;
    private List<ValidacionReglaBO> errores;

    public CargaInformePIDTO(InformePI informe, List<ValidacionReglaBO> errores) {
        this.informe = informe;
        this.errores = errores;
    }

    public InformePI getInforme() {
        return informe;
    }

    public void setInforme(InformePI informe) {
        this.informe = informe;
    }

    public List<ValidacionReglaBO> getErrores() {
        return errores;
    }

    public void setErrores(List<ValidacionReglaBO> errores) {
        this.errores = errores;
    }
}

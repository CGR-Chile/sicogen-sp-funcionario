package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class FirmaMinistro {

    @XmlElement(name="nombre", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String nombre; // = "texto 80 caracteres";
    @XmlElement(name="cargo", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String cargo; // = "texto cargo 80 caracteres";
    @XmlElement(name="firmaTimbre", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String firmaTimbre; // = "cGVwaXRvIGxvcyBwYWxvdGVzDQo=";
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public String getFirmaTimbre() {
        return firmaTimbre;
    }
    public void setFirmaTimbre(String firmaTimbre) {
        this.firmaTimbre = firmaTimbre;
    }


}

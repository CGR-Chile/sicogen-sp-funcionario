package cl.contraloria.sicogen.xml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ContraParte {

    @XmlElement(name="codigoPartidaContraparte", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoPartidaContraparte;
    @XmlElement(name="nombrePartidaContraparte", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String nombrePartidaContraparte;
    @XmlElement(name="codigoCapituloContraparte", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoCapituloContraparte;
    @XmlElement(name="nombreCapituloContraparte", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String nombreCapituloContraparte;
    @XmlElement(name="codigoProgramaContraparte", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoProgramaContraparte;
    @XmlElement(name="nombreProgramaContraparte", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String nombreProgramaContraparte;
    @XmlElement(name="codigoSubtituloContraparte", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoSubtituloContraparte;
    @XmlElement(name="nombreSubtituloContraparte", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String nombreSubtituloContraparte;
    @XmlElement(name="codigoItemContraparte", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoItemContraparte;
    @XmlElement(name="nombreItemContraparte", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String nombreItemContraparte;
    @XmlElement(name="codigoAsignacionContraparte", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoAsignacionContraparte;
    @XmlElement(name="nombreAsignacionContraparte", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String nombreAsignacionContraparte;

    public ContraParte() {
        super();
    }
    public String getCodigoPartidaContraparte() {
        return codigoPartidaContraparte;
    }
    public void setCodigoPartidaContraparte(String codigoPartidaContraparte) {
        this.codigoPartidaContraparte = codigoPartidaContraparte;
    }
    public String getNombrePartidaContraparte() {
        return nombrePartidaContraparte;
    }
    public void setNombrePartidaContraparte(String nombrePartidaContraparte) {
        this.nombrePartidaContraparte = nombrePartidaContraparte;
    }
    public String getCodigoCapituloContraparte() {
        return codigoCapituloContraparte;
    }
    public void setCodigoCapituloContraparte(String codigoCapituloContraparte) {
        this.codigoCapituloContraparte = codigoCapituloContraparte;
    }
    public String getNombreCapituloContraparte() {
        return nombreCapituloContraparte;
    }
    public void setNombreCapituloContraparte(String nombreCapituloContraparte) {
        this.nombreCapituloContraparte = nombreCapituloContraparte;
    }
    public String getCodigoProgramaContraparte() {
        return codigoProgramaContraparte;
    }
    public void setCodigoProgramaContraparte(String codigoProgramaContraparte) {
        this.codigoProgramaContraparte = codigoProgramaContraparte;
    }
    public String getNombreProgramaContraparte() {
        return nombreProgramaContraparte;
    }
    public void setNombreProgramaContraparte(String nombreProgramaContraparte) {
        this.nombreProgramaContraparte = nombreProgramaContraparte;
    }
    public String getCodigoSubtituloContraparte() {
        return codigoSubtituloContraparte;
    }
    public void setCodigoSubtituloContraparte(String codigoSubtituloContraparte) {
        this.codigoSubtituloContraparte = codigoSubtituloContraparte;
    }
    public String getNombreSubtituloContraparte() {
        return nombreSubtituloContraparte;
    }
    public void setNombreSubtituloContraparte(String nombreSubtituloContraparte) {
        this.nombreSubtituloContraparte = nombreSubtituloContraparte;
    }
    public String getCodigoItemContraparte() {
        return codigoItemContraparte;
    }
    public void setCodigoItemContraparte(String codigoItemContraparte) {
        this.codigoItemContraparte = codigoItemContraparte;
    }
    public String getNombreItemContraparte() {
        return nombreItemContraparte;
    }
    public void setNombreItemContraparte(String nombreItemContraparte) {
        this.nombreItemContraparte = nombreItemContraparte;
    }
    public String getCodigoAsignacionContraparte() {
        return codigoAsignacionContraparte;
    }
    public void setCodigoAsignacionContraparte(String codigoAsignacionContraparte) {
        this.codigoAsignacionContraparte = codigoAsignacionContraparte;
    }
    public String getNombreAsignacionContraparte() {
        return nombreAsignacionContraparte;
    }
    public void setNombreAsignacionContraparte(String nombreAsignacionContraparte) {
        this.nombreAsignacionContraparte = nombreAsignacionContraparte;
    }


}

package cl.contraloria.sicogen.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="TDRModificacionPresupuestaria", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLDocumentoTDRMP {

    @XmlElement(name="cabecera", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    CabeceraTDRMP cabeceraMP;

//	@XmlElement(name="detalleDecretoModificacionPresupuestaria", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
//	List<CuerpoTDRMP> cuerpoMP;

    @XmlElement(name="DecretoModificacionPresupuestaria", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    List<ModificacionPresupuestaria> ModificacionPresupuestaria;

    @XmlElement(name="listaGlosaInstitucional", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    ListaGlosaInstitucional listaGlosaInstitucional;

    public XMLDocumentoTDRMP() {
        super();
    }

//	public List<CuerpoTDRMP> getCuerpoMP() {
//		return cuerpoMP;
//	}
//
//	public void setCuerpoMP(List<CuerpoTDRMP> cuerpoMP) {
//		this.cuerpoMP = cuerpoMP;
//	}

    public CabeceraTDRMP getCabeceraMP() {
        return cabeceraMP;
    }

    public void setCabeceraMP(CabeceraTDRMP cabeceraMP) {
        this.cabeceraMP = cabeceraMP;
    }

    public List<ModificacionPresupuestaria> getModificacionPresupuestaria() {
        return ModificacionPresupuestaria;
    }

    public void setModificacionPresupuestaria(List<ModificacionPresupuestaria> modificacionPresupuestaria) {
        ModificacionPresupuestaria = modificacionPresupuestaria;
    }

    public ListaGlosaInstitucional getListaGlosaInstitucional() {
        return listaGlosaInstitucional;
    }

    public void setListaGlosaInstitucional(ListaGlosaInstitucional listaGlosaInstitucional) {
        this.listaGlosaInstitucional = listaGlosaInstitucional;
    }
}

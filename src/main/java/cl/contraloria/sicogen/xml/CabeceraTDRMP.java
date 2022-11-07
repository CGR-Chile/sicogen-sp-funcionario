package cl.contraloria.sicogen.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CabeceraTDRMP {

    @XmlElement(name="ejercicio", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    int ejercicio;
    @XmlElement(name="mes", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String mes;
    @XmlElement(name="dia", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String dia;
    //	@XmlElement(name="cantidadDeRegistros", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
//	int cantidadDeRegistros;
    @XmlElement(name="cantidadRegistros", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    int cantidadRegistros;


    @XmlElement(name="montoTotal", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
//	int montoTotal;
            long montoTotal;

    @XmlElement(name="IdDecreto", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    int IdDecreto;
    @XmlElement(name="sectorResponsable", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    int sectorResponsable;
    @XmlElement(name="numeroRegistro", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String numeroRegistro;
    @XmlElement(name="fechaRegistro", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String fechaRegistro;
    @XmlElement(name="tipoDeRegistro", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String tipoDeRegistro;
    @XmlElement(name="numeroTP", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    int numeroTP;
    @XmlElement(name="numeroFC", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    int numeroFC;
    @XmlElement(name="numeroIN", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    int numeroIN;
    @XmlElement(name="numeroBAV", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    int numeroBAV;
    @XmlElement(name="numeroE", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    int numeroE;
    @XmlElement(name="estadoRegistro", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    int estadoRegistro;
    @XmlElement(name="firmaJefeSector", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String firmaJefeSector;
    @XmlElement(name="inicialesJefeSector", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String inicialesJefeSector;
    @XmlElement(name="firmaJefeDAP", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String firmaJefeDAP;
    @XmlElement(name="inicialesJefeDAP", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String inicialesJefeDAP;
    @XmlElement(name="firmaSubdirector", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String firmaSubdirector;
    @XmlElement(name="inicialesSubdirector", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String inicialesSubdirector;
    @XmlElement(name="firmaDirector", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String firmaDirector;
    @XmlElement(name="inicialesDirector", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String inicialesDirector;
    @XmlElement(name="firmasMinistros", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    List<FirmaMinistro> firmasMinistros;
    //String firmaMinistros;
    @XmlElement(name="resuelvo", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String resuelvo;
    @XmlElement(name="timbreLateralCGR", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String timbreLateralCGR;
    @XmlElement(name="logoGobierno", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String logoGobierno;
    @XmlElement(name="nombreInstitucion", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String nombreInstitucion;
    @XmlElement(name="numeroDecreto", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String numeroDecreto;


    public CabeceraTDRMP() {
    }

    public CabeceraTDRMP(int ejercicio, String mes, String dia, int cantidadRegistros, long montoTotal, int idDecreto,
                         int sectorResponsable, String numeroRegistro, String fechaRegistro, String tipoDeRegistro, int numeroTP,
                         int numeroFC, int numeroIN, int numeroBAV, int numeroE, String estadoDecreto, int estadoRegistro,
                         String firmaJefeSector, String inicialesJefeSector, String firmaJefeDAP, String inicialesJefeDAP,
                         String firmaSubdirector, String inicialesSubdirector, String firmaDirector, String inicialesDirector,
                         List<FirmaMinistro> firmasMinistros, String timbreLateralCGR, String logoGobierno) {
        super();
        this.ejercicio = ejercicio;
        this.mes = mes;
        this.dia = dia;
//		this.cantidadDeRegistros = cantidadDeRegistros;
        this.cantidadRegistros = cantidadRegistros;

        this.montoTotal = montoTotal; // montoTotal;
        IdDecreto = 123456; //idDecreto;
        this.sectorResponsable = 12; //sectorResponsable;
        this.numeroRegistro = "1000"; //numeroRegistro;
        this.fechaRegistro = "20160722"; //fechaRegistro;
        this.tipoDeRegistro = "01"; //tipoDeRegistro;
        this.numeroTP = 123456; //numeroTP;
        this.numeroFC = 123456; //numeroFC;
        this.numeroIN = 123456; //numeroIN;
        this.numeroBAV = 123456; //numeroBAV;
        this.numeroE = 123456; //numeroE;
        this.estadoRegistro = 1; //estadoDelRegistro;
        this.firmaJefeSector = "cGVwaXRvIGxvcyBwYWxvdGVzDQo="; //firmaJefeSector;
        this.inicialesJefeSector = "PBE"; //inicialesJefeSector;
        this.firmaJefeDAP = "cGVwaXRvIGxvcyBwYWxvdGVzDQo="; //firmaJefeDAP;
        this.inicialesJefeDAP = "ABC"; //inicialesJefeDAP;
        this.firmaSubdirector = "cGVwaXRvIGxvcyBwYWxvdGVzDQo="; //firmaSubdirector;
        this.inicialesSubdirector = "RFG"; //inicialesSubdirector;
        this.firmaDirector = "cGVwaXRvIGxvcyBwYWxvdGVzDQo="; //firmaDirector;
        this.inicialesDirector = "TTY"; //inicialesDirector;
        this.firmasMinistros = firmasMinistros;
        this.resuelvo = "";
        this.timbreLateralCGR = "cGVwaXRvIGxvcyBwYWxvdGVzDQo="; //timbreLateralCGR;
        this.logoGobierno = "cGVwaXRvIGxvcyBwYWxvdGVzDQo="; //logoGobierno;
        this.nombreInstitucion = "";
        this.numeroDecreto = "";
    }


    public int getEjercicio() {
        return ejercicio;
    }
    public void setEjercicio(int ejercicio) {
        this.ejercicio = ejercicio;
    }
    public String getMes() {
        return mes;
    }
    public void setMes(String mes) {
        this.mes = mes;
    }
    public String getDia() {
        return dia;
    }
    public void setDia(String dia) {
        this.dia = dia;
    }
    public int getCantidadDeRegistros() {
        return cantidadRegistros;
    }
    public void setCantidadDeRegistros(int cantidadDeRegistros) {
        this.cantidadRegistros = cantidadDeRegistros;
    }
    public long getMontoTotal() {
        return montoTotal;
    }
    public void setMontoTotal(long montoTotal) {
        this.montoTotal = montoTotal;
    }
    public int getIdDecreto() {
        return IdDecreto;
    }
    public void setIdDecreto(int idDecreto) {
        IdDecreto = idDecreto;
    }

    public int getSectorResponsable() {
        return sectorResponsable;
    }
    public void setSectorResponsable(int sectorResponsable) {
        this.sectorResponsable = sectorResponsable;
    }
    public String getNumeroRegistro() {
        return numeroRegistro;
    }
    public void setNumeroRegistro(String numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }
    public String getFechaRegistro() {
        return fechaRegistro;
    }
    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    public String getTipoDeRegistro() {
        return tipoDeRegistro;
    }
    public void setTipoDeRegistro(String tipoDeRegistro) {
        this.tipoDeRegistro = tipoDeRegistro;
    }
    public int getNumeroTP() {
        return numeroTP;
    }
    public void setNumeroTP(int numeroTP) {
        this.numeroTP = numeroTP;
    }
    public int getNumeroFC() {
        return numeroFC;
    }
    public void setNumeroFC(int numeroFC) {
        this.numeroFC = numeroFC;
    }
    public int getNumeroIN() {
        return numeroIN;
    }
    public void setNumeroIN(int numeroIN) {
        this.numeroIN = numeroIN;
    }
    public int getNumeroBAV() {
        return numeroBAV;
    }
    public void setNumeroBAV(int numeroBAV) {
        this.numeroBAV = numeroBAV;
    }
    public int getNumeroE() {
        return numeroE;
    }
    public void setNumeroE(int numeroE) {
        this.numeroE = numeroE;
    }

    public int getEstadoRegistro() {
        return estadoRegistro;
    }
    public void setEstadoRegistro(int estadoRegistro) {
        this.estadoRegistro = estadoRegistro;
    }


    public String getFirmaJefeSector() {
        return firmaJefeSector;
    }
    public void setFirmaJefeSector(String firmaJefeSector) {
        this.firmaJefeSector = firmaJefeSector;
    }
    public String getFirmaJefeDAP() {
        return firmaJefeDAP;
    }
    public void setFirmaJefeDAP(String firmaJefeDAP) {
        this.firmaJefeDAP = firmaJefeDAP;
    }
    public String getFirmaSubdirector() {
        return firmaSubdirector;
    }
    public void setFirmaSubdirector(String firmaSubdirector) {
        this.firmaSubdirector = firmaSubdirector;
    }
    public String getFirmaDirector() {
        return firmaDirector;
    }
    public void setFirmaDirector(String firmaDirector) {
        this.firmaDirector = firmaDirector;
    }

    public String getInicialesJefeSector() {
        return inicialesJefeSector;
    }

    public void setInicialesJefeSector(String inicialesJefeSector) {
        this.inicialesJefeSector = inicialesJefeSector;
    }

    public String getInicialesJefeDAP() {
        return inicialesJefeDAP;
    }

    public void setInicialesJefeDAP(String inicialesJefeDAP) {
        this.inicialesJefeDAP = inicialesJefeDAP;
    }

    public String getInicialesSubdirector() {
        return inicialesSubdirector;
    }

    public void setInicialesSubdirector(String inicialesSubdirector) {
        this.inicialesSubdirector = inicialesSubdirector;
    }

    public String getInicialesDirector() {
        return inicialesDirector;
    }

    public void setInicialesDirector(String inicialesDirector) {
        this.inicialesDirector = inicialesDirector;
    }

    public List<FirmaMinistro> getFirmasMinistros() {
        return firmasMinistros;
    }
//	public String getFirmaMinistros() {
//		return firmaMinistros;
//	}

    public void setFirmaMinistros(List<FirmaMinistro> firmasMinistros) {
        this.firmasMinistros = firmasMinistros;
    }

//	public void setFirmaMinistros(String firmaMinistros) {
//	this.firmaMinistros = firmaMinistros;
//}


    public String getTimbreLateralCGR() {
        return timbreLateralCGR;
    }

    public void setTimbreLateralCGR(String timbreLateralCGR) {
        this.timbreLateralCGR = timbreLateralCGR;
    }

    public String getLogoGobierno() {
        return logoGobierno;
    }

    public void setLogoGobierno(String logoGobierno) {
        this.logoGobierno = logoGobierno;
    }

}

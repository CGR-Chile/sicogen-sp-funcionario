package cl.contraloria.sicogen.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;


@XmlAccessorType(XmlAccessType.FIELD)
public class CabeceraTDR_II {

    @XmlElement(name="ejercicio", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    int ejercicio;
    @XmlElement(name="mes", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String mes;
    @XmlElement(name="dia", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String dia;
    @XmlElement(name="cantidadRegistros", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    int cantidadDeRegistros;
    @XmlElement(name="montoTotal", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    int montoTotal;
    @XmlElement(name="IdDecreto", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    int IdDecreto;
    @XmlElement(name="sectorResponsable", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    int sectorResponsable;
    @XmlElement(name="numeroRegistro", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String numeroRegistro;
    @XmlElement(name="fechaRegistro", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String fechaRegistro;
    @XmlElement(name="tipoDeRegistro", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String tipoDeRegistro;
    @XmlElement(name="numeroTP", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    int numeroTP;
    @XmlElement(name="numeroFC", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    int numeroFC;
    @XmlElement(name="numeroIN", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    int numeroIN;
    @XmlElement(name="numeroBAV", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    int numeroBAV;
    @XmlElement(name="numeroE", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    int numeroE;
    @XmlElement(name="estadoDecreto", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String estadoDecreto;
    @XmlElement(name="estadoRegistro", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    int estadoDelRegistro;
    @XmlElement(name="firmaJefeSector", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String firmaJefeSector;
    @XmlElement(name="inicialesJefeSector", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String inicialesJefeSector;
    @XmlElement(name="firmaJefeDAP", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String firmaJefeDAP;
    @XmlElement(name="inicialesJefeDAP", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String inicialesJefeDAP;
    @XmlElement(name="firmaSubdirector", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String firmaSubdirector;
    @XmlElement(name="inicialesSubdirector", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String inicialesSubdirector;
    @XmlElement(name="firmaDirector", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String firmaDirector;
    @XmlElement(name="inicialesDirector", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String inicialesDirector;
    @XmlElement(name="firmasMinistros", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    List<FirmaMinistro> firmaMinistros;
    @XmlElement(name="timbreLateralCGR", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String timbreLateralCGR;
    @XmlElement(name="logoGobierno", namespace="http://www.contraloria.cl/Informes/SP/TDRidentificacionIniciativasInversion")
    String logoGobierno;


    public CabeceraTDR_II() {
    }

    public CabeceraTDR_II(int ejercicio, String mes, String dia, int cantidadDeRegistros, int montoTotal, int idDecreto,
                          int sectorResponsable, String numeroRegistro, String fechaRegistro, String tipoDeRegistro, int numeroTP,
                          int numeroFC, int numeroIN, int numeroBAV, int numeroE, String estadoDecreto, int estadoDelRegistro,
                          String firmaJefeSector, String inicialesJefeSector, String firmaJefeDAP, String inicialesJefeDAP,
                          String firmaSubdirector, String inicialesSubdirector, String firmaDirector, String inicialesDirector,
                          List<FirmaMinistro> firmaMinistros, String timbreLateralCGR, String logoGobierno) {
        super();
        this.ejercicio = ejercicio;
        this.mes = mes;
        this.dia = dia;
        this.cantidadDeRegistros = cantidadDeRegistros;
        this.montoTotal = montoTotal;
        IdDecreto = idDecreto;
        this.sectorResponsable = sectorResponsable;
        this.numeroRegistro = numeroRegistro;
        this.fechaRegistro = fechaRegistro;
        this.tipoDeRegistro = tipoDeRegistro;
        this.numeroTP = numeroTP;
        this.numeroFC = numeroFC;
        this.numeroIN = numeroIN;
        this.numeroBAV = numeroBAV;
        this.numeroE = numeroE;
        this.estadoDecreto = estadoDecreto;
        this.estadoDelRegistro = estadoDelRegistro;
        this.firmaJefeSector = firmaJefeSector;
        this.inicialesJefeSector = inicialesJefeSector;
        this.firmaJefeDAP = firmaJefeDAP;
        this.inicialesJefeDAP = inicialesJefeDAP;
        this.firmaSubdirector = firmaSubdirector;
        this.inicialesSubdirector = inicialesSubdirector;
        this.firmaDirector = firmaDirector;
        this.inicialesDirector = inicialesDirector;
        this.firmaMinistros = firmaMinistros;
        this.timbreLateralCGR = timbreLateralCGR;
        this.logoGobierno = logoGobierno;
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
        return cantidadDeRegistros;
    }
    public void setCantidadDeRegistros(int cantidadDeRegistros) {
        this.cantidadDeRegistros = cantidadDeRegistros;
    }
    public int getMontoTotal() {
        return montoTotal;
    }
    public void setMontoTotal(int montoTotal) {
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

    public String getEstadoDecreto() {
        return estadoDecreto;
    }
    public void setEstadoDecreto(String estadoDecreto) {
        this.estadoDecreto = estadoDecreto;
    }

    public int getEstadoDelRegistro() {
        return estadoDelRegistro;
    }
    public void setEstadoDelRegistro(int estadoDelRegistro) {
        this.estadoDelRegistro = estadoDelRegistro;
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

    public List<FirmaMinistro> getFirmaMinistros() {
        return firmaMinistros;
    }

    public void setFirmaMinistros(List<FirmaMinistro> firmaMinistros) {
        this.firmaMinistros = firmaMinistros;
    }

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

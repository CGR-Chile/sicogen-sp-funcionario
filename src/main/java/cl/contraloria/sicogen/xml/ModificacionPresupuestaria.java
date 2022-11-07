package cl.contraloria.sicogen.xml;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ModificacionPresupuestaria {

    @XmlAttribute(name="partida")
    String partida;
    @XmlAttribute(name="capitulo")
    String capitulo;
    @XmlAttribute(name="programa")
    String programa;
    @XmlAttribute(name="tipoMoneda")
    String tipoMoneda;

    @XmlElement(name="codigoPartida", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoPartida ;
    @XmlElement(name="nombrePartida", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String nombrePartida ;
    @XmlElement(name="codigoCapitulo", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoCapitulo;
    @XmlElement(name="nombreCapitulo", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String nombreCapitulo;
    @XmlElement(name="codigoPrograma", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String codigoPrograma;
    @XmlElement(name="nombrePrograma", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    String nombrePrograma;
    @XmlElement(name="cuenta", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    List<Movimiento> movimiento;
    @XmlElement(name="listaGlosaPresupuestaria", namespace="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria")
    ListaGlosaPresupuestaria ListaGlosaPresupuestaria;


    public ModificacionPresupuestaria() {
        super();
    }
    public String getCodigoPartida() {
        return codigoPartida;
    }
    public void setCodigoPartida(String codigoPartida) {
        this.codigoPartida = codigoPartida;
    }
    public String getNombrePartida() {
        return nombrePartida;
    }
    public void setNombrePartida(String nombrePartida) {
        this.nombrePartida = nombrePartida;
    }
    public String getCodigoCapitulo() {
        return codigoCapitulo;
    }
    public void setCodigoCapitulo(String codigoCapitulo) {
        this.codigoCapitulo = codigoCapitulo;
    }
    public String getNombreCapitulo() {
        return nombreCapitulo;
    }
    public void setNombreCapitulo(String nombreCapitulo) {
        this.nombreCapitulo = nombreCapitulo;
    }
    public String getCodigoPrograma() {
        return codigoPrograma;
    }
    public void setCodigoPrograma(String codigoPrograma) {
        this.codigoPrograma = codigoPrograma;
    }
    public String getNombrePrograma() {
        return nombrePrograma;
    }
    public void setNombrePrograma(String nombrePrograma) {
        this.nombrePrograma = nombrePrograma;
    }
    public List<Movimiento> getMovimiento() {
        return movimiento;
    }
    public void setMovimiento(List<Movimiento> movimiento) {
        this.movimiento = movimiento;
    }
    public String getPartida() {
        return partida;
    }
    public void setPartida(String partida) {
        this.partida = partida;
    }
    public String getCapitulo() {
        return capitulo;
    }
    public void setCapitulo(String capitulo) {
        this.capitulo = capitulo;
    }
    public String getPrograma() {
        return programa;
    }
    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public String getTipoMoneda() {
        return tipoMoneda;
    }

    public void setTipoMoneda(String tipoMoneda) {
        this.tipoMoneda = tipoMoneda;
    }

    public ListaGlosaPresupuestaria getListaGlosaPresupuestaria() {
        return ListaGlosaPresupuestaria;
    }

    public void setListaGlosaPresupuestaria(ListaGlosaPresupuestaria listaGlosaPresupuestaria) {
        ListaGlosaPresupuestaria = listaGlosaPresupuestaria;
    }
}

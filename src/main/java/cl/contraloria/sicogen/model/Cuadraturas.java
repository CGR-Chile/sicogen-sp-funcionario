package cl.contraloria.sicogen.model;

import java.util.ArrayList;
import java.util.List;

public class Cuadraturas {
	private String ctaCodigo;
	private String ctaNombre;
	private long totDebeInf01;
	private long totHaberInf01;
	private long totDeudorInf01;
	private long totAcreedorInf01;
	private long totDebeInf02;
	private long totHaberInf02;
	private long totDeudorInf02;
	private long totAcreedorInf02;
	private List<CuadraturasDetalle> detalle= new ArrayList<CuadraturasDetalle>();
	
	private String lineaInf01;
	private String debeInf01;
	private String haberInf01;
	private String deudorInf01;
	private String acreedorInf01;
	private String lineaInf02;
	private String codCtaInf02;
	private String denomConcep;
	private String debeInf02;
	private String haberInf02;
	private String deudorInf02;
	private String acreedorInf02;
	private int estado;
	
	public String getCtaCodigo() {
		return ctaCodigo;
	}
	public void setCtaCodigo(String ctaCodigo) {
		this.ctaCodigo = ctaCodigo;
	}
	public String getCtaNombre() {
		return ctaNombre;
	}
	public void setCtaNombre(String ctaNombre) {
		this.ctaNombre = ctaNombre;
	}
	public String getLineaInf01() {
		return lineaInf01;
	}
	public void setLineaInf01(String lineaInf01) {
		this.lineaInf01 = lineaInf01;
	}
	public String getDebeInf01() {
		return debeInf01;
	}
	public void setDebeInf01(String debeInf01) {
		this.debeInf01 = debeInf01;
	}
	public String getHaberInf01() {
		return haberInf01;
	}
	public void setHaberInf01(String haberInf01) {
		this.haberInf01 = haberInf01;
	}
	public String getLineaInf02() {
		return lineaInf02;
	}
	public void setLineaInf02(String lineaInf02) {
		this.lineaInf02 = lineaInf02;
	}
	public String getCodCtaInf02() {
		return codCtaInf02;
	}
	public void setCodCtaInf02(String codCtaInf02) {
		this.codCtaInf02 = codCtaInf02;
	}
	public String getDebeInf02() {
		return debeInf02;
	}
	public void setDebeInf02(String debeInf02) {
		this.debeInf02 = debeInf02;
	}
	public String getHaberInf02() {
		return haberInf02;
	}
	public void setHaberInf02(String haberInf02) {
		this.haberInf02 = haberInf02;
	}
	public List<CuadraturasDetalle> getDetalle() {
		return detalle;
	}
	public void setDetalle(List<CuadraturasDetalle> detalle) {
		this.detalle = detalle;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public long getTotDebeInf01() {
		return totDebeInf01;
	}
	public void setTotDebeInf01(long totDebeInf01) {
		this.totDebeInf01 = totDebeInf01;
	}
	public long getTotHaberInf01() {
		return totHaberInf01;
	}
	public void setTotHaberInf01(long totHaberInf01) {
		this.totHaberInf01 = totHaberInf01;
	}
	public long getTotDebeInf02() {
		return totDebeInf02;
	}
	public void setTotDebeInf02(long totDebeInf02) {
		this.totDebeInf02 = totDebeInf02;
	}
	public long getTotHaberInf02() {
		return totHaberInf02;
	}
	public void setTotHaberInf02(long totHaberInf02) {
		this.totHaberInf02 = totHaberInf02;
	}
	public String getDenomConcep() {
		return denomConcep;
	}
	public void setDenomConcep(String denomConcep) {
		this.denomConcep = denomConcep;
	}
	public String getDeudorInf01() {
		return deudorInf01;
	}
	public void setDeudorInf01(String deudorInf01) {
		this.deudorInf01 = deudorInf01;
	}
	public String getAcreedorInf01() {
		return acreedorInf01;
	}
	public void setAcreedorInf01(String acreedorInf01) {
		this.acreedorInf01 = acreedorInf01;
	}
	public String getDeudorInf02() {
		return deudorInf02;
	}
	public void setDeudorInf02(String deudorInf02) {
		this.deudorInf02 = deudorInf02;
	}
	public String getAcreedorInf02() {
		return acreedorInf02;
	}
	public void setAcreedorInf02(String acreedorInf02) {
		this.acreedorInf02 = acreedorInf02;
	}
	public long getTotDeudorInf01() {
		return totDeudorInf01;
	}
	public void setTotDeudorInf01(long totDudorInf01) {
		this.totDeudorInf01 = totDudorInf01;
	}
	public long getTotAcreedorInf01() {
		return totAcreedorInf01;
	}
	public void setTotAcreedorInf01(long totAcreedorInf01) {
		this.totAcreedorInf01 = totAcreedorInf01;
	}
	public long getTotDeudorInf02() {
		return totDeudorInf02;
	}
	public void setTotDeudorInf02(long totDeudorInf02) {
		this.totDeudorInf02 = totDeudorInf02;
	}
	public long getTotAcreedorInf02() {
		return totAcreedorInf02;
	}
	public void setTotAcreedorInf02(long totAcreedorInf02) {
		this.totAcreedorInf02 = totAcreedorInf02;
	}
	
	
}

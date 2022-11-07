package cl.contraloria.sicogen.model;

public class CuadraturaAGAN {
	
	private String LineaAG;
	private String LineaAN;
	private String CodCuentaAG;
	private String DcuentaAG;
	private String AGdebe;
	private String AGhaber;
	private String CodigoAnalitico; 
	private String ANdebe;
	private String ANhaber;
	private long TotalDebeAG;
	private long TotalHaberAG;
	private long TotalDebeAN;
	private long TotalHaberAN;
	private int Estado;
	
	

	public int getEstado() {
		return Estado;
	}
	public void setEstado(int estado) {
		Estado = estado;
	}
	public long getTotalDebeAG() {
		return TotalDebeAG;
	}
	public void setTotalDebeAG(long totalDebeAG) {
		TotalDebeAG = totalDebeAG;
	}
	public long getTotalHaberAG() {
		return TotalHaberAG;
	}
	public void setTotalHaberAG(long totalHaberAG) {
		TotalHaberAG = totalHaberAG;
	}
	public long getTotalDebeAN() {
		return TotalDebeAN;
	}
	public void setTotalDebeAN(long totalDebeAN) {
		TotalDebeAN = totalDebeAN;
	}
	public long getTotalHaberAN() {
		return TotalHaberAN;
	}
	public void setTotalHaberAN(long totalHaberAN) {
		TotalHaberAN = totalHaberAN;
	}
	public String getCodCuentaAG() {
		return CodCuentaAG;
	}
	public void setCodCuentaAG(String codCuentaAG) {
		CodCuentaAG = codCuentaAG;
	}
	public String getDcuentaAG() {
		return DcuentaAG;
	}
	public void setDcuentaAG(String dcuentaAG) {
		DcuentaAG = dcuentaAG;
	}
	public String getLineaAG() {
		return LineaAG;
	}
	public void setLineaAG(String lineaAG) {
		LineaAG = lineaAG;
	}
	public String getLineaAN() {
		return LineaAN;
	}
	public void setLineaAN(String lineaAN) {
		LineaAN = lineaAN;
	}

	public String getAGdebe() {
		return AGdebe;
	}
	public void setAGdebe(String aGdebe) {
		AGdebe = aGdebe;
	}
	public String getAGhaber() {
		return AGhaber;
	}
	public void setAGhaber(String aGhaber) {
		AGhaber = aGhaber;
	}
	public String getCodigoAnalitico() {
		return CodigoAnalitico;
	}
	public void setCodigoAnalitico(String codigoAnalitico) {
		CodigoAnalitico = codigoAnalitico;
	}
	public String getANdebe() {
		return ANdebe;
	}
	public void setANdebe(String aNdebe) {
		ANdebe = aNdebe;
	}
	public String getANhaber() {
		return ANhaber;
	}
	public void setANhaber(String aNhaber) {
		ANhaber = aNhaber;
	}
	
	
}

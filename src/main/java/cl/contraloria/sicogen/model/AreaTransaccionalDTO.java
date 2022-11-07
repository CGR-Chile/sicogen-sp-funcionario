package cl.contraloria.sicogen.model;

public class AreaTransaccionalDTO {


    //sector
    private int idSector;
    private int idSector2;
    private String nombreSector;
    private int idEjercicioAT;
    private String ejeNombre;

    //institucion
    private int idInstitucion;
    private String codInstitucion;
    private String nombreInstitucion;
    private int sectorIdInstitucion;
    private String sectorNombreInstitucion;

    //AreaTransaccional
    private int idAT;
    private String sector;
    private String institucion;
    private String at;
    private String nombre;
    private String descripcion;
    private String rut;
    private String div;
    private String codPadre;
    private String tipo;
    private String estado;
    private String codSector;


    public int getIdSector() {
        return idSector;
    }

    public void setIdSector(int idSector) {
        this.idSector = idSector;
    }

    public String getNombreSector() {
        return nombreSector;
    }

    public void setNombreSector(String nombreSector) {
        this.nombreSector = nombreSector;
    }

    public int getIdEjercicioAT() {
        return idEjercicioAT;
    }

    public void setIdEjercicioAT(int idEjercicioAT) {
        this.idEjercicioAT = idEjercicioAT;
    }

    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombreInstitucion() {
        return nombreInstitucion;
    }

    public void setNombreInstitucion(String nombreInstitucion) {
        this.nombreInstitucion = nombreInstitucion;
    }

    public int getIdAT() {
        return idAT;
    }

    public void setIdAT(int idAT) {
        this.idAT = idAT;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getAt() {
        return at;
    }

    public void setAt(String at) {
        this.at = at;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCodSector() {
        return codSector;
    }

    public void setCodSector(String codSector) {
        this.codSector = codSector;
    }

    public String getCodInstitucion() {
        return codInstitucion;
    }

    public void setCodInstitucion(String codInstitucion) {
        this.codInstitucion = codInstitucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDiv() {
        return div;
    }

    public void setDiv(String div) {
        this.div = div;
    }

    public String getCodPadre() {
        return codPadre;
    }

    public void setCodPadre(String codPadre) {
        this.codPadre = codPadre;
    }

    public int getIdSector2() {
        return idSector2;
    }

    public void setIdSector2(int idSector2) {
        this.idSector2 = idSector2;
    }

    public String getEjeNombre() {
        return ejeNombre;
    }

    public void setEjeNombre(String ejeNombre) {
        this.ejeNombre = ejeNombre;
    }

    public int getSectorIdInstitucion() {
        return sectorIdInstitucion;
    }

    public void setSectorIdInstitucion(int sectorIdInstitucion) {
        this.sectorIdInstitucion = sectorIdInstitucion;
    }

    public String getSectorNombreInstitucion() {
        return sectorNombreInstitucion;
    }

    public void setSectorNombreInstitucion(String sectorNombreInstitucion) {
        this.sectorNombreInstitucion = sectorNombreInstitucion;
    }
}

package cl.contraloria.sicogen.model;


import java.util.ArrayList;
import java.util.List;

public class InformesEstadosAnualBO {
    private List<Informes> infomes;
    private List<InformeArchivoDTO> estados;
    private List<Informes> correcciones;
    private List<InformeArchivoDTO> pendientes;

    public InformesEstadosAnualBO(){
        infomes=new ArrayList<Informes>();
        estados=new ArrayList<InformeArchivoDTO>();
        correcciones=new ArrayList<Informes>();
        pendientes=new ArrayList<InformeArchivoDTO>();
    }
    public List<Informes> getInfomes(){
        return infomes;
    }
    public void setInfomes(List<Informes> infomes) {
        this.infomes = infomes;
    }
    public List<InformeArchivoDTO> getEstados() {
        return estados;
    }
    public void setEstados(List<InformeArchivoDTO> estados) {
        this.estados = estados;
    }
    public List<Informes> getCorrecciones() {
        return correcciones;
    }
    public void setCorrecciones(List<Informes> correcciones) {
        this.correcciones = correcciones;
    }
    public List<InformeArchivoDTO> getPendientes() {
        return pendientes;
    }
    public void setPendientes(List<InformeArchivoDTO> pendientes) {
        this.pendientes = pendientes;
    }

}

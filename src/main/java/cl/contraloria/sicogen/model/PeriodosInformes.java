package cl.contraloria.sicogen.model;

import java.util.ArrayList;
import java.util.List;

public class PeriodosInformes {

    private List<Informes> listInformes;
    private List<Periodos> listPeriodos;

    public PeriodosInformes() {
        this.listInformes= new ArrayList<Informes>();
        this.listPeriodos= new ArrayList<Periodos>();
    }

    public List<Informes> getListInformes() {
        return listInformes;
    }

    public void setListInformes(List<Informes> listInformes) {
        this.listInformes = listInformes;
    }

    public List<Periodos> getListPeriodos() {
        return listPeriodos;
    }

    public void setListPeriodos(List<Periodos> listPeriodos) {
        this.listPeriodos = listPeriodos;
    }
}

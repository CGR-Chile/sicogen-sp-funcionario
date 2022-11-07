package cl.contraloria.sicogen.model;

import java.util.ArrayList;
import java.util.List;

public class CuentasPresupuestarias {
    private List<CatalogoPresupuestario> listCtasPresupDeIngreso = new ArrayList<CatalogoPresupuestario>();
    private List<CatalogoPresupuestario> listCtasPresupDeGastos = new ArrayList<CatalogoPresupuestario>();

    public List<CatalogoPresupuestario> getListCtasPresupDeIngreso() {
        return listCtasPresupDeIngreso;
    }

    public void setListCtasPresupDeIngreso(
            List<CatalogoPresupuestario> listCtasPresupDeIngreso) {
        this.listCtasPresupDeIngreso = listCtasPresupDeIngreso;
    }

    public List<CatalogoPresupuestario> getListCtasPresupDeGastos() {
        return listCtasPresupDeGastos;
    }

    public void setListCtasPresupDeGastos(
            List<CatalogoPresupuestario> listCtasPresupDeGastos) {
        this.listCtasPresupDeGastos = listCtasPresupDeGastos;
    }
}

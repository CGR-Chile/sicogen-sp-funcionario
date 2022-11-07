package cl.contraloria.sicogen.model;

import java.util.List;

public class DatosCuentaContable {
    PlanCuentasContables cuentaContableDetalle;
    CuentasPresupuestarias ctasPresupuestariasVO;
    List<Periodos> listPeriodos;

    public PlanCuentasContables getCuentaContableDetalle() {
        return cuentaContableDetalle;
    }

    public void setCuentaContableDetalle(PlanCuentasContables cuentaContableDetalle) {
        this.cuentaContableDetalle = cuentaContableDetalle;
    }

    public CuentasPresupuestarias getCtasPresupuestariasVO() {
        return ctasPresupuestariasVO;
    }

    public void setCtasPresupuestariasVO(CuentasPresupuestarias ctasPresupuestariasVO) {
        this.ctasPresupuestariasVO = ctasPresupuestariasVO;
    }

    public List<Periodos> getListPeriodos() {
        return listPeriodos;
    }

    public void setListPeriodos(List<Periodos> listPeriodos) {
        this.listPeriodos = listPeriodos;
    }
}

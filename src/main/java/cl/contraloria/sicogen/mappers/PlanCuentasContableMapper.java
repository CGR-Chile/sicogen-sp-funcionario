package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.PlanCuentasContables;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanCuentasContableMapper extends BaseMapper implements RowMapper<PlanCuentasContables> {
    @Override
    public PlanCuentasContables mapRow(ResultSet resultSet, int i) throws SQLException {
        PlanCuentasContables pcc = new PlanCuentasContables();
        pcc.setIdTabla1(resultSet.getString("ID_TABLA_1"));
        pcc.setIdTabla2(resultSet.getString("ID_TABLA_2"));
        pcc.setAgregacion(resultSet.getString("AGREGACION"));
        pcc.setCodPrimerNivel(resultSet.getString("NIVEL1"));
        pcc.setCodSegundoNivel(resultSet.getString("NIVEL2"));
        pcc.setCodTercerNivel(resultSet.getString("NIVEL3"));
        pcc.setDescripcion(resultSet.getString("NOMBRE"));
        pcc.setCierre(resultSet.getString("CIERRE"));
        pcc.setAsociacionGasto(resultSet.getString("GASTO"));
        pcc.setAsociacionIngreso(resultSet.getString("INGRESO"));
        pcc.setUsoAuxiliar(resultSet.getString("AUX"));
        pcc.setTipoSaldo(getString(resultSet, "TIPO_SALDO"));
        pcc.setImputacionPresup(resultSet.getString("IMP"));
        pcc.setCartera(resultSet.getString("CARTERA"));
        pcc.setClasificacion(resultSet.getString("CLASIFICACION"));
        pcc.setPeriodidosHabilitados(resultSet.getString("PERIODOS_HABILITADOS"));
        pcc.setFechaDesactivacion(getString(resultSet, "P_CTA_ISVALID"));
        pcc.setIdFather(getString(resultSet, "ID_FATHER"));
        return pcc;
    }
}

package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.PlanDeCuentas;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanDeCuentasMapper extends BaseMapper implements RowMapper<PlanDeCuentas> {
    @Override
    public PlanDeCuentas mapRow(ResultSet rs, int i) throws SQLException {
        PlanDeCuentas planDeCuentas = new PlanDeCuentas();
        planDeCuentas.setCuentaAsignacion(getString(rs, "CTA_ASIGNACION"));
        planDeCuentas.setCuentaNombre(getString(rs, "CTA_NOMBRE"));
        return planDeCuentas;
    }
}

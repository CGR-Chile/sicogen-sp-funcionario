package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.PlanCuentaTitulo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanCuentaTituloMapper extends BaseMapper implements RowMapper<PlanCuentaTitulo> {
    @Override
    public PlanCuentaTitulo mapRow(ResultSet resultSet, int i) throws SQLException {
        PlanCuentaTitulo pct = new PlanCuentaTitulo();
        pct.setCodTituloCta(resultSet.getString("PL_CTA_TITULO"));
        pct.setDesTituloCta(resultSet.getString("PL_CTA_DESCRIPCION"));
        pct.setIdCtaContable(resultSet.getString("PL_CTA_ID"));
        return pct;
    }
}

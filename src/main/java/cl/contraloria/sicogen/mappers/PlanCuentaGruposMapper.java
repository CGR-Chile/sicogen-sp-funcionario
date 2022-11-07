package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.PlanCuentaGrupo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanCuentaGruposMapper extends BaseMapper implements RowMapper<PlanCuentaGrupo> {
    @Override
    public PlanCuentaGrupo mapRow(ResultSet resultSet, int i) throws SQLException {
        PlanCuentaGrupo pcg = new PlanCuentaGrupo();
        pcg.setCodGrupooCta(resultSet.getString("PL_CTA_GRUPO"));
        pcg.setDesGrupoCta(resultSet.getString("PL_CTA_DESCRIPCION"));
        pcg.setIdCtaContable(resultSet.getString("PL_CTA_ID"));
        return pcg;
    }
}

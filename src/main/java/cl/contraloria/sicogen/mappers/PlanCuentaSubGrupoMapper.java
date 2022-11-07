package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.PlanCuentaSubGrupo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PlanCuentaSubGrupoMapper extends BaseMapper implements RowMapper<PlanCuentaSubGrupo> {
    @Override
    public PlanCuentaSubGrupo mapRow(ResultSet resultSet, int i) throws SQLException {
        PlanCuentaSubGrupo pcsg = new PlanCuentaSubGrupo();
        pcsg.setCodSubGrupoCta(resultSet.getString("PL_CTA_SUBGRUPO"));
        pcsg.setDesSubGrupoCta(resultSet.getString("PL_CTA_DESCRIPCION"));
        pcsg.setIdCtaContable(resultSet.getString("PL_CTA_ID"));
        return pcsg;
    }
}

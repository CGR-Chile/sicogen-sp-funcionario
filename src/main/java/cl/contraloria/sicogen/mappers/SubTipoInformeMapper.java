package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.SubTipoInforme;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubTipoInformeMapper extends BaseMapper implements RowMapper<SubTipoInforme> {
    @Override
    public SubTipoInforme mapRow(ResultSet rs, int i) throws SQLException {
        SubTipoInforme subTipoInforme = new SubTipoInforme();
        subTipoInforme.setSubTipoID(getInteger(rs, "SUBTIPO_ID"));
        subTipoInforme.setCodigo(getInteger(rs, "CODIGO"));
        subTipoInforme.setNombre(getString(rs, "NOMBRE"));
        subTipoInforme.setIsValid(getString(rs, "ISVALID"));
        return subTipoInforme;
    }
}

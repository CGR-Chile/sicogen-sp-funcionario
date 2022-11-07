package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.SubTituloBO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SubtituloMapper extends BaseMapper implements RowMapper<SubTituloBO> {
    @Override
    public SubTituloBO mapRow(ResultSet rs, int i) throws SQLException {
        SubTituloBO subTituloBO = new SubTituloBO();
        subTituloBO.setPkCuenta(getString(rs, "CTASPRESUP_ID"));
        subTituloBO.setCodSubtitulo(getString(rs, "CTASPRESUP_SUBTITULO"));
        subTituloBO.setNomCuenta(getString(rs, "CTASPRESUP_NOMBRE"));
        return subTituloBO;
    }
}

package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.InformeContableDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InformeContableMapper extends BaseMapper implements RowMapper<InformeContableDTO> {
    @Override
    public InformeContableDTO mapRow(ResultSet rs, int i) throws SQLException {
        InformeContableDTO informeContableDTO = new InformeContableDTO();
        informeContableDTO.setFileuId(getInteger(rs, "fileu_id"));
        informeContableDTO.setPartida(getString(rs, "partida"));
        informeContableDTO.setCapitulo(getString(rs, "capitulo"));
        informeContableDTO.setInforme(getString(rs, "inf_nombre"));
        informeContableDTO.setPeriodo(getString(rs, "perd_nombre"));
        informeContableDTO.setEjercicio(getString(rs, "ejr_nombre"));
        informeContableDTO.setCodPartida(getString(rs, "cod_partida"));
        informeContableDTO.setCodCapitulo(getString(rs, "cod_capitulo"));
        informeContableDTO.setUuid(getString(rs, "notic_uuid"));
        informeContableDTO.setCodPeriodo(getString(rs, "perd_codigo"));
        return informeContableDTO;
    }
}

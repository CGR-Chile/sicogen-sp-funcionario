package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.AreaTransaccionalDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ATMapper extends BaseMapper implements RowMapper<AreaTransaccionalDTO> {
    @Override
    public AreaTransaccionalDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        AreaTransaccionalDTO sector = new AreaTransaccionalDTO();
        sector.setIdAT(Integer.valueOf(resultSet.getString("ID")));
        sector.setSector(getString(resultSet, "SECTOR"));
        sector.setInstitucion(getString(resultSet, "INSTITUCION"));
        sector.setAt(getString(resultSet, "AT"));
        sector.setNombre(getString(resultSet, "NOMBRE"));
        sector.setRut(getString(resultSet, "RUT"));
        sector.setDiv(getString(resultSet, "DiV"));
        sector.setCodPadre(getString(resultSet, "COD_PADRE"));
        sector.setTipo(getString(resultSet, "TIPO"));
        sector.setEstado(getString(resultSet, "ESTADO"));
        return sector;
    }


}

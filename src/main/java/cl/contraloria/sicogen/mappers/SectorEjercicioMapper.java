package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.AreaTransaccionalDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SectorEjercicioMapper implements RowMapper<AreaTransaccionalDTO> {

    @Override
    public AreaTransaccionalDTO mapRow(ResultSet resultSet, int i) throws SQLException {
        AreaTransaccionalDTO sector = new AreaTransaccionalDTO();

        sector.setIdSector(Integer.valueOf(resultSet.getString("SECE_ID")));
        sector.setIdEjercicioAT(Integer.valueOf(resultSet.getString("EJE_ID")));
        sector.setIdSector2(Integer.valueOf(resultSet.getString("SEC_ID")));
        sector.setSector(resultSet.getString("SEC_CODIGO"));
        sector.setNombreSector(resultSet.getString("SEC_NOMBRE"));
        sector.setEjeNombre(resultSet.getString("EJE_NOMBRE"));

        return sector;
    }







}

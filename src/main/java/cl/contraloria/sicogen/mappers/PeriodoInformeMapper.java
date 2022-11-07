package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.PeriodoInforme;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeriodoInformeMapper extends BaseMapper implements RowMapper<PeriodoInforme> {
    @Override
    public PeriodoInforme mapRow(ResultSet resultSet, int i) throws SQLException {
        PeriodoInforme pinf = new PeriodoInforme();
        pinf.setId(getInteger(resultSet, "ID"));
        pinf.setInformeId(getInteger(resultSet, "INF_ID"));
        pinf.setNombrePadre(getString(resultSet, "NOMBREPADRE"));
        pinf.setCodigo(getInteger(resultSet, "CODIGO"));
        pinf.setEjercicioId(getInteger(resultSet, "EJR_ID"));
        pinf.setPerinfSecuencia(getInteger(resultSet, "PERINF_SECUENCIA"));
        pinf.setAnulacion(getString(resultSet, "ANULACION"));
        pinf.setUsuario(getString(resultSet, "USUARIO"));
        pinf.setFecha(getString(resultSet, "FECHA"));
        return pinf;
    }
}

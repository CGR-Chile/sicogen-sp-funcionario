package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.Informes;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PeriodosInformesMapper extends BaseMapper implements RowMapper<Informes> {

    @Override
    public Informes mapRow(ResultSet resultSet, int i) throws SQLException {
        Informes informes = new Informes();
        informes.setInformeId(getInteger(resultSet, "INFORME_ID"));
        informes.setSubTipoId(getInteger(resultSet, "SUBTIPO"));
        informes.setInformeCodigo(getString(resultSet, "CODIGO"));
        informes.setInformeNombre(getString(resultSet, "NOMBRE"));
        informes.setDetInf(getString(resultSet, "DESCRIPCION"));
        informes.setInformeEstado(getString(resultSet, "ISVALID"));
        return informes;
    }
}

package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.DigitacionMPBO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DigitacionMPMapper extends BaseMapper implements RowMapper<DigitacionMPBO> {
    @Override
    public DigitacionMPBO mapRow(ResultSet rs, int i) throws SQLException {
        DigitacionMPBO digitacionMPBO = new DigitacionMPBO();
        digitacionMPBO.setIdDigitacionMP(getLong(rs, "TDRMP_ID"));
        digitacionMPBO.setIdPartida(getString(rs, "ENT_PART_NOMBRE"));
        digitacionMPBO.setIdCapitulo(getString(rs, "ENT_CAP_NOMBRE"));
        digitacionMPBO.setIdPrograma(getString(rs, "ENT_PROG_NOMBRE"));
        digitacionMPBO.setTotalIngresosCLP(getLong(rs, "TDRMP_TOTAL_ING_CLP"));
        digitacionMPBO.setTotalGastosCLP(getLong(rs, "TDRMP_TOTAL_GASTO_CLP"));
        digitacionMPBO.setTotalIngresosUSD(getLong(rs, "TDRMP_TOTAL_ING_USD"));
        digitacionMPBO.setTotalGastosUSD(getLong(rs, "TDRMP_TOTAL_GASTO_USD"));
        digitacionMPBO.setCodPartida(getString(rs, "ENT_PART_CODIGO"));
        digitacionMPBO.setCodCapitulo(getString(rs, "ENT_CAP_CODIGO"));
        digitacionMPBO.setCodPrograma(getString(rs, "ENT_PROG_CODIGO"));
        digitacionMPBO.setIdPrograma2(getInteger(rs, "ENT_PROG_ID"));
        return digitacionMPBO;
    }
}

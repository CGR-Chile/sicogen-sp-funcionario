package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.ErroresSaldoInicial;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ErroresSaldoInicialMapper  implements RowMapper<ErroresSaldoInicial> {
    @Override
    public ErroresSaldoInicial mapRow(ResultSet rs, int i) throws SQLException {
        ErroresSaldoInicial saldos = new ErroresSaldoInicial();
        saldos.setEjercicio(rs.getString("EJERCICIO"));
        saldos.setMoneda(rs.getString( "MONEDA"));
        saldos.setPartida(rs.getString("PARTIDA"));
        saldos.setCapitulo(rs.getString( "CAPITULO"));
        saldos.setPrograma(rs.getString("PROGRAMA"));
        saldos.setCuenta(rs.getString( "CUENTA"));
        saldos.setSaldoDeudor(rs.getString( "SDEUDOR"));
        saldos.setSaldoAcreedor(rs.getString( "SACREEDOR"));
        saldos.setDescripcionError(rs.getString( "ERROR"));
        return saldos;
    }
}

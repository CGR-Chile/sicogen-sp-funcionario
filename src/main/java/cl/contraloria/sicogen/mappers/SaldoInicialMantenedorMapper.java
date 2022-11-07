package cl.contraloria.sicogen.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import cl.contraloria.sicogen.model.JsonJTableExpenseBean;

public class SaldoInicialMantenedorMapper  extends BaseMapper implements RowMapper<JsonJTableExpenseBean> {
    @Override
    public JsonJTableExpenseBean mapRow(ResultSet rs, int i) throws SQLException {
        JsonJTableExpenseBean saldo = new JsonJTableExpenseBean();
        saldo.setId(getInteger(rs, "ID"));
        saldo.setSaldoDeudor(getString(rs, "DEUDOR"));
        saldo.setSaldoAcreedor(getString(rs, "ACREEDOR"));
        saldo.setEjercicio(getString(rs, "EJERCICIO"));
        saldo.setCodPartida(getString(rs, "CODIGO_PARTIDA"));
        saldo.setCodCapitulo(getString(rs, "CODIGO_CAPITULO"));
        saldo.setCodPrograma(getString(rs, "CODIGO_PROGRAMA"));
        saldo.setCuentaAgregacion(getString(rs, "CUENTA_AGREGACION"));
        saldo.setCuentaN1(getString(rs, "CUENTA_NIVEL_1"));
        saldo.setCuentaN2(getString(rs, "CUENTA_NIVEL_2"));
        saldo.setCuentaN3(getString(rs, "CUENTA_NIVEL_3"));
        saldo.setFecha(getString(rs, "FECHA"));
        saldo.setAnulacion(getString(rs, "ANULACION"));
        saldo.setMoneda(getString(rs, "MONEDA"));
        return saldo;
    }
}

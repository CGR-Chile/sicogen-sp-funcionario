package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.ItemBO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper extends BaseMapper implements RowMapper<ItemBO> {
    @Override
    public ItemBO mapRow(ResultSet rs, int i) throws SQLException {
        ItemBO itemBO = new ItemBO();
        itemBO.setPkCuenta(getString(rs, "CTASPRESUP_ID"));
        itemBO.setCodItem(getString(rs, "CTASPRESUP_ITEM"));
        itemBO.setNomCuenta(getString(rs, "CTASPRESUP_NOMBRE"));
        return itemBO;
    }
}

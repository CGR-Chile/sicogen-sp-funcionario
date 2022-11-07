package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.CtaPresupSimpleVO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CtaPresupSimpleMapper extends BaseMapper implements RowMapper<CtaPresupSimpleVO> {
    @Override
    public CtaPresupSimpleVO mapRow(ResultSet rs, int i) throws SQLException {
        CtaPresupSimpleVO ctaPresupSimpleVO = new CtaPresupSimpleVO();
        ctaPresupSimpleVO.setCodSubTitulo(getString(rs, "CTASPRESUP_SUBTITULO"));
        ctaPresupSimpleVO.setCodItem(getString(rs, "CTASPRESUP_ITEM"));
        ctaPresupSimpleVO.setCodAsignacion(getString(rs, "CTASPRESUP_ASIGNACION"));
        ctaPresupSimpleVO.setNomCuenta(getString(rs,"CTASPRESUP_NOMBRE"));
        return ctaPresupSimpleVO;
    }
}

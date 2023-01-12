package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.CuentaParticularPresupDTO;
import org.springframework.jdbc.core.RowMapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class CuentaParticularMapper extends BaseMapper  {

    public static CuentaParticularPresupDTO mapRow(HttpServletRequest req) {
        CuentaParticularPresupDTO cta = new CuentaParticularPresupDTO();
        cta.setIdEjercicio(Integer.valueOf(req.getParameter("idEjercicio")));
        cta.setIdPartida(Integer.valueOf(req.getParameter("idPartida")));
        cta.setIdCapitulo(Integer.valueOf(req.getParameter("idCapitulo")));
        cta.setIdPrograma(Integer.valueOf(req.getParameter("idPrograma")));
        cta.setCodCuenta(req.getParameter("codCuenta"));
        cta.setDescripcion(req.getParameter("descripcion"));
        cta.setIsImputable(Integer.valueOf("SI".equals(req.getParameter("isImputable"))?1:0));
        cta.setIsDecreto(Integer.valueOf("DECRETO".equals(req.getParameter("isDecreto"))?1:0));//Hace referencia el Origen
        if(req.getParameter("numeroDoc")!=null && !"".equals(req.getParameter("numeroDoc"))) {
            cta.setNumeroDoc(Integer.valueOf(req.getParameter("numeroDoc")));
        }
        String[] codCuentaSplit = cta.getCodCuenta().split("\\.");
        cta.setCodSubtitulo(codCuentaSplit[0]);
        cta.setCodItem(codCuentaSplit[1]);
        cta.setCodAsignacion(codCuentaSplit[2]);
        cta.setCodSubAsignacion(codCuentaSplit[3]);
        return cta;
    }
}

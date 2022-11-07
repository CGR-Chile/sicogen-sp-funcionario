package cl.contraloria.sicogen.service;

import cl.contraloria.sicogen.dao.SistradocDAO;
import cl.contraloria.sicogen.model.CatalogarTDRDTO;
import cl.contraloria.sicogen.model.ResultadoEjecucion;
import org.springframework.stereotype.Service;

@Service
public class SistradocService {
    private SistradocDAO sistradocDAO;

    public SistradocService(SistradocDAO sistradocDAO) {
        this.sistradocDAO = sistradocDAO;
    }

    public ResultadoEjecucion asignarTDR(CatalogarTDRDTO catalogarTDRDTO) {
        return sistradocDAO.asignarTDR(catalogarTDRDTO);
    }
}

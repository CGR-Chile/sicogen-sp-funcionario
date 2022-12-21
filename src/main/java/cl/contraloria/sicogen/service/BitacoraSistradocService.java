package cl.contraloria.sicogen.service;

import cl.contraloria.sicogen.dao.BitacoraSistradocDAO;
import cl.contraloria.sicogen.dao.SistradocDAO;
import cl.contraloria.sicogen.model.BitacoraSistradocDTO;
import cl.contraloria.sicogen.model.CaratulaDTO;
import cl.contraloria.sicogen.model.CatalogarTDRDTO;
import cl.contraloria.sicogen.model.ResultadoEjecucion;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class BitacoraSistradocService {
    private BitacoraSistradocDAO bitacoraSistradocDAO;

    public BitacoraSistradocService(BitacoraSistradocDAO bitacoraSistradocDAO) {
        this.bitacoraSistradocDAO = bitacoraSistradocDAO;
    }

    public List<BitacoraSistradocDTO> getBitacoraSistradoc(String documentoId ) throws SQLException {
        return bitacoraSistradocDAO.getBitacoraSistradoc(documentoId);
    }
    public ResultadoEjecucion insertBitacoraCaratula(BitacoraSistradocDTO bitacoraSistradocDTO ) {
        return bitacoraSistradocDAO.insertBitacoraCaratula(bitacoraSistradocDTO);
    }
}

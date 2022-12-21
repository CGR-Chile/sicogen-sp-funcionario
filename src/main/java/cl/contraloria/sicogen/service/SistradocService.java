package cl.contraloria.sicogen.service;

import cl.contraloria.sicogen.dao.BitacoraSistradocDAO;
import cl.contraloria.sicogen.dao.SistradocDAO;
import cl.contraloria.sicogen.model.BitacoraSistradocDTO;
import cl.contraloria.sicogen.model.CaratulaDTO;
import cl.contraloria.sicogen.model.CatalogarTDRDTO;
import cl.contraloria.sicogen.model.ResultadoEjecucion;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SistradocService {
    public static final String CREACION = "Creación";
    public static final String MODIFICACION = "Modificación";
    private SistradocDAO sistradocDAO;
    private BitacoraSistradocDAO bitacoraSistradocDAO;

    public SistradocService(SistradocDAO sistradocDAO, BitacoraSistradocDAO bitacoraSistradocDAO) {
        this.sistradocDAO = sistradocDAO;
        this.bitacoraSistradocDAO = bitacoraSistradocDAO;
    }

    public ResultadoEjecucion asignarTDR(CatalogarTDRDTO catalogarTDRDTO) {
        return sistradocDAO.asignarTDR(catalogarTDRDTO);
    }

    public CaratulaDTO getSistradoc(String documentoId) {
        return sistradocDAO.getCaratula(documentoId);
    }

    public List<CaratulaDTO> getAllSistradoc(String idDocumento, String tipoInforme, String tipoDocumento, String ejercicio, String nroDocumento, String entidadEmisora, String fechaDocumento) throws SQLException {
        return sistradocDAO.getAllCaratula(idDocumento, tipoInforme, tipoDocumento, ejercicio, nroDocumento, entidadEmisora, fechaDocumento);
    }

    public ResultadoEjecucion insertUpdCaratula(CaratulaDTO caratulaDTO, boolean creacion) throws SQLException {
        ResultadoEjecucion resultadoEjecucion;
        List<CaratulaDTO> caratulaDTOList = sistradocDAO.getAllCaratula(null, null, caratulaDTO.getTipoDocumento(), null, String.valueOf(caratulaDTO.getNroDocumento()), caratulaDTO.getEntidadEmisora(), caratulaDTO.getFechaDocumento());
        CaratulaDTO caratulaDTO1;
        if (caratulaDTOList.size() > 0 && creacion) {
             caratulaDTO1 = caratulaDTOList.stream()
                    .max(Comparator.comparingInt(CaratulaDTO::getSecuenciaReingreso))
                    .get();
            caratulaDTO.setSecuenciaReingreso(caratulaDTO1.getSecuenciaReingreso()+1);
        }
        String operacion;
        BitacoraSistradocDTO bitacoraSistradocDTO ;
        if (creacion) {
            operacion = CREACION;
            caratulaDTO.setEstadoSistradoc("2");
        } else {
            operacion = MODIFICACION;
        }
        resultadoEjecucion = sistradocDAO.insertUpdCaratula(caratulaDTO);
        if (String.valueOf(0).equals(resultadoEjecucion.getCodEjec())) ;
        {

            caratulaDTO.setIdDocumento(resultadoEjecucion.getNewId());
            bitacoraSistradocDTO = getBitacoraSistradocDTO(caratulaDTO, operacion);
            bitacoraSistradocDTO.setSecuenciaReingreso(caratulaDTO.getSecuenciaReingreso());
            bitacoraSistradocDAO.insertBitacoraCaratula(bitacoraSistradocDTO);
        }
        return resultadoEjecucion;
    }

    private BitacoraSistradocDTO getBitacoraSistradocDTO(CaratulaDTO caratulaDTO, String operacion) {
        BitacoraSistradocDTO bitacoraSistradocDTO = new BitacoraSistradocDTO();
        bitacoraSistradocDTO.setIdDocumento(caratulaDTO.getIdDocumento());
        bitacoraSistradocDTO.setTipoDocumento(caratulaDTO.getTipoDocumento());
        bitacoraSistradocDTO.setEntidadEmisora(caratulaDTO.getEntidadEmisora());
        bitacoraSistradocDTO.setNroDocumento(caratulaDTO.getNroDocumento());
        bitacoraSistradocDTO.setFechaDocumento(caratulaDTO.getFechaDocumento());
        bitacoraSistradocDTO.setFechaRecepcionCGR(caratulaDTO.getFechaRecepcionCGR());
        bitacoraSistradocDTO.setAnalista(caratulaDTO.getAnalista());
        bitacoraSistradocDTO.setMateriaGeneral(caratulaDTO.getMateriaGeneral());
        bitacoraSistradocDTO.setMateriaEspecifica(caratulaDTO.getMateriaEspecifica());
        bitacoraSistradocDTO.setAnno(caratulaDTO.getAnno());
        bitacoraSistradocDTO.setEjercicio(caratulaDTO.getEjercicio().getEjercicioId());
        bitacoraSistradocDTO.setIdUsuario(caratulaDTO.getIdUsuario());
        bitacoraSistradocDTO.setOperacion(operacion);
        return bitacoraSistradocDTO;
    }

    public String eliminarCaratula(String idDocumento) {
        return String.valueOf(sistradocDAO.eliminarCaratula(idDocumento));
    }

}

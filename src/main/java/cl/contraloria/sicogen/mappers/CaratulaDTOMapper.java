package cl.contraloria.sicogen.mappers;

import cl.contraloria.sicogen.model.CaratulaDTO;
import cl.contraloria.sicogen.model.DocumentoBO;
import cl.contraloria.sicogen.model.EjerciciosDTO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CaratulaDTOMapper extends BaseMapper implements RowMapper<CaratulaDTO> {
    @Override
    public CaratulaDTO mapRow(ResultSet rs, int i) throws SQLException {
        CaratulaDTO caratulaDTO = new CaratulaDTO();
        EjerciciosDTO ejercicio = new EjerciciosDTO();

        caratulaDTO.setIdDocumento(getInteger(rs, "ID_SISTRADOC"));
        caratulaDTO.setEstadoSicogen(getString(rs, "ESTADO_SICOGEN"));
        caratulaDTO.setEstadoSistradoc(getString(rs, "ID_ESTADO_SISTRADOC"));
        caratulaDTO.setEntidadEmisora(getString(rs, "ENTIDAD_EMISORA"));
        caratulaDTO.setTipoDocumento(getString(rs, "TIPO_DOCUMENTO"));
        caratulaDTO.setNroDocumento(getInteger(rs, "NUMERO_DOCUMENTO"));
        caratulaDTO.setFechaDocumento(getString(rs, "FECHA_DOCUMENTO"));
        caratulaDTO.setFechaRecepcionCGR(getString(rs, "FECHA_RECEPCION_CGR"));
        caratulaDTO.setAnalista(getString(rs, "ANALISTA"));
        caratulaDTO.setMateriaGeneral(getString(rs, "MATERIA_GENERAL"));
        caratulaDTO.setMateriaEspecifica(getString(rs, "MATERIA_ESPECIFICA"));
        ejercicio.setEjercicioId(getInteger(rs, "ID_EJERCICIO"));
        ejercicio.setEjercicioNombre(getString(rs, "EJERCICIO"));
        caratulaDTO.setSecuenciaReingreso(getInteger(rs, "SECUENCIA_REINGRESO"));
        caratulaDTO.setEjercicio(ejercicio);
        caratulaDTO.setAnno(getInteger(rs, "ANNO"));
        return caratulaDTO;
    }
}

package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.mappers.*;
import cl.contraloria.sicogen.model.*;
import oracle.jdbc.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedCaseInsensitiveMap;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FiltrosDAO {

    // Package Names
    private static final String PKG_INFORMES = "PKG_INFORMES";
    private static final String PKG_SISTRADOC = "PKG_SISTRADOC";
    private static final String PKG_FILTRO = "PKG_FILTRO";
    private static final String PKG_MANTENEDORES = "PKG_MANTENEDORES";
    private static final String C_TIPO_INF = "cTIPOINF";

    /* JdbcTemplate */
    private JdbcTemplate jdbcTemplate;

    public FiltrosDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<EjerciciosDTO> getEjercicios() {
        SimpleJdbcCall llamada = getEjerciciosCall();
        SqlParameterSource parametros = new MapSqlParameterSource();
        List<LinkedCaseInsensitiveMap<Object>> result = llamada.executeFunction(ArrayList.class, parametros);
        List<EjerciciosDTO> listaEjercicios = new ArrayList<EjerciciosDTO>();

        for (int i = 0; i < result.size(); i++) {
            LinkedCaseInsensitiveMap<Object> row = result.get(i);
            EjerciciosDTO ejerciciosDTO = new EjerciciosDTO();
            BigDecimal bdEjerId = (BigDecimal) row.get("EJERCICIO_ID");
            ejerciciosDTO.setEjercicioId(bdEjerId.intValue());
            ejerciciosDTO.setEjercicioNombre((String) row.get("EJERCICIO_CODIGO"));
            ejerciciosDTO.setEjercicioNombre((String) row.get("EJERCICIO_NOMBRE"));
            listaEjercicios.add(ejerciciosDTO);
        }
        return listaEjercicios;
    }

    private SimpleJdbcCall getEjerciciosCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withFunctionName("RECUPERA_EJERCICIOS_FNC");
    }

    public List<EstadoSicogenDTO> getEstadosSicogen() {
        SimpleJdbcCall llamada = getEstadosSicogenCall();
        Map<String, Object> respuesta = llamada.execute();
        return (List<EstadoSicogenDTO>) respuesta.get("pCURSOR_ESTADO");
    }

    private SimpleJdbcCall getEstadosSicogenCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_SISTRADOC)
                .withProcedureName("RECUPERA_ESTADO_SICOGEN_v2")
                .declareParameters(
                        new SqlOutParameter("pCURSOR_ESTADO", OracleTypes.CURSOR, new EstadoSicogenMapper())
                );
    }

    public List<CapituloBO> getCapituloByPartidaId(String codPartida, String idEjercicio) {
        SimpleJdbcCall llamada = getCapituloByPartidaIdCall();
        Map<String, Object> parametros = getCapituloByPartidaIdParams(codPartida, idEjercicio);
        Map<String, Object> respuesta = llamada.execute(parametros);
        return (List<CapituloBO>) respuesta.get("CURSOR_CAPITULOS");
    }

    private Map<String, Object> getCapituloByPartidaIdParams(String codPartida, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pPARTIDA_COD", codPartida);
        parametros.put("pEJERICIO", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getCapituloByPartidaIdCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_FILTRO)
                .withProcedureName("RECUPERA_CAPITULO_SP_NEW")
                .declareParameters(
                        new SqlParameter("pPARTIDA_COD", OracleTypes.VARCHAR),
                        new SqlParameter("pEJERICIO", OracleTypes.NUMERIC),
                        new SqlOutParameter("CURSOR_CAPITULOS", OracleTypes.CURSOR, new CapituloBOMapper())
                );
    }

    public List<ProgramaBO> getProgramaByCapituloId(String idCapitulo, String idEjercicio) {
        SimpleJdbcCall llamada = getProgramaByCapituloIdCall();
        Map<String, Object> parametros = getProgramaByCapituloIdParams(idCapitulo, idEjercicio);
        Map<String, Object> respuesta = llamada.execute(parametros);
        return (List<ProgramaBO>) respuesta.get("CURSOR_PROGRAMAS");
    }

    private Map<String, Object> getProgramaByCapituloIdParams(String idCapitulo, String idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEJERCICIO", idEjercicio);
        parametros.put("pCAPITULO", idCapitulo);
        return parametros;
    }

    private SimpleJdbcCall getProgramaByCapituloIdCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_FILTRO)
                .withProcedureName("RECUPERA_PROGRAMA_SP")
                .declareParameters(
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMERIC),
                        new SqlParameter("pCAPITULO", OracleTypes.NUMERIC),
                        new SqlOutParameter("CURSOR_PROGRAMAS", OracleTypes.CURSOR, new ProgramaBOMapper())
                );
    }

    public List<TiposDeInformes> getTiposDeInformes() {
        SimpleJdbcCall llamada = getTiposDeInformesCall();
        Map<String, Object> result = llamada.execute(new HashMap<String, Object>());
        return (List<TiposDeInformes>) result.get(C_TIPO_INF);
    }

    private SimpleJdbcCall getTiposDeInformesCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("GET_TIPO_INFORMES")
                .declareParameters(
                        new SqlOutParameter(C_TIPO_INF, OracleTypes.CURSOR, new TiposDeInformesMapper())
                );
    }

    public List<Periodos> getPeriodos() {
        SimpleJdbcCall llamada = getPeriodosCall();
        Map<String, Object> result = llamada.execute();
        return (List<Periodos>) result.get("CURSOR_PERIODOS");
    }

    private SimpleJdbcCall getPeriodosCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("GET_PERIODOS")
                .declareParameters(
                        new SqlOutParameter("CURSOR_PERIODOS", OracleTypes.CURSOR, new PeriodosMapper())
                );
    }

    public List<Region> getRegiones() {
        SimpleJdbcCall llamada = getRegionesCall();
        Map<String, Object> result = llamada.execute();
        return (List<Region>) result.get("pREGIONES_CURSOR");
    }

    private SimpleJdbcCall getRegionesCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_MANTENEDORES)
                .withProcedureName("GET_REGIONES").declareParameters(
                        new SqlOutParameter("pREGIONES_CURSOR", OracleTypes.CURSOR, new RegionMapper())
                );
    }

    public List<Informes> getInformes() {
        SimpleJdbcCall llamada = getInformesCall();
        Map<String, Object> result = llamada.execute();
        return (List<Informes>) result.get("pINFORMES");
    }

    private SimpleJdbcCall getInformesCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("GET_INFORMES").declareParameters(
                        new SqlOutParameter("pINFORMES", OracleTypes.CURSOR, new PeriodosInformesMapper())
                );
    }

    public List<Periodos> getPeriodosByEjercicio(Integer idEjercicio) {
        SimpleJdbcCall llamada = getPeriodosByEjercicioCall();
        Map<String, Object> parametros = getPeriodosByEjercicioParams(idEjercicio);
        Map<String, Object> result = llamada.execute(parametros);
        return (List<Periodos>) result.get("CURSOR_PERIODOS");
    }

    private Map<String, Object> getPeriodosByEjercicioParams(Integer idEjercicio) {
        Map<String, Object> parametros = new HashMap<String, Object>();
        parametros.put("pEJERCICIO", idEjercicio);
        return parametros;
    }

    private SimpleJdbcCall getPeriodosByEjercicioCall() {
        return new SimpleJdbcCall(jdbcTemplate)
                .withCatalogName(PKG_INFORMES)
                .withProcedureName("RECUPERA_PERIODOS_SP").declareParameters(
                        new SqlParameter("pEJERCICIO", OracleTypes.NUMERIC),
                        new SqlOutParameter("CURSOR_PERIODOS", OracleTypes.CURSOR, new PeriodosMapper())
                );
    }
}

package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.mappers.DocumentoSistradocMapper;
import cl.contraloria.sicogen.mappers.InformeSistradocMapper;
import cl.contraloria.sicogen.model.InformeSistradocBO;
import cl.contraloria.sicogen.model.InformeTDR;
import cl.contraloria.sicogen.utils.CerrarConexion;
import cl.contraloria.sicogen.utils.ConexionBD;
import cl.contraloria.sicogen.utils.ErroresSicogen;
import oracle.jdbc.OracleTypes;
import org.apache.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InformesTDRDAO extends CerrarConexion {
	
	private static final Logger logger = Logger.getLogger(InformesTDRDAO.class);
	private static final String PKG_SISTRADOC = "PKG_SISTRADOC";
	private ErroresSicogen error;

	/* JdbcTemplate */
	private JdbcTemplate jdbcTemplate;

	public InformesTDRDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<InformeSistradocBO> getInformesTDR(Integer idInforme,
												   Integer idEjercicio,
												   Integer idEstadoSicogen,
												   Integer idPeriodo) {
		
		SimpleJdbcCall llamada = getInformesTDRCall();
		Map<String, Object> parametros = getInformesTDRParams(idInforme, idEstadoSicogen, idEjercicio, idPeriodo);
		Map<String, Object> respuesta = llamada.execute(parametros);
		return (List<InformeSistradocBO>) respuesta.get("pCURSOR_DOC_ENTRAMITE");
	}

	private Map<String, Object> getInformesTDRParams(Integer idInforme,
													 Integer idEstadoSicogen,
													 Integer idEjercicio,
													 Integer idPeriodo) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("pINFORME", idInforme);
		parametros.put("pESTADO_SICOGEN", idEstadoSicogen);
		parametros.put("pID_EJERCICIO", idEjercicio);
		parametros.put("pPER_EJER", idPeriodo);
		return parametros;
	}

	private SimpleJdbcCall getInformesTDRCall() {
		return new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName(PKG_SISTRADOC)
				.withProcedureName("GET_DOC_ENTRAMITE_v2")
				.declareParameters(
						new SqlParameter("pINFORME", OracleTypes.NUMBER),
						new SqlParameter("pESTADO_SICOGEN", OracleTypes.NUMBER),
						new SqlParameter("pID_EJERCICIO", OracleTypes.NUMBER),
						new SqlParameter("pPER_EJER", OracleTypes.NUMBER),
						new SqlOutParameter("pCURSOR_DOC_ENTRAMITE", OracleTypes.CURSOR, new InformeSistradocMapper())
				);
	}


	public List<InformeSistradocBO> getInformesAsigTDR() throws Exception{
		
		Connection cnn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ConexionBD con = new ConexionBD();
		List<InformeSistradocBO> lstInfTDR = new ArrayList<InformeSistradocBO>();
		
		try {
			cnn = con.abrirConexionAtencion();
			cs  = cnn.prepareCall("{call PKG_SISTRADOC.RECUPERA_INFORMES_SISTRADOC(?,?,?)}");
			
			cs.registerOutParameter(1, OracleTypes.CURSOR);	
			cs.registerOutParameter(2, OracleTypes.NUMBER);	
			cs.registerOutParameter(3, OracleTypes.VARCHAR);	
			
			cs.executeQuery();	
			
			int codigo = cs.getInt(2);
			String mensaje = cs.getString(3);
			logger.info("Codigo Respuesta: "+codigo);
			logger.info("Mensaje Respuesta: "+mensaje);
			
			if(codigo == 0){
				
				rs = (ResultSet) cs.getObject(1);
				
				while(rs.next()){
					
					InformeSistradocBO informe = new InformeSistradocBO();
					informe.setIdDocumento(rs.getInt("ID_SISTRADOC"));
					informe.setEstadoSicogen(rs.getString("ESTADO_SICOGEN"));
					informe.setEstadoSistradoc(rs.getString("ESTADO_SISTRADOC"));
					informe.setEntidadEmisora(rs.getString("ENTIDAD_EMISORA"));
					informe.setTipoDocumento(rs.getString("TIPO_DOCUMENTO"));
					informe.setNroDocumento(rs.getInt("NUMERO_DOCUMENTO"));
					informe.setFechaDocumento(rs.getString("FECHA_DOCUMENTO"));
					informe.setFechaRecepcionCGR(rs.getString("FECHA_RECEPCION_CGR"));
					informe.setAnalista(rs.getString("ANALISTA"));
					informe.setMateria(rs.getString("MATERIA"));
					
					logger.info(informe.toString());
					lstInfTDR.add(informe);		
				}
				
			}else{
				logger.error("Ocurri� un error al obtener Lista SISTRADOC ");
				logger.info("Retorna Lista vac�a... ");
			}
			
		}catch (NamingException e){
			error=new ErroresSicogen();
			logger.error("Error NamingException en el metodo getInformesAsigTDR " + e.getMessage());
			error.setTipo(1,0);
			error.setMetodo("FiltrosDAO - getInformesAsigTDR");
			error.setErrorTecnico(e);
			throw e;
		} catch (SQLException e){
			error=new ErroresSicogen();
			logger.error("Error SQLException en el metodo getInformesAsigTDR " + e.getMessage());
			error.setTipo(3,e.getErrorCode());
			error.setMetodo("FiltrosDAO - getInformesAsigTDR");
			error.setErrorTecnico(e);
			throw e;
		}catch(Exception ex){
			error=new ErroresSicogen();
			logger.error("Error Exception en el  metodo getInformesAsigTDR " + ex.getMessage());
			error.setTipo(2,0);
			error.setMetodo("FiltrosDAO - getInformesAsigTDR");
			error.setErrorTecnico(ex);
			throw ex;
		}finally {
			super.cerrarCallable(cs);
			super.cerrarResultSet(rs);
			super.cerrarConexion(cnn);
		}
		
		return lstInfTDR;
		
	}

	public InformeSistradocBO getInformeTDR(String idSistradoc) {
		SimpleJdbcCall llamada = getInformeTDRCall();
		Map<String, Object> parametros = getInformeTDRParams(idSistradoc);
		Map<String, Object> respuesta = llamada.execute(parametros);
		List<InformeSistradocBO> result = (List<InformeSistradocBO>) respuesta.get("pCURSOR_DOC_SISTRADOC");
		return result.get(0);
	}

	private Map<String, Object> getInformeTDRParams(String idSistradoc) {
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("pIDSISTRADOC", idSistradoc);
		return parametros;
	}

	private SimpleJdbcCall getInformeTDRCall() {
		return new SimpleJdbcCall(jdbcTemplate)
				.withCatalogName(PKG_SISTRADOC)
				.withProcedureName("GET_DOC_SISTRADOC")
				.declareParameters(
						new SqlParameter("pIDSISTRADOC", OracleTypes.VARCHAR),
						new SqlOutParameter("pCURSOR_DOC_SISTRADOC", OracleTypes.CURSOR, new DocumentoSistradocMapper())
				);
	}


	public InformeSistradocBO getInformeTDRbyIdFileUpload(String idFileUpload)throws Exception{
		
		Connection cnn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ConexionBD con = new ConexionBD();
		
		InformeSistradocBO informeSistradoc = new InformeSistradocBO();
		
		try {
			cnn = con.abrirConexionAtencion();
			cs  = cnn.prepareCall("{call PKG_SISTRADOC.GET_DOC_SISTRADOC_FILEU_ID(?,?)}");
			
			cs.setString(1, idFileUpload);
			cs.registerOutParameter(2, OracleTypes.CURSOR);			
			cs.executeQuery();			
			rs = (ResultSet) cs.getObject(2);
			
			while(rs.next()){
				
				informeSistradoc.setIdDocumento(rs.getInt("ID_SISTRADOC"));
				informeSistradoc.setEstadoSicogen(rs.getString("ESTADO_SICOGEN"));
				informeSistradoc.setEstadoSistradoc(rs.getString("ESTADO_SISTRADOC"));
				informeSistradoc.setEntidadEmisora(rs.getString("ENTIDAD_EMISORA"));
				informeSistradoc.setTipoDocumento(rs.getString("TIPO_DOCUMENTO"));
				informeSistradoc.setNroDocumento(rs.getInt("NUMERO_DOCUMENTO"));
				informeSistradoc.setFechaDocumento(rs.getString("FECHA_DOCUMENTO"));
				informeSistradoc.setFechaRecepcionCGR(rs.getString("FECHA_RECEPCION_CGR"));
				informeSistradoc.setAnalista(rs.getString("ANALISTA"));
				informeSistradoc.setMateria(rs.getString("MATERIA"));
				
				informeSistradoc.setIdFileUpload(rs.getString("FILEU_ID"));
				informeSistradoc.setNombreArchivo(rs.getString("NOMBRE"));
				informeSistradoc.setIdEstadoSicogen(rs.getString("ESTADO_ID"));
				
				informeSistradoc.setIdUsuario(rs.getString("USR_ID"));
				informeSistradoc.setNombreUsuario(rs.getString("NOMBRE_USUARIO"));
			}
			
		}catch (NamingException e){
			error=new ErroresSicogen();
			logger.error("Error NamingException en el metodo getInformeTDR " + e.getMessage());
			error.setTipo(1,0);
			error.setMetodo("FiltrosDAO - getInformeTDR");
			error.setErrorTecnico(e);
			throw e;
		} catch (SQLException e){
			error=new ErroresSicogen();
			logger.error("Error SQLException en el metodo getInformeTDR " + e.getMessage());
			error.setTipo(3,e.getErrorCode());
			error.setMetodo("FiltrosDAO - getInformeTDR");
			error.setErrorTecnico(e);
			throw e;
		}catch(Exception ex){
			error=new ErroresSicogen();
			logger.error("Error Exception en el  metodo getInformeTDR " + ex.getMessage());
			error.setTipo(2,0);
			error.setMetodo("FiltrosDAO - getInformeTDR");
			error.setErrorTecnico(ex);
			throw ex;
		}finally {
			super.cerrarCallable(cs);
			super.cerrarResultSet(rs);
			super.cerrarConexion(cnn);
		}
		
		return informeSistradoc;
		
	}

	
	public InformeTDR obtenerArchivoTDR(String idEjercicio, String nroDoc, String idInforme){

		Connection cnn = null;
		CallableStatement cs = null;
		ResultSet rs  = null;
		ConexionBD con = new ConexionBD();
		InformeTDR informeTDR = new InformeTDR();
		
		try {
			cnn = con.abrirConexionAtencion();
			cs   = cnn.prepareCall("{call PKG_INFORMES.OBTIENE_INFORME_TDR(?,?,?,?,?,?)}");
			cs.setString(1, idEjercicio);
			cs.setString(2, nroDoc);
			cs.setString(3, idInforme);
			cs.registerOutParameter(4, OracleTypes.CURSOR);
			cs.registerOutParameter(5, OracleTypes.VARCHAR);
			cs.registerOutParameter(6, OracleTypes.VARCHAR);
			cs.executeQuery();			
			rs = (ResultSet) cs.getObject(4);
			
			String codRespuesta = (String) cs.getString(5);
			String msnRespuesta = (String) cs.getString(6);	
			
			logger.info("CodRespuesta: "+codRespuesta+" MsjRespuesta: "+msnRespuesta);
			
			while(rs.next()){
				
				informeTDR.setNombre(rs.getString("NOMBRE_INF"));
				informeTDR.setCodigo(rs.getString("CODIGO"));	
				
				if("0".equals(codRespuesta)){
					
					informeTDR.setEstadoSicogen(rs.getString("ESTADO"));
					informeTDR.setNombreArchivo(rs.getString("NOMBRE_XML"));
					informeTDR.setIdFileUpload(rs.getString("ID_ARCHIVO"));
					
					logger.info(informeTDR.toString());
				}
			}
			
			
		}catch(NamingException e){	logger.info("Error NamingException en el metodo obtenerArchivoTDR " + e.getMessage());	e.printStackTrace();
		}catch(SQLException e){		logger.info("Error SQLException en el metodo obtenerArchivoTDR " + e.getMessage());	e.printStackTrace();
		}catch(Exception ex){		logger.info("Error en el metodo obtenerArchivoTDR " + ex.getMessage());		
		}finally{					super.cerrarCallable(cs); super.cerrarResultSet(rs); super.cerrarConexion(cnn);
		}
		
		return informeTDR;
	}
	
	

	public void procesarArchivoTDR(String idFileUpload, String userName){

		if("".equalsIgnoreCase(idFileUpload) || idFileUpload == null){
			idFileUpload = "123";
		}
		
		Connection cnn = null;
		CallableStatement cs = null;
		ConexionBD con = new ConexionBD();
		try {
			cnn = con.abrirConexionAtencion();
			cs   = cnn.prepareCall("{call PKG_FILES.ACTUALIZA_ESTADO_AUTO(?,?,?,?)}");
			cs.setString(1, idFileUpload);
			cs.setString(2, userName);
			cs.registerOutParameter(3, OracleTypes.VARCHAR);
			cs.registerOutParameter(4, OracleTypes.VARCHAR);
			cs.executeQuery();			

			String codRespuesta = (String) cs.getString(3);
			String msnRespuesta = (String) cs.getString(4);	

			logger.info("codRespuesta : "+codRespuesta + " - msnRespuesta : "+msnRespuesta );
			
		}catch(NamingException e){	logger.info("Error NamingException en el metodo procesarArchivoTDR " + e.getMessage());	e.printStackTrace();
		}catch(SQLException e){		logger.info("Error SQLException en el metodo procesarArchivoTDR " + e.getMessage());	e.printStackTrace();
		}catch(Exception ex){		logger.info("Error en el metodo procesarArchivoTDR " + ex.getMessage());		
		}finally{					super.cerrarCallable(cs); super.cerrarConexion(cnn);
		}
		
		
	}
	
	public ErroresSicogen getError() {
		return error;
	}


	public void setError(ErroresSicogen error) {
		this.error = error;
	}



}

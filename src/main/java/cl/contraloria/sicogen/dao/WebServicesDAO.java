package cl.contraloria.sicogen.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;
import javax.naming.NamingException;
import cl.contraloria.sicogen.model.WebServices;
import cl.contraloria.sicogen.utils.CerrarConexion;
import cl.contraloria.sicogen.utils.ConexionBD;
import oracle.jdbc.OracleTypes;


public class WebServicesDAO extends CerrarConexion {

	private String error;
	
	public WebServices getUrlWS(int periodo, int informe, int tipows)throws Exception {
		Connection cnn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		
		ConexionBD con = new ConexionBD();
		WebServices rwWEBSERVICE = new WebServices();
		try {
			cnn = con.abrirConexionAtencion();
			cs   = cnn.prepareCall("{call PKG_CONTRALORIA_EPU.GET_WS(?,?,?,?) }");
			cs.setInt(1, informe);
			cs.setInt(2, periodo);
			cs.setInt(3, tipows);
			
			cs.registerOutParameter(4, OracleTypes.CURSOR);
			cs.executeQuery();
			rs = (ResultSet) cs.getObject(4);
			
			while(rs.next()){
				rwWEBSERVICE.setUrl(rs.getString("URL"));
				rwWEBSERVICE.setTimeout(rs.getInt("TOUT"));
			}
		}catch(NamingException e){	
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(SQLException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(Exception e){
			e.fillInStackTrace();
		}finally {					
			super.cerrarCallable(cs);
			super.cerrarResultSet(rs);
			super.cerrarConexion(cnn);
		}
		
		return rwWEBSERVICE;
	}	
	
	
	public WebServices getUrlWSCarga(int idWS)throws Exception {
		Connection cnn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		
		ConexionBD con = new ConexionBD();
		WebServices rwWEBSERVICE = new WebServices();
		try {
			cnn = con.abrirConexionAtencion();
			cs   = cnn.prepareCall("{call PKG_PARAMETROS.GET_WS_CARGA(?,?) }");
			cs.setInt(1, idWS);
			
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.executeQuery();
			rs = (ResultSet) cs.getObject(2);
			
			while(rs.next()){
				rwWEBSERVICE.setUrl(rs.getString("URL"));
				rwWEBSERVICE.setTimeout(rs.getInt("TOUT"));
			}
		}catch(NamingException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(SQLException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(Exception e){
			e.fillInStackTrace();
		}finally {
			super.cerrarCallable(cs);
			super.cerrarResultSet(rs);
			super.cerrarConexion(cnn);
		}
		
		return rwWEBSERVICE;
	}	
	
	
	public WebServices getUrlWSValidacion(int idWS)throws Exception {
		
		Connection cnn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		
		ConexionBD con = new ConexionBD();
		WebServices rwWEBSERVICE = new WebServices();
		try {
			cnn = con.abrirConexionAtencion();
			cs   = cnn.prepareCall("{call PKG_PARAMETROS.GET_WS_VALIDACION(?,?) }");
			cs.setInt(1, idWS);
			
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.executeQuery();
			rs = (ResultSet) cs.getObject(2);
			
			while(rs.next()){
				rwWEBSERVICE.setUrl(rs.getString("URL"));
				rwWEBSERVICE.setTimeout(rs.getInt("TOUT"));
			}
		}catch(NamingException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(SQLException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(Exception e){
			e.fillInStackTrace();
		}finally {
			super.cerrarCallable(cs);
			super.cerrarResultSet(rs);
			super.cerrarConexion(cnn);
		}
		
		return rwWEBSERVICE;
	}
	
	
	public List<WebServices> getWebservices(String order)throws Exception{
		Connection cnn=null;
		CallableStatement cs=null;
		ResultSet rs=null;		
		ConexionBD con=new ConexionBD();
		List<WebServices> lws=new Vector<WebServices>();
		try {
			cnn = con.abrirConexionAtencion();
			cs = cnn.prepareCall("{call PKG_INFORMES.GET_WEBSERVICES(?,?)}");
			cs.setString(1, order);
			cs.registerOutParameter(2, OracleTypes.CURSOR);
			cs.executeQuery();
			rs = (ResultSet) cs.getObject(2);
			while(rs.next()){
				WebServices ws=new WebServices();		
				ws.setId(rs.getInt("ID"));
				ws.setNombre(rs.getString("Nombre"));
				ws.setTipo(rs.getInt("TIPO"));
				ws.setUrl(rs.getString("URL"));
				ws.setTimeout(rs.getInt("TIMEOUT"));
				ws.setAnulacion(rs.getString("ANULACION"));
				ws.setUsuario(rs.getString("USUARIO"));
				ws.setFecha(rs.getString("FECHA"));
				lws.add(ws);
			}
		}catch(NamingException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(SQLException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(Exception e){
			e.fillInStackTrace();
		}finally {
			super.cerrarCallable(cs);
			super.cerrarResultSet(rs);
			super.cerrarConexion(cnn);
		}
		return lws;
	}
	
	
	public WebServices setWebservices(WebServices ws)throws Exception{
		Connection cnn=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		ConexionBD con = new ConexionBD();
		try {
			cnn = con.abrirConexionAtencion();
			cs = cnn.prepareCall("{call PKG_INFORMES.SET_WEBSERVICES(?,?,?,?, ?,?,?) }");
			cs.setInt(1, ws.getTipo());
			cs.setString(2, ws.getNombre());
			cs.setString(3, ws.getUrl());
			cs.setInt(4, ws.getTimeout());
			cs.setString(5, ws.getUsuario());
			cs.registerOutParameter(6, OracleTypes.NUMERIC);		
			cs.registerOutParameter(7, OracleTypes.VARCHAR);
			cs.executeQuery();	
			ws.setRespuesta(cs.getInt(6));
			ws.setMensaje(cs.getString(7));
		}catch(NamingException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(SQLException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(Exception e){
			e.fillInStackTrace();
		}finally {
			super.cerrarCallable(cs);
			super.cerrarResultSet(rs);
			super.cerrarConexion(cnn);
		}
		return ws;
	}
	
	
	public WebServices updWebservices(WebServices ws)throws Exception{
		Connection cnn = null;
		CallableStatement cs = null;
		ResultSet rs = null;
		ConexionBD con = new ConexionBD();
		try {
			cnn = con.abrirConexionAtencion();
			cs = cnn.prepareCall("{call PKG_INFORMES.UPDATE_WEBSERVICES(?,?,?,?, ?,?,?,?) }");
			cs.setInt(1, ws.getId());
			cs.setInt(2, ws.getTipo());
			cs.setString(3, ws.getNombre());
			cs.setString(4, ws.getUrl());
			cs.setInt(5, ws.getTimeout());
			cs.setString(6, ws.getUsuario());
			cs.registerOutParameter(7, OracleTypes.NUMERIC);		
			cs.registerOutParameter(8, OracleTypes.VARCHAR);
			cs.executeQuery();
			ws.setRespuesta(cs.getInt(7));
			ws.setMensaje(cs.getString(8));
		}catch(NamingException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(SQLException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(Exception e){
			e.fillInStackTrace();
		}finally {
			super.cerrarCallable(cs);
			super.cerrarResultSet(rs);
			super.cerrarConexion(cnn);
		}
		return ws;
	}
	
	
	public WebServices delWebservices(WebServices ws)throws Exception{
		Connection cnn=null;
		CallableStatement cs=null;
		ResultSet rs=null;
		ConexionBD con = new ConexionBD();
		try {
			cnn = con.abrirConexionAtencion();
			cs = cnn.prepareCall("{call PKG_INFORMES.DEL_WEBSERVICES(?,?,?,?) }");
			cs.setInt(1, ws.getId());
			cs.setString(2, ws.getUsuario());
			cs.registerOutParameter(3, OracleTypes.NUMERIC);		
			cs.registerOutParameter(4, OracleTypes.VARCHAR);
			cs.executeQuery();	
			ws.setRespuesta(cs.getInt(3));
			ws.setMensaje(cs.getString(4));
		}catch(NamingException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(SQLException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(Exception e){
			e.fillInStackTrace();
		}finally {
			super.cerrarCallable(cs);
			super.cerrarResultSet(rs);
			super.cerrarConexion(cnn);
		}
		return ws;
	}

	
	public WebServices getWSContentManager()throws Exception {

		Connection cnn=null;
		CallableStatement cs=null;
		ResultSet rs=null;		
		ConexionBD con=new ConexionBD();
		WebServices ws=new WebServices();	
		try {
			cnn = con.abrirConexionAtencion();
			cs = cnn.prepareCall("{call PKG_CONTRALORIA_EPU.GET_PARAMETROS(?,?,?)}");
			cs.setString(1, "WS_EXT");
			cs.setString(2, "EXT_CM");
			
					
			cs.registerOutParameter(3, OracleTypes.CURSOR);
			cs.executeQuery();
			rs = (ResultSet) cs.getObject(3);
			while(rs.next()){
				ws.setUrl(rs.getString("PARAM_NOMBRE"));
				ws.setUsuario(rs.getString("PARAM_VALUE1"));
				ws.setPassword(rs.getString("PARAM_VALUE2"));
				ws.setItemTypeEntity(rs.getString("PARAM_VALUE3"));				
			}
		}catch(NamingException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(SQLException e){
			//this.setError(Excepciones.getError(e));
			e.fillInStackTrace();
		}catch(Exception e){
			e.fillInStackTrace();
		}finally {
			super.cerrarCallable(cs);
			super.cerrarResultSet(rs);
			super.cerrarConexion(cnn);
		}
		return ws;
	}

	
	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
	
}


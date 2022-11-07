package cl.contraloria.sicogen.utils;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class CerrarConexion {
	
	protected void cerrarConexion(Connection cn) throws CapturaExcepcion {
		try {
			if (cn != null)
				cn.close();
		} catch (Exception e) {
			throw new CapturaExcepcion("Ha ocurrido un error al cerrar la Conexion a la Base de Datos");		
			}		
	}
	
	protected void cerrarCallable(CallableStatement cs)  throws CapturaExcepcion{
		try {
			if (cs != null) 
				cs.close();
		} catch (Exception e) {
			throw new CapturaExcepcion("Ha ocurrido un error al cerrar la Conexion a la Base de Datos");
		}
	}
	protected void cerrarResultSet(ResultSet rs) throws CapturaExcepcion {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (Exception e) {
			throw new CapturaExcepcion("Ha ocurrido un error al cerrar la Conexion a la Base de Datos");
		}
	}

}

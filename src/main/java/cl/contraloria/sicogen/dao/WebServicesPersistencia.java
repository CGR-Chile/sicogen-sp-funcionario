package cl.contraloria.sicogen.dao;


import cl.contraloria.sicogen.model.WebServices;

public class WebServicesPersistencia {

private String error;
	
	public static WebServices getUrlWS(int informe, int periodo, int tipows) {
		WebServicesPersistencia wsDAO = new WebServicesPersistencia();
		WebServices ws = null;
		
		try {
			ws= wsDAO.getUrlWS(periodo, informe, tipows);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ws;
	}
	
	
	public static WebServices getUrlWSCarga(int idWS) {
		
		WebServicesDAO wsDAO = new WebServicesDAO();
		WebServices ws = null;
		
		try {
			ws= wsDAO.getUrlWSCarga(idWS);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ws;
	}
	
	
	public static WebServices getUrlWSValidacion(int idWS) {
		
		WebServicesDAO wsDAO = new WebServicesDAO();
		WebServices ws = null;
		
		try {
			ws= wsDAO.getUrlWSValidacion(idWS);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return ws;
	}
	
	public String getError() {
		return error;
	}
	
}

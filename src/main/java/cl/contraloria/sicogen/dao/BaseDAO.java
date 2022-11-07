package cl.contraloria.sicogen.dao;

import cl.contraloria.sicogen.exceptions.SicogenException;

import java.util.Map;

public class BaseDAO {
    private static final String COD = "pCOD";
    private static final String MESSAGE = "pMESSAGE";

    public void getResult(Map<String, Object> resultMap) {
        Integer codEjec = (Integer) resultMap.get(COD);

        if (codEjec != 0) {
            String msgError = (String) resultMap.get(MESSAGE);
            throw new SicogenException(codEjec, msgError);
        }
    }

    public void getResult(Map<String, Object> resultMap, String cod, String message) {
        Integer codEjec = (Integer) resultMap.get(cod);

        if (codEjec != 0) {
            String msgError = (String) resultMap.get(message);
            throw new SicogenException(codEjec, msgError);
        }
    }
}

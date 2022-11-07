package cl.contraloria.sicogen.service;

import cl.contraloria.sicogen.dao.PlanDeCuentasDAO;
import cl.contraloria.sicogen.model.PlanDeCuentas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanDeCuentasService {

    private PlanDeCuentasDAO planDeCuentasDAO;

    public PlanDeCuentasService(PlanDeCuentasDAO planDeCuentasDAO) {
        this.planDeCuentasDAO = planDeCuentasDAO;
    }

    public List<PlanDeCuentas> getCuentasPresupTDRII(String subTitulo, String item, String idEjercicio){
        return planDeCuentasDAO.getCuentasPresupTDRII(subTitulo, item, idEjercicio);
    }
}

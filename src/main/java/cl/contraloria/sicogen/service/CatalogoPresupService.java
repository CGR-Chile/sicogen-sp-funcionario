package cl.contraloria.sicogen.service;

import cl.contraloria.sicogen.dao.CatalogoPresupDAO;
import cl.contraloria.sicogen.model.ItemBO;
import cl.contraloria.sicogen.model.SubTituloBO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CatalogoPresupService {

    private CatalogoPresupDAO catalogoPresupDAO;

    public CatalogoPresupService(CatalogoPresupDAO catalogoPresupDAO) {
        this.catalogoPresupDAO = catalogoPresupDAO;
    }

    public List<SubTituloBO> getSubtitulosByEjericio(Integer idEjercicio) {
        return catalogoPresupDAO.getSubtitulosByEjericio(idEjercicio);
    }

    public List<ItemBO> getItemsBySubTitulo(Integer idEjercicio, Integer idSubtitulo) {
        return catalogoPresupDAO.getItemsBySubTitulo(idEjercicio, idSubtitulo);
    }
}

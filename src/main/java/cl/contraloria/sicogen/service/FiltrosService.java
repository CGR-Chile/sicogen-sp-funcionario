package cl.contraloria.sicogen.service;

import cl.contraloria.sicogen.dao.FiltrosDAO;
import cl.contraloria.sicogen.model.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiltrosService {

    private FiltrosDAO filtrosDAO;

    public FiltrosService(FiltrosDAO filtrosDAO) {
        this.filtrosDAO = filtrosDAO;
    }

    public List<EjerciciosDTO> getEjercicios() {
        return filtrosDAO.getEjercicios();
    }

    public List<EstadoSicogenDTO> getEstadosSicogen() {
        return filtrosDAO.getEstadosSicogen();
    }

    public List<CapituloBO> getCapituloByPartidaId(String codPartida, String idEjercicio) {
        return filtrosDAO.getCapituloByPartidaId(codPartida, idEjercicio);
    }

    public List<ProgramaBO> getProgramaByCapituloId(String idCapitulo, String idEjercicio) {
        return filtrosDAO.getProgramaByCapituloId(idCapitulo, idEjercicio);
    }

    public List<TiposDeInformes> getTiposDeInformes() {
        return filtrosDAO.getTiposDeInformes();
    }

    public List<Periodos> getPeriodos() {
        return filtrosDAO.getPeriodos();
    }

    public List<Region> getRegiones() {
        return filtrosDAO.getRegiones();
    }

    public List<Informes> getInformes() {
        return filtrosDAO.getInformes();
    }

    public List<Periodos> getPeriodosByEjercicio(Integer idEjercicio) {
        return filtrosDAO.getPeriodosByEjercicio(idEjercicio);
    }
}

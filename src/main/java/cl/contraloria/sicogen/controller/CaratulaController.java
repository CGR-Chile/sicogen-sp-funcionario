package cl.contraloria.sicogen.controller;


import cl.contraloria.sicogen.exceptions.SicogenException;
import cl.contraloria.sicogen.model.BitacoraSistradocDTO;
import cl.contraloria.sicogen.model.CaratulaDTO;
import cl.contraloria.sicogen.model.ResultadoEjecucion;
import cl.contraloria.sicogen.model.UsuarioDTO;
import cl.contraloria.sicogen.service.BitacoraSistradocService;
import cl.contraloria.sicogen.service.SistradocService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;


@Controller
@RequestMapping("/caratula")
public class CaratulaController extends HttpServlet {

    private SistradocService sistradocService;
    private BitacoraSistradocService bitacoraSistradocService;

    public CaratulaController(SistradocService sistradocService, BitacoraSistradocService bitacoraSistradocService) {
        this.sistradocService = sistradocService;
        this.bitacoraSistradocService=bitacoraSistradocService;

    }

    @GetMapping(value = "/listarCaratulas", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CaratulaDTO>> getCaratulaByFiltro(@RequestParam(required = false) String idDocumento,@RequestParam(required = false) String tipoInforme,@RequestParam(required = false) String tipoDocumento,@RequestParam(required = false) String ejercicio,@RequestParam(required = false) String nroDocumento, @RequestParam(required = false) String entidadEmisora,@RequestParam(required = false) String fechaDocumento ) {
        List<CaratulaDTO> informesPresupuestarios = null;
        try {
        informesPresupuestarios = sistradocService.getAllSistradoc(idDocumento,tipoInforme, tipoDocumento, ejercicio,nroDocumento, entidadEmisora,fechaDocumento );
        }catch (Exception ex) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(informesPresupuestarios, HttpStatus.OK);
    }

    @PostMapping(value = "/insertarCaratula", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> insertarCaratula(@RequestBody CaratulaDTO caratulaDTO, HttpServletRequest request) {
        ResultadoEjecucion result;
        int usuarioId = getUserName(request);
        caratulaDTO.setIdUsuario(usuarioId);
        HttpStatus http = HttpStatus.CREATED;
        try {
            caratulaDTO.setSecuenciaReingreso(0);
            result =  sistradocService.insertUpdCaratula(caratulaDTO,true);

        }
        catch (SicogenException ex) {
            result = new ResultadoEjecucion(ex.getCodError(), ex.getMensaje());
        }
        catch (Exception ex) {
            result = new ResultadoEjecucion("-1", ex.getLocalizedMessage());
            http = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return ResponseEntity.status(http).body(result);
    }

    @PostMapping (value = "/updateCaratula/{idDocumento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion>  updateCaratula(@PathVariable String idDocumento,@RequestBody CaratulaDTO caratulaDTO, HttpServletRequest request) {
        caratulaDTO.setIdDocumento(Integer.valueOf(idDocumento));
        int usuarioId = getUserName(request);
        caratulaDTO.setIdUsuario(usuarioId);
        ResultadoEjecucion result;
        HttpStatus http = HttpStatus.OK;
        try {
            result =  sistradocService.insertUpdCaratula(caratulaDTO,false);
        }
        catch (SicogenException ex) {
            result = new ResultadoEjecucion(ex.getCodError(), ex.getMensaje());
        }
        catch (Exception ex) {
            result = new ResultadoEjecucion("-1", ex.getLocalizedMessage());
            http = HttpStatus.OK;
        }
        return ResponseEntity.status(http).body(result);
    }

    @DeleteMapping (value = "/eliminarCaratula/{idDocumento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoEjecucion> eliminarCaratula(@PathVariable String idDocumento) {

        ResultadoEjecucion result = new ResultadoEjecucion();
               String resultado = sistradocService.eliminarCaratula(idDocumento) + " Caratula eliminada de la base de datos";
        result.setCodEjec(String.valueOf(0));
        result.setMsgEjec(resultado);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }
    private int getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        int usuario = 0;
        try {
            UsuarioDTO usuarioSesion = (UsuarioDTO) session.getAttribute("usr");
            usuario = usuarioSesion.getUserId();
        } catch(Exception e) {

        }
        return usuario;
    }
    @GetMapping (value = "/verBitacora/{idDocumento}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BitacoraSistradocDTO>> obtenerBitacora(@PathVariable String idDocumento, HttpServletRequest request) throws SQLException {
        List<BitacoraSistradocDTO>  bitacoraSistradocDTOS= bitacoraSistradocService.getBitacoraSistradoc(idDocumento) ;
        return ResponseEntity.status(HttpStatus.OK).body(bitacoraSistradocDTOS);
    }
}

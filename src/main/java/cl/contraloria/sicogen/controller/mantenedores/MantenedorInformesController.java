package cl.contraloria.sicogen.controller.mantenedores;

import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.FiltrosService;
import cl.contraloria.sicogen.service.InformesService;
import cl.contraloria.sicogen.service.MantenedoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/administracion/mantenedores")
public class MantenedorInformesController {

    private InformesService informesService;
    private MantenedoresService mantenedoresService;
    private FiltrosService filtrosService;

    public MantenedorInformesController(InformesService informesService,
                                        MantenedoresService mantenedoresService,
                                        FiltrosService filtrosService) {
        this.informesService = informesService;
        this.mantenedoresService = mantenedoresService;
        this.filtrosService = filtrosService;
    }

    @GetMapping(value = "/verMantenedorTblInformes")
    public String verMantenedorTblInformes(Model model) {
        List<TiposDeInformes> listTipoInformes = filtrosService.getTiposDeInformes();
        model.addAttribute("listTipoInformes", listTipoInformes);
        return "administracion/mantenedores/TblInforme";
    }

    @PostMapping(value = "/listTblInforme", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> listTblInforme(@RequestParam Integer subTipoInf,
                                                           @RequestParam Integer jtStartIndex,
                                                           @RequestParam Integer jtPageSize,
                                                           @RequestParam(required = false) String jtSorting) {
        int jtEndIndex = jtStartIndex + jtPageSize;

        List<JsonJTableExpenseBean> records = mantenedoresService.getTblInformes(subTipoInf);

        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }

        JsonResultJTable result = new JsonResultJTable("OK", records.subList(jtStartIndex, jtEndIndex), records.size());

        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);

    }

    @PostMapping(value = "/loadSubTipoInforme", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<SubTipoInforme>> loadSubTipoInforme(@RequestParam Integer tipoInfo){
        List<SubTipoInforme> listaSubTipoInf = mantenedoresService.loadMantenedorTblInforme(tipoInfo);
        return new ResponseEntity<List<SubTipoInforme>>(listaSubTipoInf, HttpStatus.OK);
    }

    @PostMapping(value = "/listCmbSubTipo", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> listCmbSubTipo() {
        List<OptionsJtable> subTipos = mantenedoresService.getSubTipos();
        JsonResultJTable result = new JsonResultJTable();
        result.setResult("OK");
        result.setOptions(subTipos);
        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/addTblInforme", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblInforme(@RequestParam Integer subTipoID,
                                                             @RequestParam String codigo,
                                                             @RequestParam String nombre,
                                                             @RequestParam String descripcion,
                                                             @RequestParam(required = false) String subTipoInf,
                                                             HttpServletRequest request) {
        JsonResultJTableAdd result = new JsonResultJTableAdd();

        try {
            HttpSession session = request.getSession();
            UsuarioDTO userSession = (UsuarioDTO) session.getAttribute("usr");
            JsonJTableExpenseBean resultCreate = mantenedoresService.addTblInforme(subTipoID, codigo, nombre, descripcion, userSession.getUserLogin());

            if (resultCreate.getId() < 0) {
                result.setResult("ERROR");
                result.setMessage(resultCreate.getNombre());
                return new ResponseEntity<JsonResultJTableAdd>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                result.setResult("OK");
                result.setMessage(resultCreate.getNombre());
                result.setRecord(resultCreate);
                return new ResponseEntity<JsonResultJTableAdd>(result, HttpStatus.OK);
            }
        } catch (Exception ex) {
            result.setResult("ERROR");
            result.setMessage(ex.getMessage());
            return new ResponseEntity<JsonResultJTableAdd>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/updTblInforme", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> updTblInforme(@RequestParam Integer id,
                                                          @RequestParam String codigo,
                                                          @RequestParam String nombre,
                                                          @RequestParam String descripcion,
                                                          @RequestParam(required = false) String subTipoInf,
                                                          HttpServletRequest request) {
        JsonResultJTable result = new JsonResultJTable();

        try {
            HttpSession session = request.getSession();
            UsuarioDTO userSession = (UsuarioDTO) session.getAttribute("usr");
            JsonJTableExpenseBean updateInforme = new JsonJTableExpenseBean();
            updateInforme.setId(id);
            updateInforme.setCodigo(codigo);
            updateInforme.setNombre(nombre);
            updateInforme.setDescripcion(descripcion);
            updateInforme.setUsuarioUpdate(userSession.getUserLogin());

            JsonJTableExpenseBean resultCreate = mantenedoresService.updateTblInforme(updateInforme);
            result.setMessage(resultCreate.getNombre());

            if (resultCreate.getId() < 0) {
                result.setResult("ERROR");
                return new ResponseEntity<JsonResultJTable>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                result.setResult("OK");
                return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
            }
        } catch (Exception ex) {
            result.setResult("ERROR");
            result.setMessage(ex.getMessage());
            return new ResponseEntity<JsonResultJTable>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(value = "/delTblInforme", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> delTblInforme(@RequestParam String id,
                                                          HttpServletRequest request) {
        JsonResultJTable result = new JsonResultJTable();

        try {
            HttpSession session = request.getSession();
            UsuarioDTO userSession = (UsuarioDTO) session.getAttribute("usr");

            JsonJTableExpenseBean resultCreate = mantenedoresService.delTblInforme(id, userSession.getUserId());
            result.setMessage(resultCreate.getNombre());

            if (resultCreate.getId() < 0) {
                result.setResult("ERROR");
                return new ResponseEntity<JsonResultJTable>(result, HttpStatus.INTERNAL_SERVER_ERROR);
            } else {
                result.setResult("OK");
                return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
            }
        } catch (Exception ex) {
            result.setResult("ERROR");
            result.setMessage(ex.getMessage());
            return new ResponseEntity<JsonResultJTable>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

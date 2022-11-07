package cl.contraloria.sicogen.controller.mantenedores;

import cl.contraloria.sicogen.exceptions.SicogenException;
import cl.contraloria.sicogen.model.*;
import cl.contraloria.sicogen.service.MantenedoresService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/administracion/mantenedores")
public class SaldoInicialController {

    private MantenedoresService mantenedoresService;
    private InputStream inputStream;
    private String nombreArchivo;
    public static final String SEPARATOR=";";
    private File	fileUpload;



    public SaldoInicialController(MantenedoresService mantenedoresService) {
        this.mantenedoresService = mantenedoresService;
    }

    private String getUserName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String usuario = "";
        try {
            UsuarioDTO usuarioSesion = (UsuarioDTO) session.getAttribute("usr");
            usuario = usuarioSesion.getUserLogin();
        } catch(Exception e) {

        }
        return usuario;
    }

    @GetMapping(value = "/verMantenedorTblSaldoInicial")
    public String verMantenedorTblSaldoInicial()
    {
        return ("administracion/mantenedores/TblSaldoInicial");
    }

    @PostMapping(value = "/listTblSaldoInicial", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTable> listTblSaldoInicial( @RequestParam Integer jtStartIndex,
                                                                 @RequestParam Integer jtPageSize,
                                                                 @RequestParam(required = false) String jtSorting) {
        String order;

        if (jtSorting == null) {
            order = "ID";
        } else {
            order = jtSorting;
        }

        int jtEndIndex = jtStartIndex + jtPageSize;

        List<JsonJTableExpenseBean> records = mantenedoresService.getSaldoInicialMant(order);

        if (records.size() < jtEndIndex) {
            jtEndIndex = records.size();
        }

        JsonResultJTable result = new JsonResultJTable("OK", records.subList(jtStartIndex, jtEndIndex), records.size());

        return new ResponseEntity<JsonResultJTable>(result, HttpStatus.OK);
    }

    @PostMapping(value = "/addTblSaldoInicial", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> addTblSaldoInicial(  @RequestParam Integer ejercicio,
                                                                    @RequestParam String moneda,
                                                                    @RequestParam String codPartida,
                                                                    @RequestParam String codCapitulo,
                                                                    @RequestParam String codPrograma,
                                                                    @RequestParam String cuentaAgregacion,
                                                                    @RequestParam String cuentaN1,
                                                                    @RequestParam String cuentaN2,
                                                                    @RequestParam String cuentaN3,
                                                                    @RequestParam String saldoDeudor,
                                                                    @RequestParam String saldoAcreedor,
                                                                    HttpServletRequest request) {
        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.setSaldoInicialMant(ejercicio,moneda,codPartida,codCapitulo,codPrograma, cuentaAgregacion,cuentaN1,cuentaN2,cuentaN3,saldoDeudor,saldoAcreedor,usuario);
            if(resp.getId() > 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/updTblSaldoInicial", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> updTblSaldoInicial(@RequestParam Integer id,
                                                                  @RequestParam Integer ejercicio,
                                                                  @RequestParam String moneda,
                                                                  @RequestParam String codPartida,
                                                                  @RequestParam String codCapitulo,
                                                                  @RequestParam String codPrograma,
                                                                  @RequestParam String cuentaAgregacion,
                                                                  @RequestParam String cuentaN1,
                                                                  @RequestParam String cuentaN2,
                                                                  @RequestParam String cuentaN3,
                                                                  @RequestParam String saldoDeudor,
                                                                  @RequestParam String saldoAcreedor,
                                                                      HttpServletRequest request) {
        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {
            JsonJTableExpenseBean resp = mantenedoresService.updSaldoInicialMant(id,ejercicio,moneda,codPartida,codCapitulo,codPrograma,cuentaAgregacion,cuentaN1,cuentaN2,cuentaN3,saldoDeudor,saldoAcreedor,usuario);
            if(resp.getId() == 0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @PostMapping(value = "/delTblSaldoInicial", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JsonResultJTableAdd> delTblSaldoInicial(@RequestParam Integer id,
                                                                      HttpServletRequest request) {

        String usuario = getUserName(request);

        JsonResultJTableAdd jsonAdd = new JsonResultJTableAdd();
        jsonAdd.setResult("OK");
        if(!usuario.equalsIgnoreCase("")) {

            JsonJTableExpenseBean resp = mantenedoresService.delSaldoInicialMant(id, usuario);
            if(Integer.valueOf(resp.getId()) ==  0) {
                jsonAdd.setRecord(resp);
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.OK);
            } else
                return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<JsonResultJTableAdd>(jsonAdd, HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/obtenerCSVErrores")
    public void obtenerCSVErrores(HttpServletResponse response) throws IOException {

        List<ErroresSaldoInicial> errores = mantenedoresService.getErroresSaldoInicialMant();

       StringBuilder out = new StringBuilder();

        out.append("EJERCICIO;");
        out.append("MONEDA;");
        out.append("PARTIDA;");
        out.append("CAPITULO;");
        out.append("PROGRAMA;");
        out.append("CUENTA;");
        out.append("SDEUDOR;");
        out.append("SACREEDOR;");
        out.append("ERROR"+"\n");

        if (errores!=null) {
            for (ErroresSaldoInicial esi : errores) {
                out.append(esi.getEjercicio() + ";");
                out.append(esi.getMoneda() + ";");
                out.append(esi.getPartida() + ";");
                out.append(esi.getCapitulo() + ";");
                out.append(esi.getPrograma() + ";");
                out.append(esi.getCuenta() + ";");
                out.append(esi.getSaldoDeudor() + ";");
                out.append(esi.getSaldoAcreedor() + ";");
                out.append(esi.getDescripcionError() + "\n");

            }
        }

        this.nombreArchivo= "SALDO_INICIAL_ERRORES.csv";

        response.setContentType("text/cvs");
        response.setHeader("Content-disposition", "attachment;filename="+ nombreArchivo);
        response.getOutputStream().print(out.toString());

        response.getOutputStream().flush();


    }

    @PostMapping(value = "/cargaExcelSaldosInicial", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResultadoCargaSaldoInicial> cargaExcelSaldosInicial(@RequestParam MultipartFile fileUpload,
                                                                                  HttpServletRequest request) {
        List<LogCarga> resultadoLogCarga;
        List<String> respuestaCargaSaldos = new ArrayList<String>();

        ResultadoCargaSaldoInicial rs = new ResultadoCargaSaldoInicial(new ArrayList<LogCarga> (),new ArrayList<String> ());

        try {

            if (!fileUpload.getOriginalFilename().isEmpty()) {
                if (fileUpload.getOriginalFilename().contains(".csv")) {
                    String usuario = getUserName(request);
                    BufferedReader br = null;
                    List<String[]> lista = new ArrayList<String[]>();
                    try {
                        String line=null;
                        InputStream is = fileUpload.getInputStream();
                        br = new BufferedReader(new InputStreamReader(is));

                        while ((line= br.readLine()) !=null) {
                            String[] fields = line.split(SEPARATOR);
                            lista.add(fields);
                        }
                    } catch (ArrayIndexOutOfBoundsException e) {
                        respuestaCargaSaldos.add("El archivo no tiene las 8 columnas definidas para la carga.");
                        rs.setErrorCargaSaldos(respuestaCargaSaldos);
                        return new ResponseEntity<ResultadoCargaSaldoInicial>(rs, HttpStatus.BAD_REQUEST);

                    } catch (Exception e) {
                        respuestaCargaSaldos.add("Ha ocurrido un error en la lectura del archivo.");
                        rs.setErrorCargaSaldos(respuestaCargaSaldos);
                        return new ResponseEntity<ResultadoCargaSaldoInicial>(rs, HttpStatus.BAD_REQUEST);
                    }

                    try {
                        mantenedoresService.deleteSaldosCero();

                        for (int j = 0; j < lista.size(); j++) {
                            mantenedoresService.insertarSaldosCero(lista.get(j)[0], lista.get(j)[1],
                                    lista.get(j)[2], lista.get(j)[3], lista.get(j)[4],
                                    lista.get(j)[5], lista.get(j)[6], lista.get(j)[7], usuario);
                        }

                        resultadoLogCarga = mantenedoresService.getLogCarga();

                    } catch (Exception e) {
                        respuestaCargaSaldos.add("Ha ocurrido un error en la carga del archivo.");
                        rs.setErrorCargaSaldos(respuestaCargaSaldos);
                        return new ResponseEntity<ResultadoCargaSaldoInicial>(rs,HttpStatus.OK);
                    }

                    rs.setResultadoLogCarga(resultadoLogCarga);
                    return new ResponseEntity<ResultadoCargaSaldoInicial>(rs, HttpStatus.OK);

                } else {
                    respuestaCargaSaldos.add("El archivo debe tener extensión .CSV");
                    rs.setErrorCargaSaldos(respuestaCargaSaldos);
                    return new ResponseEntity<ResultadoCargaSaldoInicial>(rs, HttpStatus.BAD_REQUEST);
                }
            } else {
                respuestaCargaSaldos.add("Favor seleccione un archivo");
                rs.setErrorCargaSaldos(respuestaCargaSaldos);
                return new ResponseEntity<ResultadoCargaSaldoInicial>(rs, HttpStatus.BAD_REQUEST);
            }


        } catch (SicogenException e) {
            respuestaCargaSaldos.add("Error inesperado al cargar archivo: " + e.getMensaje());
            rs.setErrorCargaSaldos(respuestaCargaSaldos);
            return new ResponseEntity<ResultadoCargaSaldoInicial>(rs, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            respuestaCargaSaldos.add("Error inesperado al cargar archivo: " + e.getLocalizedMessage());
            rs.setErrorCargaSaldos(respuestaCargaSaldos);
            return new ResponseEntity<ResultadoCargaSaldoInicial>(rs, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

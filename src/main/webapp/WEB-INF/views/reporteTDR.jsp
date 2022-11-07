<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">





<html>
<head>

    <script src="js/jquery/jquery-1.7.2.js"></script>
    <script src="http://code.jquery.com/jquery-latest.min.js" type="text/javascript"></script>

    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"></meta>
    <title>Reporte de Validacion TDR MP</title>
    <link rel="stylesheet" href="css/reportes/validacion/cssInformeBase.css" type="text/css"></link>
    <link href="css/mensaje/jquery.alerts.css" rel="stylesheet" type="text/css" ></link>
    <link rel="stylesheet" href="css/jquery-ui.css" ></link>

    <link href="css/reporteValidacion/reporteValidacionSP.css" rel="stylesheet" type="text/css"></link>

    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.10.2/jquery-ui.min.js"></script>

    <script src="js/jquery/jquery.base64.js"></script>
    <script src="js/jquery.fileDownload.js"></script>

    <script src="js/reportes/reporteDeValidacionTDRMP.js"></script>

    <script src="js/mensaje/jquery.alerts.js"></script>

    <script>
        $(window).load(function(){
            $('.loader').fadeOut(function(){  $('#getInforme').fadeIn(); });
        });
    </script>

</head>

<body>

<div class="loader" style="height:200px;left:35%; position: fixed !important;display:block;background-color: #fff;
			width:150px;height:300px;margin:15px auto 5px auto;padding: 50px 100px 20px;border: 2px solid;
			border-radius: 25px;-moz-border-radius: 25px; top: 3%;left: 30%;color: #333;z-index: 1002;overflow: auto;">
    <img src="images/loader.gif" ></img>
</div>
<div class="loader" style="opacity: 0;-moz-opacity: 0;filter: alpha(opacity=0);position: absolute;top: 0;left: 0;width: 100%;height: 100%;max-height: 110%;background:#000;z-index: 1001;"></div>

<form id="getInforme" name="getInforme" action="javascript:void(0);" method="post" style="display:none;">
    <table class="wwFormTable">


        <div id="reportePDF" >
            <table id="reporte" style="width:1000px;">
                <tr style="height: 120px;">
                    <td style="height:120px" colspan="6"><img src="images/sicogenii_logo.png" style="height:96px;width:322px;"></img></td>
                </tr>
                <tr></tr>
                <tr style="background-color: #d7d7d9;text-align:center;">
                    <td colspan="3" style="color:#454648; font:bold 20px Arial; padding:10px; width:934px;">REPORTE DE VALIDACIÓN</td>
                </tr>
                <tr style="height: 118px;">
                    <!-- <td style="width:100%"> -->
                    <td style="width:1000px;">
                        <!-- <table id="infGen" style="font: normal 12px Arial; border-collapse: collapse; width:100%;">   -->
                        <table id="infGen" style="font: normal 12px Arial; border-collapse: collapse; width:1000px;">
                            <thead style="text-align:left;">
                            <tr style="background-color:#464749;color:#fff;font-stretch:bold;">
                                <th colspan="2" style="padding: 2.5px 0 2.5px 5px;">Información General</th>
                            </tr>
                            </thead>
                            <tbody style="font: bold 10px sans-serif;">
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">ESTADO DE VALIDACIÓN</td>
                                <td style="background-color:#d7d7d9;width:260px;">CON ERRORES</td>
                            </tr>
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">TIPO DE INFORME</td>
                                <td style="background-color:#d7d7d9;width:260px;">Presupuestario Contable</td>
                            </tr>
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">INFORME</td>
                                <td style="background-color:#d7d7d9;width:260px;">TDR Modificaciones de presupuesto</td>
                            </tr>
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">PERÍODO</td>
                                <td style="background-color:#d7d7d9;width:260px;mso-number-format:'\@';">ENERO</td>
                            </tr>
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">EJERCICIO</td>
                                <td style="background-color:#d7d7d9;width:260px;mso-number-format:'\@'">2016</td>
                            </tr>
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">USUARIO</td>
                                <td style="background-color:#d7d7d9;width:260px;">usuario CGR</td>
                            </tr>
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">ENTIDAD</td>
                                <td style="background-color:#d7d7d9;width:260px;">SERVICIO NACIONAL DE PESCA Y ACUICULTURA</td>
                            </tr>
                            <tr style="height: 11px;">
                                <td style="background-color:#f8f8f8;width:95px;"></td>
                                <td style="background-color:#d7d7d9;width:260px;"></td>
                            </tr>
                            </tbody>
                        </table>
                        <input type="hidden" id="idFile" value="3287"></input>
                    </td>
                </tr>
                <tr></tr>

                <tr style="height: 118px;">
                    <!-- <td style="width:100%"> -->
                    <td style="width:1000px;">
                        <!-- <table id="infGen" style="font: normal 12px Arial; border-collapse: collapse; width:100%;">   -->
                        <table id="infGen" style="font: normal 12px Arial; border-collapse: collapse; width:1000px;">
                            <thead style="text-align:left;">
                            <tr style="background-color:#464749;color:#fff;font-stretch:bold;">
                                <th colspan="2" style="padding: 2.5px 0 2.5px 5px;">Información SISTRADOC</th>
                            </tr>
                            </thead>
                            <tbody style="font: bold 10px sans-serif;">
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">ANALISTA SISTRADOC</td>
                                <td style="background-color:#d7d7d9;width:260px;">Gustavo</td>
                            </tr>
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">ESTADO SICOGEN</td>
                                <td style="background-color:#d7d7d9;width:260px;">CON ERRORES</td>
                            </tr>
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">ENTIDAD EMISORA</td>
                                <td style="background-color:#d7d7d9;width:260px;">MINISTERIO DE HACIENDA</td>
                            </tr>
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">TIPO DOCUMENTO</td>
                                <td style="background-color:#d7d7d9;width:260px;">DECRETO</td>
                            </tr>
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">NRO. DOCUMENTO</td>
                                <td style="background-color:#d7d7d9;width:260px;">3000</td>
                            </tr>
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">FECHA DOCUMENTO</td>
                                <td style="background-color:#d7d7d9;width:260px;">01-02-2017</td>
                            </tr>
                            <tr>
                                <td style="background-color:#f8f8f8;width:95px;">FECHA RECEPCIÓN CGR</td>
                                <td style="background-color:#d7d7d9;width:260px;">01-02-2017</td>
                            </tr>
                            <tr style="height: 11px;">
                                <td style="background-color:#f8f8f8;width:95px;"></td>
                                <td style="background-color:#d7d7d9;width:260px;"></td>
                            </tr>
                            </tbody>
                        </table>
                        <input type="hidden" id="idSistradoc" value="209"></input>
                    </td>
                </tr>
                <tr></tr>

                <?xml version="1.0" encoding="UTF-8"?><table id="detfil" style="width:1000px;font:normal 12px Arial;border-collapse: collapse;" xmlns:tdrmp="http://www.contraloria.cl/Informes/SP/TDRModificacionPresupuestaria" xmlns:xs="http://www.w3.org/2001/XMLSchema"><thead><tr><th colspan="3" style="background-color:#0066b8;color:#fff;text-align:left;">Detalle de Archivo Cargado</th><th style="background-color:#0066b8;color:#fff;"/></tr></thead><tbody><br/><br/><table id="table1" style="background-color: #d7d7d9;"><tr><td id="td4"><strong>PARTIDA: </strong>09
                -
                MINISTERIO DE EDUCACIÓN</td></tr><tr><td id="td4"><strong>CAPITULO: </strong>01
                -
                SUBSECRETARÍA DE EDUCACIÓN</td></tr><tr><td id="td4"><strong>PROGRAMA: </strong>01
                -
                SUBSECRETARÍA DE EDUCACIÓN</td></tr><tr><td/></tr></table><br/><table id="table1" style="background-color: #d7d7d9;"><tr><td id="td1"/><td id="td2"/><td id="td3"><strong>MILES DE $ CLP</strong></td></tr></table><div style="width:1250px; overflow:auto;"><table class="TFtable primera-tabla"><tr id="cabeceraTabla"><th>SUBTITULO</th><th>ITEM</th><th>ASIGNACION</th><th>SUBASIGNACION</th><th/><th/><th>DENOMINACION</th><th>INCREMENTO</th><th>REDUCCION</th></tr><tr id="tablaDatos"><td style="text-align: center;">05</td><td style="text-align: center;">01</td><td style="text-align: center;"/><td style="text-align: center;"/><td style="text-align: center;">
                C
            </td><td style="text-align: center;"/><td style="text-align: left;">DEL SECTOR PRIVADO</td><td style="text-align: right;">20.000</td><td style="text-align: right;">0</td></tr><table id="table1" style="background-color: #d7d7d9;"><tr><td id="td1"><strong>C: Crease</strong></td><td id="td2"/><td id="td3"/></tr></table></table></div><br/><br/><table id="table1" style="background-color: #d7d7d9;"><tr><td id="td4"><strong>PARTIDA: </strong>04
                -
                CONTRALORÍA GENERAL DE LA REPÚBLICA</td></tr><tr><td id="td4"><strong>CAPITULO: </strong>01
                -
                CONTRALORÍA GENERAL DE LA REPÚBLICA</td></tr><tr><td id="td4"><strong>PROGRAMA: </strong>01
                -
                CONTRALORÍA GENERAL DE LA REPÚBLICA</td></tr><tr><td/></tr></table><br/><table id="table1" style="background-color: #d7d7d9;"><tr><td id="td1"/><td id="td2"/><td id="td3"><strong>MILES DE $ CLP</strong></td></tr></table><div style="width:1250px; overflow:auto;"><table class="TFtable primera-tabla"><tr id="cabeceraTabla"><th>SUBTITULO</th><th>ITEM</th><th>ASIGNACION</th><th>SUBASIGNACION</th><th/><th/><th>DENOMINACION</th><th>INCREMENTO</th><th>REDUCCION</th></tr><tr id="tablaDatos"><td style="text-align: center;">05</td><td style="text-align: center;">01</td><td style="text-align: center;"/><td style="text-align: center;"/><td style="text-align: center;">
                C
            </td><td style="text-align: center;"/><td style="text-align: left;">DEL SECTOR PRIVADO</td><td style="text-align: right;">10.000</td><td style="text-align: right;">0</td></tr><tr id="tablaDatos"><td style="text-align: center;">21</td><td style="text-align: center;"/><td style="text-align: center;"/><td style="text-align: center;"/><td style="text-align: center;">
                C
            </td><td style="text-align: center;"/><td style="text-align: left;">GASTOS EN PERSONAL</td><td style="text-align: right;">10.000</td><td style="text-align: right;">0</td></tr><tr id="tablaDatos"><td style="text-align: center;">21</td><td style="text-align: center;"/><td style="text-align: center;"/><td style="text-align: center;"/><td style="text-align: center;">
                C
            </td><td style="text-align: center;"/><td style="text-align: left;">GASTOS EN PERSONAL</td><td style="text-align: right;">0</td><td style="text-align: right;">5.000</td></tr><table id="table1" style="background-color: #d7d7d9;"><tr><td id="td1"><strong>C: Crease</strong></td><td id="td2"/><td id="td3"/></tr></table></table></div><br/><br/></tbody></table>

            </table>


            <div style="text-align:center;width:1000px">
                <div id="contBotones">
                    <hr class="separador" ></hr>
                    <button id="pdfTDRMP" class="boton">PDF</button>
                    <button id="excelTDRMP" class="boton">Excel</button>
                    <button id="cerrar" class="boton" onClick = "window.close()">Salir</button>
                </div>
                <div id="pie" class="Pauta_texto">
                    <span>Teatinos 56 , Santiago de Chile, Tel&eacutefono 56-2 24021100 -&nbsp;C&oacutedigo Postal: 8340521</span></br>
                    <span id="reqmin">Sitio web optimizado para ser visualizado en una resolución de pantalla de 1024 x 768 píxeles, en los navegadores iExplorer 10 o superior, Firefox 3.6 o superior y Chrome 8 o superior</span>
                </div>
            </div>

        </div>

    </table></form>




</body>

</html>

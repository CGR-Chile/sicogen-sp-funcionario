<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en">
<head>

    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
    <spring:url value="/resources/css/template.css" var="template"/>
    <spring:url value="/resources/css/rounded.css" var="rounded"/>
    <spring:url value="/resources/css/pestanas.css" var="pestanas"/>
    <spring:url value="/resources/css/SpryStyles/SpryTabbedPanels.css" var="SpryTabbedPanels"/>
    <spring:url value="/resources/css/PrincipalUser.css" var="PrincipalUser"/>

    <spring:url value="/resources/css/carrusel.css" var="carrusel"/>
    <spring:url value="/resources/css/linkCss.css" var="linkCss"/>
    <spring:url value="/resources/css/nyroModal.css" var="nyroModal"/>
    <spring:url value="/resources/css/jquery.cluetip.css" var="cluetip"/>
    <spring:url value="/resources/css/1.12.1/jquery-ui.css" var="ui"/>
    <spring:url value="/resources/css/tabla.css" var="tabla"/>
    <spring:url value="/resources/css/jquery-confirm.min.css" var="confirm"/>
    <spring:url value="/resources/img/" var="images"/>
    <spring:url value="/resources/css/system.css" var="system"/>

    <link type="text/css" href="${template}" rel="stylesheet"/>
    <link type="text/css" href="${rounded}" rel="stylesheet"/>
    <link type="text/css" href="${pestanas}" rel="stylesheet"/>
    <link type="text/css" href="${SpryTabbedPanels}" rel="stylesheet"/>
    <link type="text/css" href="${carrusel}" rel="stylesheet"/>
    <link type="text/css" href="${linkCss}" rel="stylesheet"/>
    <link type="text/css" href="${nyroModal}" rel="stylesheet"/>
    <link type="text/css" href="${cluetip}" rel="stylesheet"/>
    <link type="text/css" href="${ui}" rel="stylesheet"/>
    <link type="text/css" href="${alerts}" rel="stylesheet"/>
    <link type="text/css" href="${tabla}" rel="stylesheet"/>
    <link type="text/css" href="${PrincipalUser}" rel="stylesheet"/>
    <link type="text/css" href="${confirm}" rel="stylesheet"/>
    <link type="text/css" href="${system}" rel="stylesheet"/>


    <!--script type="text/javascript" src="/sicogen-mf/resources/js/carrusel/jquery.js"></script-->
    <!--script type="text/javascript" src="/sicogen-mf/resources/js/jquery-1.12.4.js"></script-->
    <script type="text/javascript" src="/sicogen-mf/resources/js/seguimiento/jquery-1.7.2.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/jquery.form.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/1.12.1/jquery-ui.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/filtrosPeriodos.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/envioInformes.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/reportes/reporteDeCuadratura.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/aSectorPublico/estadoInformeAnual.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/informeAnual.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/ValidacionesPendientes.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/selectorCarga/selectorCarga.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/popUpAmpliado.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/ajax.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/net.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/nu.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/functions.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/seguimiento.js"></script>
    <script type="text/javascript" src="/sicogen-mf/resources/js/jquery-confirm.min.js"></script>


    <script>

        function obtenerValidacionTDRNew(idFileIp) {

            var idInformeLP = $("#idInforme_form").text();
            var parametros = 'idFileUp=' + idFileIp;

            var action = 'obtenerValidacionTDRNew.action?' + parametros;
            console.log(action);
            window.open(action, '_blank', 'scrollbars=1,resizable=1,height=650,width=1050');
        }

        function liVerDescargaArchivo(idFileIp) {

            var parametros = 'idFileUp=' + idFileIp;
            dowFil = 'descargaInforme.action?' + parametros;
            location.href = dowFil;
        }

        function liVerBit(idFileIp) {
            var parametros = idFileIp;
            popUpBitacora(parametros);
            $("#dialogBitacora").dialog({}).dialog("open");
            $("#dialog").dialog("close");
            $("#dialog").css('width', '680px');
            $("#dialog").css('left', '300px');
            $("#dialogBitacora").parent().css('width', '680px');
            $("#dialogBitacora").parent().css('left', '25%');
            $("#dialogBitacora").parent().css('top', '10%');
            $("#dialogBitacora").parent().css('scroll', 'hidden');
            $("#ui-id-2").text($("#inf0" + informe).text());
            $(".ui-dialog-titlebar-close").css('background-color', '#F2F2F2');
            $("span.ui-dialog-title").text($('#inf' + informe).text().toUpperCase());
            return false;
        }

    </script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#contEstInfAnual').html('<p id="tdc" class="rwdetInfPar" align="center"> Cargando datos... </p>');

            //console.log( "listEstInfAnual.length: "+listEstInfAnual.length );
        });

    </script>
</head>

<body>
<form name="listadoCorreccion" id="listadoCorreccion" action="./informes/formularioCarga" method="post">
    <table class="adminlist" width="100%">
        <thead>
        <tr>
            <td colspan="15" class="colspan-padding">
                <strong>Ejercicio:</strong>
                <form:select id="cbEjercicio" path="ejercicios" class="date" onchange="cargaPartida(this.value);">
                    <form:options items="${ejercicios}" itemValue="ejercicioId" itemLabel="ejercicioNombre"/>
                </form:select>
                <strong>Partida:</strong>
                <form:select id="contPartida" path="listaPartida" onChange="cargaCapitulo(this.value);">
                    <form:options items="${listaPartida}" itemValue="codPartida" itemLabel="nombrePartida"/>
                </form:select>
                <strong>Cap&iacute;tulo:</strong>
                <form:select id="contCapitulo" path="listaCapitulo" onChange="busquedaListado(cbEjercicio.value, contTipoInformes.value);">
                    <form:options items="${listaCapitulo}" itemValue="codCapitulo" itemLabel="nombreCapitulo"/>
                </form:select>
                <strong>Tipo de Informe:</strong>
                <form:select id="contTipoInformes" path="tipoInforme" onchange="busquedaListado(cbEjercicio.value, contTipoInformes.value);">
                    <form:options items="${tipoInforme}" itemValue="codigo" itemLabel="nombre"/>
                </form:select>
            </td>
        </tr>

                <tr class="title-grid" id="periodoAbrev">
                    <td width="24%" class="title-grid">
                        <div align="center"><span class="Estilo11"><b>INFORMES</b></span></div>
                    </td>
                    <td width="5%" class="title-grid">
                        <div align="center"><span class="Estilo11"><b>ENE</b></span></div>
                    </td>
                    <td width="5%" class="title-grid">
                        <div align="center"><span class="Estilo11"><b>FEB</b></span></div>
                    </td>
                    <td width="5%" class="title-grid">
                        <div align="center"><span class="Estilo11"><b>MAR</b></span></div>
                    </td>
                    <td width="5%" class="title-grid">
                        <div align="center"><span class="Estilo11"><b>ABR</b></span></div>
                    </td>
                    <td width="5%" class="title-grid">
                        <div align="center"><span class="Estilo11"><b>MAY</b></span></div>
                    </td>
                    <td width="5%" class="title-grid">
                        <div align="center"><span class="Estilo11"><b>JUN</b></span></div>
                    </td>
                    <td width="5%" class="title-grid">
                        <div align="center"><span class="Estilo11"><b>JUL</b></span></div>
                    </td>
                    <td width="5%" class="title-grid">
                        <div align="center"><span class="Estilo11"><b>AGO</b></span></div>
                    </td>
                    <td width="5%" class="title-grid">
                        <div align="center"><span class="Estilo11"><b>SEP</b></span></div>
                    </td>
                    <td width="5%" class="title-grid">
                        <div align="center"><span class="Estilo11"><b>OCT</b></span></div>
                    </td>
                    <td width="5%" class="title-grid">
                        <div align="center"><span class="Estilo11"><b>NOV</b></span></div>
                    </td>
                    <td width="5%" class="title-grid">
                        <div align="center"><span class="Estilo11"><b>DIC</b></span></div>
                    </td>
                </tr>
        </thead>
        <tbody class="datosBusqueda"></tbody>
    </table>
    <c:choose>
        <c:when test="${hayComplementos>0}">
            <div id="titCorr" class="tituloInfColComp">COR</div>
        </c:when>
        <c:otherwise>
            <div id="titCorr" class="tituloInfColComp"></div>
        </c:otherwise>
    </c:choose>

    <table id="contEstInfAnual" class="adminlist" width="100%">
        <tbody>
        <c:forEach items="${listEstInfAnual}" var="item2">
            <div class="adminlist">
                <tr>
                    <div class="rowCenter">
                        <div id="inf0${item2.idInforme}" class="detalleInfCol01">${item2.informe}</div>
                    </div>

                    <td width="5%" class="adminlist">

                        <img src="${item2.imageEne}"
                             id="inf0${item2.idInforme}_p${item2.idEne}"
                             width="24px;" height="20px;" onclick="${item2.evEne}"
                             title="${item2.altEne}"></img>

                    </td>
                    <td width="5%" class="adminlist">
                        <img src="${item2.imageFeb}"
                             onclick="${item2.evFeb}"
                             id="inf0${item2.idInforme}_p${item2.idFeb}"
                             width="24px;" height="20px;"
                             style="margin:0 auto;"
                             title="${item2.altFeb}"></img>

                    </td>
                    <td width="5%" class="adminlist">

                        <img src="${item2.imageMar}"
                             onclick="${item2.evMar}"
                             id="inf0${item2.idInforme}_p${item2.idMar}"
                             width="24px;" height="20px;"
                             title="${item2.altMar}"></img>

                    </td>
                    <td width="5%" class="adminlist">

                        <img src="${item2.imageAbr}"
                             onclick="${item2.evAbr}"
                             id="inf0${item2.idInforme}_p${item2.idAbr}"
                             width="24px;" height="20px;"
                             title="${item2.altAbr}"></img>

                    </td>
                    <td width="5%" class="adminlist">

                        <img src="${item2.imageMay}"
                             onclick="${item2.evMay}"
                             id="inf0${item2.idInforme}_p${item2.idMay}"
                             width="24px;" height="20px;"
                             title="${item2.altMay}"></img>

                    </td>
                    <td width="5%" class="adminlist">

                        <img src="${item2.imageJun}"
                             onclick="${item2.evJun}"
                             id="inf0${item2.idInforme}_p${item2.idJun}"
                             width="24px;" height="20px;"
                             title="${item2.altJun}"></img>

                    </td>
                    <td width="5%" class="adminlist">
                        <img src="${item2.evJul}"
                             onclick="${item2.evJul}"
                             id="inf0${item2.idInforme}_p${item2.idJul}"
                             width="24px;" height="20px;"
                             title="${item2.altJul}"></img>

                    </td>
                    <td width="5%" class="adminlist">

                        <img src="${item2.imageAgo}"
                             onclick="${item2.evAgo}"
                             id="inf0${item2.idInforme}_p${item2.idAgo}"
                             width="24px;" height="20px;"
                             title="${item2.altAgo}"></img>

                    </td>
                    <td width="5%" class="adminlist">

                        <img src="${item2.imageSep}"
                             onclick="${item2.evSep}"
                             id="inf0${item2.idInforme}_p${item2.idSep}"
                             width="24px;" height="20px;"
                             title="${item2.altSep}"></img>

                    </td>
                    <td width="5%" class="adminlist">

                        <img src="${item2.imageOct}"
                             onclick="${item2.evOct}"
                             id="inf0${item2.idInforme}_p${item2.idOct}>"
                             width="24px;" height="20px;"
                             title="${item2.altOct}"></img>

                    </td>
                    <td width="5%" class="adminlist">

                        <img src="${item2.imageNov}"
                             onclick="${item2.evNov}"
                             id="inf0${item2.idInforme}_p${item2.idNov}"
                             width="24px;" height="20px;"
                             title="${item2.altNov}"></img>

                    </td>
                    <td width="5%" class="adminlist">
                        <div id="inf0${item2.idInforme}_Dic" class="detalleInfColPer">
                            <img src="${item2.imageDic}"
                                 onclick="${item2.evDic}"
                                 id="inf0${item2.idInforme}_p${item2.idDic}"
                                 width="24px;" height="20px;"
                                 title="${item2.altDic}"></img>
                        </div>
                    </td>
                </tr>
            </div>
            
        </c:forEach>
        </tbody>
    </table>

</form>


<div id="links-informe" title="">
    <p class="desc-informe"></p>
    <div style="margin-left: 10%;">
        <p>
        <p id="estadoInforme" style="display: inline;"></p> por
        <p id="usuarioInforme" style="display: inline;"></p> el día
        <p id="fechaModal" style="display: inline;"></p>.14

        <br>
        <b><p id="certificado" style="float:left;">0</p></b><br><br>
        <ul>
            <div id="contableInforme" style="display: none;">
                <li id="link-informe-contable"><a href="#" target="_blank" class="link-informe-contable">Ver Informe</a>
                </li>
            </div>
            <div id="validacionReporte" style="display: none;">
                <li id="link-reporte-val"><a href="#" target="_blank" class="link-reporte-val">Ver Reporte
                    Validación</a></li>
            </div>
            <div id="archivoDescarga" style="display: none;">
                <li id="link-descarga-archivo"><a href="#" target="_blank" class="link-descarga-archivo">Descargar
                    Archivo</a></li>
            </div>
            <div id="pdfArchivo" style="display: none;">
                <li id="link-descarga-archivoPDF" class="link-descarga-archivoPDF" style="cursor: pointer;">Descargar
                    Archivo PDF
                </li>
            </div>
            <div id="erroresResumen" style="display: none;">
                <li onclick="verErrores();" id="link-ver-resumenErrores" style="cursor: pointer;">Ver Resumen de
                    Errores
                </li>
            </div>
            <div id="cuadraturaReportes" style="display: none;">
                <li id="link-ver-reporteCuadraturas" style="cursor: pointer;">Reporte de Cuadratura</li>
            </div>
            <div id="envioCertificado" style="display: none;">
                <li id="link-certificado-envio" style="cursor: pointer;">Ver Certificado de Envío</li>
            </div>
            <div id="bitacoraVer" style="display: none;">
                <li id="link-ver-bitacora" style="cursor: pointer;">Ver Bitácora</li>
            </div>
        </ul>
        </p>
    </div>
</div>

<div class="Acceso" id="dialogBitacoraCert" title="Prop" style="height: 200px; width:600px; z-index:1800; display:none;"
     class="TextoNombre">
    <div id="" class="TextoPopupPrincipalCert"></div>
    <div style="width:660px;max-height:200px; margin:auto 0;overflow-y:scroll ;">
        <div style="float:left;margin:5px 0 0 15px;"></div>
        <div style="clear: both;display: block; margin: 10px 0 0 15px;">
            <div style="clear: both;width:620px;float:left;">
                <label id="estadoSendCGRError" style="display:inline;"> </label>
                <%-- 						 			<label id="userSendCGRError" style="display:inline;"><s:property value="inf.usuario" /></label> el día <label id="dateSendCGRError" style="display:inline;"><s:property value="inf.fecha" /> a las <s:property value="inf.hora" /></label> --%>
            </div>
        </div>
        <div style="float:left;margin:5px 0 0 15px;"></div>
        <div style=" height: 200px; clear: both;display: block; margin: 10px 0 10px 10px;">
            <div style="clear: both;" class="grillaInformes">
                <div style="clear: both;" class="rwEncInf">
                    <div style="width:99px;text-align:center" class="tituloInfColError">Estado</div>
                    <div style="width:99px;text-align:center" class="tituloInfColErrorFinal">Usuario</div>
                    <div style="width:189px;text-align:center" class="tituloInfColError">Fecha Procesamiento</div>
                    <div style="width:99px;text-align:center" class="tituloInfColErrorFinal">N° Envio</div>
                </div>
                <div class="contEstInfAnual" id="contBitacoraCert"></div>
            </div>
        </div>
    </div>
</div>

<div id="contIconografia" class="contIconografia">
    <div class="contIcono" style="clear:both;width:165px;">
        <img id="icoValidado" src="${images}/Validado.png" style="width:18px;height: 18px; float:left;margin-right:5px;" title="Informe Validado">
        <span style="float:left;">Validado</span>
    </div>
    <div class="contIcono" style="width:165px;padding:0 10px 0 0;">
        <img src="${images}/Procesado.png" id="icoProcesado" style="width:18px;height: 18px; float:left; margin-right:5px;" title="Informe Procesado">
        <span>Procesado</span>
    </div>
    <div class="contIcono" style="width:100px;">
        <img src="${images}/error.png" id="icoError" style="width:18px;height: 18px; float:left;margin-right:5px;" title="Informe Con Errores">
        <span>Error</span>
    </div>

    <div class="contIcono" style="clear:both;width:165px;">
        <img src="${images}/ValidadoOBS.png" id="icoProcesado" style="width:18px;height: 18px; float:left; margin-right:5px;" title="Validado con Observaciones">
        <span>Validado con observación</span>
    </div>
    <div class="contIcono" style="width:165px;padding:0 10px 0 0;">
        <img src="${images}/ProcesadoOBS.png" id="icoProcesadoObs" style="width:18px;height:18px;float:left;margin-right:5px;" title="Procesado con Observaciones">
        <span>Procesado con observación</span>
    </div>
    <div class="contIcono" style="width:165px;padding:0 10px 0 0;">
        <img src="${images}/icon_lupa.png" id="icoNotMov" style="width:18px;height:18px;float:left;margin-right:5px;" title="Modificaciones de Presupuesto">
        <span>Modificaciones de Presupuesto</span>
    </div>

    <div class="contIcono" style="clear:both;width:165px;">
        <img src="${images}loaderStatic.png" id="icoError" style="width:18px;height: 18px; float:left;margin-right:5px;" title="Validando Informe">
        <span>Validando</span>
    </div>

</div>
<input type="hidden" value="${codPartidaIni}" id="codPartidaIni"/>
<input type="hidden" value="${codCapituloIni}" id="codCapituloIni"/>
<input type="hidden" value="${ejercicioIni}" id="ejercicioIni"/>
<input type="hidden" value="${tipoInformeIni}" id="tipoInformeIni"/>
</body>
</html>
<script>
    cargaPartidaInicial();

</script>

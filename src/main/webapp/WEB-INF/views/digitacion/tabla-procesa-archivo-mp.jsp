<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-10-05
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:url value="/resources/img" var="images"/>
<spring:url value="/resources/js/" var="js"/>
<spring:url value="/resources/css" var="css"/>
<link type="text/css" href="${css}/custom-dialog.css" rel="stylesheet"/>
<script type="text/javascript" src="${js}/jquery.inputmask.min.js"></script>
<script type="text/javascript" src="${js}/jquery.numeric.js"></script>
<div class="py-3">
    <div class="container-fluid">
        <div class="card bg-secondary">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Tipo Informe</label>
                            <input type="text" class="form-control" value="${tipoInforme}" readonly/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Informe</label>
                            <input type="text" class="form-control" value="${informe}" readonly/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Estado SICOGEN</label>
                            <input type="text" class="form-control" value="${estadoSicogen}" readonly/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Ejercicio</label>
                            <input type="text" class="form-control" value="${ejercicio}" readonly/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Entidad Emisora</label>
                            <input type="text" class="form-control" value="${entidadEmisora}" readonly/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Tipo Documento</label>
                            <input type="text" class="form-control" value="${tipoDocumento}" readonly id="textTipDoc"/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Nro. Documento</label>
                            <input type="text" class="form-control" value="${nroDocumento}" readonly id="textNumDoc"/>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Fecha Documento</label>
                            <input type="text" class="form-control" value="${fechaDocumento}" readonly id="textFecDoc"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Fecha Recep. CGR</label>
                            <input type="text" class="form-control" value="${fechaRecepcionCGR}" readonly/>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="py-3">
    <div class="container-fluid">
        <div class="card bg-secondary">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Partida</label>
                            <select class="form-control" id="selectPartida">
                                <option value="-1">Selec. Partida</option>
                                <c:forEach var="partida" items="${partidas}">
                                    <option value="${partida.nombre}">${partida.nombre} - ${partida.codigo}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Capítulo</label>
                            <select class="form-control" id="selectCapitulo">
                                <option value="-1">Selec. Capitulo</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-3">
                        <div class="form-group">
                            <label>Programa</label>
                            <select class="form-control" id="selectPrograma">
                                <option value="-1">Selec. Programa</option>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-1 align-self-center mt-2">
                        <button type="button" class="btn btn-primary" id="btnAgregar">Agregar</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="py-3">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12" id="divTablaDigitacion">
                <table class="adminlist" id="tblDigitacion">
                    <thead>
                    <tr>
                        <td colspan="9" class="title-table"><span class="Estilo11"><b>Digitación de Presupuesto</b></span></td>
                    </tr>
                    <tr class="title-grid">
                        <td class="title-grid" width="18%"><div align="center"><span class="Estilo11"><b>Partida</b></span></div></td>
                        <td class="title-grid" width="18%"><div align="center"><span class="Estilo11"><b>Capitulo</b></span></div></td>
                        <td class="title-grid" width="18%"><div align="center"><span class="Estilo11"><b>Programa</b></span></div></td>
                        <td class="title-grid" width="9%"><div align="center"><span class="Estilo11"><b>Total Ingresos CLP</b></span></div></td>
                        <td class="title-grid" width="9%"><div align="center"><span class="Estilo11"><b>Total Gastos CLP</b></span></div></td>
                        <td class="title-grid" width="9%"><div align="center"><span class="Estilo11"><b>Total Ingresos USD</b></span></div></td>
                        <td class="title-grid" width="9%"><div align="center"><span class="Estilo11"><b>Total Gastos USD</b></span></div></td>
                        <td class="title-grid" width="5%"><div align="center"><span class="Estilo11"><b>Eliminar</b></span></div></td>
                        <td class="title-grid" width="5%"><div align="center"><span class="Estilo11"><b>Editar</b></span></div></td>
                    </tr>
                    </thead>
                    <tbody>
                        <c:if test="${!empty listaDigitacion}">
                            <c:forEach var="digitacion" items="${listaDigitacion}" varStatus="status">
                                <c:choose>
                                    <c:when test="${status.index % 2 == 0}">
                                        <c:set var="rowClass" value="row0"/>
                                    </c:when>
                                    <c:otherwise>
                                        <c:set var="rowClass" value="row1"/>
                                    </c:otherwise>
                                </c:choose>
                                <tr class="<c:out value="${rowClass}"/>">
                                    <td>${digitacion.idPartida}</td>
                                    <td>${digitacion.idCapitulo}</td>
                                    <td>${digitacion.idPrograma}</td>
                                    <td style="text-align: center;">${digitacion.totalIngresosCLP}</td>
                                    <td style="text-align: center;">${digitacion.totalGastosCLP}</td>
                                    <td style="text-align: center;">${digitacion.totalIngresosUSD}</td>
                                    <td style="text-align: center;">${digitacion.totalGastosUSD}</td>
                                    <td style="text-align: center;"><img src="${images}/delete.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="eliminarFilaDigitacionTDRMP(${digitacion.idDigitacionMP})"/></td>
                                    <td style="text-align: center;"><img src="${images}/edit.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="verEditarMP(${digitacion.idDigitacionMP}, ${digitacion.totalIngresosCLP}, ${digitacion.totalGastosCLP}, ${digitacion.totalIngresosUSD}, ${digitacion.totalGastosUSD}, '${digitacion.codPartida}', '${digitacion.idPartida}', '${digitacion.codCapitulo}', '${digitacion.idCapitulo}', '${digitacion.codPrograma}', '${digitacion.idPrograma}', ${digitacion.idPrograma2})"/></td>
                                </tr>
                            </c:forEach>
                        </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<div class="py-3">
    <div class="container-fluid d-flex justify-content-center">
        <div class="row">
            <div class="col-md-6">
                <button type="button" class="btn btn-primary" id="btnValidarInforme">Validar</button>
            </div>
            <div class="col-md-6">
                <button type="button" class="btn btn-primary" id="btnGuardarInforme">Guardar</button>
            </div>
        </div>
    </div>
</div>

<div class="py-3">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12 d-flex justify-content-end">
                <button type="button" class="btn btn-primary" onclick="volverBusquedaTDR();">Volver</button>
            </div>
        </div>
    </div>
</div>
<div id="dialogAlerta" title="Alerta" class="custom-with-dialog">
</div>
<div id="dialogConfirmacion" title="Alerta">
    <p>&iquest;Est&aacute; seguro que desea eliminar el registro?</p>
</div>
<input type="hidden" id="idFilaToDel">
<div id="dialogDigitacion" title="Digitación">
    <div class="py-5">
        <div class="container-fluid">
            <div class="row">
                <div class="col-md-3">
                    <label>Partida</label>
                </div>
                <div class="col-md-8">
                    <label id="lblPartida"/>
                </div>
                <div class="col-md-1">
                    <img src="${images}/doc.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="getGlosasPartida()"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <label>Capitulo</label>
                </div>
                <div class="col-md-8">
                    <label id="lblCapitulo"/>
                </div>
                <div class="col-md-1">
                    <img src="${images}/doc.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="getGlosasCapitulo()"/>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <label>Programa</label>
                </div>
                <div class="col-md-8">
                    <label id="lblPrograma"/>
                </div>
                <div class="col-md-1">
                    <img src="${images}/doc.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="getGlosasPrograma()"/>
                </div>
            </div>
            <div id="divDigitacionCLP">
                <div class="row">
                    <div class="col-md-3">
                        <label>Total Ingresos CLP</label>
                    </div>
                    <div class="col-md-3">
                        <label id="lblTotIngrCLP"/>
                    </div>
                    <div class="col-md-3">
                        <label>Tipo Moneda</label>
                    </div>
                    <div class="col-md-3">
                        <button type="button" class="btn btn-primary" id="btnShowUSD">CLP</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label>Total Gastos CLP</label>
                    </div>
                    <div class="col-md-3">
                        <label id="lblTotGastCLP"/>
                    </div>
                    <div class="col-md-3">
                        <label>Opcion</label>
                    </div>
                    <div class="col-md-3">
                        <div class="form-check">
                            <input class="form-check-input checkClP" type="radio" name="checkOpcionCLP" id="checkOpcionAumCLP" value="0" checked>
                            <label class="form-check-label ml-5" for="checkOpcionAumCLP"> Aumento </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input checkClP" type="radio" name="checkOpcionCLP" id="checkOpcionDisCLP" value="1">
                            <label class="form-check-label ml-5" for="checkOpcionDisCLP"> Disminucion </label>
                        </div>
                    </div>
                </div>
            </div>
            <div id="divDigitacionUSD">
                <div class="row">
                    <div class="col-md-3">
                        <label>Total Ingresos USD</label>
                    </div>
                    <div class="col-md-3">
                        <label id="lblTotIngrUSD"/>
                    </div>
                    <div class="col-md-3">
                        <label>Tipo Moneda</label>
                    </div>
                    <div class="col-md-3">
                        <button type="button" class="btn btn-primary" id="btnShowCLP">USD</button>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3">
                        <label>Total Gastos USD</label>
                    </div>
                    <div class="col-md-3">
                        <label id="lblTotGastUSD"/>
                    </div>
                    <div class="col-md-3">
                        <label>Opcion</label>
                    </div>
                    <div class="col-md-3">
                        <div class="form-check">
                            <input class="form-check-input checkUSD" type="radio" name="checkOpcionUSD" id="checkOpcionAumUSD" value="0" checked>
                            <label class="form-check-label ml-5" for="checkOpcionAumUSD"> Aumento </label>
                        </div>
                        <div class="form-check">
                            <input class="form-check-input checkUSD" type="radio" name="checkOpcionUSD" id="checkOpcionDisUSD" value="1">
                            <label class="form-check-label ml-5" for="checkOpcionDisUSD"> Disminucion </label>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-3">
                    <div class="form-group">
                        <label>Codigo cuenta</label>
                        <input type="text" class="form-control" id="inptCodCuenta">
                    </div>
                </div>
                <div class="col-md-5">
                    <div class="form-group">
                        <label>Concepto</label>
                        <input type="text" class="form-control" readonly="" id="txtConceptoCuenta">
                    </div>
                </div>
                <div class="col-md-2">
                    <div class="form-group">
                        <label>Monto</label>
                        <input type="text" class="form-control" id="inptMontoCuenta">
                    </div>
                </div>
                <div class="col-md-2">
                    <button type="button" class="btn btn-primary mt-4" id="btnAgregarCuenta">+ Agregar</button>
                </div>
            </div>
            <div class="row">
                <div class="col-md-12" id="divTablaDetTdrMp">
                </div>
            </div>
        </div>
    </div>
</div>
<div id="dialogAddCuenta" title="Agregar Cuenta">
    <div class="py-5">
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <div class="form-group">
                        <label>Codigo cuenta</label>
                        <input type="text" class="form-control" id="inptAddCodCuenta" readonly>
                    </div>
                </div>
                <div class="col-md-8">
                    <div class="form-group">
                        <label>Concepto</label>
                        <input type="text" class="form-control" id="txtAddConceptoCuenta">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="dialogGlosas" title="Glosa"></div>
<div id="dialogInfo" title="Información">
    <p>Digitación guardada con éxito.</p>
</div>
<div id="dialogValidacionInf" title="Información">
</div>
<input type="hidden" id="valIdFilaEdit">
<input type="hidden" id="valIdProgEdit">
<input type="hidden" id="valCodPartida">
<input type="hidden" id="valCodCapitulo">
<input type="hidden" id="valCodPrograma">
<input type="hidden" id="valIdCtaPresup">
<input type="hidden" id="valMoneda">
<input type="hidden" id="valIdCuenEnt">
<input type="hidden" id="valIdPeriodo" value="${idPeriodo}">
<input type="hidden" id="valIdSistradoc" value="${idSistradoc}">
<input type="hidden" id="valEjercicio" value="${ejercicio}">
<input type="hidden" id="valIdDetTDRMP">
<input type="hidden" id="valCodPartidaGlo">
<input type="hidden" id="valCodCapituloGlo">
<input type="hidden" id="valCodProgramaGlo">
<input type="hidden" id="valIndPresupGlo">
<input type="hidden" id="valIdFileUpload">
<input type="hidden" id="valNomPartidaGlo">
<input type="hidden" id="valNomCapituloGlo">
<input type="hidden" id="valNomProgramaGlo">
<input type="hidden" id="valNomPartida">
<input type="hidden" id="valNomCapitulo">
<input type="hidden" id="valNomPrograma">
<script>

    var dialogInfo;
    var dialogValidacionInf;

    $(document).ready(function(){

        dialogValidacionInf = $('#dialogValidacionInf').dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Aceptar: function() {
                    var idFileUpload = $('#valIdFileUpload').val();
                    var idSistradoc = $('#valIdSistradoc').val();
                    var url = '../validacion/obtenerValidacionTDR' +
                        '?idFileUp=' + idFileUpload +
                        '&idSistradoc=' + idSistradoc;
                    $( this ).dialog( "close" );

                    window.open(url,'_blank','scrollbars=1,resizable=1,height=650,width=1050');
                },
                Cancelar: function () {
                    $( this ).dialog( "close" );
                }
            }
        })

        $('#btnShowCLP').click(function () {
            $('#valMoneda').val('CLP');
            $('#divDigitacionUSD').hide();
            $('#divDigitacionCLP').show();
        });

        $('#btnShowUSD').click(function () {
            $('#valMoneda').val('USD');
            $('#divDigitacionUSD').show();
            $('#divDigitacionCLP').hide();
        });

        function agregaCuentaTDR() {
            var codCuenta = $('#inptCodCuenta').val();
            var monto = $('#inptMontoCuenta').val();

            if (!codCuenta || codCuenta.length === 0) {
                dialogAlerta.html('<p>Debe ingresar el numero de cuenta</p>');
                dialogAlerta.dialog('open');
                dialogAlerta.dialog('option', 'width', 320);
            } else if (!monto || monto.length === 0) {
                dialogAlerta.html('<p>Falta agregar el monto</p>');
                dialogAlerta.dialog('open');
                dialogAlerta.dialog('option', 'width', 320);
            } else {
                var moneda = $('#valMoneda').val();
                var checkAumDis = '';
                var idTDRMP = $('#valIdFilaEdit').val();
                var idCuenEnt = $('#valIdCuenEnt').val();

                if (moneda === 'CLP') {
                    checkAumDis = $('.checkClP[name="checkOpcionCLP"]:checked').val();
                } else {
                    checkAumDis = $('.checkUSD[name="checkOpcionUSD"]:checked').val();
                }

                var url = '../digitacion/insertarTDRMPAumentoDisminucion' +
                    '?idTDRMP=' + idTDRMP +
                    '&idCuenEnt=' + idCuenEnt +
                    '&checkOpcion=' + checkAumDis +
                    '&desMoneda=' + moneda +
                    '&monto=' + monto;

                $.get(url, function (data) {
                    if (data.codEjec !== "0") {
                        dialogAlerta.html('<p>' + data.msgEjec + '</p>');
                        dialogAlerta.dialog('open');
                        dialogAlerta.dialog('option', 'width', 320);
                    } else {
                        $('#inptCodCuenta').val('');
                        $('#txtConceptoCuenta').val('');
                        $('#inptMontoCuenta').val('');

                        var idTDRMP = $('#valIdFilaEdit').val();
                        var url2 = '../digitacion/buscarDetalleMPDigitacionTDR?idTDRMP=' + idTDRMP;

                        $.get(url2, function (data2) {
                            $('#divTablaDetTdrMp').html(data2);
                        })
                    }
                });
            }
        }

        $('#dialogDigitacion').keypress(function(e) {
            if (e.keyCode == $.ui.keyCode.ENTER) {
                agregaCuentaTDR();
            }
        });

        $('#inptMontoCuenta').numeric({ negative: false });

        $('#btnAgregarCuenta').click(agregaCuentaTDR);

        $('#inptCodCuenta').inputmask('99.99.999.999');

        $('#inptCodCuenta').change(function () {
            if ($('#inptCodCuenta').inputmask('isComplete')) {
                var cuentaCompleta = $(this).val();
                var idPrograma = $('#valIdProgEdit').val();
                var url = '../digitacion/buscarCuentaPIDigitacionTDRDetalle?codCuentaCompleta=' + cuentaCompleta + '&idPrograma=' + idPrograma;

                $.get(url, function (data) {
                    if (data.flagHabilitado === 1) {
                        $('#txtConceptoCuenta').val(data.nombreCuenta);
                        $('#valIdCuenEnt').val(data.idCuenEnt);
                    } else {
                        $('#inptAddCodCuenta').val($('#inptCodCuenta').val());
                        if (data.codEjec === 0) {
                            $("#valIdCtaPresup").val(data.idCuenEnt);
                            $("#txtAddConceptoCuenta").val(data.nombreCuenta);
                            $("#txtAddConceptoCuenta").prop("readonly", true);
                        } else {
                            $("#txtAddConceptoCuenta").prop("readonly", false);
                            $("#txtAddConceptoCuenta").val('');
                            $("#valIdCtaPresup").val(0);
                        }
                        $( "#dialogAddCuenta" ).dialog('open');
                        $( "#dialogAddCuenta" ).dialog('option', 'width', 700);
                    }
                });
            } else {
                dialogAlerta.html('<p>Cuenta ingresada incompleta</p>');
                dialogAlerta.dialog('open');
                dialogAlerta.dialog('option', 'width', 320);
            }

        })

        $( "#dialogDigitacion" ).dialog({
            autoOpen: false,
            modal: true,
            width: 1000,
            minWidth: 1000,
            buttons: {
                Aceptar: function() {
                    $('#inptCodCuenta').val('');
                    $('#txtConceptoCuenta').val('');
                    $( this ).dialog( "close" );
                }
            },
            close: function( event, ui ) {
                var numDoc = $('#textNumDoc').val();
                var tipoDocumento = $('#textTipDoc').val();
                var url = '../digitacion/getGrillaMPDigitacionTDR?numDoc=' + numDoc + '&tipoDoc=' + tipoDocumento;
                $.get(url, function (data) {
                    $('#divTablaDigitacion').html(data);
                })
            }
        });


        var dialogAlerta = $( "#dialogAlerta" ).dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Aceptar: function() {
                    $( this ).dialog( "close" );
                }
            }
        });

        dialogInfo = $( "#dialogInfo" ).dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Aceptar: function() {
                    $( this ).dialog( "close" );
                    volverBusquedaTDR();
                }
            },
            close: function( event, ui ) {
                volverBusquedaTDR();
            }
        });

        function createCuentaParticularEntidad() {
            var conceptoCuenta = $('#txtAddConceptoCuenta').val();

            if (!conceptoCuenta || conceptoCuenta.trim().length === 0) {
                dialogAlerta.html('<p>Ingrese un concepto para crear la cuenta</p>');
                dialogAlerta.dialog('open');
                dialogAlerta.dialog('option', 'width', 320);
            } else {
                var codPartida = $('#valCodPartida').val();
                var codCapitulo = $('#valCodCapitulo').val();
                var codPrograma = $('#valCodPrograma').val();
                var codCuenta = $('#inptAddCodCuenta').val();
                var idCuentaGen = $('#valIdCtaPresup').val();

                var url = '../digitacion/createCuentaParticularEntidad' +
                    '?codPartida=' + codPartida +
                    '&codCapitulo=' + codCapitulo +
                    '&codPrograma=' + codPrograma +
                    '&codCuenta=' + codCuenta +
                    '&nomCuenta=' + conceptoCuenta +
                    '&idCuentaGen=' + idCuentaGen;

                $.get(url, function (data) {
                    if (data.codEjec !== "0") {
                        $('#inptCodCuenta').val('');
                        $('#txtConceptoCuenta').val('');
                        dialogAlerta.html('<p>' + data.msgEjec + '</p>');
                        dialogAlerta.dialog('open');
                        dialogAlerta.dialog('option', 'width', 320);
                    } else {
                        $('#txtConceptoCuenta').val(conceptoCuenta);
                        $('#valIdCuenEnt').val(data.newId);
                    }
                });

                dialogAddCuenta.dialog( "close" );
            }

            return true;
        }

        var dialogAddCuenta = $("#dialogAddCuenta").dialog({
            autoOpen: false,
            modal: true,
            width: 700,
            minWidth: 700,
            buttons: {
                Aceptar: createCuentaParticularEntidad,
                Cancelar: function() {
                    $('#txtConceptoCuenta').val('');
                    $('#inptCodCuenta').val('');
                    $( this ).dialog( "close" );
                }
            }
        });

        $('#dialogGlosas').dialog({
            autoOpen: false,
            modal: true,
            width: 1024,
            minWidth: 1024,
            buttons: {
                Aceptar: function() {
                    $( this ).dialog( "close" );
                }
            }
        });

        $( "#dialogConfirmacion" ).dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Cancelar: function() {
                    $( this ).dialog( "close" );
                },
                Aceptar: function() {
                    var idFila = $('#idFilaToDel').val();
                    var url = '../digitacion/deleteMPDigitacionTDR?idFila=' + idFila;
                    $.get(url, function(data){
                        if (data.codEjec === '0') {
                            var numDoc = $('#textNumDoc').val();
                            var tipoDocumento = $('#textTipDoc').val();
                            var url2 = '../digitacion/getGrillaMPDigitacionTDR?numDoc=' + numDoc + '&tipoDoc=' + tipoDocumento;
                            $.get(url2, function (data) {
                                $('#divTablaDigitacion').html(data);
                            })
                        } else {
                            dialogAlerta.html('<p>' + data.msgEjec + '</p>');
                            dialogAlerta.dialog('open');
                            dialogAlerta.dialog('option', 'width', 320);
                        }
                    });
                    $( this ).dialog( "close" );
                }
            }
        });

        $('#selectPartida').change(function(){
            var codPartida = this.value;

            if (codPartida !== "-1") {
                var url = '../digitacion/getCapitulosDigitacionTDRMP?codPartida=' + codPartida
                $.get(url, function (data) {
                    $('#selectCapitulo').empty();
                    $('#selectCapitulo').append('<option value="-1">Selec. Capitulo</option>');
                    $('#selectPrograma').empty();
                    $('#selectPrograma').append('<option value="-1">Selec. Programa</option>');

                    for (var i = 0; i < data.length; i++) {
                        var capDat = data[i];
                        $('#selectCapitulo').append('<option value="' +  capDat.codCapitulo + '">' +  capDat.codCapitulo + ' - ' + capDat.nombre + '</option>');
                    }
                });
            } else {
                $('#selectCapitulo').empty();
                $('#selectCapitulo').append('<option value="-1">Selec. Capitulo</option>');
                $('#selectPrograma').empty();
                $('#selectPrograma').append('<option value="-1">Selec. Programa</option>');
            }
        });

        $('#selectCapitulo').change(function(){
            var codPartida = $('#selectPartida option:selected').val();
            var codCapitulo = this.value;

            if (codCapitulo !== "-1") {
                var url = '../digitacion/getProgramasDigitacionTDRMP?codPartida=' + codPartida + '&codCapitulo=' + codCapitulo;
                $.get(url, function (data) {
                    $('#selectPrograma').empty();
                    $('#selectPrograma').append('<option value="-1">Selec. Programa</option>');

                    for (var i = 0; i < data.length; i++) {
                        var progDat = data[i];
                        $('#selectPrograma').append('<option value="' +  progDat.idPrograma + '">' +  progDat.codPrograma + ' - ' + progDat.nombre + '</option>');
                    }
                });
            } else {
                $('#selectPrograma').empty();
                $('#selectPrograma').append('<option value="-1">Selec. Programa</option>');
            }
        });

        $('#btnAgregar').click(function () {
            var codPartida = $('#selectPartida option:selected').val();
            var codCapitulo = $('#selectCapitulo option:selected').val();
            var idPrograma = $('#selectPrograma option:selected').val();

            if (codPartida === '-1') {
                dialogAlerta.html('<p>Debe Seleccionar la Partida para Agregar</p>');
                dialogAlerta.dialog('open');
                dialogAlerta.dialog('option', 'width', 320);
            } else if (codCapitulo === '-1'){
                dialogAlerta.html('<p>Debe Seleccionar el Capítulo para Agregar</p>');
                dialogAlerta.dialog('open');
                dialogAlerta.dialog('option', 'width', 320);
            } else if (idPrograma === '-1'){
                dialogAlerta.html('<p>Debe Seleccionar el Programa para Agregar</p>');
                dialogAlerta.dialog('open');
                dialogAlerta.dialog('option', 'width', 320);
            } else {
                var numDoc = $('#textNumDoc').val();
                var tipoDocumento = $('#textTipDoc').val();
                var url = '../digitacion/insertMPDigitacionTDR' +
                    '?codPartida=' + codPartida +
                    '&codCapitulo=' + codCapitulo +
                    '&idPrograma=' + idPrograma +
                    '&numDoc=' + numDoc +
                    '&tipoDoc=' + tipoDocumento;

                $.get(url, function (data) {
                    if (data.codEjec === '0') {
                        var url2 = '../digitacion/getGrillaMPDigitacionTDR?numDoc=' + numDoc + '&tipoDoc=' + tipoDocumento;
                        $.get(url2, function (data) {
                            $('#divTablaDigitacion').html(data);
                        })
                    } else {
                        dialogAlerta.html('<p>' + data.msgEjec + '</p>');
                        dialogAlerta.dialog('open');
                        dialogAlerta.dialog('option', 'width', 320);
                    }
                });
            }
        });

        $('#btnGuardarInforme').click(function () {
            if ($('#tblDigitacion tbody').children().length > 0) {
                var numDoc = $('#textNumDoc').val();
                var tipoDoc = $('#textTipDoc').val();

                var url = '../digitacion/guardarMPDigitacionTDR' +
                    '?numDoc=' + numDoc +
                    '&tipoDoc=' + tipoDoc;

                $.get(url, function (data) {
                    if (data.codEjec === '0') {
                        dialogInfo.dialog('open');
                        dialogInfo.dialog('option', 'width', 320);
                    } else {
                        dialogAlerta.html('<p>' + data.msgEjec + '</p>');
                        dialogAlerta.dialog('open');
                        dialogAlerta.dialog('option', 'width', 320);
                    }
                })
            }
        });

        $('#btnValidarInforme').click(function () {
            if ($('#tblDigitacion tbody').children().length > 0) {
                var idPeriodo = $('#valIdPeriodo').val();
                var numDoc = $('#textNumDoc').val();
                var tipoDoc = $('#textTipDoc').val();
                var fechaDoc = $('#textFecDoc').val();
                var diaMes = fechaDoc.split("-");
                var dia = diaMes[0]; //dia
                var mes = diaMes[1];//mes
                var idInforme = $('#selectInforme option:selected').val();
                var idSistradoc = $('#valIdSistradoc').val();
                var ejercicio = $('#valEjercicio').val();

                var validacionDTO = {
                    idPeriodo: idPeriodo,
                    numDoc: numDoc,
                    dia: dia,
                    mes: mes,
                    tipoDoc: tipoDoc,
                    idInforme: idInforme,
                    idSistradoc: idSistradoc,
                    ejercicio: ejercicio
                };

                var url = '../digitacion/validarMPDigitacionTDR';

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: url,
                    data: JSON.stringify(validacionDTO),
                    dataType: 'json',
                    success: function (data) {
                        if (data.codEjec === '0') {
                            $('#valIdFileUpload').val(data.newId);
                            $('#dialogValidacionInf').html('<p>' + data.msgEjec + '<br>Pulse aceptar para ver reporte de validacion.</p>')
                            dialogValidacionInf.dialog('open');
                            dialogValidacionInf.dialog('option', 'width', 320);
                        } else {
                            dialogAlerta.html('<p>' + data.msgEjec + '</p>');
                            dialogAlerta.dialog('open');
                            dialogAlerta.dialog('option', 'width', 320);
                        }
                    }
                });
            }
        });
    });

    function eliminarFilaDigitacionTDRMP(idFila) {
        $('#idFilaToDel').val(idFila);
        $("#dialogConfirmacion").dialog('open');
        $("#dialogConfirmacion").dialog('option', 'width', 320);
    }

    function verEditarMP(idFila,
                         totalIngresosCLP,
                         totalGastosCLP,
                         totalIngresosUSD,
                         totalGastosUSD,
                         codPartida,
                         nomPartida,
                         codCapitulo,
                         nomCapitulo,
                         codPrograma,
                         nomPrograma,
                         idPrograma) {

        var url = '../digitacion/buscarDetalleMPDigitacionTDR?idTDRMP=' + idFila
        $.get(url, function (data) {
            $('#divTablaDetTdrMp').html(data);
            $('#valIdFilaEdit').val(idFila);
            $('#valIdProgEdit').val(idPrograma);
            $('#lblTotIngrCLP').text(totalIngresosCLP);
            $('#lblTotGastCLP').text(totalGastosCLP);
            $('#lblTotIngrUSD').text(totalIngresosUSD);
            $('#lblTotGastUSD').text(totalGastosUSD);
            $('#lblPartida').text(codPartida + ' ' + nomPartida);
            $('#lblCapitulo').text(codCapitulo + ' ' + nomCapitulo);
            $('#lblPrograma').text(codPrograma + ' ' + nomPrograma);
            $('#valCodPartida').val(codPartida);
            $('#valCodCapitulo').val(codCapitulo);
            $('#valCodPrograma').val(codPrograma);
            $('#valNomPartida').val(nomPartida);
            $('#valNomCapitulo').val(nomCapitulo);
            $('#valNomPrograma').val(nomPrograma);
            $('#valMoneda').val('CLP');
            $('#checkOpcionAumCLP').attr('checked', 'checked');
            $("#dialogDigitacion").dialog('open');
            $("#dialogDigitacion").dialog("option", "width", 1000);
            $('#divDigitacionUSD').hide();
            $('#divDigitacionCLP').show();
        });
    }

    function getGlosasPartida() {
        var idTDRMP = $('#valIdFilaEdit').val();
        var codPartida = $('#valCodPartida').val();
        var nomPartida = $('#valNomPartida').val();

        $('#valCodPartidaGlo').val(codPartida);
        $('#valNomPartidaGlo').val(nomPartida);
        $('#valCodCapituloGlo').val('00');
        $('#valNomCapituloGlo').val('');
        $('#valCodProgramaGlo').val('00');
        $('#valNomProgramaGlo').val('');
        $('#valIdDetTDRMP').val(0);
        $('#valIndPresupGlo').val('');

        var url = '../digitacion/getDialogGlosas' +
            '?idTDRMP=' + idTDRMP +
            '&idDetTDRMP=0&codPartida=' + codPartida +
            '&codCapitulo=00&codPrograma=00';

        $.get(url, function (data) {
            var dialogGlosas = $('#dialogGlosas');
            dialogGlosas.html(data);
            dialogGlosas.dialog('open');
            dialogGlosas.dialog("option", "width", 1024);
        });
    }

    function getGlosasCapitulo() {
        var idTDRMP = $('#valIdFilaEdit').val();
        var codPartida = $('#valCodPartida').val();
        var nomPartida = $('#valNomPartida').val();
        var codCapitulo = $('#valCodCapitulo').val();
        var nomCapitulo = $('#valNomCapitulo').val();

        $('#valCodPartidaGlo').val(codPartida);
        $('#valNomPartidaGlo').val(nomPartida);
        $('#valCodCapituloGlo').val(codCapitulo);
        $('#valNomCapituloGlo').val(nomCapitulo);
        $('#valCodProgramaGlo').val('00');
        $('#valNomProgramaGlo').val('');
        $('#valIdDetTDRMP').val(0);
        $('#valIndPresupGlo').val('');

        var url = '../digitacion/getDialogGlosas' +
            '?idTDRMP=' + idTDRMP +
            '&idDetTDRMP=0&codPartida=' + codPartida +
            '&codCapitulo=' + codCapitulo +
            '&codPrograma=00';

        $.get(url, function (data) {
            var dialogGlosas = $('#dialogGlosas');
            dialogGlosas.html(data);
            dialogGlosas.dialog('open');
            dialogGlosas.dialog("option", "width", 1024);
        });
    }

    function getGlosasPrograma() {
        var idTDRMP = $('#valIdFilaEdit').val();
        var codPartida = $('#valCodPartida').val();
        var nomPartida = $('#valNomPartida').val();
        var codCapitulo = $('#valCodCapitulo').val();
        var nomCapitulo = $('#valNomCapitulo').val();
        var codPrograma = $('#valCodPrograma').val();
        var nomPrograma = $('#valNomPrograma').val();

        $('#valCodPartidaGlo').val(codPartida);
        $('#valNomPartidaGlo').val(nomPartida);
        $('#valCodCapituloGlo').val(codCapitulo);
        $('#valNomCapituloGlo').val(nomCapitulo);
        $('#valCodProgramaGlo').val(codPrograma);
        $('#valNomProgramaGlo').val(nomPrograma);
        $('#valIdDetTDRMP').val(0);
        $('#valIndPresupGlo').val('');

        var url = '../digitacion/getDialogGlosas' +
            '?idTDRMP=' + idTDRMP +
            '&idDetTDRMP=0&codPartida=' + codPartida +
            '&codCapitulo=' + codCapitulo +
            '&codPrograma=' + codPrograma;

        $.get(url, function (data) {
            var dialogGlosas = $('#dialogGlosas');
            dialogGlosas.html(data);
            dialogGlosas.dialog('open');
            dialogGlosas.dialog("option", "width", 1024);
        });
    }

    function volverBusquedaTDR() {
        $.get('../digitacion/volverBusquedaTDR', function (data) {
            $('#divContenido').html(data);
        })
    }
</script>
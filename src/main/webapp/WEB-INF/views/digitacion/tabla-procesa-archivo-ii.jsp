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
<script type="text/javascript" src="${js}/jquery.inputmask.min.js"></script>
<script type="text/javascript" src="${js}/jquery.numeric.js"></script>
<script type="text/javascript" src="${js}/digitacionII.js"></script>
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
<div class="py-1">
    <div class="container-fluid">
        <div class="card bg-secondary">
            <div class="card-body">
                <div class="row">
                    <div class="col-md-12">
                        <ul class="nav nav-pills mb-3" id="pills-tab" role="tablist">
                            <li class="nav-item" >
                                <a class="nav-link active" id="pills-ident-tab" data-toggle="pill" href="#pills-ident" role="tab" aria-controls="pills-ident" aria-selected="true">Identificación</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" id="pills-mod-tab" data-toggle="pill" href="#pills-mod" role="tab" aria-controls="pills-mod" aria-selected="false">Modificación</a>
                            </li>
                        </ul>
                        <div class="tab-content" id="pills-tabContent">
                            <div class="tab-pane fade show active" id="pills-ident" role="tabpanel" aria-labelledby="pills-ident-tab">
                                <h6>Identificación Iniciativas de Inversión</h6>
                                <div class="row">
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>Partida</label>
                                            <select class="form-control" id="selectPartidaIdent">
                                                <option value="-1">Seleccione Partida</option>
                                                <c:forEach var="part" items="${partidas}">
                                                    <option value="${part.nombre}">${part.nombre} ${part.codigo}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>Capítulo</label>
                                            <select class="form-control" id="selectCapituloIdent">
                                                <option value="-1">Seleccione Capítulo</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>Programa</label>
                                            <select class="form-control" id="selectProgramaIdent">
                                                <option value="-1">Seleccione Programa</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>Subtítulo</label>
                                            <select class="form-control" id="selectSubtituloIdent">
                                                <option value="-1">Seleccione Subtítulo</option>
                                                <c:forEach var="subt" items="${listSubt}">
                                                    <option value="${subt.pkCuenta}">${subt.codSubtitulo} ${subt.nomCuenta}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>Item</label>
                                            <select class="form-control" id="selectItemIdent">
                                                <option value="-1">Seleccione Item</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-1">
                                        <div class="form-group">
                                            <label>Moneda</label>
                                            <select class="form-control" id="selectMonedaIdent">
                                                <option value="-1">Seleccione Moneda</option>
                                                <c:forEach var="mon" items="${listaMonedas}">
                                                    <option value="${mon.descMoneda}">${mon.descMoneda}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-1 align-self-center mt-2">
                                        <button type="button" class="btn btn-primary" id="btnAceptarIdent">Aceptar</button>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12" id="divTblIdent">
                                        <%@ include file="tabla-det-tdr-ii-ident.jsp" %>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-pane fade" id="pills-mod" role="tabpanel" aria-labelledby="pills-mod-tab">
                                <h6>Modificación Iniciativas de Inversión</h6>
                                <div class="row">
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>Partida</label>
                                            <select class="form-control" id="selectPartidaMod">
                                                <option value="-1">Seleccione Partida</option>
                                                <c:forEach var="part" items="${partidas}">
                                                    <option value="${part.nombre}">${part.nombre} ${part.codigo}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>Capítulo</label>
                                            <select class="form-control" id="selectCapituloMod">
                                                <option value="-1">Seleccione Capítulo</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>Programa</label>
                                            <select class="form-control" id="selectProgramaMod">
                                                <option value="-1">Seleccione Programa</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>Subtítulo</label>
                                            <select class="form-control" id="selectSubtituloMod">
                                                <option value="-1">Seleccione Subtítulo</option>
                                                <c:forEach var="subt" items="${listSubt}">
                                                    <option value="${subt.pkCuenta}">${subt.codSubtitulo} ${subt.nomCuenta}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-2">
                                        <div class="form-group">
                                            <label>Item</label>
                                            <select class="form-control" id="selectItemMod">
                                                <option value="-1">Seleccione Item</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-1">
                                        <div class="form-group">
                                            <label>Moneda</label>
                                            <select class="form-control" id="selectMonedaMod">
                                                <option value="-1">Seleccione Moneda</option>
                                                <c:forEach var="mon" items="${listaMonedas}">
                                                    <option value="${mon.descMoneda}">${mon.descMoneda}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-1 align-self-center mt-2">
                                        <button type="button" class="btn btn-primary" id="btnAceptarMod">Aceptar</button>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12" id="divTblMod">
                                        <%@ include file="tabla-det-tdr-ii-mod.jsp" %>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="py-3">
    <div class="container-fluid d-flex justify-content-center">
        <div class="row">
            <div class="col-md-6">
                <button type="button" class="btn btn-primary" onclick="validarDigitacionTDRII();">Validar</button>
            </div>
            <div class="col-md-6">
                <button type="button" class="btn btn-primary" onclick="guardarDigitacion();">Guardar</button>
            </div>
        </div>
    </div>
</div>
<div id="dialogInfo" title="Información" class="custom-with-dialog">
</div>
<div id="dialogConfirmacionIdent" title="Confirmación" class="custom-with-dialog">
    <p>¿Esta seguro que desea borrar esta sección de la digitación?</p>
</div>
<div id="dialogConfirmacionMod" title="Confirmación" class="custom-with-dialog">
    <p>¿Esta seguro que desea borrar esta sección de la digitación?</p>
</div>
<div id="dialogEditAsignacion" title="Editar Asignación Proyectos BIP">
</div>
<div id="dialogConfirDeleteAsig" title="Confirmación" class="custom-with-dialog">
    <p>¿Esta seguro que desea borrar esta línea de detalle?</p>
</div>
<div id="dialogAddLimFutIdent" title="Límite máximo de compromiso futuro">
</div>
<div id="dialogAddLimFutMod" title="Límite máximo de compromiso futuro">
</div>
<div id="dialogEditLimFut" title="Editar límite máximo de compromiso futuro">
</div>
<div id="dialogConfirDelLimFut" title="Confirmación" class="custom-with-dialog">
    <p>¿Esta seguro que desea borrar esta línea de detalle?</p>
</div>
<div id="dialogInfoGuardar" title="Información">
    <p>Digitación guardada con éxito.</p>
</div>
<div id="dialogValidacionInf" title="Información">
</div>
<input type="hidden" id="valIdFileUpload"/>
<input type="hidden" id="valIdSistradoc" value="${idSistradoc}"/>
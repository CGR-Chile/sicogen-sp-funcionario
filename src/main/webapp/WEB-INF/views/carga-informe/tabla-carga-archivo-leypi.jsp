<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-11-18
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>
<spring:url value="/resources/img" var="images"/>
<div class="py-3">
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-12">
                <table class="adminlist">
                    <thead>
                    <tr>
                        <td colspan="5" class="title-table"><b>Carga de Documentos</b></td>
                    </tr>
                    <tr class="title-grid">
                        <td class="title-grid" width="20%"><div align="center"><span class="Estilo11"><b>INFORMES</b></span></div></td>
                        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>SELECCION DE ARCHIVO</b></span></div></td>
                        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>OBSERVACIÓN CARGA</b></span></div></td>
                        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>ESTADO</b></span></div></td>
                        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>RV</b></span></div></td>
                    </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td style="text-align: center;">${informePI.nombre}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${informePI.estadoSicogen != null and (informePI.estadoSicogen == '6' or informePI.estadoSicogen == '7')}">
                                        <input type="text" class="form-control" value="${informePI.nombreArchivo}" readonly/>
                                    </c:when>
                                    <c:otherwise>
                                        <form id="idFormPI" enctype="multipart/form-data" class="formFile" action="javascript:void(0);" method="post">
                                            <input type="file" class="form-control-file" name="file" onchange="realizaReglasCargaPI();">
                                        </form>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td style="text-align: center;">
                                <c:choose>
                                    <c:when test="${informePI.estadoSicogen != null and (informePI.estadoSicogen=='1' or informePI.estadoSicogen=='3')}">
                                        Cargado exitosamente.
                                    </c:when>
                                    <c:when test="${informePI.estadoSicogen != null and informePI.estadoSicogen != '6' and informePI.estadoSicogen != '7'}">
                                        <div class="d-flex justify-content-center">
                                            <span style="margin-right: 10px;cursor: pointer" onclick="verErroresCarga();">Ver errores de carga</span>
                                            <img style="width:25px;height:25px;" src="${images}/error_carga.png" title="errores de carga" />
                                        </div>
                                    </c:when>
                                </c:choose>
                            </td>
                            <td style="text-align: center;">
                                <c:choose>
                                    <c:when test="${informePI.estadoSicogen == '1'}">
                                        Listo para validar.
                                    </c:when>
                                    <c:when test="${informePI.estadoSicogen =='3'}">
                                        <img  src="${images}/error.png" style="width:25px;height:25px;margin-left:auto;margin-right:auto;" title="Informe Con Errores">
                                    </c:when>
                                    <c:when test="${informePI.estadoSicogen =='4'}">
                                        <img  src="${images}/Validado.png" style="width:25px;height:25px;margin-left:auto;margin-right:auto;" title="Informe Validado">
                                    </c:when>
                                    <c:when test="${informePI.estadoSicogen =='5'}">
                                        <img  src="${images}/ValidadoOBS.png" style="width:25px;height:25px;margin-left:auto;margin-right:auto;" title="Informe Validado con observaciones">
                                    </c:when>
                                    <c:when test="${informePI.estadoSicogen =='6'}">
                                        <img  src="${images}/Procesado.png" style="width:25px;height:25px;margin-left:auto;margin-right:auto;" title="Informe Procesado">
                                    </c:when>
                                    <c:when test="${informePI.estadoSicogen =='7'}">
                                        <img  src="${images}/ProcesadoOBS.png" style="width:25px;height:25px;margin-left:auto;margin-right:auto;" title="Informe Procesado con observaciones">
                                    </c:when>
                                </c:choose>
                            </td>
                            <td style="text-align: center;">
                                <c:if test="${informePI.estadoSicogen=='3' or informePI.estadoSicogen=='4'
													or informePI.estadoSicogen=='5' or informePI.estadoSicogen=='6'
														or informePI.estadoSicogen=='7'}">
                                    <a href="../validacion/reportes/informes/pi?idFileUp=${informePI.idFileUpload}" target="_blank">
                                        <img  src="${images}/rv.png" style="margin-left:auto;margin-right:auto;cursor: pointer;" title="Reporte de Validación"/>
                                    </a>
                                </c:if>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
        <div class="row mt-5 d-flex justify-content-end">
            <div class="col-md-2 d-flex justify-content-end">
                <c:choose>
                    <c:when test="${informePI.estadoSicogen == '1' or informePI.estadoSicogen == '3'}">
                        <button type="button" class="btn btn-primary" onclick="validarInformePI();">Validar</button>
                    </c:when>
                    <c:otherwise>
                        <button type="button" class="btn btn-primary" disabled>Validar</button>
                    </c:otherwise>
                </c:choose>
                <button type="button" class="btn btn-primary" style="margin-left: 10px;" onclick="volverCargaInformes();">Volver</button>
            </div>
        </div>
    </div>
</div>
<div id="dialogReglaCarga" title="Presupuesto Inicial">
    <c:forEach items="${validacionReglaBO}" var="reglaCarga">
        <p> <c:out value="${reglaCarga.mensajeError}"/> </p>
    </c:forEach>
</div>
<script>
    var dialogReglaCarga;
    $(document).ready(function () {
        dialogReglaCarga = $( "#dialogReglaCarga" ).dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Cerrar: function() {
                    $( this ).dialog( "close" );
                }
            }
        });
    });
</script>

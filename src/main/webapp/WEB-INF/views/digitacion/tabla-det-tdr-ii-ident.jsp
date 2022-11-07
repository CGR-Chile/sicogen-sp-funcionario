<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-10-29
  Time: 18:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:url value="/resources/img" var="images"/>
<div class="accordion" id="accordion-ident">
    <c:forEach var="decr" items="${objXmlII.decreto_II}" varStatus="statusDecr">
        <c:forEach var="ident" items="${decr.identificacionProyectos}" varStatus="statusIdent">
            <div class="card">
                <div class="card-header bg-primary" id="heading_ident_${statusDecr.index}${statusIdent.index}">
                    <div class="row">
                        <div class="col-md-11">
                            <span class="text-white" data-toggle="collapse" data-target="#collapse_ident_${statusDecr.index}${statusIdent.index}" aria-expanded="true" aria-controls="collapse_ident_${statusDecr.index}${statusIdent.index}" style="cursor: pointer;"><strong>Decreto: Partida: ${decr.codigoPartida} - Capítulo : ${decr.codigoCapitulo} - Programa: ${decr.codigoPrograma} - SUBT : ${ident.codigoSubtitulo} - ITEM : ${ident.codigoItem} - MONEDA : ${ident.moneda}</strong></span>
                        </div>
                        <div class="col-md-1">
                            <img src="${images}/delete.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="openDeleteSectionIdentificacion('${decr.codigoPartida}','${decr.codigoCapitulo}','${decr.codigoPrograma}','${ident.codigoSubtitulo}','${ident.codigoItem}','${ident.moneda}')"/>
                        </div>
                    </div>
                </div>
                <div id="collapse_ident_${statusDecr.index}${statusIdent.index}" class="collapse" aria-labelledby="heading_ident_${statusDecr.index}${statusIdent.index}" data-parent="#accordion-ident">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12 d-flex justify-content-end">
                                <button type="button" class="btn btn-primary" onclick="openAddProyectosIdent('${decr.codigoPartida}', '${decr.codigoCapitulo}', '${decr.codigoPrograma}', '${ident.codigoSubtitulo}', '${ident.codigoItem}')">Proyecto BIP</button>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-12">
                                <table class="adminlist">
                                    <thead>
                                    <tr>
                                        <td colspan="12" class="title-table"><span class="Estilo11"><b>Identifiquese los siguientes proyectos</b></span></td>
                                    </tr>
                                    <tr class="title-grid">
                                        <td class="title-grid" width="20%"><div align="center"><span class="Estilo11"><b>Código BIP</b></span></div></td>
                                        <td class="title-grid" width="30%"><div align="center"><span class="Estilo11"><b>Denominación</b></span></div></td>
                                        <c:if test="${!empty ident.detalleIdentificacionProyectos}">
                                            <c:forEach var="asig" items="${ident.detalleIdentificacionProyectos[0].montosAsignacion}">
                                                <td class="title-grid" width="5%"><div align="center"><span class="Estilo11"><b>${asig.codigoAsignacion}</b></span></div></td>
                                            </c:forEach>
                                        </c:if>
                                        <td class="title-grid" width="20%"><div align="center"><span class="Estilo11"><b>Compromiso futuro</b></span></div></td>
                                        <td class="title-grid" width="5%"><div align="center"><span class="Estilo11"><b>Eliminar</b></span></div></td>
                                        <td class="title-grid" width="5%"><div align="center"><span class="Estilo11"><b>Editar</b></span></div></td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${!empty ident.detalleIdentificacionProyectos}">
                                        <c:forEach var="detproy" items="${ident.detalleIdentificacionProyectos}">
                                            <tr class="row1">
                                                <td style="text-align: center;">${detproy.codigoBIP}-${detproy.codigoInternoDecretoSIAP}</td>
                                                <td>${detproy.denominacion}</td>
                                                <c:forEach var="asig" items="${detproy.montosAsignacion}">
                                                    <td style="text-align: right;">${asig.monto}</td>
                                                </c:forEach>
                                                <td style="text-align: center;"><img src="${images}/calendar.jpg" alt="" class="icoImage18" style="cursor: pointer;" onclick="getLimitesFuturosIdent('${decr.codigoPartida}', '${decr.codigoCapitulo}', '${decr.codigoPrograma}', '${ident.moneda}', '${detproy.codigoBIP}', '${detproy.codigoInternoDecretoSIAP}')"/></td>
                                                <td style="text-align: center;"><img src="${images}/delete.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="openDeleteAsignacion('${decr.codigoPartida}','${decr.codigoCapitulo}','${decr.codigoPrograma}','${ident.codigoSubtitulo}','${ident.codigoItem}','${detproy.codigoBIP}')"/></td>
                                                <td style="text-align: center;"><img src="${images}/edit.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="openEditarAsigProyectos(true, '${decr._IdDecreto_II}', '${ident._idIdentificacionProyectos}', '${detproy._idDetalleIdentificacionProyectos}')"/></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-12">
                                <table class="adminlist">
                                    <thead>
                                    <tr>
                                        <td colspan="12" class="title-table"><span class="Estilo11"><b>Limite máximo de compromisos futuros</b></span></td>
                                    </tr>
                                    <tr class="title-grid">
                                        <td class="title-grid" width="20%"><div align="center"><span class="Estilo11"><b>Código BIP</b></span></div></td>
                                        <td class="title-grid" width="30%"><div align="center"><span class="Estilo11"><b>Denominación</b></span></div></td>
                                        <c:forEach var="mon" items="${decr.limiteMaximoCompromisos[0].montosAnio}">
                                            <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>${mon.anio}</b></span></div></td>
                                        </c:forEach>
                                        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Eliminar</b></span></div></td>
                                        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Editar</b></span></div></td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${!empty ident.detalleIdentificacionProyectos}">
                                        <c:forEach var="detproy" items="${ident.detalleIdentificacionProyectos}" varStatus="status">
                                            <c:forEach var="lim" items="${decr.limiteMaximoCompromisos}">
                                                <c:if test="${detproy.codigoBIP == lim.codigoBIP}">
                                                    <tr class="row1">
                                                        <td style="text-align: center;">${detproy.codigoBIP}-${detproy.codigoInternoDecretoSIAP}</td>
                                                        <td>${detproy.denominacion}</td>
                                                        <c:forEach var="mon" items="${lim.montosAnio}">
                                                            <td style="text-align: right;">${mon.monto}</td>
                                                        </c:forEach>
                                                        <td style="text-align: center;"><img src="${images}/delete.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="openDeleteLimite('${decr.codigoPartida}', '${decr.codigoCapitulo}', '${decr.codigoPrograma}', '${detproy.codigoBIP}')"/></td>
                                                        <td style="text-align: center;"><img src="${images}/edit.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="openEditarLimiteCompromiso('${decr._IdDecreto_II}', '${lim._idLimiteMaximoCompromiso}');"/></td>
                                                    </tr>
                                                </c:if>
                                            </c:forEach>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </c:forEach>
</div>
<div id="dialogAddProyectoBipIdent" title="Agregar Proyectos BIP">
</div>
<script>
    var dialogAddProyectoBipIdent;

    function openAddProyectosIdent(codPart, codCap, codProg, codSubt, codItem) {
        var url = '../digitacion/getProyectosByDigitacionIdent' +
            '?codPart=' + codPart +
            '&codCap=' + codCap +
            '&codProg=' + codProg +
            '&codSubt=' + codSubt +
            '&codItem=' + codItem;

        $.get(url, function (data) {
            dialogAddProyectoBipIdent = $( "#dialogAddProyectoBipIdent" ).dialog({
                autoOpen: false,
                modal: true,
                buttons: {
                    Aceptar: function() {
                        agregarProyectoIIIdent();
                    }
                }
            });
            dialogAddProyectoBipIdent.html(data);
            dialogAddProyectoBipIdent.dialog('open');
            dialogAddProyectoBipIdent.dialog('option', 'width', 800);
        });
    }

    function agregarProyectoIIIdent(){

        if (cdgProyectSeleccIdent.length < 1 ){
            dialogInfoIdent.html('<p>Debe seleccionar un Proyecto BIP</p>');
            dialogInfoIdent.dialog('open');
            dialogInfoIdent.dialog('option', 'width', 320);
        } else {

            var input = "";

            $(".dinamicField2Ident").each(function(){
                input = input.concat($(this).attr("name") + "=" + $(this).val() + "&");
            });

            input = input + 'codProyecto='  + cdgProyectSeleccIdent;

            $.post('../digitacion/postProyectosIIIdent', input).done(function (data) {
                if (data.codEjec !== "0") {
                    dialogInfoIdent.html('<p>' + data.msgEjec + '</p>');
                    dialogInfoIdent.dialog('open');
                    dialogInfoIdent.dialog('option', 'width', 320);
                } else {
                    $.get('../digitacion/getDetalleDigitacionIIIdent', function (data2) {
                        dialogAddProyectoBipIdent.dialog('destroy');
                        $('#divTblIdent').html(data2);
                    });
                }
            });
        }
    }

</script>

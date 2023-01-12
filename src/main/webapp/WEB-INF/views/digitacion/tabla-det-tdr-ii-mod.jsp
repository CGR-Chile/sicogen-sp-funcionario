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
<div class="accordion" id="accordion-mod">
    <c:forEach var="decr" items="${objXmlII.decreto_II}" varStatus="statusDecr">
        <c:forEach var="modi" items="${decr.modificacionProyectos}" varStatus="statusMod">
            <div class="card">
                <div class="card-header bg-primary" id="heading_mod_${statusDecr.index}${statusMod.index}">
                    <div class="row">
                        <div class="col-md-11">
                            <span class="text-white" data-toggle="collapse" data-target="#collapse_mod_${statusDecr.index}${statusMod.index}" aria-expanded="true" aria-controls="collapse_mod_${statusDecr.index}${statusMod.index}" id="expandCollapse_span_mod_${statusDecr.index}${statusMod.index}" onclick="expandCollapse('${statusDecr.index}${statusMod.index}','mod','${images}')" style="cursor: pointer;"><img src="${images}/expandall.png" alt="Expandir" title="Expandir" style="cursor: pointer;"/>&nbsp;<strong>Decreto: Partida: ${decr.codigoPartida} - Capítulo : ${decr.codigoCapitulo} - Programa: ${decr.codigoPrograma} - SUBT : ${modi.codigoSubtitulo} - ITEM : ${modi.codigoItem} - MONEDA : ${modi.moneda}</strong></span>
                        </div>
                        <div class="col-md-1">
                            <img src="${images}/delete.png"  alt="Eliminar" title="Eliminar"  class="icoImage18" style="cursor: pointer;" onclick="openDeleteSectionModificacion('${decr.codigoPartida}','${decr.codigoCapitulo}','${decr.codigoPrograma}','${modi.codigoSubtitulo}','${modi.codigoItem}','${modi.moneda}')"/>
                        </div>
                    </div>
                </div>
                <div id="collapse_mod_${statusDecr.index}${statusMod.index}" class="collapse" aria-labelledby="heading_mod_${statusDecr.index}${statusMod.index}" data-parent="#accordion-mod">
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-12 d-flex justify-content-end">
                                <button type="button" class="btn btn-primary" id="btnProyectoBIP" onclick="openAddProyectosMod('${decr.codigoPartida}', '${decr.codigoCapitulo}', '${decr.codigoPrograma}', '${modi.codigoSubtitulo}', '${modi.codigoItem}')">Proyecto BIP</button>
                            </div>
                        </div>
                        <br/>
                        <div class="row">
                            <div class="col-md-12">
                                <table class="adminlist">
                                    <thead>
                                    <tr>
                                        <td colspan="5" class="title-table"><span class="Estilo11"><b>Modifiquese los siguientes proyectos</b></span></td>
                                    </tr>
                                    <tr class="title-grid">
                                        <td class="title-grid" width="20%"><div align="center"><span class="Estilo11"><b>Código BIP</b></span></div></td>
                                        <td class="title-grid" width="40%"><div align="center"><span class="Estilo11"><b>Denominación</b></span></div></td>
                                        <td class="title-grid" width="20%"><div align="center"><span class="Estilo11"><b>Compromiso futuro</b></span></div></td>
                                        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Eliminar</b></span></div></td>
                                        <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Editar</b></span></div></td>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${!empty modi.detalleModificacionProyectos}">
                                        <c:forEach var="detproy" items="${modi.detalleModificacionProyectos}">
                                            <tr class="row1">
                                                <td style="text-align: center;">${detproy.codigoBIP}-${detproy.codigoInternoDecretoSIAP}</td>
                                                <td>${detproy.denominacion}</td>
                                                <td style="text-align: center;"><img src="${images}/calendar.jpg" alt="" class="icoImage18" style="cursor: pointer;" onclick="getLimitesFuturosModif('${decr.codigoPartida}', '${decr.codigoCapitulo}', '${decr.codigoPrograma}', '${modi.moneda}', '${detproy.codigoBIP}', '${detproy.codigoInternoDecretoSIAP}')"/></td>
                                                <td style="text-align: center;"><img src="${images}/delete.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="openDeleteAsignacion('${decr.codigoPartida}','${decr.codigoCapitulo}','${decr.codigoPrograma}','${modi.codigoSubtitulo}','${modi.codigoItem}','${detproy.codigoBIP}')"/></td>
                                                <td style="text-align: center;"><img src="${images}/edit.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="openEditarAsigProyectos(false, '${decr._IdDecreto_II}', '${modi._IdModificacionProyectos}', '${detproy._idDetalleModificacionProyectos}')"/></td>
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
                                    <c:if test="${!empty modi.detalleModificacionProyectos}">
                                        <c:forEach var="detproy" items="${modi.detalleModificacionProyectos}" varStatus="status">
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
<div id="dialogAddProyectoBipMod" title="Agregar Proyectos BIP">
</div>
<script>

    var dialogAddProyectoBipMod;

    function openAddProyectosMod(codPart, codCap, codProg, codSubt, codItem) {
        var url = '../digitacion/getProyectosByDigitacionMod' +
            '?codPart=' + codPart +
            '&codCap=' + codCap +
            '&codProg=' + codProg +
            '&codSubt=' + codSubt +
            '&codItem=' + codItem;

        $.get(url, function (data) {
            dialogAddProyectoBipMod = $( "#dialogAddProyectoBipMod" ).dialog({
                autoOpen: false,
                modal: true,
                buttons: {
                    Aceptar: function() {
                        agregarProyectoIIMod();
                    }
                }
            });
            dialogAddProyectoBipMod.html(data);
            dialogAddProyectoBipMod.dialog('open');
            dialogAddProyectoBipMod.dialog('option', 'width', 800);
        });
    }

    function agregarProyectoIIMod(){

        if (cdgProyectSeleccMod.length < 1 ){
            dialogInfoMod.html('<p>Debe seleccionar un Proyecto BIP</p>');
            dialogInfoMod.dialog('open');
            dialogInfoMod.dialog('option', 'width', 320);
        } else {
            /*
            var input = "";
            $(".dinamicField2Mod").each(function(){
                input = input.concat($(this).attr("name") + "=" + $(this).val() + "&");
            });
            input = input + 'codProyecto='  + cdgProyectSeleccMod;
            */
            //alert($("#form_dinamicField2Mod").serialize());
            let input=$("#form_dinamicField2Mod").serialize();
            $.post('../digitacion/postProyectosIIMod', input).done(function (data) {
                if (data.codEjec !== "0") {
                    dialogInfoMod.html('<p>' + data.msgEjec + '</p>');
                    dialogInfoMod.dialog('open');
                    dialogInfoMod.dialog('option', 'width', 320);
                } else {
                    $.get('../digitacion/getDetalleDigitacionIIMod', function (data2) {
                        dialogAddProyectoBipMod.dialog('destroy');
                        $('#divTblMod').html(data2);
                    });
                }
            });
        }
    }
</script>
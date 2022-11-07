<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-10-22
  Time: 22:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    .font-12 {
        font-size: 12px !important;
    }
</style>
<table class="adminlist" id="tblInformesReproceso">
    <thead>
    <tr>
        <td colspan="6" class="title-table"><span class="Estilo11"><b>Reprocesos enviados</b></span></td>
    </tr>
    <tr class="title-grid">
        <td class="title-grid" width="30%"><div align="center"><span class="font-12"><b>Partida</b></span></div></td>
        <td class="title-grid" width="30%"><div align="center"><span class="font-12"><b>Capítulo</b></span></div></td>
        <td class="title-grid" width="10%"><div align="center"><span class="font-12"><b>Informe</b></span></div></td>
        <td class="title-grid" width="10%"><div align="center"><span class="font-12"><b>Período</b></span></div></td>
        <td class="title-grid" width="5%"><div align="center"><span class="font-12"><b>Ejercicio</b></span></div></td>
        <td class="title-grid" width="5%"><div align="center"><span class="font-12"><b>Autorizar</b></span></div></td>
    </tr>
    </thead>
    <tbody>
    <c:if test="${!empty informesReproceso}">
        <c:forEach var="inf" items="${informesReproceso}" varStatus="status">
            <c:choose>
                <c:when test="${status.index % 2 == 0}">
                    <c:set var="rowClass" value="row0"/>
                </c:when>
                <c:otherwise>
                    <c:set var="rowClass" value="row1"/>
                </c:otherwise>
            </c:choose>
            <tr class="<c:out value="${rowClass}"/>">
                <td class="font-12">${inf.partida}</td>
                <td class="font-12">${inf.capitulo}</td>
                <td class="font-12" style="text-align: center;">${inf.informe}</td>
                <td class="font-12" style="text-align: center;">${inf.periodo}</td>
                <td class="font-12" style="text-align: center;">${inf.ejercicio}</td>
                <td class="font-12" style="text-align: center;"><input type="checkbox" id="checkId_${inf.fileuId}"></td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<div class="py-1">
    <div class="container d-flex justify-content-center">
        <div class="row">
            <div class="col-md-12">
                <button type="button" class="btn btn-primary" id="btnAutorizarReproceso">Autorizar</button>
            </div>
        </div>
    </div>
</div>
<div id="dialogAlerta" title="Alerta">
</div>
<div id="dialogConfirmacion" title="Información">
</div>
<script>
    $(document).ready(function () {

        var dialogAlerta = $( "#dialogAlerta" ).dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Aceptar: function() {
                    $( this ).dialog( "close" );
                }
            }
        });

        var dialogConfirmacion = $('#dialogConfirmacion').dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Aceptar: function() {
                    $.get('getInformesReproceso', function (data) {
                        $('#divTbl').html(data);
                    })
                    $( this ).dialog( "close" );
                }
            }
        });

        $('#btnAutorizarReproceso').click(function () {

            var selected = [];
            $('#tblInformesReproceso input:checked').each(function() {
                selected.push($(this).attr('id'));
            });

            if(selected.length === 0){
                dialogAlerta.html('<p>Debe seleccionar al menos un informe.</p>');
                dialogAlerta.dialog('open');
            } else {

                var fileuIds = []

                for (var i = 0, len = selected.length; i < len; i++) {
                    fileuIds[i] = selected[i].split('checkId_').join('');
                }

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: 'autorizarReprocesos',
                    data: JSON.stringify(fileuIds),
                    dataType: 'json',
                    success: function (data) {
                        if (data.codEjec !== "0") {
                            dialogAlerta.html('<p>' + data.msgEjec + '</p>');
                            dialogAlerta.dialog('open');
                        } else {
                            dialogConfirmacion.html('<p>' + data.msgEjec + '</p>');
                            dialogConfirmacion.dialog('open');
                        }
                    }
                });
            }
        });
    });
</script>
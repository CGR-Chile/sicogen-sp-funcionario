<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-10-06
  Time: 23:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:url value="/resources/img" var="images"/>
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
                <td style="text-align: center;"><img src="${images}/edit.png" alt="" class="icoImage18" style="cursor: pointer;"  onclick="verEditarMP(${digitacion.idDigitacionMP}, ${digitacion.totalIngresosCLP}, ${digitacion.totalGastosCLP}, ${digitacion.totalIngresosUSD}, ${digitacion.totalGastosUSD}, '${digitacion.codPartida}', '${digitacion.idPartida}', '${digitacion.codCapitulo}', '${digitacion.idCapitulo}', '${digitacion.codPrograma}', '${digitacion.idPrograma}', ${digitacion.idPrograma2})"/></td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<div id="dialogConfirmacion" title="Alerta">
    <p>&iquest;Est&aacute; seguro que desea eliminar el registro?</p>
</div>
<input type="hidden" id="idFilaToDel">
<script>

    $(document).ready(function() {

        $( "#dialogConfirmacion" ).dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Cancelar: function() {
                    $( this ).dialog( "close" );
                },
                Aceptar: function() {
                    $( this ).dialog( "close" );
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
                            $('#dialogAlerta').html('<p>' + data.msgEjec + '</p>');
                            $('#dialogAlerta').dialog('open');
                            $('#dialogAlerta').dialog('option', 'width', 320);
                        }
                    });
                }
            }
        });

    });

    function eliminarFilaDigitacionTDRMP(idFila) {
        $('#idFilaToDel').val(idFila);
        $("#dialogConfirmacion").dialog('open');
        $('#dialogConfirmacion').dialog('option', 'width', 320);
    }
</script>

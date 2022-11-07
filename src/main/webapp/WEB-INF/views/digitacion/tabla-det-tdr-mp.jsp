<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-10-14
  Time: 17:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<spring:url value="/resources/img" var="images"/>
<table class="adminlist">
    <thead>
        <tr>
            <td colspan="9" class="title-table"><span class="Estilo11"><b>Digitación TDR-MP Detalle</b></span></td>
        </tr>
        <tr class="title-grid">
            <td class="title-grid" width="5%"><div align="center"><span class="Estilo11"><b>Tipo Cuenta</b></span></div></td>
            <td class="title-grid" width="20%"><div align="center"><span class="Estilo11"><b>Código Cuenta</b></span></div></td>
            <td class="title-grid" width="20%"><div align="center"><span class="Estilo11"><b>Denominación Cuenta</b></span></div></td>
            <td class="title-grid" width="5%"><div align="center"><span class="Estilo11"><b>Tipo Moneda</b></span></div></td>
            <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Monto Aumento</b></span></div></td>
            <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Monto Disminucion</b></span></div></td>
            <td class="title-grid" width="10%" colspan="3"><div align="center"><span class="Estilo11"><b>Opciones</b></span></div></td>
        </tr>
    </thead>
    <tbody>
    <c:if test="${!empty listaDetalleTDRMP}">
        <c:forEach var="det" items="${listaDetalleTDRMP}" varStatus="status">
            <c:choose>
                <c:when test="${status.index % 2 == 0}">
                    <c:set var="rowClass" value="row0"/>
                </c:when>
                <c:otherwise>
                    <c:set var="rowClass" value="row1"/>
                </c:otherwise>
            </c:choose>
            <tr class="<c:out value="${rowClass}"/>">
                <td style="text-align: center;">${det.tipoModCuenta}</td>
                <td style="text-align: center;">${det.codCuenta}</td>
                <td>${det.nomCuenta}</td>
                <td style="text-align: center;">${det.desMoneda}</td>
                <td style="text-align: center;">
                    <input type="text" class="form-control" id="inptEditMntAum_${det.detTDRMPId}" style="display: none;" value="${det.montoIncremento}" onchange="updateDetalleAumDigitacionMP(${det.detTDRMPId}, this.value)"/>
                    <span id="spnEditMntAum_${det.detTDRMPId}">${det.montoIncremento}</span>
                </td>
                <td style="text-align: center;">
                    <input type="text" class="form-control" id="inptEditMntDis_${det.detTDRMPId}" style="display: none;" value="${det.montoDisminucion}" onchange="updateDetalleDisDigitacionMP(${det.detTDRMPId}, this.value)"/>
                    <span id="spnEditMntDis_${det.detTDRMPId}">${det.montoDisminucion}</span>
                </td>
                <td style="text-align: center;"><img src="${images}/delete.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="eliminarFilaDetalleDigitacionTDRMP(${det.detTDRMPId}, ${det.TDRMPId})"/></td>
                <td style="text-align: center;"><img src="${images}/edit.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="habilitarEditarMontoPopUp(${det.detTDRMPId})"/></td>
                <td style="text-align: center;"><img src="${images}/doc.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="getGlosasCuenta(${det.detTDRMPId}, '${det.desMoneda}')"/></td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<div id="dialogError" title="Error">
</div>
<script>

    $(document).ready(function () {

        $( "#dialogError" ).dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Aceptar: function() {
                    $( this ).dialog( "close" );
                }
            }
        });
    });

    function eliminarFilaDetalleDigitacionTDRMP(idDigitacionDetalleMP, idTDRMP) {
        var url = '../digitacion/deleteDetalleDigitacionTDRMP?idDetTDRMP=' + idDigitacionDetalleMP;

        $.get(url, function (data) {
            if (data.codEjec !== "0") {
                var dialogError = $( "#dialogError" );
                dialogError.html('<p>' + data.msgEjec + '</p>');
                dialogError.dialog('open');
                dialogError.dialog('option', 'width', 320);
            } else {
                var url2 = '../digitacion/buscarDetalleMPDigitacionTDR?idTDRMP=' + idTDRMP;

                $.get(url2, function (data2) {
                    $('#divTablaDetTdrMp').html(data2);
                })
            }
        })
    }

    function habilitarEditarMontoPopUp(idDetTDRMP) {
        var inptAum = $('#inptEditMntAum_' + idDetTDRMP);
        var inptDis = $('#inptEditMntDis_' + idDetTDRMP);

        inptDis.numeric({ negative: false });
        inptAum.numeric({ negative: false });
        $('#spnEditMntAum_' + idDetTDRMP).hide();
        inptDis.show();
        $('#spnEditMntDis_' + idDetTDRMP).hide();
        inptAum.show();
    }

    function updateDetalleAumDigitacionMP(idDetTDRMP, monto) {
        var url = '../digitacion/updateDetalleAumDisDigitacionMP?idDetTDRMP=' + idDetTDRMP + '&montoAumento=' + monto + '&montoDisminucion=0';

        $.get(url, function (data) {
            if (data.codEjec !== "0") {
                var dialogError = $( "#dialogError" );
                dialogError.html('<p>' + data.msgEjec + '</p>');
                dialogError.dialog('open');
                dialogError.dialog('option', 'width', 320);
            } else {
                var spnAum = $('#spnEditMntAum_' + idDetTDRMP);
                var spnDis = $('#spnEditMntDis_' + idDetTDRMP);
                var inptAum = $('#inptEditMntAum_' + idDetTDRMP);
                var inptDis = $('#inptEditMntDis_' + idDetTDRMP);

                spnDis.text('0');
                inptDis.val('0');
                spnAum.text(inptAum.val());

                spnDis.show();
                inptDis.hide();
                spnAum.show();
                inptAum.hide();
            }
        });
    }

    function updateDetalleDisDigitacionMP(idDetTDRMP, monto) {
        var url = '../digitacion/updateDetalleAumDisDigitacionMP?idDetTDRMP=' + idDetTDRMP + '&montoAumento=0&montoDisminucion=' + monto;

        $.get(url, function (data) {
            if (data.codEjec !== "0") {
                var dialogError = $( "#dialogError" );
                dialogError.html('<p>' + data.msgEjec + '</p>');
                dialogError.dialog('open');
                dialogError.dialog('option', 'width', 320);
            } else {
                var spnAum = $('#spnEditMntAum_' + idDetTDRMP);
                var spnDis = $('#spnEditMntDis_' + idDetTDRMP);
                var inptAum = $('#inptEditMntAum_' + idDetTDRMP);
                var inptDis = $('#inptEditMntDis_' + idDetTDRMP);

                spnAum.text('0');
                inptAum.val('0');
                spnDis.text(inptDis.val());

                spnDis.show();
                inptDis.hide();
                spnAum.show();
                inptAum.hide();
            }
        });
    }

    function getGlosasCuenta(detTDRMPId, moneda) {
        var idTDRMP = $('#valIdFilaEdit').val();
        var codPartida = $('#valCodPartida').val();
        var codCapitulo = $('#valCodCapitulo').val();
        var codPrograma = $('#valCodPrograma').val();
        var nomPartida = $('#valNomPartida').val();
        var nomCapitulo = $('#valNomCapitulo').val();
        var nomPrograma = $('#valNomPrograma').val();

        $('#valCodPartidaGlo').val(codPartida);
        $('#valCodCapituloGlo').val(codCapitulo);
        $('#valCodProgramaGlo').val(codPrograma);
        $('#valIdDetTDRMP').val(detTDRMPId);
        $('#valIndPresupGlo').val(moneda);
        $('#valNomPartidaGlo').val(nomPartida);
        $('#valNomCapituloGlo').val(nomCapitulo);
        $('#valNomProgramaGlo').val(nomPrograma);

        var url = '../digitacion/getDialogGlosas' +
            '?idTDRMP=' + idTDRMP +
            '&idDetTDRMP=' + detTDRMPId +
            '&codPartida=' + codPartida +
            '&codCapitulo=' + codCapitulo +
            '&codPrograma=' + codPrograma;

        $.get(url, function (data) {
            var dialogGlosas = $('#dialogGlosas');
            dialogGlosas.html(data);
            dialogGlosas.dialog('open');
            dialogGlosas.dialog("option", "width", 1024);
        });
    }
</script>
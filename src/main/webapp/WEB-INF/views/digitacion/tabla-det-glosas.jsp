<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-10-19
  Time: 16:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<spring:url value="/resources/img" var="images"/>
<table class="adminlist">
    <thead>
        <tr class="title-grid">
            <td class="title-grid" width="10%"><div align="center"><span class="Estilo11"><b>Nº Glosa</b></span></div></td>
            <td class="title-grid" width="80%"><div align="center"><span class="Estilo11"><b>Glosa</b></span></div></td>
            <td class="title-grid" width="5%"><div align="center"><span class="Estilo11"><b>Eliminar</b></span></div></td>
            <td class="title-grid" width="5%"><div align="center"><span class="Estilo11"><b>Editar</b></span></div></td>
        </tr>
    </thead>
    <tbody>
    <c:if test="${!empty glosas}">
        <c:forEach var="glo" items="${glosas}" varStatus="status">
            <c:choose>
                <c:when test="${status.index % 2 == 0}">
                    <c:set var="rowClass" value="row0"/>
                </c:when>
                <c:otherwise>
                    <c:set var="rowClass" value="row1"/>
                </c:otherwise>
            </c:choose>
            <tr class="<c:out value="${rowClass}"/>">
                <td style="text-align: center;">
                    <input type="text" class="form-control" id="inptEditNumGlosa_${glo.idGloTDRMP}" style="display: none;" value="${glo.numGlosa}" onchange="updateNumGlosa(${glo.idGloTDRMP})">
                    <span id="spnEditNumGlosa_${glo.idGloTDRMP}">${glo.numGlosa}</span>
                </td>
                <td>
                    <textarea class="form-control" id="inptEditTextoGlosa_${glo.idGloTDRMP}" style="display: none;" rows="1" onchange="updateTextoGlosa(${glo.idGloTDRMP})">${glo.textoGlosa}</textarea>
                    <span id="spnEditTextGlosa_${glo.idGloTDRMP}">${glo.textoGlosa}</span>
                </td>
                <td style="text-align: center;"><img src="${images}/delete.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="deleteGlosa(${glo.idGloTDRMP})"/></td>
                <td style="text-align: center;"><img src="${images}/edit.png" alt="" class="icoImage18" style="cursor: pointer;" onclick="editGlosa(${glo.idGloTDRMP})"/></td>
            </tr>
        </c:forEach>
    </c:if>
    </tbody>
</table>
<div id="dialogAlertaTablaGlosas" title="Alerta"></div>
<script>
    var dialogAlerta;

    $(document).ready(function () {
        dialogAlerta = $( "#dialogAlertaTablaGlosas" ).dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Aceptar: function() {
                    $( this ).dialog( "close" );
                }
            }
        });


    });

    function editGlosa(idGloTDRMP) {
        var inptEditNumGlosa = $('#inptEditNumGlosa_' + idGloTDRMP);
        inptEditNumGlosa.inputmask('99');

        $('#spnEditNumGlosa_' + idGloTDRMP).hide();
        $('#spnEditTextGlosa_' + idGloTDRMP).hide();
        inptEditNumGlosa.show();
        $('#inptEditTextoGlosa_' + idGloTDRMP).show();
    }

    function updateNumGlosa(idGloTDRMP) {
        var inptEditNumGlosa = $('#inptEditNumGlosa_' + idGloTDRMP);
        if (inptEditNumGlosa.inputmask('isComplete')) {
            var textoGlosa = $('#inptEditTextoGlosa_' + idGloTDRMP).val();
            var numGlosa = inptEditNumGlosa.val();
            var glosa = {
                idGloTDRMP: idGloTDRMP,
                numGlosa: numGlosa,
                textoGlosa: textoGlosa
            };
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: '../digitacion/updateGlosa',
                data: JSON.stringify(glosa),
                dataType: 'json',
                success: function (data) {
                    if (data.codEjec !== "0") {
                        dialogAlerta.html('<p>' + data.msgEjec + '</p>');
                        dialogAlerta.dialog('open');
                        dialogAlerta.dialog('option', 'width', 320);
                    } else {
                        var idTDRMP = $('#valIdFilaEdit').val();
                        var idDetTDRMP = $('#valIdDetTDRMP').val();
                        var codPartida = $('#valCodPartidaGlo').val();
                        var codCapitulo = $('#valCodCapituloGlo').val();
                        var codPrograma = $('#valCodProgramaGlo').val();

                        var url2 = '../digitacion/getGlosas' +
                            '?idTDRMP=' + idTDRMP +
                            '&idDetTDRMP=' + idDetTDRMP +
                            '&codPartida=' + codPartida +
                            '&codCapitulo=' + codCapitulo +
                            '&codPrograma=' + codPrograma;

                        $.get(url2, function (data2) {
                            $('#divTblGlosas').html(data2);
                        })
                    }
                }
            });
        } else {
            dialogAlerta.html('<p>El nº de glosa deben ser 2 dígitos.</p>');
            dialogAlerta.dialog('open');
            dialogAlerta.dialog('option', 'width', 320);
        }
    }

    function updateTextoGlosa(idGloTDRMP) {
        var textoGlosa = $('#inptEditTextoGlosa_' + idGloTDRMP).val();

        if (textoGlosa && textoGlosa.length > 0) {
            var numGlosa = $('#inptEditNumGlosa_' + idGloTDRMP).val();
            var glosa = {
                idGloTDRMP: idGloTDRMP,
                numGlosa: numGlosa,
                textoGlosa: textoGlosa
            };
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: '../digitacion/updateGlosa',
                data: JSON.stringify(glosa),
                dataType: 'json',
                success: function (data) {
                    if (data.codEjec !== "0") {
                        dialogAlerta.html('<p>' + data.msgEjec + '</p>');
                        dialogAlerta.dialog('open');
                        dialogAlerta.dialog('option', 'width', 320);
                    } else {
                        var idTDRMP = $('#valIdFilaEdit').val();
                        var idDetTDRMP = $('#valIdDetTDRMP').val();
                        var codPartida = $('#valCodPartidaGlo').val();
                        var codCapitulo = $('#valCodCapituloGlo').val();
                        var codPrograma = $('#valCodProgramaGlo').val();

                        var url2 = '../digitacion/getGlosas' +
                            '?idTDRMP=' + idTDRMP +
                            '&idDetTDRMP=' + idDetTDRMP +
                            '&codPartida=' + codPartida +
                            '&codCapitulo=' + codCapitulo +
                            '&codPrograma=' + codPrograma;

                        $.get(url2, function (data2) {
                            $('#divTblGlosas').html(data2);
                        })
                    }
                }
            });
        } else {
            dialogAlerta.html('<p>El texto de la glosa no puede ser vacío.</p>');
            dialogAlerta.dialog('open');
            dialogAlerta.dialog('option', 'width', 320);
        }
    }

    function deleteGlosa(idGloTDRMP) {
        $.ajax({
            type: "GET",
            url: '../digitacion/deleteGlosa?idGloTDRMP=' + idGloTDRMP,
            dataType: 'json',
            success: function (data) {
                if (data.codEjec !== "0") {
                    dialogAlerta.html('<p>' + data.msgEjec + '</p>');
                    dialogAlerta.dialog('open');
                    dialogAlerta.dialog('option', 'width', 320);
                } else {
                    var idTDRMP = $('#valIdFilaEdit').val();
                    var idDetTDRMP = $('#valIdDetTDRMP').val();
                    var codPartida = $('#valCodPartidaGlo').val();
                    var codCapitulo = $('#valCodCapituloGlo').val();
                    var codPrograma = $('#valCodProgramaGlo').val();

                    var url2 = '../digitacion/getGlosas' +
                        '?idTDRMP=' + idTDRMP +
                        '&idDetTDRMP=' + idDetTDRMP +
                        '&codPartida=' + codPartida +
                        '&codCapitulo=' + codCapitulo +
                        '&codPrograma=' + codPrograma;

                    $.get(url2, function (data2) {
                        $('#divTblGlosas').html(data2);
                    })
                }
            }
        });
    }
</script>


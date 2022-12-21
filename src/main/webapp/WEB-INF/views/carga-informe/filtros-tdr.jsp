<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-10-01
  Time: 14:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="row" id="divFiltros">
    <div class="col-md-3">
        <div class="form-group">
            <label>Estado SICOGEN</label>
            <select class="form-control" id="selectEstadoSicogen">
                <option value="-1">Todos</option>
                <c:forEach var="estadoSicogen" items="${estadosSicogen}">
                    <c:choose>
                        <c:when test="${estadoSicogen.estadoNombre == 'SIN CARGAR'}">
                            <option value="${estadoSicogen.estadoId}">SIN DIGITAR</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${estadoSicogen.estadoId}">${estadoSicogen.estadoNombre}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-md-2">
        <div class="form-group">
            <label>(*)Ejericicio</label>
            <select class="form-control" id="selectEjercicio">
                <option value="-1" selected>Seleccione Ejercicio</option>
                <c:forEach var="ejercicio" items="${ejercicios}">
                    <option value="${ejercicio.ejercicioId}">${ejercicio.ejercicioNombre}</option>
                </c:forEach>
            </select>
        </div>
    </div>
    <div class="col-md-2">
        <div class="form-group">
            <label>(*)Periodo</label>
            <select class="form-control" id="selectPeriodo">
                <option value="-1">Selecione Periodo</option>
            </select>
        </div>
    </div>
    <div class="col-md-1 align-self-center mt-2">
        <button type="button" class="btn btn-primary" id="btnBuscarCargaTDR">Buscar</button>
    </div>
</div>
<div class="row">
    <div class="col-md-12 text-right">
        <span class="text-muted">Los filtros marcados con * son obligatorios</span>
    </div>
</div>

<script>

    $(document).ready(function(){

       $('#selectEjercicio').change(function() {

           var idEjercicio = this.value;

           if (idEjercicio !== -1) {
               var idInforme = $('#selectInforme').children("option:selected").val();
               $.get( "getPeriodoByInforme?idEjercicio=" + idEjercicio + "&idInforme=" + idInforme, function( data ) {
                   $('#selectPeriodo').empty().append('<option selected="selected" value="-1">Selecione Periodo</option>');

                   for (var i = 0; i < data.length; i++) {
                       var periodo = data[i];
                       $('#selectPeriodo').append('<option value="' + periodo.periodoId + '">' + periodo.periodoNombre + '</option>');
                   }
               });
           } else {
               $('#selectPeriodo').empty().append('<option selected="selected" value="-1">Selecione Periodo</option>');
           }
       })

       $('#btnBuscarCargaTDR').click(function () {
           var idEjercicio = $('#selectEjercicio').children("option:selected").val();
           var idPeriodo = $('#selectPeriodo').children("option:selected").val();
           var dialogValidacion = $('#dialogValidacion');

            if (idEjercicio === "-1") {
                dialogValidacion.html('<p>Debe Seleccionar un Ejercicio para realizar la búsqueda</p>');
                dialogValidacion.dialog('open');
                dialogValidacion.dialog('option', 'width', 320);
                return;
            } else if (idPeriodo === "-1") {
                dialogValidacion.html('<p>Debe Seleccionar un Periodo para realizar la búsqueda</p>');
                dialogValidacion.dialog('open');
                dialogValidacion.dialog('option', 'width', 320);
                return;
            } else if (idPeriodo === "-1") {
                dialogValidacion.html('<p>Debe Seleccionar un Periodo para realizar la búsqueda</p>');
                dialogValidacion.dialog('open');
                dialogValidacion.dialog('option', 'width', 320);
                return;
            } else {
                var idInforme = $('#selectInforme').children("option:selected").val();
                var idEstadoSicogen = $('#selectEstadoSicogen').children("option:selected").val();
                var url = 'obtenerListaTDR?idInforme=' + idInforme + '&idEjercicio=' + idEjercicio + '&idEstadoSicogen=' + idEstadoSicogen + '&idPeriodo=' + idPeriodo;

                $.get( url , function( data ) {
                    $('#divCargaDocumento').hide();
                    $('#contIconografia').hide();
                    $( "#divContenido" ).html( data );
                });
            }
       })
    });


</script>

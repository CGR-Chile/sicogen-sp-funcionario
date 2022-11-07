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
    <div class="col-md-2">
        <div class="form-group">
            <label>Ejericicio</label>
            <select class="form-control" id="cbEjercicio">
                <option value="-1" selected>Seleccione Ejercicio</option>
                <c:forEach var="ejercicio" items="${ejercicios}">
                    <option value="${ejercicio.ejercicioId}">${ejercicio.ejercicioNombre}</option>
                </c:forEach>
            </select>
        </div>
    </div>
</div>
<script>
    $(document).ready(function () {
        $('#cbEjercicio').change(function () {
            var idEjercicio = this.value;

            if (idEjercicio !== '-1') {
                var url = './obtenerArchivoPI?idEjercicio=' + idEjercicio;
                $.get(url, function (data) {
                    $('#divCargaDocumento').hide();
                    $('#divContenido').html(data);
                });
            } else {
                $('#divCargaDocumento').show();
                $('#divContenido').empty();
            }
        })
    });
</script>

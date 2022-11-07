<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-11-24
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<div style="position: absolute; margin-left: 10px;margin-top: 10px;">

    <div style="font: bold 12px sans-serif; float:left;">Ejercicio: <br />
        <select id="slctEjercicioUpdate" class="Selectano" style="width:150px;">
            <c:forEach var="ejer" items="${listaEjercicios}">
                <c:choose>
                    <c:when test="${ejer.ejercicioId == ctaParticular.idEjercicio}">
                        <option value="${ejer.ejercicioId}" selected>${ejer.ejercicioNombre}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${ejer.ejercicioId}">${ejer.ejercicioNombre}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>

    <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Partida: <br />
        <select id="slctPartidaUpdate" class="Selectano" style="width:250px;">
            <option value="-1">Seleccione Partida</option>
            <c:forEach var="part" items="${listaPartidas}">
                <c:choose>
                    <c:when test="${part.idPartida == ctaParticular.idPartida}">
                        <option value="${part.idPartida}" selected>${part.nombrePartida}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${part.idPartida}">${part.nombrePartida}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>

    <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Capítulo: <br/>
        <select id="slctCapituloUpdate" class="Selectano" style="width:250px;">
            <option value="-1">Selecciona Capítulo</option>
            <c:forEach var="cap" items="${listaCapitulos}">
                <c:choose>
                    <c:when test="${cap.idCapitulo == ctaParticular.idCapitulo}">
                        <option value="${cap.idCapitulo}" selected>${cap.nombreCapitulo}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${cap.idCapitulo}">${cap.nombreCapitulo}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>

    <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Programa: <br />
        <select  id="slctProgramaUpdate" class="Selectano" style="width:250px;">
            <option value="-1">Seleccione Programa</option>
            <c:forEach var="prog" items="${listaProgramas}">
                <c:choose>
                    <c:when test="${prog.idPrograma == ctaParticular.idPrograma}">
                        <option value="${prog.idPrograma}" selected>${prog.codPrograma} - ${prog.nombre}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${prog.idPrograma}">${prog.codPrograma} - ${prog.nombre}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select>
    </div>
</div>
<div style="position: absolute; margin-left: 10px;margin-top: 60px;">

    <div style="font: bold 12px sans-serif; float:left;">Código Cuenta: <br />
        <input type="text" class="Inputs" id="codCtaParticularUpdate" style="width:250px;" value="${ctaParticular.codSubtitulo}.${ctaParticular.codItem}.${ctaParticular.codAsignacion}.${ctaParticular.codSubAsignacion}"/>
    </div>

    <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Descripción: <br />
        <input type="text" class="Inputs" id="descCtaParticularUpdate" style="width:400px;" value="${ctaParticular.descripcion}"/>
    </div>

    <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Imputación Presupuestaria: <br>
        <c:choose>
            <c:when test="${ctaParticular.isImputable == 1}">
                <input type="radio" name="pnImpPresupUpd" value="1" class="checkImpUpd" checked> Si<br>
                <input type="radio" name="pnImpPresupUpd" value="0" class="checkImpUpd"> No<br>
            </c:when>
            <c:otherwise>
                <input type="radio" name="pnImpPresupUpd" value="1" class="checkImpUpd"> Si<br>
                <input type="radio" name="pnImpPresupUpd" value="0" class="checkImpUpd" checked> No<br>
            </c:otherwise>
        </c:choose>
    </div>
</div>

<div style="position: absolute; margin-left: 10px;margin-top: 120px;">

    <c:choose>
        <c:when test="${ctaParticular.isDecreto == 1}">
            <div style="font: bold 12px sans-serif; float:left;">Origen: <br>
                <input type="radio" name="checkOrigenCtaPartUpd" value="0" class="checkOrigenCtaPartUpd"> Ley de Presupuesto<br>
                <input type="radio" name="checkOrigenCtaPartUpd" value="1" class="checkOrigenCtaPartUpd" checked> Decreto<br>
            </div>

            <div id="datosDecretoUpdateCtaPart" style="float: left">
                <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Nº de Documento: <br />
                    <input type="text" class="Inputs" id="nroDocumentoCtaPartUpd" style="width:100px;" value="${ctaParticular.numeroDoc}"/>
                </div>

                <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Año: <br />
                    <input type="text" class="Inputs" id="anioDocumentoCtaPartUpd" style="width:50px;" value="${ctaParticular.anioDocumento}"/>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div style="font: bold 12px sans-serif; float:left;">Origen: <br>
                <input type="radio" name="checkOrigenCtaPartUpd" value="0" class="checkOrigenCtaPartUpd" checked> Ley de Presupuesto<br>
                <input type="radio" name="checkOrigenCtaPartUpd" value="1" class="checkOrigenCtaPartUpd"> Decreto<br>
            </div>

            <div id="datosDecretoUpdateCtaPart" style="float: left;display: none;">
                <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Nº de Documento: <br />
                    <input type="text" class="Inputs" id="nroDocumentoCtaPartUpd" style="width:100px;"/>
                </div>

                <div style="margin-left:15px; font: bold 12px sans-serif; float:left;">Año: <br />
                    <input type="text" class="Inputs" id="anioDocumentoCtaPartUpd" style="width:50px;"/>
                </div>
            </div>
        </c:otherwise>
    </c:choose>
</div>
<script>
    $(document).ready(function () {
        $('#slctEjercicioUpdate').change(function () {
            var idEjercicio = this.value;
            var url = './mantenedores/get-partidas?idEjercicio=' + idEjercicio;

            $.get(url, function (data) {
                $('#slctPartidaUpdate').html('<option value="-1">Seleccione Partida</option>');
                $('#slctCapituloUpdate').html('<option value="-1">Seleccione Capítulo</option>');
                $('#slctProgramaUpdate').html('<option value="-1">Seleccione Programa</option>');
                $.each(data, function (key, val) {
                    $('#slctPartidaUpdate').append('<option value="' + val.idPartida + '">' + val.nombrePartida + '</option>')
                });
            });
        });

        $('#slctPartidaUpdate').change(function () {
            var idEjercicio = $('#slctEjercicioUpdate option:selected').val();
            var idPartida = this.value;

            if (idPartida !== '-1') {
                var url = './mantenedores/get-capitulos?idEjercicio=' + idEjercicio + '&idPartida=' + idPartida;

                $.get(url, function (data) {
                    $('#slctCapituloUpdate').html('<option value="-1">Seleccione Capítulo</option>');
                    $('#slctProgramaUpdate').html('<option value="-1">Seleccione Programa</option>');
                    $.each(data, function (key, val) {
                        $('#slctCapituloUpdate').append('<option value="' + val.idCapitulo + '">' + val.nombreCapitulo + '</option>')
                    });
                });
            } else {
                $('#slctCapituloUpdate').html('<option value="-1">Seleccione Capítulo</option>');
                $('#slctProgramaUpdate').html('<option value="-1">Seleccione Programa</option>');
            }
        });

        $('#slctCapituloUpdate').change(function () {
            var idEjercicio = $('#slctEjercicioUpdate option:selected').val();
            var idCapitulo = this.value;

            if (idCapitulo !== '-1') {
                var url = './mantenedores/get-programas?idEjercicio=' + idEjercicio + '&idCapitulo=' + idCapitulo;

                $.get(url, function (data) {
                    $('#slctProgramaUpdate').html('<option value="-1">Seleccione Programa</option>');
                    $.each(data, function (key, val) {
                        $('#slctProgramaUpdate').append('<option value="' + val.idPrograma + '">' + val.codPrograma + ' - ' + val.nombre + '</option>')
                    });
                });
            } else {
                $('#slctProgramaUpdate').html('<option value="-1">Seleccione Programa</option>');
            }
        });

        $('#codCtaParticularUpdate').inputmask('99.99.999.999');
        $('#nroDocumentoCtaPartUpd').numeric({ negative: false });
        $('#anioDocumentoCtaPartUpd').inputmask('2099');

        $("input:radio[name='checkOrigenCtaPartUpd']").change(function() {
            if (this.value === '1') {
                $('#datosDecretoUpdateCtaPart').show();
            } else {
                $('#datosDecretoUpdateCtaPart').hide();
            }
        });
    });
</script>

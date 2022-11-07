<%--
  Created by IntelliJ IDEA.
  User: kibernum
  Date: 2020-10-19
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="py-5">
    <div class="container-fluid">
        <div class="form-group row">
            <div class="col-md-1">
                <label for="inptNumGlosa">Nº Glosa</label>
            </div>
            <div class="col-md-1">
                <input type="text" class="form-control" id="inptNumGlosa">
            </div>
        </div>
        <div class="form-group row">
            <div class="col-md-1">
                <label for="inptTextoGlosa">Glosa</label>
            </div>
            <div class="col-md-9">
                <textarea class="form-control" id="inptTextoGlosa" rows="5"></textarea>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-primary mt-4" id="btnAgregarGlosa">+ Agregar</button>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12" id="divTblGlosas">
                <%@ include file="tabla-det-glosas.jsp" %>
            </div>
        </div>
    </div>
</div>
<div id="dialogAlertaGlosas" title="Alerta"></div>
<script>
    $(document).ready(function () {

        var dialogAlerta = $( "#dialogAlertaGlosas" ).dialog({
            autoOpen: false,
            modal: true,
            buttons: {
                Aceptar: function() {
                    $( this ).dialog( "close" );
                }
            }
        });

        var inptNumGlosa = $('#inptNumGlosa').inputmask('99');

        $('#btnAgregarGlosa').click(function () {
            var inptTextoGlosa = $('#inptTextoGlosa');
            var numGlosa = inptNumGlosa.val();
            var textoGlosa = inptTextoGlosa.val();

            if (!numGlosa || numGlosa.length === 0) {
                dialogAlerta.html('<p>Debe ingresar el número de glosa.</p>');
                dialogAlerta.dialog('open');
                dialogAlerta.dialog('option', 'width', 320);
            } else if (!inptNumGlosa.inputmask('isComplete')) {
                dialogAlerta.html('<p>El nº de glosa deben ser 2 dígitos.</p>');
                dialogAlerta.dialog('open');
                dialogAlerta.dialog('option', 'width', 320);
            } else if (!textoGlosa || textoGlosa.length === 0) {
                dialogAlerta.html('<p>Debe ingresar el texto de la glosa.</p>');
                dialogAlerta.dialog('open');
                dialogAlerta.dialog('option', 'width', 320);
            } else {
                var idTDRMP = $('#valIdFilaEdit').val();
                var idDetTDRMP = $('#valIdDetTDRMP').val();
                var indPresup = $('#valIndPresupGlo').val();
                var codPartida = $('#valCodPartidaGlo').val();
                var codCapitulo = $('#valCodCapituloGlo').val();
                var codPrograma = $('#valCodProgramaGlo').val();
                var nomPartida = $('#valNomPartidaGlo').val();
                var nomCapitulo = $('#valNomCapituloGlo').val();
                var nomPrograma = $('#valNomProgramaGlo').val();

                var glosa = {
                    idTDRMP: idTDRMP,
                    idDetTDRMP: idDetTDRMP,
                    indPresup: indPresup,
                    codPart: codPartida,
                    codCap: codCapitulo,
                    codProg: codPrograma,
                    nomPart: nomPartida,
                    nomCap: nomCapitulo,
                    nomProg: nomPrograma,
                    numGlosa: numGlosa,
                    textoGlosa: textoGlosa
                }

                var url = '../digitacion/createGlosa';

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: url,
                    data: JSON.stringify(glosa),
                    dataType: 'json',
                    success: function (data) {
                        inptNumGlosa.val('');
                        inptTextoGlosa.val('');

                        if (data.codEjec !== "0") {
                            dialogAlerta.html('<p>' + data.msgEjec + '</p>');
                            dialogAlerta.dialog('open');
                            dialogAlerta.dialog('option', 'width', 320);
                        } else {
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
        });
    });
</script>

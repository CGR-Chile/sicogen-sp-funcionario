$( document ).ajaxStart(function() {
    $("#loading-spinner").addClass("is-active");
});

$( document ).ajaxStop(function() {
    $("#loading-spinner").removeClass("is-active");
});

var dialogAlerta;

$(document).ready(function () {

    dialogAlerta = $( "#dialogAlerta" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Aceptar: function() {
                $( this ).dialog( "close" );
            }
        }
    });

    $('#slctTipoInf').change(function () {
        $('#slctPeriodo').html('<option value="-1">Seleccione</option>');
        $('#slctEjercicio').val('-1');
    });

    $('#slctEjercicio').change(function () {
        var idEjercicio = $('#slctEjercicio option:selected').val();

        if (idEjercicio === "-1") {
            $('#slctPeriodo').html('<option value="-1">Seleccione</option>');
        } else {
            var idTipInf = $('#slctTipoInf option:selected').val();

            if (idTipInf === "-1") {
                dialogAlerta.html('<p>Debe Seleccionar un Tipo de Informe</p>');
                dialogAlerta.dialog('open');
            } else {
                var url = '../carga/getPeriodoByInforme' +
                    '?idEjercicio=' + idEjercicio +
                    '&idInforme=' + idTipInf;

                $.get(url, function (data) {
                    console.log('select periodo:')
                    console.log(data)
                    $('#slctPeriodo').empty().append('<option selected="selected" value="-1">Selec. Período</option>');

                    for (var i = 0; i < data.length; i++) {
                        var periodo = data[i];
                        $('#slctPeriodo').append('<option value="' + periodo.periodoId + '">' + periodo.periodoNombre + '</option>');
                    }
                });

            }
        }
    });

    var dialogConfirmacion = $('#dialogConfirmacion').dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Aceptar: function() {
                $.get('buscarAsignacionTDR', function (data) {
                    $('#divTbl').html(data);
                })
                $( this ).dialog( "close" );
            }
        }
    });

    $('#btnCatalogar').click(function () {
        var idInforme = $('#slctTipoInf option:selected').val();
        var idEjercicio = $('#slctEjercicio option:selected').val();
        var idPeriodo = $('#slctPeriodo option:selected').val();

        if (idInforme  === '-1') {
            dialogAlerta.html('<p>Debe Seleccionar un Tipo de Informe</p>');
            dialogAlerta.dialog('open');
        } else if (idEjercicio === '-1') {
            dialogAlerta.html('<p>Debe Seleccionar un Ejercicio</p>');
            dialogAlerta.dialog('open');
        } else if (idPeriodo === '-1') {
            dialogAlerta.html('<p>Debe Seleccionar un Período</p>');
            dialogAlerta.dialog('open');
        } else {

            var selected = [];
            $('#tblInformesSistra input:checked').each(function() {
                selected.push($(this).attr('id'));
            });

            if(selected.length === 0){
                dialogAlerta.html('<p>Debe Seleccionar al menos un Documento</p>');
                dialogAlerta.dialog('open');
            } else {
                var documentos = "";

                for (var i = 0, len = selected.length; i < len; i++) {
                    documentos = documentos + "$" +selected[i];
                }

                var idocs = documentos.split("checkId_").join("");

                var catalogarTDRDTO = {
                    idEjercicio: idEjercicio,
                    idInforme: idInforme,
                    idPeriodo: idPeriodo,
                    idDocs: idocs
                };

                $.ajax({
                    type: "POST",
                    contentType: "application/json",
                    url: 'asignarDocumentosTDR',
                    data: JSON.stringify(catalogarTDRDTO),
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
        }
    });

});
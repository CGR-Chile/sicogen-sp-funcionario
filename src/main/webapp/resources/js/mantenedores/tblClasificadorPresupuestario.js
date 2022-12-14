$( document ).ajaxStart(function() {
    $("#loading-spinner-mant").addClass("is-active");
});

$( document ).ajaxStop(function() {
    $("#loading-spinner-mant").removeClass("is-active");
});

var dialogInfo;
var dialogCreateCuenta;
var dialogCreate;
var dialogEditCuenta;
var dialogUpdate;
var dialogDesCta;
var dialogActCta;
var dialogBitacora;
var rutaImg = $('#rutaImagenes').val();

$(document).ready(function () {

    dialogInfo = $( "#dialogInfo" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Cerrar: function() {
                $( this ).dialog( "close" );
            }
        }
    });

    dialogCreateCuenta = $( "#dialogCreateCuenta" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Cancelar: function() {
                $( this ).dialog( "close" );
            },
            Crear: createCuentaParticular
        }
    });

    dialogCreate = $( "#dialogCreate" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Cerrar: function() {
                loadTable();
                dialogCreateCuenta.dialog('close');
                $( this ).dialog( "close" );
            }
        },
        close: function () {
            loadTable();
            dialogCreateCuenta.dialog('close');
        }
    });

    dialogEditCuenta = $( "#dialogEditCuenta" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Cancelar: function() {
                $( this ).dialog( "close" );
            },
            Actualizar: updateCuentaParticular
        }
    });

    dialogUpdate = $( "#dialogUpdate" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Cerrar: function() {
                loadTable();
                dialogEditCuenta.dialog('close');
                $( this ).dialog( "close" );
            }
        },
        close: function () {
            loadTable();
            dialogEditCuenta.dialog('close');
        }
    });

    dialogDesCta = $( "#dialogDesCta" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Cancelar: function() {
                $( this ).dialog( "close" );
            },
            Desactivar: desactivarCuentaPresup
        }
    });

    dialogActCta = $( "#dialogActCta" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Cancelar: function() {
                $( this ).dialog( "close" );
            },
            Activar: activarCuentaPresup
        }
    });

    dialogBitacora = $( "#dialogBitacora" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Cerrar: function() {
                $( this ).dialog( "close" );
            }
        }
    });

    $('#slctEjercicio').change(function () {
        var idEjercicio = this.value;
        var url = './mantenedores/get-partidas?idEjercicio=' + idEjercicio;

        $.get(url, function (data) {
            $('#slctPartida').html('<option value="-1">Seleccione Partida</option>');
            $('#slctCapitulo').html('<option value="-1">Seleccione Cap??tulo</option>');
            $('#slctPrograma').html('<option value="-1">Todos</option>');
            $.each(data, function (key, val) {
                $('#slctPartida').append('<option value="' + val.idPartida + '">' + val.nombrePartida + '</option>')
            });
        });
    });

    $('#slctPartida').change(function () {
        var idEjercicio = $('#slctEjercicio option:selected').val();
        var idPartida = this.value;

        if (idPartida !== '-1') {
            var url = './mantenedores/get-capitulos?idEjercicio=' + idEjercicio + '&idPartida=' + idPartida;

            $.get(url, function (data) {
                $('#slctCapitulo').html('<option value="-1">Seleccione Cap??tulo</option>');
                $('#slctPrograma').html('<option value="-1">Todos</option>');
                $.each(data, function (key, val) {
                    $('#slctCapitulo').append('<option value="' + val.idCapitulo + '">' + val.nombreCapitulo + '</option>')
                });
            });
        } else {
            $('#slctCapitulo').html('<option value="-1">Seleccione Cap??tulo</option>');
            $('#slctPrograma').html('<option value="-1">Todos</option>');
        }
    });

    $('#slctCapitulo').change(function () {
        var idEjercicio = $('#slctEjercicio option:selected').val();
        var idCapitulo = this.value;

        if (idCapitulo !== '-1') {
            var url = './mantenedores/get-programas?idEjercicio=' + idEjercicio + '&idCapitulo=' + idCapitulo;

            $.get(url, function (data) {
                $('#slctPrograma').html('<option value="-1">Todos</option>');
                $.each(data, function (key, val) {
                    $('#slctPrograma').append('<option value="' + val.idPrograma + '">' + val.codPrograma + ' - ' + val.nombre + '</option>')
                });
            });
        } else {
            $('#slctPrograma').html('<option value="-1">Todos</option>');
        }
    });

    $('#ExpenseTableContainer').jtable({
        title: 'Clasificador Presupuestario',
        selecting: true, //Enable selecting
        paging: true, //Enable paging
        pageSize: 10, //Set page size (default: 10)
        sorting: false, //Enable sorting
        tableId: "tblCuentasParticulares", //Enable sorting
        messages: {
            serverCommunicationError: 'Se ha producido un error al comunicarse con el servidor.',
            loadingMessage: 'Cargando Registros ...',
            noDataAvailable: 'No hay registros!',
            addNewRecord: 'Crear Registro',
            editRecord: 'Editar',
            areYouSure: 'Alerta',
            deleteConfirmation: 'Se eliminar&aacute; este registro. &iquest;Est&aacute; seguro? ',
            save: 'Guardar',
            saving: 'Guardando',
            cancel: 'Cancelar',
            deleteText: 'Eliminar',
            deleting: 'Borrado',
            error: 'Alerta',
            close: 'Cerrar',
            cannotLoadOptionsFor: 'No se puede cargar las opciones para el campo {0}',
            pagingInfo: 'Mostrando: {0}-{1}, de: {2}',
            pageSizeChangeLabel: 'Numero de Registros',
            gotoPageLabel: 'ir a la p&aacute;gina',
            canNotDeletedRecords: '?No se puede eliminar {0} de {1} registro',
            deleteProggress: 'Eliminado {0} de {1} archivos, procesamiento de ...'
        },actions:{
            listAction: './mantenedores/listTblCuentasParticulares'
        },fields:{
            id: { title: 'id', key: true, list: false, create: false, edit:false},
            codPartida: { title: 'PARTIDA', width: '5%'},
            codCapitulo: { title: 'CAPITULO', width: '5%'},
            codPrograma: { title: 'PROGR.', width: '5%'},
            codSubtitulo: { title: 'SUBT??TULO', width: '5%'},
            codItem: { title: 'ITEM', width: '5%'},
            codAsignacion: { title: 'ASIGNACI??N', width: '5%'},
            codSubAsignacion: { title: 'SUBASIGNACI??N', width: '5%'},
            descripcion: { title: 'DESCRIPCION', width: '15%'},
            ejercicio: { title: 'EJERCICIO', width: '5%'},
            folioAnio: { title: 'N?? DOCUMENTO', width: '5%'},
            imputable: { title: 'IMPUTABLE', width: '5%'},
            activo: { title: 'ACTIVO', width: '5%'},
            bitacora: {
                title: 'BITACORA',
                width: '5%',
                display: function (data) {
                    return $('<a href="javascrip:void(0);" onclick="verBitacoraCtaParticular(' + data.record.id + ')">Ver</a>')
                }
            },
            edit: {
                title: '',
                width: '1%',
                display: function (data) {
                    return $('<button title="Editar cuenta" type="button" onclick="getCtaParticularPresup(' + data.record.id + ');" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"><img src="' + rutaImg + '/themes/lightcolor/edit.png" height="15"/></button>');
                }},
            enableDisable: {
                title: '',
                width: '1%',
                display: function (data) {
                    if (data.record.activo === 'SI') {
                        return $('<button title="Desactivar cuenta" type="button" onclick="showDialogDesactivar(' + data.record.id + ');" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"><img src="' + rutaImg + '/themes/lightcolor/delete.png" height="15"/></button>');
                    } else {
                        return $('<button title="Activar cuenta" type="button" onclick="showDialogActivar(' + data.record.id + ');" style="width: 25px;display:inline; background-color:transparent; border-color:transparent;"><img src="' + rutaImg + '/themes/lightcolor/add.png" height="15"/></button>');
                    }
                }
            }
        },
        rowInserted:  function(event,data){

            if (data.record.isDecreto === 1) {
                data.row.css('color','red');
            }
        },
        recordAdded:  function(event,data){},
        recordUpdated:function(event,data){}
    });

    $('#tblCuentasParticulares').attr('style', "font-size: 11px;");

    $('#slctEjercicioCreate').change(function () {
        var idEjercicio = this.value;
        var url = './mantenedores/get-partidas?idEjercicio=' + idEjercicio;

        $.get(url, function (data) {
            $('#slctPartidaCreate').html('<option value="-1">Seleccione Partida</option>');
            $('#slctCapituloCreate').html('<option value="-1">Seleccione Cap??tulo</option>');
            $('#slctProgramaCreate').html('<option value="-1">Seleccione Programa</option>');
            $.each(data, function (key, val) {
                $('#slctPartidaCreate').append('<option value="' + val.idPartida + '">' + val.nombrePartida + '</option>')
            });
        });
    });

    $('#slctPartidaCreate').change(function () {
        var idEjercicio = $('#slctEjercicioCreate option:selected').val();
        var idPartida = this.value;

        if (idPartida !== '-1') {
            var url = './mantenedores/get-capitulos?idEjercicio=' + idEjercicio + '&idPartida=' + idPartida;

            $.get(url, function (data) {
                $('#slctCapituloCreate').html('<option value="-1">Seleccione Cap??tulo</option>');
                $('#slctProgramaCreate').html('<option value="-1">Seleccione Programa</option>');
                $.each(data, function (key, val) {
                    $('#slctCapituloCreate').append('<option value="' + val.idCapitulo + '">' + val.nombreCapitulo + '</option>')
                });
            });
        } else {
            $('#slctCapituloCreate').html('<option value="-1">Seleccione Cap??tulo</option>');
            $('#slctProgramaCreate').html('<option value="-1">Seleccione Programa</option>');
        }
    });

    $('#slctCapituloCreate').change(function () {
        var idEjercicio = $('#slctEjercicioCreate option:selected').val();
        var idCapitulo = this.value;

        if (idCapitulo !== '-1') {
            var url = './mantenedores/get-programas?idEjercicio=' + idEjercicio + '&idCapitulo=' + idCapitulo;

            $.get(url, function (data) {
                $('#slctProgramaCreate').html('<option value="-1">Seleccione Programa</option>');
                $.each(data, function (key, val) {
                    $('#slctProgramaCreate').append('<option value="' + val.idPrograma + '">' + val.codPrograma + ' - ' + val.nombre + '</option>')
                });
            });
        } else {
            $('#slctProgramaCreate').html('<option value="-1">Seleccione Programa</option>');
        }
    });

    $('#codCtaParticularCreate').inputmask('99.99.999.999');
    $('#nroDocumentoCtaPart').numeric({ negative: false });
    $('#anioDocumentoCtaPart').inputmask('2099');

    $("input:radio[name='checkOrigenCtaPart']").change(function() {
        if (this.value === '1') {
            $('#datosDecretoCreateCtaPart').show();
        } else {
            $('#datosDecretoCreateCtaPart').hide();
        }
    });
});

function loadTable() {
    var idPartida = $('#slctPartida option:selected').val();

    if (idPartida !== '-1') {
        var idCapitulo = $('#slctCapitulo option:selected').val();

        if (idCapitulo !== '-1') {
            var idEjercicio = $('#slctEjercicio option:selected').val();
            var idPrograma = $('#slctPrograma option:selected').val();

            $('#ExpenseTableContainer').jtable('load', {
                idEjercicio: idEjercicio,
                idPartida: idPartida,
                idCapitulo: idCapitulo,
                idPrograma: idPrograma
            });
        } else {
            dialogInfo.html('<p>Debe seleccionar un capitulo.</p>')
            dialogInfo.dialog('open');
            dialogInfo.dialog('option', 'width', '320');
        }
    } else {
        dialogInfo.html('<p>Debe seleccionar una partida.</p>')
        dialogInfo.dialog('open');
        dialogInfo.dialog('option', 'width', '320');
    }

}

function openDialogCreateCtaParticular() {
    $('#slctPartidaCreate').val('-1');
    $('#slctCapituloCreate').val('-1');
    $('#slctProgramaCreate').val('-1');
    $('#codCtaParticularCreate').val('');
    $('#descCtaParticularCreate').val('');
    $('#nroDocumentoCtaPart').val('');
    $('#anioDocumentoCtaPart').val('');

    dialogCreateCuenta.dialog('open');
    dialogCreateCuenta.dialog('option', 'width', '1000');
    dialogCreateCuenta.dialog('option', 'height', '300');
}

function createCuentaParticular() {
    var idPartida = $('#slctPartidaCreate option:selected').val();

    if (idPartida !== '-1') {
        var idCapitulo = $('#slctCapituloCreate option:selected').val();

        if (idCapitulo !== '-1') {
            var idEjercicio = $('#slctEjercicioCreate option:selected').val();
            var idPrograma = $('#slctProgramaCreate option:selected').val();

            if ($('#codCtaParticularCreate').inputmask('isComplete')) {
                var descCuenta = $('#descCtaParticularCreate').val();

                if (descCuenta.length > 0) {
                    var codCuenta = $('#codCtaParticularCreate').val();
                    var imputacion = $('.checkImp[name="pnImpPresup"]:checked').val();
                    var isDecreto = $('.checkOrigenCtaPart[name="checkOrigenCtaPart"]:checked').val();
                    var postData = {};

                    postData.idPartida = idPartida;
                    postData.idCapitulo = idCapitulo;
                    postData.idPrograma = idPrograma;
                    postData.idEjercicio = idEjercicio;
                    postData.codCuenta = codCuenta;
                    postData.isImputable = imputacion;
                    postData.descripcion = descCuenta;
                    postData.isDecreto = 0;

                    if (isDecreto === '1') {
                        var numDoc = $('#nroDocumentoCtaPart').val();

                        if (numDoc.length > 0) {
                            if ($('#anioDocumentoCtaPart').inputmask('isComplete')) {
                                postData.isDecreto = 1;
                                postData.numeroDoc = numDoc;
                                postData.anioDocumento = $('#anioDocumentoCtaPart').val();

                                $.ajax({
                                    type: "POST",
                                    contentType: "application/json",
                                    url: './mantenedores/create-cta-particular',
                                    data: JSON.stringify(postData),
                                    dataType: 'json',
                                    success: function (data) {

                                        if (data.codEjec !== "0") {
                                            dialogInfo.html('<p>' + data.msgEjec + '</p>');
                                            dialogInfo.dialog('open');
                                        } else {
                                            dialogCreate.dialog('open');
                                            dialogCreate.dialog('option', 'width', '320');
                                        }
                                    }
                                });
                            } else {
                                dialogInfo.html('<p>Debe ingresar el a??o del decreto.</p>')
                                dialogInfo.dialog('open');
                                dialogInfo.dialog('option', 'width', '320');
                            }
                        } else {
                            dialogInfo.html('<p>Debe ingresar el n?? de documento.</p>')
                            dialogInfo.dialog('open');
                            dialogInfo.dialog('option', 'width', '320');
                        }
                    } else {
                        $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: './mantenedores/create-cta-particular',
                            data: JSON.stringify(postData),
                            dataType: 'json',
                            success: function (data) {

                                if (data.codEjec !== "0") {
                                    dialogInfo.html('<p>' + data.msgEjec + '</p>');
                                    dialogInfo.dialog('open');
                                } else {
                                    dialogCreate.dialog('open');
                                    dialogCreate.dialog('option', 'width', '320');
                                }
                            }
                        });
                    }
                } else {
                    dialogInfo.html('<p>Debe ingresar la descripci??n de la cuenta.</p>')
                    dialogInfo.dialog('open');
                    dialogInfo.dialog('option', 'width', '320');
                }
            } else {
                dialogInfo.html('<p>Debe ingresar un c??digo de cuenta correcto.</p>')
                dialogInfo.dialog('open');
                dialogInfo.dialog('option', 'width', '320');
            }

        } else {
            dialogInfo.html('<p>Debe seleccionar un capitulo.</p>')
            dialogInfo.dialog('open');
            dialogInfo.dialog('option', 'width', '320');
        }
    } else {
        dialogInfo.html('<p>Debe seleccionar una partida.</p>')
        dialogInfo.dialog('open');
        dialogInfo.dialog('option', 'width', '320');
    }
}

function getCtaParticularPresup(id) {
    var url = './mantenedores/get-cta-particular?idCuenta=' + id;

    $.get(url, function (data) {
        dialogEditCuenta.html(data);
        dialogEditCuenta.dialog('open');
        dialogEditCuenta.dialog('option', 'width', '1000');
        dialogEditCuenta.dialog('option', 'height', '300');
    });
}

function updateCuentaParticular() {
    var idPartida = $('#slctPartidaUpdate option:selected').val();

    if (idPartida !== '-1') {
        var idCapitulo = $('#slctCapituloUpdate option:selected').val();

        if (idCapitulo !== '-1') {
            var idEjercicio = $('#slctEjercicioUpdate option:selected').val();
            var idPrograma = $('#slctProgramaUpdate option:selected').val();

            if ($('#codCtaParticularUpdate').inputmask('isComplete')) {
                var descCuenta = $('#descCtaParticularUpdate').val();

                if (descCuenta.length > 0) {
                    var codCuenta = $('#codCtaParticularUpdate').val();
                    var imputacion = $('.checkImpUpd[name="pnImpPresupUpd"]:checked').val();
                    var isDecreto = $('.checkOrigenCtaPartUpd[name="checkOrigenCtaPartUpd"]:checked').val();
                    var postData = {};

                    postData.idPartida = idPartida;
                    postData.idCapitulo = idCapitulo;
                    postData.idPrograma = idPrograma;
                    postData.idEjercicio = idEjercicio;
                    postData.codCuenta = codCuenta;
                    postData.isImputable = imputacion;
                    postData.descripcion = descCuenta;
                    postData.isDecreto = 0;

                    if (isDecreto === '1') {
                        var numDoc = $('#nroDocumentoCtaPartUpd').val();

                        if (numDoc.length > 0) {
                            if ($('#anioDocumentoCtaPartUpd').inputmask('isComplete')) {
                                postData.isDecreto = 1;
                                postData.numeroDoc = numDoc;
                                postData.anioDocumento = $('#anioDocumentoCtaPartUpd').val();

                                $.ajax({
                                    type: "POST",
                                    contentType: "application/json",
                                    url: './mantenedores/update-cta-particular',
                                    data: JSON.stringify(postData),
                                    dataType: 'json',
                                    success: function (data) {

                                        if (data.codEjec !== "0") {
                                            dialogInfo.html('<p>' + data.msgEjec + '</p>');
                                            dialogInfo.dialog('open');
                                        } else {
                                            dialogUpdate.dialog('open');
                                            dialogUpdate.dialog('option', 'width', '320');
                                        }
                                    }
                                });
                            } else {
                                dialogInfo.html('<p>Debe ingresar el a??o del decreto.</p>')
                                dialogInfo.dialog('open');
                                dialogInfo.dialog('option', 'width', '320');
                            }
                        } else {
                            dialogInfo.html('<p>Debe ingresar el n?? de documento.</p>')
                            dialogInfo.dialog('open');
                            dialogInfo.dialog('option', 'width', '320');
                        }
                    } else {
                        $.ajax({
                            type: "POST",
                            contentType: "application/json",
                            url: './mantenedores/update-cta-particular',
                            data: JSON.stringify(postData),
                            dataType: 'json',
                            success: function (data) {

                                if (data.codEjec !== "0") {
                                    dialogInfo.html('<p>' + data.msgEjec + '</p>');
                                    dialogInfo.dialog('open');
                                } else {
                                    dialogUpdate.dialog('open');
                                    dialogUpdate.dialog('option', 'width', '320');
                                }
                            }
                        });
                    }
                } else {
                    dialogInfo.html('<p>Debe ingresar la descripci??n de la cuenta.</p>')
                    dialogInfo.dialog('open');
                    dialogInfo.dialog('option', 'width', '320');
                }
            } else {
                dialogInfo.html('<p>Debe ingresar un c??digo de cuenta correcto.</p>')
                dialogInfo.dialog('open');
                dialogInfo.dialog('option', 'width', '320');
            }

        } else {
            dialogInfo.html('<p>Debe seleccionar un capitulo.</p>')
            dialogInfo.dialog('open');
            dialogInfo.dialog('option', 'width', '320');
        }
    } else {
        dialogInfo.html('<p>Debe seleccionar una partida.</p>')
        dialogInfo.dialog('open');
        dialogInfo.dialog('option', 'width', '320');
    }
}

function showDialogDesactivar(idCuenta) {
    var url = './mantenedores/get-ctas-dependientes?idCuenta=' + idCuenta;

    $.get(url, function (data) {
        $('#divTblCtasDepenDes').html(data);
        dialogDesCta.dialog('open');
        dialogDesCta.dialog('option', 'width', '500');
    });
}

function showDialogActivar(idCuenta) {
    var url = './mantenedores/get-ctas-dependientes?idCuenta=' + idCuenta;

    $.get(url, function (data) {
        $('#divTblCtasDepenAct').html(data);
        dialogActCta.dialog('open');
        dialogActCta.dialog('option', 'width', '500');
    });
}

function desactivarCuentaPresup() {
    $.post('./mantenedores/desactivar-cta-particular').done(function () {
        loadTable();
        dialogDesCta.dialog('close');
    });
}

function activarCuentaPresup() {
    $.post('./mantenedores/activar-cta-particular').done(function () {
        loadTable();
        dialogActCta.dialog('close');
    });
}

function verBitacoraCtaParticular(id) {
    var url = './mantenedores/verBitacoraCatalogoPresup?idCuenta=' + id;

    $.get(url, function (data) {
        dialogBitacora.html(data);
        dialogBitacora.dialog('open');
        dialogBitacora.dialog('option', 'width', '400');
    });
}
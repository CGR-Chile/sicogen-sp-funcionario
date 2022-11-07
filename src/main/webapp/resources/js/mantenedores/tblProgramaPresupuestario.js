function ProgramaPresupuestario(ejercicio, partida, capitulo) {
    $('#ExpenseTableContainer').jtable('load', {ejercicio: ejercicio, partida: partida, capitulo: capitulo});
}

function load(ejercicio, partida, capitulo) {
    partida = $("#cbPartida option:selected").attr('data-cod');
    capitulo = $("#cbCapitulo option:selected").attr('data-cod');
    ProgramaPresupuestario(ejercicio, partida, capitulo);
}

function loadPartida(value) {
    console.log("loadPartida - value: " + value);
    var action = 'mantenedores/loadPartida?ejercicioId=' + $("#cbEjercicio option:selected").val();
    $.ajax({
        url: action,
        type: "POST",
        dataType: "json",
        error: function (data) {
            $('#cbPartida').empty();
            $("#cbPartida").get(0).options[$("#cbPartida").get(0).options.length] = new Option('Selec.', -1);
            document.getElementById('fade').style.display = 'none';
            document.getElementById('formEnvio').style.display = 'none';
        },
        success: function (data) {
            console.log('data: ');
            console.log(data);
            $('#cbPartida').empty();
            $("#cbPartida").get(0).options[$("#cbPartida").get(0).options.length] = new Option('Selec.', -1);
            $.each(data, function (i, itm) {
                var optionSelect = new Option(itm.nombrePartida, itm.codPartida);
                $("#cbPartida").get(0).options[$("#cbPartida").get(0).options.length] = optionSelect;
                optionSelect.setAttribute("data-cod", itm.codPartida);
            });
        }
    });
    console.log("loadPartida - FIN ");
}

function loadCapitulo(value) {
    var idEjercicio = $("#cbEjercicio").val();
    var idPartida = $("#cbPartida option:selected").attr('data-cod');


    var action = 'mantenedores/loadCapitulo?partidaId=' + idPartida + '&idEjercicio=' + idEjercicio;
    $.ajax({
        url: action,
        type: "POST",
        dataType: "json",
        error: function (data) {
            $('#cbCapitulo').empty();
            $("#cbCapitulo").get(0).options[$("#cbCapitulo").get(0).options.length] = new Option('Selec.', -1);
            document.getElementById('fade').style.display = 'none';
            document.getElementById('formEnvio').style.display = 'none';
        },
        success: function (data) {
            $('#cbCapitulo').empty();
            $("#cbCapitulo").get(0).options[$("#cbCapitulo").get(0).options.length] = new Option('Selec.', -1);
            $.each(data, function (i, itm) {
                var optionSelect = new Option(itm.nombreCapitulo, itm.idCapitulo);
                $("#cbCapitulo").get(0).options[$("#cbCapitulo").get(0).options.length] = optionSelect;
                optionSelect.setAttribute("data-cod", itm.codCapitulo);
            });
            $('#ExpenseTableContainer').jtable('load',
                {
                    ejercicio: $("#cbEjercicio option:selected").val(),
                    partida: $("#cbPartida option:selected").val(),
                    capitulo: $("#cbCapitulo option:selected").val()
                });
        }
    });
    console.log("loadCapitulo - FIN: ");
}

$(document).ready(function () {
    $('#ExpenseTableContainer').jtable({
        title: 'Tabla de Programas Presupuestarios',
        selecting: true, //Enable selecting
        paging: true, //Enable paging
        pageSize: 10, //Set page size (default:)
        sorting: false, //Enable sorting
        tableId: "tblPPres",
        messages: {
            serverCommunicationError: 'Se ha producido un error al comunicarse con el servidor.',
            loadingMessage: 'Cargando Registros ...',
            noDataAvailable: 'No hay registros!',
            addNewRecord: 'Crear Partida',
            editRecord: 'Editar',
            areYouSure: '&iquest;Est&aacute; seguro?',
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
            canNotDeletedRecords: 'No se puede eliminar {0} de {1} registro',
            deleteProggress: 'Eliminado {0} de {1} archivos, procesamiento de ...'
        }, actions: {
            listAction: 'mantenedores/listTblPP',
            createAction: 'mantenedores/addTblPP'
            //updateAction: 'updTblPP',
            //deleteAction: 'delTblPP'
        }, fields: {
            idPP: {
                title: 'ID',
                key: true,
                list: false,
                create: false,
                edit: false
            },
            ejercicio: {create: true, type: 'hidden'},
            partida: {
                title: 'Partida', width: '10%',
                input: function (data) {
                    if (data.record) {
                        return '<input type="text" maxlength="2"  name="partida" value="{0}" class="solo-numero numero2"  pattern="[0-9][0-9]" title="Debe ingresar sólo 2 caracteres">'.replace("{0}", data.record.partida);
                    } else {
                        return '<input type="text" maxlength="2"  name="partida" class="solo-numero numero2"  pattern="[0-9][0-9]" title="Debe ingresar sólo 2 caracteres">';
                    }
                }
            },
            capitulo: {title: 'Capitulo', width: '10%', create: false, edit: false},
            programa: {title: 'Programa', width: '10%', create: false, edit: false},
            nombre: {title: 'Nombre', width: '35%'},
            estado: {title: 'Estado', width: '20%', create: false, edit: false},
            bitacora: {
                title: 'Bitacora', width: '10%', create: false, edit: false,
                display: function (data) {
                    if (data.record.programa == " " && data.record.capitulo == " " && data.record.partida != " ") {
                        return $(
                            "<button type='button' onclick='verBitacoraPartida(\"" + data.record.idPP + "\")' style='background-color:transparent; border-color:transparent;'> " +
                            '  <img src="../resources/img/bitacora.png" height="20"/>' +
                            '</button>');
                    } else if (data.record.programa == " " && data.record.capitulo != " " && data.record.partida != " ") {
                        return $(
                            "<button type='button' onclick='verBitacoraCapitulo(\"" + data.record.idPP + "\")' style='background-color:transparent; border-color:transparent;'> " +
                            '  <img src="../resources/img/bitacora.png" height="20"/>' +
                            '</button>');
                    } else {
                        return $(
                            "<button type='button' onclick='verBitacoraPrograma(\"" + data.record.idPP + "\")' style='background-color:transparent; border-color:transparent;'> " +
                            '  <img src="../resources/img/bitacora.png" height="20"/>' +
                            '</button>');
                    }
                }
            },
            niveles: {
                title: 'Niveles', width: '10%', create: false, edit: false,
                display: function (data) {
                    if (data.record.programa == " " && data.record.capitulo == " " && data.record.partida != " ") {
                        return $(
                            "<button type='button' onclick='crearCapitulo(\"" + data.record.idPP + "\")' style='background-color:transparent; border-color:transparent;'> " +
                            '  <img src="../resources/img/agregar_nivel.png" height="10"/>' +
                            '</button>');
                    } else if (data.record.programa == " " && data.record.capitulo != " " && data.record.partida != " ") {
                        return $(
                            "<button type='button' onclick='crearPrograma(\"" + data.record.idPP + "\")' style='background-color:transparent; border-color:transparent;'> " +
                            '  <img src="../resources/img/agregar_nivel.png" height="10"/>' +
                            '</button>');
                    } else {
                        return " ";
                    }
                }
            },
            editar: {
                title: 'Editar', width: '10%', create: false,
                display: function (data) {
                    if (data.record.programa == " " && data.record.capitulo == " " && data.record.partida != " ") {
                        return $(
                            "<button type='button' onclick='editarPartida(\"" + data.record.idPP + "\")' style='background-color:transparent; border-color:transparent;'> " +
                            '  <img src="../resources/jtable/themes/lightcolor/edit.png" height="15"/>' +
                            '</button>');
                    } else if (data.record.programa == " " && data.record.capitulo != " " && data.record.partida != " ") {
                        return $(
                            "<button type='button' onclick='editarCapitulo(\"" + data.record.idPP + "\")' style='background-color:transparent; border-color:transparent;'> " +
                            '  <img src="../resources/jtable/themes/lightcolor/edit.png" height="15"/>' +
                            '</button>');
                    } else {
                        return $(
                            "<button type='button' onclick='editarPrograma(\"" + data.record.idPP + "\",\"" + data.record.programa + "\")' style='background-color:transparent; border-color:transparent;'> " +
                            '  <img src="../resources/jtable/themes/lightcolor/edit.png" height="15"/>' +
                            '</button>');
                    }
                }
            },
            eliminar: {
                title: 'Eliminar', width: '10%', create: false,
                display: function (data) {
                    if (data.record.programa == " " && data.record.capitulo == " " && data.record.partida != " ") {
                        if (data.record.estado == "ACTIVO") {
                            return $(
                                "<button type='button' onclick='eliminarPartida(\"" + data.record.idPP + "\",\"" + data.record.estado + "\"); return false;' style='background-color:transparent; border-color:transparent;'> " +
                                '  <img src="../resources/jtable/themes/lightcolor/delete.png" height="15"/>' +
                                '</button>');
                        }
                    } else if (data.record.programa == " " && data.record.capitulo != " " && data.record.partida != " ") {
                        if (data.record.estado == "ACTIVO") {
                            return $(
                                "<button type='button' onclick='eliminarCapitulo(\"" + data.record.idPP + "\",\"" + data.record.estado + "\"); return false;' style='background-color:transparent; border-color:transparent;'> " +
                                '  <img src="../resources/jtable/themes/lightcolor/delete.png" height="15"/>' +
                                '</button>');
                        }
                    } else {
                        if (data.record.estado == "ACTIVO") {
                            return $(
                                "<button type='button' onclick='eliminarPrograma(\"" + data.record.idPP + "\",\"" + data.record.estado + "\"); return false;' style='background-color:transparent; border-color:transparent;'> " +
                                '  <img src="../resources/jtable/themes/lightcolor/delete.png" height="15"/>' +
                                '</button>');
                        }
                    }
                }
            }
        }, rowInserted: function (event, data) {
            $('#ExpenseTableContainer').jtable('selectRows', data.row);
        }, recordAdded: function (event, data) {
            $('#ExpenseTableContainer').jtable('load', {
                    ejercicio: $("#cbEjercicio option:selected").val(),
                    partida: $("#cbPartida option:selected").val(),
                    capitulo: $("#cbCapitulo option:selected").val()
                }
            );
            console.log($("#cbEjercicio option:selected").val());
            loadPartida($("#cbEjercicio option:selected").val());
        }, recordUpdated: function (event, data) {
            $('#ExpenseTableContainer').jtable('load', {
                ejercicio: $("#cbEjercicio option:selected").val(),
                partida: $("#cbPartida option:selected").val(),
                capitulo: $("#cbCapitulo option:selected").val()
            });
        }, formCreated: function (event, data) {
            data.form.find('input[id="Edit-partida"]').addClass('validate[minSize[2],maxSize[2],required,custom[integer]]');
            data.form.find('#Edit-ejercicio').val($("#cbEjercicio").val());
            data.form.validationEngine('attach', {
                relative: true,
                overflownDIV: "#divOverflown",
                promptPosition: "bottomLeft"
            });
        }, formSubmitting: function (event, data) {
            return data.form.validationEngine('validate');
        }, formClosed: function (event, data) {
            data.form.validationEngine('hide');
            data.form.validationEngine('detach');
        }
    });
    $(document).on("keydown", "input.numero2", function (e) {
        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
            // Allow: Ctrl+A, Command+A
            (e.keyCode === 65 && (e.ctrlKey === true || e.metaKey === true)) ||
            // Allow: home, end, left, right, down, up
            (e.keyCode >= 35 && e.keyCode <= 40)) {
            // let it happen, don't do anything
            return;
        }
        // Ensure that it is a number and stop the keypress
        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
            e.preventDefault();
        }
    });
    $('#tblPPres').css('font-size', '11px');
});

function verBitacoraPartida(idPP) {
    console.log("verBitacoraPartida - idPP: " + idPP);
    var param = '?idPP=' + idPP;
    $.ajax({
        type: "GET",
        url: "mantenedores/verBitacoraPartida" + param,
        dataType: "json",
        complete: function (data) {
            $('#fadePeriodos').remove();
            $('#waitPeriodos').remove();
        }, success: function (data) {
            var tbla = "";
            $('#tblDesactivarCtasCtables  > tbody').empty();
            jQuery.each(data, function (i, val) {
                tbla = tbla + "<tr><td>" + val.estado + "</td><td>" + val.fecha + "</td><td>" + val.usuario + "</td></tr>"
            });
            $('#tblDesactivarCtasCtables > tbody').html(tbla);
            var opt = {
                autoOpen: true,
                modal: true,
                width: 500,
                show: "blind",
                title: "Bitacora de Actualizaciones"
            };
            var popUp = $("#popupBitacoraCuenta").dialog(opt);
            popUp.dialog("open");
            $(".ui-widget-overlay").addClass("overlay");
            $(".overlay").removeClass("ui-widget-overlay");
            $(".ui-icon-closethick").css('background-position', '-32px -192px');
            $(".ui-icon-closethick").css('background-color', '#F2F2F2');
            $(".ui-icon-closethick").css('top', '0px');
            $(".ui-icon-closethick").css('left', '0px');
        }
    });

    var opt = {
        autoOpen: true,
        modal: true,
        width: 505,
        show: "blind",
        title: "Bitácora de Actualizaciones"
    };
    try {
        $(".ui-dialog-content").dialog("close");
    } catch (err) {
    }
    $("#divPopUpAmpliado").css({'left': '0px'});
    var popUp = $("#divPopUpAmpliado").dialog(opt);
    popUp.dialog("open");
    $(".ui-icon-closethick").css('background-position', '-32px -192px');
    $(".ui-icon-closethick").css('background-color', '#F2F2F2');
    $(".ui-icon-closethick").css('top', '0px');
    $(".ui-icon-closethick").css('left', '0px');
    console.log("verBitacoraPartida - FIN ");
}

function verBitacoraCapitulo(idPP) {
    console.log("verBitacoraCapitulo - idPP: " + idPP);
    var param = '?idPP=' + idPP;
    $.ajax({
        type: "GET",
        url: "mantenedores/verBitacoraCapitulo" + param,
        dataType: "json",
        complete: function (data) {
            $('#fadePeriodos').remove();
            $('#waitPeriodos').remove();
        }, success: function (data) {
            var tbla = "";
            $('#tblDesactivarCtasCtables  > tbody').empty();
            jQuery.each(data, function (i, val) {
                tbla = tbla + "<tr><td>" + val.estado + "</td><td>" + val.fecha + "</td><td>" + val.usuario + "</td></tr>"
            });
            $('#tblDesactivarCtasCtables > tbody').html(tbla);
            var opt = {
                autoOpen: true,
                modal: true,
                width: 500,
                show: "blind",
                title: "Bitacora de Actualizaciones"
            };
            var popUp = $("#popupBitacoraCuenta").dialog(opt);
            popUp.dialog("open");
            $(".ui-widget-overlay").addClass("overlay");
            $(".overlay").removeClass("ui-widget-overlay");
            $(".ui-icon-closethick").css('background-position', '-32px -192px');
            $(".ui-icon-closethick").css('background-color', '#F2F2F2');
            $(".ui-icon-closethick").css('top', '0px');
            $(".ui-icon-closethick").css('left', '0px');
        }
    });

    var opt = {
        autoOpen: true,
        modal: true,
        width: 505,
        show: "blind",
        title: "Bitácora de Actualizaciones"
    };

    try {
        $(".ui-dialog-content").dialog("close");
    } catch (err) {
    }

    $("#divPopUpAmpliado").css({'left': '0px'});

    var popUp = $("#divPopUpAmpliado").dialog(opt);
    popUp.dialog("open");
    $(".ui-icon-closethick").css('background-position', '-32px -192px');
    $(".ui-icon-closethick").css('background-color', '#F2F2F2');
    $(".ui-icon-closethick").css('top', '0px');
    $(".ui-icon-closethick").css('left', '0px');
    console.log("verBitacoraCapitulo - FIN: ");
}

function verBitacoraPrograma(idPP) {
    console.log("verBitacoraPrograma - idPP: " + idPP);
    var param = '?idPP=' + idPP;
    $.ajax({
        type: "GET",
        url: "mantenedores/verBitacoraPrograma" + param,
        dataType: "json",
        complete: function (data) {
            $('#fadePeriodos').remove();
            $('#waitPeriodos').remove();
        }, success: function (data) {
            var tbla = "";
            $('#tblDesactivarCtasCtables  > tbody').empty();
            jQuery.each(data, function (i, val) {
                tbla = tbla + "<tr><td>" + val.estado + "</td><td>" + val.fecha + "</td><td>" + val.usuario + "</td></tr>"
            });
            $('#tblDesactivarCtasCtables > tbody').html(tbla);
            var opt = {
                autoOpen: true,
                modal: true,
                width: 500,
                show: "blind",
                title: "Bitacora de Actualizaciones"
            };
            var popUp = $("#popupBitacoraCuenta").dialog(opt);
            popUp.dialog("open");
            $(".ui-widget-overlay").addClass("overlay");
            $(".overlay").removeClass("ui-widget-overlay");
            $(".ui-icon-closethick").css('background-position', '-32px -192px');
            $(".ui-icon-closethick").css('background-color', '#F2F2F2');
            $(".ui-icon-closethick").css('top', '0px');
            $(".ui-icon-closethick").css('left', '0px');
        }
    });

    var opt = {
        autoOpen: true,
        modal: true,
        width: 505,
        show: "blind",
        title: "Bitácora de Actualizaciones"
    };

    try {
        $(".ui-dialog-content").dialog("close");
    } catch (err) {
    }

    $("#divPopUpAmpliado").css({'left': '0px'});

    var popUp = $("#divPopUpAmpliado").dialog(opt);
    popUp.dialog("open");
    $(".ui-icon-closethick").css('background-position', '-32px -192px');
    $(".ui-icon-closethick").css('background-color', '#F2F2F2');
    $(".ui-icon-closethick").css('top', '0px');
    $(".ui-icon-closethick").css('left', '0px');
    console.log("verBitacoraPrograma - FIN: ");
}

function crearCapitulo(idPP) {
    console.log("crearCapitulo - idPP: " + idPP);
    var action = 'mantenedores/getCodigoPartidaById';
    var partida = $('#cbPartida option:selected').text();
    var codPartida = $('#cbPartida option:selected').val();

    $("#txtPartida").val(partida);
    $("#txtCodPartida").val(codPartida);

    document.getElementById('txtCodPartida').style.display = 'none';
    $("#txtCodigoCapitulo").val("");
    $('#txtNombreCapitulo').val("");

    $.ajax({
        type: "POST",
        url: action,
        data: {
            idPP: idPP, idEjercicio: $('#cbEjercicio').val()
        },
        datatype: 'json',
        success: function (data) {
            $('#txtPartida').val(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
        }
    });

    var opt = {
        autoOpen: true,
        modal: true,
        width: 505,
        show: "blind",
        title: "Crear Capitulo"
    };

    try {
        $(".ui-dialog-content").dialog("close");
    } catch (err) {
    }

    $("#divPopUpCapitulo").css({'left': '0px'});

    var popUp = $("#divPopUpCapitulo").dialog(opt);
    popUp.dialog("open");

    $(".ui-icon-closethick").css('background-position', '-32px -192px');
    $(".ui-icon-closethick").css('background-color', '#F2F2F2');
    $(".ui-icon-closethick").css('top', '0px');
    $(".ui-icon-closethick").css('left', '0px');

    $("#Btn_CancelarCrearCapitulo").attr("onclick", "botonCancelarCrearCap()");
    console.log("crearCapitulo - FIN: ");
}

function setCapitulo(idPP) {
    console.log("setCapitulo - idPP: " + idPP);
    idPP = $("#txtCodPartida").val();
    var codPartida = $('#txtPartida').val();
    var codCapitulo = $('#txtCodigoCapitulo').val();
    var nomCapitulo = $('#txtNombreCapitulo').val();
    var ejercicio = $('#cbEjercicio option:selected').val();
    var action = 'mantenedores/addTblCapitulo';
    $.ajax({
        type: "POST",
        url: action,
        data: {
            idPP: idPP,
            partida: codPartida,
            capitulo: codCapitulo,
            nombre: nomCapitulo,
            ejercicio: ejercicio
        },
        datatype: 'json',
        complete: function (data) {
            $('#fadePeriodos').remove();
            $('#waitPeriodos').remove();

        }, success: function (data) {
            console.log(data);
            console.log(data.Message);
            if (data.Result == "ERROR") {
                jAlert(data.Message, "Alerta");
            } else {
                load($('#cbEjercicio').val(), $('#cbPartida').attr('data-cod'), $('#cbCapitulo').attr('data-cod'));
                loadCapitulo($("#cbPartida option:selected").attr('data-cod'));
                $("#divPopUpCapitulo").dialog("close");
                $("#popup_container").dialog("close");
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
            jAlert(data.Message, "Alerta");
        }
    });
    console.log("setCapitulo - FIN: ");
}

function crearPrograma(idPP) {
    console.log("crearPrograma - idPP: " + idPP);
    var partida = $('#cbPartida option:selected').text();
    var capitulo = $('#cbCapitulo option:selected').text();
    $('#txtPartidaPro').val(partida);
    $('#txtCapituloPro').val(capitulo);
    $("#txtCodigoPrograma").val("");
    $('#txtNombrePrograma').val("");
    var action = 'mantenedores/getCodigoPartidaByIdCap';
    $.ajax({
        type: "POST",
        url: action,
        data: {idPP: idPP, idEjercicio: $('#cbEjercicio').val()},
        datatype: 'json',
        success: function (data) {
            $('#txtPartidaPro').val(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
        }
    });

    var action2 = 'mantenedores/getCodigoCapituloById';
    $.ajax({
        type: "POST",
        url: action2,
        data: {idPP: idPP, idEjercicio: $('#cbEjercicio').val()},
        datatype: 'json',
        success: function (data) {
            $('#txtCapituloPro').val(data);
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
        }
    });

    var opt = {
        autoOpen: true,
        modal: true,
        width: 505,
        show: "blind",
        title: "Crear Programa"
    };

    try {
        $(".ui-dialog-content").dialog("close");
    } catch (err) {
    }
    $("#divPopUpPrograma").css({'left': '0px'});
    var popUp = $("#divPopUpPrograma").dialog(opt);
    popUp.dialog("open");

    $(".ui-icon-closethick").css('background-position', '-32px -192px');
    $(".ui-icon-closethick").css('background-color', '#F2F2F2');
    $(".ui-icon-closethick").css('top', '0px');
    $(".ui-icon-closethick").css('left', '0px');
    $("#Btn_crearPrograma").attr("onclick", "setPrograma(\"" + idPP + "\")");
    $("#Btn_CancelarCrearPrograma").attr("onclick", "botonCancelarCrearProg()");
    console.log("crearPrograma - FIN: ");
}

function setPrograma(idPP) {
    console.log("setPrograma - idPP: " + idPP);
    var codigo = $('#txtCodigoPrograma').val();
    var nombre = $('#txtNombrePrograma').val();
    var ejercicio = $('#cbEjercicio').val();
    var action = 'mantenedores/addTblPrograma';
    $.ajax({
        type: "POST",
        url: action,
        data: {
            idPP: idPP,
            programa: codigo,
            nombre: nombre,
            ejercicio: ejercicio
        },
        datatype: 'json',
        complete: function (data) {
            $('#fadePeriodos').remove();
            $('#waitPeriodos').remove();
        }, success: function (data) {
            console.log(data);
            if (data.Result == "ERROR") {
                jAlert(data.Message, "Alerta");
            } else {
                $("#divPopUpPrograma").dialog("close");
                load($('#cbEjercicio').val(), $('#cbPartida').attr('data-cod'), $('#cbCapitulo').attr('data-cod'));
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
        }
    });
    console.log("setPrograma - FIN: ");
}

function editarCapitulo(idPP) {
    console.log("editarCapitulo - idPP: " + idPP);
    document.getElementById('txtCodParCap').style.display = 'none';
    var partida = $('#cbPartida option:selected').text();
    console.log("editarCapitulo - partida: " + partida);
    var par = partida.split("-");
    console.log("editarCapitulo - par[0]: " + par[0]);
    var capitulo = $('#cbCapitulo option:selected').text();
    console.log("editarCapitulo - capitulo: " + capitulo);
    var cap = capitulo.split(" -");
    console.log("editarCapitulo - cap[0]: " + cap[0]);
    $("#txtCodParCap").val(idPP);
    var action = 'mantenedores/getCapituloById';
    $.ajax({
        type: "POST",
        url: action,
        data: {idPP: idPP, idEjercicio: $("#cbEjercicio").val()},
        datatype: 'text',
        success: function (data) {
            $('#txtCodigoPartidaEditar').val(par[0].trim());
            $('#txtCodigoCapituloEditar').val(cap[0].trim());
            $('#txtNombreCapituloEditar').val(data);
            var opt = {
                autoOpen: true,
                modal: true,
                width: 505,
                show: "blind",
                title: "Editar Capitulo"
            };
            try {
                $(".ui-dialog-content").dialog("close");
            } catch (err) {
            }
            $("#divPopUpEditarCapitulo").css({'left': '0px'});
            var popUp = $("#divPopUpEditarCapitulo").dialog(opt);
            popUp.dialog("open");

            $(".ui-icon-closethick").css('background-position', '-32px -192px');
            $(".ui-icon-closethick").css('background-color', '#F2F2F2');
            $(".ui-icon-closethick").css('top', '0px');
            $(".ui-icon-closethick").css('left', '0px');
            $("#Btn_CancelarEditarCapitulo").attr("onclick", "botonCancelarEditarCapitulo()");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
        }
    });
}

function editarPrograma(idPP, codPP) {
    console.log("editarPrograma - idPP: " + idPP);
    console.log("editarPrograma - codPP: " + codPP);
    var action = 'mantenedores/getProgramaById';
    $.ajax({
        type: "POST",
        url: action,
        data: {idPP: idPP, idEjercicio: $("#cbEjercicio").val()},
        datatype: 'text',
        success: function (data) {
            $('#txtPartidaEditPro').val($('#cbPartida option:selected').text().trim());
            $('#txtCapituloEditPro').val($('#cbCapitulo option:selected').text().trim());
            $('#txtCodigoProgramaEditar').val(codPP.trim());
            $('#txtNombreProgramaEditar').val(data);

            var opt = {
                autoOpen: true,
                modal: true,
                width: 505,
                show: "blind",
                title: "Editar Programa"
            };

            try {
                $(".ui-dialog-content").dialog("close");
            } catch (err) {
            }

            $("#divPopUpEditarPrograma").css({'left': '0px'});
            var popUp = $("#divPopUpEditarPrograma").dialog(opt);
            popUp.dialog("open");
            $(".ui-icon-closethick").css('background-position', '-32px -192px');
            $(".ui-icon-closethick").css('background-color', '#F2F2F2');
            $(".ui-icon-closethick").css('top', '0px');
            $(".ui-icon-closethick").css('left', '0px');
            $("#Btn_editarPrograma").attr("onclick", "updPrograma(\"" + idPP + "\")");
            $("#Btn_CancelarEditarPrograma").attr("onclick", "botonCancelarEditarProg()");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
        }
    });
}

function editarPartida(idPP) {
    console.log("editarPartida - idPP: " + idPP);
    var partida = $('#cbPartida option:selected').text();
    console.log("editarPartida - NombrePartida: " + partida);
    document.getElementById('txtCodPar').style.display = 'none';
    var codPartida = partida.split("-");
    var action = 'mantenedores/getPartidaById';
    $.ajax({
        type: "POST",
        url: action,
        data: {idPP: idPP, idEjercicio: $("#cbEjercicio").val()},
        datatype: 'text',
        success: function (data) {
            $('#txtNombrePartida').val(data);
            $("#txtCodigoPartida").val(codPartida[0].trim());
            console.log('entro: ');
            var opt = {
                autoOpen: true,
                modal: true,
                width: 505,
                show: "blind",
                title: "Editar Partida"
            };
            console.log('entro 2: ');
            try {
                $(".ui-dialog-content").dialog("close");
            } catch (err) {
            }
            console.log('entro 3: ');
            $("#divPopUpEditarPartida").css({'left': '0px'});
            var popUp = $("#divPopUpEditarPartida").dialog(opt);
            popUp.dialog("open");
            console.log('entro 4: ');
            $(".ui-icon-closethick").css('background-position', '-32px -192px');
            $(".ui-icon-closethick").css('background-color', '#F2F2F2');
            $(".ui-icon-closethick").css('top', '0px');
            $(".ui-icon-closethick").css('left', '0px');
            $("#Btn_CancelarEditarPartida").attr("onclick", "botonCancelarEditarPartida()");
            console.log("editarPartida - FIN ");
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
        }
    });
}

function updPartida(idPP) {
    console.log("updPartida - idPP: " + idPP);
    idPP = $("#txtCodPar").val();
    nombre = $("#txtNombrePartida").val();
    var action = 'mantenedores/updTblPartida';
    $.ajax({
        type: "POST",
        url: action,
        data: {
            idPP: idPP,
            nombre: nombre
        },
        datatype: 'json',
        complete: function (data) {
            $('#fadePeriodos').remove();
            $('#waitPeriodos').remove();
        }, success: function (data) {
            $("#divPopUpEditarPartida").dialog("close");
            load($('#cbEjercicio').val(), $('#cbPartida').attr('data-cod'), $('#cbCapitulo').attr('data-cod'));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
        }
    });
    console.log("updPartida - FIN ");
}

function updCapitulo(idPP) {
    console.log("updCapitulo - idPP: " + idPP);
    var codigo = $("#txtCodigoCapituloEditar").val();
    console.log("updCapitulo - codigo: " + codigo);
    var nombre = $("#txtNombreCapituloEditar").val();
    console.log("updCapitulo - nombre: " + nombre);
    idPP = $("#txtCodParCap").val();
    console.log("updCapitulo - idPP(2): " + idPP);
    var idPartida = $('#cbPartida option:selected').val();
    console.log("updCapitulo - idPartida: " + idPartida);
    var ejercicio = $('#cbEjercicio option:selected').val();
    console.log("updCapitulo - ejercicio: " + ejercicio);
    document.getElementById('txtCodParCap').style.display = 'none';
    var action = 'mantenedores/updTblCapitulo';
    $.ajax({
        type: "POST",
        url: action,
        data: {idPP: idPP, codigo: codigo, nombre: nombre, idPartida: idPartida, ejercicio: ejercicio},
        datatype: 'json',
        complete: function (data) {
            $('#fadePeriodos').remove();
            $('#waitPeriodos').remove();
        },
        success: function (data) {
            if (data.Result == "ERROR") {
                jAlert(data.Message, "Alerta");
            } else {
                $("#divPopUpEditarCapitulo").dialog("close");
                load($('#cbEjercicio').val(), $('#cbPartida').attr('data-cod'), $('#cbCapitulo').attr('data-cod'));
                loadCapitulo($('#cbPartida').attr('data-cod'));
            }
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
        }
    });
    console.log("updCapitulo - FIN ");
}

function updPrograma(idPP) {
    console.log("updPrograma - idPP: " + idPP);
    var idCapitulo = $('#cbCapitulo option:selected').val();
    console.log("updPrograma - idCapitulo: " + idCapitulo);
    var nombre = $("#txtNombreProgramaEditar").val();
    console.log("updPrograma - nombre: " + nombre);
    var codigo = $("#txtCodigoProgramaEditar").val();
    console.log("updPrograma - codigo: " + codigo);
    var ejercicio = $('#cbEjercicio option:selected').val();
    console.log("updPrograma - ejercicio: " + ejercicio);
    var action = 'mantenedores/updTblPrograma';
    $.ajax({
        type: "POST",
        url: action,
        data: {idPP: idPP, nombre: nombre, codigo: codigo, idCapitulo: idCapitulo, ejercicio: ejercicio},
        datatype: 'json',
        complete: function (data) {
            $('#fadePeriodos').remove();
            $('#waitPeriodos').remove();
        }, success: function (data) {
            $("#divPopUpEditarPrograma").dialog("close");
            //jAlert(data.Message, "Alerta");
            load($('#cbEjercicio').val(), $('#cbPartida').attr('data-cod'), $('#cbCapitulo').attr('data-cod'))
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
        }
    });
    console.log("updPrograma - FIN: ");
}

function eliminarPartida(idPP) {
    console.log("eliminarPartida - idPP: " + idPP);
    var opt = {
        autoOpen: true,
        modal: true,
        width: 505,
        show: "blind",
        title: "Eliminar Partida"
    };
    try {
        $(".ui-dialog-content").dialog("close");
    } catch (err) {
    }
    $("#divPopUpEliminarPartida").css({'left': '0px'});
    var popUp = $("#divPopUpEliminarPartida").dialog(opt);
    popUp.dialog("open");
    $(".ui-icon-closethick").css('background-position', '-32px -192px');
    $(".ui-icon-closethick").css('background-color', '#F2F2F2');
    $(".ui-icon-closethick").css('top', '0px');
    $(".ui-icon-closethick").css('left', '0px');
    $("#Btn_eliminarPartida").attr("onclick", "delPartida(" + idPP + ")");
    $("#Btn_CancelarPartida").attr("onclick", "botonCancelarEliminarPartida()");
    console.log("eliminarPartida - FIN ");
}

function eliminarCapitulo(idPP) {
    console.log("eliminarCapitulo - idPP: " + idPP);
    var opt = {
        autoOpen: true,
        modal: true,
        width: 505,
        show: "blind",
        title: "Eliminar Capitulo"
    };
    try {
        $(".ui-dialog-content").dialog("close");
    } catch (err) {
    }
    $("#divPopUpEliminarCapitulo").css({'left': '0px'});
    var popUp = $("#divPopUpEliminarCapitulo").dialog(opt);
    popUp.dialog("open");
    $(".ui-icon-closethick").css('background-position', '-32px -192px');
    $(".ui-icon-closethick").css('background-color', '#F2F2F2');
    $(".ui-icon-closethick").css('top', '0px');
    $(".ui-icon-closethick").css('left', '0px');
    $("#Btn_eliminarCapitulo").attr("onclick", "delCapitulo(" + idPP + ")");
    $("#Btn_CancelarCapitulo").attr("onclick", "botonCancelarEliminarCapitulo()");
    console.log("eliminarCapitulo - FIN ");
}

function eliminarPrograma(idPP) {
    console.log("eliminarPrograma - idPP: " + idPP);
    var opt = {
        autoOpen: true,
        modal: true,
        width: 505,
        show: "blind",
        title: "Eliminar Programa"
    };
    try {
        $(".ui-dialog-content").dialog("close");
    } catch (err) {
    }
    $("#divPopUpEliminarPrograma").css({'left': '0px'});
    var popUp = $("#divPopUpEliminarPrograma").dialog(opt);
    popUp.dialog("open");
    $(".ui-icon-closethick").css('background-position', '-32px -192px');
    $(".ui-icon-closethick").css('background-color', '#F2F2F2');
    $(".ui-icon-closethick").css('top', '0px');
    $(".ui-icon-closethick").css('left', '0px');
    $("#Btn_eliminarPrograma").attr("onclick", "delPrograma(" + idPP + ")");
    $("#Btn_CancelarPrograma").attr("onclick", "botonCancelarEliminarProg()");
    console.log("eliminarPrograma - FIN ");
}

function partidaEliminado(idPP) {
    var opt = {
        autoOpen: true,
        modal: true,
        width: 505,
        show: "blind",
        title: "Eliminar Partida"
    };
    try {
        $(".ui-dialog-content").dialog("close");
    } catch (err) {
    }
    var popUp = $("#divPopUpEliminarPartidaYaEliminado").dialog(opt);
    popUp.dialog("open");
    $(".ui-icon-closethick").css('background-position', '-32px -192px');
    $(".ui-icon-closethick").css('background-color', '#F2F2F2');
    $(".ui-icon-closethick").css('top', '0px');
    $(".ui-icon-closethick").css('left', '0px');
    $("#Btn_CancelarPartida2").attr("onclick", "botonCancelarEliminarPartida2()");
}

function capituloEliminado(idPP) {
    var opt = {
        autoOpen: true,
        modal: true,
        width: 505,
        show: "blind",
        title: "Eliminar Capitulo"
    };
    try {
        $(".ui-dialog-content").dialog("close");
    } catch (err) {
    }
    var popUp = $("#divPopUpEliminarCapituloYaEliminado").dialog(opt);
    popUp.dialog("open");
    $(".ui-icon-closethick").css('background-position', '-32px -192px');
    $(".ui-icon-closethick").css('background-color', '#F2F2F2');
    $(".ui-icon-closethick").css('top', '0px');
    $(".ui-icon-closethick").css('left', '0px');
    $("#Btn_CancelarCapitulo2").attr("onclick", "botonCancelarEliminarCapitulo2()");
}

function programaEliminado(idPP) {
    var opt = {
        autoOpen: true,
        modal: true,
        width: 505,
        show: "blind",
        title: "Eliminar Programa"
    };
    try {
        $(".ui-dialog-content").dialog("close");
    } catch (err) {
    }
    var popUp = $("#divPopUpEliminarProgramaYaEliminado").dialog(opt);
    popUp.dialog("open");
    $(".ui-icon-closethick").css('background-position', '-32px -192px');
    $(".ui-icon-closethick").css('background-color', '#F2F2F2');
    $(".ui-icon-closethick").css('top', '0px');
    $(".ui-icon-closethick").css('left', '0px');
    $("#Btn_CancelarPrograma2").attr("onclick", "botonCancelarEliminarProg2()");
}

function delPartida(idPP) {
    console.log("delPartida - idPP: " + idPP);
    var action = 'mantenedores/delTblPartida';
    $.ajax({
        type: "POST",
        url: action,
        data: {idPP: idPP},
        datatype: 'json',
        success: function (data) {
            $("#divPopUpEliminarPartida").dialog("close");
            load($('#cbEjercicio').val(), $('#cbPartida').attr('data-cod'), $('#cbCapitulo').attr('data-cod'));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
        }
    });
    console.log("delPartida - FIN ");
}

function delCapitulo(idPP) {
    console.log("delCapitulo - idPP: " + idPP);
    var action = 'mantenedores/delTblCapitulo';
    $.ajax({
        type: "POST",
        url: action,
        data: {idPP: idPP},
        datatype: 'json',
        success: function (data) {
            $("#divPopUpEliminarCapitulo").dialog("close");
            load($('#cbEjercicio').val(), $('#cbPartida').attr('data-cod'), $('#cbCapitulo').attr('data-cod'));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
        }
    });
    console.log("delCapitulo - FIN ");
}

function delPrograma(idPP) {
    console.log("delPrograma - idPP: " + idPP);
    var action = 'mantenedores/delTblPrograma';
    $.ajax({
        type: "POST",
        url: action,
        data: {idPP: idPP},
        datatype: 'json',
        success: function (data) {
            $("#divPopUpEliminarPrograma").dialog("close");
            load($('#cbEjercicio').val(), $('#cbPartida').attr('data-cod'), $('#cbCapitulo').attr('data-cod'));
        },
        error: function (XMLHttpRequest, textStatus, errorThrown) {
            console.log('Error');
        }
    });
    console.log("delPrograma - FIN ");
}

function botonCancelar() {
    console.log("botonCancelar");
    $("#divPopUpEliminarPartida").dialog("close");
    $("#divPopUpEliminarPrograma").dialog("close");
    $("#divPopUpEliminarCapitulo").dialog("close");
    console.log("botonCancelar - FIN ");
}

function botonCancelarEditarPartida() {
    console.log("botonCancelarEditarPartida");
    $("#divPopUpEditarPartida").dialog("close");
    console.log("botonCancelarEditarPartida - FIN");
}

function botonCancelarEditarCapitulo() {
    console.log("botonCancelarEditarCapitulo");
    $("#divPopUpEditarCapitulo").dialog("close");
    console.log("botonCancelarEditarCapitulo - FIN");
}

function botonCancelarEditarProg() {
    console.log("botonCancelarEditarProg");
    $("#divPopUpEditarPrograma").dialog("close");
    console.log("botonCancelarEditarProg - FIN");
}

function botonCancelarCrearProg() {
    console.log("botonCancelarCrearProg");
    $("#divPopUpPrograma").dialog("close");
    console.log("botonCancelarCrearProg - FIN");
}

function botonCancelarCrearCap() {
    console.log("botonCancelarCrearCap");
    $("#divPopUpCapitulo").dialog("close");
    console.log("botonCancelarCrearCap - FIN");
}

function botonCancelarEliminarPartida() {
    $("#divPopUpEliminarPartida").dialog("close");
}

function botonCancelarEliminarPartida2() {
    $("#divPopUpEliminarPartidaYaEliminado").dialog("close");
}

function botonCancelarEliminarCapitulo() {
    $("#divPopUpEliminarCapitulo").dialog("close");
}

function botonCancelarEliminarCapitulo2() {
    $("#divPopUpEliminarCapituloYaEliminado").dialog("close");
}

function botonCancelarEliminarProg() {
    $("#divPopUpEliminarPrograma").dialog("close");
}

function botonCancelarEliminarProg2() {
    $("#divPopUpEliminarProgramaYaEliminado").dialog("close");
}

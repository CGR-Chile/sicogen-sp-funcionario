var codPartDel;
var codCapDel;
var codProgDel;
var codSubtDel;
var codItemDel;
var monedaDel;
var dialogConfirmacionIdent;
var dialogConfirmacionMod;
var dialogEditAsignacion;
var dialogConfirDeleteAsig;
var dialogAddLimFutIdent;
var dialogAddLimFutMod;
var dialogEditLimFut;
var dialogConfirDelLimFut;
var dialogInfoGuardar;
var dialogInfo;
var dialogValidacionInf;

$(document).ready(function () {

    dialogValidacionInf = $('#dialogValidacionInf').dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Aceptar: function() {
                var idFileUpload = $('#valIdFileUpload').val();
                var idSistradoc = $('#valIdSistradoc').val();
                var url = '../validacion/obtenerValidacionTDR' +
                    '?idFileUp=' + idFileUpload +
                    '&idSistradoc=' + idSistradoc;
                $( this ).dialog( "close" );

                window.open(url,'_blank','scrollbars=1,resizable=1,height=650,width=1050');
            },
            Cancelar: function () {
                $( this ).dialog( "close" );
            }
        }
    });

    dialogInfo = $( "#dialogInfo" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Aceptar: function() {
                $( this ).dialog( "close" );
            }
        }
    });

    dialogInfoGuardar = $( "#dialogInfoGuardar" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Aceptar: function() {
                $( this ).dialog( "close" );
                volverBusquedaTDR();
            }
        },
        close: function( event, ui ) {
            volverBusquedaTDR();
        }
    });

    dialogConfirDelLimFut = $( "#dialogConfirDelLimFut" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Cancelar: function() {
                $( this ).dialog( "close" );
            },
            Si: deleteLimiteFuturo
        }
    });

    dialogEditLimFut = $( "#dialogEditLimFut" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Aceptar: editarLimitesPost
        }
    });

    dialogAddLimFutIdent = $( "#dialogAddLimFutIdent" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Aceptar: postLimitesFuturosIdenti,
            Cancelar: function() {$( this ).dialog( "close" );}
        }
    });

    dialogAddLimFutMod = $( "#dialogAddLimFutMod" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Aceptar: postLimitesFuturosModif
        }
    });

    dialogConfirDeleteAsig = $('#dialogConfirDeleteAsig').dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Cancelar: function() {
                $( this ).dialog( "close" );
            },
            Si: deleteAsignacion
        }
    });

    dialogEditAsignacion = $( "#dialogEditAsignacion" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Aceptar: postEditarAsigProyectos
        }
    });

    dialogConfirmacionIdent = $( "#dialogConfirmacionIdent" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Aceptar: function() {
                var postData = {
                    codPart: codPartDel,
                    codCap: codCapDel,
                    codProg: codProgDel,
                    codSubt: codSubtDel,
                    codItem: codItemDel,
                    moneda: monedaDel
                };

                $.post('../digitacion/deleteSeccionDecretoIdentificacion', postData).done(function (data) {
                    if (data.codEjec !== "0") {
                        dialogInfo.html('<p>' + data.msgEjec + '</p>');
                        dialogInfo.dialog('open');
                        dialogInfo.dialog('option', 'width', 320);
                    } else {
                        $.get('../digitacion/getDetalleDigitacionIIIdent', function (data2) {
                            $('#divTblIdent').html(data2);
                        });
                    }
                });

                $( this ).dialog( "close" );
            },
            Cancelar: function() {
                $( this ).dialog( "close" );
            }
        }
    });

    dialogConfirmacionMod = $( "#dialogConfirmacionMod" ).dialog({
        autoOpen: false,
        modal: true,
        buttons: {
            Aceptar: function() {
                var postData = {
                    codPart: codPartDel,
                    codCap: codCapDel,
                    codProg: codProgDel,
                    codSubt: codSubtDel,
                    codItem: codItemDel,
                    moneda: monedaDel
                };

                $.post('../digitacion/deleteSeccionDecretoModificacion', postData).done(function (data) {
                    if (data.codEjec !== "0") {
                        dialogInfo.html('<p>' + data.msgEjec + '</p>');
                        dialogInfo.dialog('open');
                        dialogInfo.dialog('option', 'width', 320);
                    } else {
                        $.get('../digitacion/getDetalleDigitacionIIMod', function (data2) {
                            $('#divTblMod').html(data2);
                        });
                    }
                });

                $( this ).dialog( "close" );
            },
            Cancelar: function() {
                $( this ).dialog( "close" );
            }
        }
    });
    
    $('#selectPartidaIdent').change(function () {
        var codPartida = $('#selectPartidaIdent option:selected').val();

        if (codPartida !== "-1") {
            var url = '../digitacion/getCapitulosDigitacionTDRII?codPartida=' + codPartida;
            $.get(url, function (data) {
                $('#selectCapituloIdent').html('<option value="-1">Seleccione Capítulo</option>');
                $('#selectProgramaIdent').html('<option value="-1">Seleccione Programa</option>');
                $.each(data, function (i, itm) {
                    $('#selectCapituloIdent').append('<option value="' + itm.idCapitulo + '">' + itm.codCapitulo + ' ' + itm.nombre + '</option>');
                })
            })
        } else {
            $('#selectCapituloIdent').html('<option value="-1">Seleccione Capítulo</option>');
            $('#selectProgramaIdent').html('<option value="-1">Seleccione Programa</option>');
        }
    });

    $('#selectPartidaMod').change(function () {
        var codPartida = $('#selectPartidaMod option:selected').val();

        if (codPartida !== "-1") {
            var url = '../digitacion/getCapitulosDigitacionTDRII?codPartida=' + codPartida;
            $.get(url, function (data) {
                $('#selectCapituloMod').html('<option value="-1">Seleccione Capítulo</option>');
                $('#selectProgramaMod').html('<option value="-1">Seleccione Programa</option>');
                $.each(data, function (i, itm) {
                    $('#selectCapituloMod').append('<option value="' + itm.idCapitulo + '">' + itm.codCapitulo + ' ' + itm.nombre + '</option>');
                })
            })
        } else {
            $('#selectCapituloMod').html('<option value="-1">Seleccione Capítulo</option>');
            $('#selectProgramaMod').html('<option value="-1">Seleccione Programa</option>');
        }
    });

    $('#selectCapituloIdent').change(function () {
        var idCapitulo = $('#selectCapituloIdent option:selected').val();

        if (idCapitulo !== "-1") {
            var url = '../digitacion/getProgramasDigitacionTDRII?idCapitulo=' + idCapitulo;
            $.get(url, function (data) {
                $('#selectProgramaIdent').html('<option value="-1">Seleccione Programa</option>');
                $.each(data, function (i, itm) {
                    $('#selectProgramaIdent').append('<option value="' + itm.idPrograma + '">' + itm.codPrograma + ' ' + itm.nombre + '</option>');
                })
            })
        } else {
            $('#selectProgramaIdent').html('<option value="-1">Seleccione Programa</option>');
        }
    });

    $('#selectCapituloMod').change(function () {
        var idCapitulo = $('#selectCapituloMod option:selected').val();

        if (idCapitulo !== "-1") {
            var url = '../digitacion/getProgramasDigitacionTDRII?idCapitulo=' + idCapitulo;
            $.get(url, function (data) {
                $('#selectProgramaMod').html('<option value="-1">Seleccione Programa</option>');
                $.each(data, function (i, itm) {
                    $('#selectProgramaMod').append('<option value="' + itm.idPrograma + '">' + itm.codPrograma + ' ' + itm.nombre + '</option>');
                })
            })
        } else {
            $('#selectProgramaMod').html('<option value="-1">Seleccione Programa</option>');
        }
    });

    $('#selectSubtituloIdent').change(function () {
        var idSubtitulo = $('#selectSubtituloIdent option:selected').val();

        if (idSubtitulo !== "-1") {
            var url = '../digitacion/getItemsBySubTitulo?idSubtitulo=' + idSubtitulo;
            $.get(url, function (data) {
                $('#selectItemIdent').html('<option value="-1">Seleccione Item</option>');
                $.each(data, function (i, itm) {
                    $('#selectItemIdent').append('<option value="' + itm.pkCuenta + '">' + itm.codItem + ' ' + itm.nomCuenta + '</option>');
                })
            })
        } else {
            $('#selectItemIdent').html('<option value="-1">Seleccione Item</option>');
        }
    });

    $('#selectSubtituloMod').change(function () {
        var idSubtitulo = $('#selectSubtituloMod option:selected').val();

        if (idSubtitulo !== "-1") {
            var url = '../digitacion/getItemsBySubTitulo?idSubtitulo=' + idSubtitulo;
            $.get(url, function (data) {
                $('#selectItemMod').html('<option value="-1">Seleccione Item</option>');
                $.each(data, function (i, itm) {
                    $('#selectItemMod').append('<option value="' + itm.pkCuenta + '">' + itm.codItem + ' ' + itm.nomCuenta + '</option>');
                })
            })
        } else {
            $('#selectItemMod').html('<option value="-1">Seleccione Item</option>');
        }
    });
    
    $('#btnAceptarIdent').click(function () {
        var codPartida = $('#selectPartidaIdent option:selected').val();

        if (codPartida !== "-1") {
            var idCapitulo = $('#selectCapituloIdent option:selected').val();

            if (idCapitulo !== "-1") {
                var idPrograma = $('#selectProgramaIdent option:selected').val();

                if (idPrograma !== "-1") {
                    var idSubtitulo = $('#selectSubtituloIdent option:selected').val();

                    if (idSubtitulo !== "-1") {
                        var moneda = $('#selectMonedaIdent option:selected').val();

                        if (moneda !== "-1") {
                            var idItem = $('#selectItemIdent option:selected').val();
                            var idCuenta;

                            if (idItem === "-1") {
                                idCuenta = idSubtitulo;
                            } else {
                                idCuenta = idItem;
                            }

                            var postData = {
                                idPrograma: idPrograma,
                                idCuenta: idCuenta,
                                moneda: moneda
                            };

                            $.post('../digitacion/insertIIDigitacionTDR', postData).done(function (data) {
                                if (data.codEjec !== "0") {
                                    dialogInfo.html('<p>' + data.msgEjec + '</p>');
                                    dialogInfo.dialog('open');
                                    dialogInfo.dialog('option', 'width', 320);
                                } else {
                                    $.get('../digitacion/getDetalleDigitacionIIIdent', function (data2) {
                                        $('#divTblIdent').html(data2);
                                    });
                                }
                            });
                        } else {
                            dialogInfo.html('<p>Debe seleccionar una Moneda</p>');
                            dialogInfo.dialog('open');
                            dialogInfo.dialog('option', 'width', 320);
                        }
                    } else {
                        dialogInfo.html('<p>Debe seleccionar una Subtítulo</p>');
                        dialogInfo.dialog('open');
                        dialogInfo.dialog('option', 'width', 320);
                    }
                } else {
                    dialogInfo.html('<p>Debe seleccionar un programa</p>');
                    dialogInfo.dialog('open');
                    dialogInfo.dialog('option', 'width', 320);
                }
            } else {
                dialogInfo.html('<p>Debe seleccionar un capitulo</p>');
                dialogInfo.dialog('open');
                dialogInfo.dialog('option', 'width', 320);
            }
        } else {
            dialogInfo.html('<p>Debe seleccionar una partida</p>');
            dialogInfo.dialog('open');
            dialogInfo.dialog('option', 'width', 320);
        }
    });

    $('#btnAceptarMod').click(function () {
        var codPartida = $('#selectPartidaMod option:selected').val();

        if (codPartida !== "-1") {
            var idCapitulo = $('#selectCapituloMod option:selected').val();

            if (idCapitulo !== "-1") {
                var idPrograma = $('#selectProgramaMod option:selected').val();

                if (idPrograma !== "-1") {
                    var idSubtitulo = $('#selectSubtituloMod option:selected').val();

                    if (idSubtitulo !== "-1") {
                        var moneda = $('#selectMonedaMod option:selected').val();

                        if (moneda !== "-1") {
                            var idItem = $('#selectItemMod option:selected').val();
                            var idCuenta;

                            if (idItem === "-1") {
                                idCuenta = idSubtitulo;
                            } else {
                                idCuenta = idItem;
                            }

                            var postData = {
                                idPrograma: idPrograma,
                                idCuenta: idCuenta,
                                moneda: moneda
                            };

                            $.post('../digitacion/insertIIDigitacionTDR_modi', postData).done(function (data) {
                                if (data.codEjec !== "0") {
                                    dialogInfo.html('<p>' + data.msgEjec + '</p>');
                                    dialogInfo.dialog('open');
                                    dialogInfo.dialog('option', 'width', 320);
                                } else {
                                    $.get('../digitacion/getDetalleDigitacionIIMod', function (data2) {
                                        $('#divTblMod').html(data2);
                                    });
                                }
                            });
                        } else {
                            dialogInfo.html('<p>Debe seleccionar una Moneda</p>');
                            dialogInfo.dialog('open');
                            dialogInfo.dialog('option', 'width', 320);
                        }
                    } else {
                        dialogInfo.html('<p>Debe seleccionar una Subtítulo</p>');
                        dialogInfo.dialog('open');
                        dialogInfo.dialog('option', 'width', 320);
                    }
                } else {
                    dialogInfo.html('<p>Debe seleccionar un programa</p>');
                    dialogInfo.dialog('open');
                    dialogInfo.dialog('option', 'width', 320);
                }
            } else {
                dialogInfo.html('<p>Debe seleccionar un capitulo</p>');
                dialogInfo.dialog('open');
                dialogInfo.dialog('option', 'width', 320);
            }
        } else {
            dialogInfo.html('<p>Debe seleccionar una partida</p>');
            dialogInfo.dialog('open');
            dialogInfo.dialog('option', 'width', 320);
        }
    });
});

function openDeleteSectionIdentificacion(codPart, codCap, codProg, codSubt, codItem, moneda) {
    codPartDel = codPart;
    codCapDel = codCap;
    codProgDel = codProg;
    codSubtDel = codSubt;
    codItemDel = codItem;
    monedaDel = moneda;

    dialogConfirmacionIdent.dialog('open');
    dialogConfirmacionIdent.dialog('option', 'width', 320);
}

function openDeleteSectionModificacion(codPart, codCap, codProg, codSubt, codItem, moneda) {
    codPartDel = codPart;
    codCapDel = codCap;
    codProgDel = codProg;
    codSubtDel = codSubt;
    codItemDel = codItem;
    monedaDel = moneda;

    dialogConfirmacionMod.dialog('open');
    dialogConfirmacionMod.dialog('option', 'width', 320);
}

function openEditarAsigProyectos(flagIdentificacion, idDecreto, idMIIProyectos, idDetalleMIIProyectos){
    var url = '../digitacion/getEditarAsigProyectos' +
        '?idDecreto=' + idDecreto +
        '&idMIIProyectos=' + idMIIProyectos +
        '&idDetalleMIIProyectos=' + idDetalleMIIProyectos +
        '&flagIdentificacion=' + flagIdentificacion;

    $.get(url, function (data) {
        dialogEditAsignacion.html(data);
        dialogEditAsignacion.dialog('open');
        dialogEditAsignacion.dialog('option', 'width', '400');
    });
}

function postEditarAsigProyectos() {

    var input = '';

    $(".monto-edit-asig").each(function(){
        input = input.concat($(this).attr("name") + "=" + $(this).val() + "&");
    });

    $.post('../digitacion/postEditarAsigProyectos', input).done(function () {
        $.get('../digitacion/getDetalleDigitacionIIIdent', function (data1) {
            $('#divTblIdent').html(data1);

            $.get('../digitacion/getDetalleDigitacionIIMod', function (data2) {
                $('#divTblMod').html(data2);
                dialogEditAsignacion.dialog('close');
            });
        });
    });
}

function openDeleteAsignacion(codPart, codCap, codProg, codSubt, codItem, codigoBIP) {
    $.post('../digitacion/setDeleteProyectAsignacion', {
        codPart: codPart,
        codCap: codCap,
        codProg: codProg,
        codSubt: codSubt,
        codItem: codItem,
        codigoBIP: codigoBIP
    }).done(function () {
        dialogConfirDeleteAsig.dialog('open');
        dialogConfirDeleteAsig.dialog('option', 'width', '320');
    });
}

function deleteAsignacion() {
    $.post('../digitacion/deleteProyectAsignacion').done(function () {
        $.get('../digitacion/getDetalleDigitacionIIIdent', function (data1) {
            $('#divTblIdent').html(data1);

            $.get('../digitacion/getDetalleDigitacionIIMod', function (data2) {
                $('#divTblMod').html(data2);
                dialogConfirDeleteAsig.dialog('close');
            });
        });
    });
}

function getLimitesFuturosIdent(codPart, codCap, codProg, moneda, codigoBIP, dvProyecto) {
    var url = '../digitacion/getLimitesFuturos' +
        '?codigoBIP=' + codigoBIP +
        '&dvProyecto=' + dvProyecto +
        '&moneda=' + moneda +
        '&codPart=' + codPart +
        '&codCap=' + codCap +
        '&codProg=' + codProg;

    $.get(url, function (data) {
        dialogAddLimFutIdent.html(data);
        dialogAddLimFutIdent.dialog('open');
        dialogAddLimFutIdent.dialog('option', 'width', '320');
    })
}

function getLimitesFuturosModif(codPart, codCap, codProg, moneda, codigoBIP, dvProyecto) {
    var url = '../digitacion/getLimitesFuturosMI' +
        '?codigoBIP=' + codigoBIP +
        '&dvProyecto=' + dvProyecto +
        '&moneda=' + moneda +
        '&codPart=' + codPart +
        '&codCap=' + codCap +
        '&codProg=' + codProg;

    $.get(url, function (data) {
        dialogAddLimFutMod.html(data);
        dialogAddLimFutMod.dialog('open');
        dialogAddLimFutMod.dialog('option', 'width', '320');
    })
}

function postLimitesFuturosIdenti(){
    var input = "";

    $(".add-lim-fut-ident").each(function(){
        input = input.concat($(this).attr("name") + "=" + $(this).val() + "&");
    });

    $.post('../digitacion/agregarLimitesFuturos', input).done(function () {
        $.get('../digitacion/getDetalleDigitacionIIIdent', function (data1) {
            $('#divTblIdent').html(data1);

            $.get('../digitacion/getDetalleDigitacionIIMod', function (data2) {
                $('#divTblMod').html(data2);
                dialogAddLimFutIdent.dialog('close');
            });
        });
    });
}

function postLimitesFuturosModif(){
    var input = "";

    $(".add-lim-fut-mod").each(function(){
        input = input.concat($(this).attr("name") + "=" + $(this).val() + "&");
    });

    $.post('../digitacion/agregarLimitesFuturos', input).done(function () {
        $.get('../digitacion/getDetalleDigitacionIIIdent', function (data1) {
            $('#divTblIdent').html(data1);

            $.get('../digitacion/getDetalleDigitacionIIMod', function (data2) {
                $('#divTblMod').html(data2);
                dialogAddLimFutMod.dialog('close');
            });
        });
    });
}

function openEditarLimiteCompromiso(idDecreto, idLimiteCompromiso) {
    var url = '../digitacion/getEditarLimitesFuturos?idDecreto=' + idDecreto + '&idLimiteCompromiso=' + idLimiteCompromiso;

    $.get(url, function (data) {
        dialogEditLimFut.html(data);
        dialogEditLimFut.dialog('open');
        dialogEditLimFut.dialog('option', 'width', '400');
    })

}

function editarLimitesPost() {
    var input = "";

    $(".edit-lim-fut").each(function(){
        input = input.concat($(this).attr("name") + "=" + $(this).val() + "&");
    });

    $.post('../digitacion/editarLimitesPost', input).done(function () {
        $.get('../digitacion/getDetalleDigitacionIIIdent', function (data1) {
            $('#divTblIdent').html(data1);

            $.get('../digitacion/getDetalleDigitacionIIMod', function (data2) {
                $('#divTblMod').html(data2);
                dialogEditLimFut.dialog('close');
            });
        });
    });
}

function openDeleteLimite(codPart, codCap, codProg, codigoBIP) {
    $.post('../digitacion/setDeleteLimiteFuturo', {
        codPart: codPart,
        codCap: codCap,
        codProg: codProg,
        codigoBIP: codigoBIP
    }).done(function () {
        dialogConfirDelLimFut.dialog('open');
        dialogConfirDelLimFut.dialog('option', 'width', '320');
    });

}

function deleteLimiteFuturo() {
    $.post('../digitacion/deleteLimiteFuturo').done(function () {
        $.get('../digitacion/getDetalleDigitacionIIIdent', function (data1) {
            $('#divTblIdent').html(data1);

            $.get('../digitacion/getDetalleDigitacionIIMod', function (data2) {
                $('#divTblMod').html(data2);
                dialogConfirDelLimFut.dialog('close');
            });
        });
    });
}

function guardarDigitacion() {
    if ($('#accordion-mod').children().length > 0 || $('#accordion-ident').children().length > 0) {
        var numDoc = $('#textNumDoc').val();
        var tipoDoc = $('#textTipDoc').val();

        var url = '../digitacion/guardarMPDigitacionTDR' +
            '?numDoc=' + numDoc +
            '&tipoDoc=' + tipoDoc;

        $.get(url, function (data) {
            if (data.codEjec === '0') {
                dialogInfoGuardar.dialog('open');
                dialogInfoGuardar.dialog('option', 'width', 320);
            } else {
                dialogInfo.html('<p>' + data.msgEjec + '</p>');
                dialogInfo.dialog('open');
                dialogInfo.dialog('option', 'width', 320);
            }
        })
    }
}

function volverBusquedaTDR() {
    $.get('../digitacion/volverBusquedaTDR', function (data) {
        $('#divContenido').html(data);
    })
}

function validarDigitacionTDRII() {
    $.post('../digitacion/validarDigitacionTDRII').done(function (data) {
        if (data.codEjec === '0') {
            $('#valIdFileUpload').val(data.newId);
            $('#dialogValidacionInf').html('<p>' + data.msgEjec + '<br>Pulse aceptar para ver reporte de validacion.</p>')
            dialogValidacionInf.dialog('open');
            dialogValidacionInf.dialog('option', 'width', 320);
        } else {
            dialogInfo.html('<p>' + data.msgEjec + '</p>');
            dialogInfo.dialog('open');
            dialogInfo.dialog('option', 'width', 320);
        }
    });
}

function expandCollapse(index, view, pathImg) {

    let id = "expandCollapse_span_"+view+"_"+index;
    let collapse = pathImg+"/collapseall.png";
    let expand = pathImg+"/expandall.png";
    let status = $("#"+id+" img").attr("title");

    if(status == "Expandir"){
        $("#"+id+" img").attr("title","Contraer");
        $("#"+id+" img").attr("alt","Contraer");
        $("#"+id+" img").attr("src",collapse);
    }else{
        $("#"+id+" img").attr("title","Expandir");
        $("#"+id+" img").attr("alt","Expandir");
        $("#"+id+" img").attr("src",expand);
    }

    $("#accordion-"+view+" div span").each(function(idx, el) {
        if($(el).prop("id").startsWith('expandCollapse_span_'+view) && $(el).prop("id")!=id){
            let element = $(el).attr('id');
            $("#"+element+" img").attr("title","Expandir");
            $("#"+element+" img").attr("alt","Expandir");
            $("#"+element+" img").attr("src",expand);
        }
    });
}
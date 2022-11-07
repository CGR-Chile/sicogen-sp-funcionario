$(document).ready(function () {
    $('.tablaCuentas').DataTable({
        "paging": false,
        "searching": false,
        "ordering": false,
        "language": {
            "sProcessing": "Procesando...",
            "sLengthMenu": "Mostrar _MENU_ registros",
            "sZeroRecords": "No se encontraron resultados",
            "sEmptyTable": "Ningún dato disponible en esta tabla",
            "sInfo": "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
            "sInfoEmpty": "Mostrando registros del 0 al 0 de un total de 0 registros",
            "sInfoFiltered": "(filtrado de un total de _MAX_ registros)",
            "sInfoPostFix": "",
            "sSearch": "Buscar:",
            "sUrl": "",
            "sInfoThousands": ",",
            "sLoadingRecords": "Cargando...",
            "oPaginate": {
                "sFirst": "Primero",
                "sLast": "Último",
                "sNext": "Siguiente",
                "sPrevious": "Anterior"
            },
            "oAria": {
                "sSortAscending": ": Activar para ordenar la columna de manera ascendente",
                "sSortDescending": ": Activar para ordenar la columna de manera descendente"
            }
        }
    });

    $('.detalleError').tipr({
        'alt': false,
        'marginAbove': -65,
        'marginBelow': 7,
        'mode': 'above',
        'space': 70,
        'speed': 300
    });

    $('.expandButton').on('click', function () {
        $(".detalleInforme").slideToggle(500);
    });

    var dialogoDescarga = $('#dialogoDescarga').dialog({
        autoOpen: false,
        closeOnEscape: false,
        resizable: false,
        draggable: false,
        open: function (event, ui) {
            $(".ui-dialog-titlebar-close").hide();
        }
    });

    $('#progressbar').progressbar({
        value: false
    });

    $('#excel').click(function () {

        dialogoDescarga.dialog('open');

        var idFileUp = $('#idArchivo').val();

        $.fileDownload('../../downExcelValidacionPI?idFileUp=' + idFileUp, {
            successCallback: function (url) {
                dialogoDescarga.dialog('close');
            },
            failCallback: function (responseHtml, url) {
                dialogoDescarga.dialog('close');
                console.log(responseHtml);
                $.confirm({
                    title: 'Disculpa las molestias...',
                    content: "Ha ocurrido un error interno en el servidor",
                    type: 'red',
                    typeAnimated: true,
                    boxWidth: '30%',
                    useBootstrap: false,
                    buttons: {
                        close: {
                            text: 'Cerrar',
                            action: function () {
                            }
                        }
                    }
                });
            }
        });
    });
});

function toggleDetalle(urlImagenes) {
    $(".detalleInformeDiv").slideToggle(500, function () {
        $(".toggleImg").attr("src",function () {
           return $(".detalleInforme").is(":visible") ? urlImagenes + "notice-alert.png" : urlImagenes + "add.png";
        });
    });
}
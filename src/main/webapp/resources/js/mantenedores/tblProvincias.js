function load() {
    $("Edit-region").prop('disabled', true);
}
function SunProvincias(value) {
    console.log("Paso 1");
    $('#ExpenseTableContainer').jtable('load', {regionId: $("#cbRegion option:selected").val()});
}
function loadTbl(value) {
    SunProvincias(value);
}
$(document).ready(function () {
    $('#ExpenseTableContainer').jtable({
        title: 'Tabla de Provincia',
        selecting: true, //Enable selecting
        paging: true, //Enable paging
        pageSize: 10, //Set page size (default:)
        sorting: false, //Enable sorting
        tableId: "tblProvincias",
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
            canNotDeletedRecords: '¡No se puede eliminar {0} de {1} registro',
            deleteProggress: 'Eliminado {0} de {1} archivos, procesamiento de ...'
        }, actions: {
            listAction: 'mantenedores/listTblProvincias',
            createAction: 'mantenedores/addTblProvincias',
            updateAction: 'mantenedores/updTblProvincias',
            deleteAction: 'mantenedores/delTblProvincias'
        }, fields: {
            id: {title: 'ID', key: true, list: false, create: false, edit: false},
            codigo: {title: 'Codigo', width: '15%'},
            nombre: {title: 'Nombre', width: '30%'},
            region: {title: 'Region', width: '10%', edit: false},
//				visibility: 'hidden',
//				options: function(data) {
//					return $.map($('#cbRegion')[0].options,function(elem){return({Value:elem.value,DisplayText:elem.text});});}
//			},
            anulacion: {edit: false, create: false, title: 'Fecha Anulaci&oacute;n', width: '15%'},
        }, formCreated: function (event, data) {

            data.form.find('input[id="Edit-codigo"]').addClass('validate[maxSize[10],minSize[1],required]');
            data.form.find('input[id="Edit-nombre"]').addClass('validate[maxSize[255],required]');

            data.form.validationEngine('attach', {
                relative: true,
                overflownDIV: "#divOverflown",
                promptPosition: "bottomLeft"
            });
        }, formSubmitting: function (event, data) {
            return data.form.validationEngine('validate');
        },
        rowInserted: function (event, data) {
            $('#ExpenseTableContainer').jtable('selectRows', data.row);
        }, recordAdded: function (event, data) {
            $('#ExpenseTableContainer').jtable('load', {regionId: $("#cbRegion option:selected").val()});
        }, recordUpdated: function (event, data) {
            $('#ExpenseTableContainer').jtable('load',
                {
                    regionId: $("#cbRegion option:selected").val()
                }
            );
        }
    });
    $("#newItem").each(function (i) {
        $(this).attr({'onClick': 'loadInput()'});
    });

    $('#tblProvincias').attr('style', "font-size: 11px;");
});
function loadInput() {
    $("#Edit-region").val($("#cbRegion option:selected").val());
    $("#Edit-region").parent().parent().hide();
}
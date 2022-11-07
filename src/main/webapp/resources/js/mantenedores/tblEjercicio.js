function load() {
    Ejercicio();
}

function Ejercicio() {
    $('#ExpenseTableContainer').jtable('load');
    return true;
}

$(document).ready(function () {
    $('#ExpenseTableContainer').jtable({
        title: 'Tabla de Ejercicio',
        selecting: true, //Enable selecting
        paging: true, //Enable paging
        pageSize: 10, //Set page size (default: 10)
        sorting: false, //Enable sorting
        tableId: "tblEjercicio", //Enable sorting
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
            canNotDeletedRecords: '�No se puede eliminar {0} de {1} registro',
            deleteProggress: 'Eliminado {0} de {1} archivos, procesamiento de ...'
        },
        actions: {
            listAction: 'mantenedores/listTblEjercicio',
            createAction: 'mantenedores/addTblEjercicio',
            updateAction: 'mantenedores/updTblEjercicio',
            deleteAction: 'mantenedores/delTblEjercicio'
        }, fields: {
            areaID: {title: 'ID', key: true, list: false, create: false, edit: false},
            codigo: {title: 'Codigo', list: false, create: false, edit: false, width: '30%'},
            nombre: {title: 'Nombre', width: '15%'},
            anulacion: {title: 'Fecha Anulaci&oacute;n', width: '15%', edit: false, create: false}
        }, rowInserted: function (event, data) {
            $('#ExpenseTableContainer').jtable('selectRows', data.row);
            console.log('inserted' + data);
        }, recordAdded: function (event, data) {
            $('#ExpenseTableContainer').jtable('load');
            console.log('added' + data);
        }, recordUpdated: function (event, data) {
            $('#ExpenseTableContainer').jtable('load');
            console.log('updated' + data);
        },
        formCreated: function (event, data) {
            data.form.find('input[id="Edit-codigo"]').addClass('validate[minSize[4],maxSize[4],required,custom[integer]]');
            data.form.find('input[id="Edit-nombre"]').addClass('validate[minSize[4],maxSize[4],required,custom[integer]]');

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
    $('#ExpenseTableContainer').jtable('load');

    $('#tblEjercicio').attr('style', "font-size: 11px;");
});

function loadData() {

    $('#ExpenseTableContainer').click(function (e) {
        e.preventDefault();
        $('#ExpenseTableContainer').jtable('load', {
            tipoInforme: $("#TipoInforme :selected").val(),
            ejercicio: $("#cbEjercicioC1 :selected").val(),
            regionId: $("#cbRegion :selected").val(),
            comunaId: $("#cbComuna :selected").val()
        });
    });
    $('#LoadRecordsButton').click();
}

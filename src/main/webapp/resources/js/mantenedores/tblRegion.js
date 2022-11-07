
function load(){	
	Region();	
}


function Region(){	
	$('#ExpenseTableContainer').jtable('load');	
	return false;
}


$(document).ready(function() {   
	
	var codigo = $("#Edit-codigo").val();
	var nombre = $("#Edit-nombre").val();
	    		 
	$('#ExpenseTableContainer').jtable({
		title: 'Tabla de Regiones',
		selecting: true, //Enable selecting 
		paging: true, //Enable paging
		pageSize: 20, //Set page size (default: 10)
		sorting: false, //Enable sorting
		messages: {
			serverCommunicationError: 'Se ha producido un error al comunicarse con el servidor.',
			loadingMessage: 'Cargando Registros ...',
			noDataAvailable: 'No hay registros!',
			addNewRecord: 'Crear Registro',
			editRecord: 'Editar',
//			areYouSure: '&iquest;Est&aacute; seguro?',
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
		},actions: {
			listAction: 'listTblRegion',
			createAction: 'addTblRegion',
			updateAction: 'updTblRegion',
			deleteAction: 'delTblRegion'
		},fields: {
			areaID: {title: 'ID',key: true,list: false,create: false,edit: false},
		    codigo: {title: 'Codigo',width: '10%'},
		    nombre: {title: 'Nombre',width: '30%'},
		    anulacion: {title: 'Fecha Anulaci&oacute;n',width: '15%',edit: false,create: false}
		},formCreated: function (event, data) {
			
//			data.form.find('input[id="Edit-codigo"]').addClass('validate[maxSize[10],minSize[1],required]');
// 			data.form.find('input[id="Edit-nombre"]').addClass('validate[maxSize[255],required]');
 			
			data.form.validationEngine('attach', {
				relative: true,
				overflownDIV:"#divOverflown",
				promptPosition:"bottomLeft"
			});
		},formSubmitting: function (event, data) {
			return data.form.validationEngine('validate');
		},
		rowInserted: function (event, data) {
			$('#ExpenseTableContainer').jtable('selectRows', data.row);
		},recordAdded: function(event, data){
			$('#ExpenseTableContainer').jtable('load');
		},recordUpdated: function(event, data){
			$('#ExpenseTableContainer').jtable('load');
		}
	});
	
	load();
	//loadData();
	
});
	

function loadData(){
	$('#ExpenseTableContainer').click(function (e) {
		e.preventDefault();
		$('#ExpenseTableContainer').jtable('load', {
			tipoInforme: $("#TipoInforme :selected").val(),
			ejercicio: $("#cbEjercicioC1 :selected").val(),
			regionId : $("#cbRegion :selected").val(),
			comunaId : $("#cbComuna :selected").val()
    	});
    });
    $('#LoadRecordsButton').click();
}
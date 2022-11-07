function load()
{	
	
	Moneda();
}

function Moneda(){
	$('#ExpenseTableContainer').jtable('load');	
 
	return false;
} 
 
function selectCombo(listCmbTipo)
{

	//$("#tipo :selected").val(2);
	//return 'listCmbTipo';
	//$('select').val(tipo);	
  
}
$(document).ready(function () 
{
	var codigo = $("#Edit-codigo").val();
	var nombre = $("#Edit-nombre").val();
	$('#ExpenseTableContainer').jtable(
	{
		title: 'Tabla de Complemento',
		selecting: true, //Enable selecting 
		paging: true, //Enable paging
		pageSize: 10, //Set page size (default: 10)
		sorting: false, //Enable sorting
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
		actions:{
			listAction	: 'listTblComp', 
			createAction: 'addTblComp',
			updateAction: 'updTblComp', 
			deleteAction: 'delTblComp' 
		},fields: {
			areaID: {title: 'ID',key: true,list: false,create: false,edit: false},
			tipo: {
				title: 'Tipo',
				width: '10%',
				 list: false, 
				options: function(data) 
				{
					return 'listCmbTipo';
 				},
 
			},	 
			tipoComplemento : {title: 'Tipo', create: false,edit: false},
			grupo: {title: 'Grupo',width: '15%'},
			familia: {title: 'Familia',width: '15%'},	
			//tipoComplemento : {title: 'Tipo', create: false,edit: false},
			insumo: {title: 'Insumo',width: '15%'},		
			isValid: {title: 'Fecha Anulaci&oacute;n',width: '15%',edit: false,create: false}
		},rowInserted: function (event, data) {
			$('#ExpenseTableContainer').jtable('selectRows', data.row);
		},recordAdded: function(event, data){
			$('#ExpenseTableContainer').jtable('load');
		},recordUpdated: function(event, data) {
			$('#ExpenseTableContainer').jtable('load');
		},formCreated: function (event, data) {
			
			if (data.formType == "edit"){
 				console.log(data.record.tipoComplemento);
 				console.log(data.record.entidad);			
 	 			$("#Edit-tipo").val(data.record.entidad);
 			}
		}
	});

	load();
});
    
function loadData()
{
	console.log("cargando datos combo");
	$('#ExpenseTableContainer').click(function (e) 
	{
		e.preventDefault();
		$('#ExpenseTableContainer').jtable('load', 
		{
			tipo: $("#tipo :selected").val(2),
		});
	});
	$('#LoadRecordsButton').click();
}
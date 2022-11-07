$(document).ready(function() {
	$("#selectInforme").change(function(){
		$('#divCargaDocumento').show();
		$('#contIconografia').show();
		$('#divContenido').empty();
		$('#divFiltrosSelect').empty();
		var idInforme = $("#selectInforme option:selected").val();
		var informeName = $("#selectInforme option:selected").text();

		if (idInforme !== '-1') {
			var url = "informeCargaSelected?idInforme=" + idInforme + "&informeName=" + informeName;
			$.get( url, function( data ) {
				$( "#divFiltrosSelect" ).html( data );
			});
		}
	});

	$( "#dialogValidacion" ).dialog({
		autoOpen: false,
		modal: true,
		buttons: {
			OK: function() {
				$( this ).dialog( "close" );
			}
		}
	});
});

$( document ).ajaxStart(function() {
	$("#loading-spinner").addClass("is-active");
});

$( document ).ajaxStop(function() {
	$("#loading-spinner").removeClass("is-active");
});

function cargaDigitacionTDR(idDoc, nroDoc, tipDoc, estado, entEmisora, fechaDocumento, FechaRecep) {
	var ejercicio = $('#selectEjercicio option:selected').text();
	var idEjercicio = $('#selectEjercicio option:selected').val();
	var informe = $('#selectInforme option:selected').text();
	var tipoInforme = $('#selectTipoInforme option:selected').text();
	var idInforme = $('#selectInforme option:selected').val();
	var idPeriodo = $('#selectPeriodo').val();
	var url = '../digitacion/obtenerDigitacionTDR?' +
		'nroSistradoc=' + nroDoc +
		'&ejercicio=' + ejercicio +
		'&idEjercicio=' + idEjercicio +
		'&informe=' + informe +
		'&tipoInforme=' + tipoInforme +
		'&idInforme=' + idInforme +
		'&tipDoc=' + tipDoc +
		'&estado=' + estado +
		'&entEmisora=' + entEmisora +
		'&fechaDocumento=' + fechaDocumento +
		'&FechaRecep=' + FechaRecep +
		'&idTblSistradoc=' + idDoc +
		'&idPeriodo=' + idPeriodo;

	$.get(url, function( data ) {
		$( "#divContenido" ).html( data );
	});
}

function realizaReglasCargaPI(){

	var anio = $('#cbEjercicio option:selected').text();

	var action = './uploadFilePI?anio='+anio;
	var form = $("#idFormPI");

	$.ajax({
		url : action,
		type : 'POST',
		data : new FormData(form[0]),
		cache : false,
		contentType : false,
		processData : false,
		success: function(data){
			$('#divContenido').html(data);
		}
	});
}

function verErroresCarga(){
	dialogReglaCarga.dialog('open');
	dialogReglaCarga.dialog('option', 'width', '320');
}

function validarInformePI(){
	$.post('./validarNegocioPI').done(function (data) {
		$('#divContenido').html(data);
	});
}

function volverCargaInformes() {
	$('#selectInforme').val('-1');
	$('#divFiltrosSelect').empty();
	$('#divContenido').empty();
	$('#divCargaDocumento').show();
}
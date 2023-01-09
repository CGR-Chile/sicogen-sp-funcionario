
$(document).ready(function() {
	console.log('ready cargaInforme.js')
	$('#divCaratulaDocumento').hide();

	$("#selectInforme").change(function(){
		$('#contIconografia').show();
		$('#divContenido').empty();
		$('#divFiltrosSelect').empty();
		var idInforme = $("#selectInforme option:selected").val();
		var informeName = $("#selectInforme option:selected").text();
		console.log('idInforme')
		console.log(idInforme)
		console.log('informeName')
		console.log(informeName)
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

	$("#filters-decreto-resolicion-ley").hide();
	$("#filters-caratula-decreto-resolucion").hide();
	$("#selectFilterPanel").change(function () {
		console.log( this.value)
		if (!this.value) {
			$( "#filters-decreto-resolicion-ley" ).hide();
			$( "#filters-caratula-decreto-resolucion" ).hide();
			$('#divCaratulaDocumento').hide();
			$('#divCargaDocumento').show();
		}
		if (this.value === 'decreto-resolicion-ley')  {
			$( "#filters-decreto-resolicion-ley" ).fadeIn("slow");
			$( "#filters-caratula-decreto-resolucion" ).hide();
			$('#divCaratulaDocumento').hide();
			$('#divCargaDocumento').show();
		}
		if (this.value === 'caratula-decreto-resolucion')  {
			$( "#filters-caratula-decreto-resolucion" ).fadeIn("slow");
			$( "#filters-decreto-resolicion-ley" ).hide();
			$('#divCargaDocumento').hide();
			$('#divContenido').empty();
			$('#divCaratulaDocumento').show();
			loadEjerciciosFilter()
		}
	})
	$(".iframe-tab-content").on('click', function() {
		console.log('CLICK TAB')
		$("#filters-decreto-resolicion-ley").hide();
		$("#filters-caratula-decreto-resolucion").hide();
	});

	const dialogFormCaratula = $("#dialog-form-caratula").dialog({
		title: 'Carátula',
		autoOpen: false,
		width: 600,
		modal: true,
		buttons: {
			Cancelar: function() {
				console.log('Cerramos form de caratula');
				$(this).dialog("close");
			},
			Crear: function(payload) {
				const isValid = formCaratula.valid();
				if (isValid) {
					console.log('Ejecutando endpoint...');
					const payload = convertFormToJSON(formCaratula)
					addCaratula(payload, $(this))
				}
				console.log('Llena todos los campos');

			},
		},
		close: function() {
			$(this).dialog("close");
			$('#dialog-form-caratula').closest('.ui-dialog')[0].style.width = 0
			formCaratula[0].reset()
		}
	});

	const formCaratula = dialogFormCaratula.find("form").on("submit", function(event) {
		event.preventDefault();
	});

	$("#openFormCaratula").click(function () {
		console.log('iniciando dialogo')
		dialogFormCaratula.dialog('open');
		// cargar select de ejercicio
		loadEjercicios()
		// cargar select de emisora
		loadEntidadEmisora()

	})

	$('#document-date').datepicker({
		dateFormat: 'dd-mm-yy',
		showOn: 'button'
	}).next('button').button({
		icons: {
			primary: 'ui-icon-calendar'
		}, text:false
	});
	$('#reception-date-cgr').datepicker({
		dateFormat: 'dd-mm-yy',
		showOn: 'button'
	}).next('button').button({
		icons: {
			primary: 'ui-icon-calendar'
		}, text:false
	});
	$('#document-date-edit').datepicker({
		dateFormat: 'dd-mm-yy',
		showOn: 'button'
	}).next('button').button({
		icons: {
			primary: 'ui-icon-calendar'
		}, text:false
	});
	$('#reception-date-cgr-edit').datepicker({
		dateFormat: 'dd-mm-yy',
		showOn: 'button'
	}).next('button').button({
		icons: {
			primary: 'ui-icon-calendar'
		}, text:false
	});
	$('#caratula-form').validate({
		rules: {
			tipoDocumento: {
				required: true,
			},
			nroDocumento: {
				required: true,
				maxlength: 5,
				digits: true
			},
			ejercicioId: {
				required: true,
			},
			fechaDocumento: {
				required: true,
			},
			entidadEmisora: {
				required: true,
			},
			fechaRecepcionCGR: {
				required: true,
			},
			analista: {
				required: true,
			},
			materiaGeneral: {
				required: true,
			},
			materiaEspecifica: {
				required: true,
			}
		}
	});
	$('#caratula-form-edit').validate({
		rules: {
			tipoDocumento: {
				required: true,
			},
			nroDocumento: {
				required: true,
				maxlength: 5,
				digits: true
			},
			ejercicioId: {
				required: true,
			},
			fechaDocumento: {
				required: true,
			},
			entidadEmisora: {
				required: true,
			},
			fechaRecepcionCGR: {
				required: true,
			},
			analista: {
				required: true,
			},
			materiaGeneral: {
				required: true,
			},
			materiaEspecifica: {
				required: true,
			}
		}
	});

	$("#searchCaratulas").on("click", function () {
		const tipoInforme = $("#selectPresupuestoContable").val()
		const tipoDocumento = $("#selectTipoDocumento").val()
		const ejercicio = $("#selectEjercicioFilter").val()
		let queryParams = `?ejercicio=${ejercicio}&tipoInforme=${tipoInforme}`
		if (tipoDocumento) {
			queryParams += `&tipoDocumento=${tipoDocumento}`
		}
		getCaratulas(queryParams)
	})
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
	console.log('form')
	console.log(form)
	$.ajax({
		url : action,
		type : 'POST',
		data : new FormData(form[0]),
		cache : false,
		contentType : false,
		processData : false,
		success: function(data){
			console.log('realizaReglasCargaPI')
			console.log(data)
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

function addCaratula(payload, dialog) {
	const data = { ...payload, ejercicio: { ejercicioId: payload.ejercicioId}, estadoSicogen: 0}
	delete data.ejercicioId
	const settings = {
		url: "../caratula/insertarCaratula",
		method: "POST",
		timeout: 0,
		headers: {
			"Content-Type": "application/json"
		},
		data: JSON.stringify(data),
	};

	$.ajax(settings).done((response) => {
		console.log("caratula creada")
		console.log(response)
		$("#searchCaratulas").click()
		dialog.dialog("close");
		// $('.ui-dialog').each((i, obj) => obj.style.width = 0)
	}).catch(error => {
		console.log(error)
	});

}
function editCaratula(payload, dialog, item) {
	const data = { ...payload, ejercicio: { ejercicioId: payload.ejercicioId}, estadoSicogen: 0}
	delete data.ejercicioId
	const settings = {
		url: `../caratula/updateCaratula/${item.idDocumento}`,
		method: "POST",
		timeout: 0,
		headers: {
			"Content-Type": "application/json"
		},
		data: JSON.stringify(data),
	};

	$.ajax(settings).done((response) => {
		console.log("caratula editada")
		console.log(response)
		$("#searchCaratulas").click()
		dialog.dialog("close");
		// $('.ui-dialog').each((i, obj) => obj.style.width = 0)
	}).catch(error => {
		console.log(error)
	});

}
function loadEjerciciosFilter() {
	$.get("getEjercicios", function (response) {
		const select = $("#selectEjercicioFilter")
		select.find('option').remove();

		const setSelect = (item) => {
			const option = `<option value="${item.ejercicioId}">${item.ejercicioNombre}</option>`
			select.append(option)
		}
		response.forEach(setSelect)
		$("#searchCaratulas").click()
	})
}
function loadEjercicios(item = null) {
	$.get("getEjercicios", function (response) {
		const select = $("#selectEjercicio")
		select.find('option').not(':first').remove();

		const selectEdit = $("#selectEjercicioEdit")
		selectEdit.find('option').not(':first').remove();

		const setSelect = (item) => {
			const option = `<option value="${item.ejercicioId}">${item.ejercicioNombre}</option>`
			select.append(option)
			selectEdit.append(option)
		}
		response.forEach(setSelect)
		if (item) {
			$("#selectEjercicioEdit").val(item.ejercicio.ejercicioId).change();
		}
	})
}
function loadEntidadEmisora(item = null) {
	$.get("getEntidadEmisora", function (response) {
		const select = $("#entidadEmisora")
		select.find('option').not(':first').remove();
		const selectEdit = $("#entidadEmisoraEdit")
		selectEdit.find('option').not(':first').remove();

		const setSelect = (item) => {
			const option = `<option value="${item.nombre}">${item.nombre}</option>`
			select.append(option)
			selectEdit.append(option)
		}
		response.forEach(setSelect)

		if (item) {
			$("#entidadEmisoraEdit").val(item.entidadEmisora).change();
		}
	})
}
function convertFormToJSON(form) {
	return $(form)
		.serializeArray()
		.reduce(function (json, { name, value }) {
			json[name] = value;
			return json;
		}, {});
}

function getCaratulas(queryParams = '') {
	const settings = {
		url: `../caratula/listarCaratulas${queryParams}`,
		method: "GET",
		timeout: 0,
		headers: {
			"Content-Type": "application/json"
		}
	}
	$.ajax(settings).done((response) => {
		console.log("caratulas listadas")
		console.log(response)
		const dataSet = response
		$('#example').DataTable({
			data: dataSet,
			columns: [
				{ data: 'entidadEmisora' },
				{ data: 'tipoDocumento' },
				{ data: 'nroDocumento' },
				{ data: 'anno' },
				{ title: "F. Documento", data: "fechaDocumento"},
				{ title: "F. Recepción CGR", data: 'fechaRecepcionCGR' },
				{ data: 'analista' },
				{
					data: null,
					wrap: true,
					render: function (item) {
						return '<div style="text-align: center"><a class="bitacora-caratula" style="cursor: pointer" title="Ver Bitácora"><img height="16" width="16" src="/sicogen-mf/resources/img/bitacora.png" alt="Ver Bitácora" /></a></div>'
					}
				},
				{
					data: null,
					wrap: true,
					render: function (item) {
						return '<div style="text-align: center">' +
							'<a class="digitacion-caratula" style="cursor: pointer" title="Editar Carátula"><img height="16" width="16" src="/sicogen-mf/resources/img/1_digitar.png" alt="Editar Carátula" /></a>' +
							'<a class="eliminar-caratula" style="cursor: pointer" title="Eliminar Carátula"><img height="16" width="16" src="/sicogen-mf/resources/img/delete.png" alt="Eliminar Carátula" /></a>' +'</div>'
					}
				}
			],
			dom: "rtip",
			language: {
				url: "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
			},
			searching: false,
			paging: true,
			ordering: false,
			info: false,
			destroy: true
		});
		$('#example tbody').off('click').on('click', 'a', function() {
			const table = $('#example').DataTable();
			const tr = $(this).closest('tr');
			const className = $(this)[0].className
			const data = table.row(tr).data();

			if (className === "bitacora-caratula") {
				bitacora(data);
			}
			if (className === "eliminar-caratula") {
				eliminar(data);
			}
			if (className === "digitacion-caratula") {
				digitacion(data);
			}
		});
	}).catch(error => {
		console.log(error)
	});
}

function digitacion(item) {
	console.log('digitacion')
	console.log(item)
	const dialogFormEditCaratula = $("#dialog-form-caratula-edit").dialog({
		title: 'Carátula',
		autoOpen: false,
		width: 600,
		modal: true,
		buttons: {
			Cancelar: function() {
				console.log('Cerramos form edit de caratula');
				$(this).dialog("close");
			},
			Aceptar: function(payload) {
				const isValid = formCaratulaEdit.valid();
				if (isValid) {
					console.log('Ejecutando endpoint...');
					const payload = convertFormToJSON(formCaratulaEdit)
					editCaratula(payload, $(this), item)
				}
				console.log('Llena todos los campos');

			}
		},
		close: function() {
			$(this).dialog("close");
			// $('.ui-dialog').each((i, obj) => obj.style.width = 0)
			formCaratulaEdit[0].reset()
		}
	});
	const formCaratulaEdit = dialogFormEditCaratula.find("form").on("submit", function(event) {
		event.preventDefault();
	});

	dialogFormEditCaratula.dialog('open');
	// cargar select de ejercicio
	loadEjercicios(item)
	// cargar select de emisora
	loadEntidadEmisora(item)
	$("#selectDocumentTypeEdit").val(item.tipoDocumento).change();
	$("#number-document-edit").val(item.nroDocumento);
	$("#document-date-edit").val(item.fechaDocumento);
	$("#reception-date-cgr-edit").val(item.fechaRecepcionCGR);
	$("#analistaEdit").val(item.analista);
	$("#materiaGeneralEdit").val(item.materiaGeneral);
	$("#materiaEspecificaEdit").val(item.materiaEspecifica);
}
function eliminar(item) {
	const dialogDeleteCaratula = $("#dialog-delete-caratula").dialog({
		title: 'Alerta',
		autoOpen: false,
		width: 400,
		modal: true,
		buttons: {
			Cancelar: function() {
				console.log('Cerramos alerta de eliminación');
				dialogDeleteCaratula.dialog("close");
			},
			Aceptar: function() {
				console.log(item)
				console.log('Ejecutando endpoint...');
				const settings = {
					url: `../caratula/eliminarCaratula/${item.idDocumento}`,
					method: "POST",
					timeout: 0,
					headers: {
						"Content-Type": "application/json"
					}
				};

				$.ajax(settings).done((response) => {
					console.log("caratula eliminada")
					console.log(response)
					$("#searchCaratulas").click()
					dialogDeleteCaratula.dialog("close");
				}).catch(error => {
					console.log(error)
				});
			},
		},
		close: function() {
			// $('.ui-dialog').each((i, obj) => obj.style.width = 0)
			dialogDeleteCaratula.dialog("close");
		}
	});
	console.log('eliminar')
	console.log(item)
	dialogDeleteCaratula.dialog('open');
}
function bitacora(item) {
	console.log('bitacora')
	console.log(item)
	console.log('Ejecutando endpoint...');
	const settings = {
		url: `../caratula/verBitacora/${item.idDocumento}`,
		method: "GET",
		timeout: 0,
		headers: {
			"Content-Type": "application/json"
		}
	};

	$.ajax(settings).done((response) => {
		console.log("verBitacora")
		console.log(response)
		const dataSet = response.map(item => {
			if (item.fechaCreacion) {
				item.fechaCreacion = new Date(item.fechaCreacion).toLocaleString()
			}

			if (item.operacion.includes("Crea")) {
				item.operacion = "Creación"
			}
			if (item.operacion.includes("Modi")) {
				item.operacion = "Modificación"
			}

			return item
		})
		$('#table-bitacora').DataTable({
			data: dataSet,
			columns: [
				{ data: 'nombreUsuario' },
				{ data: 'operacion' },
				{ data: 'fechaCreacion' },
			],
			dom: "rtip",
			language: {
				url: "//cdn.datatables.net/plug-ins/1.10.15/i18n/Spanish.json"
			},
			searching: false,
			paging: true,
			ordering: false,
			info: false,
			destroy: true
		});
	}).catch(error => {
		console.log(error)
	});
	const dialogBitacoraCaratula = $("#dialog-bitacora-caratula").dialog({
		title: 'Bitácora',
		autoOpen: false,
		width: 400,
		modal: true,
		buttons: {
			Aceptar: function() {
				dialogBitacoraCaratula.dialog("close");
				//$('.ui-dialog').each((i, obj) => obj.style.width = 0)
			},
		},
		close: function() {
			dialogBitacoraCaratula.dialog("close");
			//$('.ui-dialog').each((i, obj) => obj.style.width = 0)
		}
	});

	dialogBitacoraCaratula.dialog('open');
}
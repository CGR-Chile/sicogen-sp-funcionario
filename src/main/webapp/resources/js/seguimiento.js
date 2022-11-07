var flag ="";


var IdArchivo = "";
function obtieneReporteValIC(idArchivo) {

	IdArchivo = idArchivo;
	$( "#links-informe" ).dialog('open');
}

function cargaInforme(){

	var URL = '../validacion/obtenerValidacionIC?idFileUp='+IdArchivo;
	window.open(URL,'_blank','scrollbars=1,resizable=1,height=650,width=1300');

}

$( function() {
	$("#links-informe").dialog({
		autoOpen: false,
		show: {
//			effect: "blind",
			duration: 100
		},
		hide: {
//			effect: "blind",
			duration: 100
		}
	});
} );


function mostrarDiv(div){
	$('#' + div).show();
}

function ocultarDiv(div){
	$('#' + div).hide();
}

function cargaPartidaInicial(){
	mostrarDiv('registros');

	var idEjercicio = document.getElementById('cbEjercicio').value;
	var url = '../cargacombos/partida?ejercicioId='+idEjercicio;
	//var cf  = new net.ContentLoader(url,comboPartida,"",null,null,null);
}

function cargaPartida(idEjercicio){

	var url = '../cargacombos/partida?ejercicioId='+idEjercicio;
	var cf  = new net.ContentLoader(url,comboPartida,"",null,null,null);
}

function comboPartida(){

	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		$('#contPartida').empty().append(new Option('Seleccione Partida', '-1'));
		for(i=0; i < nodoMensaje.length; i++){
			$('#contPartida').append(
				new Option(
					nodoMensaje[i].getElementsByTagName("PARTIDA_NOMBRE")[0].childNodes[0].nodeValue,
					nodoMensaje[i].getElementsByTagName("PARTIDA_CODIGO")[0].childNodes[0].nodeValue
				)
			);
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}

	$('#contCapitulo').empty().append(new Option('Seleccione Capitulo', '-1'));
}

function cargaMensajeTabla() {
	var objSelect1 =  document.getElementById("contPartida");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Seleccione Partida",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}

function cargaCapitulo(idPartida){

	var idEjercicio = document.getElementById('cbEjercicio').value;
	var url = '../cargacombos/capitulo?ejercicioId='+idEjercicio+ "&idPartida=" + idPartida;
	var cf  = new net.ContentLoader(url,comboCapitulo,"",null,null,null);
}

function comboCapitulo(){

	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listadoCapitulo");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("CAPITULO_CODIGO")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("CAPITULO_NOMBRE")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaMensajeCapitulo();

}

function cargaMensajeCapitulo() {
	var objSelect1 =  document.getElementById("contCapitulo");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Seleccione Capitulo",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}


function cargaInformexTipoReporte2(id)
{
	var reportes = id;//deocument.getElement('contTipoInformes').value();

	if (reportes == 1 ){
		//alert("Reporte Cumplimiento");
		cargaInformexTipo(reportes);
	}else if (reportes == 2){
		cargaInformexTipo(reportes);
	}else if (reportes == 3){
		//alert("Reporte de Informes Reversados");
		cargaInformeRIR(reportes);
	}else if (reportes == 4){
		//alert("Reporte de Correcciones");
		cargaInformeRCORR(reportes);
	}else if (reportes == 5){
		//alert("Reporte de Bitácora de Acciones");
		cargaInformeRB(reportes);
	}
}

function cargaInformexTipo(tipoInforme)
{
	var tpInformes = tipoInforme;

	var url = '../cargacombos/informesTipo?tpInformes='+tipoInforme;
	var cf  = new net.ContentLoader(url,cargaInforme,"",null,null,null);
}


function cargaInforme() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_TIPOINFORME")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("NOMBRE_INFORME")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaMensajeTabla();

}

function cargaMensajeTabla() {
	var objSelect1 =  document.getElementById("contInforme");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Todos",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}

function cargaInformeRIR(tipoInforme)
{
	var tpInformes = tipoInforme;

	var url = '../cargacombos/informesTipo?tpInformes='+tipoInforme;
	var cf  = new net.ContentLoader(url,cargaComboInformeRIR,"",null,null,null);
}


function cargaComboInformeRIR() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_TIPOINFORME")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("NOMBRE_INFORME")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaComboRIR();

}

function cargaComboRIR() {
	var objSelect1 =  document.getElementById("contInformeRIR");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Todos",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}


function cargaInformeRCORR(tipoInforme)
{
	var tpInformes = tipoInforme;

	var url = '../cargacombos/informesTipo?tpInformes='+tipoInforme;
	var cf  = new net.ContentLoader(url,cargaComboInformeRCORR,"",null,null,null);
}


function cargaComboInformeRCORR() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_TIPOINFORME")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("NOMBRE_INFORME")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaComboRCORR();

}

function cargaComboRCORR() {
	var objSelect1 =  document.getElementById("contInformeRCORR");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Todos",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}

function cargaInformeRB(tipoInforme)
{
	var tpInformes = tipoInforme;

	var url = '../cargacombos/informesTipo?tpInformes='+tipoInforme;
	var cf  = new net.ContentLoader(url,cargaComboInformeRB,"",null,null,null);
}


function cargaComboInformeRB() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_TIPOINFORME")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("NOMBRE_INFORME")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaComboRB();

}

function cargaComboRB() {
	var objSelect1 =  document.getElementById("contInformeRB");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Todos",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}


function cargaPeriodo(id){
	var flag = document.getElementById('oculto').value;
	var idEjercicio = id;

	if(flag=='RC'){
		var url = '../cargacombos/listPeriodos?idEjercicio='+idEjercicio;
		var cf  = new net.ContentLoader(url,cargaComboPeriodo,"",null,null,null);
	}else if(flag=='RERE'){
		cargaPeriodoRERE(idEjercicio);
	}else if(flag=='RIR'){
		cargaPeriodoRIR(idEjercicio);
	}else if(flag=='RCORR'){
		cargaPeriodoRCORR(idEjercicio);
	}else if(flag=='RB'){
		cargaPeriodoRB(idEjercicio);
	}
}

function cargaComboPeriodo() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_PERIODO")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("PERIODO_CODIGO")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaCMBPeriodos();

}

function cargaCMBPeriodos(){
	var objSelect1 =  document.getElementById("contPeriodos");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Todos",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}

function cargaPeriodoRERE(id){
	var idEjercicio = id;

	var url = '../cargacombos/listPeriodos?idEjercicio='+idEjercicio;
	var cf  = new net.ContentLoader(url,cargaComboPeriodoRERE,"",null,null,null);
}

function cargaComboPeriodoRERE() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_PERIODO")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("PERIODO_CODIGO")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaCMBPeriodosRERE();

}

function cargaCMBPeriodosRERE(){
	var objSelect1 =  document.getElementById("contPeriodosRERE");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Todos",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}


function cargaPeriodoRIR(id){
	var idEjercicio = id;

	var url = '../cargacombos/listPeriodos?idEjercicio='+idEjercicio;
	var cf  = new net.ContentLoader(url,cargaComboPeriodoRIR,"",null,null,null);
}

function cargaComboPeriodoRIR() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_PERIODO")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("PERIODO_CODIGO")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaCMBPeriodosRIR();

}

function cargaCMBPeriodosRIR(){
	var objSelect1 =  document.getElementById("contPeriodosRIR");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Todos",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}

function cargaPeriodoRCORR(id){
	var idEjercicio = id;

	var url = '../cargacombos/listPeriodos?idEjercicio='+idEjercicio;
	var cf  = new net.ContentLoader(url,cargaComboPeriodoRCORR,"",null,null,null);
}

function cargaComboPeriodoRCORR() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_PERIODO")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("PERIODO_CODIGO")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaCMBPeriodosRCORR();

}

function cargaCMBPeriodosRCORR(){
	var objSelect1 =  document.getElementById("contPeriodosRCORR");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Todos",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}

function cargaPeriodoRCORR(id){
	var idEjercicio = id;

	var url = '../cargacombos/listPeriodos?idEjercicio='+idEjercicio;
	var cf  = new net.ContentLoader(url,cargaComboPeriodoRCORR,"",null,null,null);
}

function cargaComboPeriodoRCORR() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_PERIODO")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("PERIODO_CODIGO")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaCMBPeriodosRCORR();

}

function cargaCMBPeriodosRCORR(){
	var objSelect1 =  document.getElementById("contPeriodosRCORR");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Todos",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}


function cargaPeriodoRB(id){
	var idEjercicio = id;

	var url = '../cargacombos/listPeriodos?idEjercicio='+idEjercicio;
	var cf  = new net.ContentLoader(url,cargaComboPeriodoRB,"",null,null,null);
}

function cargaComboPeriodoRB() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_PERIODO")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("PERIODO_CODIGO")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaCMBPeriodosRB();

}

function cargaCMBPeriodosRB(){
	var objSelect1 =  document.getElementById("contPeriodosRB");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Todos",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}


function cargaPartida(id) {
	var idEjercicio = id;
	var flag = document.getElementById('oculto').value;

	if(flag=='RC'){
		var url = '../cargacombos/listPartidas?idEjercicio='+idEjercicio;
		var cf  = new net.ContentLoader(url,cargaComboPartidas,"",null,null,null);
	}else if(flag=='RERE'){
		cargaPartidaRERE(idEjercicio);
	}else if(flag=='RIR'){
		cargaPartidaRIR(idEjercicio);
	}else if(flag=='RCORR'){
		cargaPartidaRCORR(idEjercicio);
	}else if(flag=='RB'){
		cargaPartidaRIB(idEjercicio);
	}
}

function cargaComboPartidas() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_PARTIDA")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("PARTIDA_CODIGO")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaCMBPartidas();

}

function cargaCMBPartidas(){
	var objSelect1 =  document.getElementById("contPartidas");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Seleccione Partida",0);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}

function cargaPartidaRERE(id) {
	var idEjercicio = id;

	var url = '../cargacombos/listPartidas?idEjercicio='+idEjercicio;
	var cf  = new net.ContentLoader(url,cargaComboPartidasRERE,"",null,null,null);
}

function cargaComboPartidasRERE() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_PARTIDA")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("PARTIDA_CODIGO")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaCMBPartidasRERE();

}

function cargaCMBPartidasRERE(){
	var objSelect1 =  document.getElementById("contPartidasRERE");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Seleccione Partida",0);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}


function cargaPartidaRIR(id) {
	var idEjercicio = id;

	var url = '../cargacombos/listPartidas?idEjercicio='+idEjercicio;
	var cf  = new net.ContentLoader(url,cargaComboPartidasRIR,"",null,null,null);
}

function cargaComboPartidasRIR() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_PARTIDA")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("PARTIDA_CODIGO")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaCMBPartidasRIR();

}

function cargaCMBPartidasRIR(){
	var objSelect1 =  document.getElementById("contPartidasRIR");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Seleccione Partida",0);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}


function cargaPartidaRCORR(id) {
	var idEjercicio = id;

	var url = '../cargacombos/listPartidas?idEjercicio='+idEjercicio;
	var cf  = new net.ContentLoader(url,cargaComboPartidasRCORR,"",null,null,null);
}

function cargaComboPartidasRCORR() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_PARTIDA")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("PARTIDA_CODIGO")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaCMBPartidasRCORR();

}

function cargaCMBPartidasRCORR(){
	var objSelect1 =  document.getElementById("contPartidasRCORR");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Seleccione Partida",0);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}

function cargaPartidaRIB(id) {
	var idEjercicio = id;

	var url = '../cargacombos/listPartidas?idEjercicio='+idEjercicio;
	var cf  = new net.ContentLoader(url,cargaComboPartidasRIB,"",null,null,null);
}

function cargaComboPartidasRIB() {
	var datos  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datos.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_PARTIDA")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("PARTIDA_CODIGO")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaCMBPartidasRIB();

}

function cargaCMBPartidasRIB(){
	var objSelect1 =  document.getElementById("contPartidasRB");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Seleccione Partida",0);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}

//Combo Informes
function cargarInformesPeriodoRC(id) {
	var idPeriodo = id;
	var flag = document.getElementById('oculto').value;

	if(flag=='RC'){
		var url = '../cargacombos/listInformesPeriodo?idPeriodo='+idPeriodo;
		var cf  = new net.ContentLoader(url,cargaComboInformes,"",null,null,null);
	}else if(flag=='RIR'){
		cargaInformeRIR(idPeriodo)
	}else if(flag=='RCORR'){
		cargaInformeRCORR(idPeriodo)
	}else if(flag=='RB'){
		cargaInformeRB(idPeriodo)
	}


}

function cargaComboInformes() {
	var datosInformes  = this.req.responseXML;
	i = 0;

	var nodoMensaje = datosInformes.getElementsByTagName("listado");

	if(nodoMensaje.length>0){
		arrDetalleMensaje = new Array();
		for(i=0; i < nodoMensaje.length; i++){
			arrDetalleMensaje[i]=new Array(nodoMensaje[i].getElementsByTagName("ID_INFORME")[0].childNodes[0].nodeValue,
				nodoMensaje[i].getElementsByTagName("NOMBRE_INFORME")[0].childNodes[0].nodeValue)
		}
	}
	else{
		arrDetalleMensaje = new Array();
	}
	cargaCMBInformes();

}

function cargaCMBInformes(){
	var objSelect1 =  document.getElementById("contInforme");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Todos",-1);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
}

//Hacer uno por cada combo partida
function cargaCapitulo(idPartida){

	var flag = document.getElementById('oculto').value;
	var idEjercicio = document.getElementById('cbEjercicio').value;

	if(flag=='RC'){
		var url = '../cargacombos/capitulo?ejercicioId='+idEjercicio+ "&idPartida=" + idPartida;
		var cf  = new net.ContentLoader(url,comboCapitulo,"",null,null,null);
	}else if(flag=='RERE'){
		cargaCapituloRERE(idPartida);
	}else if(flag=='RIR'){
		cargaCapituloRIR(idPartida);
	}else if(flag=='RCORR'){
		cargaCapituloRCORR(idPartida);
	}else if(flag=='RB'){
		cargaCapituloRB(idPartida);
	}
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
	objSelect1.options[0] =new Option("Seleccione Capitulo",0);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
	rendertable('listado');
}


function cargaCapituloRERE(idPartida){
	    var idEjercicio = document.getElementById('cbEjercicio').value;
	    var url = '../cargacombos/capitulo?ejercicioId='+idEjercicio+ "&idPartida=" + idPartida;
		var cf  = new net.ContentLoader(url,comboCapituloRERE,"",null,null,null);
}

function comboCapituloRERE(){

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
	cargaMensajeCapituloRERE();

}

function cargaMensajeCapituloRERE() {
	var objSelect1 =  document.getElementById("contCapituloRERE");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Seleccione Capitulo",0);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
	//rendertable('listado');
}


function cargaCapituloRERE(idPartida){
	var idEjercicio = document.getElementById('cbEjercicio').value;
	var url = '../cargacombos/capitulo?ejercicioId='+idEjercicio+ "&idPartida=" + idPartida;
	var cf  = new net.ContentLoader(url,comboCapituloRERE,"",null,null,null);
}

function comboCapituloRERE(){

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
	cargaMensajeCapituloRERE();

}

function cargaMensajeCapituloRERE() {
	var objSelect1 =  document.getElementById("contCapituloRERE");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Seleccione Capitulo",0);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
	//rendertable('listado');
}

function cargaCapituloRCORR(idPartida){
	var idEjercicio = document.getElementById('cbEjercicio').value;
	var url = '../cargacombos/capitulo?ejercicioId='+idEjercicio+ "&idPartida=" + idPartida;
	var cf  = new net.ContentLoader(url,comboCapituloRCORR,"",null,null,null);
}

function comboCapituloRCORR(){

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
	cargaMensajeCapituloRCORR();

}

function cargaMensajeCapituloRCORR() {
	var objSelect1 =  document.getElementById("contCapituloRCORR");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Seleccione Capitulo",0);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
	//rendertable('listado');
}


function cargaCapituloRB(idPartida){
	var idEjercicio = document.getElementById('cbEjercicio').value;
	var url = '../cargacombos/capitulo?ejercicioId='+idEjercicio+ "&idPartida=" + idPartida;
	var cf  = new net.ContentLoader(url,comboCapituloRB,"",null,null,null);
}

function comboCapituloRB(){

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
	cargaMensajeCapituloRB();

}

function cargaMensajeCapituloRB() {
	var objSelect1 =  document.getElementById("contCapituloRB");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Seleccione Capitulo",0);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
	//rendertable('listado');
}

function cargaCapituloRIR(idPartida){
	var idEjercicio = document.getElementById('cbEjercicio').value;
	var url = '../cargacombos/capitulo?ejercicioId='+idEjercicio+ "&idPartida=" + idPartida;
	var cf  = new net.ContentLoader(url,comboCapituloRIR,"",null,null,null);
}

function comboCapituloRIR(){

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
	cargaMensajeCapituloRIR();

}

function cargaMensajeCapituloRIR() {
	var objSelect1 =  document.getElementById("contCapituloRIR");

	objSelect1.length = 0;
	objSelect1.length =  arrDetalleMensaje.length;
	objSelect1.options[0] =new Option("Seleccione Capitulo",0);
	for(i=0;i < arrDetalleMensaje.length ;i++){
		objSelect1.options[i+1] = new Option(arrDetalleMensaje[i][1], arrDetalleMensaje[i][0]);
	}
	//rendertable('listado');
}


function buscarReporteCumpl(){
	var tpInforme = document.getElementById('contTipoInformes').value;
	var reporte = document.getElementById('cbReporte').value;
	var ejercicio = document.getElementById('cbEjercicio').value;
	var periodo = document.getElementById('contPeriodos').value;
	var informe = document.getElementById('contInforme').value;
	var partida = document.getElementById('contPartidas').value;
	var capitulo = document.getElementById('contCapitulo').value;

	if(partida=="0"){

		$.confirm({
			title: 'Reporte de Cumplimiento',
			content: 'Debe seleccionar una partida',
			type: 'blue',
			typeAnimated: true,
			buttons: {
				tryAgain:{
					text:'Aceptar',
					btnClass: 'btn-blue',
					action: function () {
					}
				},

			}
		});
		return;
	}else if(capitulo=="0"){

		$.confirm({
			title: 'Reporte de Cumplimiento',
			content: 'Debe seleccionar un Capitulo',
			type: 'blue',
			typeAnimated: true,
			buttons: {
				tryAgain:{
					text:'Aceptar',
					btnClass: 'btn-blue',
					action: function () {
					}
				},

			}
		});
		return;
	}else {
		window.parent.cargando();
		var url = '../reportes/listReporteCumpl?tpInforme='+tpInforme+"&reporte="+reporte+"&ejercicio="+ejercicio+"&periodo="+periodo +"&informe="+informe +"&partida="+partida +"&capitulo="+capitulo;
		var cf  = new net.ContentLoader(url,cargaReporteCumplimiento,"",null,null,null);
	}
}

function cargaReporteCumplimiento(){

	var data       	= this.req.responseXML;
	var elemento   	= document.getElementById("scrollarea-content");
	var childNodes 	= data.getElementsByTagName("listaReportes");
	var gko        	= navigator.userAgent.toLowerCase();

	mostrarDiv('scrollarea-content');

	window.parent.stop();
	if(elemento.hasChildNodes()){
		rendertable(elemento);
	}

	var tablaListado = document.createElement("table");
	tablaListado.id = "tablaMonedas";

	var tbodyListado = document.createElement("tbody");

	if(childNodes.length>0){
		ocultarDiv('registros');

		for(i=0;i<childNodes.length;i++){
			var trListado = document.createElement("tr");


			tablaListado.className = "adminlist";
			trListado.className = childNodes[i].getElementsByTagName("rowClass")[0].childNodes[0].nodeValue;


			var tdInforme = document.createElement("td");
			tdInforme.width ="20%";
			tdInforme.setAttribute("align","center");
			var spanInforme = document.createElement("span");
			spanInforme.className= "editlinktip hasTip";
			spanInforme.setAttribute("title",childNodes[i].getElementsByTagName("INFORME")[0].childNodes[0].nodeValue);
			var txInforme = document.createTextNode(childNodes[i].getElementsByTagName("INFORME")[0].childNodes[0].nodeValue);
			spanInforme.appendChild(txInforme);
			tdInforme.appendChild(spanInforme);
			trListado.appendChild(tdInforme);

			var tdPeriodoContable = document.createElement("td");
			tdPeriodoContable.width ="20%";
			tdPeriodoContable.setAttribute("align","center");
			var spanPeriodoContable = document.createElement("span");
			spanPeriodoContable.className= "editlinktip hasTip";
			spanPeriodoContable.setAttribute("title",childNodes[i].getElementsByTagName("PERIODO")[0].childNodes[0].nodeValue);
			var txtPeriodoContable = document.createTextNode(childNodes[i].getElementsByTagName("PERIODO")[0].childNodes[0].nodeValue);
			spanPeriodoContable.appendChild(txtPeriodoContable);
			tdPeriodoContable.appendChild(spanPeriodoContable);
			trListado.appendChild(tdPeriodoContable);

			var tdPartida = document.createElement("td");
			tdPartida.width ="10%";
			tdPartida.setAttribute("align","center");
			var spanPartida = document.createElement("span");
			spanPartida.className= "editlinktip hasTip";
			spanPartida.setAttribute("title",childNodes[i].getElementsByTagName("PARTIDA")[0].childNodes[0].nodeValue);
			var txtPartida = document.createTextNode(childNodes[i].getElementsByTagName("PARTIDA")[0].childNodes[0].nodeValue);
			spanPartida.appendChild(txtPartida);
			tdPartida.appendChild(spanPartida);
			trListado.appendChild(tdPartida);

			var tdCapitulo = document.createElement("td");
			tdCapitulo.width ="10%";
			tdCapitulo.setAttribute("align","center");
			var spanCapitulo = document.createElement("span");
			spanCapitulo.className= "editlinktip hasTip";
			spanCapitulo.setAttribute("title",childNodes[i].getElementsByTagName("CAPITULO")[0].childNodes[0].nodeValue);
			var txtCapitulo = document.createTextNode(childNodes[i].getElementsByTagName("CAPITULO")[0].childNodes[0].nodeValue);
			spanCapitulo.appendChild(txtCapitulo);
			tdCapitulo.appendChild(spanCapitulo);
			trListado.appendChild(tdCapitulo);

			var tdResponsableEnvio = document.createElement("td");
			tdResponsableEnvio.width ="20%";
			tdResponsableEnvio.setAttribute("align","center");
			var spanResponsableEnvio = document.createElement("span");
			spanResponsableEnvio.className= "editlinktip hasTip";
			spanResponsableEnvio.setAttribute("title",childNodes[i].getElementsByTagName("RESPONSABLE_ENVIO")[0].childNodes[0].nodeValue);
			var txtResponsableEnvio = document.createTextNode(childNodes[i].getElementsByTagName("RESPONSABLE_ENVIO")[0].childNodes[0].nodeValue);
			spanResponsableEnvio.appendChild(txtResponsableEnvio);
			tdResponsableEnvio.appendChild(spanResponsableEnvio);
			trListado.appendChild(tdResponsableEnvio);

			var tdFechaEnvio = document.createElement("td");
			tdFechaEnvio.width ="9.0%";
			tdFechaEnvio.setAttribute("align","center");
			var spanFechaEnvio = document.createElement("span");
			spanFechaEnvio.className= "editlinktip hasTip";
			spanFechaEnvio.setAttribute("title",childNodes[i].getElementsByTagName("FECHA_ENVIO")[0].childNodes[0].nodeValue);
			var txtFechaEnvio = document.createTextNode(childNodes[i].getElementsByTagName("FECHA_ENVIO")[0].childNodes[0].nodeValue);
			spanFechaEnvio.appendChild(txtFechaEnvio);
			tdFechaEnvio.appendChild(spanFechaEnvio);
			trListado.appendChild(tdFechaEnvio);


			tbodyListado.appendChild(trListado);
			tablaListado.appendChild(tbodyListado);
			elemento.appendChild(tablaListado);

		}

	}else{
		mostrarDiv('registros');
	}
}

function buscarReporteEnvio(){
	var tpInforme = document.getElementById('contTipoInformes').value;
	var partida = document.getElementById('contPartidasRERE').value;
	var ejercicio = document.getElementById('cbEjercicio').value;
	var periodo = document.getElementById('contPeriodosRERE').value;
	var capitulo = document.getElementById('contCapituloRERE').value;

	if(partida=="0"){

		$.confirm({
			title: 'Reporte de Envio Relaizado',
			content: 'Debe seleccionar una partida',
			type: 'blue',
			typeAnimated: true,
			buttons: {
				tryAgain:{
					text:'Aceptar',
					btnClass: 'btn-blue',
					action: function () {
					}
				},
			}
		});
		return;
	}else if(capitulo=="0"){

		$.confirm({
			title: 'Reporte de Envio Relaizado',
			content: 'Debe seleccionar un Capitulo',
			type: 'blue',
			typeAnimated: true,
			buttons: {
				tryAgain:{
					text:'Aceptar',
					btnClass: 'btn-blue',
					action: function () {
					}
				},
			}
		});
		return;
	}else {
		window.parent.cargando();
		var url = '../reportes/listReporteEnvioEntidad?tpInforme=' + tpInforme + "&partida=" + partida + "&ejercicio=" + ejercicio + "&periodo=" + periodo + "&capitulo=" + capitulo;
		var cf = new net.ContentLoader(url, cargaReporteEnvio, "", null, null, null);
	}
}

function cargaReporteEnvio() {
	var data       	= this.req.responseXML;
	var elemento   	= document.getElementById("scrollarea-content");
	var childNodes 	= data.getElementsByTagName("listaEnvio");
	var gko        	= navigator.userAgent.toLowerCase();

	mostrarDiv('scrollarea-content');

	window.parent.stop();
	if(elemento.hasChildNodes()){
		rendertable(elemento);
	}

	var tablaListado = document.createElement("table");
	tablaListado.id = "tablaMonedas";

	var tbodyListado = document.createElement("tbody");

	if(childNodes.length>0){
		ocultarDiv('registros');

		for(i=0;i<childNodes.length;i++){
			var trListado = document.createElement("tr");


			tablaListado.className = "adminlist";
			trListado.className = childNodes[i].getElementsByTagName("rowClass")[0].childNodes[0].nodeValue;


			var tdTransaccion = document.createElement("td");
			tdTransaccion.width ="8%";
			tdTransaccion.setAttribute("align","center");
			var spanTransaccion = document.createElement("span");
			spanTransaccion.className= "editlinktip hasTip";
			spanTransaccion.setAttribute("title",childNodes[i].getElementsByTagName("TRANSACCION")[0].childNodes[0].nodeValue);
			var txtTransaccion = document.createTextNode(childNodes[i].getElementsByTagName("TRANSACCION")[0].childNodes[0].nodeValue);
			spanTransaccion.appendChild(txtTransaccion);
			tdTransaccion.appendChild(spanTransaccion);
			trListado.appendChild(tdTransaccion);

			var tdPeriodo = document.createElement("td");
			tdPeriodo.width ="20%";
			tdPeriodo.setAttribute("align","center");
			var spanPeriodo = document.createElement("span");
			spanPeriodo.className= "editlinktip hasTip";
			spanPeriodo.setAttribute("title",childNodes[i].getElementsByTagName("PERIODO")[0].childNodes[0].nodeValue);
			var txtPeriodo = document.createTextNode(childNodes[i].getElementsByTagName("PERIODO")[0].childNodes[0].nodeValue);
			spanPeriodo.appendChild(txtPeriodo);
			tdPeriodo.appendChild(spanPeriodo);
			trListado.appendChild(tdPeriodo);

			var tdResponsableEnvio = document.createElement("td");
			tdResponsableEnvio.width ="20%";
			tdResponsableEnvio.setAttribute("align","center");
			var spanResponsableEnvio = document.createElement("span");
			spanResponsableEnvio.className= "editlinktip hasTip";
			spanResponsableEnvio.setAttribute("title",childNodes[i].getElementsByTagName("RESPONSABLE_ENVIO")[0].childNodes[0].nodeValue);
			var txtResponsableEnvio = document.createTextNode(childNodes[i].getElementsByTagName("RESPONSABLE_ENVIO")[0].childNodes[0].nodeValue);
			spanResponsableEnvio.appendChild(txtResponsableEnvio);
			tdResponsableEnvio.appendChild(spanResponsableEnvio);
			trListado.appendChild(tdResponsableEnvio);

			var tdFechaEnvio = document.createElement("td");
			tdFechaEnvio.width ="20%";
			tdFechaEnvio.setAttribute("align","center");
			var spanFechaEnvio = document.createElement("span");
			spanFechaEnvio.className= "editlinktip hasTip";
			spanFechaEnvio.setAttribute("title",childNodes[i].getElementsByTagName("FECHA_ENVIO")[0].childNodes[0].nodeValue);
			var txtFechaEnvio = document.createTextNode(childNodes[i].getElementsByTagName("FECHA_ENVIO")[0].childNodes[0].nodeValue);
			spanFechaEnvio.appendChild(txtFechaEnvio);
			tdFechaEnvio.appendChild(spanFechaEnvio);
			trListado.appendChild(tdFechaEnvio);


			var tdCertificado = document.createElement("td");
			tdCertificado.width = "9.1%";
			tdCertificado.setAttribute("align", "center");

			var spanCertificado = document.createElement("img");
			spanCertificado.src = "/sicogen-mf/resources/img/certificado.png";
			spanCertificado.setAttribute('title', 'Certificado de Envio');
			spanCertificado.setAttribute("onclick", "javascript:verCertificadoR('" + childNodes[i].getElementsByTagName("CERT_ENVIO")[0].childNodes[0].nodeValue + "');");
			spanCertificado.setAttribute('width', '24px;');
			spanCertificado.setAttribute('height', '20px;');
			tdCertificado.appendChild(spanCertificado);
			trListado.appendChild(tdCertificado);

			tbodyListado.appendChild(trListado);
			tablaListado.appendChild(tbodyListado);
			elemento.appendChild(tablaListado);

		}

	}else{
		mostrarDiv('registros');
	}
}

function verCertificadoR(id){

		var url='../reportes/certificadoEnvio?certificado='+id;
		//location.href = url;
		window.open(url);
}

function initial() {
	var idEjercicio = document.getElementById("cbEjercicio").value;
	document.getElementById('oculto').value = 'RC';
	cargaPeriodo(idEjercicio);
	cargaPartida(idEjercicio);
	mostrarDiv('RC');
}

function seleccionaDivReportes()
{
	var reporte = document.getElementById("cbReporte").value;
	if (reporte == 1){
		document.getElementById('oculto').value = 'RC';
		document.getElementById('RC').style.display = 'block';
		document.getElementById('RCORR').style.display = 'none';
		document.getElementById('tablaUno').style.display = 'block';
		document.getElementById('RERE').style.display = 'none';
		document.getElementById('tablaDos').style.display = 'none';
		document.getElementById('tablaCuatro').style.display = 'none';
		document.getElementById('RB').style.display = 'none';
		document.getElementById('tablaCinco').style.display = 'none';
		document.getElementById('tablaSeis').style.display = 'none';
		document.getElementById('RIR').style.display = 'none';
		//$('#tablaDocumentosCarga').html('<tr id="tdc" class="rwdetInfPar" align="center"> No hay informacion para los filtros seleccionados </tr>');
		//cargaPeriodosEjercicioRC();
	}else if (reporte == 2){
		ocultarDiv('scrollarea-content');
		var idEjercicio = document.getElementById("cbEjercicio").value;
		document.getElementById('oculto').value = 'RERE';
		cargaPartidaRERE(idEjercicio);
		cargaPeriodoRERE(idEjercicio);
		document.getElementById('RC').style.display = 'none';
		document.getElementById('RCORR').style.display = 'none';
		document.getElementById('tablaUno').style.display = 'none';
		//$('#tablaRealizadoEntidad').html('<p id="tre" class="rwdetInfPar" align="center"> No hay informacion para los filtros seleccionados <p>');
		document.getElementById('RERE').style.display = 'block';
		document.getElementById('tablaDos').style.display = 'block';
		document.getElementById('tablaCuatro').style.display = 'none';
		document.getElementById('RB').style.display = 'none';
		document.getElementById('RIR').style.display = 'none';
		document.getElementById('tablaCinco').style.display = 'none';
		document.getElementById('tablaSeis').style.display = 'none';
		//cargaPerEjerReporteEnvio();

	}else if (reporte == 3){
		ocultarDiv('scrollarea-content');
		var idEjercicio = document.getElementById("cbEjercicio").value;
		var idTipoInforme = document.getElementById('contTipoInformes').value;
		document.getElementById('oculto').value = 'RIR';
		cargaPartidaRIR(idEjercicio);
		cargaInformeRIR(idTipoInforme);
		cargaPeriodoRIR(idEjercicio);
		document.getElementById('RC').style.display = 'none';
		document.getElementById('RCORR').style.display = 'none';
		document.getElementById('tablaUno').style.display = 'none';
		document.getElementById('RERE').style.display = 'none';
		document.getElementById('tablaDos').style.display = 'none';
		document.getElementById('RB').style.display = 'none';
		document.getElementById('tablaCuatro').style.display = 'none';
		document.getElementById('tablaCinco').style.display = 'none';
		document.getElementById('RIR').style.display = 'block';
		document.getElementById('tablaSeis').style.display = 'block';
		//$('#tablaInformesReversados').html('<p id="tbc" class="rwdetInfPar" align="center"> No hay informacion para los filtros seleccionados <p>');
		//cargaInformexReversados();
		//cargaPeriodosEjercicioRev();
	}else if (reporte == 4){
		ocultarDiv('scrollarea-content');
		var idEjercicio = document.getElementById("cbEjercicio").value;
		var idTipoInforme = document.getElementById('contTipoInformes').value;
		document.getElementById('oculto').value = 'RCORR';
		cargaPartidaRCORR(idEjercicio);
		cargaPeriodoRCORR(idEjercicio);
		cargaInformeRCORR(idTipoInforme);
		document.getElementById('RC').style.display = 'none';
		document.getElementById('RCORR').style.display = 'block';
		document.getElementById('tablaUno').style.display = 'none';
		document.getElementById('RERE').style.display = 'none';
		document.getElementById('tablaDos').style.display = 'none';
		document.getElementById('RB').style.display = 'none';
		document.getElementById('RIR').style.display = 'none';
		document.getElementById('tablaSeis').style.display = 'none';
		document.getElementById('tablaCuatro').style.display = 'block';
		//$('#tablaCorrecciones').html('<p id="tbc" class="rwdetInfPar" align="center"> No hay informacion para los filtros seleccionados <p>');
		document.getElementById('tablaSeis').style.display = 'none';
		document.getElementById('tablaCinco').style.display = 'none';
		//cargaInformexTipoCorrecciones();
		//cargaPeriodosEjercicioCorrecciones();
	}else if (reporte == 5){
		ocultarDiv('scrollarea-content');
		var idEjercicio = document.getElementById("cbEjercicio").value;
		var idTipoInforme = document.getElementById('contTipoInformes').value;
		document.getElementById('oculto').value = 'RB';
		cargaPartidaRIB(idEjercicio);
		cargaInformeRB(idTipoInforme);
		cargaPeriodoRB(idEjercicio);
		document.getElementById('RC').style.display = 'none';
		document.getElementById('RCORR').style.display = 'none';
		document.getElementById('tablaUno').style.display = 'none';
		document.getElementById('RERE').style.display = 'none';
		document.getElementById('tablaDos').style.display = 'none';
		document.getElementById('tablaCuatro').style.display = 'none';
		document.getElementById('RIR').style.display = 'none';
		//$('#tablaBitacoraAcciones').html('<p id="tbc" class="rwdetInfPar" align="center"> No hay informacion para los filtros seleccionados <p>');
		document.getElementById('RB').style.display = 'block';
		document.getElementById('tablaCinco').style.display = 'block';
		document.getElementById('tablaSeis').style.display = 'none';
		//cargaInformexTipoBitacora();
		//cargaPeriodosEjercicioBitAcciones();
	}
}

function rendertable(div){

	var element = div;
	while(element.childNodes.length){
		element.removeChild(element.lastChild);
	}
}



function mostrarDiv(div){

	document.getElementById(div).style.display = "block";
	document.getElementById(div).style.visibility = "visible";

}

function ocultarDiv(div){

	document.getElementById(div).style.display = "none";
	document.getElementById(div).style.visibility = "hidden";
}
// JavaScript Document

function cambiaTabla(id){

	alert(id);

}
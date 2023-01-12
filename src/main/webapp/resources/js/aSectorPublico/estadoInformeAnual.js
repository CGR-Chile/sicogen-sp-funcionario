$(document).ready(function(){
	var codPartida = document.getElementById('codPartidaIni').value;
	var codCapitulo = document.getElementById('codCapituloIni').value;
	var ejercicio = document.getElementById('ejercicioIni').value;
	var tipoInforme = document.getElementById('tipoInformeIni').value;

	var action = '../seguimiento/list?ejercicioId='+ejercicio+"&tipoInforme="+tipoInforme+"&codCapitulo="+codCapitulo+"&codPartida="+codPartida;
	var data = {}
	$.ajax({
		url: action,
		type: "POST",
		contentType  : "application/json",
		data : JSON.stringify(data)
		,complete: function (data) {
			$('#fadePeriodos').remove();
			$('#waitPeriodos').remove();
		},error: function(XMLHttpRequest, textStatus, errorThrown){
			alert('Error: ' + textStatus);
			alert(XMLHttpRequest.responseText);
		},
		success: function(data){

			actualizarGrillaInformesJSON(data, $("#cbEjercicio option:selected").val() );
		}
	});
});

function actualizarGrillaInformesJSON(xml, idEjercicio){
	window.parent.stop();

	var indiceEvento;

	if(xml.hayComplementos>0){
		$("#titCorr").text("COR"); 
		$("#titCorr").css("width","37px");
	}else{
		$("#titCorr").text("");
		$("#titCorr").css("width","0px");
	}
	
	//limpia contenedor
	$('#contEstInfAnual').text('');
	$('.datosBusqueda').empty();

	var ejercicio = document.getElementById('cbEjercicio').value;
	var action = 'listaPeriodoAbrev?ejercicioId=' + ejercicio
	var data = { ejercicioId: ejercicio}
	var arrayData = [];
	var periodosCod = [];

	$.ajax({
		type: "POST",
		url: action,
		contentType: "application/json",
		data: JSON.stringify(data),
		dataType: "json",
		success: function (data) {
			$.each(data, function (key, registro) {
				arrayData.push(registro);
			});
			$("#periodoAbrev").empty();
			$("#periodoAbrev").append('<td width="25%" class="title-grid"><div align="center"><span class="Estilo11"><b>INFORMES</b></span></div></td>');

			for (var i = 0; arrayData.length > i; i++) {
				$("#periodoAbrev").append('<td width="5%" class="title-grid"><div align="center"><span class="Estilo11"><b>'+arrayData[i].periodoAbrev+'</b></span></div></td>');
				periodosCod.push(arrayData[i].periodoNombre);
			}

	var periodosNom = [ 'Ape','Ene','Feb','Mar','Abr','May','Jun','Jul','Ago','Sep','Oct','Nov','Dic','Cor','Cie' ];

	indiceEvento=0;
	$.each(xml.infomes, function(i, itm) {
		var row='rw_inf0'+(itm.informeId);
		var clase='';
		if ((i+1) %2 == 0){ clase= "row0"; }else{ clase= "row1";}
		jQuery('<tr/>', {id:row,class:clase}).appendTo('.datosBusqueda');

		//imprime contenedor con el nombre del informe
		var rowcol = 'inf' + (itm.informeId);
		jQuery('<td/>', {
				id: rowcol,
				html:  "<div style='display: none;width:6%;vertical-align:top;'></div>"+"<div style='display:inline-block;width:2%;vertical-align:top;'>"+" </div>"+"<div id='envio_"+itm.informeId+"' style='display:inline-block;width:92%;'> "+itm.informeNombre+"</div> "
		}).appendTo('#'+row);

		$.each(periodosCod, function(p, pitm){
			var rowcol='inf'+(itm.informeId)+'_'+pitm;
			jQuery('<td/>', {
				id: rowcol,
				//class: clase
				style: 'text-align: center;'
			}).appendTo('#' + row);

			var objeto=	"<img id='inf"+(itm.informeId)+'_'+pitm+"' src='/sicogen-mf/resources/img/blanco.png' class='img18 popUpInf pi"+itm.informeId+" cod"+pitm+"' aria-describedby='ui-tooltip-8'/>";
			$('#' + rowcol).append(objeto);
			
			if(pitm=='Cor'){
				 $('#inf'+(itm.informeId)+'_'+pitm).hide();
			}
		
		});	
	
	});
 
	
	
	$.each(xml.estados, function(p, pitm){
		switch(pitm.archivoEstadoId){
			case  3:	imagen='/sicogen-mf/resources/img/error.png';break;
			case  4:	imagen='/sicogen-mf/resources/img/Validado.png';break;
			case  5:	imagen='/sicogen-mf/resources/img/ValidadoOBS.png';break;
			case  6:	imagen='/sicogen-mf/resources/img/Procesado.png';break;
			case  7:	imagen='/sicogen-mf/resources/img/ProcesadoOBS.png';break;
			case  8:	imagen='/sicogen-mf/resources/img/NotMov.png';break;
			case 10:	imagen='/sicogen-mf/resources/img/NotMovProc.png';break;
			default:	imagen='/sicogen-mf/resources/img/blanco.png';break;
		}
		
		
		// validacion de TDR .. Se debe mostrar siempre una Lupa !!
		if(pitm.informeId == 2 || pitm.informeId == 11 || pitm.informeId == 12){
			imagen='/sicogen-mf/resources/img/icon_lupa.png';
		}
		
		
		$('img[class="img18 popUpInf pi'+pitm.informeId+' cod'+pitm.periodoCodigo+'"]').attr('src',imagen);
		$('img[class="img18 popUpInf pi'+pitm.informeId+' cod'+pitm.periodoCodigo+'"]').attr('idFileUpload',pitm.archivoId);
		if(pitm.archivoFecha != null)
		{
			var fechas=pitm.archivoFecha.split(' ');
		}

		
		var menEstado="";
		switch(pitm.archivoEstadoId){
			case  3:	menEstado="Informe con error bloqueante cargado";break;
			case  4:	menEstado="Validado";break;
			case  5:	menEstado="Validado con observaciones";break;
			case  6:	menEstado="Procesado";break;
			case  7:	menEstado="Procesado con observaciones ";break;
			case  8:	menEstado="Validado sin movimiento en CGR ";break;
			case 10:	menEstado="Enviado sin movimiento a CGR ";break;
		}

		$('img[class="img18 popUpInf pi'+pitm.informeId+' cod'+pitm.periodoCodigo+'"]').live({


			mouseenter: function()
			{
				//alert(pitm.informeId);
					$(this).css({'cursor':'pointer'});
					
					if (pitm.informeId !== 11) {
						tooltip="<div class='ui-tooltip-content' style='width:450px;position:absolute; '>"+
							"<div class='tooltip-title'> "+ $('#inf'+pitm.informeId).text().toUpperCase() +" </div>"+
							"<div class='tooltip-content'>"+
							"<p style='margin:0;padding:0;'>"+menEstado+" por "+pitm.archivoUsuario+" el "+fechas[0].substr(8, 2)+"-"+fechas[0].substr(5, 2)+"-"+fechas[0].substr(0, 4) + ' a las ' + fechas[1].substr(0, 5)+
							"</p>";

						if((pitm.archivoEstadoId=="6")||(pitm.archivoEstadoId=="7")||(pitm.archivoEstadoId=="10")){
							/*tooltip=tooltip+"<br><p style='margin:0;padding:0;'>N&deg; de Env&iacuteo: "+pitm.certificadoId+"</p>";*/
							tooltip=tooltip+"<br><p style='margin:0;padding:0;'></p>";
						}
						tooltip=tooltip+"<br><p style='margin:0;padding:0;' class='tooltip-message'>Para Acceder a m&aacutes Informacion Haga Click sobre el Icono del Estado del Informe</p></div></div>";
					} else {
						tooltip="<div class='ui-tooltip-content' style='width:450px;position:absolute; '>"+
							"<div class='tooltip-title'> "+ $('#inf'+pitm.informeId).text().toUpperCase() +" </div>"+
							"<div class='tooltip-content'>"+
							"<p style='margin:0;padding:0;'></p>";
						tooltip=tooltip+"<br><p style='margin:0;padding:0;' class='tooltip-message'>Para Acceder a m&aacutes Informacion Haga Click sobre el Icono del Estado del Informe</p></div></div>";
					}
					
					$('body').append(tooltip);
					
					izq=0;
					if ($(this).offset().left<820){ izq=$(this).offset().left+20;}
					else{							izq=$(this).offset().left-450;}
					$('.ui-tooltip-content').css({left:izq,top: $(this).offset().top});
				//}
			},
			mouseleave: function(){
									
					$('div').remove('.ui-tooltip-content');}
			
				});

				$('img[class="img18 popUpInf pi'+pitm.informeId+' cod'+pitm.periodoCodigo+'"]').click({
					 
					informe: 		pitm.informeId, 	
					periodo: 		pitm.periodoInformeId,		
					ejercicio:		pitm.ejercicioId,			
					usuario:		pitm.archivoUsuario,		
					fecha:			pitm.archivoFecha,						
					periodo2:		pitm.periodoEjercicioId,	
					certificado:	pitm.certificadoId,			
					estado:			pitm.archivoEstadoId,		
					idFileUpload:	pitm.archivoId, 
					periodo: 		pitm.periodoCodigo
				}, demo);
		});
	
	$.each(xml.correcciones, function(i, itm) {

		console.log("correcciones--->",itm);
		var param = '\"'+$("#cbPartida option:selected").val()+'\",\"'+$("#cbCapitulo option:selected").val()+'\",'+$("#cbEjercicio option:selected").val();
		
		//var objeto="<a class='gbgt gbes' id='gbg"+i+"' onclick='verComplementosEnviados("+param+")'><div id='gbgs"+i+"'><span id='gbi"+i+"'>Ver</span></div></a>";
		//$(objeto).appendTo('#inf0'+itm.informeId+'_Cor');
		//$('#gbgs'+i).css({'cursor':'pointer'});
		
		var clasesCorr = 'img18 pi'+itm.informeId;
		
		var append = '#inf'+itm.informeId+'_Cor';
		
		var objeto = 
			
			"<div id='"+append+"' class='detalleInfColPer' style='width: 37px;'>"
			+ "<img onclick='verComplementosEnviados("+param+")' class='"+clasesCorr+"' style='cursor: pointer;' src='images/ver_correccion.png'/>"
			+"</div>" ;
		//alert(objeto);
		
		
		//alert(append);
		
		$( append).replaceWith( objeto );
		//$(objeto).appendTo( append );
		$('#gbgs'+i).css({'cursor':'pointer'});
		
		
	});

		},
		error: function (data) {
			alert('Error al cargar la información de Periodos');
		}
	});

}

function busquedaListado(ejercicio, tipoInforme){

	if ($.isNumeric(ejercicio)==false ){ejercicio=0;}
	
	var codPartida = document.getElementById('contPartida').value;
	var codCapitulo = document.getElementById('contCapitulo').value;

	window.parent.cargando();
	var action = '../seguimiento/list?ejercicioId='+ejercicio+"&tipoInforme="+tipoInforme+"&codCapitulo="+codCapitulo+"&codPartida="+codPartida;
	var data = {}
	$.ajax({
		 url: action,
		    type: "POST",
		    contentType  : "application/json",
		    data : JSON.stringify(data)
		    ,complete: function (data) {
				$('#fadePeriodos').remove();
				$('#waitPeriodos').remove();
			},error: function(XMLHttpRequest, textStatus, errorThrown){
		        alert('Error: ' + textStatus);
		        alert(XMLHttpRequest.responseText);
		    },
		    success: function(data){

		    	actualizarGrillaInformesJSON(data, $("#cbEjercicio option:selected").val() );
		    }
		});
}

function loadPartida(idEjercicio){
	var action='loadPartida?ejercicioId=' + $("#cbEjercicio option:selected").val();
	$.ajax({url:action,
			type:"POST",
			dataType: "json",
			beforeSend: function (xhr) {
				$('body').append('<div id="fadePeriodos" class="overlay" style="display:block"></div>'+
					'<div id="waitPeriodos" class = "contEspera modalCarga" style="left:35%;position:absolute;width:350px;height:250px;z-index:2005;padding:15px !important;display:block">'+ 
					'	  <div style="font:bold 14px arial,sans-serif;margin: 20px 0 0 40px;">Cargando la informacion</div>'+
					'     <img id="estado" src="images/loader.gif" style="margin:40px 0 0 120px;width:96px;height:96px;" />'+
					'</div>');
				
			 },complete: function (data) {
					$('#fadePeriodos').remove();
					$('#waitPeriodos').remove();
			},
			success: function(data){

				$('#cbPartida').empty();
				$("#cbPartida").get(0).options[$("#cbPartida").get(0).options.length] = new Option( 'Selec.',-1);
		    	$.each(data.listaPartida, function(i, itm){ 	
		    		
		    		var optionSelect = new Option(itm.nombrePartida,itm.codPartida);
		    		$("#cbPartida").get(0).options[$("#cbPartida").get(0).options.length] = optionSelect;
		    		optionSelect.setAttribute("data-cod",itm.codPartida);

	    		});
		    }
	});
}

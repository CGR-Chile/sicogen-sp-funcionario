$(document).ready(function() {
	$('.verInformeBD').click(function() {
		verInf = 'verInforme.action?periodo='+$('#periodo').text()+'&tpArchivo=0'+'&informe='+$('#informe').text()+'&entidad='+sessEntidad;
		var windowSizeArray = ["width=200,height=200","width=300,height=400,scrollbars=yes"];
		var windowName = "popUp";
		var windowSize = windowSizeArray[$(this).attr("rel")];
		window.open(verInf, windowName, windowSize);
	});
	$('.verInformeCM').click(function() {
		verInf = 'verArchivoPdfCM.action?periodo='+$('#periodo').text()+'&tpArchivo=0'+'&informe='+$('#informe').text()+'&entidad='+sessEntidad;
		var windowSizeArray = ["width=200,height=200","width=300,height=400,scrollbars=yes"];
		var windowName = "popUp";
		var windowSize = windowSizeArray[$(this).attr("rel")];
		window.open(verInf, windowName, windowSize);
	});
	
	$('.repValidacion').click(function() {
		repVal = 'getValidacionInforme.action?periodo='+$('#periodo').text()+'&tpArchivo=0'+'&informe='+$('#informe').text()+'&entidad='+sessEntidad;
		console.log(repVal);
		var windowSizeArray = [ "width=200,height=200","width=300,height=400,scrollbars=yes" ];
		var windowName = "popUp";
		var windowSize = windowSizeArray[  $(this).attr("rel")  ];
		window.open(repVal, windowName, windowSize);
    });
	
	$('.verCertEnvio').click(function() {
		alert(corrEnvio);
		if ('0' == $('#corrEnvio').text()) {	
			console.log('entre a mostrar alerta');
			jAlert('No se ha enviado el informe a Contraloria', "Certificados");			
		} else {
			console.log('entre a mostrar el certificado');
			jAlert('Entre a mostrar el certificado', "Certificados");		
			var windowSizeArray = ["width=200,height=200","width=300,height=400,scrollbars=yes"];
			var windowName = "popUp";
			var windowSize = windowSizeArray[$(this).attr("rel")];
			window.open('certificadoEnvio.action?certificado='+ $('#corrEnvio').text(), windowName, windowSize);
		}
    });
	
	$('.descFile').click(function() { 
		
		//jAlert($('#periodo').text()+" "+$('#informe').text()+" "+sessEntidad);	
		
		location.href = 'descargaInforme.action?periodo='+$('#periodo').text()+'&tpArchivo=0'+'&informe='+$('#informe').text()+'&entidad='+sessEntidad; 
	});
	$('.descFileCM').click(function() { 
		location.href = 'descargaInformeCM.action?periodo='+$('#periodo').text()+'&tpArchivo=0'+'&informe='+$('#informe').text()+'&entidad='+sessEntidad; 
	});

	$("#liVerBitacora").unbind('click');
	$("#liVerBitacora").click(function() 
	{
		$("#divColFechaTramitacion"	,"#dialogBitacora").show();
		$("#divColFechaEnvio"		,"#dialogBitacora").hide();
		popUpBitacora(parametros);
	});
	
});
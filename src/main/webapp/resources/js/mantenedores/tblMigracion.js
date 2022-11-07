function Migrar(ejercicioId1,ejercicioId2){
	
//	alert(ejercicio1 + ' - ' + ejercicio2)
	
	var desde = $("#cbEjercicio option:selected").val();
	var hasta = $("#cbEjercicio2 option:selected").val();
	
	var d = $("#cbEjercicio option:selected").text();
	var h = $("#cbEjercicio2 option:selected").text();

	var action='mantenedores/migrarEjercicio?ejercicioId=' + $("#cbEjercicio option:selected").val() +'&ejercicioId2=' + $("#cbEjercicio2 option:selected").val();
	if(d > h){
		jAlert("El año de origen no puede ser mayor al de destino.");
		return false;
	}
	if(desde == hasta){
		jAlert("Debe elegir diferentes Ejercicios");
		return false;
	}
	if(d+1 == h){
		jAlert("Solo se puede migrar de un año especifico al siguiente.");
	
	}else{
	jConfirm('&iquest;Est&aacute; seguro que desea migrar el ejercicio?', 'Migrar Ejercicio', function(r) {

		if(r){
	$.ajax({
			url:action,
			type:"POST",
			data: {ejercicioId1:ejercicioId1,ejercicioId2:ejercicioId2},
			dataType: "json",
			success: function(data){
				jAlert(data.Message);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				console.log('Error');
			}
			
		});	
	}}
);
	}
}
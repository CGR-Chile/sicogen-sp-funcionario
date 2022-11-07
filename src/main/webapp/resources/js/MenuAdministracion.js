$( document ).ajaxStart(function() {
	$("#loading-spinner").addClass("is-active");
});

$( document ).ajaxStop(function() {
	$("#loading-spinner").removeClass("is-active");
});


function ChangeMantenedor(accion){

	//$('#contAdministracion').html(data);

	if((accion=='verMantenedorTblPLanCuenta')||(accion=='verMantenedorTblPLanCuentaPresup')){
		$('#contAdministracion').html('<iframe id="carga" src="mantenedores/' + accion + '" width="1028px" height="500px" style="float:left;margin: 0 -12px 0" scrolling="auto"></iframe>');
	}else{
		$.ajax({
			url: 'mantenedores/' + accion,
			type: "GET",
			dataType: "html",
			success: function(data){
				$('#contAdministracion').html(data);
			}
		});
	}
}
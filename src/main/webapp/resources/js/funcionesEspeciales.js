$( document ).ajaxStart(function() {
	$("#loading-spinner").addClass("is-active");
});

$( document ).ajaxStop(function() {
	$("#loading-spinner").removeClass("is-active");
});

$(document).ready(function () {

	$('#selectFuncion').change(function () {
		var funcion = $('#selectFuncion option:selected').val();

		if (funcion === "1") {
			$.get('getInformesReproceso', function(data) {
				$('#divTbl').html(data);
			});
		} else {
			$('#divTbl').empty();
		}
	});


});
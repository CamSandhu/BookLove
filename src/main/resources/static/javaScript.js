
$.noConflict();
	$(document).ready(function() {

		////////////////////////////////////////////geting value from elect////////////////////////////////////////

		$(document).on('change', 'select', function() {

			console.log($(this).val()); // the selected options’s value				

			var select = $(this).val();
			// if you want to do stuff based on the OPTION element:
			var opt = $(this).find('option:selected')[0];

			$("form").attr('action', '/' + select);

		});

	}

	);
$('select[data-value]').each(function(index,el) {
	const $el = $(el);
	
	const defaulValue = $el.attr('data-value').trim();
	
	
	
	if(defaulValue.length > 0) {
		$el.val(defaultValue);
	}
	
});
function contemplate(tmpl_name) {
	
    if ( !contemplate.tmpl_cache ) { 
    	contemplate.tmpl_cache = {};
    }

    if ( ! contemplate.tmpl_cache[tmpl_name] ) {
        var tmpl_dir = 'templates';
        var tmpl_url = tmpl_dir + '/' + tmpl_name + '.tmpl';

        var tmpl_string = "";
        $jq.ajax({
            url: tmpl_url,
            method: 'GET',
            async: false,
            success: function(data) {
                tmpl_string = data;
            }
        });

        contemplate.tmpl_cache[tmpl_name] = tmpl_string;
    }

    return contemplate.tmpl_cache[tmpl_name];
}
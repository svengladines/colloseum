	function assemble( feed ) {
		alert( feed.entry.title.$t );
		$jq( "#title" ).html( feed.entry.title.$t ) ;
	}

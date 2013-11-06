function build( feed ) {
		
		publit["@title"] = feed.entry.title.$t;
		publit["@preview"] = feed.entry.media$group.media$thumbnail[0].url;
		// publit["@url"] = feed.entry.rel[0].href;
		publit["meta"] = "duration=" + feed.entry.media$group.yt$duration.seconds;
		show( publit );
		
}

function show( publit ) {
	
	$jq("#title").html( publit["@title"] );
	$jq("#preview").attr( "src", publit["@preview"] );
	
}
function personAsRow( person, link ) {
	
	var row 
		= $jq("<tr/>");
	
	var t;
	
	if ( link ) {
		t = $jq("<a/>");
		// t.attr("href", personHref( person, person.sourcedIds[0].source, period ) );
	}
	else {
		t = $jq("<span/>");
	}
	t.text( person["@firstName"] + " " + person["@name"] );
	
	var ref
		= $jq( "<td/>" ).append( t );
	
	var id
		= $jq( "<td/>" )
		.html( person["@id"] );
	
	var email
		= $jq( "<td/>" )
		.html( person["@email"] ); 
	
	// row.append( id );
	
	row.append( ref );

	row.append( email );
		
	return row;
		
}
var Player = function ( given, family, email ) {
	
	this.givenName = given;
	this.familyName = family;
	this.email = email;
	
};

var postPlayer = function ( px ) {

	$jq.ajax( {
		type: "post",
		url:"/colloseum-kuleuven/rs/players",
		dataType: "json",
		contentType: "application/json",
	    processData: false,
		data: JSON.stringify(rx),
		success: function( player ) {
				window.location.href = "/colloseum-kuleuven/rs/players/" + player.id ;
		},
		error: function( player ) {
			alert( "error" );
		}
	});
	
};

function getParameter(url, key) {
    var sURLVariables = url.split('&');
    for (var i = 0; i < sURLVariables.length; i++) 
    {
        var sParameterName = sURLVariables[i].split('=');
        if (sParameterName[0] == key) 
        {
            return sParameterName[1];
        }
    }
};
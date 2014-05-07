var Registration = function ( match, player, answer ) {
	
	this.match = new Object();
	this.match.id = match;
	this.player = new Object();
	this.player.id = player;
	this.rsvp = new Object();
	this.rsvp.answer = answer;
	
};

var postRegistration = function ( rx ) {

	$jq.ajax( {
		type: "post",
		url:"/colloseum-kuleuven/rs/registrations",
		dataType: "json",
		contentType: "application/json",
	    processData: false,
		data: JSON.stringify(rx),
		success: function( registration ) {
				window.location.reload();
		},
		error: function( registration ) {
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
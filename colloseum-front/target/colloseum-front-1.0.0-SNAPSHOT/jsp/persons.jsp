<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html>

<html>

<head>
	
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap.css" rel="stylesheet">
    <link href="http://twitter.github.com/bootstrap/assets/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="http://jdewit.github.com/bootstrap-timepicker/assets/bootstrap-timepicker/css/timepicker.css" rel="stylesheet">
    
    
    <link href="http://www.eyecon.ro/bootstrap-datepicker/css/datepicker.css" rel="stylesheet">
    
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.7.2.min.js"></script>
    <script type="text/javascript" src="http://jdewit.github.com/bootstrap-timepicker/assets/bootstrap-timepicker/js/bootstrap-timepicker.js"></script>
	<script type="text/javascript" src="http://www.eyecon.ro/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>

	<title>Colloseum</title>
	
	<style type="text/css">
   		body {
     		padding-top: 60px;
     		padding-bottom: 40px;
   		}
    </style>

</head>

  <body>
  
	  <script>
	  	var $jq = jQuery.noConflict();
	  </script>
  
		<div class="navbar navbar-fixed-top">
		    <div class="navbar-inner">
		        <div class="container-fluid">
		        	<span class="brand">Colloseum</span>
		            <div class="nav-collapse">
		                <ul class="nav">
		                    <li class="active"><a href=".">Home</a></li>
		                </ul>
		            </div>
		        </div>
		    </div>
		</div>
		
		<div class="container-fluid">
	
		    <div class="row-fluid">
		
				<div class="span2">
				
				</div>
				
				<div class="span8">
				
					<form class="form-horizontal">
					
						<fieldset>
						
							<div class="control-group">
           						<label class="control-label" for="select01">Select list</label>
					            <div class="controls">
					              <select id="event">
					                <option value="be.occam.colloseum.model.match.events.MatchScheduledEvent">MatchScheduledEvent<option>
					              </select>
					            </div>
					          </div>
					          
					         <div class="control-group">
           						<label class="control-label" for="date">Date</label>
					            <div id="dte" class="controls input-append date" data-date-format="dd-mm-yyyy" data-date="12-02-2012"">
					              <input type="text" value="12-02-2012"><span class="add-on"><i class="icon-th"></i></span>
					            </div>
					         </div>
				
			    			<div class="control-group">
           						<label class="control-label" for="time">Time</label>
					            <div class="controls input-append bootstrap-timepicker-component">
					              <input id="time" class="dropdown-timepicker input-small" type="text" /><span class="add-on"><i class="icon-time"></i></span>
					            </div>
					         </div>
			    			
		                	<div class="control-group">
           						<label class="control-label" for="subject">Subject</label>
					            <div class="controls">
					                <input id="subject" class="text"></input>
					            </div>
					         </div>
					         
					         <div class="control-group">
           						<label class="control-label" for="object">Object</label>
					            <div class="controls">
					                <input id="object" class="text"></input>
					            </div>
					         </div>
		                	
		                	<input type="hidden">
		                			
		                	</input>
                	
                		</fieldset>
                	
                	</form>
            </div>
          				
				</div>
				
				<div class="span2">
				
				</div>
				
			</div>
			
				
			
			 <div class="row-fluid">
			
			 
			 </div>
			
		</div>
		
		<script>
		
		$jq('#dte').datepicker().on('changeDate', function(ev){
	    	
	    	alert("date");
		   
	    });
		
		$jq('.dropdown-timepicker').timepicker({
            minuteStep: 5,
            disableFocus: true,
            showMeridian: false
        });
		
		</script>
		
</body>

</html>
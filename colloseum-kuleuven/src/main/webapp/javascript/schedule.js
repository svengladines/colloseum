$jq( function() {
	
	moment.lang("nl", {  months : [
	                               "januari", "februari", "maart", "april", "mei", "juni", "juli",
	                               "augustus", "september", "oktober", "november", "december"
	                           ] });
	
	var Event = Backbone.Model.extend({
	
		defaults: function() {
	      return {
	        subject: "subject"
	      };
	    }
	
	  });

	var EventList = Backbone.Collection.extend({
	
		    model: Event,
	
		    past: function() {
		      
		    },
		    
		    future: function() {
		      
		    },
		    
		    url: function() {
		    	return "rs/matches?cut=28.08.2015";
		    }
	 });

	var EventView = Backbone.View.extend({
	
		    tagName:  "tr",
	
		    initialize: function() {
		      this.listenTo(this.model, 'change', this.render);
		    },
		    
		    template: _.template( contemplate("template-schedule") ),
	
		    render: function() {
		    	var json = this.model.toJSON();
		    	
		    	json["dx"] = moment( json.starts ).format("DD MMMM");
		    	json["tx"] = moment( json.starts ).format("HH:mm");
		    	
		    	var tmpl = this.template( json );
		    	this.$el.html( tmpl );
		    	
		    	this.$el.find(".registry").addClass( "badge-important" );
		    	
		    	if ( json.firstToCome == true ) {
		    	
		    		this.$el.addClass( "info" );
		    		
		    	}
		    	
		    	if ( json.isDeviantKickOff == true ) {
		    		
		    		this.$el.find(".kick").addClass( "label label-inverse" );
		    		
		    	}
		    	
		    	if ( json.homeTeam ) {
			    	
			    	if ( json.homeTeam.name == "KU Leuven" ) {
			    		
			    		this.$el.find(".home").addClass( "bold" );
			    		
			    		if ( json.homeTeamScore > json.awayTeamScore ){
			    			this.$el.find(".score").addClass( "badge-success" );
			    		}
			    		else if ( json.homeTeamScore == json.awayTeamScore ){
			    			this.$el.find(".score").addClass( "badge-warning" );
			    		}
			    		else {
			    			this.$el.find(".score").addClass( "badge-important" );
			    		}
			    		
			    		
			    	}
		    	}
		    	
		    	if ( json.awayTeam ) {
		    	
			    	if ( json.awayTeam.name == "KU Leuven" ) {
			    		
			    		this.$el.find(".away").addClass( "bold" );
			    		
			    		if ( json.homeTeamScore < json.awayTeamScore ){
			    			this.$el.find(".score").addClass( "badge-success" );
			    		}
			    		else if ( json.homeTeamScore == json.awayTeamScore ){
			    			this.$el.find(".score").addClass( "badge-warning" );
			    		}
			    		else {
			    			this.$el.find(".score").addClass( "badge-important" );
			    		}
			    		
			    	}
		    	}
		    	
		    	return this.$el;
		    },
	
		    clear: function() {
		      this.model.destroy();
		    }
	
	 });

	var AppView = Backbone.View.extend({
	
		    el: $jq("#calendar"),
		    
		    sx: function() {
		    	
		    },
	
		    er: function() {
		    	
		    },
	
		    initialize: function() {
	
		      this.listenTo(events, 'reset', this.addAll );
		      this.listenTo(events, 'all', this.render );
	
		      this.main = $jq('#main');
	
		      events.fetch( {
		    	  	reset: true,
		    	    success: function(model, response) {
		    	        console.log(response);
		    	    },
		    	    error: function(model, response) {
		    	        console.log(response);
		    	    }
		      });
		      
		    },
	
		  render: function() {
		    
			  //var done = events.done().length;
		      //var remaining = events.remaining().length;
	
		      if (events.length) {
		        this.main.show();
		      } else {
		        this.main.hide();
		      }
	
		    },
	
		    addOne: function(event) {
		      var view = new EventView({model: event});
		      $jq("#matches").append( view.render() );
		    },
	
		    addAll: function() {
		      events.each(this.addOne, this);
		    }
	
	});	

	var EventRouter = Backbone.Router.extend({
	
		  routes: {
		    "events":   "query"
		  },
	
		  query: function() {
		  }
	
	});


	
	var events = new EventList;
	var view = new AppView;
	var router = new EventRouter;

});
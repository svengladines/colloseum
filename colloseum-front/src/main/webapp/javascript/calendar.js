$jq( function() {

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
		    	return "http://localhost:8089/colloseum-soccer/rs/events";
		    }
	 });

	var EventView = Backbone.View.extend({
	
		    tagName:  "tr",
	
		    initialize: function() {
		      this.listenTo(this.model, 'change', this.render);
		    },
		    
		    template: _.template( $jq('#event-template').html() ),
	
		    render: function() {
		    	var json = this.model.toJSON();
		    	
		    	json["dx"] = moment( json.start ).format("DD.MM.YYYY");
		    	
		    	var tmpl = this.template( json );
		    	this.$el.html( tmpl );
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
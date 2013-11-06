$jq( function() {
	
	var Publit = Backbone.Model.extend({
	
		defaults: function() {
	      return {
	        title: "title",
	        author: "author",
	        timestamp: "timestamp",
	        preview: "preview",
	        text: "text"
	        
	      };
	    }
	
	  });

	var PublitList = Backbone.Collection.extend({
	
		    model: Publit,
	
		    past: function() {
		      
		    },
		    
		    future: function() {
		      
		    },
		    
		    url: function() {
		    	return "rs/publits";
		    }
	 });

	var PublitView = Backbone.View.extend({
	
		    tagName:  "div",
	
		    initialize: function() {
		      this.listenTo(this.model, 'change', this.render);
		    },
		    
		    template: _.template( contemplate("template-publit") ),
	
		    render: function() {
		    	var json = this.model.toJSON();
		    	
		    	json["dx"] = moment( json.starts ).format("DD MMMM");
		    	json["tx"] = moment( json.starts ).format("HH:mm");
		    	
		    	var tmpl = this.template( json );
		    	this.$el.html( tmpl );
		    	
		    	return this.$el;
		    },
	
		    clear: function() {
		      this.model.destroy();
		    }
	
	 });

	var AppView = Backbone.View.extend({
	
		    el: $jq("#publits"),
		    
		    sx: function() {
		    	
		    },
	
		    er: function() {
		    	
		    },
	
		    initialize: function() {
	
		      this.listenTo(publits, 'reset', this.addAll );
		      this.listenTo(publits, 'all', this.render );
	
		      this.main = $jq('#main');
	
		      publits.fetch( {
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
		    
			  //var done = publits.done().length;
		      //var remaining = publits.remaining().length;
	
		      if (publits.length) {
		        this.main.show();
		      } else {
		        this.main.hide();
		      }
	
		    },
	
		    addOne: function(publit) {
		      var view = new PublitView({model: publit});
		      $jq("#publits").append( view.render() );
		    },
	
		    addAll: function() {
		      publits.each(this.addOne, this);
		    }
	
	});	

	var PublitRouter = Backbone.Router.extend({
	
		  routes: {
		    "publits": "publits"
		  },
	
		  publits: function() {
			  
			  publits.fetch( {reset: true});
			  
		  }
	
	});

	var router = function() { return new PublitRouter(); };
	
	var publits = new PublitList;
	var view = new AppView;

});
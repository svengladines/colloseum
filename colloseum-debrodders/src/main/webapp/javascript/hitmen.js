	var HitMan = Backbone.Model.extend({
	
	  });

	var HitManList = Backbone.Collection.extend({
	
		    model: HitMan,
	
		    past: function() {
		      
		    },
		    
		    future: function() {
		      
		    },
		    
		    url: function() {
		    	return "rs/hitmen";
		    }
	 });

	var HitManRowView = Backbone.View.extend({
	
		    tagName:  "tr",
	
		    initialize: function() {
		      this.listenTo(this.model, 'change', this.render);
		    },
		    
		    template: _.template( contemplate("template-hitman") ),
	
		    render: function() {
		    	
		    	var json = this.model.toJSON();
		    	
		    	var tmpl = this.template( json );
		    	
		    	this.$el.html( tmpl );
		    	
		    	return this.$el;
		    },
	
		    clear: function() {
		      this.model.destroy();
		    }
	
	 });

	var AppView = Backbone.View.extend({
	
			hitmen: null,
			
		    el: $jq("#hitman"),
		    
		    initialize: function() {
	
		      this.listenTo(hitmen, 'reset', this.addAll );
		      this.listenTo(hitmen, 'all', this.render );
	
		      this.main = $jq('#main');
	
		    },
	
		  render: function() {
		    
		      if (HitMans.length) {
		        this.main.show();
		      } else {
		        this.main.hide();
		      }
	
		    },
	
		    addOne: function(HitMan) {
		      var view = new HitManRowView({model: HitMan});
		      $jq("#hitlist").append( view.render() );
		    },
	
		    addAll: function() {
		    	hitmen.each(this.addOne, this);
		    }
	
	});	

	var HitManRouter = Backbone.Router.extend({
		
		hitmen: null,
	
		query: function() {
			  
			hitmen.fetch( {
	    	  	reset: true,
	    	    success: function(model, response) {
	    	        console.log(response);
	    	    },
	    	    error: function(model, response) {
	    	        console.log(response);
	    	    }
	      });
			  
		}
	
	});

	var hitmen =  function() { return new HitManList; };
	var view = function( hitmen ) { return new AppView( hitmen ); };
	var router = function( hitmen ) { return new HitManRouter( {hitmen:hitmen} ); };
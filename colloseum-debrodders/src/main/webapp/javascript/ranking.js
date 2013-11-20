	var Ranking = Backbone.Model.extend({
	
	  });

	var RankingList = Backbone.Collection.extend({
	
		    model: Ranking,
	
		    past: function() {
		      
		    },
		    
		    future: function() {
		      
		    },
		    
		    url: function() {
		    	return "rs/rankings";
		    }
	 });

	var RankingRowView = Backbone.View.extend({
	
		    tagName:  "tr",
	
		    initialize: function() {
		      this.listenTo(this.model, 'change', this.render);
		    },
		    
		    template: _.template( contemplate("template-ranking") ),
	
		    render: function() {
		    	
		    	var json = this.model.toJSON();
		    	
		    	var tmpl = this.template( json );
		    	
		    	this.$el.html( tmpl );
		    	
		    	if ( json.itIsUs == true ) {
			    	
		    		this.$el.addClass( "info" );
		    		
		    	}
		    	
		    	return this.$el;
		    },
	
		    clear: function() {
		      this.model.destroy();
		    }
	
	 });

	var AppView = Backbone.View.extend({
	
			rankings: null,
			
		    el: $jq("#ranking"),
		    
		    initialize: function() {
	
		      this.listenTo(rankings, 'reset', this.addAll );
		      this.listenTo(rankings, 'all', this.render );
	
		      this.main = $jq('#main');
	
		    },
	
		  render: function() {
		    
		      if (rankings.length) {
		        this.main.show();
		      } else {
		        this.main.hide();
		      }
	
		    },
	
		    addOne: function(ranking) {
		      var view = new RankingRowView({model: ranking});
		      $jq("#rankings").append( view.render() );
		    },
	
		    addAll: function() {
		    	rankings.each(this.addOne, this);
		    }
	
	});	

	var RankingRouter = Backbone.Router.extend({
		
		rankings: null,
	
		query: function() {
			  
		  rankings.fetch( {
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

	var rankings =  function() { return new RankingList; };
	var view = function( rankings ) { return new AppView( rankings ); };
	var router = function( rankings ) { return new RankingRouter( {rankings:rankings} ); };
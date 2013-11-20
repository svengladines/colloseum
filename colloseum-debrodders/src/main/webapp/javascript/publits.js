var Publit = Backbone.Model.extend({

	defaults: function() {
      return {
        title: "title",
        url: "url"
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
    	
    	this.$el.addClass("publit");
    	
    	if ( json["status"] ) {
    		this.$el.addClass( "publit-" + json["status"].toLowerCase() );	
    	} 
    	
    	var html = this.template( json );
    	this.$el.html( html );
    	
    	return this.$el;
    },

    clear: function() {
      this.model.destroy();
    }

});

var PublitsView = Backbone.View.extend({

    el: $jq("#publits"),
    
    publits: null,

    initialize: function() {

      this.listenTo(publits, 'reset', this.addAll );
      this.listenTo(publits, 'all', this.render );

    },

  render: function() {
    
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
	
	  publits: null,

	  list: function( publits, callback ) {
		  
		  publits.fetch( 
				{
					reset: true,
					success: function( publits ) {
						if ( callback ) {
							callback();
						}
					}
			  	});
		  
	  }
});

var publits = function() {
	return new PublitList();
};
	
var view = function( publits ) {
	return new PublitsView( publits ); 
};

var router = function( publits ) {
	return new PublitRouter( {publits: publits} );
};
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

	    comparator: function( publit ) {
	    	console.log( publit.attributes.touched );
	      return 0 - publit.attributes.touched;
	    },
	    
	    future: function() {
	      
	    },
	    
	    url: function() {
	    	return "rs/publits";
	    }
 });

var PublitView = Backbone.View.extend({

    tagName:  "li",

    initialize: function() {
      this.listenTo(this.model, 'change', this.render);
    },
    
    template: _.template( contemplate("template-publit") ),

    render: function() {
    	
    	this.$el.addClass("media");
    	
    	var json = this.model.toJSON();
    	
    	// this.$el.addClass("publit");
    	
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
    
    callback: null,
    
    publits: null,

    initialize: function() {

      //this.listenTo(publits, 'reset', this.addAll );
      this.listenTo(publits, 'sync', this.addAll );
      this.listenTo(publits, 'destroy', this.addAll );

    },

    render: function() {
    	
    },

    addOne: function(publit) {
      var view = new PublitView({model: publit});
      $jq("#publits").append( view.render() );
    },

    addAll: function() {
      $jq("#publits").html("");
      publits.each(this.addOne, this);
      this.options.callback();
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
	
var view = function( publitz, callbck ) {
	return new PublitsView( { publits : publitz, callback: callbck } ); 
};

var router = function( publits ) {
	return new PublitRouter( {publits: publits} );
};
(function(){
	'use strict';
	
	rap.registerTypeHandler("widget.gmap", {	
		
		factory : function(properties) {
			return new widget.gmap(properties);
		},
		
		destructor : "destroy",
		
		properties : ["address", "zoom", "centerLocation"]
		
	});
	
	// needed because of namespace
	if(!window.widget) {
		window.widget = {};
	}
	
	widget.gmap = function(properties) {
		// important for transferring arguments to prototype
		bindAll(this, [ "layout", "onSend", "onRender"] );
//		console.log(JSON.stringify(properties, null, 4));
		this.parent = rap.getObject(properties.parent);
		
		this.element = document.createElement("div");
		this.element.id = "gmapDiv";
		this.element.style.height = "100%";
		this.element.style.width = "100%";		
        
        this.parent.append(this.element);
        this.parent.addListener("Resize", this.layout);
        rap.on("render", this.onRender);
	};
	
	widget.gmap.prototype = {
	
		onRender : function() {
			if(!this.element) return;
			if(this.element.parentNode) {					
				// initialize map   
				if(!this._map) {
					this.initialize();
					rap.on("send", this.onSend);
				}				
		     }
		},
		
		initialize : function() {
            var zoom = this._zoom;
            if(!zoom) zoom = 13;
            var mapOptions = {
            	zoom: zoom
            };          
	        this._geocoder = new google.maps.Geocoder();
	    	this._map = new google.maps.Map(document.getElementById("gmapDiv"),mapOptions);      	                
	        this.initializeGeoCoding(this._map);
		},
		
		initializeGeoCoding : function(map) {
			var instance = this;

			if(navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(function(position) {
					var pos = new google.maps.LatLng(
							position.coords.latitude,
							position.coords.longitude);
					var infowindow = new google.maps.InfoWindow({
						map: map,
						position: pos,
						content: 'Current location.'
					});

					map.setCenter(pos);
				}, function() {
					instance.handleNoGeolocation(true);
				});
			} else {
				// Browser doesn't support Geolocation
				instance.handleNoGeolocation(false);
			}
		},
		
		handleNoGeolocation : function(errorFlag) {
			if(errorFlag) {
				var content = 'Error: The Geolocation service failed.';
			} else {
				var content = 'Error: Your browser doesn\'t support geolocation.';
			}
		},

		onSend : function() {
			rap.getRemoteObject(this).set("address", this._address);
			rap.getRemoteObject(this).set("zoom", this._zoom);
			rap.getRemoteObject(this).set("centerLocation", this._centerLocation);
		},
	
		destroy : function() {
			rap.off("send", this.onSend);
			this.element.parentNode.removeChild(this.element);
		},
		
		layout : function() {
			if(this._map != null) {
				google.maps.event.trigger(this._map, "resize");
			}
		},
			
		/* 
		 * SETTER & GETTER 
		 */
        setZoom : function(zoom) {
        	this._zoom = zoom;
        	this._map.setZoom(zoom);
        },
        
        setAddress : function(address) {
        	this._address = address;
        	var map = this._map;
        	this._geocoder.geocode({'address': address}, function(results, status) {
        		if(status == google.maps.GeocoderStatus.OK) {
        			map.setCenter(results[0].geometry.location);
        			var marker = new google.maps.Marker({
        				map: map,
        				position: results[0].geometry.location
        			});
        		} else {
        			alert('Geocode was not successful for the following reason: ' + status);
        		}
        	});
        },
        
        setCenterLocation : function(centerLocation) {
        	this._centerLocation = centerLocation;
            var addressSplit = centerLocation.split(",", 2);
            
            var lat = parseFloat(addressSplit[0]);
            var lng = parseFloat(addressSplit[1]);           
        	var latLng = new google.maps.LatLng(lat, lng)
        	
        	var map = this._map;
        	this._geocoder.geocode({'location': latLng}, function(results, status) {
        		if(status == google.maps.GeocoderStatus.OK) {
        			map.setCenter(results[0].geometry.location);
        			var marker = new google.maps.Marker({
        				map: map,
        				position: results[0].geometry.location
        			});
        		} else {
        			alert('Geocode was not successful for the following reason: ' + status);
        		}
        	});
        }
	};

	var bind = function(context, method) {
		return function() {
			return method.apply(context, arguments);
		};
	};

	var bindAll = function(context, methodNames) {
		for(var i = 0; i < methodNames.length; i++) {
			var method = context[methodNames[i]];
			context[ methodNames[i]] = bind(context, method);
		}
	};
}());
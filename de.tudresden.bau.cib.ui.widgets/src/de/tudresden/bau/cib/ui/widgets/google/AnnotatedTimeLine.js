(function(){
	'use strict';
	
	rap.registerTypeHandler( "widget.timeline", {	
		
		factory : function(properties) {
			return new widget.timeline(properties);
		},
		
		destructor : "destroy",
		
		properties : ["widgetData", "widgetOptions", "selectedRow", "selectedColumn", "selectedItem", "selectedValue"]
		
	});
	
	// needed because of namespace
	if(!window.widget) {
		window.widget = {};
	}

	var element = null;
	var parent = null;
	var widgetData = null;
	var widgetOptions = null;
	
	widget.timeline = function(properties) {
//		this.bindAll( this, [ "layout", "onReady", "onSend", "onRender" ] );
		parent = rap.getObject(properties.parent);
		widgetData = properties.widgetData;
		widgetOptions = properties.widgetOptions;
		element = document.createElement( "div" );
		element.id = "timeLineDiv";
		element.style.height = "100%";
		element.style.width = "100%";
//		parent.addListener( "Resize", this.layout );
		parent.append(element);
		rap.on("render", this.onRender);
	};
	
	widget.timeline.prototype = {
	
		onRender : function() {
			if(!element) return;
			if(element.parentNode) {
				rap.off("render", this.onRender);		
				var data = eval('(' + widgetData + ')');				
				var chart = new google.visualization.AnnotatedTimeLine(element);				
				var dataTable = new google.visualization.DataTable(data);				
		
				var options = {};
				if (widgetOptions != null) {
					options = eval('(' + widgetOptions + ')');
				}
				
				chart.draw(dataTable, options);				
	
	//			google.visualization.events.addListener(chart, 'select', function() {
	//				var selArray = chart.getSelection();
	//				var selObj = selArray[0];
	//				var selection = dataTable.getValue(selObj.row, 0) + "," + dataTable.getColumnId(selObj.column) + "," + dataTable.getValue(selObj.row, selObj.column);
	//				this.selectedItem = selection;
	//				this.selectedRow = dataTable.getValue(selObj.row, 0);
	//				this.selectedColumn = dataTable.getColumnId(selObj.column);
	//				this.selectedValue = dataTable.getValue(selObj.row, selObj.column);
		//
	//				//fire selection event
	//				var req = org.eclipse.swt.Request.getInstance();
	//				req.addParameter(widgetId + ".selectedItem", this.selectedItem);
	//				req.addParameter(widgetId + ".selectedRow", this.selectedRow);
	//				req.addParameter(widgetId + ".selectedColumn", this.selectedColumn);
	//				req.addParameter(widgetId + ".selectedValue", this.selectedValue);
	//				req.addEvent( "org.eclipse.swt.events.widgetSelected", widgetId );
	//				req.send();
	//			});
				
	//			this.parent.addListener("Resize", this.layout);
				
				rap.on("render", this.onRender);
//				rap.on( "send", this.onSend );
		     }
		},
	
		onSend : function() {
			rap.getRemoteObject(this).set("widgetData", this.widgetData);
			rap.getRemoteObject(this).set("widgetOptions", this.widgetOptions);
			rap.getRemoteObject(this).set("selectedItem", this.selectedItem);
			rap.getRemoteObject(this).set("selectedRow", this.selectedRow);
			rap.getRemoteObject(this).set("selectedColumn", this.selectedColumn);
			rap.getRemoteObject(this).set("selectedValue", this.selectedValue);
		},
	
		destroy : function() {
			rap.off("send", this.onSend);
			this.element.parentNode.removeChild(this.element);
		},
			
		// SETTER & GETTER
	
		setWidgetData : function(widgetData) {
			this._widgetData = widgetData;
		},
	
		setWidgetOptions : function(widgetOptions) {
			this._widgetOptions = widgetOptions;
		},
	
		setSelectedItem : function(selectedItem) {
			this._selectedItem = selectedItem;
		},
	
		setSelectedColumn : function(selectedColumn) {
			this._selectedColumn = selectedColumn;
		},
	
		setSelectedRow : function(selectedRow) {
			this._selectedRow = selectedRow;
		},
	
		setSelectedValue : function(selectedValue) {
			this._selectedValue = selectedValue;
		}	
	};
	
	bind = function( context, method ) {
		return function() {
			return method.apply( context, arguments );
		};
	};

	bindAll = function( context, methodNames ) {
		for( var i = 0; i < methodNames.length; i++ ) {
			var method = context[ methodNames[ i ] ];
			context[ methodNames[ i ] ] = this.bind( context, method );
		}
	};

	async = function( context, func ) {
		window.setTimeout( function(){
			func.apply( context );
		}, 0 );
	};
}());

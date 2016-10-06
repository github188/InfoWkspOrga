define(['jquery', 'undo-redo', 'configuration', 'handlebars', 'moment',
        'i18n!windows/preferences/nls/messages', 
        'text!windows/preferences/preferences.html', 
        'text!windows/preferences/preferences.css', 
        'windows/preferences/controller',
        'jquery-easyui'
       ], function($, UndoManager, configuration, Handlebars, moment, i18n, templateHtml, css, controller) {

  
/**
 * --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * Preferences Screen
 */
function PreferencesScreen(preferencesController) {
  // Initialisation du module de gestion des logs
  Trace.traceLevel("PreferencesScreen", configuration.logLevel);
  this.logger = new Trace("PreferencesScreen");

  this.compiledTemplateHtml = null;

  this.preferencesController = preferencesController;

  return this;
}

/**
 * Build UI
 */
PreferencesScreen.prototype.buildUI = function() {
	this.logger.debug("PreferencesScreen.buildUI()");
	this.initialize();
	this.createUI();
	this.createListeners();
	this.fillUI();
}

/**
 * initialize of the object needed to build screen
 */
PreferencesScreen.prototype.initialize = function() {
	this.logger.debug("PreferencesScreen.initialize()");
	
	 //Compile the template with handlebars
	 this.compiledTemplateHtml  = Handlebars.compile(templateHtml);
 };

/**
 * Build UI
 */
PreferencesScreen.prototype.createUI = function() {
  this.logger.debug("PreferencesScreen.createUI()");

  // Call Load Preferences For Project X from date to date Service
  var _this = this;
  
  
  var result = this.compiledTemplateHtml({}); //({ i18n: i18n, });
  var html = $.parseHTML(result, document, true);
  
  $('#screen').html(html);
  
  this.buildTableProperties();

};

  /**
	 * Build Table Properties
	 */
  PreferencesScreen.prototype.buildTableProperties = function() {
	  this.logger.debug("PreferencesScreen.buildTableProperties()");
	  
	  
	  $('#pGrid').propertygrid({
	  	url: 'http://localhost:8080/maze-explorer-prez/app/windows/preferences/data/preferences-data.json',
	  	width: '100%',
	  	showGroup: true,
	  	showHeader: true,
	  	scrollbarSize: 0,
	  	
	  	groupFormatter : function groupFormatter(fvalue, rows){
          return fvalue + ' - <span style="color:red">' + rows.length + ' rows</span>';
      },
	  	
      columns: [[
	  		{field:'name',title:'Name',width:200,resizable:true},
	  		{field:'value',title:'Value',width:400,resizable:true,
	  			formatter:function(value,row){
	  				if (row.name == 'My Field'){
	  					return row.label || value;
	  				} else {
	  					return value;
	  				}
	  			}
	  		}
	  	]],
	  	
	  	onBeginEdit:function(index,row){
	  		var pg = $(this);
	  		var ed = pg.propertygrid('getEditors',index)[0];
	  		if (ed){
	  			var input;
	  			if ($(ed.target).hasClass('textbox-f')){
	  				input = $(ed.target).textbox('textbox');
	  			} else {
	  				input = $(ed.target);
	  			}
	  			input.bind('keydown', function(e){
	  				if (e.keyCode == 9){	// tab
	  					var cell = pg.propertygrid('options').finder.getTr(pg[0], index+1).find('td[field="value"] div.datagrid-cell');
	  					cell.trigger('click');
	  					return false;
	  				}
	  			})
	  		}
	  	},
	  	
	  	onEndEdit: function(index,row){
	  		if (row.name == 'My Field'){
	  			var ed = $(this).datagrid('getEditors',index)[0];
	  			row.label = $(ed.target).combobox('getText');
	  		}

	  	}
	  });

	  
	  
  };


  /**
	 * create Listeners
	 */
PreferencesScreen.prototype.createListeners = function() {
	this.logger.debug("PreferencesScreen.createListeners()");
};



/**
 * fill UI
 */
PreferencesScreen.prototype.fillUI = function() {
	this.logger.debug("PreferencesScreen.fillUI()");
};

// Return the object needed to create an instance of this module
return PreferencesScreen;

});

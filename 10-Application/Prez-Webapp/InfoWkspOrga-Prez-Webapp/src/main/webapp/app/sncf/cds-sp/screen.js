define(['jquery', 'undo-redo', 'configuration', 'handlebars', 'moment',
        'i18n!sncf/cds-sp/nls/messages', 
        'text!sncf/cds-sp/pilotConsole.html', 
        'text!sncf/cds-sp/pilotConsole.css', 
        'sncf/cds-sp/controller',
        'datatable'
       ], function($, UndoManager, configuration, Handlebars, moment, i18n, templateHtml, css, controller) {

  
/**
 * --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * pilotConsole Screen
 */
function PilotConsoleScreen(pilotConsoleController) {
  // Initialisation du module de gestion des logs
  Trace.traceLevel("PilotConsoleScreen", configuration.logLevel);
  this.logger = new Trace("PilotConsoleScreen");

  this.compiledTemplateHtml = null;

  this.pilotConsoleController = pilotConsoleController;

  return this;
}

/**
 * Build UI
 */
PilotConsoleScreen.prototype.buildUI = function() {
	this.logger.debug("PilotConsoleScreen.buildUI()");
	this.initialize();
	this.createUI();
	this.createListeners();
	this.fillUI();
}

/**
 * initialize of the object needed to build screen
 */
PilotConsoleScreen.prototype.initialize = function() {
	this.logger.debug("PilotConsoleScreen.initialize()");
	
	 //Compile the template with handlebars
	 this.compiledTemplateHtml  = Handlebars.compile(templateHtml);
 };

/**
 * Build UI
 */
PilotConsoleScreen.prototype.createUI = function() {
  this.logger.debug("PilotConsoleScreen.createUI()");

  // Call Load pilotConsole For Project X from date to date Service
  var _this = this;
  
  
  var result = this.compiledTemplateHtml({}); //({ i18n: i18n, });
  var html = $.parseHTML(result, document, true);
  
  $('#screen').html(html);
  
  this.buildTableProperties();

};

  /**
	 * Build Table Properties
	 */
  PilotConsoleScreen.prototype.buildTableProperties = function() {
	  this.logger.debug("PilotConsoleScreen.buildTableProperties()");
	  
	  
	  $(document).ready(function() {
	    $('#tableNewRequest').DataTable( {
	        keys: true,
	        autoFill: false
	    } );
	} );

	  
	  
  };


  /**
	 * create Listeners
	 */
PilotConsoleScreen.prototype.createListeners = function() {
	this.logger.debug("PilotConsoleScreen.createListeners()");
};



/**
 * fill UI
 */
PilotConsoleScreen.prototype.fillUI = function() {
	this.logger.debug("PilotConsoleScreen.fillUI()");
};

// Return the object needed to create an instance of this module
return PilotConsoleScreen;

});

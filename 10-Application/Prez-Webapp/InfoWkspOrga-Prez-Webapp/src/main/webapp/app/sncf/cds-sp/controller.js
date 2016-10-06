/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

define([ 'jquery', 'undo-redo', 'configuration', 'moment', 'sncf/cds-sp/screen', 'datatable' ], function($, UndoManager, configuration, moment,
                                                                                                    pilotConsoleScreen) {

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * PilotConsoleController
	 */
	function PilotConsoleController() {
		// Initialization du module de gestion des logs
		Trace.traceLevel("PilotConsoleController", configuration.logLevel);
		this.logger = new Trace("PilotConsoleController");

		this.pilotConsoleScreen = null;
		this.undoManager = null;

		// Contains all the applicable pilotConsole in JSON format
		this.pilotConsoleData = null;

		return this;
	}

	/**
	 * initialize
	 */
	PilotConsoleController.prototype.initialize = function() {
		this.pilotConsoleScreen = new pilotConsoleScreen(this);
		this.undoManager = new UndoManager();

	};

	/**
	 * call Get pilotConsole Rest Service
	 */
	PilotConsoleController.prototype.callGetpilotConsoleRestService = function() {
	  var pivotIn = '';

	};

	/**
	 * show pilotConsole Screen
	 */
	PilotConsoleController.prototype.showScreen = function() {
		this.pilotConsoleScreen.buildUI();

	};

	return PilotConsoleController;

});

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

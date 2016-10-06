/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

define([ 'jquery', 'undo-redo', 'configuration', 'moment', 'windows/preferences/screen', 'jquery-easyui' ], function($, UndoManager, configuration, moment,
                                                                                                    PreferencesScreen) {

	// ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

	/**
	 * PreferencesController
	 */
	function PreferencesController() {
		// Initialization du module de gestion des logs
		Trace.traceLevel("PreferencesController", configuration.logLevel);
		this.logger = new Trace("PreferencesController");

		this.preferencesScreen = null;
		this.undoManager = null;

		// Contains all the applicable preferences in JSON format
		this.preferencesData = null;

		return this;
	}

	/**
	 * initialize
	 */
	PreferencesController.prototype.initialize = function() {
		this.preferencesScreen = new PreferencesScreen(this);
		this.undoManager = new UndoManager();

	};

	/**
	 * call Get Preferences Rest Service
	 */
	PreferencesController.prototype.callGetPreferencesRestService = function() {
	  var pivotIn = '';

	};

	/**
	 * show Preferences Screen
	 */
	PreferencesController.prototype.showScreen = function() {
		this.preferencesScreen.buildUI();

	};

	return PreferencesController;

});

/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

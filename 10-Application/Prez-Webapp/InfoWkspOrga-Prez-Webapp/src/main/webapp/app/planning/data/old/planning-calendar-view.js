define(['jquery', 'undo-redo', 'configuration', 'i18n!planning/nls/planning-messages',
        'text!planning/planning-calendar-view.html', 'handlebars',
        'planning/planningDataManager',
       ], function($, UndoManager, configuration, i18n, templateHtml, Handlebars) {

/**
 * ----------------------------------------------------------------------------------------------------------------------------------------
 * Planning Calendar View
 */
function PlanningCalendarView() {
  // module de gestion des logs
  this.logger = null;

  this.compiledTemplateHtml = null;
  this.planningScreen = null;

  return this;
}

/**
 * initialize the Planning Calendar View
 */
PlanningCalendarView.prototype.initialize = function(planningScreen) {
  Trace.traceLevel("PlanningCalendarView", configuration.logLevel);
  this.logger = new Trace("PlanningCalendarView");

  this.planningScreen = planningScreen;

   //Compile the template with handlebars
  this.compiledTemplateHtml  = Handlebars.compile(templateHtml);

  this.logger.debug ("PlanningCalendarView Object is initialized!");
 };




//Return the object needed to create an instance of this module
return PlanningCalendarView;

});

//{locale: 'fr'}, // lang is a global js var that gets rendered on the html page from the server. You could interrogate the browser langugage here instead via Javascript if you’d like

define(['jquery', 'configuration', 'planning-screen', 'text!application.html',  'text!menu.html', 'handlebars',
       ], function($, configuration, PlanningScreen, appLayoutTemplate, appMenuTemplate, Handlebars) {

  var application = {
  // Initialisation du module de gestion des log
  logger : null,
  
  compiledAppLayoutTemplate : null,
  compiledAppMenuTemplate : null,
  planningScreen : null,
  
  // Définition de variables globales
  isUserConnected : true,

  /**
   * initialize of the application
   */
  initialize : function() {
    // Initialisation du module de gestion des log
    Trace.traceLevel("Application", configuration.logLevel);
    this.logger = new Trace("Application");
    
    // Compile the template with handlebars 
    this.compiledAppLayoutTemplate  = Handlebars.compile(appLayoutTemplate);
    this.compiledAppMenuTemplate  = Handlebars.compile(appMenuTemplate);
    
    this.logger.debug ("Application Object is initialized!");
  },
  
  /**
   * build UI
   */
  buildUI : function () {
    var appLayout = this.compiledAppLayoutTemplate({}); //({ i18n: i18n, });
    var appLayoutHtml = $.parseHTML(appLayout, document, true); 
    $('#capla-app').html(appLayoutHtml);
    
    var appMenu = this.compiledAppMenuTemplate({}); //({ i18n: i18n, });
    var appMenuHtml = $.parseHTML(appMenu, document, true); 
    $('#capla-menu').html(appMenuHtml);
    
    this.createListeners ();
    
    $.parser.parse ('body');

    $('#txtPrjBeginDate').datebox('setValue', '01/01/2016');
    $('#txtPrjEndDate').datebox('setValue', '31/01/2016');
  },
  
  
  /**
   * createListeners
   */
  createListeners : function () {
    var _this = this;
    
    $('#btnDisplayPlanning').bind('click', function(){
      _this.displayPlanningScreen();
    });
    
  },
  
  
  /**
   * 
   */
  displayPlanningScreen : function() {
    this.logger.info('Display the first screen of the application');
    
    if (this.isUserConnected == false) {
      this.logger.info('user not connected');
    }
    else {
      this.logger.info('user already connected');
      this.planningScreen = new PlanningScreen();
      this.planningScreen.initialize();
      
      var txtPrjBeginDateValue = $('#txtPrjBeginDate').datebox('getValue');
      var txtPrjEndDateValue = $('#txtPrjEndDate').datebox('getValue');
      this.planningScreen.buildUI(txtPrjBeginDateValue, txtPrjEndDateValue);
    }

  },

  };

  return application;
  
});

/*
$(function() {
  ucManager.initialize();
});
*/
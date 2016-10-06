define(['jquery', 'moment', 'trace',
       ], function($, moment) {

/**
 * Declaration d'un objet Java
 */
var configuration = {

 // Defini le niveau de trace pour toute l'application
 logLevel : null,
 // Initialisation du module de gestion des log
 logger : null,

 // La langue par defaut à utiliser
 defaultLanguage : "fr",

 initialize : function() {
   // Initialisation du module de gestion des log
   this.logLevel = Trace.Levels.debug,
   Trace.traceLevel("Configuration", this.logLevel),
   this.logger = new Trace("Configuration"),
   moment.locale(this.defaultLanguage);

   this.logger.debug ("Configuration Object is initialized!");
 },

 /**
  * Return the log level to use
  */
 getLogLevel : function() {
   return this.logLevel;
 },

};

//This module now returns our application main object
configuration.initialize();
return configuration;

});

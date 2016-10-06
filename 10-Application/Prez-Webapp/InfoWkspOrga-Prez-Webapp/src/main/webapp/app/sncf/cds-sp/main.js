//main.js est chargé automatiquement grâce à l'attribut data-main dans la balise script de require.js
require.config({
   //Le paramètre baseUrl permet de spécifier un
  // préfixe à appliquer à tous les chemins
  // qu'utilisera RequireJS pour résoudre les chemins
  // d'accès aux modules. S'il n'est pas précisé,
  // tous les chemins sont résolus par rapport à
  // l'emplacement du fichier main.js.
  baseUrl: '../app',

  //l'objet path va permettre de faire une
  // association entre un nom de module et son
  // chemin d'accès. Si vous ne faite pas ça,
  // RequireJS considère que le nom d'un module
  // est le nom du fichier (sans l'extension .js)
  // précédé de son chemin relatif par rapport au
  // fichier main.js
  paths: {
    /* ------------------------------------------------------------------------------------ */
    'jquery':               '../lib/jquery/jquery',
    'jquery-notification':  '../lib/jquery/jquery.notification',

    /* ------------------------------------------------------------------------------------ */
    'i18n':                 '../lib/requirejs/plugins/i18n',
    'text':                 '../lib/requirejs/plugins/text',

    /* ------------------------------------------------------------------------------------ */
    'datatable':            '../lib/data-tables/media/js/jquery.dataTables',
    
    /* ------------------------------------------------------------------------------------ */
    'jquery-easyui':        '../lib/jquery-easyui/jquery.easyui.min',
    'treegrid-dnd':         '../lib/jquery-easyui/plugins/advanced/treegrid-dnd',
    'treegrid-cellediting': '../lib/jquery-easyui/plugins/advanced/treegrid-cellediting-gfi',
    /* ------------------------------------------------------------------------------------ */
    //'datagrid-scrollview':  '../lib/jquery-easyui/plugins/gfi/datagrid-scrollview-gfi',

    //'jquery.edatagrid':     '../lib/jquery-easyui/plugins/advanced/jquery.edatagrid',
    //'datagrid-bufferview' : '../lib/jquery-easyui/plugins/advanced/datagrid-bufferview',
    //'datagrid-detailview' : '../lib/jquery-easyui/plugins/advanced/datagrid-detailview',
    //'datagrid-groupview' :  '../lib/jquery-easyui/plugins/advanced/datagrid-groupview',

    /* ------------------------------------------------------------------------------------ */
    'trace':                '../lib/trace/Trace',
    'moment':               '../lib/momentjs/moment-with-locales',
    'handlebars':           '../lib/handlebars/handlebars',
    'undo-redo':            '../lib/undo-redo/undomanager',
    'collections':          '../lib/collection/collections.min',
    'json2':                '../lib/json2',
    'domReady':             '../lib/domReady',
    'html5':                '../lib/html5',

    /* ------------------------------------------------------------------------------------ 
    'pilotConsole-screen':   'windows/pilotConsole/pilotConsole-screen',*/

    /* ------------------------------------------------------------------------------------ */
    'utilApp':              '../lib/utilApp',
    'utilDebug':            '../lib/utilDebug',
  },

  //l'objet shim va permettre de spécifier les
  // dépendances entre fichiers qui n'utilisent
  // pas la fonction define et associer d'éventuelles
  // variables globales à un nom de module.
  // In other words : Sets the configuration for your third party scripts that are not AMD compatible
  shim: {

      // jQuery Easy UI
      'jquery-easyui'         : { deps: ['jquery'],
                                  // Pour les scripts qui exposent une variable
                                  // globale il faut la déclarer explicitement
                                  exports: '$'},

      'treegrid-dnd'          : { deps: ['jquery-easyui'],
                                },

      'treegrid-cellediting'  : { deps: ['jquery-easyui'],
                                 },

      'utilApp'               : { deps: ['moment'],
                                },

      /*'datagrid-excelview'    : { deps: ['datagrid-scrollview'],
                                // Pour les scripts qui exposent une variable
                                // globale il faut la déclarer explicitement
                                 exports : 'excelview'
                                },
       */
      //'datagrid.detailview'   : ['jquery-easyui'],
      //'datagrid.groupview'    : ['jquery-easyui', 'datagrid.detailview' ],
      //'jquery.edatagrid'      : ['jquery-easyui', 'datagrid.detailview' ],
      //'datagrid-scrollview'   : ['jquery-easyui', 'datagrid.detailview' ],
      //'datagrid-bufferview'   : ['jquery-easyui', 'datagrid.detailview' ],

  },

  i18n: {
    locale: 'fr'
  },
});

/**
 * Call to the tool controller initialization
 */
require([ 'jquery', 'sncf/cds-sp/controller',
          'jquery-easyui', 'trace', 'handlebars', 'moment', 'i18n', 'text', 'collections', 'undo-redo', // Load the main js files wee need in the whole application
          'configuration', 'utilApp', 'utilDebug' // Load the module needed to initialize the application
          ], function($, pilotConsoleController) {
    	      // Callback executé une fois les dépendances "js" chargées
	          var appPilotConsole = new pilotConsoleController();
	          appPilotConsole.initialize();
	          appPilotConsole.showScreen();
          });

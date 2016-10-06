define(['jquery', 'undo-redo', 'configuration', 'i18n!planning/nls/planning-messages', 'text!planning/planning.html', 'text!planning/planning.css', 'handlebars',
        'planning/planningDataManager', 'moment', 'treegrid-cellediting'
       ], function($, UndoManager, configuration, i18n, templateHtml, css, Handlebars, planningDataMng, moment) {

  
/**
 * --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
 * Planning Screen
 */
function PlanningScreen() {
  //Initialisation du module de gestion des logs
  Trace.traceLevel("PlanningScreen", configuration.logLevel);
  this.logger = new Trace("PlanningScreen");

  this.planningData = null;

  this.compiledTemplateHtml = null;

  this.planningDataManager = null;
  this.undoManager = null;

  return this;
}

/**
 * initialize of the application
 */
PlanningScreen.prototype.initialize = function() {
    this.undoManager = new UndoManager();
    // Reference submodules PlanningDataManager
    this.planningDataManager = new planningDataMng.planningDataManager ();
    this.planningDataManager.initialize();

    //Compile the template with handlebars
    this.compiledTemplateHtml  = Handlebars.compile(templateHtml);

    this.logger.debug ("PlanningScreen Object is initialized!");
 };

/**
 * Build UI
 */
PlanningScreen.prototype.buildUI = function(txtPrjBeginDateValue, txtPrjEndDateValue) {
  this.logger.debug("PlanningScreen.buildUI()");

  // Call Load Planning For Project X from date to date Service
  var _this = this;

    $.ajax({ type : "GET", async : true, url :  "app/planning/data/planning-data.json", contentType : "application/json", dataType : "json", success : function(planning_data_ctx) {
      _this.logger.debug("Call to service Loading Planning Success");
      _this.planningData = planning_data_ctx['planning-data'];

      // Initialise data used by calendar and gantt part
      var projectStartDate = moment(txtPrjBeginDateValue, "DD/MM/YYYY");
      var projectEndDate = moment(txtPrjEndDateValue, "DD/MM/YYYY");

      //--------------------
      _this.planningDataManager.setPlanningDates(projectStartDate, projectEndDate);
      var wbsHeader = _this.planningDataManager.buildWBSHeaderForViewByDay ();
      var calendarHeader = _this.planningDataManager.buildCalendarHeaderForViewByDay();
      var planningData =_this.planningDataManager.buildInitialPlanningDataFromRestJSON(_this.planningData,
                                                                                       _this.planningData.rows.length,
                                                                                       wbsHeader.headerRow_4.length + calendarHeader.headerRow_4.length);


      // Build Planning View
      _this.buildTableUI(wbsHeader, calendarHeader, planningData);

    }, error : function(msg) {
      _this.logger.debug("error");
      alert('error : ' + msg.d);
    } });

};

  /**
   * Build Table UI
   */
  PlanningScreen.prototype.buildTableUI = function(wbsHeader, calendarHeader, planningData) {
    this.logger.debug("PlanningScreen.buildUI()");
    var _this = this;
    var result = this.compiledTemplateHtml(); //({ i18n: i18n, });
    //var html = $.parseHTML(result, document, true);
    $('#capla-planning').html(result);

    // Finalisation de la configuration qui ne peut pas �tre effectu�e en HTML
    $('#planning-view').treegrid({
      view: planningView,
      rownumbers:true,
      animate:true,
      data: planningData,
      idField: 'id',
      treeField: 'task',
      frozenColumns : this.buildHeaderColumms (wbsHeader, true),
      columns:this.buildHeaderColumms (calendarHeader, false),
      onAfterEdit: function(index, row, changes) {
        _this.planningDataManager.updateSumByRowAndSumByColumn(index,row,changes);
      }
    });

    
    $('#planning-view').treegrid('enableCellEditing');
    $('#planning-view').treegrid('gotoCell', {index: 0,field: '12-01-2016'});
    
    // By default expand root node
    $('#planning-view').treegrid('expand', 0);

  };

  /**
   * build Header columns Row
   */
  PlanningScreen.prototype.buildHeaderColumms = function(planningHeader, isWbs) {

    var headerColumns = [];

    // ----------------------------
    var rowMonth = [];
    for(var i=0; i<planningHeader.headerRow_1.length; i++){

      rowMonth.push({
        id: planningHeader.headerRow_1[i].id,
        title: planningHeader.headerRow_1[i].label,
        colspan:planningHeader.headerRow_1[i].colspan,
        rowspan:planningHeader.headerRow_1[i].rowspan,
        width:planningHeader.headerRow_1[i].width,
        align:planningHeader.headerRow_1[i].align,
        colindex: planningHeader.headerRow_1[i].colindex,
        colclass: planningHeader.headerRow_1[i].colclass,
        editor:{type:'numberbox',options:{precision:3}},

      });
    }
    headerColumns.push(rowMonth);

//----------------------------
    var rowDay = [];
    for(var i=0; i<planningHeader.headerRow_2.length; i++) {

      rowDay.push({
        id: planningHeader.headerRow_2[i].id,
        title: planningHeader.headerRow_2[i].label,
        colspan:planningHeader.headerRow_2[i].colspan,
        rowspan:planningHeader.headerRow_2[i].rowspan,
        width:planningHeader.headerRow_2[i].width,
        align:planningHeader.headerRow_2[i].align,
        colindex: planningHeader.headerRow_2[i].colindex,
        colclass: planningHeader.headerRow_2[i].colclass,
        editor:{type:'numberbox',options:{precision:3}},
      });
    }
    headerColumns.push(rowDay);

//----------------------------
    var rowWeek = [];
    for(var i=0; i<planningHeader.headerRow_3.length; i++){
      rowWeek.push({
        id: planningHeader.headerRow_3[i].id,
        title: planningHeader.headerRow_3[i].label,
        colspan:planningHeader.headerRow_3[i].colspan,
        rowspan:planningHeader.headerRow_3[i].rowspan,
        width:planningHeader.headerRow_3[i].width,
        align:planningHeader.headerRow_3[i].align,
        editor:{type:'numberbox',options:{precision:3}},
        colindex: planningHeader.headerRow_3[i].colindex,
        colclass: planningHeader.headerRow_3[i].colclass,
        styler:function(value, row, index) {
          return 'background-color:#ffee00;color:black;font-weight:bold;';
        },
      });
    }
    headerColumns.push(rowWeek);

    //----------------------------
    var rowSumAffected = [];
    for(var i=0; i<planningHeader.headerRow_4.length; i++) {
      rowSumAffected.push({
        id: planningHeader.headerRow_4[i].id,
        title: planningHeader.headerRow_4[i].label,
        colspan:planningHeader.headerRow_4[i].colspan,
        rowspan:planningHeader.headerRow_4[i].rowspan,
        field:planningHeader.headerRow_4[i].field,
        width:planningHeader.headerRow_4[i].width,
        align:planningHeader.headerRow_4[i].align,
        editor:{type:'numberbox',options:{precision:3}},
        colindex: planningHeader.headerRow_4[i].colindex,
        colclass: planningHeader.headerRow_4[i].colclass,

        styler:function(value, row, index) {
          if (isWbs == false) {
            if (value > 1.0) {
              return 'background-color:#ffee00;color:red;font-weight:bold;';
            }
            else if (value > 0.0 && value <= 1.0) {
              return 'background-color:green;color:white;font-weight:bold;';
            }
            else if (planningHeader.headerRow_4[index].colclass.indexOf ("week-end") != -1){
              return 'background-color:#c9c9c9;';
            }
          }
        },
      });
    }
    headerColumns.push(rowSumAffected);

    return headerColumns;
  }

  /**
   * --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
   *  Planning View
   */
  var planningView = $.extend({},$.fn.treegrid.defaults.view,{
    onBeforeRender:function(target, parentId, data){
      $.fn.treegrid.defaults.view.onBeforeRender.call(this,target,parentId,data);
      
      var data = $(target).treegrid('getData');
      
      function setData(){ 
        var todo = []; 
        for(var i=0; i<data.length; i++){
          var node = data[i];
          if (!node.setted){
            node.setted = true;
            todo.push(node);
          }       
        } 
        while(todo.length){ 
          var node = todo.shift(); 
          if (node.children){ 
            node.state = 'closed'; 
            node.children1 = node.children; 
            node.children = undefined; 
            todo = todo.concat(node.children1); 
          } 
        } 
      } 
       
      setData(data); 
      var tg = $(target); 
      var opts = tg.treegrid('options'); 
      opts.onBeforeExpand = function(row){ 
        if (row.children1){ 
          tg.treegrid('append',{ 
            parent: row[opts.idField], 
            data: row.children1 
          }); 
          row.children1 = undefined; 
          tg.treegrid('expand', row[opts.idField]); 
          tg.treegrid('enableDnd', row?row.id:null);
        } 
        return row.children1 == undefined; 
      }; 
    }
  });
  
  

//Return the object needed to create an instance of this module
return PlanningScreen;

});

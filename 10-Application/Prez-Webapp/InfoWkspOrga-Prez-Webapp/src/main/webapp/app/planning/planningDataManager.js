/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

define(['jquery', 'moment', 'configuration', 'collections', 'utilCapla'
       ], function($, moment, configuration) {

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * PlanningDataManager
 */
function PlanningDataManager(viewType) {
  // module de gestion des logs
  this.logger = null;

  this.viewType = viewType == undefined ? 'day' : viewType;

  // Start date of the planning (= beginning date of the project)
  this.startDate = null;
  // End date of the planning (= date end of the project)
  this.endDate = null;

  this.wbsHeaderRowManager = [];
  this.calHeaderRowManager = [];
  
  this.weekColumnId = [];
  this.weekEndColumnId = [];

  // Contains all the task and some affectation
  this.planningData = null;
  // Variable used to store children task to optimize first display of the planning
  this.taskChildrenMap = null;

  this.sumByColumnMap = null;

  return this;
}

/**
 * initialize
 */
PlanningDataManager.prototype.initialize = function() {
  Trace.traceLevel("PlanningDataManager", configuration.logLevel);
  this.logger = new Trace("PlanningDataManager");
};

/**
 * setProjectDates
 */
PlanningDataManager.prototype.setPlanningDates = function(startDate, endDate) {
  // We compute the start date of the planning to begin with the begenning of a week
  this.startDate = moment("01/" + startDate.format('MM') + "/" + startDate.format('YYYY'), "DD/MM/YYYY");

  // We compute the end date of the planning to end with the end of a week
  this.endDate = moment(getNbDaysInMonth(endDate.format('MM'), endDate.format('YYYY')) + "/" + endDate.format('MM') + "/" + endDate.format('YYYY'), "DD/MM/YYYY");
};

/**
 * getDateUsedAsJQueryIdentifier
 */
PlanningDataManager.prototype.getProjectFirstDay = function() {
  var result = this.startDate.format("DD-MM-YYYY");
  return result;
}

/**
 * Get dayAtColumnIndex
 *@return moment objet
 */
PlanningDataManager.prototype.getDayAtColumnIndex = function(columnIndex) {
  return moment(this.startDate).add('days', columnIndex);
}

/**
 * getColumnIndexForDay
 */
PlanningDataManager.prototype.getColumnIndexForDay = function(momentDay) {
  var columnIndexForDay = 0;
  for (var d = moment(this.startDate); d.isBefore(momentDay); d = moment(d).add('days', 1)) {
    columnIndexForDay++;
  }

  return columnIndexForDay;
};

  /*
  PlanningDataManager.prototype.getChildrenRowsData = function(selectedRowToOpen) {
    if (selectedRowToOpen == null) {
      var firstRowsToDisplay = [];
      firstRowsToDisplay.push (this.planningData.rows[0]);
      var children = this.taskChildrenMap.get (this.planningData.rows[0].id);
      for (var i = 0; i < children.length; i++) {
        firstRowsToDisplay.push(children[i]);
      }

      return firstRowsToDisplay;
    }

  };
  */

/**
 * build WBS Header View
 */
PlanningDataManager.prototype.buildWBSHeaderForViewByDay = function() {

  var wbsHeader = [];
  var dummyHeader_2 = []
  var dummyHeader_3 = []
  var wbsDetailHeader = [];

  var wbsHeaderVo = new HeaderColumnVo();
  wbsHeaderVo.label = "WBS - (Working Breakdown Structure)";
  wbsHeaderVo.colclass = "month ";
  wbsHeaderVo.colspan = 4;
  wbsHeader.push (wbsHeaderVo);

  //--------------------------------------------------------------------
  var wbsDummyHeaderVo_2 = new HeaderColumnVo();
  wbsDummyHeaderVo_2.label = "";
  wbsDummyHeaderVo_2.colclass = "";
  wbsDummyHeaderVo_2.colspan = 4;
  dummyHeader_2.push (wbsDummyHeaderVo_2);

  //--------------------------------------------------------------------
  var wbsDummyHeaderVo_3 = new HeaderColumnVo();
  wbsDummyHeaderVo_3.label = "";
  wbsDummyHeaderVo_3.colclass = "";
  wbsDummyHeaderVo_3.colspan = 4;
  dummyHeader_3.push (wbsDummyHeaderVo_3);

  // --------------------------------------------------------------------
  var wbsHeaderIdVo = new HeaderColumnVo();
  wbsHeaderIdVo.id = "id";
  wbsHeaderIdVo.field = "id";
  wbsHeaderIdVo.label = "Id";
  wbsHeaderIdVo.width = 50;
  wbsHeaderIdVo.colindex = 0;
  wbsDetailHeader.push (wbsHeaderIdVo);

  var wbsHeaderTaskVo = new HeaderColumnVo();
  wbsHeaderTaskVo.id = "task";
  wbsHeaderTaskVo.field = "task";
  wbsHeaderTaskVo.label = "Task";
  wbsHeaderTaskVo.width = 250;
  wbsHeaderTaskVo.colindex = 1;
  wbsDetailHeader.push (wbsHeaderTaskVo);

  var wbsHeaderResourceVo = new HeaderColumnVo();
  wbsHeaderResourceVo.id = "resource";
  wbsHeaderResourceVo.field = "resource";
  wbsHeaderResourceVo.label = "Resource";
  wbsHeaderResourceVo.width = 180;
  wbsHeaderResourceVo.colindex = 2;
  wbsDetailHeader.push (wbsHeaderResourceVo);


  var wbsHeaderChargeVo = new HeaderColumnVo();
  wbsHeaderChargeVo.id = "charge";
  wbsHeaderChargeVo.field = "charge";
  wbsHeaderChargeVo.label = "Charge Aff.";
  wbsHeaderChargeVo.width = 80;
  wbsHeaderChargeVo.colindex = 3;
  wbsHeaderChargeVo.align = 'right';
  wbsDetailHeader.push (wbsHeaderChargeVo);


  this.wbsHeaderRowManager = new HeaderRowManager();
  this.wbsHeaderRowManager.headerRow_1 = wbsHeader;
  this.wbsHeaderRowManager.headerRow_2 = dummyHeader_2;
  this.wbsHeaderRowManager.headerRow_3 = dummyHeader_3;
  this.wbsHeaderRowManager.headerRow_4 = wbsDetailHeader;

  return this.wbsHeaderRowManager;

}

/**
 * build Calendar Header View By Day
 */
PlanningDataManager.prototype.buildCalendarHeaderForViewByDay = function() {

  var monthHeader = [];
  var dayHeader = [];
  var weekHeader = [];
  var rowSumAffected = [];
  
  this.weekOfYearColumnId = [];
  this.weekEndColumnId = [];

  // We always build all months list because it is light (only the first time)
  for (var d = moment(this.startDate); d.isSameOrBefore(this.endDate); d = d.add(1, 'months')) {
    var monthHeaderVo = new HeaderColumnVo();
    var month = d.format('MM')
    var year = d.format('YYYY');
    monthHeaderVo.id = month + "-" + year;
    monthHeaderVo.label = getMonthName (month)  + "    -    " + year;
    monthHeaderVo.colclass = "month ";
    monthHeaderVo.colspan = getNbDaysInMonth(month, year);
    monthHeader.push(monthHeaderVo);
  }

  //--------------------------------------------------------------------------------------------------
  var index = 4;
  for (var j = moment(this.startDate.toDate()); j.isSameOrBefore(this.endDate); j = j.add(1, 'days'), index++) {

    var weekOfYear = j.isoWeek();
    var dayOfWeek = j.isoWeekday();
    var date = j.format('DD-MM-YYYY');

    var style = "";
    
    if (dayOfWeek == 6 || dayOfWeek == 7) {
      style = "-week-end";
      this.weekEndColumnId.push(date);
    }
    
    // Becarefull of the order of calls
    var dayHeaderVo = new HeaderColumnVo();
    dayHeaderVo.id = date + style;
    dayHeaderVo.label = j.format('DD');  // Day of month 01
    dayHeaderVo.colclass = style;
    dayHeader.push(dayHeaderVo);

    var rowSumAffectedHeaderVo = new HeaderColumnVo();
    rowSumAffectedHeaderVo.id = date + "-sum-aff" + style;
    rowSumAffectedHeaderVo.label = ''; //columnSum;
    rowSumAffectedHeaderVo.field = date;
    rowSumAffectedHeaderVo.colclass = style; // + "col-sum-aff ";
    rowSumAffectedHeaderVo.width = 37;
    rowSumAffectedHeaderVo.colindex = index;
    rowSumAffectedHeaderVo.align = 'center';
    rowSumAffected.push(rowSumAffectedHeaderVo);

    var dayLetter = j.format("dd").charAt(0);
    if (dayOfWeek == 1) {
      style = "-week-of-year";
      this.weekOfYearColumnId.push(date);

      var result = weekOfYear;
      if (result < 10) {
        result = "0" + result;
      }
      dayLetter = "S" + result;
    }    
    
    var weekHeaderVo = new HeaderColumnVo();
    weekHeaderVo.id = date + style;
    weekHeaderVo.label = dayLetter;
    weekHeaderVo.colclass = style;
    weekHeader.push(weekHeaderVo);
  }


  this.calHeaderRowManager = new HeaderRowManager();
  this.calHeaderRowManager.headerRow_1 = monthHeader;
  this.calHeaderRowManager.headerRow_2 = dayHeader;
  this.calHeaderRowManager.headerRow_3 = weekHeader;
  this.calHeaderRowManager.headerRow_4 = rowSumAffected;


  return this.calHeaderRowManager;
};

/**
 * get Nb Day Between Start And End
 */
PlanningDataManager.prototype.getNbDayBetweenStartAndEnd = function() {
  var result = this.endDate.diff(this.startDate, 'days') + 1;

  return result;
};


/**
 * build Table Data JSon
 */
PlanningDataManager.prototype.buildInitialPlanningDataFromRestJSON = function(planningData, nbRow, nbDays) {
  var serviceStart = new Date();

  this.planningData = planningData;
  this.sumByColumnMap = new Map();
  this.taskChildrenMap = new Map();

  var _this = this;
  // Iterate other all tasks to calculate Sum in column
  $.each(this.planningData.rows, function(i, task) {
    //_this.logger.debug (i + " = " + task);
    var children = _this.taskChildrenMap.get(task._parentId);
    if (children == undefined) {
      children = [];
      _this.taskChildrenMap.set(task._parentId, children);
    }
    children.push(task);


    // Sum Computation
    $.each(task, function(att, value) {
      if (att.indexOf("-") > -1) {
        var computedSum = _this.sumByColumnMap.get (att);
        var newSum =  computedSum == (null || undefined) ? 0 : computedSum;
         newSum = getAsNumber (newSum) +  getAsNumber(value);
        //_this.logger.debug (att + " = " + newSum);
        _this.sumByColumnMap.set(att, newSum);
      }
    });
  });

  // Update calHeaderRowManager with computedSum
  $.each(_this.sumByColumnMap.keys(), function(i, day) {
    //_this.logger.debug (i + " = " + day);
    var columnIndex = _this.getColumnIndexForDay (moment (day, 'DD-MM-YYYY'));
    try {
      _this.calHeaderRowManager.headerRow_4[columnIndex].label = _this.sumByColumnMap.get(day);
    }catch (err) {
      _this.calHeaderRowManager;
    }
  });

  //displayServiceDuration (serviceStart);

   return planningData;

};


//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * HeaderColumnVo
 */
function HeaderColumnVo(label, colspan) {
  // Column ID
  this.id = null;
  // FieldID used to retrieve column_row value
  this.field = null;
  this.label = label == undefined ? "column x" : label;
  this.colclass = '';
  this.colindex = '';
  this.colspan = colspan == undefined ? null : label;
  this.rowspan = '';
  this.width = '';
  this.align = '';
  

  return this;
}

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

/**
 * HeaderRowsManager
 */
function HeaderRowManager() {
  this.displayMode = 'day';
  this.headerRow_1 = [];  // By default Month
  this.headerRow_2 = [];  // By default Day
  this.headerRow_3 = [];  // By default Week day (L, M , M, J, V, S, D)
  this.headerRow_4 = [];  // By sum Affected
  this.nbMaxColumn = 0;

  return this;
}

/**
 * count nb max column to draw
 */
HeaderRowManager.prototype.getNbMaxColumn = function () {
 if (this.nbMaxColumn == 0) {
  this.nbMaxColumn = this.headerRow_4.length;

  if (this.headerRow_3.length > this.nbMaxColumn) {
    this.nbMaxColumn = this.headerRow_3.length;
  }
  if (this.headerRow_2.length > this.nbMaxColumn) {
    this.nbMaxColumn = this.headerRow_2.length;
  }
  if (this.headerRow_1.length > this.nbMaxColumn) {
    this.nbMaxColumn = this.headerRow_1.length;
  }
 }

  return this.nbMaxColumn;
};

//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

// http://stackoverflow.com/questions/9806940/requirejs-two-classes-in-one-file
// Store the visible subModules of this file
var exports = {};
//Add headerRowManager to list of this file objects
exports.headerRowManager = HeaderRowManager;
//Add HeaderColumnVo to list of this file objects
exports.headerColumnVo = HeaderColumnVo;
//Add PlanningDataManager to list of this file objects
exports.planningDataManager = PlanningDataManager;

return exports;
});
/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/

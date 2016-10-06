/**
 * DateCalculator
 */
function DateCalculator(startDate, endDate) {
  var firstDayOfStartMonth = moment ("01/" + startDate.format('MM') + "/" + startDate.format('YYYY'), "DD/MM/YYYY");
  var endDateMonth = endDate.format('MM');
  var endDateYear = endDate.format('YYYY');
  var endDateDays = new MonthVo (endDateMonth, endDateYear).getNbDaysInMonth ();
  var lastDayOfEndMonth =  moment (endDateDays + "/" + endDateMonth + "/" + endDateYear, "DD/MM/YYYY");
  
  this.startDate = firstDayOfStartMonth;
  this.endDate = lastDayOfEndMonth;
  this.viewType = 'days';
  this.firstWeekDay = 'monday';
  this.monthList = [];
  this.dayList = [];

  return this;
};


DateCalculator.prototype.buildMonthList = function(viewType) {
  this.monthList = [];
    
  for (d = this.startDate; d.isBefore(this.endDate); d = d.add('months', 1)) {
    var monthVo = new MonthVo (d.format('MM'), d.format('YYYY'));
    this.monthList.push (monthVo);
    document.getElementById('content').innerHTML = document.getElementById('content').innerHTML + d.format("DD/MM/YYYY"); 
    document.getElementById('content').innerHTML = document.getElementById('content').innerHTML + " --> NbDays = " + monthVo.getNbDaysInMonth ();
    document.getElementById('content').innerHTML = document.getElementById('content').innerHTML + "<br>";
  }
  
  document.getElementById('content').innerHTML = document.getElementById('content').innerHTML + this.monthList.length;
};

DateCalculator.prototype.buildDayList = function(viewType) {
  this.dayList = [];
  
  for (d = this.startDate; d.isBefore(this.endDate); d = d.add('days', 1)) {
    var dayVo = new DayVo (d);
    this.dayList.push (dayVo);
    document.getElementById('content').innerHTML = document.getElementById('content').innerHTML + dayVo.moment.format("DD/MM/YYYY"); 
    document.getElementById('content').innerHTML = document.getElementById('content').innerHTML + " --> Letter = " + dayVo.getDayLetter ();
    document.getElementById('content').innerHTML = document.getElementById('content').innerHTML + " --> Day Of Month = " + dayVo.getDayOfMonth ();
    document.getElementById('content').innerHTML = document.getElementById('content').innerHTML + "<br>";
  }
};


/*-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------*/


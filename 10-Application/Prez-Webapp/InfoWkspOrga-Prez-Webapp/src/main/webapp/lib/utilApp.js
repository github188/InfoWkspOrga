/**
 * @brief Wait for something to be ready before triggering a timeout
 * @param {callback} isready Function which returns true when the thing we're waiting for has happened
 * @param {callback} success Function to call when the thing is ready
 * @param {callback} error Function to call if we time out before the event becomes ready
 * @param {int} count Number of times to retry the timeout (default 300 or 6s)
 * @param {int} interval Number of milliseconds to wait between attempts (default 20ms)
 *
 * To call this, for example in jQuery, use something like:
 *
 * <pre>
 * waitUntil(function() {
 *   return $('#myelement').length &gt; 0;
 * }, function() {
 *   alert(&quot;myelement now exists&quot;);
 * }, function() {
 *   alert(&quot;I'm bored. I give up.&quot;);
 * });
 * </pre>
 */
function waitUntil(isready, success, error, count, interval) {
    if (count === undefined) {
        count = 300;
    }
    if (interval === undefined) {
        interval = 20;
    }
    if (isready()) {
        success();
        return;
    }
    // The call back isn't ready. We need to wait for it
    setTimeout(function () {
        if (!count) {
            // We have run out of retries
            if (error !== undefined) {
                error();
            }
        }
        else {
            // Try again
            waitUntil(isready, success, error, count - 1, interval);
        }
    }, interval);
}

/**
 * Utilise l'objet date pour provoquer une attente
 * @param ms
 */
function delay(ms) {
    var cur_d = new Date();
    var cur_ticks = cur_d.getTime();
    var ms_passed = 0;
    while (ms_passed < ms) {
        var d = new Date();  // Possible memory leak?
        var ticks = d.getTime();
        ms_passed = ticks - cur_ticks;
        d = null;  // Prevent memory leak?
    }
}

/**
 * Permet d'ajouter une propriété à un objet d'une librairie externe Utile dans le cas du mode closure (changement de reference d'objet)
 */
var addPropertyToObject = function (obj, key, value) {
    var config = {value: value, writable: true, enumerable: true, configurable: true};
    Object.defineProperty(obj, key, config);
};


/**
 *
 * @param htmlWithScript
 * @returns {string}
 */
function removeScriptsTag(htmlWithScript) {
  var div = document.createElement('div');
  div.innerHTML = htmlWithScript;
  var scripts = div.getElementsByTagName('script');
  var i = scripts.length;
  while (i--) {
    scripts[i].parentNode.removeChild(scripts[i]);
  }
  return div.innerHTML;
}
/**
 *
 * @param htmlWithScript
 * @returns {string}
 */
function removeEnclosingScriptsTag(htmlWithScript) {
    var str = htmlWithScript;
    str = str.replace(/<script(.*)>/ig, "");
    str = str.replace("<\/script>", "");

    return str;
}

/**
 * Used at first for number in the calendar
 * @param value
 * @returns
 */
function getAsNumber (value) {
  var result = "" + value;
  result = result.replace(",", ".");

  if ($.isNumeric(result) == false) {
   return 0;
  }

  return parseFloat(result.replace(",", "."));
}

/**
* Used at first for number in the calendar
* @param value
* @returns
*/
function displayAsNumber (value) {
  var result = "" + value;
  result = result.replace(",", ".");

  if ($.isNumeric(result) == false || result == 0) {
    return "";
   }

  var result = "" + getAsNumber(result);

  return result.replace(".", ",");
}

/**
 * Used to merge 2 tables in the first one
 * @param idTable01 the destination table id
 * @param idTable02 the source table id
 * @returns Table02 merge with Table01
 */
function mergeTable2InTable1(idTable01, idTable02) {
  $("#" + idTable01 + " thead tr").each(function (i) {
    $(this).append($("#" + idTable02 + " thead tr").eq(i).find("th"));
  });

  $("#" + idTable01 + " tbody tr").each(function (i) {
    $(this).append($("#" + idTable02 + " tbody tr").eq(i).find("td"));
  });

  $("#" + idTable01 + " tfoot tr").each(function (i) {
    $(this).append($("#" + idTable02 + " tfoot tr").eq(i).find("td"));
  });
}

/**
 * get URL Parameter
 */
function getURLParameter(name) {
  return decodeURIComponent((new RegExp('[?|&]' + name + '=' + '([^&;]+?)(&|#|;|$)').exec(location.search) || [null, ''])[1].replace(/\+/g, '%20')) || null;
}

/**
 * get JSon Object Attribut Names
 */
function getJSonObjectAttributeNames(obj) {
  var cols = new Array();
  for (var key in obj) {
      //alert(' name=' + key + ' value=' + obj[key]);
      cols.push(key);
  }
  return cols;
}

/**
 *
 */
function getNbDaysInMonth (monthNumber, year) {
  var nbDays = 0;

  switch (monthNumber) {
    case "01":
      nbDays = 31;
      break;
    case "02":
      nbDays = new Date(year, monthNumber, 0).getDate();
      break;
    case "03":
      nbDays = 31;
      break;
    case "04":
      nbDays = 30;
      break;
    case "05":
      nbDays = 31;
      break;
    case "06":
      nbDays = 30;
      break;
    case "07":
      nbDays = 31;
      break;
    case "08":
      nbDays = 31;
      break;
    case "09":
      nbDays = 30;
      break;
    case "10":
      nbDays = 31;
      break;
    case "11":
      nbDays = 30;
      break;
    case "12":
      nbDays = 31;
      break;
    default:
      nbDays = "Mois Inconnu : " + monthNumber;
      break;
  }

  return nbDays;
};

function getMonthName (monthNumber) {
  var name = "";

  switch (monthNumber) {
    case "01":
      name = "Janvier";
      break;
    case "02":
      name = "Février";
      break;
    case "03":
      name = "Mars";
      break;
    case "04":
      name = "Avril";
      break;
    case "05":
      name = "Mai";
      break;
    case "06":
      name = "Juin";
      break;
    case "07":
      name = "Juillet";
      break;
    case "08":
      name = "Août";
      break;
    case "09":
      name = "Septembre";
      break;
    case "10":
      name = "Octobre";
      break;
    case "11":
      name = "Novembre";
      break;
    case "12":
      name = "Décembre";
      break;
    default:
      name = "Mois Inconnu : " + monthNumber;
      break;
  }

  return name;
}

/**
 * display Service Duration
 */
function displayServiceDuration (serviceStartDate) {
  var serviceEndDate = new Date();
  var duration = serviceEndDate.getMilliseconds() - serviceStartDate.getMilliseconds();
  alert ("duration = " + duration);
}

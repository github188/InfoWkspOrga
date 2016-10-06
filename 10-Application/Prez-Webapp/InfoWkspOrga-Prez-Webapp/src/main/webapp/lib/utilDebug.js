/**
 * Can be used to determine an object type
 * @param obj the instance of the object
 * @param objName the object name
 */ 
function showProps(obj, objName) {
  var result = "";
  for (var i in obj) {
    if (obj.hasOwnProperty(i)) {
      result += objName + "." + i + " = " + obj[i] + "\n";
    }
  }
  alert (result);
}

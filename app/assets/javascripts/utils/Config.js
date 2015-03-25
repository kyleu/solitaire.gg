define(function () {
  "use strict";
  var c = document.getElementById('scalataire-config');
  if(c === undefined) {
    throw "NoConfigurationException";
  }
  var json = JSON.parse(c.innerText);
  json.wsUrl = "ws://" + document.location.host + "/websocket";
  return json;
});

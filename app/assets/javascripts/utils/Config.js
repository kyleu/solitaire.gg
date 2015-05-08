define(function () {
  "use strict";

  var c = document.getElementById('solitaire-gg-config');
  if(c === undefined) {
    throw "NoConfigurationException";
  }
  var json = JSON.parse(c.innerHTML);
  json.wsUrl = "ws://" + document.location.host + "/websocket";
  return json;
});

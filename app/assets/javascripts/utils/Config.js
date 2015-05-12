define(function () {
  "use strict";

  var c = document.getElementById('solitaire-gg-config');
  if(c === undefined) {
    throw "NoConfigurationException";
  }
  var json = JSON.parse(c.innerHTML);
  if(document.location.href.indexOf("https") === 0) {
    json.wsUrl = "wss://" + document.location.host + "/websocket";
  } else {
    json.wsUrl = "ws://" + document.location.host + "/websocket";
  }
  return json;
});

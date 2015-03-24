define(function () {
  "use strict";
  var c = window.scalataireConfig;
  if(c === undefined) {
    throw "NoConfigurationException";
  }
  c.wsUrl = "ws://" + document.location.host + "/websocket";
  return c;
});

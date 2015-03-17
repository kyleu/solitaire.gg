define(function () {
  "use strict";
  var c = window.scalataireConfig;
  c.wsUrl = "ws://" + document.location.host + "/websocket";
  return c;
});

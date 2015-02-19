define(function () {
  "use strict";

  var Config = {
    id: "scalataire",
    name: "Scalataire",
    debug: true,
    wsUrl: "ws://" + document.location.host + "/websocket"
  };

  return Config;
});

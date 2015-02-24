define(function () {
  "use strict";

  var Config = {
    id: "scalataire",
    name: "Scalataire",
    debug: true,
    wsUrl: "ws://" + document.location.host + "/websocket",
    cardHeight: 300,
    cardWidth: 200,
  };

  return Config;
});

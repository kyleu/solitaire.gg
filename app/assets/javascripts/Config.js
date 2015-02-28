define(function () {
  "use strict";

  var c = window.scalataireConfig;

  return {
    id: c.id,
    name: c.name,
    debug: c.debug,
    autoStart: c.autoStart,
    cardSet: c.cardSet,
    wsUrl: "ws://" + document.location.host + "/websocket"
  };
});

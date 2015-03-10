define(function () {
  "use strict";

  var c = window.scalataireConfig;

  return {
    id: c.id,
    name: c.name,
    debug: c.debug,
    cardSet: c.cardSet,
    cardSize: c.cardSize,
    initialAction: c.initialAction,
    wsUrl: "ws://" + document.location.host + "/websocket"
  };
});

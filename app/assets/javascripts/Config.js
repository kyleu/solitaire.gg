define(function () {
  "use strict";

  function logMessage(level, msg) {
    console.log(level, msg);
  }

  var Config = {
    id: "scalataire",
    name: "Scalataire",
    debug: true,
    wsUrl: "ws://localhost:9000/websocket",

    log: {
      debug: function(msg) {
        logMessage("[DEBUG] ", msg);
      },
      info: function(msg) {
        logMessage("[INFO] ", msg);
      },
      warn: function(msg) {
        logMessage("[WARN] ", msg);
      },
      error: function(msg) {
        logMessage("[ERROR] ", msg);
      }
    }
  };

  return Config;
});

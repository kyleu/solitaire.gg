define([], function() {
  "use strict";

  var connectionElement = document.getElementById('status-connection');
  var usernameElement = document.getElementById("status-username");
  var fpsElement = document.getElementById('status-fps');
  var latencyElement = document.getElementById('status-latency');
  var versionElement = document.getElementById('status-version');

  return {
    setConnectionStatus: function(cs) {
      connectionElement.innerText = cs;
    },

    setUsername: function(username) {
      usernameElement.innerText = username;
    },

    setFps: function(fps) {
      fpsElement.innerText = fps;
    },

    setLatency: function(started) {
      latencyElement.innerText = (new Date().getTime() - started).toString();
    },

    setVersion: function(version) {
      versionElement.innerText = version;
    }
  };
});

define(function () {
  "use strict";

  function Websocket(url, onConnect, onMessage) {
    this.url = url;

    var ws = new WebSocket(url);
    ws.onopen = function(event) {
      console.log("Connection opened to [" + url + "].");
      onConnect(url);
    };
    ws.onmessage = function(event) {
      var json = JSON.parse(event.data);
      onMessage(json.c, json.v);
    };
    this.connection = ws;
  }

  Websocket.prototype.send = function(c, v) {
    var msg = { "c": c, "v": v };
    this.connection.send(JSON.stringify(msg));
  };

  return Websocket;
});

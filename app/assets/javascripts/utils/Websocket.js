define(["utils/Config"], function (cfg) {
  "use strict";

  function Websocket(url, statusObj, messageObj) {
    this.url = url;
    this.connected = false;

    var me = this;
    var ws = new WebSocket(this.url);
    ws.onopen = function() {
      me.connected = true;
      statusObj.onConnect(me.url);
    };
    ws.onmessage = function(event) {
      var json = JSON.parse(event.data);
      if(json.c === "Pong") {
        //console.log("Received message [Pong].");
      } else if(json.c === "MessageSet") {
        var messages = "";
        for(var messageIndex in json.v.messages) {
          if(messageIndex > 0) {
            messages += ", ";
          }
          messages += json.v.messages[messageIndex].c;
        }
        console.log("Received message [MessageSet] containing [" + messages + "].");
      } else {
        console.log("Received message [" + json.c + "].");
      }
      messageObj.onMessage(json.c, json.v);
    };
    ws.onclose = function() {
      me.connected = false;
      console.info("Websocket connection to [" + me.url + "] closed.");
    };
    ws.onerror = function() {
      me.connected = false;
      console.error("Received error from websocket connection to [" + me.url + "].");
    };
    this.connection = ws;
  }

  Websocket.prototype.send = function(c, v) {
    var msg = { "c": c, "v": v };
    var s = null;
    if(cfg.debug) {
      console.log("Sending message [" + c + "].");
      s = JSON.stringify(msg, undefined, 2);
    } else {
      s = JSON.stringify(msg);
    }
    this.connection.send(s);
  };

  return Websocket;
});

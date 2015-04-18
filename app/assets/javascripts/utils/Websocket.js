define(["utils/Config"], function (cfg) {
  "use strict";

  function Websocket(url, context) {
    this.url = url;
    this.connected = false;
    this.connect(context);
  }

  Websocket.prototype.connect = function(context) {
    var me = this;
    var ws = new WebSocket(this.url);
    ws.onopen = function() {
      me.connected = true;
      document.getElementById('status-connection').innerText = 'Connected';
      context.onConnect(me.url);
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
        // console.log("Received message [MessageSet] containing [" + messages + "].");
      } else {
        // console.log("Received message [" + json.c + "].");
      }
      context.onMessage(json.c, json.v);
    };
    ws.onclose = function() {
      me.connected = false;
      document.getElementById('status-connection').innerText = 'Disconnected';
      console.info("Websocket connection closed. Attempting to reconnect.");
      me.connect(context);
    };
    ws.onerror = function(err) {
      me.connected = false;
      document.getElementById('status-connection').innerText = 'Connection Error';
      console.error("Received error from websocket connection [" + err + "].");
    };
    this.connection = ws;
  };

  Websocket.prototype.close = function() {
    this.connection.close();
  };

  Websocket.prototype.send = function(c, v) {
    var msg = { "c": c, "v": v };
    var s = null;
    if(cfg.debug) {
      // console.log("Sending message [" + c + "].");
      s = JSON.stringify(msg, undefined, 2);
    } else {
      s = JSON.stringify(msg);
    }
    this.connection.send(s);
  };

  return Websocket;
});

define(["utils/DebugInfo"], function (di) {
  "use strict";

  function GameState(id, game) {
    this.id = id;
    Phaser.State.call(this, game);
  }

  GameState.prototype = Object.create(Phaser.State.prototype);
  GameState.prototype.constructor = GameState;

  GameState.prototype.create = function() {
    // console.log(this.constructor.name + " created.");
  };

  GameState.prototype.update = function() {
    document.getElementById('status-fps').innerText = this.game.time.fps;
  };

  GameState.prototype.onMessage = function(c, v) {
    switch(c) {
      case "MessageSet":
        for(var messageIndex in v.messages) {
          var message = v.messages[messageIndex];
          if(message.c !== undefined) {
            this.onMessage(message.c, message.v);
          } else {
            this.onMessage(message[0].replace("models.", ""), message[1]);
          }
        }
        break;
      case "SendDebugInfo":
        this.game.send("DebugInfo", { data: JSON.stringify(di.getDebugInfo(this.id, this.game)) });
        break;
      default:
        console.warn("Unhandled message [" + c + "]: " + JSON.stringify(v));
    }
  };

  GameState.prototype.resize = function(h, w) {
    // console.log("Resizing [" + this.id + "] to [" + h + ", " + w + "].");
  };

  return GameState;
});

/* global define:false */
define(['utils/Config', 'utils/Websocket', 'game/Game'], function (cfg, Websocket, Game) {
  'use strict';

  function Solitaire() {
    if(cfg.offline) {
      this.game = new Game();
    } else {
      this.game = null;
      this.ws = new Websocket(cfg.wsUrl, this);
    }

    var self = this;
    window.onbeforeunload = function() {
      return self.onBeforeUnload();
    };
    window.onunload = function() {
      return self.onUnload();
    };
  }

  Solitaire.prototype.onBeforeUnload = function() {
    if(this.game !== null) {
      if(this.game.movesMade > 0 && !this.game.complete) {
        return 'You\'re playing a game. Are you sure you\'d like to resign?';
      }
    }
  };

  Solitaire.prototype.onUnload = function() {
    //if(this.game !== null) {
    //  if(this.game.id !== undefined) {
    //    this.game.send('ResignGame', {id: this.game.id});
    //  }
    //}
  };

  Solitaire.prototype.onConnect = function() {
    if(this.game === null) {
      this.game = new Game(this.ws);
    } else {
      this.game.onMessage('Reconnect', {});
    }
  };

  Solitaire.prototype.onMessage = function(c, v) {
    if(this.game === null) {
      console.log('No game available for message [' + c + '].');
    } else {
      this.game.onMessage(c, v);
    }
  };

  return Solitaire;
});

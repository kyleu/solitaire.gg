define(['ui/Modal'], function(Modal) {
  var Help = function(game) {
    this.game = game;
  };

  Help.prototype.toggleRules = function() {
    if(Modal.isVisible()) {
      Modal.hide();
    } else {
      Modal.show("/help/" + this.game.rules);
    }
  };

  Help.prototype.toggleHelp = function() {
    console.log("Help helps! [" + this.game.rules + "]");
  };

  return Help;
});

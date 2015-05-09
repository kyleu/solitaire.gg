define(['ui/Modal'], function(Modal) {
  var Help = function(game) {
    this.game = game;
  };

  Help.prototype.toggleRules = function() {
    if(Modal.isVisible()) {
      Modal.hide();
    } else {
      var height = this.game.height * 0.9;
      var margin = this.game.height * 0.05;
      Modal.show(height, margin, "/help/" + this.game.rules);
    }
  };

  Help.prototype.toggleHelp = function() {
    console.log("Help helps! [" + this.game.rules + "]");
  };

  return Help;
});

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

  Help.prototype.toggleFeedback = function() {
    if(Modal.isVisible()) {
      Modal.hide();
    } else {
      var height = this.game.height * 0.9;
      var margin = this.game.height * 0.05;
      Modal.show(height, margin, "/feedback");
    }
  };

  Help.prototype.initIfNeeded = function() {
    if(this.graphics === undefined) {
      this.graphics = new Phaser.Graphics(this.game, 0, 0);
      this.graphics.visible = false;
      this.graphics.lineWidth = 4;
      this.graphics.lineColor = "#000000";
      this.game.playmat.add(this.graphics);
      for(var psIdx in this.game.pileSets) {
        var ps = this.game.pileSets[psIdx];
        var position = [ps.position[0] * this.game.cardSet.cardWidth, ps.position[1] * this.game.cardSet.cardHeight];
        var size = [(ps.dimensions[0] - 0.2) * this.game.cardSet.cardWidth, (ps.dimensions[1] - 0.2) * this.game.cardSet.cardHeight];

        this.graphics.beginFill(0x000000, 0.25);
        this.graphics.drawRect(position[0], position[1], size[0], size[1]);
      }
    }
  };

  Help.prototype.toggleHelp = function() {
    this.initIfNeeded();
    this.graphics.visible = !this.graphics.visible;
  };

  return Help;
});

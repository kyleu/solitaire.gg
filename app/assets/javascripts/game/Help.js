define(['ui/Modal'], function(Modal) {
  var Help = function(game) {
    this.game = game;
  };

  Help.prototype.toggleRules = function() {
    var postLoad = function() {
      _.each(document.getElementsByClassName("help-link"), function(helpLink) {
        helpLink.onclick = function() {
          Modal.show("GET", helpLink.href + "?inline=true", {}, postLoad);
          return false;
        };
      });
    };
    Modal.show("GET", "/help/" + this.game.rules + "?inline=true", {}, postLoad);
  };

  Help.prototype.toggleFeedback = function() {
    var g = this.game;
    var postLoad = function() {
      var textarea = document.getElementById("feedback-input");
      var button = document.getElementById("feedback-submit");

      textarea.onfocus = function() {
        console.log("off");
        g.keyboard.disable();
      };
      textarea.onblur = function() {
        console.log("on");
        g.keyboard.enable();
      };

      button.onclick = function() {
        var text = textarea.value;
        if(text === "") {
          alert("Please enter some feedback first.");
        } else {
          Modal.show("POST", "/feedback", { "feedback": text });
        }
      };
    };
    Modal.show("GET", "/feedback", {}, postLoad);
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

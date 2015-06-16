define(['ui/Modal'], function (Modal) {
  "use strict";

  function toggleDebug() {
    var debugPanels = document.getElementsByClassName("pdebug");
    if(debugPanels.length == 1) {
      console.log(debugPanels[0].style.display);
      if(debugPanels[0].style.display === "none" || debugPanels[0].style.display === "") {
        debugPanels[0].style.display = "block";
      } else {
        debugPanels[0].style.display = "none";
      }
    }
  }

  var Keyboard = function(game) {
    this.game = game;
  };

  Keyboard.prototype.logGame = function() {
    console.log("Hotkeys:");
    console.log("  L: Show this message and log game to console.");
    console.log("  U: Undo move.");
    console.log("  R: Redo move.");
    console.log("  G: Show game rules.");
    console.log("  H: Dynamic help.");
    console.log("  V: Victory animation.");
    console.log("  C: Force connection close.");
    console.log("  Space: Toggle debug view.");
    console.log("Game [" + this.game.id + "] (" + this.game.rules + "):");
    console.log(this.game);
    console.log("Piles:");
    for(var cardIndex in this.game.piles) {
      console.log(this.game.piles[cardIndex]);
    }
    console.log("Possible Moves:");
    for(var moveIndex in this.game.possibleMoves) {
      console.log(this.game.possibleMoves[moveIndex]);
    }
  };

  Keyboard.prototype.init = function() {
    var g = this.game;

    var rulesKey = g.input.keyboard.addKey(Phaser.Keyboard.G);
    rulesKey.onDown.add(function() {
      g.help.toggleRules();
    });

    var helpKey = g.input.keyboard.addKey(Phaser.Keyboard.H);
    helpKey.onDown.add(function () {
      g.help.toggleHelp();
    });

    var logKey = g.input.keyboard.addKey(Phaser.Keyboard.L);
    logKey.onDown.add(this.logGame);

    var feedbackKey = g.input.keyboard.addKey(Phaser.Keyboard.F);
    feedbackKey.onDown.add(function() {
      g.help.toggleFeedback();
    });

    var quietKey = g.input.keyboard.addKey(Phaser.Keyboard.Q);
    quietKey.onDown.add(function() {
      var gp = document.getElementById("game-container");
      if(gp.style.display === "none") { gp.style.display = "block"; } else { gp.style.display = "none"; }
    });

    var undoKey = g.input.keyboard.addKey(Phaser.Keyboard.Z);
    undoKey.onDown.add(function() {
      if(g.options.undosAvailable > 0) {
        g.send("Undo", {});
      }
    });

    var redoKey = g.input.keyboard.addKey(Phaser.Keyboard.Y);
    redoKey.onDown.add(function() {
      if(g.options.redosAvailable > 0) {
        g.send("Redo", {});
      }
    });

    var victoriousCheatKey = g.input.keyboard.addKey(Phaser.Keyboard.V);
    victoriousCheatKey.onDown.add(function() {
      console.log("Victorious!");
      for(var cardIndex in g.cards) {
        g.cards[cardIndex].animation = {id: "mouse", speed: 200 + Math.floor(Math.random() * 200)};
      }
    });

    var closeConnectionKey = g.input.keyboard.addKey(Phaser.Keyboard.C);
    closeConnectionKey.onDown.add(function() {
      g.ws.close();
    });

    var hideModalKey = g.input.keyboard.addKey(Phaser.Keyboard.ESC);
    hideModalKey.onDown.add(function() {
      Modal.hide();
    });

    var debugKey = g.input.keyboard.addKey(Phaser.Keyboard.SPACEBAR);
    debugKey.onDown.add(toggleDebug);
  };

  Keyboard.prototype.enable = function() {
    this.game.input.keyboard.enabled = true;
  };

  Keyboard.prototype.disable = function() {
    this.game.input.keyboard.enabled = false;
  };

  return Keyboard;
});

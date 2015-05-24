define([], function () {
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

  return {
    "init": function(game) {
      function logGame() {
        console.log("Hotkeys:");
        console.log("  L: Show this message and log game to console.");
        console.log("  U: Undo move.");
        console.log("  R: Redo move.");
        console.log("  G: Show game rules.");
        console.log("  H: Dynamic help.");
        console.log("  V: Victory animation.");
        console.log("  T: Fire trails because why not.");
        console.log("  C: Force connection close.");
        console.log("  Space: Toggle debug view.");
        console.log("Game [" + game.id + "] (" + game.rules + "):");
        console.log(game);
        console.log("Piles:");
        for(var cardIndex in game.piles) {
          console.log(game.piles[cardIndex]);
        }
        console.log("Possible Moves:");
        for(var moveIndex in game.possibleMoves) {
          console.log(game.possibleMoves[moveIndex]);
        }
      }

      var rulesKey = game.input.keyboard.addKey(Phaser.Keyboard.G);
      rulesKey.onDown.add(function() {
        game.help.toggleRules();
      });

      var helpKey = game.input.keyboard.addKey(Phaser.Keyboard.H);
      helpKey.onDown.add(function () {
        game.help.toggleHelp();
      });

      var logKey = game.input.keyboard.addKey(Phaser.Keyboard.L);
      logKey.onDown.add(logGame);

      var feedbackKey = game.input.keyboard.addKey(Phaser.Keyboard.F);
      feedbackKey.onDown.add(function() {
        game.help.toggleFeedback();
      });

      var quietKey = game.input.keyboard.addKey(Phaser.Keyboard.Q);
      quietKey.onDown.add(function() {
        var gp = document.getElementById("game-container");
        if(gp.style.display === "none") { gp.style.display = "block"; } else { gp.style.display = "none"; }
      });

      var undoKey = game.input.keyboard.addKey(Phaser.Keyboard.Z);
      undoKey.onDown.add(function() {
        game.send("Undo", {});
      });

      var redoKey = game.input.keyboard.addKey(Phaser.Keyboard.Y);
      redoKey.onDown.add(function() {
        game.send("Redo", {});
      });

      var victoriousCheatKey = game.input.keyboard.addKey(Phaser.Keyboard.V);
      victoriousCheatKey.onDown.add(function() {
        console.log("Victorious!");
        for(var cardIndex in game.cards) {
          game.cards[cardIndex].animation = {id: "mouse", speed: 200 + Math.floor(Math.random() * 200)};
        }
      });

      var trailsCheatKey = game.input.keyboard.addKey(Phaser.Keyboard.T);
      trailsCheatKey.onDown.add(function() {
        game.playmat.enableTrails();
      });

      var closeConnectionKey = game.input.keyboard.addKey(Phaser.Keyboard.C);
      closeConnectionKey.onDown.add(function() {
        game.ws.close();
      });

      var debugKey = game.input.keyboard.addKey(Phaser.Keyboard.SPACEBAR);
      debugKey.onDown.add(toggleDebug);
    }
  };
});

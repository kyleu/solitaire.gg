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
        console.log("Game [" + game.id + "] (" + game.variant + "):");
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

      function victorious() {
        console.log("Victorious!");
        for(var cardIndex in game.cards) {
          game.cards[cardIndex].animation = {id: "mouse", speed: 200 + Math.floor(Math.random() * 200)};
        }
      }

      function undo() {
        game.ws.send("Undo", {});
      }

      function redo() {
        game.ws.send("Redo", {});
      }

      var undoKey = game.input.keyboard.addKey(Phaser.Keyboard.Z);
      undoKey.onDown.add(undo);

      var redoKey = game.input.keyboard.addKey(Phaser.Keyboard.Y);
      redoKey.onDown.add(redo);

      var victoriousCheatKey = game.input.keyboard.addKey(Phaser.Keyboard.V);
      victoriousCheatKey.onDown.add(victorious);

      var helpKey = game.input.keyboard.addKey(Phaser.Keyboard.H);
      helpKey.onDown.add(logGame);

      var debugKey = game.input.keyboard.addKey(Phaser.Keyboard.SPACEBAR);
      debugKey.onDown.add(toggleDebug);
    }
  };
});

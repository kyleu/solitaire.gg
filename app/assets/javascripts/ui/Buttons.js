define([], function() {
  return function(game, options) {
    var buttons = {
      "menu": document.getElementById("btn-menu"),
      "options": document.getElementById("btn-options"),

      "help": document.getElementById("btn-help"),
      "feedback": document.getElementById("btn-feedback"),

      "undo": document.getElementById("btn-undo"),
      "redo": document.getElementById("btn-redo"),
      "hint": document.getElementById("btn-hint"),

      "redeal": document.getElementById("btn-redeal"),
      "chooseGame": document.getElementById("btn-choose-game"),
      "giveUp": document.getElementById("btn-give-up"),
      "otherStuff": document.getElementById("btn-other-stuff")
    };

    buttons.menu.onclick = function() {
      game.options.toggleMenuPanel();
    };

    buttons.options.onclick = function() {
      game.options.toggleOptionsPanel();
    };

    buttons.help.onclick = function() {
      game.help.toggleRules();
    };

    buttons.feedback.onclick = function() {
      game.help.toggleFeedback();
    };

    buttons.undo.onclick = function() {
      if(options.undosAvailable > 0) {
        game.send("Undo", {});
      }
    };

    buttons.redo.onclick = function() {
      if(options.redosAvailable > 0) {
        game.send("Redo", {});
      }
    };

    buttons.hint.onclick = function() {
      game.autoMove();
    };

    var comingSoon = function() {
      alert("Coming soon!");
    };

    buttons.redeal.onclick = comingSoon;
    buttons.chooseGame.onclick = comingSoon;
    buttons.giveUp.onclick = comingSoon;
    buttons.otherStuff.onclick = comingSoon;

    return buttons;
  };
});

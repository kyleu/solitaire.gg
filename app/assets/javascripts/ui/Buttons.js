define([], function() {
  return function(game, options) {
    var buttons = {
      "menu": document.getElementById("btn-menu"),
      "options": document.getElementById("btn-options"),
      "help": document.getElementById("btn-help"),
      "feedback": document.getElementById("btn-feedback"),
      "undo": document.getElementById("btn-undo"),
      "redo": document.getElementById("btn-redo"),
      "hint": document.getElementById("btn-hint")
    };

    buttons.menu.onclick = function() {
      if(game.options.toggleMenuPanel()) {
        buttons.menu.className += " disabled";
      } else {
        buttons.menu.className = buttons.menu.className.replace(" disabled", "");
      }
      return false;
    };

    buttons.options.onclick = function() {
      if(game.options.toggleOptionsPanel()) {
        buttons.options.className += " disabled";
      } else {
        buttons.options.className = buttons.options.className.replace(" disabled", "");
      }
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

    return buttons;
  };
});

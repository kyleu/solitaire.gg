define(['ui/Modal'], function(Modal) {
  return function(game, options) {
    var buttons = {
      "options": document.getElementById("btn-options"),
      "help": document.getElementById("btn-help"),
      "feedback": document.getElementById("btn-feedback"),
      "undo": document.getElementById("btn-undo"),
      "redo": document.getElementById("btn-redo"),
      "hint": document.getElementById("btn-hint")
    };

    buttons.options.onclick = function() {
      Modal.showOptions();
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

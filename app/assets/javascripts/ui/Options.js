define([], function() {
  var Options = function(game) {
    var self = this;

    this.gameName = document.getElementById("link-game");

    this.buttons = {
      "menu": document.getElementById("btn-menu"),
      "options": document.getElementById("btn-options"),
      "help": document.getElementById("btn-help"),
      "feedback": document.getElementById("btn-feedback"),
      "undo": document.getElementById("btn-undo"),
      "redo": document.getElementById("btn-redo"),
      "hint": document.getElementById("btn-hint")
    };

    this.buttons.menu.onclick = function() {
      alert("The menu's not quite ready yet.");
    };

    this.buttons.options.onclick = function() {
      alert("Options aren't quite ready yet.");
    };

    this.buttons.help.onclick = function() {
      game.help.toggleRules();
    };

    this.buttons.feedback.onclick = function() {
      game.help.toggleFeedback();
    };

    this.buttons.undo.onclick = function() {
      if(self.undosAvailable > 0) {
        game.send("Undo", {});
      }
    };

    this.buttons.redo.onclick = function() {
      if(self.redosAvailable > 0) {
        game.send("Redo", {});
      }
    };

    this.buttons.hint.onclick = function() {
      alert("Hints aren't quite ready yet.");
    };
  };

  Options.prototype.setGame = function(state) {
    this.gameName.innerText = state.rulesTitle;
  };

  Options.prototype.setUndosAvalable = function(num) {
    this.undosAvailable = num;
    if(num === 0) {
      this.buttons.undo.className = "btn undo disabled";
      this.buttons.undo.title = "No undos available.";
    } else {
      this.buttons.undo.className = "btn undo";
      if(num === 1) {
        this.buttons.undo.title = "One undo available.";
      } else {
        this.buttons.undo.title = num + " undos available.";
      }
    }
  };

  Options.prototype.setRedosAvalable = function(num) {
    this.redosAvailable = num;
    if(num === 0) {
      this.buttons.redo.className = "btn redo disabled";
      this.buttons.redo.title = "No redos available.";
    } else {
      this.buttons.redo.className = "btn redo";
      if(num === 1) {
        this.buttons.redo.title = "One redo available.";
      } else {
        this.buttons.redo.title = num + " redos available.";
      }
    }
  };

  return Options;
});

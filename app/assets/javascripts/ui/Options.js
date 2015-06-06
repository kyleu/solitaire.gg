define(['ui/Buttons'], function(Buttons) {
  var Options = function(game) {
    var self = this;

    this.gameName = document.getElementById("link-game");

    this.buttons = Buttons(game, this);
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

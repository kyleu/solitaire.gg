define(['ui/Buttons'], function(Buttons) {
  var Options = function(game) {
    this.gameName = document.getElementById("link-game");
    this.buttons = new Buttons(game, this);
    this.timerDisplay = document.getElementById("timer-display");
  };

  Options.prototype.setGame = function(state) {
    this.gameName.innerText = state.rulesTitle;
  };

  Options.prototype.setTimerValue = function(time) {
    this.timerDisplay.innerText = time;
  };

  Options.prototype.setUndosAvalable = function(num) {
    this.undosAvailable = num;
    if(num === 0) {
      if(this.buttons.undo.className.indexOf(" disabled" === -1)) {
        this.buttons.undo.className += " disabled";
      }
      this.buttons.undo.title = "No undos available.";
    } else {
      if(this.buttons.undo.className.indexOf(" disabled" > -1)) {
        this.buttons.undo.className = this.buttons.undo.className.replace(" disabled", "");
      }
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
      if(this.buttons.redo.className.indexOf(" disabled" === -1)) {
        this.buttons.redo.className += " disabled";
      }
      this.buttons.redo.title = "No redos available.";
    } else {
      if(this.buttons.redo.className.indexOf(" disabled" > -1)) {
        this.buttons.redo.className = this.buttons.redo.className.replace(" disabled", "");
      }
      if(num === 1) {
        this.buttons.redo.title = "One redo available.";
      } else {
        this.buttons.redo.title = num + " redos available.";
      }
    }
  };

  return Options;
});

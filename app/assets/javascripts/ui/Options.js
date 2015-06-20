define(['ui/Buttons', 'ui/Colors'], function(Buttons, Colors) {
  var Options = function(game) {
    this.game = game;
    this.elements = {
      "gameName": document.getElementById("link-game"),
      "timerDisplay": document.getElementById("timer-display"),
      "topBar": document.getElementById("menu-container"),
      "bottomBar": document.getElementById("status-container"),
      "optionsPanel": document.getElementById("gameplay-options"),
      "menuPanel": document.getElementById("gameplay-menu")
    };
    game.menusVisible = this.elements.topBar.style.display !== 'none';
    this.buttons = new Buttons(game, this);
    Colors.init(function(color) {
      game.send("SetPreference", { "name": "color", "value": color });
    });
  };

  Options.prototype.setGame = function(state) {
    this.elements.gameName.innerText = state.rulesTitle;
  };

  Options.prototype.setTimerValue = function(time) {
    this.elements.timerDisplay.innerText = time;
  };

  Options.prototype.setUndosAvailable = function(num) {
    this.undosAvailable = num;
    if(num === 0) {
      if(this.buttons.undo.className.indexOf(" disabled") === -1) {
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

  Options.prototype.setRedosAvailable = function(num) {
    this.redosAvailable = num;
    if(num === 0) {
      if(this.buttons.redo.className.indexOf(" disabled") === -1) {
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

  Options.prototype.toggleMenus = function() {
    if(this.game.menusVisible) {
      this.elements.topBar.className = this.elements.topBar.className.replace(" fade-in", "") + " fade-out";
      this.elements.bottomBar.className = this.elements.bottomBar.className.replace(" fade-in", "") + " fade-out";
      this.game.menusVisible = false;
    } else {
      this.elements.topBar.className = this.elements.topBar.className.replace(" fade-out", "") + " fade-in";
      this.elements.bottomBar.className = this.elements.bottomBar.className.replace(" fade-out", "") + " fade-in";
      this.game.menusVisible = true;
    }
  };

  Options.prototype.toggleOptionsPanel = function() {
    var e = this.elements.optionsPanel;
    if(e.style.display === "block") {
      e.style.display = "none";
      return false;
    } else {
      e.style.display = "block";
      return true;
    }
  };

  Options.prototype.toggleMenuPanel = function() {
    var e = this.elements.menuPanel;
    if(e.style.display === "block") {
      e.style.display = "none";
      return false;
    } else {
      e.style.display = "block";
      return true;
    }
  };

  return Options;
});

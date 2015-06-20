define(['ui/Buttons', 'ui/Colors'], function(Buttons, Colors) {
  var Options = function(game) {
    this.game = game;
    this.elements = {
      "gameName": document.getElementById("game-name"),
      "timerDisplay": document.getElementById("timer-display"),
      "topBar": document.getElementById("menu-container"),
      "bottomBar": document.getElementById("status-container"),
      "optionsButton": document.getElementById("btn-options"),
      "optionsPanel": document.getElementById("gameplay-options"),
      "menuButton": document.getElementById("btn-menu"),
      "menuPanel": document.getElementById("gameplay-menu")
    };
    game.menusVisible = this.elements.topBar.style.display !== 'none';
    this.buttons = new Buttons(game, this);
    Colors.init(function(color) {
      game.send("SetPreference", { "name": "color", "value": color });
    });
  };

  Options.prototype.setGame = function(state) {
    this.elements.gameName.textContent = state.rulesTitle;
  };

  Options.prototype.setTimerValue = function(time) {
    this.elements.timerDisplay.textContent = time;
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

  Options.prototype.hidePanels = function() {
    if(this.elements.optionsPanel.style.visibility === "inherit") {
      this.hideOptionsPanel();
    }
    if(this.elements.menuPanel.style.visibility === "inherit") {
      this.hideMenuPanel();
    }
  };

  Options.prototype.showOptionsPanel = function() {
    this.elements.optionsButton.className += " disabled";
    this.elements.optionsPanel.style.visibility = "inherit";
  };

  Options.prototype.hideOptionsPanel = function() {
    this.elements.optionsButton.className = this.elements.optionsButton.className.replace(" disabled", "");
    this.elements.optionsPanel.style.visibility = "hidden";
  };

  Options.prototype.toggleOptionsPanel = function() {
    if(this.elements.optionsPanel.style.visibility === "inherit") {
      this.hideOptionsPanel();
    } else {
      if(this.elements.menuPanel.style.visibility === "inherit") {
        this.hideMenuPanel();
      }
      this.showOptionsPanel();
    }
  };

  Options.prototype.showMenuPanel = function() {
    this.elements.menuButton.className += " disabled";
    this.elements.menuPanel.style.visibility = "inherit";
  };

  Options.prototype.hideMenuPanel = function() {
    this.elements.menuButton.className = this.elements.menuButton.className.replace(" disabled", "");
    this.elements.menuPanel.style.visibility = "hidden";
  };

  Options.prototype.toggleMenuPanel = function() {
    if(this.elements.menuPanel.style.visibility === "inherit") {
      this.hideMenuPanel();
    } else {
      if(this.elements.optionsPanel.style.visibility === "inherit") {
        this.hideOptionsPanel();
      }
      this.showMenuPanel();
    }
  };

  return Options;
});

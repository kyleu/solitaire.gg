/* global define:false */
define(['ui/Buttons', 'ui/Theme', 'ui/Panels'], function(Buttons, Theme, Panels) {
  var Options = function(game) {
    this.game = game;
    this.elements = {
      'gameName': document.getElementById('game-name'),
      'timerDisplay': document.getElementById('timer-display'),
      'topBar': document.getElementById('menu-container'),
      'bottomBar': document.getElementById('status-container'),
      'optionsButton': document.getElementById('btn-options'),
      'optionsPanel': document.getElementById('gameplay-options'),
      'menuButton': document.getElementById('btn-menu'),
      'menuPanel': document.getElementById('gameplay-menu')
    };
    game.menusVisible = this.elements.topBar.style.display !== 'none';
    this.buttons = new Buttons(game, this);
    Theme.setGame(game);
  };

  Options.prototype.setGame = function(state) {
    this.elements.gameName.textContent = state.rulesTitle;
    this.elements.bottomBar.className += ' fade-in';
    this.elements.optionsButton.className += ' fade-in';
    this.elements.menuButton.className += ' fade-in';
  };

  Options.prototype.setTimerValue = function(time) {
    this.elements.timerDisplay.textContent = time;
  };

  Options.prototype.setUndosAvailable = function(num) {
    this.undosAvailable = num;
    if(num === 0) {
      if(this.buttons.undo.className.indexOf(' disabled') === -1) {
        this.buttons.undo.className += ' disabled';
      }
      this.buttons.undo.title = 'No undos available.';
    } else {
      if(this.buttons.undo.className.indexOf(' disabled' > -1)) {
        this.buttons.undo.className = this.buttons.undo.className.replace(' disabled', '');
      }
      if(num === 1) {
        this.buttons.undo.title = 'One undo available.';
      } else {
        this.buttons.undo.title = num + ' undos available.';
      }
    }
  };

  Options.prototype.setRedosAvailable = function(num) {
    this.redosAvailable = num;
    if(num === 0) {
      if(this.buttons.redo.className.indexOf(' disabled') === -1) {
        this.buttons.redo.className += ' disabled';
      }
      this.buttons.redo.title = 'No redos available.';
    } else {
      if(this.buttons.redo.className.indexOf(' disabled' > -1)) {
        this.buttons.redo.className = this.buttons.redo.className.replace(' disabled', '');
      }
      if(num === 1) {
        this.buttons.redo.title = 'One redo available.';
      } else {
        this.buttons.redo.title = num + ' redos available.';
      }
    }
  };

  Options.prototype.toggleMenus = Panels.toggleMenus;
  Options.prototype.hidePanels = Panels.hidePanels;
  Options.prototype.showOptionsPanel = Panels.showOptionsPanel;
  Options.prototype.hideOptionsPanel = Panels.hideOptionsPanel;
  Options.prototype.toggleOptionsPanel = Panels.toggleOptionsPanel;
  Options.prototype.showMenuPanel = Panels.showMenuPanel;
  Options.prototype.hideMenuPanel = Panels.hideMenuPanel;
  Options.prototype.toggleMenuPanel = Panels.toggleMenuPanel;

  return Options;
});

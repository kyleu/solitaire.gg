/* global define:false */
define(['ui/Buttons', 'ui/Theme', 'ui/Panels'], function(Buttons, Theme, Panels) {
  var Options = function(game) {
    var els = {
      gameNameContainer: document.getElementById('game-name-container'),
      gameName: document.getElementById('game-name'),
      timerContainer: document.getElementById('timer-container'),
      timerDisplay: document.getElementById('timer-display'),
      topBar: document.getElementById('menu-container'),
      menuButton: document.getElementById('btn-menu'),
      menuPanel: document.getElementById('gameplay-menu'),
      gameResultsPanel: document.getElementById('game-results-panel'),
      gameOptionsButton: document.getElementById('btn-game-options'),
      gameOptionsPanel: document.getElementById('gameplay-options'),
      bottomBar: document.getElementById('status-container'),
      bottomBarLeft: document.getElementById('status-container-left'),
      bottomBarRight: document.getElementById('status-container-right')
    };

    els.gameName.onclick = function() {
      els.gameNameContainer.style.display = 'none';
      els.timerContainer.style.display = 'block';
    };
    els.timerDisplay.onclick = function() {
      els.timerContainer.style.display = 'none';
      els.gameNameContainer.style.display = 'block';
    };

    game.menusVisible = els.topBar.style.display !== 'none';

    this.game = game;
    this.elements = els;
    this.buttons = new Buttons(game, this);
    Theme.setGame(game);
  };

  Options.prototype.setGame = function(state) {
    var els = this.elements;
    els.gameName.textContent = state.rulesTitle;
    els.gameOptionsButton.className = els.gameOptionsButton.className.replace('invisible', 'fade-in');
    els.menuButton.className = els.menuButton.className.replace('invisible', 'fade-in');
    els.bottomBarLeft.className = els.bottomBarLeft.className.replace('invisible', 'fade-in');
    els.bottomBarRight.className = els.bottomBarRight.className.replace('invisible', 'fade-in');
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
  Options.prototype.showMenuPanel = Panels.showMenuPanel;
  Options.prototype.hideMenuPanel = Panels.hideMenuPanel;
  Options.prototype.toggleMenuPanel = Panels.toggleMenuPanel;
  Options.prototype.showGameOptionsPanel = Panels.showGameOptionsPanel;
  Options.prototype.hideGameOptionsPanel = Panels.hideGameOptionsPanel;
  Options.prototype.toggleGameOptionsPanel = Panels.toggleGameOptionsPanel;

  return Options;
});

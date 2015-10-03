/* global define:false */
define([], function() {
  return {
    toggleMenus: function() {
      var els = this.elements;
      if(this.game.menusVisible) {
        els.topBar.className = els.topBar.className.replace(' fade-in', '') + ' fade-out';
        els.bottomBar.className = els.bottomBar.className.replace(' fade-in', '') + ' fade-out';
        this.game.menusVisible = false;
        setTimeout(function() {
          if(els.topBar.className.indexOf('fade-out') > -1) {
            els.topBar.style.height = '0';
            els.bottomBar.style.height = '0';
          }
        }, 1000);
      } else {
        els.topBar.style.height = '40px';
        els.bottomBar.style.height = '40px';
        els.topBar.className = els.topBar.className.replace(' fade-out', '') + ' fade-in';
        els.bottomBar.className = els.bottomBar.className.replace(' fade-out', '') + ' fade-in';
        this.game.menusVisible = true;
      }
    },

    hidePanels: function() {
      if(this.elements.gameResultsPanel.style.display === 'block') {
        this.elements.gameResultsPanel.style.display = 'none';
      }
      if(this.elements.menuPanel.style.display === 'block') {
        this.hideMenuPanel();
      }
      if(this.elements.gameOptionsPanel.style.display === 'block') {
        this.hideGameOptionsPanel();
      }
    },

    showMenuPanel: function() {
      var els = this.elements;
      els.menuButton.className += ' disabled';
      els.menuPanel.style.display = 'block';
    },

    hideMenuPanel: function() {
      var els = this.elements;
      els.menuButton.className = this.elements.menuButton.className.replace(' disabled', '');
      els.menuPanel.style.display = 'none';
    },

    toggleMenuPanel: function() {
      if(this.elements.menuPanel.style.display === 'block') {
        this.hideMenuPanel();
      } else {
        if(this.elements.gameOptionsPanel.style.display === 'block') {
          this.hideGameOptionsPanel();
        }
        this.showMenuPanel();
      }
    },

    showGameOptionsPanel: function() {
      var els = this.elements;
      els.gameOptionsButton.className += ' disabled';
      els.gameOptionsPanel.style.display = 'block';
    },

    hideGameOptionsPanel: function() {
      var els = this.elements;
      els.gameOptionsButton.className = this.elements.gameOptionsButton.className.replace(' disabled', '');
      els.gameOptionsPanel.style.display = 'none';
    },

    toggleGameOptionsPanel: function() {
      if(this.elements.gameOptionsPanel.style.display === 'block') {
        this.hideGameOptionsPanel();
      } else {
        if(this.elements.menuPanel.style.display === 'block') {
          this.hideMenuPanel();
        }
        this.showGameOptionsPanel();
      }
    }
  };
});

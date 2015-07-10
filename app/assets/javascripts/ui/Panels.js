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
          els.topBar.style.height = '0';
          els.bottomBar.style.height = '0';
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
      if(this.elements.optionsPanel.className === 'fade-in') {
        this.hideOptionsPanel();
      }
      if(this.elements.menuPanel.className === 'fade-in') {
        this.hideMenuPanel();
      }
    },

    showOptionsPanel: function() {
      var els = this.elements;
      els.optionsButton.className += ' disabled';
      els.optionsPanel.style.display = 'block';
    },

    hideOptionsPanel: function() {
      var els = this.elements;
      els.optionsButton.className = this.elements.optionsButton.className.replace(' disabled', '');
      els.optionsPanel.style.display = 'none';
    },

    toggleOptionsPanel: function() {
      if(this.elements.optionsPanel.style.display === 'block') {
        this.hideOptionsPanel();
      } else {
        if(this.elements.menuPanel.style.display === 'block') {
          this.hideMenuPanel();
        }
        this.showOptionsPanel();
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
        if(this.elements.optionsPanel.style.display === 'block') {
          this.hideOptionsPanel();
        }
        this.showMenuPanel();
      }
    }
  };
});

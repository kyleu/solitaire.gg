define([], function() {
  return {
    toggleMenus: function() {
      if(this.game.menusVisible) {
        this.elements.topBar.className = this.elements.topBar.className.replace(" fade-in", "") + " fade-out";
        this.elements.bottomBar.className = this.elements.bottomBar.className.replace(" fade-in", "") + " fade-out";
        this.game.menusVisible = false;
      } else {
        this.elements.topBar.className = this.elements.topBar.className.replace(" fade-out", "") + " fade-in";
        this.elements.bottomBar.className = this.elements.bottomBar.className.replace(" fade-out", "") + " fade-in";
        this.game.menusVisible = true;
      }
    },

    hidePanels: function() {
      if(this.elements.optionsPanel.className === "fade-in") {
        this.hideOptionsPanel();
      }
      if(this.elements.menuPanel.className === "fade-in") {
        this.hideMenuPanel();
      }
    },

    showOptionsPanel: function() {
      this.elements.optionsButton.className += " disabled";
      this.elements.optionsPanel.className = "fade-in";
    },

    hideOptionsPanel: function() {
      this.elements.optionsButton.className = this.elements.optionsButton.className.replace(" disabled", "");
      this.elements.optionsPanel.className = "fade-out";
    },

    toggleOptionsPanel: function() {
      if(this.elements.optionsPanel.className === "fade-in") {
        this.hideOptionsPanel();
      } else {
        if(this.elements.menuPanel.className === "fade-in") {
          this.hideMenuPanel();
        }
        this.showOptionsPanel();
      }
    },

    showMenuPanel: function() {
      this.elements.menuButton.className += " disabled";
      this.elements.menuPanel.className = "fade-in";
    },

    hideMenuPanel: function() {
      this.elements.menuButton.className = this.elements.menuButton.className.replace(" disabled", "");
      this.elements.menuPanel.className = "fade-out";
    },

    toggleMenuPanel: function() {
      if(this.elements.menuPanel.className === "fade-in") {
        this.hideMenuPanel();
      } else {
        if(this.elements.optionsPanel.className === "fade-in") {
          this.hideOptionsPanel();
        }
        this.showMenuPanel();
      }
    }
  };
});

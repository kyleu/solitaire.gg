/* global define:false */
define([], function() {
  return function(game, options) {
    var buttons = {
      menu: document.getElementById('btn-menu'),
      gameOptions: document.getElementById('btn-game-options'),
      cardOptions: document.getElementById('btn-card-options'),

      help: document.getElementById('btn-help'),
      feedback: document.getElementById('btn-feedback'),

      undo: document.getElementById('btn-undo'),
      redo: document.getElementById('btn-redo'),
      hint: document.getElementById('btn-hint'),

      redeal: document.getElementById('btn-redeal'),
      chooseGame: document.getElementById('btn-choose-game'),
      giveUp: document.getElementById('btn-give-up'),
      specificSeed: document.getElementById('btn-specific-seed'),
      winnableGame: document.getElementById('btn-winnable-game')
    };

    buttons.menu.onclick = function() {
      game.options.toggleMenuPanel();
    };

    buttons.gameOptions.onclick = function() {
      game.options.toggleGameOptionsPanel();
    };

    buttons.cardOptions.onclick = function() {
      game.options.toggleCardOptionsPanel();
    };

    buttons.help.onclick = function() {
      game.help.toggleRules();
    };

    buttons.feedback.onclick = function() {
      game.help.toggleFeedback();
    };

    buttons.undo.onclick = function() {
      if(options.undosAvailable > 0) {
        game.send('Undo', {});
      }
    };

    buttons.redo.onclick = function() {
      if(options.redosAvailable > 0) {
        game.send('Redo', {});
      }
    };

    buttons.hint.onclick = function() {
      game.autoMove();
    };

    var comingSoon = function() {
      game.options.hidePanels();
      alert('Coming soon!');
    };

    buttons.redeal.onclick = function() {
      game.options.hidePanels();
      game.redeal();
    };
    buttons.chooseGame.onclick = comingSoon;
    buttons.giveUp.onclick = comingSoon;
    buttons.specificSeed.onclick = comingSoon;
    buttons.winnableGame.onclick = comingSoon;

    return buttons;
  };
});

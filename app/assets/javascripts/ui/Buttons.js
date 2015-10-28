/* global define:false */
define([], function() {
  return function(game, options) {
    var buttons = {
      menu: document.getElementById('btn-menu'),
      gameOptions: document.getElementById('btn-game-options'),

      help: document.getElementById('btn-help'),
      feedback: document.getElementById('btn-feedback'),
      resultsFeedback: document.getElementById('results-feedback'),

      undo: document.getElementById('btn-undo'),
      redo: document.getElementById('btn-redo'),
      hint: document.getElementById('btn-hint'),

      redeal: document.getElementById('btn-redeal'),
      specificSeed: document.getElementById('btn-specific-seed'),
      winnableGame: document.getElementById('btn-winnable-game'),
      chooseGame: document.getElementById('btn-choose-game'),
      giveUp: document.getElementById('btn-give-up'),

      redealResults: document.getElementById('btn-results-redeal'),
      specificSeedResults: document.getElementById('btn-results-specific-seed'),
      winnableGameResults: document.getElementById('btn-results-winnable-game'),
      chooseGameResults: document.getElementById('btn-results-choose-game')
    };

    buttons.menu.onclick = function() {
      game.options.toggleMenuPanel();
    };

    buttons.gameOptions.onclick = function() {
      game.options.toggleGameOptionsPanel();
    };

    buttons.help.onclick = function() {
      game.help.toggleRules();
    };

    buttons.feedback.onclick = function() {
      game.help.toggleFeedback();
    };

    buttons.resultsFeedback.onclick = function() {
      game.help.toggleFeedback();
      return false;
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

    var hintLastClicked;
    buttons.hint.onclick = function() {
      var now = new Date().getMilliseconds();
      if(hintLastClicked === undefined || (now - hintLastClicked) > 5000) {
        game.autoMove();
        buttons.hint.addClass('disabled');
        hintLastClicked = now;
        setTimeout(function() {
          buttons.hint.removeClass('disabled');
        }, 5000);
      } else {
        console.log('Too soon for a new hint.'); // TODO alert?
      }
    };

    function redeal() {
      game.options.hidePanels();
      game.redeal();
    }
    buttons.redeal.onclick = redeal;
    buttons.redealResults.onclick = redeal;

    function specificSeed() {
      var seed = prompt('What game number would you like to play?', '1');
      game.options.hidePanels();
      game.redeal(seed);
    }
    buttons.specificSeed.onclick = specificSeed;
    buttons.specificSeedResults.onclick = specificSeed;

    function winnableGame() {
      game.options.hidePanels();
      game.redeal(-1);
    }
    buttons.winnableGame.onclick = winnableGame;
    buttons.winnableGameResults.onclick = winnableGame;

    function chooseGame() {
      game.options.hidePanels();
      var destination = '/';
      if(window.location.href.indexOf('file://') > -1) {
        destination = 'index.html';
      }
      window.location.href = destination;
    }
    buttons.chooseGame.onclick = chooseGame;
    buttons.chooseGameResults.onclick = chooseGame;

    buttons.giveUp.onclick = function() {
      game.options.hidePanels();
      game.resign();
    };

    return buttons;
  };
});

/* global define:false */
/* global _:false */
define(['card/CardImages', 'ui/ThemeStartup'], function(CardImages, ThemeStartup) {
  var assetRoot;
  var game;
  var preferences;
  var elements = {
    backs: [],
    layouts: [],
    suits: [],
    ranks: [],
    faceCards: [],
    autoFlips: [],
    audio: [],
    gamepad: [],
    colors: []
  };

  function selectColor(color) {
    var bgIndex = document.body.className.indexOf('background-');
    document.body.className = document.body.className.substring(0, bgIndex) + ' background-' + color;
    document.getElementById('favicon').href = '/assets/images/ui/favicon/favicon-' + color + '.png';

    var buttons = document.getElementsByClassName('btn');
    _.each(buttons, function(btn) {
      var cn = btn.className;
      var colorIndexStart = cn.indexOf('btn-');
      var colorIndexEnd = cn.indexOf(' ', colorIndexStart);
      if(colorIndexEnd === -1) {
        colorIndexEnd = cn.length;
      }
      btn.className = cn.substr(0, colorIndexStart) + 'btn-' + color + cn.substr(colorIndexEnd);
    });
  }

  function reloadTexture(loader) {
    function fileComplete() {
      CardImages.rerender(game, preferences);
      game.refreshTextures();
    }
    loader.onFileComplete.addOnce(fileComplete);
    loader.start();
  }

  function optionClick(evt) {
    var optionClass;
    var optionValue;
    if(evt.target.getAttribute('data-option-class') !== null) {
      optionClass = evt.target.getAttribute('data-option-class');
      optionValue = evt.target.getAttribute('data-option-value');
    } else {
      optionClass = evt.target.parentElement.getAttribute('data-option-class');
      optionValue = evt.target.parentElement.getAttribute('data-option-value');
    }

    var selectedElements;
    switch(optionClass) {
      case 'card-back':
        selectedElements = elements.backs;
        break;
      case 'card-layout':
        selectedElements = elements.layouts;
        break;
      case 'card-suit':
        selectedElements = elements.suits;
        break;
      case 'card-rank':
        selectedElements = elements.ranks;
        break;
      case 'card-face':
        selectedElements = elements.faceCards;
        break;
      case 'auto-flip':
        selectedElements = elements.autoFlips;
        break;
      case 'audio':
        selectedElements = elements.audio;
        break;
      case 'gamepad':
        selectedElements = elements.gamepad;
        break;
      case 'background-color':
        selectedElements = elements.colors;
        break;
      default:
        throw new Error(optionClass);
    }

    if(preferences[optionClass] !== optionValue) {
      setOption(optionClass, optionValue, selectedElements);
    }
  }

  function setOption(optionClass, optionValue, selectedElements) {
    preferences[optionClass] = optionValue;
    game.send('SetPreference', {'name': optionClass, 'value': optionValue});

    switch(optionClass) {
      case 'background-color':
        selectColor(optionValue);
        break;
      case 'audio':
        break;
      case 'gamepad':
        break;
      case 'auto-flip':
        break;
      case 'card-layout':
        CardImages.rerender(game, preferences);
        game.refreshTextures();
        break;
      case 'card-back':
        reloadTexture(game.load.image('card-back', assetRoot + 'images/cards/back-' + optionValue + '.png'));
        break;
      case 'card-face':
        reloadTexture(game.load.spritesheet('card-faces', assetRoot + 'images/cards/face-cards-' + optionValue + '.png', 200, 300));
        break;
      case 'card-suit':
        reloadTexture(game.load.spritesheet('card-suits', assetRoot + 'images/cards/suits-' + optionValue + '.png', 200, 200));
        break;
      case 'card-rank':
        reloadTexture(game.load.spritesheet('card-ranks', assetRoot + 'images/cards/ranks-' + optionValue + '.png', 200, 200));
        break;
      default:
        throw new Error(optionClass);
    }

    _.each(selectedElements, function(el) {
      if(el.getAttribute('data-option-value') === optionValue) {
        el.className = 'game-option active';
      } else {
        el.className = 'game-option';
      }
    });
  }

  return {
    init: function() {
      preferences = ThemeStartup.init(optionClick, elements);
      return preferences;
    },
    getPreferences: function() {
      return preferences;
    },
    setAssetRoot: function(ar) {
      assetRoot = ar;
    },
    setGame: function(g) {
      game = g;
    }
  };
});

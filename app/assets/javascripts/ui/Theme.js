/* global define:false */
/* global _:false */
define(['card/CardImages', 'ui/ThemeStartup'], function(CardImages, ThemeStartup) {
  var game;
  var preferences;
  var elements = {
    backs: [],
    layouts: [],
    suits: [],
    ranks: [],
    faceCards: [],
    autoFlips: [],
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
      case 'background-color':
        selectedElements = elements.colors;
        break;
      default:
        throw optionClass;
    }

    if(preferences[optionClass] !== optionValue) {
      switch(optionClass) {
        case 'background-color':
          selectColor(optionValue);
          break;
        case 'auto-flip':
          break;
        default:
          CardImages.rerender(preferences);
          game.refreshTextures();
          break;
      }
      preferences[optionClass] = optionValue;
      game.send('SetPreference', {'name': optionClass, 'value': optionValue});

      _.each(selectedElements, function(el) {
        if(el.getAttribute('data-option-value') === optionValue) {
          el.className = 'game-option active';
        } else {
          el.className = 'game-option';
        }
      });
    }
  }

  return {
    init: function() {
      preferences = ThemeStartup.init(optionClick, elements);
      return preferences;
    },
    getPreferences: function() {
      return preferences;
    },
    setGame: function(g) {
      game = g;
    }
  };
});

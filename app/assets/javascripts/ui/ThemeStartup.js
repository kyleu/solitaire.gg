/* global define:false */
/* global _:false */
define([], function() {
  'use strict';
  return {
    init: function(optionClick, elements) {
      var preferences = {};
      var layoutOptions = document.getElementsByClassName('game-option');
      _.each(layoutOptions, function(layoutOption) {
        var optionClass = layoutOption.getAttribute('data-option-class');
        switch(optionClass) {
          case 'card-back':
            elements.backs.push(layoutOption);
            break;
          case 'card-layout':
            elements.layouts.push(layoutOption);
            break;
          case 'card-suit':
            elements.suits.push(layoutOption);
            break;
          case 'card-rank':
            elements.ranks.push(layoutOption);
            break;
          case 'card-face':
            elements.faceCards.push(layoutOption);
            break;
          case 'auto-flip':
            elements.autoFlips.push(layoutOption);
            break;
          case 'audio':
            elements.audio.push(layoutOption);
            break;
          case 'gamepad':
            elements.gamepad.push(layoutOption);
            break;
          case 'background-color':
            elements.colors.push(layoutOption);
            break;
          default:
            throw optionClass;
        }

        if(layoutOption.className.indexOf('active') > -1) {
          preferences[optionClass] = layoutOption.getAttribute('data-option-value');
        }
        console.log('Reading');

        layoutOption.onclick = optionClick;
      });

      return preferences;
    }
  };
});

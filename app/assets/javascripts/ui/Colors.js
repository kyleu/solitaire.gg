/* global define:false */
/* global _:false */
define([], function() {
  'use strict';

  var elements = {};
  var activeColor = '';
  var selectCallback = function() {};

  function selectColor(color) {
    _.each(elements.colors, function(el) {
      if(el.id === 'color-' + color) {
        el.className = 'game-option background-color active';
      } else {
        el.className = 'game-option background-color';
      }
    });
    var bgIndex = document.body.className.indexOf('background-');
    document.body.className = document.body.className.substring(0, bgIndex) + ' background-' + color;
    document.getElementById('favicon').href = '/assets/images/ui/favicon/favicon-' + color + '.png';

    if(activeColor !== color) {
      selectCallback(color);
      activeColor = color;
    }
  }

  function init(callback) {
    if(callback !== undefined) {
      selectCallback = callback;
    }
    function onClick(evt) {
      var color = evt.target.id.substr(6);
      if(color === '') {
        color = evt.target.parentElement.id.substr(6);
      }
      selectColor(color);
    }

    elements.colors = [];
    _.each(document.getElementsByClassName('background-color'), function(el) {
      el.onclick = onClick;
      elements.colors.push(el);
    });
  }

  return {
    init: init
  };
});

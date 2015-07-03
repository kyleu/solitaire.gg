(function() {
  'use strict';
  var elements = {};
  var activeColor = '';

  function selectColor(color) {
    for(var elIndex in elements.colors) {
      if(elements.colors.hasOwnProperty(elIndex)) {
        var el = elements.colors[elIndex];
        if(el.id === 'color-' + color) {
          el.className = 'game-option background-color active';
        } else {
          el.className = 'game-option background-color';
        }
      }
    }
    var bgIndex = document.body.className.indexOf('background-');
    document.body.className = document.body.className.substring(0, bgIndex) + ' background-' + color;
    document.getElementById('favicon').href = '/assets/images/ui/favicon/favicon-' + color + '.png';

    var buttons = document.getElementsByClassName('btn');
    for(var btnIndex = 0; btnIndex < buttons.length; btnIndex++) {
      var btn = buttons.item(btnIndex);
      var cn = btn.className;
      var colorIndexStart = cn.indexOf('btn-');
      var colorIndexEnd = cn.indexOf(' ', colorIndexStart);
      if(colorIndexEnd === -1) {
        colorIndexEnd = cn.length;
      }
      btn.className = cn.substr(0, colorIndexStart) + 'btn-' + color + cn.substr(colorIndexEnd);
    }

    activeColor = color;
  }

  function setColor(color) {
    var url = '/options/set/color/' + color;
    var request = new XMLHttpRequest();
    request.open('GET', url, true);
    request.send();
  }

  function init() {
    elements.optionsButton = document.getElementById('btn-options');
    elements.optionsPanel = document.getElementById('static-options-panel');
    elements.confirmButton = document.getElementById('btn-confirm');

    elements.optionsButton.onclick = function() {
      if(elements.optionsPanel.style.display === 'block') {
        elements.optionsButton.className = elements.optionsButton.className.replace(' disabled', '');
        elements.optionsPanel.style.display = 'none';
      } else {
        elements.optionsButton.className += ' disabled';
        elements.optionsPanel.style.display = 'block';
      }
    };

    function onClick(evt) {
      var color = evt.target.id.substr(6);
      if(color === '') {
        color = evt.target.parentElement.id.substr(6);
      }
      selectColor(color);
    }

    elements.colors = [];
    var els = document.getElementsByClassName('background-color');
    for(var elIndex = 0; elIndex < els.length; elIndex++) {
      var el = els.item(elIndex);
      el.onclick = onClick;
      elements.colors.push(el);
    }

    elements.confirmButton.onclick = function() {
      if(activeColor !== '') {
        setColor(activeColor);
      }
      elements.optionsButton.className = elements.optionsButton.className.replace(' disabled', '');
      elements.optionsPanel.style.display = 'none';
    };
  }

  document.addEventListener('DOMContentLoaded', init);
})();

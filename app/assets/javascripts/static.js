(function() {
  'use strict';
  var elements = {};
  var activeColor = '';
  var currentUsername;

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
    var url = '/options/set/color/' + encodeURIComponent(color);
    var request = new XMLHttpRequest();
    request.open('GET', url, true);
    request.send();
  }

  function usernameEditStart() {
    currentUsername = elements.usernameLabel.textContent;
    elements.usernameInput.value = currentUsername;
    elements.usernameEdit.style.display = 'block';
    elements.usernameLabel.style.display = 'none';
    elements.usernameInput.focus();
  }

  function usernameEditConfirm() {
    var newName = elements.usernameInput.value.trim();
    if(newName === currentUsername || newName === '') {
      usernameEditCancel();
    } else {
      console.log('Saving new username [' + newName + '].');
      var url = '/options/set/username/' + encodeURIComponent(newName);
      var request = new XMLHttpRequest();
      request.onreadystatechange = function() {
        if (request.readyState === 4) {
          switch(request.responseText) {
            case 'OK':
              elements.profileLink.textContent = newName;
              elements.usernameLabel.textContent = newName;
              elements.usernameEdit.style.display = 'none';
              elements.usernameLabel.style.display = 'inline-block';
              break;
            case '':
              break;
            default:
              if(request.responseText.indexOf('ERROR:') === 0) {
                elements.usernameEditError.textContent = request.responseText.substr(6);
              } else {
                throw request.responseText;
              }
              break;
          }
        }
      };
      request.open('GET', url, true);
      request.send();
    }
  }

  function usernameEditCancel() {
    elements.usernameInput.value = currentUsername;
    elements.usernameEdit.style.display = 'none';
    elements.usernameLabel.style.display = 'inline-block';
  }

  function saveUsername() {
    var url = '/options/set/username/' + elements.usernameInput.value;
    var request = new XMLHttpRequest();
    request.open('GET', url, true);
    request.send();
  }

  function init() {
    elements.optionsButton = document.getElementById('btn-game-options');
    elements.optionsPanel = document.getElementById('static-options-panel');
    elements.confirmButton = document.getElementById('btn-confirm');
    elements.usernameContainer = document.getElementById('username-container');
    if(elements.usernameContainer !== null) {
      elements.profileLink = document.getElementById('profile-link');
      elements.usernameLabel = document.getElementById('username-label');
      elements.usernameEdit = document.getElementById('username-edit');
      elements.usernameInput = document.getElementById('username-input');
      elements.usernameEditError = document.getElementById('username-edit-error');
      elements.usernameEditConfirm = document.getElementById('username-edit-confirm');
      elements.usernameEditCancel = document.getElementById('username-edit-cancel');
    }

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

    if(elements.usernameContainer !== null) {
      elements.usernameInput.onkeypress = function(e) {
        if (e.keyCode === 13) {
          usernameEditConfirm();
          e.preventDefault();
        } else if(e.keyCode === 27) {
          usernameEditCancel();
          e.preventDefault();
        }
      };
      elements.usernameLabel.onclick = usernameEditStart;
      elements.usernameEditConfirm.onclick = saveUsername;
      elements.usernameEditConfirm.onclick = usernameEditConfirm;
      elements.usernameEditCancel.onclick = usernameEditCancel;
    }
  }

  document.addEventListener('DOMContentLoaded', init);
})();

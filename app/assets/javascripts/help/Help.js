/* global define:false */
/* global _:false */
define(['ui/Modal'], function(Modal) {
  var online = document.location.href.indexOf('http') > -1;

  var Help = function(game) {
    this.game = game;
  };

  Help.prototype.toggleRules = function() {
    var postLoad = function() {
      _.each(document.getElementsByClassName('help-link'), function(helpLink) {
        helpLink.onclick = function() {
          var href;
          if(online) {
            href = helpLink.href + '?inline=true';
          } else {
            href = helpLink.href.substr(1) + '.html';
          }
          Modal.show('GET', href, {}, postLoad);
          return false;
        };
      });
    };
    var url;
    if(online) {
      url = '/help/' + this.game.rules + '?inline=true';
    } else {
      url = 'help/' + this.game.rules + '.html';
    }
    Modal.show('GET', url, {}, postLoad);
  };

  Help.prototype.toggleFeedback = function() {
    var g = this.game;
    this.game.options.hidePanels();
    var postLoad = function() {
      var textarea = document.getElementById('feedback-input');
      var button = document.getElementById('feedback-submit');
      var cancel = document.getElementById('feedback-cancel');

      textarea.onfocus = function() {
        console.log('off');
        g.keyboard.disable();
      };
      textarea.onblur = function() {
        console.log('on');
        g.keyboard.enable();
      };

      button.onclick = function() {
        var text = textarea.value;
        if(text === '') {
          alert('Please enter some feedback first.');
        } else {
          var postUrl = '/feedback';
          if(!online) {
            postUrl = 'http://solitaire.gg/feedback';
          }
          Modal.show('POST', postUrl, { 'feedback': text });
        }
      };

      cancel.onclick = function() {
        Modal.hide();
        return false;
      };
    };
    var getUrl = '/feedback';
    if(!online) {
      getUrl = 'http://solitaire.gg/feedback';
    }
    Modal.show('GET', getUrl, {}, postLoad);
  };

  return Help;
});

/* global define:false */
/* global _:false */
define(['ui/Modal'], function(Modal) {
  var Help = function(game) {
    this.game = game;
  };

  Help.prototype.toggleRules = function() {
    var postLoad = function() {
      _.each(document.getElementsByClassName('help-link'), function(helpLink) {
        helpLink.onclick = function() {
          Modal.show('GET', helpLink.href + '?inline=true', {}, postLoad);
          return false;
        };
      });
    };
    Modal.show('GET', '/help/' + this.game.rules + '?inline=true', {}, postLoad);
  };

  Help.prototype.toggleFeedback = function() {
    var g = this.game;
    this.game.options.hidePanels();
    var postLoad = function() {
      var textarea = document.getElementById('feedback-input');
      var button = document.getElementById('feedback-submit');

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
          Modal.show('POST', '/feedback', { 'feedback': text });
        }
      };
    };
    Modal.show('GET', '/feedback', {}, postLoad);
  };

  return Help;
});

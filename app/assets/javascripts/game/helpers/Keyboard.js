/* global define:false */
/* global Phaser:false */
define(['ui/Modal'], function (Modal) {
  'use strict';

  function toggleDebug() {
    var debugPanels = document.getElementsByClassName('pdebug');
    if(debugPanels.length === 1) {
      console.log(debugPanels[0].style.display);
      if(debugPanels[0].style.display === 'none' || debugPanels[0].style.display === '') {
        debugPanels[0].style.display = 'block';
      } else {
        debugPanels[0].style.display = 'none';
      }
    }
  }

  var Keyboard = function(game) {
    this.game = game;
  };

  Keyboard.prototype.init = function() {
    var g = this.game;

    var helpKey = g.input.keyboard.addKey(Phaser.Keyboard.H);
    helpKey.onDown.add(function () {
      g.help.toggleRules();
    });

    var undoKey = g.input.keyboard.addKey(Phaser.Keyboard.Z);
    undoKey.onDown.add(function() {
      if(g.options.undosAvailable > 0) {
        g.send('Undo', {});
      }
    });

    var redoKey = g.input.keyboard.addKey(Phaser.Keyboard.Y);
    redoKey.onDown.add(function() {
      if(g.options.redosAvailable > 0) {
        g.send('Redo', {});
      }
    });

    var hideModalKey = g.input.keyboard.addKey(Phaser.Keyboard.ESC);
    hideModalKey.onDown.add(function() {
      Modal.hide();
    });

    var sandboxKey = g.input.keyboard.addKey(Phaser.Keyboard.X);
    sandboxKey.onDown.add(function() {
      g.sandbox();
    });

    var debugKey = g.input.keyboard.addKey(Phaser.Keyboard.SPACEBAR);
    debugKey.onDown.add(toggleDebug);
  };

  Keyboard.prototype.enable = function() {
    this.game.input.keyboard.enabled = true;
  };

  Keyboard.prototype.disable = function() {
    this.game.input.keyboard.enabled = false;
  };

  return Keyboard;
});

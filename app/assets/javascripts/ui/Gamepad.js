/* global define:false */
define([], function() {
  var Gamepad = function(game) {
    if(game.input.gamepad.supported) {
      game.input.gamepad.addCallbacks({
        onConnectCallback: function(x, y, z) {
          console.log('onConnectCallback', x, y, z);
        },
        onDisconnectCallback: function(x, y, z) {
          console.log('onDisconnectCallback', x, y, z);
        },
        onDownCallback: function(x, y, z) {
          console.log('onDownCallback', x, y, z);
        },
        onUpCallback: function(x, y, z) {
          console.log('onUpCallback', x, y, z);
        },
        onAxisCallback: function(x, y, z) {
          console.log('onAxisCallback', x, y, z);
        },
        onFloatCallback: function(x, y, z) {
          console.log('onFloatCallback', x, y, z);
        }
      });

      game.input.gamepad.start();
      console.log('Gamepads initialized.');
    } else {
      console.log('No gamepad support detected.');
    }
  };

  return Gamepad;
});

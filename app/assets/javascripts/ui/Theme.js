/* global define:false */
/* global _:false */
define(['ui/Colors'], function(Colors) {
  function setColor(color) {
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

  var Theme = function(game) {
    Colors.init(function(color) {
      setColor(color);
      game.send('SetPreference', { 'name': 'color', 'value': color });
    });
  };

  return Theme;
});

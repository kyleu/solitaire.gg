/* global define:false */
define([], function () {
  'use strict';

  function onOrientationChange(scale, prevOrientation) {
    console.log('OC: Was [' + prevOrientation + '], now [' + scale.screenOrientation + ']. Window: [' + window.innerWidth + 'x' +  window.innerHeight + '].');
    document.getElementById('game-container').style.width = window.innerWidth;
    document.getElementById('game-container').style.height = window.innerHeight;
  }

  return {
    init: function(game) {
      game.scale.onOrientationChange.add(onOrientationChange);
    }
  };
});

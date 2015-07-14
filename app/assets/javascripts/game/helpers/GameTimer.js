/* global define:false */
define([], function () {
  'use strict';

  function GameTimer() {

  }

  GameTimer.prototype.start = function(elapsedMs) {
    if(elapsedMs) {
      this.timerStarted = new Date().getTime() + elapsedMs;
    } else {
      this.timerStarted = new Date().getTime();
    }

    var el = document.getElementById('timer-display');
    var self = this;

    function timerTick() {
      var delta = new Date().getTime() - self.timerStarted;
      var minutes = parseInt(delta / 1000 / 60);
      var seconds = parseInt((delta / 1000) % 60);
      if(seconds < 10) {
        seconds = '0' + seconds;
      }
      el.textContent = minutes + ':' + seconds;
      setTimeout(timerTick, 1000);
    }

    setTimeout(timerTick, 1000);
  };

  return GameTimer;
});

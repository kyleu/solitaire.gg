/* global define:false */
define([], function() {
  var initialized = false;
  var el;

  function init() {
    initialized = true;
    el = document.getElementById('game-results-panel');
  }

  function show() {
    el.style.display = 'block';
  }

  return {
    initialized: function() { return initialized; },
    init: init,
    show: show
  };
});

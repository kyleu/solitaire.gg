/* global define:false */
define([], function() {
  var initialized = false;
  var el;
  var cells;

  function init() {
    initialized = true;
    el = document.getElementById('game-results-panel');
    cells = {
      score: document.getElementById('results-score'),
      time: document.getElementById('results-time'),
      moves: document.getElementById('results-moves'),
      undos: document.getElementById('results-undos')
    };
  }

  function show(result) {
    el.style.display = 'block';
    cells.score.innerText = result.score;
    cells.time.innerText = result.durationSeconds + ' seconds';
    cells.moves.innerText = result.moves;
    cells.undos.innerText = result.undos;
  }

  return {
    initialized: function() { return initialized; },
    init: init,
    show: show
  };
});

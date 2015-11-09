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
      undos: document.getElementById('results-undos'),
      statsWins: document.getElementById('results-stats-wins'),
      statsLosses: document.getElementById('results-stats-losses'),
      statsDuration: document.getElementById('results-stats-duration'),
      statsMoves: document.getElementById('results-stats-moves'),
      statsUndos: document.getElementById('results-stats-undos')
    };
  }

  function show(result, stats) {
    var timeSeconds = parseInt(result.durationSeconds);
    var time = Math.round(timeSeconds / 60) + 'm ' + Math.round(timeSeconds % 60) + 's';

    cells.score.innerText = result.score;
    cells.time.innerText = time;
    cells.moves.innerText = result.moves;
    cells.undos.innerText = result.undos;

    var durationSeconds = parseInt(stats.games.totalDurationMs) / 1000;
    var duration = Math.round(durationSeconds / 60) + 'm ' + Math.round(durationSeconds % 60) + 's';

    cells.statsWins.innerText = stats.games.wins;
    cells.statsLosses.innerText = stats.games.losses;
    cells.statsDuration.innerText = duration;
    cells.statsMoves.innerText = stats.games.totalMoves;
    cells.statsUndos.innerText = stats.games.totalUndos;
    el.style.display = 'block';
  }

  return {
    initialized: function() { return initialized; },
    init: init,
    show: show
  };
});

/* global define:false */
define([], function () {
  function sandbox(game) {
    game.send('DebugInfo', { data: 'cheat win' });
    return 'Ok: ' + game.id;
  }

  return {
    go: function(game) {
      var startTime = new Date().getTime();
      var result = sandbox(game);
      var elapsed = new Date().getTime() - startTime;
      console.log('Sandbox executed in [' + elapsed + 'ms] with result [' + result + '].');
      return result;
    }
  };
});

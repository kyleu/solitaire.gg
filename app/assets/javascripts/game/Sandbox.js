define([], function () {
  function sandbox(game) {
    return "Ok: " + game.id;
  }

  return {
    "go": function(game) {
      var startTime = new Date().getTime();
      var result = sandbox(game);
      var elapsed = new Date().getTime() - startTime;
      console.log("Sandbox executed in [" + elapsed + "ms] with result [" + result + "].");
      return result;
    }
  };
});

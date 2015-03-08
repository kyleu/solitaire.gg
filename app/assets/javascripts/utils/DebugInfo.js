define([], function() {
  return {
    getDebugInfo: function(scene, game) {
      return {
        "userAgent": window.navigator.userAgent,
        "gameHeight": game === null ? 0 : game.height,
        "gameWidth": game === null ? 0 : game.width,
        "windowHeight": window.innerHeight,
        "windowWidth": window.innerWidth,
        "screenHeight": screen.height,
        "screenWidth": screen.width,
        "screenOrientation": window.screen.orientation === undefined ? "?" : window.screen.orientation.type,
        "gameId": game === null ? "-" : game.id.toString()
      };
    }
  };
});

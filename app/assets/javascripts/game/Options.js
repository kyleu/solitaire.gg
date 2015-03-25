define(["utils/Config"], function(cfg) {
  return {
    start: function() {
      var cardSet = gui.add(options, 'cardSet', {
        'Standard': 'standard',
        'Anglo': 'anglo'
      }).name('Card Set');
      cardSet.onFinishChange(function(value) {
        console.log("The new value is " + value);
      });

      var cardSize = gui.add(options, 'cardSize', {
        'Extra Small': 'x-small',
        'Small': 'small',
        'Medium': 'medium',
        'Large': 'large',
        'Extra Large': 'x-large'
      }).name('Card Size');
      cardSize.onFinishChange(function(value) {
        console.log("The new value is " + value);
      });

      var autoFlip = gui.add(options, 'autoFlip').name('Auto Flip Cards');
      autoFlip.onFinishChange(function(value) {
        console.log("The new value is " + value);
      });

      var variant = gui.add(options, 'variant', variants).name('Variant');
      variant.onFinishChange(function(value) {
        console.log("The new value is " + value);
      });

      gui.add(options, 'startGame').name('Start New Game');

      var container = document.getElementById('game-container');
    }
  };
});

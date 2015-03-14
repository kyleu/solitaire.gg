define(["utils/Config"], function(cfg) {
  var Options = function() {
    this.cardSet = cfg.cardSet;
    this.cardSize = cfg.cardSize;
    this.autoFlip = true;
    this.variant = cfg.variant;
    this.startGame = function() {
      document.location.href = "/play/" + this.variant;
    };
  };

  var variants = {};
  for(var v in cfg.variants) {
    variants[cfg.variants[v][1]] = cfg.variants[v][0];
  }

  return {
    start: function() {
      var options = new Options();
      var gui = new dat.GUI({ autoPlace: false });
      gui.name = cfg.name + " Options";
      gui.closed = true;

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
      gui.domElement.style.position = 'absolute';
      gui.domElement.style.top = '0';
      gui.domElement.style.right = '20px';
      container.appendChild(gui.domElement);
    }
  };
});

define(["Config"], function(cfg) {
  var Options = function() {
    this.cardSet = cfg.cardSet;
    this.cardSize = cfg.cardSize;
    this.autoFlip = true;
    this.foo = false;
  };

  return {
    start: function() {
      var options = new Options();
      var gui = new dat.GUI({ autoPlace: true });
      gui.name = cfg.name + " Options";
      gui.closed = true;
      gui.add(options, 'cardSet', {
        'Standard': 'standard',
        'Anglo': 'anglo'
      }).name('Card Set');
      gui.add(options, 'cardSize', {
        'Extra Small': 'x-small',
        'Small': 'small',
        'Medium': 'medium',
        'Large': 'large',
        'Extra Large': 'x-large'
      }).name('Card Size');
      gui.add(options, 'autoFlip').name('Auto Flip Cards');

      //var container = document.getElementById('game-container');
      //gui.domElement.style.position = 'absolute';
      //gui.domElement.style.top = '20px';
      //gui.domElement.style.right = '20px';
      //container.appendChild(gui.domElement);
    }
  };
});

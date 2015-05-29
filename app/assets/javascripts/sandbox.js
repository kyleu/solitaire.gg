function go() {
  "use strict";
  var container = document.getElementById("container");
  document.getElementById("win").innerText = (window.innerWidth + "x" + window.innerHeight);
  document.getElementById("doc").innerText = (document.body.clientHeight + "x" + document.body.clientWidth);
  document.getElementById("screen").innerText = (screen.width + "x" + screen.height);
  document.getElementById("con").innerText = (container);
}

window.addEventListener("orientationchange", function() {
  "use strict";
  go();
});

window.addEventListener("resize", function() {
  "use strict";
  go();
});

Boot = function (game) {
  this.game = game;
};

var i = 0;

Boot.prototype = {
  init: function () {
    //  This tells the game to resize the renderer to match the game dimensions (i.e. 100% browser width / height)
    this.scale.scaleMode = Phaser.ScaleManager.RESIZE;
  },

  preload: function () {

  },

  create: function () {

  },

  resize: function(x, y, z) {
    console.log(x, y, z);
  },

  update: function() {
  }
};

var init = function() {
  //new Phaser.Game('100%', '100%', Phaser.AUTO, 'container', new Boot());
};

window.addEventListener('load', function() {
  "use strict";
  go();
  init();
}, false);

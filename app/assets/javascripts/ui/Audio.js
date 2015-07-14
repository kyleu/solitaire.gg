/* global define:false */
define([], function() {
  var Audio = function(game) {
    var soundManager = game.sound;

    this.sounds = {
      shuffle: soundManager.add('audio-shuffle'),
      flip: soundManager.add('audio-flip'),
      win: soundManager.add('audio-win'),
      loss: soundManager.add('audio-loss')
    };

    console.log('Audio initialized, [' + soundManager.channels + '] channels available.');
  };

  return Audio;
});

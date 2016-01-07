/* global define:false */
define([], function() {
  var promptsElement = document.getElementById('game-prompts');

  function show(key) {
    promptsElement.style.position = 'absolute';
    promptsElement.style.display = 'block';
  }

  function hide() {
    promptsElement.style.display = 'none';
    promptsElement.style.position = 'relative';
  }

  var offlineOffered;

  return {
    offerOffline: function() {
      if(offlineOffered === undefined || offlineOffered > 5) {
        offlineOffered = 0;
      }
      if(offlineOffered === 0) {
        console.log('Hey buddy, wanna go offline?');
        show();
        offlineOffered += 1;
      } else {
        offlineOffered += 1;
      }
    },
    cancel: hide
  };
});

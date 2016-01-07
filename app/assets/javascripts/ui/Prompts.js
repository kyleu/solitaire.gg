/* global define:false */
define([], function() {
  var promptsElement = document.getElementById('game-prompts');

  var activePanel = null;

  function show(key) {
    promptsElement.style.position = 'absolute';
    promptsElement.style.display = 'block';

    if(activePanel !== null) {
      activePanel.style.display = 'none';
    }

    activePanel = document.getElementById('prompt-panel-' + key);
    if(activePanel === null) {
      throw key;
    }

    activePanel.style.display = 'block';
  }

  function hide() {
    promptsElement.style.display = 'none';
    promptsElement.style.position = 'relative';
  }

  return {
    //offerLowRes: function() { show('lowres'); },
    offerOffline: function() { show('offline'); },
    cancel: hide
  };
});

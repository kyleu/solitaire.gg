requirejs.config({
  baseUrl: '/assets/javascripts',
  paths: {
    lib: '/assets/lib'
  }
});

requirejs(['game/Scalataire'], function(Scalataire) {
  var callback = function() {
    alert("No callback defined.");
  };
  if(window.onConnected !== undefined) {
    callback = window.onConnected;
  }
  window.scalataire = new Scalataire(callback);
});

"use strict";

window.shouldRotateToOrientation = function() {
  return true;
};

function checkConnection() {
  var networkState = navigator.connection.type;
  //alert('Connection type: ' + networkState);
}

var app = {
  initialize: function() {
    this.bindEvents();
  },

  bindEvents: function() {
    document.addEventListener('deviceready', this.onDeviceReady, false);
  },

  onDeviceReady: function() {
    checkConnection();
  }
};

app.initialize();

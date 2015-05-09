define([], function() {
  var div = document.getElementById("gameplay-modal");
  var backdrop = document.getElementById("gameplay-modal-backdrop");
  var content = document.getElementById("gameplay-modal-content");
  var iframe = document.getElementById("gameplay-modal-iframe");

  var Modal = {
    show: function(url) {
      iframe.src = url;
      div.className = "on";
    },

    hide: function() {
      div.className = "off";
    },

    isVisible: function() {
      console.log(div.className);
      return div.className === "on";
    }
  };

  backdrop.onclick = function() {
    Modal.hide();
    return true;
  };

  return Modal;
});

define([], function() {
  var div = document.getElementById("gameplay-modal");
  var backdrop = document.getElementById("gameplay-modal-backdrop");
  var content = document.getElementById("gameplay-modal-content");
  var iframe = document.getElementById("gameplay-modal-iframe");

  var Modal = {
    show: function(url) {
      iframe.src = url;
      content.style.height = (window.innerHeight - 120 - 20) + "px";
      content.style.marginTop = "60px";
      div.className = "on";
    },

    hide: function() {
      div.className = "off";
    },

    isVisible: function() {
      return div.className === "on";
    }
  };

  backdrop.onclick = function() {
    Modal.hide();
    return true;
  };

  return Modal;
});

define([], function() {
  var div = document.getElementById("gameplay-modal");
  var backdrop = document.getElementById("gameplay-modal-backdrop");
  var content = document.getElementById("gameplay-modal-content");
  var iframe = document.getElementById("gameplay-modal-iframe");

  var Modal = {
    show: function(height, margin, url) {
      iframe.src = url;
      content.style.marginTop = margin + "px";
      content.style.height = (height - 40) + "px";
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

define(['utils/Network'], function(Network) {
  var modalElement = document.getElementById("gameplay-modal");
  var backdropElement = document.getElementById("gameplay-modal-backdrop");
  var contentElement = document.getElementById("gameplay-modal-content");
  var loadingElement = document.getElementById("gameplay-modal-loading");
  var dataElement = document.getElementById("gameplay-modal-data");

  var errorCallback = function(url, statusCode, errorContent) {
    alert("Error [" + statusCode + "] for [" + url + "]: " + errorContent);
  };

  var Modal = {
    show: function(method, url, requestData, postLoad) {
      contentElement.style.height = (window.innerHeight - 120 - 20) + "px";
      contentElement.style.marginTop = "60px";

      dataElement.style.display = "none";
      loadingElement.style.display = "block";
      modalElement.className = "on";
      Network.call(method, url, requestData, function(url, result) {
        dataElement.innerHTML = result;
        loadingElement.style.display = "none";
        dataElement.style.display = "block";
        dataElement.scrollTop = 0;

        _.each(document.getElementsByClassName("modal-close"), function(el) {
          el.onclick = Modal.hide;
        });

        if(postLoad !== undefined) {
          postLoad(method, url, result);
        }
      }, errorCallback);
    },

    hide: function() {
      modalElement.className = "off";
    },

    isVisible: function() {
      return modalElement.className === "on";
    }
  };

  backdropElement.onclick = function() {
    Modal.hide();
    return true;
  };

  return Modal;
});

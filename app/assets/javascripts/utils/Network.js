define([], function() {
  "use strict";

  var Network = {
    call: function(method, url, requestData, successCallback, failureCallback) {
      var request = new XMLHttpRequest();

      var query = [];
      for(var key in requestData) {
        query.push(encodeURIComponent(key) + '=' + encodeURIComponent(requestData[key]));
      }

      request.onreadystatechange = function() {
        if(request.readyState == XMLHttpRequest.DONE) {
          if(request.status == 200) {
            successCallback(url, request.responseText);
          } else {
            failureCallback(url, request.responseText);
          }
        }
      };
      request.onerror = function() {
        failureCallback(url, "Network error");
      };

      if(method === "GET") {
        var getUrl = url;
        if(query.length > 0) {
          getUrl = url + "?" + query.join('&');
        }
        request.open(method, getUrl, true);
        request.send();
      } else if(method === "POST") {
        request.open(method, url, true);
        request.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        request.send(query.join('&'));
      } else {
        throw "Unsupported method [" + method + "].";
      }
    }
  };

  return Network;
});

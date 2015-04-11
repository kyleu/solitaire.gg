$(function() {
  "use strict";

  $("#change-name-link").on('click', function() {
    var name = document.getElementById("account" ).value;
    if(name.length === 0) {
      alert("Please enter a name");
    } else {
      document.location.href = ('/changeName?name=' + name);
    }
    return false;
  });

  $("#account").on('keypress', function(evt) {
    if(evt.charCode === 13) {
      var name = document.getElementById("account").value;
      if(name.length === 0) {
        alert("Please enter a name");
      } else {
        document.location.href = ('/changeName?name=' + name);
      }
    }
  });
});

function wireLinks() {
  "use strict";
  $(".trace-link").on('click', function(ev) {
    var url = ev.currentTarget.href;
    $("#trace-content").load(url, null, function() {
      wireLinks();
    });
    return false;
  });
}

function wireSearchButton() {
  "use strict";
  $("#search-button").on('click', function(ev) {
    $("#search-form").submit();
    return false;
  });
}

$(function() {
  "use strict";
  wireLinks();
  wireSearchButton();
});

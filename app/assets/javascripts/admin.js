function wireLinks() {
  console.log($(".trace-link").size());

  $(".trace-link").on('click', function(ev) {
    var url = ev.currentTarget.href;
    $("#trace-content").load(url, null, function() {
      wireLinks();
    });
    return false;
  });
}

$(function() {
  wireLinks();
});

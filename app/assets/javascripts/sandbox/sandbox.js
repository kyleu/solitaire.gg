function go() {
  var container = document.getElementById("container");
  document.getElementById("win").innerText = (window.innerWidth + "x" + window.innerHeight);
  document.getElementById("doc").innerText = (document.body.clientHeight + "x" + document.body.clientWidth);
  document.getElementById("screen").innerText = (screen.width + "x" + screen.height);
  document.getElementById("con").innerText = (container);
}

window.addEventListener("orientationchange", function() {
  alert("the orientation of the device is now " + screen.orientation);
  go();
});

window.addEventListener('load', go, false);

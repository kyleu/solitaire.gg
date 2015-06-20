define([], function() {
  "use strict";

  var elements = {};
  var activeColor = "";
  var selectCallback = function() {};

  function selectColor(color) {
    for(var elIndex in elements.colors) {
      var el = elements.colors[elIndex];
      if(el.id == "color-" + color) {
        el.parentElement.className = "swatch-border active";
      } else {
        el.parentElement.className = "swatch-border";
      }
    }
    var bgIndex = document.body.className.indexOf("background-");
    document.body.className = document.body.className.substring(0, bgIndex) + " background-" + color;
    document.getElementById("favicon").href = "/assets/images/ui/favicon/favicon-" + color + ".png";

    var buttons = document.getElementsByClassName("btn");
    for(var btnIndex = 0; btnIndex < buttons.length; btnIndex++) {
      var btn = buttons.item(btnIndex);
      var cn = btn.className;
      var colorIndexStart = cn.indexOf("btn-");
      var colorIndexEnd = cn.indexOf(" ", colorIndexStart);
      if(colorIndexEnd == -1) {
        colorIndexEnd = cn.length;
      }
      var newClassName = cn.substr(0, colorIndexStart) + "btn-" + color + cn.substr(colorIndexEnd);
      btn.className = newClassName;
    }

    if(activeColor !== color) {
      selectCallback(color);
      activeColor = color;
    }
  }

  function init(callback) {
    if(callback !== undefined) {
      selectCallback = callback;
    }
    function onClick(evt) {
      var color = evt.target.id.substr(6);
      selectColor(color);
    }

    elements.colors = [];
    var els = document.getElementsByClassName("swatch");
    for(var elIndex = 0; elIndex < els.length; elIndex++) {
      var el = els.item(elIndex);
      el.onclick = onClick;
      elements.colors.push(el);
    }
  }

  return {
    init: init
  };
});

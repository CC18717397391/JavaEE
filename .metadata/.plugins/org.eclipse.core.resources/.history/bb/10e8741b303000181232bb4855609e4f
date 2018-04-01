(function() {
  "use strict";

  var system = {
    "win": false,
    "mac": false,
    "xll": false,
    "ipad": false
  };
  var p = navigator.platform;
  system.win = 0 === p.indexOf("Win");
  system.mac = 0 === p.indexOf("Mac");
  system.x11 = "X11" === p || 0 === p.indexOf("Linux");
  system.ipad = null !== navigator.userAgent.match(/iPad/i) ? true : false;
  if (!system.win && !system.mac && !system.xll && !system.ipad) {
    window.location.href = "phone.html";
  }
})();

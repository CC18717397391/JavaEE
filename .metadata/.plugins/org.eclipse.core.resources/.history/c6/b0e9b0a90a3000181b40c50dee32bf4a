(function() {
  "user strict";

  window.cookieOperation = {
    "set": setCookies,
    "get": getCookies,
    "delete": removeCookies
  };

  function setCookies(key, value, options) {
    var TIMES = 864e5;
    options = options || {};
    if ("number" === typeof options.expires) {
      var day = options.expires;
      options.expires = new Date();
      options.expires.setTime(options.expires.getTime() + day * TIMES);
    }
    document.cookie = [
      key,
      "=",
      encodeURIComponent(value),
      options.expires ? ";expires=" + options.expires.toUTCString() : "",
      options.path ? ";path=" + options.path : "",
      options.domain ? ";domain=" + options.domain : "",
      options.secure ? ";secure" : ""
    ].join("");
  }

  function getCookies(key) {
    var cookies = document.cookie ? document.cookie.split("; ") : [];
    for (var i = 0; i < cookies.length; i++) {
      var parts = cookies[i].split("=");
      if (parts[0] === key) {
        return decodeURIComponent(parts[1]);
      }
    }
    return null;
  }

  function removeCookies(key) {
    setCookies(key, "", { "expires": -1 });
    return !getCookies(key);
  }
})();

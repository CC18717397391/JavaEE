(function() {
  "user strict";

  var username = "";
  var password = "";
  $("[data-toggle='tooltip']").tooltip({
    "trigger": "focus",
    "placement": "top"
  });
  $("#submit").click(function() {
    username = $("#username").val();
    password = $("#password").val();
    if (check()) {
      submit();
    }
  });
  $("#username").focus(function() {
    $(".error").text("");
  });
  $("#password").focus(function() {
    $(".error").text("");
  });
  $(".form-group > input").keyup(function(e) {
    var key = 13;
    var smallKey = 108;
    if (key === e.keyCode || smallKey === e.keyCode) {
      $("#submit").click();
    }
  });

  function check() {
    if (!username || !password) {
      $(".error").text("请输入用户名和密码！");
      return false;
    }
    if (
      !/^\w{3,10}$/.test(username) ||
      !/^[A-Za-z]{1}([A-Za-z0-9]|[._*]){5,19}$/.test(password)
    ) {
      $(".error").text("用户名或者密码格式输入有误！");
      return false;
    }
    return true;
  }

  function submit() {
    $.ajax({
      "type": "post",
      "url": "/iyizhan/permission/login",
      "data": {
        "username": username,
        "password": password
      },
      "dataType": "json",
      "success": function(response) {
        if ("FAILED" === response.state) {
          $(".error").text(response.errorMessage);
        } else if ("REDIRECT" === response.state) {
          window.location.href = response.redirect_url;
        }
      },
      "error": function() {
        $(".error").text("服务器链接失败，请稍后再试！");
      }
    });
  }
})();
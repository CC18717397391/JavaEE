(function() {
  "user strict";

  function getParams(body) {
    var params = {};
    var items = body.split("&");
    var temp = "";
    $.each(items, function(index, value) {
      temp = value.split("=");
      params[temp[0]] = temp[1];
    });
    return params;
  }

  Mock.mock("/iyizhan/permission/login", "post", function(request) {
    var response = {
      "state": "FAILED",
      "errorMessage": "用户名或者密码输入有误！"
    };
    var params = getParams(request.body);
    if ("admin123" === params.password && "admin" === params.username) {
      response.state = "SUCCESS";
    }
    return Mock.mock(response);
  });

  Mock.mock("/iyizhan/permission/login", "put", function(request) {
    var response = {
      "state": "FAILED",
      "errorMessage": "密码输入有误！"
    };
    var params = getParams(request.body);
    if ("admin123" !== params.oldPwd) {
      response.errorMessage = "密码校验失败！";
    } else if (params.oldPwd === params.newPwd) {
      response.errorMessage = "新密码和旧密码一样！";
    } else if (params.newPwd !== params.confirmPwd) {
      response.errorMessage = "新密码与确认密码不一致";
    } else {
      response.state = "SUCCESS";
    }
    return Mock.mock(response);
  });

  Mock.mock("/iyizhan/permission/logout", "post", function() {
    return Mock.mock({
      "state": "FAILED",
      "errorMessage": "注销失败！"
    });
  });
})();

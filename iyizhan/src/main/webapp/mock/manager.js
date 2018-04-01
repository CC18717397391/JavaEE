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

  var imageObj = {
    "uid": 1,
    "img_url": "/iyizhan/mock/img/"
  };
  Mock.mock("/iyizhan/image/images", "get", function() {
    var response = {
      "state": "SUCCESS",
      "list": []
    };
    var temp = {};
    for (var index = 1; 7 > index; index++) {
      temp = $.extend({}, imageObj);
      temp.uid = index;
      temp.img_url += Math.ceil(Math.random() * 3) + ".jpg";
      response.list.push(temp);
    }
    return Mock.mock(response);
  });
  Mock.mock(/\/iyizhan\/image\/images\/\d+/, "delete", function() {
    return Mock.mock({
      "state": "FAILED",
      "errorMessage": "删除失败！"
    });
  });
  Mock.mock("/iyizhan/image/images", "post", function(request) {
    console.log(request);
    return Mock.mock({
      "state": "SUCCESS"
    });
  });

  var userObj = {
    "uid": 1,
    "name": "名字",
    "company": "XXX有限公司",
    "img_url": "/iyizhan/mock/img/",
    "job": "软件开发工程师",
    "salary": "15K"
  };
  Mock.mock("/iyizhan/user/users", "get", function() {
    var response = {
      "state": "SUCCESS",
      "list": []
    };
    var temp = {};
    for (var index = 1; 10 > index; index++) {
      temp = $.extend({}, userObj);
      temp.uid = index;
      temp.img_url += Math.ceil(Math.random() * 3) + ".jpg";
      response.list.push(temp);
    }
    return Mock.mock(response);
  });
  Mock.mock(/\/iyizhan\/user\/users\/\d+/, "delete", function() {
    return Mock.mock({
      "state": "FAILED",
      "errorMessage": "删除失败！"
    });
  });
  Mock.mock(/\/iyizhan\/user\/users\/\d+/, "get", function() {
    var temp = $.extend({}, userObj);
    temp.img_url += Math.ceil(Math.random() * 3) + ".jpg";
    return Mock.mock({
      "state": "SUCCESS",
      "data": temp
    });
  });
  Mock.mock("/iyizhan/user/users", "post", function(request) {
    console.log(request);
    return Mock.mock({
      "state": "SUCCESS"
    });
  });
  Mock.mock(/\/iyizhan\/user\/users\/\d+/, "put", function(request) {
    console.log(request);
    return Mock.mock({
      "state": "SUCCESS"
    });
  });

  var articalList = {
    "state": "SUCCESS",
    "total": 0,
    "list": [
      {
        "uid": 1,
        "title": "标题",
        "content": "正文",
        "cover_url": "/iyizhan/mock/img/1.jpg",
        "create_time": "2018-03-24 12:30:30",
        "update_time": "2018-03-24 12:30:30"
      }
    ]
  };
  for (var index = 0; 32 > index; index++) {
    articalList.list.push($.extend({}, articalList.list[0]));
  }
  articalList.list[0].is_hot = true;
  Mock.mock(/\/iyizhan\/artical\/articals\?\.*/, "get", function(request) {
    var params = getParams(request.url.split("?")[1]);
    var response = $.extend({}, articalList);
    response.total = Math.ceil(response.list.length / params.limit, 10);
    response.list = response.list.slice(
      (params.page - 1) * params.limit,
      params.page * params.limit
    );
    return Mock.mock(response);
  });
  Mock.mock(/\/iyizhan\/artical\/articals\/\d+/, "delete", function() {
    return Mock.mock({
      "state": "SUCCESS"
    });
  });
  Mock.mock(/\/iyizhan\/artical\/articals\/\d+/, "get", function() {
    return Mock.mock({
      "state": "SUCCESS",
      "total": 0,
      "data": {
        "uid": 1,
        "title": "标题",
        "content": "正文",
        "cover_url": "/iyizhan/mock/img/1.jpg",
        "create_time": "2018-03-24 12:30:30",
        "update_time": "2018-03-24 12:30:30"
      }
    });
  });
  Mock.mock("/iyizhan/artical/articals", "post", function(request) {
    console.log(request);
    return Mock.mock({
      "state": "SUCCESS"
    });
  });
  Mock.mock(/\/iyizhan\/artical\/articals\/\d+/, "put", function(request) {
    console.log(request);
    return Mock.mock({
      "state": "SUCCESS"
    });
  });
})();
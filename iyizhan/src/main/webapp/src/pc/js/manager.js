(function() {
  "user strict";
  permission();

  var editorImg = UE.getEditor("editorImg", {
    "toolbars": [["insertimage"]]
  });
  var editor = null;

  //  后台管理页面
  var time = {
    "artical": 0,
    "user": 0,
    "img": 0,
    "password": 0
  };
  var TIME_SPACE = 1000;
  $("#articalManager").click(function() {
    if (new Date().getTime() - time.artical < TIME_SPACE) {
      return;
    }
    time.artical = new Date().getTime();
    articalList = {
      "page": 1,
      "limit": 10,
      "total": 1
    };
    setTemplate(
      "/iyizhan/src/pc/views/manager/artical.html",
      "#articalManager",
      initArtical
    );
  });
  $("#articalManager").click();
  $("#userManager").click(function() {
    if (new Date().getTime() - time.user < TIME_SPACE) {
      return;
    }
    time.user = new Date().getTime();
    setTemplate(
      "/iyizhan/src/pc/views/manager/user.html",
      "#userManager",
      initUser
    );
  });
  $("#imgManager").click(function() {
    if (new Date().getTime() - time.img < TIME_SPACE) {
      return;
    }
    time.img = new Date().getTime();
    setTemplate(
      "/iyizhan/src/pc/views/manager/img.html",
      "#imgManager",
      initImg
    );
  });
  $("#passwordManager").click(function() {
    if (new Date().getTime() - time.password < TIME_SPACE) {
      return;
    }
    time.password = new Date().getTime();
    setTemplate(
      "/iyizhan/src/pc/views/manager/password.html",
      "#passwordManager",
      initPassword
    );
  });
  function setTemplate(url, id, callback) {
    if (null !== editor) {
      editor.destroy();
      editor = null;
    }
    $.ajax({
      "type": "get",
      "url": url,
      "success": function(response) {
        $(".right")
          .empty()
          .append(response);
        $(".left>li").removeClass("active");
        $(id).addClass("active");
        callback && callback();
      },
      "error": function() {
        alert("服务器链接失败，请稍后再试！");
      }
    });
  }
  $("#logout").click(function() {
    $("#fiModal .modal-title").text("退出登录");
    $("#fiModal .modal-body").text("确定要退出登陆吗？");
    $("#fiModal").modal("toggle");
    $("#modalSubmit")
      .off("click")
      .on("click", function() {
        logout();
      });
  });

  //                 文章管理 开始
  function initArtical() {
    $("#listArt").show();
    $("#editorArt").hide();
    getArticalList();
    bindArt();
  }

  var articalList = {
    "page": 1,
    "limit": 10,
    "total": 1
  };

  function bindArt() {
    var uid = -1;
    editor = UE.getEditor("editor", {
      "toolbars": [
        [
          "|",
          "undo",
          "redo",
          "|",
          "bold",
          "italic",
          "underline",
          "fontborder",
          "strikethrough",
          "superscript",
          "subscript",
          "removeformat",
          "formatmatch",
          "autotypeset",
          "blockquote",
          "pasteplain",
          "|",
          "forecolor",
          "backcolor",
          "insertorderedlist",
          "insertunorderedlist",
          "selectall",
          "cleardoc",
          "|",
          "rowspacingtop",
          "rowspacingbottom",
          "lineheight",
          "|",
          "customstyle",
          "paragraph",
          "fontfamily",
          "fontsize",
          "|",
          "directionalityltr",
          "directionalityrtl",
          "indent",
          "|",
          "justifyleft",
          "justifycenter",
          "justifyright",
          "justifyjustify",
          "|",
          "touppercase",
          "tolowercase",
          "|",
          "link",
          "unlink",
          "anchor",
          "|",
          "imagenone",
          "imageleft",
          "imageright",
          "imagecenter",
          "insertimage",
          "|",
          "horizontal",
          "date",
          "time",
          "spechars",
          "|",
          "inserttable",
          "deletetable",
          "insertparagraphbeforetable",
          "insertrow",
          "deleterow",
          "insertcol",
          "deletecol",
          "mergecells",
          "mergeright",
          "mergedown",
          "splittocells",
          "splittorows",
          "splittocols",
          "|",
          "help"
        ]
      ]
    });
    // 创建按钮
    $("#createArtical").on("click", function() {
      $("#listArt").hide();
      $("#editorArt").show();
    });
    // 表格的编辑和删除按钮
    $("#listArt .table").on("click", "td>a", function(e) {
      uid = $(e.target)
        .parent()
        .attr("uid");
      var type = $(e.target).attr("operation");
      if (uid === undefined || type === undefined) {
        return;
      }
      if ("delete" === type) {
        $("#fiModal .modal-title").text("删除文章");
        $("#fiModal .modal-body").text(
          "是否要删除文章：" +
            $(e.target)
              .parent()
              .parent()
              .children()[0].innerText
        );
        $("#fiModal").modal("toggle");
      }
      if ("modify" === type) {
        $("#listArt").hide();
        $("#editorArt").show();
        getArtcalInfo(uid);
      }
    });
    // 删除弹窗的确认按钮
    $("#modalSubmit")
      .off("click")
      .on("click", function() {
        deleteArtical(uid);
      });
    // 分页按钮
    $("#listArt .pagination").on(
      "click",
      "li>a:not([disable]):not(.active)",
      function(e) {
        var value = $(e.target).text();
        if ("首页" === value) {
          articalList.page = 1;
        } else if ("尾页" === value) {
          articalList.page = articalList.total;
        } else {
          articalList.page = parseInt(value, 10);
        }
        getArticalList();
      }
    );
    $("#articalSubmit").click(function() {
      var method = "post";
      var url = "/iyizhan/artical/articals";
      var uid = $("#editorArt").attr("uid");
      if (uid !== undefined) {
        method = "put";
        url += "/" + uid;
      }
      if (!$("#artTitle").val()) {
        alert("标题不能为空");
        return;
      }
      $.ajax({
        "type": method,
        "url": url,
        "data": {
          "title": $("#artTitle").val(),
          "cover_url": $("#coverImg img").attr("src") || "",
          "content": editor.getContent()
        },
        "dataType": "json",
        "success": function(response) {
          if ("SUCCESS" === response.state) {
            $("#articalManager").click();
          } else if ("REDIRECT" === response.state) {
            window.location.href = response.redirect_url;
          } else {
            alert(response.errorMessage);
          }
        },
        "error": function() {
          alert("服务器链接失败，请稍后再试！");
        }
      });
    });
    $("#coverBtn").click(function() {
      editorImg.getDialog("insertimage").open();
    });
    editorImg.removeListener("beforeInsertImage");
    editorImg.addListener("beforeInsertImage", function(t, arg) {
      $("#coverImg")
        .empty()
        .append("<img src='" + arg[0].src + "'>");
    });
  }

  function getArticalList() {
    $.ajax({
      "type": "get",
      "url": "/iyizhan/artical/articals",
      "data": {
        "page": articalList.page,
        "limit": articalList.limit
      },
      "dataType": "json",
      "success": function(response) {
        if ("SUCCESS" === response.state) {
          resetArticalList(response);
          resetArticalPagination();
        } else {
          alert(response.errorMessage);
        }
      },
      "error": function() {
        alert("服务器链接失败，请稍后再试！");
      }
    });
  }

  // 重置表格
  function resetArticalList(response) {
    var html = "";
    articalList.total = response.total;
    $.each(response.list, function(index, value) {
      html += "<tr><td>" + value.title + "</td>";
      html += "<td>" + value.create_time + "</td>";
      html +=
        "<td uid='" +
        value.uid +
        "'><a class=\"mr-10\" operation=\"modify\">编辑</a><a operation=\"delete\">删除</a></td></tr>";
    });
    $("#listArt table>tbody").empty();
    $("#listArt table>tbody").append(html);
  }

  // 重置分页
  function resetArticalPagination() {
    var LAST = 3;
    var MAX = 7;
    var html =
      "<li><a " + (1 === articalList.page ? "disable" : "") + ">首页</a></li>";
    var temp = 0 < articalList.page - LAST ? articalList.page - LAST : 1;
    for (var index = temp; index <= articalList.total; index++) {
      if (MAX < index - temp) {
        break;
      }
      html +=
        "<li><a class='" +
        (index === articalList.page ? "active" : "") +
        "'>" +
        index +
        "</a></li>";
    }
    html +=
      "<li><a " +
      (articalList.total === articalList.page ? "disable" : "") +
      ">尾页</a></li>";
    $("#listArt .page-info").text("总页数：" + articalList.total);
    $("#listArt .pagination").empty();
    $("#listArt .pagination").append(html);
  }

  function deleteArtical(uid) {
    $.ajax({
      "type": "delete",
      "url": "/iyizhan/artical/articals/" + uid,
      "dataType": "json",
      "success": function(response) {
        if ("FAILED" === response.state) {
          alert(response.errorMessage);
        } else if ("REDIRECT" === response.state) {
          window.location.href = response.redirect_url;
        } else {
          articalList.page = 1;
          getArticalList();
          $("#fiModal").modal("toggle");
        }
      },
      "error": function() {
        alert("服务器链接失败，请稍后再试！");
      }
    });
  }

  function getArtcalInfo(uid) {
    $.ajax({
      "type": "get",
      "url": "/iyizhan/artical/articals/" + uid,
      "dataType": "json",
      "success": function(response) {
        if ("FAILED" === response.state) {
          alert(response.errorMessage);
        } else {
          $("#editorArt").attr("uid", response.data.uid);
          $("#artTitle").val(response.data.title);
          editor.setContent(response.data.content);
          $("#coverImg")
            .empty()
            .append(
              "<img class='preview-img' src='" + response.data.cover_url + "'>"
            );
        }
      },
      "error": function() {
        alert("服务器链接失败，请稍后再试！");
      }
    });
  }
  //                 文章管理 结束

  //                 学员管理 开始
  function initUser() {
    $("#listUser").show();
    $("#editorUser").hide();
    getUserList();
    bindUser();
  }

  function bindUser() {
    var uid = -1;
    $("#addUserImg").click(function() {
      editorImg.getDialog("insertimage").open();
    });
    editorImg.removeListener("beforeInsertImage");
    editorImg.addListener("beforeInsertImage", function(t, arg) {
      $("#userImg")
        .empty()
        .append("<img src='" + arg[0].src + "'>");
    });
    // 创建按钮
    $("#createUser").on("click", function() {
      $("#listUser").hide();
      $("#editorUser").show();
    });
    $("#modalSubmit")
      .off("click")
      .on("click", function() {
        deleteUser(uid);
      });
    // 操作按钮
    $("#listUser .img-container").on("click", ".item a", function(e) {
      var type = $(e.target).attr("type");
      uid = $(e.target)
        .parent()
        .attr("uid");
      if ("delete" === type) {
        $("#fiModal .modal-title").text("删除学员");
        $("#fiModal .modal-body").text(
          "确定要删除学员 " +
            $(
              $(e.target)
                .parent()
                .parent().children[0]
            ).text() +
            " ？"
        );
        $("#fiModal").modal("toggle");
      } else {
        $("#listUser").hide();
        $("#editorUser").show();
        getUserInfo(uid);
      }
    });

    $("#userSubmit").click(function() {
      var method = "post";
      var url = "/iyizhan/user/users";
      var uid = $("#editorUser").attr("uid");
      if (uid !== undefined) {
        method = "put";
        url += "/" + uid;
      }
      if (!$("#userName").val()) {
        alert("名字不能为空");
        return;
      }
      $.ajax({
        "type": method,
        "url": url,
        "data": {
          "name": $("#userName").val(),
          "img_url": $("#userImg img").attr("src") || "",
          "company": $("#company").val(),
          "salary": $("#salary").val(),
          "job": $("#job").val()
        },
        "dataType": "json",
        "success": function(response) {
          if ("SUCCESS" === response.state) {
            $("#userManager").click();
          } else if ("REDIRECT" === response.state) {
            window.location.href = response.redirect_url;
          } else {
            alert(response.errorMessage);
          }
        },
        "error": function() {
          alert("服务器链接失败，请稍后再试！");
        }
      });
    });
  }

  function getUserList() {
    $.ajax({
      "type": "get",
      "url": "/iyizhan/user/users",
      "dataType": "json",
      "success": function(response) {
        var html = "";
        if ("SUCCESS" === response.state) {
          $.each(response.list, function(index, value) {
            html += getUserHtml(value);
          });
          $("#listUser .img-container")
            .empty()
            .append(html);
        } else {
          $(".error").text(response.errorMessage);
        }
      },
      "error": function() {
        alert("服务器链接失败，请稍后再试！");
      }
    });
  }

  function getUserHtml(data) {
    return (
      "<div class=\"item\"><div class=\"item-header\"><img src=\"" +
      data.img_url +
      "\"></div><div class=\"item-body\"><div class=\"item-body-title\"><div class=\"float-left\">" +
      data.name +
      "</div><div class=\"float-right\" uid=\"" +
      data.uid +
      "\" class=\"item-body-title\"><a type='modify' class=\"glyphicon glyphicon-pencil mr-10\"></a>" +
      "<a type='delete' class=\"glyphicon glyphicon-trash\"></a></div></div></div></div>"
    );
  }

  function deleteUser(uid) {
    $.ajax({
      "type": "delete",
      "url": "/iyizhan/user/users/" + uid,
      "dataType": "json",
      "success": function(response) {
        if ("FAILED" === response.state) {
          alert(response.errorMessage);
        } else if ("REDIRECT" === response.state) {
          window.location.href = response.redirect_url;
        } else {
          getUserList();
          $("#fiModal").modal("toggle");
        }
      },
      "error": function() {
        alert("服务器链接失败，请稍后再试！");
      }
    });
  }

  function getUserInfo(uid) {
    $.ajax({
      "type": "get",
      "url": "/iyizhan/user/users/" + uid,
      "dataType": "json",
      "success": function(response) {
        if ("FAILED" === response.state) {
          alert(response.errorMessage);
        } else {
          $("#editorUser").attr("uid", response.data.uid);
          $("#userName").val(response.data.name);
          $("#job").val(response.data.job);
          $("#company").val(response.data.company);
          $("#salary").val(response.data.salary);
          $("#userImg")
            .empty()
            .append(
              "<img class='preview-img' src='" + response.data.img_url + "'>"
            );
        }
      },
      "error": function() {
        alert("服务器链接失败，请稍后再试！");
      }
    });
  }
  //                 学员管理 结束

  //                 图片管理 开始
  function initImg() {
    getImgList();
    bindImg();
  }

  function bindImg() {
    var uid = -1;
    $("#createImg").click(function() {
      editorImg.getDialog("insertimage").open();
    });
    editorImg.removeListener("beforeInsertImage");
    editorImg.addListener("beforeInsertImage", function(t, arg) {
      addImg(arg[0].src);
    });
    $("#modalSubmit")
      .off("click")
      .on("click", function() {
        deleteImg(uid);
      });
    // 操作按钮
    $("#listImg .img-container").on("click", ".item a", function(e) {
      uid = $(e.target)
        .parent()
        .attr("uid");
      $("#fiModal .modal-title").text("删除图片");
      $("#fiModal .modal-body").text("确定要删除该图片吗？");
      $("#fiModal").modal("toggle");
    });
  }

  function getImgList() {
    $.ajax({
      "type": "get",
      "url": "/iyizhan/image/images",
      "dataType": "json",
      "success": function(response) {
        var html = "";
        if ("SUCCESS" === response.state) {
          $.each(response.list, function(index, value) {
            html += getImgHtml(value);
          });
          $("#listImg .img-container")
            .empty()
            .append(html);
        } else {
          $(".error").text(response.errorMessage);
        }
      },
      "error": function() {
        alert("服务器链接失败，请稍后再试！");
      }
    });
  }

  function getImgHtml(data) {
    return (
      "<div class=\"item\"><div class=\"item-header\"><img src=\"" +
      data.img_url +
      "\"></div><div class=\"item-body\"><div uid=\"" +
      data.uid +
      "\" class=\"item-body-title\"><a class=\"glyphicon glyphicon-trash\"></a></div></div></div>"
    );
  }

  function addImg(url) {
    $.ajax({
      "type": "post",
      "url": "/iyizhan/image/images",
      "data": {
        "img_url": url
      },
      "dataType": "json",
      "success": function(response) {
        if ("FAILED" === response.state) {
          alert(response.errorMessage);
        } else if ("REDIRECT" === response.state) {
          window.location.href = response.redirect_url;
        } else {
          getImgList();
        }
      },
      "error": function() {
        alert("服务器链接失败，请稍后再试！");
      }
    });
  }

  function deleteImg(uid) {
    $.ajax({
      "type": "delete",
      "url": "/iyizhan/image/images/" + uid,
      "dataType": "json",
      "success": function(response) {
        if ("FAILED" === response.state) {
          alert(response.errorMessage);
        } else if ("REDIRECT" === response.state) {
          window.location.href = response.redirect_url;
        } else {
          getImgList();
          $("#fiModal").modal("toggle");
        }
      },
      "error": function() {
        alert("服务器链接失败，请稍后再试！");
      }
    });
  }
  //                 图片管理 结束

  //                 重置密码 开始
  function initPassword() {
    $("#passwordContainer [data-toggle='tooltip']").tooltip({
      "trigger": "focus",
      "placement": "top"
    });
    $("#submit").click(function() {
      var oldPwd = $("#oldPwd").val();
      var newPwd = $("#newPwd").val();
      var confirmPwd = $("#confirmPwd").val();
      $(".error").text("");
      if (
        !/^[A-Za-z]{1}([A-Za-z0-9]|[._*]){5,19}$/.test(oldPwd) ||
        !/^[A-Za-z]{1}([A-Za-z0-9]|[._*]){5,19}$/.test(newPwd) ||
        !/^[A-Za-z]{1}([A-Za-z0-9]|[._*]){5,19}$/.test(confirmPwd)
      ) {
        $(".error").text("输入格式输入有误！");
      }
      if (newPwd !== confirmPwd) {
        $(".error").text("新密码与确认密码不一致");
      }
      submitResetPassword({
        "oldPwd": oldPwd,
        "newPwd": newPwd,
        "confirmPwd": confirmPwd
      });
    });
    $("#passwordContainer input").focus(function() {
      $(".error").text("");
    });
    $("#passwordContainer input").keyup(function(e) {
      var key = 13;
      var smallKey = 108;
      if (key === e.keyCode || smallKey === e.keyCode) {
        $("#submit").click();
      }
    });
  }

  function submitResetPassword(params) {
    $.ajax({
      "type": "put",
      "url": "/iyizhan/permission/login",
      "data": params,
      "dataType": "json",
      "success": function(response) {
        if ("FAILED" === response.state) {
          $(".error").text(response.errorMessage);
        } else if ("REDIRECT" === response.state) {
          window.location.href = response.redirect_url;
        }
      },
      "error": function() {
        alert("服务器链接失败，请稍后再试！");
      }
    });
  }
  //                 重置密码 结束

  function logout() {
    $.ajax({
      "type": "post",
      "url": "/iyizhan/permission/logout",
      "dataType": "json",
      "success": function(response) {
        if ("FAILED" === response.state) {
          alert(response.errorMessage);
        } else if ("REDIRECT" === response.state) {
          window.location.href = response.redirect_url;
        }
      },
      "error": function() {
        alert("服务器链接失败，请稍后再试！");
      }
    });
  }

  function permission() {
    $.ajax({
      "type": "post",
      "url": "/iyizhan/permission/permission",
      "success": function(response) {
        if ("REDIRECT" === response.state) {
          window.location.href = response.redirect_url;
        }
      },
      "error": function() {
        // alert("服务器链接失败，请稍后再试！");
      }
    });
  }
})();

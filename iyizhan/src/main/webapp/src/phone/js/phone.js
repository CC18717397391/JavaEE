(function() {
  $(".item-class-ui").click(function() {
    window.location.href = "/iyizhan/src/phone/views/ui.html";
  });
  $(".item-class-pm").click(function() {
    window.location.href = "/iyizhan/src/phone/views/pm.html";
  });
  $(".item-class-yw").click(function() {
    window.location.href = "/iyizhan/src/phone/views/maintenance.html";
  });
  $(".item-class-ps").click(function() {
    window.location.href = "/iyizhan/src/phone/views/ps.html";
  });

  $(".nav .operation").click(function() {
    $(".nav ul").toggleClass("hide");
    $(".nav").toggleClass("nav-open");
  });

  $("#op-index:not(.active)").click(function() {
    window.location.href = "/iyizhan/phone.html";
  });
  $("#op-ui:not(.active)").click(function() {
    window.location.href = "/iyizhan/src/phone/views/ui.html";
  });
  $("#op-pm:not(.active)").click(function() {
    window.location.href = "/iyizhan/src/phone/views/pm.html";
  });
  $("#op-yw:not(.active)").click(function() {
    window.location.href = "/iyizhan/src/phone/views/maintenance.html";
  });
  $("#op-ps:not(.active)").click(function() {
    window.location.href = "/iyizhan/src/phone/views/ps.html";
  });
})();

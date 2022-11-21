let index = {
  init: function () {
    $("#btn-save").on("click", () => {
      this.save();
    });
    $("#btn-delete").on("click", () => {
      this.delete();
    });
    $("#btn-update").on("click", () => {
      this.update();
    });
  },

  save: function () {
    let data={
      title: $("#title").val(),
      content: $("#content").val()
    };

    console.log(data);

    $.ajax({
      type: "POST",
      url: "/api/board",
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    }).done(function (response) {
      alert("글쓰기가 완료되었습니다.");
      location.href = "/";
    }).fail(function (error) {
      alert("글쓰기에 실패하였습니다.");
      alert(JSON.stringify(error));
    });
  },

  delete: function () {
    let id = $("#board-id").text();

    alert(id);

    $.ajax({
      type: "DELETE",
      url: "/api/board/" + id,
      dataType: "json"
    }).done(function (response) {
      alert("삭제가 완료되었습니다.");
      location.href = "/";
    }).fail(function (error) {
      alert("삭제에 실패하였습니다.");
      alert(JSON.stringify(error));
    });
  },

  update: function () {
    let id = $("#board-id").val();

    let data={
      title: $("#title").val(),
      content: $("#content").val()
    };

    console.log(content);

    $.ajax({
      type: "PUT",
      url: "/api/board/" + id + "/edit",
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json"
    }).done(function (response) {
      alert("수정이 완료되었습니다.");
      location.href = "/";
    }).fail(function (error) {
      alert("수정에 실패하였습니다.");
      alert(JSON.stringify(error));
    });
  },
}

index.init();
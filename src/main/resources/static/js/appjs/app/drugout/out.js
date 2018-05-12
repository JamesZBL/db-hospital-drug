$().ready(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        save();
    }
});

function save() {
    $.ajax({
        cache: true,
        type: "POST",
        url: "/inventory/drugout/save",
        data: $('#signupForm').serialize(),
        async: false,
        error: function (request) {
            parent.layer.alert("Connection error");
        },
        success: function (data) {
            if (data.code == 0) {
                parent.layer.msg("操作成功");
                parent.reLoad();
                var index = parent.layer.getFrameIndex(window.name); // 获取窗口索引
                parent.layer.close(index);

            } else {
                parent.layer.alert(data.msg)
            }

        }
    });

}

function validateRule() {
    $("#signupForm").validate({

    })
}

var openUser = function () {
    layer.open({
        type: 2,
        title: "选择人员",
        area: ['300px', '450px'],
        content: "/sys/user/treeView"
    })
}

function loadUser(userIds, userNames) {
    $("#userIds").val(userIds);
    $("#userNames").val(userNames);
}
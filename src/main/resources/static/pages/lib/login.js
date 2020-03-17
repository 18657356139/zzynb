function comment(text) {
    clearComment();
    $(".comment").show();
    $(".comment").text(text);
}

function commentSuccess() {
    clearComment();
    $(".comment_success").show();
    $(".comment_success").text("Register success!");
}

function clearComment(){
    $(".comment").hide();
    $(".comment_success").hide();
}

function login() {
    var username = $("#username").val();
    var pswd = $("#pswd").val();
    if (!username || !pswd) {
        comment("Username Or Password Cannot Be Null.");
        return;
    }
    $.ajax({
        url: "/login",
        method: "POST",
        data: {
            username: username,
            pswd: pswd
        },
        success: function (data) {
            if (data.indexOf("redirect") == 0) {
                window.location.href = data.replace("redirect:", "");
            } else {
                comment(data);
            }
        },
        error: function (data) {
            comment("Error!");
        }
    });
}

function register() {
    var username = $("#username").val();
    var pswd = $("#pswd").val();
    if (!username || !pswd) {
        comment("Username Or Password Cannot Be Null.");
        return;
    }
    $.ajax({
        url: "/register",
        method: "POST",
        data: {
            username: username,
            pswd: pswd
        },
        success: function (data) {
            clearComment();
            if (data == "success") {
                commentSuccess();
            } else {
                comment(data);
            }
        },
        error: function (data) {
            comment("Error!");
        }
    });
}
var vm;
var currentUser;

$(document).ready(function () {
    init();

    $("#type>input").keyup(function (e) {
        if (e.keyCode == 13) {
            say();
        }
    });
});

function openSpeaker() {
    $("#type").fadeIn();
    $(".open_speaker").fadeOut();
}

function closeSpeaker() {
    $("#type").fadeOut();
    $(".open_speaker").fadeIn();
}

function flushMsg() {
    $.ajax({
        url: "/getAllMsg",
        method: "POST",
        success: function (result) {
            vm.props = result.data;
            vm.currentUser = result.currentUser;
        }
    });
}

function say() {
    var content = $("#type>input").val().trim();
    if (!content) {
        return;
    }
    if (content.length > 50) {
        return;
    }
    $.ajax({
        url: "/addMsg",
        data: {
            content: content
        },
        method: "POST",
        success: function (result) {
            if (result == "success") {
                $("#type>input").val("")
                flushMsg();
            }
        }
    });
}

function init() {
    vm = new Vue({
        el: "#container",
        data: {
            currentUser: null,
            props: null
        }
    });
    flushMsg();
}
Vue.component("msg-li", {
    props: ["msg", "current_user"],
    template: `
                <div class='msg_wrapper'>
                    <div class='msg_title'>
                            <div class='username' :class='current_user == msg.username ? "green_color" : ""'>{{msg.username}}</div>
                            <div class='datetime'>{{msg.datetime}}</div>
                    </div><div class='msg_content'>
                            <span>{{msg.content}}</span>
                    </div>
                    
                </div>
            `
});
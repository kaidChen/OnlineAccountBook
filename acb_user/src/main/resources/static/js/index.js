function login() {
    var user = {};
    user.username = document.getElementById("username").value.trim()
    user.password = document.getElementById("password").value.trim()

    if (user.username === "" || user.password === "") {
        confirm("用户名或密码为空");
        return;
    }

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "/login", true);
    xhr.setRequestHeader("content-type", "application/json");
    xhr.send(JSON.stringify(user));

    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            const result = JSON.parse(xhr.responseText.toString());
            if (result["success"] === true) {
                window.location.href = "/home";
                return;
            }
            confirm(result["message"]);
        }
    }
}

function signup() {
    confirm("功能未开发！")
}
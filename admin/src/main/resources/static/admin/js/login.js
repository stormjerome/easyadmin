$(function() {
    makeCaptcha();
    $(document).keyup(function(event){
        if(event.keyCode ==13){
            $("#login-btn").trigger("click");
        }
    });
    $("#login-btn").click(function(){
        var captcha = $("#captcha").val();
        var username = $("#username").val();
        var password = $("#password").val();
        if(username == "" || password == "" || captcha == ""){
            layer.msg("请输入用户名/密码/验证码!",{time:1000,icon:2});
            return;
        }
        var data = {uuid:uuid,username:username,password:password,code:captcha};
        $.ajax({
            type : 'post',
            url : '/login',
            data:data,
            success : function(res) {
                if(res.code == 200){
                    localStorage.setItem(token_key, res.data);
                    window.location.href = "/index";
                }else{
                    $(".yzm-img").trigger("click");
                    layer.msg(res.msg,{time:1000,icon:1});
                }
            },
            error : function(xhr, textStatus, errorThrown) {
                var responseStr = xhr.responseText;
                var res = JSON.parse(responseStr);
                if (res.code == 401) {
                    localStorage.removeItem(token_key);
                }
                $(".yzm-img").trigger("click");
                layer.msg(res.msg,{time:1000,icon:2});

            }
        });

    });
});

function makeCaptcha(){
    $.get("/captcha",function (res) {
        if(res.code == 200){
            uuid = res.uuid;
            $(".yzm-img").attr("src","data:image/jpg;base64,"+ res.img);
        }
    });
}
var token = "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjkyZmE5MDQwLTc1YmItNDVhNC1hOTkzLTc3OWQ2ZjM4OWVmMiJ9.xGBftCPitz1_Og5zJh_Sd4DLbsyu_8idhyghw-h2U48HhWetqzWrX7c19eZ7sCzxPgf_kejBh6487uLeHg0rsA";
var token_key = "admin-toke";
var token_expires = 1800;//30分钟
/**
 * 获取token
 * */
function getToken() {
    return getCookie(token_key);
}

function setToken(token){
    setCookie(token_key,token,token_expires);
}

function removeToken(){
    removeCookie(token_key);
}

function getCookie(cookieName) {
    var start = document.cookie.indexOf(cookieName+"=");
    if(start == -1) return "";
    start = start + cookieName.length + 1;
    var end = document.cookie.indexOf(";",start);
    if(-1 == end) end = document.cookie.length;
    return decodeURIComponent(document.cookie.substring(start,end));
}

function setCookie(cookieName, cookieValue, seconds, path, domain, secure) {
    var expires = new Date();
    expires.setTime(expires.getTime() + seconds);
    document.cookie = escape(cookieName) + '=' + escape(cookieValue)
        + (expires ? '; expires=' + expires.toGMTString() : '')
        + (path ? '; path=' + path : '/')
        + (domain ? '; domain=' + domain : '')
        + (secure ? '; secure' : '');
}

function removeCookie(cookieName){
    setCookie(cookieName,'');
}

$.extend({
    ajaxGet:function( url , data , callback){
        $.ajax({
            url :url,
            method:'get',
            data:data ,
            success:callback ,
            error : function(xhr) {
                layer.closeAll('loading');
                var responseStr = xhr.responseText;
                var res = JSON.parse(responseStr);
                var msg = res.msg ? res.msg:res.message;
                layer.msg(msg,{time:1000,icon:2});
            }
        })
    },
    ajaxPost:function(url,data,success){
        $.ajax({
            url :url,
            method:'post',
            data:data ,
            success:success ,
            error : function(xhr) {
                layer.closeAll('loading');
                var responseStr = xhr.responseText;
                var res = JSON.parse(responseStr);
                var msg = res.msg ? res.msg:res.message;
                layer.msg(msg,{time:1000,icon:2});
            }
        })
    }
});
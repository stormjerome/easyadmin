layui.use(['form', 'layer'], function () {
    var form = layui.form,layer = layui.layer,$= layui.jquery;
    form.on('submit(add-btn)', function (data) {
        $.ajaxPost(postUrl,data.field,function(res){
            if(res.code == 200){
                layer.msg(res.msg,{time:1000,icon:1},function(){
                    goMenuIndex();
                });
            }else{
                layer.msg(res.msg,{time:1000,icon:2});
            }
        });
    })
});
function goMenuIndex(){
    location.href = apiRouter.menuIndex;
}
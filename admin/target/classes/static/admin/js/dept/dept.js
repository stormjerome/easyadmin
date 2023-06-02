var select_page = null;
layui.use(['form', 'layer'], function () {
    var form = layui.form,layer = layui.layer,$= layui.jquery;
    form.on('submit(add-btn)', function (data) {
        $.ajaxPost(postUrl,data.field,function(res){
            if(res.code == 200){
                layer.msg(res.msg,{time:1000,icon:1},function(){
                    goDeptIndex();
                });
            }else{
                layer.msg(res.msg,{time:1000,icon:2});
            }
        });
    });
    $("#parentDept").click(function(){
        var loading = layer.load(1, {shade: [0.1, '#fff']});
        var deptId = $("#parentId").val();
        select_page = layer.open({
            type: 2,
            maxmin: true,
            shade: 0.3,
            fix: false,
            skin:"commen-layer-form",
            title:"选择公司",
            area: ['400px', '400px'], //宽高
            resize:false,
            shadeClose: true,
            content: apiRouter.deptTree+"/"+deptId,
            end: function(){
                select_page = 0;
            }
            ,success:function(layero,index) {
                layer.close(loading);
            }
        });
    });
});
function selectDept(item){
    $("#parentDept").val(item.name);
    $("#parentId").val(item.id);
    $("#levelPath").val(item.levelPath+","+item.id);
    layui.layer.close(select_page);
}
function goDeptIndex(){
    location.href = apiRouter.deptIndex;
}
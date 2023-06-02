var select_dept_page = null;
layui.use(['form', 'layer','upload'], function () {
    var form = layui.form,layer = layui.layer,upload=layui.upload,$= layui.jquery;

    form.on('submit(add-btn)', function (data) {
        $.ajaxPost(postUrl,data.field,function(res){
            if(res.code == 200){
                layer.msg(res.msg,{time:1000,icon:1},function(){
                    goList();
                });
            }else{
                layer.msg(res.msg,{time:1000,icon:2});
            }
        });
    });
    var uploadInst = upload.render({
        elem: '#thumb_img_Btn'
        ,url: "/common/upload"
        ,before: function(obj){
            obj.preview(function(index, file, result){
                $('#thumb_img').attr('src', result);
            });
        }
        ,done: function(res){
            console.log(res);
            if(res.code == 200){

                $('#thumb_url').val(res.fileName);

            }else{
                return layer.msg('上传失败');
            }
        }
        ,error: function(){
            var demoText = $('#demoText');
            demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-mini demo-reload">重试</a>');
            demoText.find('.demo-reload').on('click', function(){
                uploadInst.upload();
            });
        }
    });
    $("#dept").click(function(){
        var loading = layer.load(1, {shade: [0.1, '#fff']});
        var deptId = 1;
        select_dept_page = layer.open({
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
    $("#dept").val(item.name);
    $("#deptId").val(item.id);
    layui.layer.close(select_dept_page);
}
function goList(){
    location.href = apiRouter.userIndex;
}
var tableIn;
layui.use(['table','form'],function(){
    var table = layui.table,form = layui.form, $ = layui.jquery;
    tableIn = table.render({
        elem: '#list',
        url: apiRouter.roleList,
        page:true,
        cols: [[
            {field: 'roleId', title: 'ID', width: 80, fixed: true},
            {field: 'roleName', title: '角色名',width: 150},
            {field: 'roleCode', title: '角色编号',width: 150},
            {field:'status', title: '是否开启',toolbar: '#status',width: 100},
            {field: 'sort',align: 'center', title: '排序', width: 150, templet: '#sort'},
            {field: 'remark', title: '备注',width: 150},
            {field:'createdAt', title: '添加时间'},
            {width: 260,align: 'center',title: '操作', toolbar: '#action'}
        ]],
        limit:15
    });

    form.on('switch(status)', function(obj){
        layer.load(1, {shade: [0.1,'#fff']});
        var id = this.value;
        var status = obj.elem.checked === true ? 1 : 0;
        $.ajaxPost(apiRouter.roleStatus,{'id':id,'status':status},function (res) {
            layer.closeAll('loading');
            if (res.code === 200) {
                layer.msg(res.msg,{time:1000,icon:1});
            }else{
                layer.msg(res.msg,{time:1000,icon:2});
                return false;
            }
        })
    });

    table.on('tool(list)', function(obj){
        var data = obj.data;
        if(obj.event === 'del'){
            layer.confirm('您确定要删除该记录吗？', function(index){
                var loading = layer.load(1, {shade: [0.1, '#fff']});
                $.ajaxPost(apiRouter.roleDel,{id:data.roleId},function(res){
                    layer.close(loading);
                    if(res.code==200){
                        layer.msg(res.msg,{time:1000,icon:1});
                        obj.del();
                    }else{
                        layer.msg(res.msg,{time:1000,icon:2});
                    }
                });
                layer.close(index);
            });
        }
    });
    $('body').on('blur','.list_order',function() {
        var id = $(this).attr('data-id');
        var sort = $(this).val();
        $.ajaxPost(apiRouter.roleSort,{id:id,sort:sort},function(res){
            if(res.code==200){
                layer.msg(res.msg,{time:1000,icon:1});
                setTimeout(function(){window.location.reload()},1000)
            }else{
                layer.msg(res.msg,{time:1000,icon:2});
            }
        })
    });
});

var tableIn;
layui.use(['table','form'],function(){
    var table = layui.table,form = layui.form, $ = layui.jquery;
    tableIn = table.render({
        elem: '#list',
        url: apiRouter.userList,
        page:true,
        cols: [[
            {field: 'adminUserId', title: 'ID', width: 80, fixed: true},
            {field: 'userName', title: '用户名',width: 150},
            {field: 'nickName', title: '昵称',width: 150},
            {field:'status', title: '是否开启',toolbar: '#status',width: 100},
            {field: 'deptName',align: 'center', title: '部门', width: 150},
            {field: 'phone', title: '电话号',width: 150},
            {field: 'email', title: '邮箱',width: 150},
            {field:'createdAt', title: '添加时间'},
            {width: 260,align: 'center',title: '操作', toolbar: '#action'}
        ]],
        parseData: function(res){
            var new_res = {
                "code": res.code,
                "msg": res.msg,
                "count": res.count,
                "page_count":1,
                "data":[]
            };
            $.each(res.data,function(i,n){
                var item = n;
                if(n.dept){
                    item.deptName = n.dept.deptName;
                }

                new_res.data.push(item);
            });
            return new_res;
        },
        limit:15
    });

    form.on('switch(status)', function(obj){
        layer.load(1, {shade: [0.1,'#fff']});
        var id = this.value;
        var status = obj.elem.checked === true ? 1 : 0;
        $.ajaxPost(apiRouter.userStatus,{'id':id,'status':status},function (res) {
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
                $.ajaxPost(apiRouter.userDel,{id:data.roleId},function(res){
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

});

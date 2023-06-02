var tableIn;
layui.config({
    base: '/admin/js/extends/'
}).extend({
    treetable: 'treetable/treetable'
}).use(['table','treetable','form'],function(){
    var table = layui.table,form = layui.form,treetable = layui.treetable, $ = layui.jquery;
    layer.load(2);
    tableIn = treetable.render({
        treeColIndex: 1,
        treeSpid: 0,
        treeIdName: 'menuId',
        treePidName: 'parentId',
        elem: '#list',
        url: apiRouter.menuList,
        page: false,
        cols: [[
            {field: 'menuId', title: '权限ID', width: 70, fixed: true},
            {field: 'menuName', align: 'center',title: '权限名称', width: 200},
            {field: 'icon', align: 'center',title: '图标', width: 100,templet: '#icon'},
            {field: 'menuUrl', title: '链接', width: 200},
            {field: 'status',align: 'center', title: '是否启用', width: 150,toolbar: '#status'},
            {field: 'isView',align: 'center',title: '菜单显示', width: 150,toolbar: '#is_view'},
            {field: 'sort',align: 'center', title: '排序', width: 150, templet: '#sort'},
            {field: 'remark', title: '描述'},
            {width: 200,align: 'center',title: '操作', toolbar: '#action'}
        ]],
        done: function () {
            var perms = [];
            $.ajaxGet(apiRouter.userPerms,{},function(res){
                if(res.code = 200){
                    perms = res.data;
                    $("[permission]").each(function() {
                        var perm = $(this).attr("permission");
                        if ($.inArray(perm, perms) < 0) {
                            $(this).hide();
                        }
                    });
                    if ($.inArray("sys:menu:edit", perms) < 0) {
                        $(".btn-edit").hide();
                    }
                    if ($.inArray("sys:menu:delete", perms) < 0) {
                        $(".btn-delete").hide();
                    }
                }
            });
            layer.closeAll('loading');
        }
    });
    $('#btn-open-all').click(function () {
        treetable.expandAll('#list');
    });
    $('#btn-close-all').click(function () {
        treetable.foldAll('#list');
    });
    form.on('switch(status)', function(obj){
        layer.load(1, {shade: [0.1,'#fff']});
        var id = this.value;
        var status = obj.elem.checked === true ? 1 : 0;
        $.ajaxPost(apiRouter.menuStatus,{'id':id,'status':status},function (res) {
            layer.closeAll('loading');
            if (res.code === 200) {
                layer.msg(res.msg,{time:1000,icon:1});
            }else{
                layer.msg(res.msg,{time:1000,icon:2});
                return false;
            }
        })
    });
    form.on('switch(isView)', function(obj){
        layer.load(1, {shade: [0.1,'#fff']});
        var id = this.value;
        var isView = obj.elem.checked === true?1:0;
        $.ajaxPost(apiRouter.menuView,{'id':id,'isView':isView},function (res) {
            layer.closeAll('loading');
            if (res.code == 200) {
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
                $.ajaxPost(apiRouter.menuDel,{id:data.menuId},function(res){
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
        $.ajaxPost(apiRouter.menuSort,{id:id,sort:sort},function(res){
            if(res.code==200){
                layer.msg(res.msg,{time:1000,icon:1});
                setTimeout(function(){window.location.reload()},1000)
            }else{
                layer.msg(res.msg,{time:1000,icon:2});
            }
        })
    });
});

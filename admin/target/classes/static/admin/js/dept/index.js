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
        treeIdName: 'deptId',
        treePidName: 'parentId',
        elem: '#list',
        url: apiRouter.deptList,
        page: false,
        cols: [[
            {field: 'deptId', title: '部门ID', width: 70, fixed: true},
            {field: 'deptName', align: 'center',title: '部门名称'},
            {field: 'status',align: 'center', title: '是否启用', width: 150,toolbar: '#status'},
            {field: 'sort',align: 'center', title: '排序', width: 150, templet: '#sort'},
            {field: 'createdAt',align: 'center', title: '创建时间'},
            {align: 'center',title: '操作', toolbar: '#action'}
        ]],
        done: function () {
            layer.closeAll('loading');
        }
    });
    $('#btn-open-all').click(function () {
        treetable.expandAll('#list');});
    $('#btn-close-all').click(function () {
        treetable.foldAll('#list');});
    form.on('switch(status)', function(obj){
        layer.load(1, {shade: [0.1,'#fff']});
        var id = this.value;
        var status = obj.elem.checked === true ? 1 : 0;
        $.ajaxPost(apiRouter.deptStatus,{'id':id,'status':status},function (res) {
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
                $.ajaxPost(apiRouter.deptDel,{id:data.deptId},function(res){
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
        $.ajaxPost(apiRouter.deptSort,{id:id,sort:sort},function(res){
            if(res.code==200){
                layer.msg(res.msg,{time:1000,icon:1});
                setTimeout(function(){window.location.reload()},1000)
            }else{
                layer.msg(res.msg,{time:1000,icon:2});
            }
        })
    });
});

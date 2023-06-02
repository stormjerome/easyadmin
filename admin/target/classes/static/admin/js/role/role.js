var zTree;
var setting = {
    view: {
        selectedMulti: false,      // 设置是否允许同时选中多个节点
        nameIsHTML: true           // 设置 name 属性是否支持 HTML 脚本
    },
    check: {
        enable: true,             // 置 zTree 的节点上是否显示 checkbox / radio
        nocheckInherit: true,      // 设置子节点是否自动继承
    },
    data: {
        simpleData: {enable: true, pIdKey:'pId', idKey:'id'},
        key:{title:'title'}
    }
};
layui.use(['form', 'layer'], function () {
    var form = layui.form,layer = layui.layer,$= layui.jquery;
    initTree();
    form.on('submit(add-btn)', function (data) {
        var roleData = data.field;
        roleData.menuIds = getCheckedNodes();

        $.ajaxPost(postUrl,roleData,function(res){
            if(res.code == 200){
                layer.msg(res.msg,{time:1000,icon:1},function(){
                   goList();
                });
            }else{
                layer.msg(res.msg,{time:1000,icon:2});
            }
        });
    });


});
function initTree(){
    var tData = {};
    if(roleId >0) tData.roleId = roleId;
    $.ajaxGet(apiRouter.menuTree,tData,function(data){
        zTree = $.fn.zTree.init($("#menuTree"), setting, data);
        setCheck();

        var nodes = zTree.getNodes();
        for (var i = 0; i < nodes.length; i++) { //设置节点展开
            zTree.expandNode(nodes[i], true, false, true);
        }
    });
}
function setCheck() {
    var zTree = $.fn.zTree.getZTreeObj("menuTree");
    zTree.setting.check.chkboxType = { "Y":"ps", "N":"ps"};
}
function getCheckedNodes(){
    var nodes = zTree.getCheckedNodes(true);
    return $.map(nodes, function (row) {
        return row["id"];
    }).join();
}
function goList(){
    location.href = apiRouter.roleIndex;
}
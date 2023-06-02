var selectedNode = {};
var zTree;
var setting = {
    view: {
        selectedMulti: false,      // 设置是否允许同时选中多个节点
        nameIsHTML: true           // 设置 name 属性是否支持 HTML 脚本
    },
    check: {
        enable: false,             // 置 zTree 的节点上是否显示 checkbox / radio
        nocheckInherit: true,      // 设置子节点是否自动继承
    },
    data: {
        simpleData: {enable: true, pIdKey:'pId', idKey:'id'},
        key:{title:'title'}
    },
    callback:{
        onClick:zOnClick
    }
};
initTree();
$(".layer_selected").click(function(){
    if(JSON.stringify(selectedNode) === "{}"){
        selectedNode.id = $("#deptId").val();
        selectedNode.name = $("#deptName").val();
        selectedNode.levelPath = $("#levelPath").val();
    }
    parent.selectDept(selectedNode);
});
function initTree(){
    $.ajaxGet(apiRouter.deptTreeData,{},function(data){
        zTree = $.fn.zTree.init($("#tree"), setting, data);

        var nodes = zTree.getNodesByParam("level",1);
        for (var j = 0; j < nodes.length; j++) { //设置节点展开
            zTree.expandNode(nodes[j], true, false, true);
        }
        var node = zTree.getNodeByParam("id", $("#deptId").val());
        if(node != null) {
            zTree.selectNode(node, true);
        }
    });
}
function zOnClick(event, treeId, treeNode) {
    selectedNode = treeNode;
}
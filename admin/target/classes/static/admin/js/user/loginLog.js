var tableIn;
layui.use(['table','form'],function(){
    var table = layui.table,form = layui.form, $ = layui.jquery;
    tableIn = table.render({
        elem: '#list',
        url: apiRouter.userLoginLog,
        page:true,
        cols: [[
            {field: 'logId', title: 'ID', width: 80, fixed: true},
            {field: 'userName', title: '用户名',width: 150},
            {field: 'loginIp', title: '登录IP',width: 150},
            {field: 'loginLocation', title: '登录地区',width: 150},
            {field:'userAgent', title: '登录终端'},
            {field:'loginTime', title: '登录时间'}
        ]],
        limit:15
    });

});

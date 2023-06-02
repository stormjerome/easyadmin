var tableIn;
layui.use(['table','form'],function(){
    var table = layui.table,form = layui.form, $ = layui.jquery;
    tableIn = table.render({
        elem: '#list',
        url: apiRouter.userOperateLog,
        page:true,
        cols: [[
            {field: 'logId', title: 'ID', width: 80, fixed: true},
            {field: 'title', title: '操作行为',width: 150},
            {field: 'userName', title: '操作人',width: 150},
            {field: 'operateIp', title: '登录IP',width: 150},
            {field: 'operateLocation', title: '操作地区',width: 150},
            {field: 'method', title: '请求方法名',width: 150},
            {field: 'requestUrl', title: '请求路径',width: 150},
            {field: 'requestType', title: '请求方式',width: 150},
            {field:'createdAt', title: '操作时间'}
        ]],
        limit:15
    });

});

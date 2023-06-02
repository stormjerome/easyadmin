// 接口访问地址
var apiRouter = {
    //图片上传
    uploadImg:"/upload",

    //菜单
    menuIndex:"/menu/index",         //菜单首页
    menuAdd:"/menu/add",             //菜单新增
    menuEdit:"/menu/edit",           //菜单编辑
    menuList:"/menu/list",           //菜单列表
    menuStatus:"/menu/changeStatus", //菜单状态更新
    menuView:"/menu/changeView",     //菜单显示更新
    menuSort:"/menu/changeSort",     //菜单排序更新
    menuDel:"/menu/delete",          //菜单删除
    menuCurrent:"/menu/current",     //当前用户的菜单
    userPerms:"/menu/getPerms",      //当前用户的权限
    menuTree:"/menu/treeData",       //权限菜单树

    //部门
    deptIndex:"/dept/index",         //部门首页
    deptTree:"/dept/tree",           //部门树
    deptTreeData:"/dept/treeData",   //部门树数据
    deptAdd:"/dept/add",             //部门新增
    deptEdit:"/dept/edit",           //部门编辑
    deptList:"/dept/list",           //部门列表
    deptStatus:"/dept/changeStatus", //部门状态更新
    deptSort:"/dept/changeSort",     //部门排序更新
    deptDel:"/dept/delete",          //部门删除

    //角色
    roleIndex:"/role/index",         //角色首页
    roleList:"/role/list",           //角色列表
    roleAdd:"/role/add",             //角色新增
    roleEdit:"/role/edit",           //角色编辑
    roleStatus:"/role/changeStatus", //角色状态更新
    roleSort:"/role/changeSort",     //角色排序更新
    roleDel:"/role/delete",          //角色删除

    //用户
    userIndex:"/user/index",         //用户首页
    userList:"/user/list",           //用户列表
    userAdd:"/user/add",             //用户添加
    userEdit:"/user/edit",           //用户编辑
    userStatus:"/user/changeStatus", //角色状态更新
    userDel:"/user/delete",          //用户删除
    userLoginLog:"/user/loginLogList",//用户登录日志
    userOperateLog:"/user/operateLogList",//用户操作日志

};
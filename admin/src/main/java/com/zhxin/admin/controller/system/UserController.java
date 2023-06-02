package com.zhxin.admin.controller.system;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhxin.common.utils.EasyUtil;
import com.zhxin.core.security.LoginUser;
import com.zhxin.core.security.util.UserUtil;
import com.zhxin.core.web.controller.BaseController;
import com.zhxin.core.web.model.ResponseVo;
import com.zhxin.core.web.model.TableResponseVo;
import com.zhxin.logic.system.model.SysAdminLoginLog;
import com.zhxin.logic.system.model.SysAdminOperateLog;
import com.zhxin.logic.system.model.SysAdminUser;
import com.zhxin.logic.system.model.SysRole;
import com.zhxin.logic.system.service.ISysAdminLoginLogService;
import com.zhxin.logic.system.service.ISysAdminOperateLogService;
import com.zhxin.logic.system.service.ISysAdminUserService;
import com.zhxin.logic.system.service.ISysRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName UserController
 * @Description //TODO
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/26 0026 下午 3:48
 **/
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    ISysAdminUserService sysAdminUserService;

    @Autowired
    ISysRoleService sysRoleService;

    @Autowired
    ISysAdminLoginLogService sysAdminLoginLogService;

    @Autowired
    ISysAdminOperateLogService sysAdminOperateLogService;

    @GetMapping("/index")
    public String index(){
        return "/system/user/index";
    }

    @GetMapping("/add")
    public String add(ModelMap modelMap){
        modelMap.put("roles",sysRoleService.selectRole(new SysRole()));
        return "/system/user/add";
    }

    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable Long userId, ModelMap modelMap){

        modelMap.put("info", sysAdminUserService.getUserById(userId));
        modelMap.put("roles", sysRoleService.selectRolesByUserId(userId));
        return "/system/user/edit";
    }

    @ApiOperation(value = "用户列表")
    @PreAuthorize("hasAuthority('sys:user:query')")
    @GetMapping("/list")
    @ResponseBody
    public TableResponseVo list(SysAdminUser user){
        startPage();
        List<SysAdminUser> list = sysAdminUserService.selectUserList(user);

        return getLayTable(list);
    }

    @ApiOperation(value = "用户新增")
    @PreAuthorize("hasAuthority('sys:user:add')")
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public ResponseVo add(SysAdminUser user) {

        if(!sysAdminUserService.checkUserByName(user.getUserName())) {
            return ResponseVo.error("已存在的管理员");
        }

        LoginUser loginUser = UserUtil.getLoginUser();
        user.setPassword(EasyUtil.encryptPassword(user.getPassword()));
        user.setCreatedBy(loginUser.getUser().getAdminUserId());
        user.setUpdatedBy(loginUser.getUser().getAdminUserId());

        return ajaxReturn(sysAdminUserService.insertUser(user));
    }

    @ApiOperation(value = "用户编辑")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public ResponseVo edit(SysAdminUser user){

        LoginUser loginUser = UserUtil.getLoginUser();
        user.setUpdatedBy(loginUser.getUser().getAdminUserId());

        return ajaxReturn(sysAdminUserService.updateUser(user));
    }

    @ApiOperation(value = "用户状态更新")
    @PreAuthorize("hasAuthority('sys:user:edit')")
    @PostMapping("/changeStatus")
    @ResponseBody
    public ResponseVo changeStatus(@RequestParam("id") Long userId, @RequestParam("status") int status){
        LoginUser loginUser = UserUtil.getLoginUser();

        SysAdminUser user = new SysAdminUser();
        user.setAdminUserId(userId);
        user.setStatus(status);
        user.setUpdatedBy(loginUser.getUser().getAdminUserId());

        return ajaxReturn(sysAdminUserService.updateUserSimple(user));
    }

    @GetMapping("/loginLog")
    public String loginLog(){
        return "/system/user/loginLog";
    }

    @ApiOperation(value = "用户登录日志")
    @PreAuthorize("hasAuthority('sys:loginLog:query')")
    @GetMapping("/loginLogList")
    @ResponseBody
    public TableResponseVo loginLog(SysAdminLoginLog log){
        startPage();
        List<SysAdminLoginLog> list = sysAdminLoginLogService.selectLog(log);

        return getLayTable(list);
    }

    @GetMapping("/operateLog")
    public String operateLog(){
        return "/system/user/operateLog";
    }

    @ApiOperation(value = "用户操作日志")
    @PreAuthorize("hasAuthority('sys:operateLog:query')")
    @GetMapping("/operateLogList")
    @ResponseBody
    public TableResponseVo operateLog(SysAdminOperateLog log){
        startPage();
        List<SysAdminOperateLog> list = sysAdminOperateLogService.selectLog(log);

        return getLayTable(list);
    }
}

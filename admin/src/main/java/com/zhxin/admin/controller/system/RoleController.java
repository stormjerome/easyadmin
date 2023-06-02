package com.zhxin.admin.controller.system;

import com.zhxin.common.page.TableDataInfo;
import com.zhxin.common.web.ZtreeVo;
import com.zhxin.core.security.LoginUser;
import com.zhxin.core.security.util.UserUtil;
import com.zhxin.core.web.controller.BaseController;
import com.zhxin.core.web.model.ResponseVo;
import com.zhxin.core.web.model.TableResponseVo;
import com.zhxin.logic.system.model.SysDept;
import com.zhxin.logic.system.model.SysMenu;
import com.zhxin.logic.system.model.SysRole;
import com.zhxin.logic.system.service.ISysMenuService;
import com.zhxin.logic.system.service.ISysRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

/**
 * @ClassName RoleController
 * @Description //角色管理接口
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/14 0014 下午 2:22
 **/
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    @Autowired
    ISysRoleService sysRoleService;




    @GetMapping("/index")
    public String index(){
        return "/system/role/index";
    }

    @GetMapping("/add")
    public String add(){

        return "/system/role/add";
    }

    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable Long roleId,ModelMap modelMap){
        SysRole info = sysRoleService.selectRoleById(roleId);

        modelMap.put("info",info);
        return "/system/role/edit";
    }

    @GetMapping("/auth/{roleId}")
    public String auth(@PathVariable Long roleId){
        return "/system/role/user";
    }


    @ApiOperation(value = "角色列表")
    @PreAuthorize("hasAuthority('sys:role:query')")
    @GetMapping("/list")
    @ResponseBody
    public TableResponseVo list(SysRole role){
        startPage();
        List<SysRole> list = sysRoleService.selectRole(role);

        return getLayTable(list);
    }


    @ApiOperation(value = "角色新增")
    @PreAuthorize("hasAuthority('sys:role:add')")
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public ResponseVo add(SysRole role) {
        LoginUser loginUser = UserUtil.getLoginUser();
        role.setCreatedBy(loginUser.getUser().getAdminUserId());
        role.setUpdatedBy(loginUser.getUser().getAdminUserId());

        return ajaxReturn(sysRoleService.insertRole(role));
    }

    @ApiOperation(value = "角色编辑")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public ResponseVo edit(SysRole role) {
        LoginUser loginUser = UserUtil.getLoginUser();
        role.setUpdatedBy(loginUser.getUser().getAdminUserId());

        return ajaxReturn(sysRoleService.updateRole(role));
    }

    @ApiOperation(value = "角色状态更新")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @PostMapping("/changeStatus")
    @ResponseBody
    public ResponseVo changeStatus(@RequestParam("id") Long roleId, @RequestParam("status") int status){
        LoginUser loginUser = UserUtil.getLoginUser();

        SysRole role = new SysRole();
        role.setRoleId(roleId);
        role.setStatus(status);
        role.setUpdatedBy(loginUser.getUser().getAdminUserId());

        return ajaxReturn(sysRoleService.updateRoleSimple(role));
    }

    @ApiOperation(value = "角色排序更新")
    @PreAuthorize("hasAuthority('sys:role:edit')")
    @PostMapping("/changeSort")
    @ResponseBody
    public ResponseVo changeSort(@RequestParam("id") Long menuId,@RequestParam("sort") int sort){
        LoginUser loginUser = UserUtil.getLoginUser();

        SysRole role = new SysRole();
        role.setRoleId(menuId);
        role.setSort(sort);
        role.setUpdatedBy(loginUser.getUser().getAdminUserId());
        return ajaxReturn(sysRoleService.updateRoleSimple(role));
    }

    @ApiOperation(value = "角色删除")
    @PreAuthorize("hasAuthority('sys:role:delete')")
    @PostMapping("/delete")
    @ResponseBody
    public ResponseVo deleteRole(@RequestParam("id") Long roleId){
        LoginUser loginUser = UserUtil.getLoginUser();

        SysRole role = new SysRole();
        role.setRoleId(roleId);
        role.setDeletedFlag(1);
        role.setStatus(0);
        role.setDeletedBy(loginUser.getUser().getAdminUserId());

        ResponseVo result =ajaxReturn(sysRoleService.removeRole(role));
        reloadUserAuthority();
        return result;
    }
}

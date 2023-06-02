package com.zhxin.admin.controller.system;

import com.zhxin.common.annotation.Log;
import com.zhxin.common.enums.OperateType;
import com.zhxin.common.utils.StringUtil;
import com.zhxin.common.web.ZtreeVo;
import com.zhxin.core.security.LoginUser;
import com.zhxin.core.security.util.UserUtil;
import com.zhxin.core.web.controller.BaseController;
import com.zhxin.core.web.model.ResponseVo;
import com.zhxin.core.web.model.TableResponseVo;
import com.zhxin.logic.system.model.SysMenu;
import com.zhxin.logic.system.model.SysRole;
import com.zhxin.logic.system.service.ISysMenuService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @ClassName MenuController
 * @Description //菜单权限类
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/26 0026 下午 3:26
 **/
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    ISysMenuService sysMenuService;

    @GetMapping("/index")
    public String index(){

        return "/system/menu/index";
    }

    @PreAuthorize("hasAuthority('sys:menu:add')")
    @GetMapping("/add")
    public String add(Model model){

        List<SysMenu> treeMenu = getAllMenuList();


        model.addAttribute("menuList",treeMenu);
        return "/system/menu/add";
    }


    @Log(title = "添加权限菜单",operateType = OperateType.INSERT)
    @ApiOperation(value = "权限添加")
    @PreAuthorize("hasAuthority('sys:menu:add')")
    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(SysMenu menu){

        if(!sysMenuService.checkMenuData(menu)){
            ResponseVo.error("新增失败,该菜单已经存在");
        }
        LoginUser loginUser = UserUtil.getLoginUser();
        menu.setCreatedBy(loginUser.getUser().getAdminUserId());
        menu.setUpdatedBy(loginUser.getUser().getAdminUserId());

        return ajaxReturn(sysMenuService.insertMenu(menu));
    }

    @PreAuthorize("hasAuthority('sys:menu:edit')")
    @GetMapping("/edit/{menuId}")
    public String edit(@PathVariable Long menuId,Model model){

        SysMenu info = sysMenuService.selectMenuById(menuId);
        List<SysMenu> treeMenu = getAllMenuList();


        model.addAttribute("menuList",treeMenu);
        model.addAttribute("info",info);
        return "/system/menu/edit";
    }

    @Log(title = "编辑权限菜单",operateType = OperateType.UPDATE)
    @ApiOperation(value = "权限编辑")
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    @PostMapping("/edit")
    @ResponseBody
    public ResponseVo edit(SysMenu menu){

        if(sysMenuService.checkMenuData(menu).equals(0)){
            ResponseVo.error("编辑失败,该菜单已经存在");
        }
        LoginUser loginUser = UserUtil.getLoginUser();
        menu.setUpdatedBy(loginUser.getUser().getAdminUserId());

        return ajaxReturn(sysMenuService.updateMenu(menu));
    }

    @ApiOperation(value = "权限列表")
    @PreAuthorize("hasAuthority('sys:menu:query')")
    @GetMapping("/list")
    @ResponseBody
    public TableResponseVo list(SysMenu menu){

        // TODO redis cache

        List<SysMenu> list = sysMenuService.selectAllMenu(menu);
        return TableResponseVo.setReturnResult(list,list.size(),0);
    }

    @Log(title = "权限菜单状态更新",operateType = OperateType.UPDATE)
    @ApiOperation(value = "权限状态更新")
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    @PostMapping("/changeStatus")
    @ResponseBody
    public ResponseVo changeStatus(@RequestParam("id") Long menuId,@RequestParam("status") int status){
        LoginUser loginUser = UserUtil.getLoginUser();

        SysMenu menu = new SysMenu();
        menu.setMenuId(menuId);
        menu.setStatus(status);
        menu.setUpdatedBy(loginUser.getUser().getAdminUserId());
        return ajaxReturn(sysMenuService.updateMenu(menu));
    }

    @Log(title = "权限菜单显示更新",operateType = OperateType.UPDATE)
    @ApiOperation(value = "菜单显示更新")
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    @PostMapping("/changeView")
    @ResponseBody
    public ResponseVo changeView(@RequestParam("id") Long menuId,@RequestParam("isView") int isView){
        LoginUser loginUser = UserUtil.getLoginUser();

        SysMenu menu = new SysMenu();
        menu.setMenuId(menuId);
        menu.setIsView(isView);
        menu.setUpdatedBy(loginUser.getUser().getAdminUserId());
        return ajaxReturn(sysMenuService.updateMenu(menu));
    }

    @Log(title = "权限菜单排序更新",operateType = OperateType.UPDATE)
    @ApiOperation(value = "菜单排序更新")
    @PreAuthorize("hasAuthority('sys:menu:edit')")
    @PostMapping("/changeSort")
    @ResponseBody
    public ResponseVo changeSort(@RequestParam("id") Long menuId,@RequestParam("sort") int sort){
        LoginUser loginUser = UserUtil.getLoginUser();

        SysMenu menu = new SysMenu();
        menu.setMenuId(menuId);
        menu.setSort(sort);
        menu.setUpdatedBy(loginUser.getUser().getAdminUserId());
        return ajaxReturn(sysMenuService.updateMenu(menu));
    }

    @Log(title = "权限菜单删除",operateType = OperateType.DELETE)
    @ApiOperation(value = "菜单删除")
    @PreAuthorize("hasAuthority('sys:menu:delete')")
    @PostMapping("/delete")
    @ResponseBody
    public ResponseVo deleteMenu(@RequestParam("id") Long menuId){
        LoginUser loginUser = UserUtil.getLoginUser();

        SysMenu menu = new SysMenu();
        menu.setMenuId(menuId);
        menu.setDeletedFlag(1);
        menu.setStatus(0);
        menu.setDeletedBy(loginUser.getUser().getAdminUserId());

        ResponseVo result =ajaxReturn(sysMenuService.removeMenu(menu));
        reloadUserAuthority();
        return result;
    }

    @ApiOperation(value = "当前登录用户权限")
    @GetMapping("/getPerms")
    @ResponseBody
    public ResponseVo permList(){
        LoginUser loginUser = UserUtil.getLoginUser();
        List<SysMenu> menuList = loginUser.getPermissions();

        Set<String> perms = menuList.stream().filter(m -> StringUtil.isNotEmpty(m.getPerms()))
                .map(SysMenu::getPerms) .collect(Collectors.toSet());

        return ResponseVo.success(perms);
    }

    @ApiOperation(value = "当前登录用户菜单栏")
    @GetMapping("/current")
    @ResponseBody
    public List<SysMenu> currentMenuList(){
        LoginUser loginUser = UserUtil.getLoginUser();
        List<SysMenu> menuList = loginUser.getPermissions();

        List<SysMenu> permissions = menuList.stream().filter(m -> (m.getMenuType().equals(1) && m.getIsView().equals(1)))
                .collect(Collectors.toList());

        // 左侧导航栏数据生成
        List<SysMenu> navMenuList = permissions.stream().filter(p -> p.getParentId().equals(0L)).collect(Collectors.toList());
        navMenuList.parallelStream().forEach(p -> {
            setChild(p, permissions);
        });

        return navMenuList;
    }

    /**
     * 加载角色菜单树，角色为空时为新增场合
     * */
    @GetMapping("/treeData")
    @ResponseBody
    public List<ZtreeVo> treeData(SysRole role) {
        LoginUser loginUser = UserUtil.getLoginUser();
        return sysMenuService.treeData(role, loginUser.getUser().getAdminUserId());
    }

    /**
     * 获取全部菜单
     * */
    private List<SysMenu> getAllMenuList(){
        SysMenu m = new SysMenu();
        m.setStatus(1);
        List<SysMenu> list = sysMenuService.selectAllMenu(m);

        return makeTree(list, 0L,0);
    }

    /**
     * 设置菜单子元素
     */
    private void setChild(SysMenu m, List<SysMenu> permissions) {
        List<SysMenu> child = permissions.parallelStream().filter(a -> a.getParentId().equals(m.getMenuId())).collect(Collectors.toList());
        m.setChildren(child);
        if (!CollectionUtils.isEmpty(child)) {
            child.parallelStream().forEach(c -> {
                setChild(c, permissions);
            });
        }
    }


}

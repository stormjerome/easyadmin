package com.zhxin.admin.controller.system;

import com.zhxin.common.web.ZtreeVo;
import com.zhxin.core.security.LoginUser;
import com.zhxin.core.security.util.UserUtil;
import com.zhxin.core.web.controller.BaseController;
import com.zhxin.core.web.model.ResponseVo;
import com.zhxin.core.web.model.TableResponseVo;
import com.zhxin.logic.system.model.SysDept;
import com.zhxin.logic.system.service.ISysDeptService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName DeptController
 * @Description //部门接口类
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/11 0011 下午 5:00
 **/
@Controller
@RequestMapping("/dept")
public class DeptController extends BaseController {

    @Autowired
    ISysDeptService sysDeptService;

    @GetMapping("/index")
    public String index(){
        return "/system/dept/index";
    }

    @GetMapping("/add/{parentId}")
    public String add(@PathVariable Long parentId,Model model){
        SysDept deptInfo = sysDeptService.selectDeptById(parentId);
        model.addAttribute("info",deptInfo);
        return "/system/dept/add";
    }

    @GetMapping("/edit/{deptId}")
    public String edit(@PathVariable Long deptId, ModelMap modelMap){
        SysDept deptInfo = sysDeptService.selectDeptById(deptId);
        modelMap.put("info",deptInfo);
        return "/system/dept/edit";
    }

    @GetMapping("/tree/{deptId}")
    public String tree(@PathVariable Long deptId,Model model){
        SysDept deptInfo = sysDeptService.selectDeptById(deptId);
        model.addAttribute("info",deptInfo);
        return "/system/dept/tree";
    }

    @ApiOperation(value = "部门列表")
    @PreAuthorize("hasAuthority('sys:dept:query')")
    @GetMapping("/list")
    @ResponseBody
    public TableResponseVo list(SysDept dept){
        List<SysDept> list = sysDeptService.selectDept(dept);
        return TableResponseVo.setReturnResult(list,list.size(),0);
    }

    @ApiOperation(value = "部门树列表")
    @PreAuthorize("hasAuthority('sys:dept:query')")
    @GetMapping("/treeData")
    @ResponseBody
    public List<ZtreeVo> treeData(){
        return sysDeptService.selectDeptTree(new SysDept());
    }

    @ApiOperation(value = "部门添加")
    @PreAuthorize("hasAuthority('sys:dept:add')")
    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(SysDept dept){

        if(sysDeptService.checkDeptData(dept)){
            ResponseVo.error("新增失败,该部门已经存在");
        }
        LoginUser loginUser = UserUtil.getLoginUser();
        dept.setCreatedBy(loginUser.getUser().getAdminUserId());
        dept.setUpdatedBy(loginUser.getUser().getAdminUserId());

        return ajaxReturn(sysDeptService.insertDept(dept));
    }

    @ApiOperation(value = "部门编辑")
    @PreAuthorize("hasAuthority('sys:dept:edit')")
    @PostMapping("/edit")
    @ResponseBody
    public ResponseVo edit(SysDept dept){

        if(sysDeptService.checkDeptData(dept)){
            ResponseVo.error("编辑失败,该部门已经存在");
        }
        LoginUser loginUser = UserUtil.getLoginUser();
        dept.setUpdatedBy(loginUser.getUser().getAdminUserId());

        return ajaxReturn(sysDeptService.updateDept(dept));
    }

    @ApiOperation(value = "部门状态更新")
    @PreAuthorize("hasAuthority('sys:dept:edit')")
    @PostMapping("/changeStatus")
    @ResponseBody
    public ResponseVo changeStatus(@RequestParam("id") Long deptId, @RequestParam("status") int status){
        LoginUser loginUser = UserUtil.getLoginUser();

        SysDept dept = new SysDept();
        dept.setDeptId(deptId);
        dept.setStatus(status);
        dept.setUpdatedBy(loginUser.getUser().getAdminUserId());
        return ajaxReturn(sysDeptService.updateDept(dept));
    }

    @ApiOperation(value = "部门排序更新")
    @PreAuthorize("hasAuthority('sys:dept:edit')")
    @PostMapping("/changeSort")
    @ResponseBody
    public ResponseVo changeSort(@RequestParam("id") Long deptId,@RequestParam("sort") int sort){
        LoginUser loginUser = UserUtil.getLoginUser();

        SysDept dept = new SysDept();
        dept.setDeptId(deptId);
        dept.setSort(sort);
        dept.setUpdatedBy(loginUser.getUser().getAdminUserId());
        return ajaxReturn(sysDeptService.updateDept(dept));
    }

    @ApiOperation(value = "部门删除")
    @PreAuthorize("hasAuthority('sys:dept:delete')")
    @PostMapping("/delete")
    @ResponseBody
    public ResponseVo deleteDept(@RequestParam("id") Long deptId){
        LoginUser loginUser = UserUtil.getLoginUser();

        if(!sysDeptService.removeCheck(deptId)){
            return ResponseVo.error("存在子部门或者存在部门成员数据！");
        }

        SysDept dept = new SysDept();
        dept.setDeptId(deptId);
        dept.setDeletedFlag(1);
        dept.setStatus(0);
        dept.setDeletedBy(loginUser.getUser().getAdminUserId());
        return ajaxReturn(sysDeptService.removeDept(dept));
    }
}

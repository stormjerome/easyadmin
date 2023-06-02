package com.zhxin.core.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhxin.common.constant.EasyConstants;
import com.zhxin.common.page.PageDomain;
import com.zhxin.common.page.TableDataInfo;
import com.zhxin.common.page.TableSupport;
import com.zhxin.common.utils.ServletUtil;
import com.zhxin.common.utils.SpringUtil;
import com.zhxin.common.utils.StringUtil;
import com.zhxin.core.security.LoginUser;
import com.zhxin.core.security.service.PermissionService;
import com.zhxin.core.security.util.UserUtil;
import com.zhxin.core.web.model.ResponseVo;
import com.zhxin.core.web.model.TableResponseVo;
import com.zhxin.logic.system.model.SysMenu;
import com.zhxin.logic.system.service.ISysAdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName BaseController
 * @Description // controller基类
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/14 0014 下午 4:15
 **/

public class BaseController {

    /**
     * 更新在线用户
     * */
    protected void reloadUserAuthority() {
        HttpSession session = ServletUtil.getSession();
        PermissionService permissionService = SpringUtil.getBean(PermissionService.class);
        ISysAdminUserService userService = SpringUtil.getBean(ISysAdminUserService.class);
        SecurityContext securityContext = (SecurityContext) session.getAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY);
        Authentication authentication = securityContext.getAuthentication();
        LoginUser loginUser = UserUtil.getLoginUser();

        loginUser.setPermissions(permissionService.getMenuPermission(loginUser.getUser()));
        loginUser.setUser(userService.getUserByName(loginUser.getUsername()));
        UsernamePasswordAuthenticationToken result = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
        result.setDetails(authentication.getDetails());
        securityContext.setAuthentication(result);

    }

        /**
         * 菜单树
         * */
    protected List<SysMenu> makeTree(List<SysMenu> menuList, Long parentId, Integer level){
        List<SysMenu> treeMenu = new ArrayList<>();
        for(SysMenu m:menuList){
            if(m.getParentId().equals(parentId)){
                m.setLevel(level+1);
                m.setLeftHtml(StringUtil.repeatString(EasyConstants.LEFT_HTML,level));
                m.setParentId(parentId);
                m.setMenuId(m.getMenuId());
                m.setMenuName(m.getMenuName());
                treeMenu.add(m);
                treeMenu.addAll(makeTree(menuList, m.getMenuId(),level +1));
            }
        }
        return treeMenu;
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = StringUtil.isNotNull(pageDomain.getPageNum()) ?
                pageDomain.getPageNum(): EasyConstants.DEFAULT_PAGE_NUM;
        Integer pageSize = StringUtil.isNotNull(pageDomain.getPageSize()) ?
                pageDomain.getPageSize():EasyConstants.DEFAULT_PAGE_SIZE;
        String orderBy = pageDomain.getOrderBy();
        PageHelper.startPage(pageNum, pageSize, orderBy);
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.OK.value());
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    protected TableResponseVo getLayTable(List<?> list) {

        return TableResponseVo.setReturnResult(list,list.size(),ServletUtil.getParameterToInt("limit"));
    }

    /**
     * 响应返回结果
     *
     */
    protected ResponseVo ajaxReturn(int rows) {
        return rows > 0 ? ResponseVo.success() : ResponseVo.error();
    }

}

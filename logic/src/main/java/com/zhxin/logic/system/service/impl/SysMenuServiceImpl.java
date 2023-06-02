package com.zhxin.logic.system.service.impl;

import com.zhxin.common.constant.EasyConstants;
import com.zhxin.common.utils.StringUtil;
import com.zhxin.common.web.ZtreeVo;
import com.zhxin.logic.system.mapper.SysMenuMapper;
import com.zhxin.logic.system.model.SysMenu;
import com.zhxin.logic.system.model.SysRole;
import com.zhxin.logic.system.service.ISysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @ClassName SysMenuServiceImpl
 * @Description //SysMenuServiceImpl
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/10/23 0023 上午 10:37
 **/
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    @Autowired
    private SysMenuMapper menuMapper;

    @Override
    public int insertMenu(SysMenu menu){
        return menuMapper.insertMenu(menu);
    }

    @Override
    public Set<String> selectMenuPermsByUserId(Long userId){
        List<String> perms = menuMapper.selectMenuPermsByUserId(userId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms) {
            if (StringUtil.isNotEmpty(perm)) {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }

    @Override
    public List<SysMenu> selectAllMenu(SysMenu menu){
        return menuMapper.selectMenuList(menu);
    }

    @Override
    public List<SysMenu> selectMenuAll(Long userId) {
        List<SysMenu> menuList;
        if (userId.equals(1L)){
            menuList = menuMapper.selectAllMenuForAdmin();
        }else{
            menuList = menuMapper.selectMenuByUserId(userId);
        }
        return menuList;
    }
    @Override
    public List<SysMenu> selectAllMenuForAdmin(){
        return menuMapper.selectAllMenuForAdmin();
    }

    @Override
    public List<SysMenu> selectMenuByUserId(Long userId){
        return menuMapper.selectMenuByUserId(userId);
    }

    @Override
    public int updateMenu(SysMenu menu){return menuMapper.updateMenu(menu);}

    @Override
    public int removeMenu(SysMenu menu){
        return menuMapper.softDeleteMenu(menu);
    }

    /**
     * 查重
     * */
    @Override
    public Boolean checkMenuData(SysMenu menu){
        SysMenu menuInfo =  menuMapper.checkMenuData(menu.getMenuName(),menu.getParentId());
        return !StringUtil.isNotNull(menuInfo);
    }

    @Override
    public SysMenu selectMenuById(Long menuId){
       return menuMapper.selectMenuById(menuId);
    }

    @Override
    public List<ZtreeVo> treeData(SysRole role, Long userId){
        Long roleId = role.getRoleId();
        List<ZtreeVo> ztrees = new ArrayList<>();
        List<SysMenu> menuList = selectMenuAll(userId);

        if (StringUtil.isNotNull(roleId)) {

            List<String> roleMenuList = menuMapper.selectMenuByRoleId(roleId);
            ztrees = initZtree(menuList, roleMenuList, true);
        } else {
            ztrees = initZtree(menuList, null,true);
        }
        return ztrees;
    }

    /**
     * 菜单树数据设置
     * */
    private List<ZtreeVo> initZtree(List<SysMenu> menuList,List<String> roleMenuList,boolean permsFlag){
        List<ZtreeVo> zList = new ArrayList<>();
        boolean setCheckFlag = StringUtil.isNotEmpty(roleMenuList);
        if(StringUtil.isNotEmpty(menuList)){
            menuList.stream().filter(d-> EasyConstants.COMMON_STATUS_OPEN.equals(d.getStatus())).forEach(
                    d->{
                        ZtreeVo ztree = new ZtreeVo();
                        ztree.setId(d.getMenuId());
                        ztree.setpId(d.getParentId());
                        ztree.setName(FormatMenuName(d,permsFlag));
                        ztree.setTitle(d.getMenuName());
                        if(setCheckFlag){
                            ztree.setChecked(roleMenuList.contains(d.getMenuId()+d.getPerms()));
                        }
                        zList.add(ztree);
                    });
        }
        return zList;
    }


    private String FormatMenuName(SysMenu menu, boolean permsFlag) {
        StringBuilder sb = new StringBuilder();
        sb.append(menu.getMenuName());
        if (permsFlag) {
            sb.append("<font color=\"#888\">&nbsp;&nbsp;&nbsp;").append(menu.getPerms()).append("</font>");
        }
        return sb.toString();
    }

}

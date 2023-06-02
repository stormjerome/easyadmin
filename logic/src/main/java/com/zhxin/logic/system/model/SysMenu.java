package com.zhxin.logic.system.model;

import java.util.List;

public class SysMenu extends BaseModel{

    private static final long serialVersionUID = -8051600539409170462L;

    private Long menuId;
    private String menuName;
    private Long parentId;
    private String menuUrl;
    private String perms;
    private String icon;
    private Integer sort;
    private Integer isView;
    private Integer isLink;
    private Integer status;
    private Integer menuType;
    private Integer deletedFlag;

    private List<SysMenu> children;
    private Integer level;
    private String leftHtml;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getLeftHtml() {
        return leftHtml;
    }

    public void setLeftHtml(String leftHtml) {
        this.leftHtml = leftHtml;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getIsView() {
        return isView;
    }

    public void setIsView(Integer isView) {
        this.isView = isView;
    }

    public Integer getIsLink() {
        return isLink;
    }

    public void setIsLink(Integer isLink) {
        this.isLink = isLink;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getMenuType() {
        return menuType;
    }

    public void setMenuType(Integer menuType) {
        this.menuType = menuType;
    }
    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }
    public List<SysMenu> getChildren(){
        return children;
    }

    public Integer getDeletedFlag() {
        return deletedFlag;
    }

    public void setDeletedFlag(Integer deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

    public String toString(){
        return getLeftHtml()+""+getMenuName();
    }
}
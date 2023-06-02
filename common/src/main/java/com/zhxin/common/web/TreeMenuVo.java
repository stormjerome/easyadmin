package com.zhxin.common.web;

/**
 * @ClassName TreeMenuVo
 * @Description //TODO
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/6 0006 下午 5:17
 **/
public class TreeMenuVo {
    private Long parentId;
    private Integer level;
    private String leftHtml;
    private String menuName;
    private Long menuId;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

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

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String toString(){
        return getLeftHtml()+""+getMenuName();
    }
}

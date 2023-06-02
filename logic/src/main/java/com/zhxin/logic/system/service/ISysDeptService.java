package com.zhxin.logic.system.service;

import com.zhxin.common.web.ZtreeVo;
import com.zhxin.logic.system.model.SysDept;

import java.util.List;

public interface ISysDeptService {

    int insertDept(SysDept dept);
    List<SysDept> selectDept(SysDept dept);
    SysDept selectDeptById(Long deptId);
    List<ZtreeVo> selectDeptTree(SysDept dept);
    Boolean checkDeptData(SysDept dept);

    int updateDept(SysDept dept);
    Boolean removeCheck(Long deptId);
    int selectChildCount(Long deptId);
    int selectMemberCount(Long deptId);
    int removeDept(SysDept dept);

}

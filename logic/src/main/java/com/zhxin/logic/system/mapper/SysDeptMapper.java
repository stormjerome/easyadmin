package com.zhxin.logic.system.mapper;

import com.zhxin.logic.system.model.SysDept;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysDeptMapper {
    int insertDept(SysDept dept);

    SysDept checkDeptData(@Param("deptName") String deptName, @Param("parentId") Long parentId);
    List<SysDept> selectDeptList(SysDept dept);
    int updateDept(SysDept dept);
    int selectChildCount(Long deptId);
    int selectMemberCount(Long deptId);
    int softDeleteDept(SysDept dept);
    SysDept selectDeptById(Long deptId);
}
package com.zhxin.logic.system.service.impl;

import com.zhxin.common.constant.EasyConstants;
import com.zhxin.common.utils.StringUtil;
import com.zhxin.common.web.ZtreeVo;
import com.zhxin.logic.system.mapper.SysDeptMapper;
import com.zhxin.logic.system.model.SysDept;
import com.zhxin.logic.system.service.ISysDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SysDeptServiceImpl
 * @Description //SysDeptServiceImpl
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/11 0011 下午 5:04
 **/
@Service
public class SysDeptServiceImpl implements ISysDeptService {

    @Autowired
    SysDeptMapper sysDeptMapper;

    @Override
    public int insertDept(SysDept dept){
        return sysDeptMapper.insertDept(dept);
    }

    @Override
    public List<SysDept> selectDept(SysDept dept){
        return sysDeptMapper.selectDeptList(dept);
    }

    @Override
    public SysDept selectDeptById(Long deptId) {
        return sysDeptMapper.selectDeptById(deptId);
    }

    @Override
    public List<ZtreeVo> selectDeptTree(SysDept dept) {
        List<SysDept> deptList = sysDeptMapper.selectDeptList(dept);
        return initZtree(deptList);
    }

    private List<ZtreeVo> initZtree(List<SysDept> deptList){
        List<ZtreeVo> zList = new ArrayList<>();

        if(StringUtil.isNotEmpty(deptList)){
            deptList.stream().filter(d-> EasyConstants.COMMON_STATUS_OPEN.equals(d.getStatus())).forEach(
                    d->{
                        ZtreeVo ztree = new ZtreeVo();
                        ztree.setId(d.getDeptId());
                        ztree.setpId(d.getParentId());
                        ztree.setName(d.getDeptName());
                        ztree.setTitle(d.getDeptName());
                        ztree.setLevelPath(d.getLevelPath());
                        zList.add(ztree);
                    });
        }
        return zList;
    }

    @Override
    public Boolean checkDeptData(SysDept dept){
        SysDept deptInfo = sysDeptMapper.checkDeptData(dept.getDeptName(),dept.getParentId());
        return !StringUtil.isNotNull(deptInfo);
    }

    @Override
    public int updateDept(SysDept dept){
        return sysDeptMapper.updateDept(dept);
    }

    @Override
    public Boolean removeCheck(Long deptId){
        return selectChildCount(deptId) == 0 && selectMemberCount(deptId) == 0;
    }


    @Override
    public int selectChildCount(Long deptId){

        return sysDeptMapper.selectChildCount(deptId);
    }

    @Override
    public int selectMemberCount(Long deptId){
        return sysDeptMapper.selectMemberCount(deptId);
    }

    @Override
    public int removeDept(SysDept dept){
        return sysDeptMapper.softDeleteDept(dept);
    }
}

package com.zhxin.logic.system.service.impl;

import com.zhxin.logic.system.mapper.SysAdminLoginLogMapper;
import com.zhxin.logic.system.mapper.SysAdminUserMapper;
import com.zhxin.logic.system.model.SysAdminLoginLog;
import com.zhxin.logic.system.service.ISysAdminLoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @ClassName SysAdminLoginLogServiceImpl
 * @Description //TODO
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/23 0023 下午 4:22
 **/
@Service
public class SysAdminLoginLogServiceImpl implements ISysAdminLoginLogService {

    @Autowired
    SysAdminLoginLogMapper sysAdminLoginLogMapper;

    @Autowired
    SysAdminUserMapper sysAdminUserMapper;

    @Override
    @Transactional
    public int insertLog(SysAdminLoginLog log){
        sysAdminUserMapper.updateUser(log.getUser());
        return sysAdminLoginLogMapper.insertLog(log);
    }

    public List<SysAdminLoginLog> selectLog(SysAdminLoginLog log){
        return sysAdminLoginLogMapper.selectLog(log);
    }
}

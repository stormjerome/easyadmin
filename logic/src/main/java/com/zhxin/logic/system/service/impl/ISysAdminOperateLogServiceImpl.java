package com.zhxin.logic.system.service.impl;

import com.zhxin.logic.system.mapper.SysAdminLoginLogMapper;
import com.zhxin.logic.system.mapper.SysAdminOperateLogMapper;
import com.zhxin.logic.system.model.SysAdminOperateLog;
import com.zhxin.logic.system.service.ISysAdminOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ISysAdminOperateLogServiceImpl
 * @Description //ISysAdminOperateLogServiceImpl
 * @Author singleZhang
 * @Email 405780096@qq.com
 * @Date 2020/11/24 0024 下午 3:09
 **/
@Service
public class ISysAdminOperateLogServiceImpl implements ISysAdminOperateLogService {

    @Autowired
    SysAdminOperateLogMapper sysAdminOperateLogMapper;

    @Override
    public int insertLog(SysAdminOperateLog log){
        return sysAdminOperateLogMapper.insertLog(log);
    }

    @Override
    public List<SysAdminOperateLog> selectLog(SysAdminOperateLog log){
        return sysAdminOperateLogMapper.selectLog(log);
    }
}

package com.zhxin.logic.system.service;

import com.zhxin.logic.system.model.SysAdminOperateLog;

import java.util.List;

public interface ISysAdminOperateLogService {
    int insertLog(SysAdminOperateLog log);
    List<SysAdminOperateLog> selectLog(SysAdminOperateLog log);
}

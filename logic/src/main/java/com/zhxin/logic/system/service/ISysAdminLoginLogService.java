package com.zhxin.logic.system.service;

import com.zhxin.logic.system.model.SysAdminLoginLog;

import java.util.List;

public interface ISysAdminLoginLogService {
    int insertLog(SysAdminLoginLog log);
    List<SysAdminLoginLog> selectLog(SysAdminLoginLog log);
}

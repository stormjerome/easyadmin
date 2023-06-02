package com.zhxin.logic.system.mapper;

import com.zhxin.logic.system.model.SysAdminOperateLog;

import java.util.List;

public interface SysAdminOperateLogMapper {
    int insertLog(SysAdminOperateLog log);
    List<SysAdminOperateLog> selectLog(SysAdminOperateLog log);
}

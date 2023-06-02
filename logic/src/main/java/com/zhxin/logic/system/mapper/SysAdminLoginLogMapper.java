package com.zhxin.logic.system.mapper;


import com.zhxin.logic.system.model.SysAdminLoginLog;

import java.util.List;

public interface SysAdminLoginLogMapper {
    int insertLog(SysAdminLoginLog log);
    List<SysAdminLoginLog> selectLog(SysAdminLoginLog log);
}

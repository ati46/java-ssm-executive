package com.mapper;

import com.po.SysMaintenance;

public interface SysMaintenanceMapper {
    int deleteByPrimaryKey(Integer maintenance_id);

    int insert(SysMaintenance record);

    int insertSelective(SysMaintenance record);

    SysMaintenance selectByPrimaryKey(Integer maintenance_id);

    int updateByPrimaryKeySelective(SysMaintenance record);

    int updateByPrimaryKey(SysMaintenance record);
}
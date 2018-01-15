package com.mapper.custom;

import org.apache.ibatis.annotations.Param;

import com.po.custom.SysMaintenanceCustom;

public interface SysMaintenanceMapperCustom {
	public SysMaintenanceCustom findMaintenanceByName(@Param("maintenance_name")String maintenance_name) throws Exception;
}

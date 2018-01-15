package com.mapper.custom;

import org.apache.ibatis.annotations.Param;

import com.po.custom.SysMacCustom;

public interface SysMacMapperCustom {
	public SysMacCustom findSysMacByMac(@Param("mac_code")String mac_code) throws Exception;
}

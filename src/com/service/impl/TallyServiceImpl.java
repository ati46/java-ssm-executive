package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.mapper.custom.SysMacMapperCustom;
import com.mapper.custom.SysMaintenanceMapperCustom;
import com.po.custom.SysMacCustom;
import com.po.custom.SysMaintenanceCustom;
import com.service.TallyService;

public class TallyServiceImpl implements TallyService{
	
	@Autowired
	private SysMaintenanceMapperCustom sysMaintenanceMapperCustom;
	
	
	@Autowired
	private SysMacMapperCustom sysMacMapperCustom;

	@Override
	public SysMaintenanceCustom findMaintenanceByName(String maintenance_name) throws Exception {
		SysMaintenanceCustom sysMaintenanceCustom = sysMaintenanceMapperCustom.findMaintenanceByName(maintenance_name);
		return sysMaintenanceCustom;
	}

	@Override
	public List<SysCommodityInfoResponseVo> getCommodityInfoList() throws Exception {
		List<SysCommodityInfoResponseVo> list = sysCommodityMapperCustom.getCommodityInfoList();
		return list;
	}

	@Override
	public SysMacCustom findSysMacByMac(String mac_code) throws Exception {
		SysMacCustom sysMacCustom = sysMacMapperCustom.findSysMacByMac(mac_code);
		return sysMacCustom;
	}

}

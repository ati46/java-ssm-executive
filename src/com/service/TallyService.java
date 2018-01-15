package com.service;

import java.util.List;

import com.po.custom.SysCommodityShopCustom;
import com.po.custom.SysInventoryCustom;
import com.po.custom.SysLogCustom;
import com.po.custom.SysMacCustom;
import com.po.custom.SysMaintenanceCustom;
import com.po.vo.SysCommodityInfoResponseVo;

public interface TallyService {
	public SysMaintenanceCustom findMaintenanceByName(String maintenance_name) throws Exception;
	
	public List<SysCommodityInfoResponseVo> getCommodityInfoList() throws Exception;
	
	
	public SysMacCustom findSysMacByMac(String mac_code) throws Exception;
}

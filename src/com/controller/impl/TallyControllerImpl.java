package com.controller.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Named;
import javax.xml.rpc.ServiceException;

import org.springframework.beans.factory.annotation.Autowired;

import com.controller.TallyController;
import com.po.custom.ResponseMessageCustom;
import com.po.custom.SysCommodityShopCustom;
import com.po.custom.SysInventoryCustom;
import com.po.custom.SysLogCustom;
import com.po.vo.SysCommodityInfoResponseVo;
import com.po.vo.SysCommodityShopQueryVo;
import com.po.vo.SysInventoryQueryVo;
import com.service.TallyService;

@Named("tallyController")
public class TallyControllerImpl implements TallyController{

	@Autowired
	private TallyService tallyService;
	
	ResponseMessageCustom responseMessageCustom = new ResponseMessageCustom();
	
	@Override
	public List<SysCommodityInfoResponseVo> getCommodityList() throws Exception {
//		JSONObject jsonObject = JSONObject.fromObject(object);
//		String maintenance_name = jsonObject.getString("maintenance_name");
//		SysMaintenanceCustom sysMaintenanceCustom = tallyService.findMaintenanceByName(maintenance_name);
//		if(sysMaintenanceCustom == null)
//			throw new ServiceException("该用户不存在，请输入正确的名字！");
		List<SysCommodityInfoResponseVo> list = tallyService.getCommodityInfoList();
		return list;
	}
}

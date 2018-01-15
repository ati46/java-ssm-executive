package com.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.po.vo.SysCommodityInfoResponseVo;

/**
 * 理货控制器
 * @author Ati
 *
 */
@Path("/tally")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML}) //输出类型，可选json、xml(默认)
public interface TallyController {
	
	//返回商品信息
	@POST
	@Path("/getCL")
	@Consumes(MediaType.APPLICATION_JSON)
	public List<SysCommodityInfoResponseVo> getCommodityList() throws Exception;
	
}

package com.po.vo;

import com.po.SysUser;
import com.po.custom.SysUserCustom;

/**
 * 用户包装对象
 * @author Ati
 *
 */
public class SysUserQueryVo {
	private SysUser sysUser;
	private SysUserCustom sysUserCustom;
	
	public SysUser getSysUser() {
		return sysUser;
	}
	public void setSysUser(SysUser sysUser) {
		this.sysUser = sysUser;
	}
	public SysUserCustom getSysUserCustom() {
		return sysUserCustom;
	}
	public void setSysUserCustom(SysUserCustom sysUserCustom) {
		this.sysUserCustom = sysUserCustom;
	}
	
}

package com.po.custom;

import java.util.List;

public class WXPayCustom {
	private String open_id;
	private String amount;
	private List<String> rfid;
	private int shop_id;
	public String getOpen_id() {
		return open_id;
	}
	public void setOpen_id(String open_id) {
		this.open_id = open_id;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public List<String> getRfid() {
		return rfid;
	}
	public void setRfid(List<String> rfid) {
		this.rfid = rfid;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
}

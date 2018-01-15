package com.po.custom;

import java.math.BigDecimal;

public class GZHPayCustom {
	private String trade_code;
	private BigDecimal price;
	private BigDecimal account_balance;
	public String getTrade_code() {
		return trade_code;
	}
	public void setTrade_code(String trade_code) {
		this.trade_code = trade_code;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getAccount_balance() {
		return account_balance;
	}
	public void setAccount_balance(BigDecimal account_balance) {
		this.account_balance = account_balance;
	}
	
}

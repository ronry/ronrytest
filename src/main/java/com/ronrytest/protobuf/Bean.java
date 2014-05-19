package com.ronrytest.protobuf;

import java.util.Date;
import java.util.List;

public class Bean extends AbstractBean {

	private String email;
	private String mobile;
	private String hashedPassword;
	private Date lastLoginTime;
	private List<String> zhiwu;
	private int[] gonghao;
	private java.util.Map<String, String> shouru;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getHashedPassword() {
		return hashedPassword;
	}

	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public List<String> getZhiwu() {
		return zhiwu;
	}

	public void setZhiwu(List<String> zhiwu) {
		this.zhiwu = zhiwu;
	}

	public int[] getGonghao() {
		return gonghao;
	}

	public void setGonghao(int[] gonghao) {
		this.gonghao = gonghao;
	}

	public java.util.Map<String, String> getShouru() {
		return shouru;
	}

	public void setShouru(java.util.Map<String, String> shouru) {
		this.shouru = shouru;
	}

}

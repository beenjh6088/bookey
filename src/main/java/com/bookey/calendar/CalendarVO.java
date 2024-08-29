package com.bookey.calendar;

import java.util.Date;

public class CalendarVO {

	private String dateID;
	private Date dt;
	private int dayNum;
	private String dayCha;
	private String yyyy;
	private String mm;
	private String dd;
	private String isDayOff;
	private String remark;
	
	public String getDateID() {
		return dateID;
	}
	public void setDateID(String dateID) {
		this.dateID = dateID;
	}
	public Date getDt() {
		return dt;
	}
	public void setDt(Date dt) {
		this.dt = dt;
	}
	public int getDayNum() {
		return dayNum;
	}
	public void setDayNum(int dayNum) {
		this.dayNum = dayNum;
	}
	public String getDayCha() {
		return dayCha;
	}
	public void setDayCha(String dayCha) {
		this.dayCha = dayCha;
	}
	public String getYyyy() {
		return yyyy;
	}
	public void setYyyy(String yyyy) {
		this.yyyy = yyyy;
	}
	public String getMm() {
		return mm;
	}
	public void setMm(String mm) {
		this.mm = mm;
	}
	public String getDd() {
		return dd;
	}
	public void setDd(String dd) {
		this.dd = dd;
	}
	public String getIsDayOff() {
		return isDayOff;
	}
	public void setIsDayOff(String isDayOff) {
		this.isDayOff = isDayOff;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

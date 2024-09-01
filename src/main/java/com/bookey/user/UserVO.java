package com.bookey.user;

import java.util.Date;

public class UserVO {

	private String userID;
	private String userPW;
	private String email;
	private String name;
	private String address;
	private String isOpenToMarketing;
	private Date birthday;
	private String gender;
	private String rank;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserPW() {
		return userPW;
	}
	public void setUserPW(String userPW) {
		this.userPW = userPW;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIsOpenToMarketing() {
		return isOpenToMarketing;
	}
	public void setIsOpenToMarketing(String isOpenToMarketing) {
		this.isOpenToMarketing = isOpenToMarketing;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	
}

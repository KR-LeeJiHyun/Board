package com.board.web.entity;

import java.util.Date;

public class Member {
	private String name;
	private String nickname; 
	private String id;
	private String password; 
	private String confirmationPassword;
	private String email;
	private String address;
	private Date birthday;
	
	public Member() {
		this.name = null;
		this.nickname = null;
		this.id = null;
		this.password = null;
		this.confirmationPassword = null;
		this.email = null;
		this.address = null;
		this.birthday = null;
	}
	
	public Member(String name, String nickname, String id, String password, String confirmationPassword, String email,
			String address, Date birthday) {
		this.name = name;
		this.nickname = nickname;
		this.id = id;
		this.password = password;
		this.confirmationPassword = confirmationPassword;
		this.email = email;
		this.address = address;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getConfirmationPassword() {
		return confirmationPassword;
	}
	
	public void setConfirmationPassword(String confirmationPassword) {
		this.confirmationPassword = confirmationPassword;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}

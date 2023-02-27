package com.board.web.entity;

import java.util.Date;

public class Member {
	private String name;
	private String nickname; 
	private String id;
	private String email;
	private Date birthday;
	
	public Member() {
		this.name = null;
		this.nickname = null;
		this.id = null;
		this.email = null;
		this.birthday = null;
	}
	
	public Member(String name, String nickname, String id, String email, Date birthday) {
		this.name = name;
		this.nickname = nickname;
		this.id = id;
		this.email = email;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Date getBirthday() {
		return birthday;
	}
	
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	
}

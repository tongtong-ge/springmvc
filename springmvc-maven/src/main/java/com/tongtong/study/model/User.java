package com.tongtong.study.model;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;


public class User {
	private String name;
	private String sex;
	private String phone;
	private String account;
	private String password;

	public User() {
		
	}
	
	@NotEmpty(message="名字不能为空")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Size(min = 11, max = 11, message = "手机号码长度不正确")
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAccount() {
		return account;
	}
	
	public void setAccount(String account) {
		this.account = account;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public User(String name, String sex, String phone, String account, String password) {
		super();
		this.name = name;
		this.sex = sex;
		this.phone = phone;
		this.account = account;
		this.password = password;
	}
}

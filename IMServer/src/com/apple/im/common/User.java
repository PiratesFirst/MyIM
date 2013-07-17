package com.apple.im.common;

import java.io.Serializable;

/**包含用户操作信息，帐号，密码，
 * 头像，昵称，动态信息，
 * 性别，年龄，等级，用户时间*/
public class User implements Serializable{
	String operation;
	String password;
	String nick;
	String time;
	String trends;
	String sex;
	int age;
	int lev;
	int account;
	int avatar;
	
	/**获得操作类型,如：注册，登录等等*/
	public String getOperation(){
		return operation;
	}
	
	/**设置操作类型，如：注册，登录等等*/
	public void setOperation(String operation) {
		this.operation = operation;
	}
	/**获得帐号*/
	public int getAccount() {
		return account;
	}
	/**设置帐号*/
	public void setAccount(int account) {
		this.account = account;
	}
	/**获得密码*/
	public String getPassword() {
		return password;
	}
	/**设置密码*/
	public void setPassword(String password) {
		this.password = password;
	}
	/**获得昵称*/
	public String getNick() {
		return nick;
	}
	/**设置昵称*/
	public void setNick(String nick) {
		this.nick = nick;
	}
	/**获得头像*/
	public int getAvatar() {
		return avatar;
	}
	/**设置头像*/
	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}
	/**获得动态信息*/
	public String getTrends() {
		return trends;
	}
	/**设置动态信息*/
	public void setTrends(String trends) {
		this.trends = trends;
	}
	/**获得性别信息*/
	public String getSex() {
		return sex;
	}
	/**设置性别信息*/
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**获得年龄信息*/
	public int getAge() {
		return age;
	}
	/**设置年龄信息*/
	public void setAge(int age) {
		this.age = age;
	}
	/**获得等级信息*/
	public int getLev() {
		return lev;
	}
	/**设置等级信息*/
	public void setLev(int lev) {
		this.lev = lev;
	}
	/**获得时间*/
	public String getTime() {
		return time;
	}
	/**设置时间*/
	public void setTime(String time) {
		this.time = time;
	}
}

package com.apple.im.common;

import java.io.Serializable;

/**�����û�������Ϣ���ʺţ����룬
 * ͷ���ǳƣ���̬��Ϣ��
 * �Ա����䣬�ȼ����û�ʱ��*/
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
	
	/**��ò�������,�磺ע�ᣬ��¼�ȵ�*/
	public String getOperation(){
		return operation;
	}
	
	/**���ò������ͣ��磺ע�ᣬ��¼�ȵ�*/
	public void setOperation(String operation) {
		this.operation = operation;
	}
	/**����ʺ�*/
	public int getAccount() {
		return account;
	}
	/**�����ʺ�*/
	public void setAccount(int account) {
		this.account = account;
	}
	/**�������*/
	public String getPassword() {
		return password;
	}
	/**��������*/
	public void setPassword(String password) {
		this.password = password;
	}
	/**����ǳ�*/
	public String getNick() {
		return nick;
	}
	/**�����ǳ�*/
	public void setNick(String nick) {
		this.nick = nick;
	}
	/**���ͷ��*/
	public int getAvatar() {
		return avatar;
	}
	/**����ͷ��*/
	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}
	/**��ö�̬��Ϣ*/
	public String getTrends() {
		return trends;
	}
	/**���ö�̬��Ϣ*/
	public void setTrends(String trends) {
		this.trends = trends;
	}
	/**����Ա���Ϣ*/
	public String getSex() {
		return sex;
	}
	/**�����Ա���Ϣ*/
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**���������Ϣ*/
	public int getAge() {
		return age;
	}
	/**����������Ϣ*/
	public void setAge(int age) {
		this.age = age;
	}
	/**��õȼ���Ϣ*/
	public int getLev() {
		return lev;
	}
	/**���õȼ���Ϣ*/
	public void setLev(int lev) {
		this.lev = lev;
	}
	/**���ʱ��*/
	public String getTime() {
		return time;
	}
	/**����ʱ��*/
	public void setTime(String time) {
		this.time = time;
	}
}

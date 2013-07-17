package com.apple.im.view.buddy;

/**�����࣬�����ʺţ�ͷ�񣬶�̬���ǳƣ��Լ��Ƿ����ߵ���Ϣ*/
public class BuddyEntity {
	private int avatar;
	private int account;
	private String nick;
	private String trends;
	private int isOnline;
	
	public BuddyEntity(int avatar,int account,String nick,String trends,int isOnline){
		this.avatar=avatar;
		this.account=account;
		this.nick=nick;
		this.trends=trends;
		this.isOnline=isOnline;
	}
	
	/**���ͷ��*/
	public int getAvatar() {
		return avatar;
	}
	/**����ͷ��*/
	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}
	/**����ʺ�*/
	public int getAccount() {
		return account;
	}
	/**�����ʺ�*/
	public void setAccount(int account) {
		this.account = account;
	}
	/**����ǳ�*/
	public String getNick() {
		return nick;
	}
	/**�����ǳ�*/
	public void setNick(String nick) {
		this.nick = nick;
	}
	/**��ö�̬��Ϣ*/
	public String getTrends() {
		return trends;
	}
	/**���ö�̬��Ϣ*/
	public void setTrends(String trends) {
		this.trends = trends;
	}	
	/**����Ƿ����ߵ���Ϣ*/
	public int getIsOnline() {
		return isOnline;
	}
	/**����������Ϣ*/
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
}

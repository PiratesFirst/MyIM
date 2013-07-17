package com.apple.im.view.buddy;

/**好友类，包含帐号，头像，动态，昵称，以及是否在线等信息*/
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
	
	/**获得头像*/
	public int getAvatar() {
		return avatar;
	}
	/**设置头像*/
	public void setAvatar(int avatar) {
		this.avatar = avatar;
	}
	/**获得帐号*/
	public int getAccount() {
		return account;
	}
	/**设置帐号*/
	public void setAccount(int account) {
		this.account = account;
	}
	/**获得昵称*/
	public String getNick() {
		return nick;
	}
	/**设置昵称*/
	public void setNick(String nick) {
		this.nick = nick;
	}
	/**获得动态信息*/
	public String getTrends() {
		return trends;
	}
	/**设置动态信息*/
	public void setTrends(String trends) {
		this.trends = trends;
	}	
	/**获得是否在线的信息*/
	public int getIsOnline() {
		return isOnline;
	}
	/**设置在线信息*/
	public void setIsOnline(int isOnline) {
		this.isOnline = isOnline;
	}
}

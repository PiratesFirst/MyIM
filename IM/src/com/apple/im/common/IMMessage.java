package com.apple.im.common;

import java.io.Serializable;

public class IMMessage implements Serializable{
	String type;
	String senderNick;
	String groupNick;
	String content;
	String sendTime;
	int gaccount;
	int senderAvatar;
	int receiver;
	int senderAccount;
	
	/**获得信息类型*/
	public String getType() {
		return type;
	}
	
	/**设置信息类型*/
	public void setType(String type) {
		this.type = type;
	}
	
	/**获得发送方帐号*/
	public int getSender() {
		return senderAccount;
	}
	
	/**设置发送方帐号*/
	public void setSender(int senderAccount) {
		this.senderAccount = senderAccount;
	}
	
	/**获得发送方昵称*/
	public String getSenderNick() {
		return senderNick;
	}
	
	/**设置发送方昵称*/
	public void setSenderNick(String senderNick) {
		this.senderNick = senderNick;
	}
	
	/**获得发送方头像*/
	public int getSenderAvatar() {
		return senderAvatar;
	}
	
	/**设置发送方头像*/
	public void setSenderAvatar(int senderAvatar) {
		this.senderAvatar = senderAvatar;
	}
	
	/**获得接收方帐号*/
	public int getReceiver() {
		return receiver;
	}
	
	/**设置接收方帐号*/
	public void setReceiver(int receiver) {
		this.receiver = receiver;
	}
	
	/**获得数据内容：如好友信息，组信息，
	/**普通信息等*/
	public String getContent() {
		return content;
	}
	
	/**设置数据内容:如好友信息，组信息,普通信息等*/
	public void setContent(String content) {
		this.content = content;
	}
	
	/**获得发送时间*/
	public String getSendTime() {
		return sendTime;
	}
	
	/**设置发送时间*/
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	
	/**获得组帐号*/
	public int getGaccount() {
		return gaccount;
	}
	
	/**设置组帐号*/
	public void setGaccount(int gaccount) {
		this.gaccount = gaccount;
	}
	
	/**获得组昵称*/
	public String getGroupNick() {
		return groupNick;
	}
	
	/**设置组昵称*/
	public void setGroupNick(String groupNick) {
		this.groupNick = groupNick;
	}

}

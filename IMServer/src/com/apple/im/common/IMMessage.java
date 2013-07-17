package com.apple.im.common;

import java.io.Serializable;


/**��Ϣ�࣬��Ҫ�����շ�˫�����ʺ�
 * �����ͷ�ͷ�񣬷��ͷ��ǳƣ���Ϣ����
��Ϣ���������ݣ����ʺţ����ǳƣ�����ʱ��*/
public class IMMessage implements Serializable{
	
	String type;
	String groupNick;
	String senderNick;
	String content; 
	String sendTime;
	int gaccount;
	int senderAccount;
	int senderAvatar;
	int receiverAccount;
	
	/**�����Ϣ����*/
	public String getType() {
		return type;
	}
	/**������Ϣ����*/
	public void setType(String type) {
		this.type = type;
	}
	/**��÷��ͷ��ʺ�*/
	public int getSenderAccount() {
		return senderAccount;
	}
	/**���÷��ͷ��ʺ�*/
	public void setSenderAccount(int senderAccount) {
		this.senderAccount = senderAccount;
	}
	/**��÷��ͷ��ǳ�*/
	public String getSenderNick() {
		return senderNick;
	}
	/**���÷��ͷ��ǳ�*/
	public void setSenderNick(String senderNick) {
		this.senderNick = senderNick;
	}
	/**��÷��ͷ�ͷ��*/
	public int getSenderAvatar() {
		return senderAvatar;
	}
	/**���÷��ͷ�ͷ��*/
	public void setSenderAvatar(int senderAvatar) {
		this.senderAvatar = senderAvatar;
	}
	/**��ý��շ��ʺ�*/
	public int getReceiverAccount() {
		return receiverAccount;
	}
	/**���ý��շ��ʺ�*/
	public void setReceiverAccount(int receiverAccount) {
		this.receiverAccount = receiverAccount;
	}
	
	/**����������ݣ��������Ϣ������Ϣ����ͨ��Ϣ��*/
	public String getContent() {
		return content;
	}
	
	/**������������:�������Ϣ������Ϣ����ͨ��Ϣ��*/
	public void setContent(String content) {
		this.content = content;
	}
	/**��÷���ʱ��*/
	public String getSendTime() {
		return sendTime;
	}
	/**���÷���ʱ��*/
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	/**������ʺ�*/
	public int getGaccount() {
		return gaccount;
	}
	/**�������ʺ�*/
	public void setGaccount(int gaccount) {
		this.gaccount = gaccount;
	}
	/**������ǳ�*/
	public String getGroupNick() {
		return groupNick;
	}
	/**�������ǳ�*/
	public void setGroupNick(String groupNick) {
		this.groupNick = groupNick;
	}
}

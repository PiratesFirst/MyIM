package com.apple.im.model;

import java.io.IOException;
import java.io.ObjectOutputStream;

import com.apple.im.common.ClientTime;
import com.apple.im.common.IMMessage;
import com.apple.im.control.ManageClientConnectServer;
import com.apple.im.view.more.MoreActivity;

public class SendMessage {
	/**发送消息的类，发送普通消息，发送更新好友列表消息*/
	public static void sendMes(int dfAccount,String content,String type){
		int myAccount = MoreActivity.me.getAccount();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream
					(ManageClientConnectServer.getClientConnectServerThread(myAccount).getSocket().getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//发送消息
		IMMessage mes = new IMMessage();
		mes.setContent(type);
		mes.setSender(myAccount);
		mes.setSenderNick(MoreActivity.me.getNick());
		mes.setSenderAvatar(MoreActivity.me.getAvatar());
		mes.setReceiver(dfAccount);
		mes.setContent(content);
		mes.setSendTime(ClientTime.geTimeNoSecond());
		try {
			oos.writeObject(mes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void sendADbuddy(int myAccount,int dfAccount ,String type){
		try{
			ObjectOutputStream oos = new ObjectOutputStream
			(ManageClientConnectServer.getClientConnectServerThread(myAccount).getSocket().getOutputStream());
			IMMessage m=new IMMessage();
			m.setType(type);
			m.setSender(myAccount);
			m.setReceiver(dfAccount);
			oos.writeObject(m);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

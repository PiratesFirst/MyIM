package com.apple.im.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.apple.im.common.IMMessage;
import com.apple.im.common.InfoType;

public class ServerConnectClientThread extends Thread{
	Socket socket;
	public ServerConnectClientThread(Socket socket){
		this.socket = socket;
	}
	
	public void run(){
		while(true){
			ObjectInputStream ois = null;
			IMMessage mes = null;
			try {
				ois = new ObjectInputStream(socket.getInputStream());
				System.out.println("读取对象成功");
				mes = (IMMessage) ois.readObject();
				System.out.println("读取对象成功");
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				try {
					ois.close();
					socket.close();
					System.out.println("读取对象失败");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			if(mes.getType().equals(InfoType.COM_MES)){//如果是普通消息包
					controlAndSendMessage.sendMessageToReceiver(mes);
			}else if(mes.getType().equals(InfoType.GROUP_MES)){ //如果是群消息
					controlAndSendMessage.sendGroupMes(mes);
			}else if(mes.getType().equals(InfoType.GET_ONLINE_FRIENDS)){//如果是请求好友列表
					controlAndSendMessage.sendBuddyList(mes);
			}else if(mes.getType().equals(InfoType.DEL_BUDDY)){ //如果是删除好友
					controlAndSendMessage.delBuddy(mes);
			}	
		}
	}
}

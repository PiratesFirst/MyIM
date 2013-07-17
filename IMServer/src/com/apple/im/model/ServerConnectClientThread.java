package com.apple.im.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import com.apple.im.common.IMMessage;
import com.apple.im.common.IMMessageType;

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
				mes = (IMMessage) ois.readObject();
				if(mes.getType().equals(IMMessageType.COM_MES)){//�������ͨ��Ϣ��
					controlAndSendMessage.sendMessageToReceiver(mes);
				}else if(mes.getType().equals(IMMessageType.GROUP_MES)){ //�����Ⱥ��Ϣ
					controlAndSendMessage.sendGroupMes(mes);
				}else if(mes.getType().equals(IMMessageType.GET_ONLINE_FRIENDS)){//�������������б�
					controlAndSendMessage.sendBuddyList(mes);
				}else if(mes.getType().equals(IMMessageType.DEL_BUDDY)){ //�����ɾ������
					controlAndSendMessage.delBuddy(mes);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				try {
					socket.close();
					ois.close();
				} catch (IOException e1) {	
				}
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
